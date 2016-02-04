/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AstronomyCalculator;


/**
 *
 * @author Emma
 */

public class AstronomyFunctions {
    private double Mod2Pi(double X) {
        double A, B;
        X = Math.toRadians(X);
        B = X / (2*Math.PI);        
        A = (2*Math.PI)*(B - abs_floor(B));
        if (A < 0) {
            A = (2*Math.PI) + A;
        }
        return A;
    }
    
    private double abs_floor(double B) {
        if (B >= 0) {
            B = Math.floor(B);
        } else {
            B = Math.ceil(B);
        }
        return B;
    }
    
    private void RightAscDegToHrMinSec(double RA, double hr, double min, double sec) {
        hr = (RA/15.0);
        hr = (int) hr;
        min = ((RA/15.0) - hr)*(60.0);
        min = (int) min;
        sec = ((((RA/15.0) - hr)*(60.0)) - min)*(60.0);
    }
    
    private void DecDegToDegMinSec(double dec, int deg, double min, double sec) {
        deg = (int) deg;
        min = (dec - deg)*(60.0);
        min = (int) min;
        sec = (((dec - deg)*(60.0)) - min)*(60.0);
    }
    
    private double TrueAnomFromMeanAnom(double M, double e) {
        double E = M + e*Math.sin(M)*(1.0 + e*Math.cos(M));
        double E1 = 0;
        do {
            E1 = E;
            E = E1 - (E1 - e*Math.sin(E1) - M)/(1 - e*Math.cos(E1));
        } while(Math.abs(E - E1) > (1.0e-12));
        
        double V = 2*Math.atan(Math.sqrt(1 + e))*Math.tan(0.5*E);
        if (V < 0) {
            V = V + (2*Math.PI);
        }
        return V;
    }
    
    private void AltAndAzimOfPlanet(double lat, double lon, double RA, double dec, double az, double alt) {
        if (lat < 0) {
            lat = lat * -1.0;
        }
        if (lon < 0) {
            lon = lon * -1.0;
        }
        double hourAngle = 0;
        //double hourAngle = meanSiderealTime() - RA
        //if (hourAngle < 0) {
        //    hourAngle += 360;
        //}
        double decRad = dec * (Math.PI/180.0);
        double latRad = lat * (Math.PI/180.0);
        double hrRad = hourAngle * (Math.PI/180.0);
        
        // Calculate altitude in radians
        double sin_alt = (Math.sin(decRad) * Math.sin(latRad)) + (Math.cos(decRad)*Math.cos(latRad)*Math.cos(hrRad));
        alt = Math.abs(sin_alt);
        
        // Calculate azimuth in radians (handle inside of a try...catch)
        try {
          double cos_az = (Math.sin(decRad) - Math.sin(alt)*Math.sin(latRad))/(Math.cos(alt)*Math.cos(latRad));
          az = Math.acos(cos_az);
        } catch (Exception e) {
          az = 0;
        }       
        
        // Convert altitude and azimuth to degrees
        alt = alt * (180.0/Math.PI);
        az = az * (180.0/Math.PI);
        
        if (Math.sin(hrRad) > 0.0) {
            az = 360.0 - az;
        }      
    }
    
    // Calculating the Mean Sidereal Time
    private void MeanSiderealTime(int year, int month, int day, int hour, int min, int sec) {
        // global variable lon? or passed in?
        double lon = 0;
        // Adjust month and year if needed
        if (month <= 2) {
            year = year - 1;
            month = month + 12;
        }
        double a = Math.floor(year / 100.0);
        double b = 2 - a + Math.floor(a / 4);
        double c = Math.floor(365.25 * year);
        double d = Math.floor(30.6001 * (month + 1));
        // Get days since J2000.0
        double jd = b + c + d - 730550.5 + day + (hour + min/60 + sec/3600) / 24;
        // Get Julian centuries since J2000.0
        double jt = jd / 36525.0;
        // Calculate initial Mean Sidereal Time (mst)
        double mst = 280.46061837 + (360.98564736629 * jd) + (0.000387933 * Math.pow(jt, 2)) - (Math.pow(jt, 3)/38710000)+ lon;
        // Clip mst to range 0.0 to 360.0
        if (mst > 0.0) {
            while(mst > 360.0) {
                mst -= 360.0;
            }            
        } else {
            while(mst < 0.0) {
                mst += 360.0;
            }
        }
        // Result is Mean Sidereal Time for the location given by Lat, Lon
    }
    
    // Calculating Exact Julian Day
    
    /*private double ExactJulianDay(int day, int month, int year, Date dt) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        double JD = 0.0;
        int YYYY = year;
        int MM = month;
        long DD = day;
        double y = 0;
        double m = 0;
        double A = 0;
        double B = 0;
        
        //Date date = dt;
        
        if(MM > 2) {
            y = YYYY;
            m = MM;
        } else {
            y = YYYY - 1;
            m = MM + 12;
        }
        
        return JD;
    }   */
    
}
