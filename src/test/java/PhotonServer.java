/**
 * Created by Stuart on 2016/06/26.
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import io.moquette.interception.InterceptHandler;
import io.moquette.server.Server;
import io.moquette.server.config.FilesystemConfig;
import io.moquette.server.config.IConfig;
import org.apache.log4j.BasicConfigurator;

public class PhotonServer
{

    public PhotonServer() throws IOException
    {
        BasicConfigurator.configure();
        File configFile = new File("test.conf");

        final IConfig classPathConfig = new FilesystemConfig(configFile);
        final Server mqttBroker = new Server();
        final List<? extends InterceptHandler> userHandlers = Arrays.asList(new PublisherListener());

        mqttBroker.startServer(classPathConfig, userHandlers);

        System.out.println("MQTT Server Started.");
    }

    public static void main(String [] args) throws IOException
    {
        new PhotonServer();
    }

}