/**
 * @(#) StoreObject.java
 */

package persistence.PersistenceHandler;

public class StoreObject
{
	private String id;
	private double current;
	private double voltage;
	private double truePower;
	private double reactivePower;
	private double apparentPower;

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public double getCurrent()
	{
		return this.current;
	}

	public void setCurrent(double current)
	{
		this.current = current;
	}

	public double getVoltage()
	{
		return this.voltage;
	}

	public void setVoltage(double voltage)
	{
		this.voltage = voltage;
	}

	public double getTruePower()
	{
		return this.truePower;
	}

	public void setTruePower(double truePower)
	{
		this.truePower = truePower;
	}

	public double getReactivePower()
	{
		return this.reactivePower;
	}

	public void setReactivePower(double reactivePower)
	{
		this.reactivePower = reactivePower;
	}

	public double getApparentPower()
	{
		return this.apparentPower;
	}

	public void setApparentPower(double apparentPower)
	{
		this.apparentPower = apparentPower;
	}

	public String toString() {
		return "{ power: " + this.truePower + ", voltage: " + this.voltage + ", current: " + this.current + " }";
	}
}