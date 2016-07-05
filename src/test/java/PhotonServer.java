/**
 * Created by Stuart on 2016/06/26.
 */

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import io.moquette.*;
import io.moquette.server.Server;


public class PhotonServer
{
    private Integer port;

    private LinkedList<String> list;

    public PhotonServer(int portNum) throws IOException
    {
        Server server = new Server();
        File configFile = new File("test.conf");
        server.startServer(configFile);
    }

    public void listen(int port) throws IOException
    {

    }

    public static void main(String [] args) throws IOException
    {
        String arg1 = "5000";//args[0];
        int port = Integer.parseInt(arg1);
        PhotonServer photonServer = new PhotonServer(port);
    }

}