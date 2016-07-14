/**
 * @(#) FirebaseHandler.java
 */

package persistence.FirebaseHandler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import persistence.PersistenceHandler.PersistenceHandler;
import persistence.PersistenceHandler.StoreObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class FirebaseHandler implements PersistenceHandler
{
	private StoreObject storeObject;
	
	private String APIKey;

	private String URL;

	String day;

	String month;

	String year;

	private DatabaseReference powerCloudRef;

	private FirebaseDatabase powerCloud;

	public FirebaseHandler()
	{

	}

	/** The FirebaseHandler class constructer, takes in two Strings, the APIKey and the URL.
	 *
	 * @param APIKey			The FirebaseHandler APIKey
	 * @param URL				The FirebaseHandler URL
	 */
	public FirebaseHandler(String APIKey, String URL)
	{
		this.APIKey = APIKey;
		this.URL = URL;
	}

	public String getAPIKey()
	{
		return APIKey;
	}

	private String getURL()
	{
		return URL;
	}

	public void setAPIKey(String apiKey)
	{
		APIKey = apiKey;
	}

	public boolean store( StoreObject data )
	{
		// Initialize the app with a service account, granting admin privileges

		try
		{
			FirebaseOptions options = null;
			options = new FirebaseOptions.Builder()
                    .setDatabaseUrl(this.getURL())
                    .setServiceAccount(new FileInputStream("authentication_configuration.json"))
                    .build();
			FirebaseApp.initializeApp(options);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}


		powerCloud = FirebaseDatabase.getInstance();
		powerCloudRef = powerCloud.getReference("device/year/month/day");

		Date date = new Date();
		String day = "", month = "", year = "";

		day = date.toString().substring(4,7);
		month = date.toString().substring(8,10);
		year = date.toString().substring(24,28);

		return false;
	}

}
