/**
 * @(#) FirebaseHandler.java
 */

package persistence.FirebaseHandler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import persistence.PersistenceHandler.PersistenceHandler;
import persistence.PersistenceHandler.StoreObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FirebaseHandler implements PersistenceHandler
{
	private StoreObject storeObject;

	private String APIKey;

	private String URL;

	private final String [] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

	private DatabaseReference powerCloudRef;

	private FirebaseDatabase powerCloud;

	private FileInputStream authentication;

	public FirebaseHandler(FileInputStream authenticationFile, String databaseUrl)
	{
		this.URL = databaseUrl;
		authentication = authenticationFile;
		FirebaseOptions options;
		options = new FirebaseOptions.Builder()
				.setDatabaseUrl(URL)
				.setServiceAccount(authentication)
				.build();
		FirebaseApp.initializeApp(options);
		powerCloud = FirebaseDatabase.getInstance();

		// Initialize the app with a service account, granting admin privileges
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

		storeObject = data;
		validateId(data.getId());

		return true;
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


	/** Set the Firebase URL needed to store data.
	 *
	 * @param url			The URL of the database. https://[url]
	 */
	public void setURL(String url)
	{
		this.URL = url;
	}

	/** The validateId method, takes in one Strings.
	 *
	 * @param id			Data(id) to be check if it is in Firebase. Calls the store(int) method.
	 */
	private void validateId(String id)  {
		//Creating the Firebase reference
		powerCloud = FirebaseDatabase.getInstance();
		powerCloudRef = powerCloud.getReference("/");

		powerCloudRef.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot)
				{
					boolean found = false;
					int index = -1;

					if (dataSnapshot.getChildrenCount() > 0)
					{
						System.out.println(dataSnapshot.getChildrenCount());
						for (DataSnapshot c : dataSnapshot.getChildren())
						{
							DeviceMeta d = c.child("meta/").getValue(DeviceMeta.class);

							if (d.getId().equals(id))
							{
								found = true;
								index = Integer.parseInt(c.getKey());
								store(index);
								break;
							}
						}

						store(index);
					}
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {
					System.out.println("The read failed: " + databaseError.getCode());
				}
			});
	}

	/** The store method, takes in one Integer.
	 *
	 * @param id			The id where the data must be placed in Firebase.
	 */
	private boolean store(int id)
	{
		String day;
		String month;
		String year;
		int device;
		int month_int;
		int day_int;

		device = id;
		day = "";
		month = "";
		year = "";
		Date date = new Date();
		System.out.println("DEVICE ID " + device);

		if (device != -1)
		{
			String[] temp = date.toString().split(" ");

			day = temp[2];
			month = temp[1];
			year = temp[5];

			System.out.println("\nDate: " + day + "/" + month + "/" + year);
			day_int = Integer.parseInt(day);
			day_int -= 1;
			month_int = checkMonth(month);

			//Creating the Firebase reference
			String tempURL = device + "/data/" + year + "/" + month_int + "/" + day_int + "/" + storeObject.getDatetime();
			powerCloudRef = powerCloud.getReference(tempURL);

			System.out.println("ParsedURL: " + tempURL);
			System.out.println("\nFirebase App: " + powerCloud.getApp().getName());
			System.out.println("URL: " + getURL() + tempURL);
			System.out.println("Reference" + powerCloud.getReference().toString());
			System.out.println("URL Reference" + powerCloud.getReferenceFromUrl(getURL() + tempURL).toString());
			System.out.println("Data: " + storeObject.toString());


			//powerCloudRef = powerCloudRef.push();
			powerCloudRef.setValue(storeObject, new DatabaseReference.CompletionListener() {
				@Override
				public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
					if (databaseError != null) {
						System.out.println("What is this? Amateur hour?");
						System.out.println(databaseError.getMessage());
					}
					else
						{
						System.out.println("Success!");
					}
				}
			});
			return true;
		}
		return false;
	}
}
