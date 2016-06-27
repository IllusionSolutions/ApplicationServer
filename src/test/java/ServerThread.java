/**
 * Created by Stuart on 2016/06/26.
 */

import java.net.*;

import persistence.Firebase.*;
import persistence.PersistenceHandler.*;

public class ServerThread extends Thread
{
    private PhotonServer photonServer;
    private PersistenceHandler persistenceHandler;
    private Socket socket;
    public ServerThread(PhotonServer photonServer, Socket socket)
    {
        this.photonServer = photonServer;
        this.socket = socket;
        this.persistenceHandler = new Firebase();
    }

    public void run()
    {

    }

}
