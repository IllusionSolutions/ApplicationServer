import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import persistence.PersistenceHandler.PersistenceHandler;
import persistence.PersistenceHandler.StoreObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

class PublisherListener extends AbstractInterceptHandler
{
    private PersistenceHandler persistenceHandler;
    private String id = "";
    private String payload = "";
    private String array[];
    private StoreObject toStore;

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
        System.out.println("\nAttempting to store data:\n=============================\n");
        String[] temp = this.parse(message);
        System.out.println(temp[1]);
        this.storeData(temp);
    }

    /** Takes in the InterceptPublishMessage message, and seperates the Photons ID
     * from the payload, namely the Current, Voltage and Power, which
     * are all stored in a JSON String. The parse function returns a String array
     * that stores the ID and the payload JSON String.
     *
     * @param message			The data we receive from the Photon
     * @return			        An array that stores the Photons ID and the Data we received
     */
    private String[] parse(InterceptPublishMessage message)
    {
        array = new String[2];
        id = message.getClientID();
        payload = new String(message.getPayload().array());
        array[0] = id;
        array[1] = payload;

        return array;
    }

    /** Takes in a String array which contains the data received from the Photon device.
     * This data is then extracted from the message, and used to populate a StoreObject
     * which is passed to the PersistenceHandler object via the store() method.
     *
     * @param message           The String array containing the data from the Photon.
     */
    public void storeData(String[] message)
    {
        toStore = new StoreObject();

        double current = 0.0;
        double voltage = 0.0;
        double truePower = 0.0;
        double reactivePower = 0.0;
        double apparentPower = 0.0;
        long datetime = 0;

        try
        {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(message[1]);

            current = (Double) jsonObject.get("current");
            voltage = (Double) jsonObject.get("voltage");
            truePower = (Double) jsonObject.get("truePower");
            datetime = (Long) jsonObject.get("time");


//            double truePower = Double.parseDouble((String) jsonObject.get("True Power"));
//            double reactivePower = Double.parseDouble((String) jsonObject.get("Reactive Power"));
//            double apparentPower = Double.parseDouble((String) jsonObject.get("Apparent Power"));

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        // Stores desired power data
//        toStore.setId(message[0]);
//        toStore.setCurrent(current);
//        toStore.setVoltage(voltage);
//        toStore.setTruePower(truePower);
//        toStore.setReactivePower(reactivePower);
//        toStore.setApparentPower(apparentPower);

        // Stores temp power data
        toStore.setId(message[0]);
        toStore.setCurrent(current);
        toStore.setVoltage(voltage);
        toStore.setTruePower(truePower);
        toStore.setReactivePower(reactivePower);
        toStore.setApparentPower(apparentPower);
        toStore.setDatetime(datetime);

        persistenceHandler.store(toStore);
    }

    /** Takes in a String as a parameter and checks whether said string is in the
     * correct JSON format. This is achieved by passing the String to a JSON parser
     * which will return an exception if the String is not a correct (parseable) format.
     * @param payload               The String which is to be checked.
     * @return                      Returns true if the String is in the correct JSON
     *                              format, else it returns false.
     */
    public boolean validateData(String payload)
    {
        JSONParser parser = new JSONParser();

        try
        {
            parser.parse(payload);
        }
        catch (ParseException e)
        {
            return false;
        }
        return true;
    }

    /** Takes in a String as a parameter and parses it into a JSONObject, which it then
     * returns.
     * @param payload               The String which needs to be parsed into a JSONObject.
     * @return                      A JSONObject which is made up of the parsed input String
     *                              parameter.
     */
    public JSONObject convertToJson(String payload)
    {
        JSONParser parser;
        JSONObject jsonObject = null;
        try
        {
            parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(payload);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return jsonObject;
    }
}