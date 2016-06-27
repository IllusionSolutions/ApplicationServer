/**
 * @(#) Firebase.java
 */

package persistence.Firebase;

import persistence.PersistenceHandler.PersistenceHandler;

public class Firebase implements PersistenceHandler
{
	private StoreObject storeObject;
	
	private RequestObject requestObject;
	
	private String APIKey;
	
	private String URI;
	
	public String retrieve( )
	{
		return null;
	}
	
	public StoreObject buildStore( )
	{
		return null;
	}
	
	public RequestObject buildRequest( )
	{
		return null;
	}
	
	public boolean store( String data )
	{
		return false;
	}
	
	
}
