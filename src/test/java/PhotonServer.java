/**
 * Created by Stuart on 2016/06/26.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;

public class PhotonServer
{
    private Integer port;

    private LinkedList<String> list;

    public void startServer( )
    {

    }

    public PhotonServer(int portNum) throws IOException
    {
        listen(portNum);
    }

    public void listen(int port) throws IOException
    {
        ServerSocket socket = new ServerSocket(port);

        // Tell the world we're ready to go
        System.out.println("Listening on " + socket);
        // Keep accepting connections forever
        while(true)
        {
            Socket s = socket.accept();
            System.out.println( "Connection from "+s );
            DataOutputStream dout = new DataOutputStream( s.getOutputStream() );
            DataInputStream din = new DataInputStream(s.getInputStream());
            String user = din.readUTF();
            System.out.println("New user is " + user);

            synchronized(this)
            {
               // outputStreams.put(s, dout);
               // clients.put(dout, user);
            }

            new ServerThread( this, s );
        }
    }



    public void removeConnection(Socket s)
    {
       /* synchronized(outputStreams)
        {
            System.out.println("Removing connection to " + s);
            outputStreams.remove(s);

            try
            {
                s.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error closing " + s);
                ie.printStackTrace();
            }
        }*/
    }

    public static void main(String [] args) throws IOException
    {
        String arg1 = "5000";//args[0];
        int port = Integer.parseInt(arg1);
        PhotonServer photonServer = new PhotonServer(port);
    }

}