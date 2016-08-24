/**
 * @(#) StoreObject.java
 */

package com.illusionsolutions.persistence.PersistenceHandler;

public class StoreObject
{
	private String id;
	private double current;
	private double voltage;
	private double power;
	private long datetime;
	private Calculations calculations;

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
	public double getPower()
	{
		return this.power;
	}

	/** Takes in a double, and sets the true power measured during a reading to this double.
	*
	* @param power 		Double which contains the desired power.
	*/
	public void setPower(double power)
	{
		this.power = power;
	}

	/** Returns the true power recorded during a reading.
	 *
	 * @return 				Returns the true power as a double.
	 */
	public Calculations getCalculations()
	{
		return this.calculations;
	}

	/** Takes in a double, and sets the true power measured during a reading to this double.
	 *
	 * @param calculations 		Double which contains the desired power.
	 */
	public void setCalculations(Calculations calculations)
	{
		this.calculations = calculations;
	}

	/** Returns the StoreObject in a String format.
	*
	* @return 				Returns a String representation of a StoreObject.
	*/
	public String toString() { return "{ power: " + this.power + ", voltage: " + this.voltage + ", current: " + this.current + " }"; }
}