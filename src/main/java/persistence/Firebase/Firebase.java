/**
 * @(#) Firebase.java
 */

package persistence.Firebase;

import persistence.PersistenceHandler.PersistenceHandler;
import persistence.PersistenceHandler.StoreObject;

public class Firebase implements PersistenceHandler
{
	private StoreObject storeObject;
	
	private String APIKey;
	
	private String URL;

	public Firebase()
	{

	}

	/** The Firebase class constructer, takes in two Strings, the APIKey and the URL.
	 *
	 * @param APIKey			The Firebase APIKey
	 * @param URL				The Firebase URL
	 * @return			        An array that stores the Photons ID and the Data we received
	 */
	public Firebase(String APIKey, String URL)
	{
		this.APIKey = APIKey;
		this.URL = URL;
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
		return false;
	}
}
