/**
 * @(#) FirebaseHandler.java
 */

package persistence.FirebaseHandler;

import com.firebase.client.Firebase;
import persistence.PersistenceHandler.PersistenceHandler;
import persistence.PersistenceHandler.StoreObject;

import java.util.Date;

public class FirebaseHandler implements PersistenceHandler
{
	private StoreObject storeObject;
	
	private String APIKey;

	private String URL;

	Firebase ref;

	String day;

	String month;

	String year;

	public FirebaseHandler()
	{

	}

	/** The FirebaseHandler class constructer, takes in two Strings, the APIKey and the URL.
	 *
	 * @param APIKey			The FirebaseHandler APIKey
	 * @param URL				The FirebaseHandler URL
	 * @return			        An array that stores the Photons ID and the Data we received
	 */
	public FirebaseHandler(String APIKey, String URL)
	{
		this.APIKey = APIKey;
		this.URL = URL;
		ref = new Firebase(this.URL);
	}

	public String getAPIKey()
	{
		return APIKey;
	}

	public String getURL()
	{
		return URL;
	}

	public void setAPIKey(String apiKey)
	{
		APIKey = apiKey;
	}
	
	public boolean store( StoreObject data )
	{
		Date date = new Date();
		String day = "", month = "", year = "";

		day = date.toString().substring(4,7);
		month = date.toString().substring(8,10);
		year = date.toString().substring(24,28);

		return false;
	}

	/** The validateId method, takes in one Strings.
	 *
	 * @param data			Data(id) to be check if it is in Firebase
	 * @return			    True if id is in firebase, false if it isn't
	 */
	public boolean validateId(String data)
	{
		String newURL = URL+"/devices/0/id";
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
		});
	}
}
