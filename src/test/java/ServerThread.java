
/**
 * Created by Stuart on 2016/06/26.
 */

import persistence.PersistenceHandler.*;
import persistence.Firebase.*;
import io.moquette.server.*;


import javax.inject.*;
import java.net.Socket;

public class ServerThread extends Thread
{
    private PhotonServer photonServer;
    @Inject
    private PersistenceHandler persistenceHandler;
    @Inject
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
