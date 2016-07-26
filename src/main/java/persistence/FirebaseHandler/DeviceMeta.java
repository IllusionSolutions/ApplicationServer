package persistence.FirebaseHandler;

public class DeviceMeta
{
    // id
    //name
    // address
    private String id;
    private String name;
    private String appliance;

    public DeviceMeta() {/*Blank default constructor essential for Firebase*/}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAppliance()
    {
        return appliance;
    }

    public void setAppliance(String appliance)
    {
        this.appliance = appliance;
    }
}
