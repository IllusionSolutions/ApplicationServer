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

	private String [] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

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

	/** Takes in a StoreObject object, and gets the authorization to edit Firebase.
	 * It also determines the reference to the location where the data will be stored
	 * within Firebase
	 *
	 * @param data			StoreObject object, containing the data that needs to be stored.
	 * @return			    If the store is successful it returns true, else it returns false.
	 */
	public boolean store( StoreObject data )
	{
		String day;
		String month;
		String year;
		int device;
		int month_int;
		int day_int;

		// Initialize the app with a service account, granting admin privileges
		try
		{
			FirebaseOptions options;
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

		device = 0;
		day = "";
		month = "";
		year = "";

		/*Splitting the date into:
			Day: eg 14
			Month: eg Jul
			Year: eg 2016

		  From:
		  	on Jul 14 09:51:52 CAT 2016
		 */
		Date date = new Date();
		device = validateId(storeObject.getId());
		day = date.toString().substring(4,7);
		month = date.toString().substring(8,10);
		year = date.toString().substring(24,28);

		day_int = Integer.parseInt(day);
		day_int -= 1;
		month_int = checkMonth(month);

		//Creating the Firebase reference
		powerCloud = FirebaseDatabase.getInstance();
		powerCloudRef = powerCloud.getReference(device + "/data/" + year + "/" + month_int + "/" + day_int);

		return false;
	}

	/** Takes in a month string, and determines the month's index from the month array.
	 *
	 * @param month			Current month
	 * @return			    The index, if the month isn't valid it returns -1
	 */
	private int checkMonth(String month)
	{
		for(int i = 0 ; i < 12 ; i++)
		{
			if(months[i].equals(month))
				return i;
		}
		return  -1;
	}

	/** The validateId method, takes in one Strings.
	 *
	 * @param data			Data(id) to be check if it is in Firebase
	 * @return			    True if id is in firebase, false if it isn't
	 */
	private int validateId(String data)
	{
		/*String newURL = URL+"/devices/0/id";
		Firebase ref = new Firebase(newURL);

		ref.child(data).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				if (snapshot.exists()) {
					return true;
				}
				else {
					return false;
				}
			}

			@Override
			public void onCancelled(FirebaseError firebaseError) { }
		});*/
		return -1;
	}
}
