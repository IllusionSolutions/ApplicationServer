package com.illusionsolutions;

import com.illusionsolutions.persistence.PersistenceHandler.Calculations;
import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import com.illusionsolutions.persistence.PersistenceHandler.PersistenceHandler;
import com.illusionsolutions.persistence.PersistenceHandler.StoreObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublisherListener extends AbstractInterceptHandler
{
    private PersistenceHandler persistenceHandler;
    private Calculations calculations;
    private String id = "";
    private String payload = "";
    private String array[];
    private StoreObject toStore;

    public PublisherListener(PersistenceHandler handler)
    {
        super();
        persistenceHandler = handler;
    }

    @Override
    public void onPublish(InterceptPublishMessage message)
    {
        String topic = message.getTopicName();
        System.out.println(
                "\nClient " + message.getClientID() +
                "\nTopic: " + message.getTopicName() +
                "\nMessage: " + new String(message.getPayload().array())
        );
        System.out.println("\nAttempting to store data:\n=============================\n");
        String[] temp = this.parse(message);
        System.out.println(temp[1]);
        System.out.println("Message : " + topic);

        if(topic.equals("powercloud"))
            this.storeData(temp, topic);
        else if(topic.equals("powernotify"))
            this.notification(temp, topic);
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
    public void storeData(String[] message, String topic) {
        toStore = new StoreObject();

        double current = 0.0;
        double voltage = 0.0;
        double power = 0.0;
        long datetime = 0;

        if(validateData(message[1], topic))
        {
            JSONObject jsonObject = convertToJson(message[1]);

            current = (Double) jsonObject.get("current");
            voltage = (Double) jsonObject.get("voltage");
            power = (Double) jsonObject.get("power");//Kilowatt/hours
            datetime = (Long) jsonObject.get("time");

            toStore.setId(message[0]);
            toStore.setCurrent(current);
            toStore.setVoltage(voltage);
            toStore.setPower(power);
            toStore.setDatetime(datetime);

            calculations = new Calculations(power);
            toStore.setCalculations(calculations);

            persistenceHandler.store(toStore);
        }
        else
        {
            System.err.println("Invalid JSON Object\nFrom: " +  message[0] + "\n");
        }
    }

    public void notification(String[] message, String topic)
    {

        if(validateData(message[1], topic))
        {
            String user = "";
            String payload = "";
            long datetime = 0;
            String content = "";

            try {

                JSONObject jsonObject = convertToJson(message[1]);
                user = (String) jsonObject.get("user");
                payload = (String) jsonObject.get("status");
                datetime = (Long) jsonObject.get("time");
                Date date = new Date(datetime*1000);
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                content = "===========Date:" + format.format(date) + "===========\n" +
                          "User: " + user + "\n" +
                          "Status: " + payload + "\n" +
                          "==============================================\n";

                File file = new File("notifications.txt");

                // if file doesnt exists, then create it
                if (!file.exists())
                {
                    System.out.println("New File");
                    file.createNewFile();

                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    out.write(content);
                    out.close();
                }
                else
                {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String load = "";
                    String str = "";
                    while ((str = in.readLine()) != null)
                    {
                        load += str + "\n";
                    }
                    load += "\n" + content;

                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    out.write(load);
                    out.close();
                }

                System.out.println("Done");

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.err.println("Invalid JSON Object\nFrom: " +  message[0] + "\n");
        }

    }

    /** Takes in a String as a parameter and checks whether said string is in the
     * correct JSON format. This is achieved by passing the String to a JSON parser
     * which will return an exception if the String is not a correct (parseable) format.
     * @param payload               The String which is to be checked.
     * @return                      Returns true if the String is in the correct JSON
     *                              format, else it returns false.
     */
    public boolean validateData(String payload, String topic)
    {
        JSONParser parser = new JSONParser();

        try
        {
            JSONObject jsonObject = (JSONObject) parser.parse(payload);

            if((jsonObject.size() < 4 && jsonObject.size() > 4) && topic.equals("powercloud"))
                return false;
            else if((jsonObject.size() < 3 && jsonObject.size() > 3) && topic.equals("powernotify"))
                return false;

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