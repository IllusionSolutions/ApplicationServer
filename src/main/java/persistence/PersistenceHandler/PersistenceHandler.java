/**
 * @(#) PersistenceHandler.java
 */

package persistence.PersistenceHandler;
import exceptions.DeviceNotFoundException;

public interface PersistenceHandler
{
	boolean store(StoreObject data);
}
