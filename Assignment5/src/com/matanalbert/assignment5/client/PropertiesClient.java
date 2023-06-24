package com.matanalbert.assignment5.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

public class PropertiesClient {
    public static void main(String[] args) throws IOException {
        String propertiesFile = args[0];
        Socket socket = new Socket("localhost", 4444);
        System.out.println("Connected to server");
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             FileInputStream in = new FileInputStream(propertiesFile)) {

            Properties properties = new Properties();
            properties.load(in);
            System.out.println("Loaded " + propertiesFile);
            out.writeObject(properties);
            System.out.println("Sent properties to server");
        }
    }
}
