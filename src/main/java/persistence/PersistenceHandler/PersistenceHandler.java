/**
 * @(#) PersistenceHandler.java
 */

package persistence.PersistenceHandler;

public interface PersistenceHandler
{
	boolean store(StoreObject data);
	StoreObject buildStore(String[] data);
}
