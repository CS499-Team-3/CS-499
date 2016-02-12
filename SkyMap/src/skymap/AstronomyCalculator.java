/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

/**
 *
 * @author Lindsey Harris, Joseph Noyes, Zein Sleiman, Emma Bachelor
 */
public class AstronomyCalculator {

    public static final double RADS = Math.PI / 180;
    public static final double DEGS = 180 / Math.PI;

    public AstronomyCalculator() {

    }

    /**
     *
     * @param year - the year user entered
     * @param month - month Jan - Dec ( 1 - 12)
     * @param day - Day converted to day.hours for example 9:00 AM on the 15 of
     * a month is 15 + 9/24 = 15.375
     * @return - Returns the exact Julian Date
     */
    //might need to be changed based on how user enters hours and mins
    public double calExactJulianDate(int year, int month, double day) {
        //variables needed
        int y;
        int m;
        double exactJulian = 0;
        int b = 0;
        int a = 0;

        //modify input variables as needed
        if (month > 2) {
            y = year;
            m = month;
        } else {
            y = year - 1;
            m = month + 12;
        }

        //find the value of b.  Used in calculation
        if (year >= 1583) {
            if (year == 1583 && m <= 10 && day < 15) {
                b = 0;
            } else {
                a = (y / 100);
                b = 2 - a + (a / 4);
            }
        } else {
            b = 0;
        }

        //perform calculation
        exactJulian = ((int) (365.25 * y)) + ((int) (30.6001 * (m + 1)))
                + day + 1720994.5 + b;

        return exactJulian;
    }
    /**
     * Calculates the Julian Date relative to 2000.
     */
    double calRelativeJulian(int year, int month, int day, int hour, int min, int sec)
    {
        /*
        JD = (367 * YYYY) 
	- (Math.floor(7.0 * (YYYY + Math.floor((MM + 9.0)/12.0))/4.0))
	+ (Math.floor(275.0 * MM / 9.0))
	+ DD - 730531.5 + HH/24.0;
        */
        double relativeJulian;
        
        relativeJulian = (367 * year) 
                - Math.floor(7.0 * (year + Math.floor((month + 9.0/12))/4.0)) +
                (Math.floor(275.0 * month / 9.0)) + day - 730531 + hour/24.0;
        
        return relativeJulian;
    }

    public boolean usePrecalculatedPlanetElems(Planet planet, double julianDate) {
        double cy = getCY(julianDate);
        if (null != planet.name.toUpperCase()) {
            switch (planet.name.toUpperCase()) {
                case "MERCURY":
                    planet.set_mean_longitude(Mod2Pi((252.25084 + 538101628.29 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(0.38709893 + 0.00000066 * cy);
                    planet.set_eccentricity(0.20563069 + 0.00002527 * cy);
                    planet.set_inclination((7.00487 - 23.51 * cy / 3600) * RADS);
                    planet.set_perihelion((77.45645 + 573.57 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((48.33167 - 446.30 * cy / 3600) * RADS);
                    break;
                case "VENUS":
                    planet.set_mean_longitude(Mod2Pi((181.97973 + 210664136.06 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(0.72333199 + 0.00000092 * cy);
                    planet.set_eccentricity(0.00677323 - 0.00004938 * cy);
                    planet.set_inclination((3.39471 - 2.86 * cy / 3600) * RADS);
                    planet.set_perihelion((131.53298 - 108.80 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((76.68069 - 996.89 * cy / 3600) * RADS);
                    break;
                case "EARTH":
                case "SUN":
                    planet.set_mean_longitude(Mod2Pi((100.46435 + 129597740.63 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(1.00000011 - 0.00000005 * cy);
                    planet.set_eccentricity(0.01671022 - 0.00003804 * cy);
                    planet.set_inclination((0.00005 - 46.94 * cy / 3600) * RADS);
                    planet.set_perihelion((102.94719 + 1198.28 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((-11.26064 - 18228.25 * cy / 3600) * RADS);
                    break;
                case "MARS":
                    planet.set_mean_longitude(Mod2Pi((355.45332 + 68905103.78 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(1.52366231 - 0.00007221 * cy);
                    planet.set_eccentricity(0.09341233 + 0.00011902 * cy);
                    planet.set_inclination((1.85061 - 25.47 * cy / 3600) * RADS);
                    planet.set_perihelion((336.04084 + 1560.78 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((49.57854 - 1020.19 * cy / 3600) * RADS);
                    break;
                case "JUPITER":
                    planet.set_mean_longitude(Mod2Pi((34.40438 + 10925078.35 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(5.20336301 + 0.00060737 * cy);
                    planet.set_eccentricity(0.04839266 - 0.00012880 * cy);
                    planet.set_inclination((1.30530 - 4.15 * cy / 3600) * RADS);
                    planet.set_perihelion((14.75385 + 839.93 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((100.55615 + 1217.17 * cy / 3600) * RADS);
                    break;
                case "SATURN":
                    planet.set_mean_longitude(Mod2Pi((49.94432 + 4401052.95 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(9.53707032 - 0.00301530 * cy);
                    planet.set_eccentricity(0.05415060 - 0.00036762 * cy);
                    planet.set_inclination((2.48446 + 6.11 * cy / 3600) * RADS);
                    planet.set_perihelion((92.43194 - 1948.89 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((113.71504 - 1591.05 * cy / 3600) * RADS);
                    break;
                case "URANUS":
                    planet.set_mean_longitude(Mod2Pi((313.23218 + 1542547.79 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(19.19126393 + 0.00152025 * cy);
                    planet.set_eccentricity(0.04716771 - 0.00019150 * cy);
                    planet.set_inclination((0.76986 - 2.09 * cy / 3600) * RADS);
                    planet.set_perihelion((170.96424 + 1312.56 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((74.22988 - 1681.40 * cy / 3600) * RADS);
                    break;
                case "NEPTUNE":
                    planet.set_mean_longitude(Mod2Pi((304.88003 + 786449.21 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(30.06896348 - 0.00125196 * cy);
                    planet.set_eccentricity(0.00858587 + 0.00002510 * cy);
                    planet.set_inclination((1.76917 - 3.64 * cy / 3600) * RADS);
                    planet.set_perihelion((44.97135 - 844.43 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((131.72169 - 151.25 * cy / 3600) * RADS);
                    break;
                case "PLUTO":
                    planet.set_mean_longitude(Mod2Pi((238.92881 + 522747.90 * cy / 3600) * RADS));
                    planet.set_semiMajor_axis(39.48168677 - 0.00076912 * cy);
                    planet.set_eccentricity(0.24880766 + 0.00006465 * cy);
                    planet.set_inclination((17.14175 + 11.07 * cy / 3600) * RADS);
                    planet.set_perihelion((224.06676 - 132.25 * cy / 3600) * RADS);
                    planet.set_longitude_ascending((110.30347 - 37.33 * cy / 3600) * RADS);
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public double getCY(double JulianDate) {
        return JulianDate / 36525.0;
    }

    public double Mod2Pi(double radians) {
        double b = radians / (2 * Math.PI);
        double a = (2 * Math.PI) * (b - abs_floor(b));
        if (a < 0) {
            a = (2 * Math.PI) + a;
        }
        return a;
    }

    double abs_floor(double x) {
        if (x >= 0) {
            return Math.floor(x);
        } else {
            return Math.ceil(x);
        }
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