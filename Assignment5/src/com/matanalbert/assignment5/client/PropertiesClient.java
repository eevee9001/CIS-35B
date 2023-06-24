package com.matanalbert.assignment5.client;

import com.matanalbert.assignment5.model.AddAutoRequest;
import com.matanalbert.assignment5.model.AddAutoResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

public class PropertiesClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String propertiesFile = args[0];
        Socket socket = new Socket("localhost", 4444);
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
