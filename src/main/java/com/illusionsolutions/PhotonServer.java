package com.illusionsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import io.moquette.interception.InterceptHandler;
import io.moquette.server.Server;
import io.moquette.server.config.FilesystemConfig;
import io.moquette.server.config.IConfig;
import org.apache.log4j.BasicConfigurator;
import com.illusionsolutions.persistence.FirebaseHandler.FirebaseHandler;

public class PhotonServer
{
    private File configFile, authorizationFile;
    private IConfig classPathConfig;
    private Server mqttBroker;
    private List<? extends InterceptHandler> userHandlers;
    private String storageURL;

    public PhotonServer(String firebaseUrl) throws IOException
    {

        BasicConfigurator.configure();

        //Default Auth and config files
        configFile = new File("server.conf");
        authorizationFile = new File("auth.json");

        storageURL = firebaseUrl;
        classPathConfig = new FilesystemConfig(configFile);
        mqttBroker = new Server();

        FirebaseHandler firebase = new FirebaseHandler(new FileInputStream(authorizationFile), storageURL);
        userHandlers = Arrays.asList(new PublisherListener(firebase));
        startServer();
    }

    public void setConfigFile(String configFile) throws IOException
    {
        this.configFile = new File(configFile);
    }

    public void setAuthorizationFile(String authFile)
    {
        this.authorizationFile = new File(authFile);
    }

    public boolean startServer() throws IOException
    {
        if (mqttBroker != null)
        {
            mqttBroker.startServer(classPathConfig, userHandlers);
            System.out.println("\n\nPhoton Server Started.\n\n");
            return true;

        }
        else
        {
            return false;
        }
    }

    public void stopServer()
    {
        if (mqttBroker != null)
            mqttBroker.stopServer();
    }

    public static void main(String [] args)
    {
        try {
            new PhotonServer("https://powercloud-bf968.firebaseio.com/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}