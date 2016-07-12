/**
 * @(#) PersistenceHandler.java
 */

package persistence.PersistenceHandler;

public interface PersistenceHandler
{
	public boolean store(StoreObject data);
	public String retrieve();
	public StoreObject buildStore(String data);
}
