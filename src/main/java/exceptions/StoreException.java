/**
 *	@file StoreException.java
 *  @class StoreException
 *	@author IllusionSolutions
 *	@brief An exception thrown when trying to store in database
 */

package exceptions;

public class StoreException extends Exception{

    /**
     * Constructs a new StoreException with null as its detail message.
     */
    public StoreException() {}

    /**
     * Constructs a new StoreException with the specified detail message
     * @param message
     */
    public StoreException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new StoreException with the specified detail message and cause.
     * @param message
     * @param cause
     */
    public StoreException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Constructs a new StoreException with the specified cause and a detail message of cause.toString().
     * @param cause
     */
    public StoreException(Throwable cause)
    {
        super(cause);
    }
}
