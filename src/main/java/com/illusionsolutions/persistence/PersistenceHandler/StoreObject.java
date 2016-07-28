/**
 * @(#) StoreObject.java
 */

package com.illusionsolutions.persistence.PersistenceHandler;

public class StoreObject
{
	private String id;
	private double current;
	private double voltage;
	private double truePower;
	private double reactivePower;
	private double apparentPower;
	private long datetime;

	/** Returns the date and time when the device's reading took place.
	*
	* @return 				Returns a unix timestamp of the time the device took a reading, stored as a long.
	*/
	public long getDatetime() {
		return datetime;
	}

	/** Takes in a long, and sets the unix timestamp of the reading to that value.
	*
	* @param datetime 		Long which contains the desired unix timestamp of the reading.
	*/
	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	/** Returns the ID of the device which took the reading.
	*
	* @return 				Returns the ID of the device as a String.
	*/
	public String getId()
	{
		return this.id;
	}

	/** Takes in a String object, and sets the ID of the device which took the reading to this String.
	*
	* @param id 			String which contains the desired device ID.
	*/
	public void setId(String id)
	{
		this.id = id;
	}

	/** Returns the current recorded during a reading.
	*
	* @return 				Returns the current as a double.
	*/
	public double getCurrent()
	{
		return this.current;
	}

	/** Takes in a double, and sets the current measured during a reading to this double.
	*
	* @param current 		Double which contains the desired current.
	*/
	public void setCurrent(double current)
	{
		this.current = current;
	}

	/** Returns the voltage recorded during a reading.
	*
	* @return 				Returns the voltage as a double.
	*/
	public double getVoltage()
	{
		return this.voltage;
	}

	/** Takes in a double, and sets the voltage measured during a reading to this double.
	*
	* @param voltage 		Double which contains the desired voltage.
	*/
	public void setVoltage(double voltage)
	{
		this.voltage = voltage;
	}

	/** Returns the true power recorded during a reading.
	*
	* @return 				Returns the true power as a double.
	*/
	public double getTruePower()
	{
		return this.truePower;
	}

	/** Takes in a double, and sets the true power measured during a reading to this double.
	*
	* @param truePower 		Double which contains the desired true power.
	*/
	public void setTruePower(double truePower)
	{
		this.truePower = truePower;
	}

	/** Returns the reactive power recorded during a reading.
	*
	* @return 				Returns the reactive power as a double.
	*/
	public double getReactivePower()
	{
		return this.reactivePower;
	}

	/** Takes in a double, and sets the reactive power measured during a reading to this double.
	*
	* @param reactivePower 	Double which contains the desired reactive power.
	*/
	public void setReactivePower(double reactivePower)
	{
		this.reactivePower = reactivePower;
	}

	/** Returns the apparent power recorded during a reading.
	*
	* @return 				Returns the apparent power as a double.
	*/
	public double getApparentPower()
	{
		return this.apparentPower;
	}

	/** Takes in a double, and sets the apparent power measured during a reading to this double.
	*
	* @param apparentPower 	Double which contains the desired apparent power.
	*/
	public void setApparentPower(double apparentPower)
	{
		this.apparentPower = apparentPower;
	}

	/** Returns the StoreObject in a String format.
	*
	* @return 				Returns a String representation of a StoreObject.
	*/
	public String toString() {
		return "{ power: " + this.truePower + ", voltage: " + this.voltage + ", current: " + this.current + " }";
	}
}