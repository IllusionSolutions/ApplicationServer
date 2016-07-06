/**
 * @(#) PersistenceHandler.java
 */

package persistence.PersistenceHandler;

import persistence.Firebase.StoreObject;
import persistence.Firebase.RequestObject;

public interface PersistenceHandler
{
	public boolean store( String data );
	public String retrieve( );
	public StoreObject buildStore( );
	public RequestObject buildRequest( );
	
}
