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
import persistence.FirebaseHandler.FirebaseHandler;

public class PhotonServer
{
    // Declaring class members
    private File configFile;
    private IConfig classPathConfig;
    private Server mqttBroker;
    private List<? extends InterceptHandler> userHandlers;
    
    public PhotonServer() throws IOException
    {
        BasicConfigurator.configure();
        configFile = new File("server.conf");

        classPathConfig = new FilesystemConfig(configFile);
        mqttBroker = new Server();

        FirebaseHandler firebase = new FirebaseHandler(new FileInputStream("auth.json"));
        firebase.setURL("https://powercloud-bf968.firebaseio.com/");
        userHandlers = Arrays.asList(new PublisherListener(firebase));

        mqttBroker.startServer(classPathConfig, userHandlers);
        System.out.println("Photon Server Started.");
    }

    public static void main(String [] args) throws IOException
    {
        new PhotonServer();
    }
}