/**
 * *****************************************************************************
 * Lunar_cycles.java - class that calculates the Lunar phases. *
 *****************************************************************************
 */
package lunar_cycles;

/**
 *
 * @author Joseph Noyes
 */
public class Lunar_cycles {
    /**
     * 
     * @param year - the year in Julian Date format
     * @return double - returns a Julian day of a given phase
     */
    double julianDateOfGivenPhase(double year) {
        //formula
        /*JD = 2415020.75933 + (29.53058868 * k) + (0.0001178 * T2) – 
         (0.000000155 * T3) + (0.00033 * sin((166.56 + (132.87 * T) – 
         (0.009173 * T2)) * RAD) */
        
        //Caluculate K value used in formula
        int k = 0;
        double kvalue = ((year - 1900) * 12.3685);
        float kfloat = (float) kvalue;
        k = Math.round(kfloat);
        
        //calculate t value
        double t = k / 1236.85;        

        double julianDate;

        //get the sin value in radians
        double sinValue = 166.56 + (132.87 * t);
        sinValue = sinValue - (0.009173 * Math.pow(t, 2));
        sinValue = Math.toRadians(sinValue);
        sinValue = Math.sin(sinValue);

        //peform calculations, broken down into parts
        julianDate = 2415020.75933 + (29.53058868 * k);
        julianDate = julianDate + (0.0001178 * Math.pow(t, 2));
        julianDate = julianDate - (0.000000155 * Math.pow(t, 3));
        julianDate = julianDate + (0.00033 * sinValue);

        return julianDate;
    }

}
