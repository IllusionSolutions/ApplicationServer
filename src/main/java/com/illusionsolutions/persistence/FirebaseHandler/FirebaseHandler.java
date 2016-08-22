/**
 * @(#) FirebaseHandler.java
 */

package com.illusionsolutions.persistence.FirebaseHandler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.illusionsolutions.persistence.PersistenceHandler.PersistenceHandler;
import com.illusionsolutions.persistence.PersistenceHandler.StoreObject;

import java.io.FileInputStream;
import java.util.Date;

public class FirebaseHandler implements PersistenceHandler
{
	private StoreObject storeObject;
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

	/** The FirebaseHandler class constructer, takes in one string the URL.
	 *
	 * @param URL				The FirebaseHandler URL
	 */
	public FirebaseHandler(String URL)
	{
		this.URL = URL;
	}

	public String getURL()
	{
		return URL;
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
	public int checkMonth(String month)
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
		powerCloudRef = powerCloud.getReference("/meta_data");

		powerCloudRef.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot)
				{
					String index = "-1";

					if (dataSnapshot.getChildrenCount() > 0)
					{
						for (DataSnapshot c : dataSnapshot.getChildren())
						{
							System.out.println("----------------->"+c.getKey());

							if (c.getKey().equals(id))
							{
								index = id;
								break;
							}
						}
						store(index);
					}
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {

				}
			});
	}

	/** The store method, takes in one Integer.
	 *
	 * @param id			The id where the data must be placed in Firebase.
	 */
	private boolean store(String id)
	{
		if (!id.equals("-1"))
		{
			String day;
			String month;
			String year;
			String device;
			int month_int;
			int day_int;

			device = id;
			day = "";
			month = "";
			year = "";
			Date date = new Date();

			String[] temp = date.toString().split(" ");

			day = temp[2];
			month = temp[1];
			year = temp[5];

			System.out.println("\nDate: " + day + "/" + month + "/" + year);
			day_int = Integer.parseInt(day);
			day_int -= 1;
			month_int = checkMonth(month);

			//Creating the Firebase reference
			String tempURL = "/device_data/" + device + "/" + year + "/" + month_int + "/" + day_int + "/" + storeObject.getDatetime();
			powerCloudRef = powerCloud.getReference(tempURL);

			System.out.println("\nParsedURL: " + tempURL);
			System.out.println("Firebase App: " + powerCloud.getApp().getName());
			System.out.println("URL: " + getURL() + tempURL);
			System.out.println("Reference: " + powerCloud.getReference().toString());
			System.out.println("URL Reference: " + powerCloud.getReferenceFromUrl(getURL() + tempURL).toString());
			System.out.println("Data: " + storeObject.toString() + "\n");

			powerCloudRef.setValue(storeObject, new DatabaseReference.CompletionListener() {
				@Override
				public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
					if (databaseError != null) {
						System.out.println("What is this? Amateur hour?");
						System.out.println(databaseError.getMessage());
					} else {
						System.out.println("Success!");
					}
				}
			});
			return true;
		}
		else
		{
			System.out.println("Device ID not found. Storage Failed:\n ID: " + storeObject.getId());
			return false;
		}

	}
}
