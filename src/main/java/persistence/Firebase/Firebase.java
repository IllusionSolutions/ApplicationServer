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


	public StoreObject buildStore(String[] data)
	{
		return null;
	}
	
	public boolean store( StoreObject data )
	{
		return false;
	}
}
