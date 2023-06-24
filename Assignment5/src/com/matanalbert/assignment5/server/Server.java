package com.matanalbert.assignment5.server;

import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.util.FileIO;
import com.matanalbert.assignment5.util.Logger;

public class Server {
    public static void main(String[] args) {
        BuildAuto buildAuto = new BuildAuto();
        FileIO fileIO = new FileIO(new Logger("log.txt"));

        BuildCarModelOptions buildCarModelOptions = new BuildCarModelOptions(buildAuto, fileIO);

        PropertiesServer propertiesServer = new PropertiesServer(4444, buildAuto, fileIO);
        propertiesServer.start();
    }
}
