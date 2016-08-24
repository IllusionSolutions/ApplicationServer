package com.illusionsolutions.persistence.PersistenceHandler;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by stuart on 2016/08/24.
 */
public class Calculations
{
       private double emission;
       private double price;
       private static DecimalFormat decimalFormat = new DecimalFormat(".##");
       private double CO2perKWh = 0.703;
       private double pricePerKWH = 1.89;


       public Calculations(double kwh)
       {
            emission = calculateEmissions(kwh);
            price = calculatePrice(kwh);
       }

       /** Takes in a String as a parameter and checks whether said string is in the
        * correct JSON format. This is achieved by passing the String to a JSON parser
        * which will return an exception if the String is not a correct (parseable) format.
        * @param KWh               The String which is to be checked.
        * @return                      Returns true if the String is in the correct JSON
        *                              format, else it returns false.
        **/
       public double calculateEmissions(double KWh)
       {
           decimalFormat.setRoundingMode(RoundingMode.UP);
           String emissionString = decimalFormat.format(KWh*CO2perKWh);
           return Double.parseDouble(emissionString);
       }

        public double calculatePrice(double KWh)
        {
            decimalFormat.setRoundingMode(RoundingMode.UP);
            String priceString = decimalFormat.format(KWh*pricePerKWH);
            return Double.parseDouble(priceString);
        }

       public double getEmission()
       {
           return emission;
       }

       public void SetEmission(double emission)
       {
           this.emission = emission;
       }

        public double getPrice()
        {
            return price;
        }

        public void setPrice(double price)
        {
            this.price = price;
        }
}