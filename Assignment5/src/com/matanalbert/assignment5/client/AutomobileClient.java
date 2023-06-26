package com.matanalbert.assignment5.client;

import com.matanalbert.assignment5.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AutomobileClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please upload an automobile properties file:");


        while (scan.hasNextLine()) {
            String fileName = scan.nextLine();
            if (fileName.equals("")) {
                break;
            }
            try {
                uploadAuto(fileName);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<String> models = getModels();
        System.out.println(models);
        for (String model : models) {

            Automobile automobile = downloadAuto(models.get(getModels().indexOf(model)));
            automobile.printData();
        }
    }

    private static void uploadAuto(String arg) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket("localhost", 4444)) {
            String propertiesFile = arg;
            System.out.println("Connected to server");
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 FileInputStream propertiesStream = new FileInputStream(propertiesFile)) {

                Properties properties = new Properties();
                properties.load(propertiesStream);
                System.out.println("Loaded " + propertiesFile);
                out.writeObject(new AddAutoRequest(properties));
                System.out.println("Sent properties to server");
                try (ObjectInputStream serverStream = new ObjectInputStream(socket.getInputStream())) {
                    AddAutoResponse response = (AddAutoResponse) serverStream.readObject();
                    System.out.println("Got response: " + response.getStatus());
                }
            }
        }
    }

    private static List<String> getModels() throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket("localhost", 4444)) {
            System.out.println("Connected to server");
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                out.writeObject(new GetAutoListRequest());
                System.out.println("Sent request to server to get list of models");
                try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    GetAutoListResponse response = (GetAutoListResponse) in.readObject();
                    System.out.println("Got response");
                    System.out.println();
                    return response.getModels();
                }
            }
        }
    }

    private static Automobile downloadAuto(String modelName) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket("localhost", 4444)) {
            System.out.println("Connected to server");
            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                out.writeObject(new DownloadAutoRequest(modelName));
                System.out.println("Sent request to configure automobile");
                try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                    DownloadAutoResponse response = (DownloadAutoResponse) in.readObject();
                    System.out.println("Got response");
                    System.out.println();
                    return response.getAutomobile();
                }
            }
        }
    }
}
