import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import persistence.PersistenceHandler.PersistenceHandler;


class PublisherListener extends AbstractInterceptHandler
{
    private PersistenceHandler persistenceHandler;
    private String id = "";
    private String payload = "";
    private String array[];

    PublisherListener(PersistenceHandler handler)
    {
        super();
        persistenceHandler = handler;
    }

    @Override
    public void onPublish(InterceptPublishMessage message)
    {
        System.out.println(
                "\nClient " + message.getClientID() +
                "\nTopic: " + message.getTopicName() +
                "\nMessage: " + new String(message.getPayload().array())
        );
    }

    /** Takes in the InterceptPublishMessage message, and seperates the Photons ID
     * from the payload, namely the Current, Voltage and Power, which
     * are all stored in a JSON String. The retrieve function returns a String array
     * that stores the ID and the payload JSON String.
     *
     * @param message			The data we receive from the Photon
     * @return			        An array that stores the Photons ID and the Data we received
     */
    private String[] retrieve(InterceptPublishMessage message)
    {
        array = new String[2];
        id = message.getClientID();
        payload = new String(message.getPayload().array());
        array[0] = id;
        array[1] = payload;

        return array;
    }
}