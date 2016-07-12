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
	
	public String retrieve( )
	{
		return null;
	}

	public StoreObject buildStore(String data)
	{
		return null;
	}
	
	public boolean store( StoreObject data )
	{
		return false;
	}
}
