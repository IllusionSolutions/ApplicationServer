/**
 * @(#) PersistenceHandler.java
 */

package com.illusionsolutions.persistence.PersistenceHandler;

public interface PersistenceHandler
{
	boolean store(StoreObject data);
	void retrieve(String id, String message);
}
