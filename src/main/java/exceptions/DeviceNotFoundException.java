package exceptions;

/**
 * @file DeviceNotFoundException.java
 * @class DeviceNotFoundException
 * @author Illusion Solutions
 * @brief Thrown by Persistence handler.
 *        This exception is thrown when a device is not present in the database.
 */

public class DeviceNotFoundException extends Exception
{
    /**
     * Constructs a new InvalidRequestException with null message.
     */
    public DeviceNotFoundException() { super(); }

    /**
     * Constructs a new InvalidRequestException with the specified detail message and a cause.
     * @param message
     * @param cause
     */
    public DeviceNotFoundException(String message, Throwable cause) { super(message, cause); }

    /**
     * Constructs a new InvalidRequestException with the specified detail message.
     * @param message
     */
    public DeviceNotFoundException(String message) { super(message); }

    /**
     * Constructs a new InvalidRequestException with the specified cause.
     * @param cause
     */
    public DeviceNotFoundException(Throwable cause) { super(cause); }
}
