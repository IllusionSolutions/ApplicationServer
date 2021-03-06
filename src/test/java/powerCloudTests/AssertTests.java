package powerCloudTests;

import com.illusionsolutions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.illusionsolutions.persistence.FirebaseHandler.FirebaseHandler;
import com.illusionsolutions.persistence.PersistenceHandler.Calculations;
import com.illusionsolutions.persistence.PersistenceHandler.StoreObject;
import com.illusionsolutions.PublisherListener;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssertTests
{
    private final String firebaseURL = "https://powercloud-bf968.firebaseio.com/";
    private PhotonServer photonServer;
    private FirebaseHandler fbHandler = null;
    private final String deviceID = "Brandon";
    private PublisherListener pListener;
    private Calculations calculations;

    @Before
    public void createServer()
    {
        try
        {
            photonServer = new PhotonServer(firebaseURL);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Before
    public void prepareFirebase()
    {
        fbHandler = new FirebaseHandler();
    }

    @Before
    public void preparePublisherListener()
    {
        pListener = new PublisherListener(fbHandler);
    }

    //Ensure that the server is running
    @Test
    public void serverCanRun()
    {
        try
        {
            boolean serverRunning = photonServer.startServer();
            assertTrue(serverRunning);
            photonServer.stopServer();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //PublisherListenerTests
 /*   @Test
    public void validateDataReturnsTrue()
    {
        String data = "{\"power\":12.2, \"voltage\":2.1, \"current\":3.6}";
        boolean temp = pListener.validateData(data);
        assertTrue(temp);
    }

    @Test
    public void validateDataReturnsFalse()
    {
        String data = "{\"truePower\":12.2\"voltage\":2.1, \"current\":3.6}";
        boolean temp = pListener.validateData(data);
        assertTrue(temp);
    }

    @Test
    public void validJsonConversion()
    {
        String data = "{\"truePower\":12.2, \"voltage\":2.1, \"current\":3.6}";
        JSONObject temp = pListener.convertToJson(data);
        assertNotNull(temp);
    }

   /* @Test
    public void firebaseNotNull()
    {
        assertNotNull(fbHandler);
    }

    @Test
    public void urlNotNull()
    {
        assertNotNull(fbHandler.getURL());
    }

    @Test
    public void monthConvertedProperly()
    {
        assertEquals(0,fbHandler.checkMonth("Jan"));
    }

    /*@Test
    public void storeWithObjectReturnsTrue()
    {
        StoreObject temp = new StoreObject();
        temp.setDatetime(new Date().getTime());
        temp.setId(deviceID);
        temp.setVoltage(12.2);
        temp.setPower(2.3);
        temp.setCurrent(123.2);

        boolean stored = fbHandler.store(temp);
        assertTrue(stored);
    }

    /*@Test
    public void storeWithObjectReturnsFalse()
    {
        String wrongID = "";
        StoreObject temp = new StoreObject();
        temp.setDatetime(new Date().getTime());
        temp.setId(wrongID);
        temp.setVoltage(22.2);
        temp.setCurrent(77.2);
        temp.setPower(991.2);

        boolean stored = fbHandler.store(temp);
        assertTrue(stored);
    }*/
}