package com.illusionsolutions.persistence.FirebaseHandler;

public class DeviceMeta
{
    // id
    //name
    // address
    private String id;
    private String name;
    private String appliance;

    public DeviceMeta() {/*Blank default constructor essential for Firebase*/}

    /** Returns the name of the device.
    *
    * @return           Returns the name of the device as a String.
    */
    public String getName()
    {
        return name;
    }

    /** Takes in a String object, and sets the name of the device to that String.
    *
    * @param name       String which contains the desired name of the device.
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /** Returns the ID of the device.
    *
    * @return           Returns the ID of the device as a String.
    */
    public String getId()
    {
        return id;
    }

    /** Takes in a String object, and sets the ID of the device to that String.
    *
    * @param id         String which contains the desired ID of the device.
    */
    public void setId(String id)
    {
        this.id = id;
    }

    /** Returns the appliance name to which the device is connected.
    *
    * @return           Returns the name of the appliance to which the device is connected as a String.
    */
    public String getAppliance()
    {
        return appliance;
    }

    /** Takes in a String object, and sets the name of the appliance to which the device is connected.
    *
    * @param appliance  String which contains the desired appliance name.
    */
    public void setAppliance(String appliance)
    {
        this.appliance = appliance;
    }
}
