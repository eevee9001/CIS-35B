package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.model.AddAutoRequest;
import com.matanalbert.assignment5.model.AutoRequest;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.util.FileIO;
import com.matanalbert.assignment5.util.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AutomobileServer extends Thread {
    private final BuildAuto buildAuto = new BuildAuto();
    private final FileIO fileIO = new FileIO(new Logger("log.txt"));

    private final int port;
    private ServerSocket server;

    public AutomobileServer(int port) {
        this.port = port;
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
                System.out.println("Client connected on port " + clientSocket.getPort());
                try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());) {
                    AutoRequest request = (AutoRequest) in.readObject();
                    System.out.println("Received request from client");

                    switch (request.getType()) {
                        case ADD_AUTO -> {
                            handleAddAutoRequest((AddAutoRequest) request);
                        }
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error establishing client connection ... ");
                System.exit(1);
            }

        }
    }

    private void handleAddAutoRequest(AddAutoRequest request) throws IOException {
        System.out.println("Received properties from client");
        Automobile automobile = fileIO.buildFromProperties(request.getProperties());
        System.out.println("Created automobile");
        buildAuto.addCreatedAutoToLHM(automobile);
        System.out.println("Added to database: " + automobile.getModel());

    }

    public static void main(String[] args) {
        AutomobileServer automobileServer = new AutomobileServer(4444);
        automobileServer.start();
    }
}
