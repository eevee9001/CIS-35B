package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.model.*;
import com.matanalbert.assignment5.util.FileIO;
import com.matanalbert.assignment5.util.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class BuildCarModelOptions extends Thread implements AutoServer {
    private final BuildAuto buildAuto = new BuildAuto();
    private final FileIO fileIO = new FileIO(new Logger("log.txt"));

    private final int port;
    private ServerSocket server;

    public BuildCarModelOptions(int port) {
        this.port = port;
    }

    @Override
    public void addCreatedAutoToLHM(Automobile automobile) {
        buildAuto.addCreatedAutoToLHM(automobile);
    }

    @Override
    public List<String> getModels() {
        return buildAuto.getModels();
    }

    @Override
    public Automobile getAutomobile(String modelName) {
        return buildAuto.getAutomobile(modelName);
    }

    @Override
    public void run() {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port + " ... ");
            System.exit(1);
        }

        System.out.println("Automobile server started on port: " + port);
        while (true) {
            try {
                Socket clientSocket = null;
                clientSocket = server.accept();
                System.out.println("Client connected on port " + clientSocket.getPort());
                try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
                    AutoRequest request = (AutoRequest) in.readObject();
                    System.out.println("Received request from client");
                    System.out.println();

                    AutoResponse response = null;

                    switch (request.getType()) {
                        case ADD_AUTO -> {
                            response = handleAddAutoRequest((AddAutoRequest) request);
                        }
                        case GET_AUTO_LIST -> {
                            response = handleGetAutoListRequest((GetAutoListRequest) request);
                        }
                        case DOWNLOAD_AUTO -> {
                            response = handleDownloadAutoRequest((DownloadAutoRequest) request);
                        }
                    }

                    if (response != null) {
                        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
                            out.writeObject(response);
                        }
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error establishing client connection ... ");
                System.exit(1);
            }

        }
    }

    private AutoResponse handleAddAutoRequest(AddAutoRequest request) {
        System.out.println("Received properties from client");
        Automobile automobile = null;
        try {
            automobile = fileIO.buildFromProperties(request.getProperties());
        } catch (IOException e) {
            return new AddAutoResponse("Parse error");
        }
        System.out.println("Created automobile");
        addCreatedAutoToLHM(automobile);
        System.out.println("Added to database: " + automobile.getModel());
        System.out.println();
        return new AddAutoResponse("OK");
    }

    private AutoResponse handleGetAutoListRequest(GetAutoListRequest request) {
        System.out.println("Received request to get list of models from client");
        List<String> list = getModels();
        System.out.println("Sent list of models");
        System.out.println();
        return new GetAutoListResponse(list);
    }

    private AutoResponse handleDownloadAutoRequest(DownloadAutoRequest request) {
        System.out.println("Received request to download " + request.getModelName());
        Automobile automobile = getAutomobile(request.getModelName());
        System.out.println("Downloaded automobile");
        System.out.println();
        return new DownloadAutoResponse(automobile);
    }

    public static void main(String[] args) {
        BuildCarModelOptions automobileServer = new BuildCarModelOptions(4444);
        automobileServer.start();

    }
}
