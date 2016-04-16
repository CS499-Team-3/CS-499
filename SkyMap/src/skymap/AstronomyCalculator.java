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
    private double mst = 0.0;
    public AstronomyCalculator() {
        
    }

    //function that converts a time with AM/PM to military time
    public int time2Milarary(int hour, String am_pm) {
        if (am_pm.contentEquals("AM") && hour != 12) {
            //no need to convert if it is AM and not midnight
            return hour;
        } else if (am_pm.contentEquals("AM") && hour == 12) {
            return 0; //0 is midnight in military time
        } //if PM and after 12, add 1 to time
        else if (am_pm.contentEquals("PM") && hour == 12) {
            return hour;  //12 is noon in military time
        } else {
            return hour + 12;
        }

    }

    /**
     *
     * @param year - the year user entered
     * @param month - month Jan - Dec ( 1 - 12)
     * @param day - Day that will be converted to day.hours for example 9:00 AM
     * on the 15 of a month is 15 + 9/24 = 15.375
     * @param hours - the hours in military time i.e. 13 = 1 o'clock
     * @param minutes - the minutes that passed by in the hour, part of the time
     * @return - Returns the exact Julian Date
     */
    //Pass in day as just the day value, not as a decimal
    public double calExactJulianDate(int year, int month, double day, double hours,
            double minutes) {
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
                + (day + hours / 24 + (minutes / 1440)) + 1720994.5 + b;

        return exactJulian;
    }

    /**
     * Calculates the Julian Date relative to 2000.
     */
    public double calRelativeJulian(int year, int month, int day, int hour, int min, int sec) {
        /*
         JD = (367 * YYYY) 
         - (Math.floor(7.0 * (YYYY + Math.floor((MM + 9.0)/12.0))/4.0))
         + (Math.floor(275.0 * MM / 9.0))
         + DD - 730531.5 + HH/24.0;
         */

        double relativeJulian = (367 * year)
                - (Math.floor(7.0 * (year + Math.floor((month + 9.0) / 12)) / 4.0))
                + (Math.floor(275.0 * month / 9.0)) + day - 730531.5 + hour / 24.0;

        return relativeJulian;
    }

    /*Function takes year, month and day and converts it to a decimal format
     to be used in the lunar phase functions. */
    double date2Decimal(int year, int month, int day) {
        double decYear = 0;
        double daysOver365 = 0;
        double daysPassed = 0;
        //find the decimal part of the year
        //days passed in the year / 365

        //find the number of days passed from each month
        for (int i = month - 1; i != 0; i--) {

            //if Month has 30 days
            if (i == 4 || i == 6 || i == 9 || i == 11) {
                daysPassed = daysPassed + 30;
            } else if (i == 2) //add for Feb
            {
                daysPassed = daysPassed + 28;
            } else // month has 31 days
            {
                daysPassed = daysPassed + 31;
            }

        }

        //now do calculation of days/365
        daysPassed = daysPassed + day;
        daysOver365 = daysPassed / 365;

        //add daysOver365 to decYear
        decYear = year + daysOver365;
        return decYear;
    }

    /*phaseValue corresponds with what lunar phase we need to calculate, 0 for
     new moon, 0.25 for first quarter, 0.50 for full moon, and 0.75 for the last
     quarter 
    
    year is a decimal, so that function takes the date after it has been converted
    from date2decimal*/
    double julianDateOfGivenPhase(double year, double phaseValue) {
        //formula
        /*JD = 2415020.75933 + (29.53058868 * k) + (0.0001178 * T2) – 
         (0.000000155 * T3) + (0.00033 * sin((166.56 + (132.87 * T) – 
         (0.009173 * T2)) * RAD) */

        //Caluculate K value used in formula
        double k = 0;
        double kvalue = ((year - 1900) * 12.3685);
        float kfloat = (float) kvalue;
        k = Math.round(kfloat);
        k = k + phaseValue;

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

    //calculates the closest moon phase so that the moon can be plotted with the
    //most accurate phase.  Takes the date and calls date2Decimal
    public void calculateClosestPhase(int year, int month, int day) {

        //variables needed
        double date = date2Decimal(year, month, (day - 15));
        double tempDate;
        double temp;
        double lowestValue = 0;
        double phaseValue = 0.0;
        double julianDate = calExactJulianDate(year, month, day, 0, 0);
        SkyBox sb = SkyBox.getSkyBox();  //get the SkyBox
        Moon m = sb.getMoon();

        //for each phase, determine the Julian date of it and see which one is
        //closer
        for (double i = 0.0; i < 1; i = i + 0.25) {
            tempDate = julianDateOfGivenPhase(date, i);

            //see how close the date of phase is to the current date
            temp = Math.abs(tempDate - julianDate);
            if (lowestValue == 0) {
                lowestValue = temp;
                phaseValue = i;
            } else if (lowestValue > temp) {
                lowestValue = temp;
                phaseValue = i;
            }
        }

        //now set the moon_phase based on phaseValue
        if (phaseValue == 0.0) {
            m.setPhase(lunar_phase.NEW_MOON);
        } else if (phaseValue == 0.25) {
            m.setPhase(lunar_phase.FIRST_QUARTER);
        } else if (phaseValue == 0.50) {
            m.setPhase(lunar_phase.FULL_MOON);
        } else {
            m.setPhase(lunar_phase.LAST_QUARTER);
        }
    }
//make sure you pass in RELATIVE julian date!

    public boolean usePrecalculatedPlanetElems(Planet planet, double reljulianDate) {
        double cy = getCY(reljulianDate);
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


    public double getRightAscension(Planet planet, double relJulianDate) {
        Coordinate coord = getRectEquatCoord(planet, relJulianDate);
        double right_ascension = Mod2Pi(Math.atan2(coord.y,coord.x)) * DEGS;
        return right_ascension;
    }

    public Coordinate getPosition(double lat, double lon, 
                                  Star s, double relativeDate) {
        Coordinate coord = new Coordinate();
        coord.x = (Mod2Pi(getAz(lat, lon, s.RA * DEGS, s.dec, relativeDate)*RADS) *DEGS);
        coord.y =  (Mod2Pi(getAlt(lat, lon, s.RA * DEGS, s.dec, relativeDate)*RADS) *DEGS);
        coord.z = 10;
        return coord;
    }
    
    public Coordinate getPlanetPos(double lat, double lon, 
                                   Planet p, double relativeDate) {
        Coordinate coord = new Coordinate();
        coord.x = (DEGS * Mod2Pi(getAlt(lat, lon, getRightAscension(p,relativeDate), 
                         getDeclination(p,relativeDate), relativeDate)));
        coord.y = (DEGS * Mod2Pi(getAz(lat, lon, getRightAscension(p,relativeDate), 
                   getDeclination(p,relativeDate), relativeDate)))-180;
        coord.z = 10;
        return coord;
    }

    public double getDeclination(Planet planet, double relJulianDate) {
        Coordinate coord = getRectEquatCoord(planet, relJulianDate);
        if ((coord.x == 0) && (coord.y == 0)) {
            if (coord.z > 0) {
                return 90.0;
            } else if (coord.z < 0) {
                return 270.0;
            } else {
                return 0.0;
            }
        }
        double declination = Math.atan(coord.z / Math.sqrt((coord.x * coord.x) + (coord.y * coord.y))) * DEGS;
        return declination;
    }

    public double getDistance(Planet planet, double julianDate) {
        Coordinate coord = getRectEquatCoord(planet, julianDate);
        double dist = Math.sqrt((coord.x * coord.x)
                + (coord.y * coord.y)
                + (coord.z * coord.z));
        return dist; // in AUs
    }

    public Coordinate getRectEquatCoord(Planet planet, double relJulianDate) {
        Planet Earth = new Planet("Earth");
        usePrecalculatedPlanetElems(planet, relJulianDate);
        usePrecalculatedPlanetElems(Earth, relJulianDate);

        double m_earth = Mod2Pi(Earth.mean_longitude - Earth.perihelion);
        double v_earth = TrueAnomFromMeanAnom(m_earth, Earth.eccentricity);
        double r_earth = Earth.semiMajor_axis
                * (1 - (Earth.eccentricity * Earth.eccentricity))
                / (1 + (Earth.eccentricity * Math.cos(v_earth)));
        double x_earth = r_earth * Math.cos(v_earth + Earth.perihelion);
        double y_earth = r_earth * Math.sin(v_earth + Earth.perihelion);
        double z_earth = 0.0;

        double m_planet = Mod2Pi(planet.mean_longitude - planet.perihelion);
        double v_planet = TrueAnomFromMeanAnom(m_planet, planet.eccentricity);
        double r_planet = planet.semiMajor_axis
                * (1 - (planet.eccentricity * planet.eccentricity))
                / (1 + (planet.eccentricity * Math.cos(v_planet)));
        double x_p_helio = r_planet
                * (Math.cos(planet.longitude_ascending)
                * Math.cos(v_planet + planet.perihelion
                        - planet.longitude_ascending)
                - Math.sin(planet.longitude_ascending)
                * Math.sin(v_planet + planet.perihelion
                        - planet.longitude_ascending)
                * Math.cos(planet.inclination));
        double y_p_helio = r_planet * (Math.sin(planet.longitude_ascending)
                * Math.cos(v_planet + planet.perihelion
                        - planet.longitude_ascending)
                + Math.cos(planet.longitude_ascending)
                * Math.sin(v_earth + planet.perihelion
                        - planet.longitude_ascending)
                * Math.cos(planet.inclination));
        double z_p_helio = r_planet * (Math.sin(v_planet + planet.perihelion
                - planet.longitude_ascending)
                * Math.sin(planet.inclination));
        double x_p_geo = x_p_helio - x_earth;
        double y_p_geo = y_p_helio - y_earth;
        double z_p_geo = z_p_helio - z_earth;

        double ecl = 23.439281 * RADS;
        double xeq = x_p_geo;
        double yeq = y_p_geo * Math.cos(ecl) - (z_p_geo * Math.sin(ecl));
        double zeq = y_p_geo * Math.sin(ecl) + (z_p_geo * Math.cos(ecl));
        Coordinate coord = new Coordinate();
        coord.x = xeq;
        coord.y = yeq;
        coord.z = zeq;
        return coord;
    }

    private void RightAscDegToHrMinSec(double RA, double hr, double min, double sec) {
        hr = (RA / 15.0);
        hr = (int) hr;
        min = ((RA / 15.0) - hr) * (60.0);
        min = (int) min;
        sec = ((((RA / 15.0) - hr) * (60.0)) - min) * (60.0);
    }

    private void DecDegToDegMinSec(double dec, int deg, double min, double sec) {
        deg = (int) dec;
        min = (dec - deg) * (60.0);
        min = (int) min;
        sec = (((dec - deg) * (60.0)) - min) * (60.0);
    }

    public double TrueAnomFromMeanAnom(double meanAnom, double eccent) {
        double E = meanAnom + eccent * Math.sin(meanAnom) * (1.0 + eccent * Math.cos(meanAnom));
        double E1 = 0;
        do {
            E1 = E;
            E = E1 - ((E1 - eccent * Math.sin(E1) - meanAnom) / (1 - eccent * Math.cos(E1)));
        } while (Math.abs(E - E1) > (1.0e-12));

        double V = 2 * Math.atan(Math.sqrt((1 + eccent) / (1 - eccent)) * Math.tan(0.5 * E));
        if (V < 0) {
            V = V + (2 * Math.PI);
        }
        return V;
    }

    // test before use
    private double getAlt(double lat, double lon, 
                          double RA, double dec, double jd) {
        if (lat < 0) {
            lat = lat * -1.0;
        }
        if (lon < 0) {
            lon = lon * -1.0;
        }

        double hourAngle = mst - RA;
        if (hourAngle < 0) {
            hourAngle += 360;
        }
        double decRad = dec * RADS;
        double latRad = lat * RADS;
        double hrRad = hourAngle * RADS;

        // Calculate altitude in radians
        double sin_alt = (Math.sin(decRad) * Math.sin(latRad)) +
                         (Math.cos(decRad) * Math.cos(latRad) * Math.cos(hrRad));
        return (Math.asin(sin_alt) * DEGS);
    }

    // test before use
    private double getAz(double lat, double lon, 
                         double RA, double dec, double jd) {
        double az;
        double alt = getAlt(lat,lon,RA,dec,jd);
        //double hourAngle = MeanSiderealTime(jd, lon) - RA;
        double hourAngle = mst - RA;
        if (hourAngle < 0) {
            hourAngle += 360;
        }
        double decRad = dec * RADS;
        double latRad = lat * RADS;
        double hrRad = hourAngle * RADS;
        // Calculate azimuth in radians (handle inside of a try...catch)
        try {
            double cos_az = (Math.sin(decRad) - Math.sin(alt*RADS) * Math.sin(latRad)) /
                            (Math.cos(alt*RADS) * Math.cos(latRad));
            az = Math.acos(cos_az);
        } catch (Exception e) {
            az = 0;
        }

        // Convert azimuth to degrees
        az = az * DEGS;

        if (Math.sin(hrRad) > 0.0) {
            az = 360.0 - az;
        }
        System.out.println("AZ:" + az);
        return az;
    }

    /*private double MeanSiderealTime(double jd, double lon) {
        // Get Julian centuries since J2000.0
        double jt = jd / 36525.0;
        // Calculate initial Mean Sidereal Time (mst)
        double mst = 280.46061837 + (360.98564736629 * jd) + (0.000387933 * Math.pow(jt, 2)) - (Math.pow(jt, 3) / 38710000) + lon;
        // Clip mst to range 0.0 to 360.0
        if (mst > 0.0) {
            while (mst > 360.0) {
                mst -= 360.0;
            }
        } else {
            while (mst < 0.0) {
                mst += 360.0;
            }
        }
        // Result is Mean Sidereal Time for the location given by Lat, Lon
        return mst;
    }*/
    
    //Pass in day as just the day value, not as a decimal
    public void calculateMST(int year, int month, double day, double hours,
            double minutes, double seconds, double lon) {
        //variables needed
        double b = 0;
        double a = 0;
        double c = 0;
        double d = 0;

        if (month <= 2) {
            year--;
            month=+12;
        }
        a = Math.floor(year / 100.0);
        b = (2 - a + Math.floor(a / 4));
        c = Math.floor(365.25 * year);
        d = Math.floor(30.6001 * (month + 1));
        
        // Get days since J2000.0
        double jd = b + c + d - 730550.5 + day + 
                (hours + minutes/60 + seconds/3600) / 24;
        
        // Get Julian centuries since J2000.0
        double jt = jd / 36525.0;
        
        // Calculate initial MST
        mst = 280.46061837 + (360.98564736629 * jd) + 
                (0.000387933 * Math.pow(jt, 2) 
                - (Math.pow(jt, 3)/38710000) + lon);
        
        //Clip mst to range 0.0 to 360.0
        if (mst > 0.0) {
            while (mst > 360.0) {
                mst -= 360.0;
            }           
        } else {
            while (mst < 0.0) {
                mst += 360.0;
            }
        }
    }

}
