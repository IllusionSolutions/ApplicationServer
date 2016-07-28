import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import io.moquette.interception.InterceptHandler;
import io.moquette.server.Server;
import io.moquette.server.config.FilesystemConfig;
import io.moquette.server.config.IConfig;
import org.apache.log4j.BasicConfigurator;
import persistence.FirebaseHandler.FirebaseHandler;

public class PhotonServer
{
    private File configFile, authorizationFile;
    private IConfig classPathConfig;
    private Server mqttBroker;
    private List<? extends InterceptHandler> userHandlers;
    private String storageURL;
    
    public PhotonServer() throws IOException
    {
        BasicConfigurator.configure();

        configFile = new File("server.conf");
        authorizationFile = new File("auth.json");

        storageURL = "https://powercloud-bf968.firebaseio.com/";
        classPathConfig = new FilesystemConfig(configFile);
        mqttBroker = new Server();

        FirebaseHandler firebase = new FirebaseHandler(new FileInputStream(authorizationFile), storageURL);
        userHandlers = Arrays.asList(new PublisherListener(firebase));

        mqttBroker.startServer(classPathConfig, userHandlers);
        System.out.println("Photon Server Started.\n\n");
    }

    public static void main(String [] args)
    {
        try {
            new PhotonServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}