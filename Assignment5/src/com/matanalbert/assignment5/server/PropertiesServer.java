package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.util.FileIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class PropertiesServer extends Thread {

    private final int port;
    private final BuildAuto buildAuto;
    private final FileIO fileIO;
    private ServerSocket server;

    public PropertiesServer(int port, BuildAuto buildAuto, FileIO fileIO) {
        this.port = port;
        this.buildAuto = buildAuto;
        this.fileIO = fileIO;
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port + " ... ");
            System.exit(1);
        }

        System.out.println("Properties server started on port: " + port);
        while (true) {
            try {
                Socket clientSocket = null;
                clientSocket = server.accept();
                System.out.println("Client connected on port " + clientSocket.getPort() );
                try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());) {
                    Properties properties = (Properties) in.readObject();
                    System.out.println("Received properties from client");
                    Automobile automobile = fileIO.buildFromProperties(properties);
                    System.out.println("Created automobile");
                    buildAuto.addCreatedAutoToLHM(automobile);
                    System.out.println("Added to database: " + automobile.getModel());
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error establishing client connection ... ");
                System.exit(1);
            }

        }
    }
}
