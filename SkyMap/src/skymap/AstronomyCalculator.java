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
    public static final double RADS = Math.PI/180;
    public static final double DEGS = 180/Math.PI;
    
    public AstronomyCalculator() {
        
    }
    
    public boolean usePrecalculatedPlanetElems(Planet planet, double julianDate) {
        double cy = getCY(julianDate);
        if(null != planet.name.toUpperCase()) 
        switch (planet.name.toUpperCase()) {
            case "MERCURY":
                planet.set_mean_longitude(Mod2Pi((252.25084 + 538101628.29 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(0.38709893 + 0.00000066 * cy);
                planet.set_eccentricity(0.20563069 + 0.00002527 * cy);
                planet.set_inclination(( 7.00487  -  23.51 * cy / 3600) * RADS);
                planet.set_perihelion((77.45645  + 573.57 * cy / 3600) * RADS);
                planet.set_longitude_ascending((48.33167 - 446.30 * cy / 3600) * RADS);
                break;
            case "VENUS":
                planet.set_mean_longitude(Mod2Pi ((181.97973 + 210664136.06 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(0.72333199 + 0.00000092 * cy);
                planet.set_eccentricity(0.00677323 - 0.00004938 * cy);
                planet.set_inclination(( 3.39471 - 2.86 * cy / 3600) * RADS);
                planet.set_perihelion((131.53298 - 108.80 * cy / 3600) * RADS);
                planet.set_longitude_ascending(( 76.68069 - 996.89 * cy / 3600) * RADS);
                break;
            case "EARTH":
            case "SUN":
                planet.set_mean_longitude(Mod2Pi ((100.46435 + 129597740.63 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(1.00000011 - 0.00000005 * cy);
                planet.set_eccentricity(0.01671022 - 0.00003804 * cy);
                planet.set_inclination(( 0.00005 - 46.94 * cy / 3600) * RADS);
                planet.set_perihelion((102.94719 +  1198.28 * cy / 3600) * RADS);
                planet.set_longitude_ascending((-11.26064 - 18228.25 * cy / 3600) * RADS);
                break;
            case "MARS":
                planet.set_mean_longitude(Mod2Pi ((355.45332 + 68905103.78 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(1.52366231 - 0.00007221 * cy);
                planet.set_eccentricity(0.09341233 + 0.00011902 * cy);
                planet.set_inclination((1.85061 - 25.47 * cy / 3600) * RADS);
                planet.set_perihelion((336.04084 + 1560.78 * cy / 3600) * RADS);
                planet.set_longitude_ascending(( 49.57854 - 1020.19 * cy / 3600) * RADS);
                break;
            case "JUPITER":
                planet.set_mean_longitude(Mod2Pi ((34.40438 + 10925078.35 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(5.20336301 + 0.00060737 * cy);
                planet.set_eccentricity(0.04839266 - 0.00012880 * cy);
                planet.set_inclination(( 1.30530 -  4.15 * cy / 3600) * RADS);
                planet.set_perihelion(( 14.75385 +  839.93 * cy / 3600) * RADS);
                planet.set_longitude_ascending((100.55615 + 1217.17 * cy / 3600) * RADS);
                break;
            case "SATURN":
                planet.set_mean_longitude(Mod2Pi ((49.94432 + 4401052.95 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(9.53707032 - 0.00301530 * cy);
                planet.set_eccentricity(0.05415060 - 0.00036762 * cy);
                planet.set_inclination(( 2.48446 +  6.11 * cy / 3600) * RADS);
                planet.set_perihelion(( 92.43194 - 1948.89 * cy / 3600) * RADS);
                planet.set_longitude_ascending((113.71504 - 1591.05 * cy / 3600) * RADS);
                break;
            case "URANUS":
                planet.set_mean_longitude(Mod2Pi ((313.23218 + 1542547.79 * cy / 3600) * RADS));
                planet.set_semiMajor_axis (19.19126393 + 0.00152025 * cy);
                planet.set_eccentricity (0.04716771 - 0.00019150 * cy);
                planet.set_inclination (( 0.76986  -  2.09 * cy / 3600) * RADS);
                planet.set_perihelion ((170.96424  + 1312.56 * cy / 3600) * RADS);
                planet.set_longitude_ascending(( 74.22988  - 1681.40 * cy / 3600) * RADS);
                break;
            case "NEPTUNE":
                planet.set_mean_longitude(Mod2Pi ((304.88003 + 786449.21 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(30.06896348 - 0.00125196 * cy);
                planet.set_eccentricity(0.00858587 + 0.00002510 * cy);
                planet.set_inclination (( 1.76917  -  3.64 * cy / 3600) * RADS);
                planet.set_perihelion(( 44.97135  - 844.43 * cy / 3600) * RADS);
                planet.set_longitude_ascending((131.72169 - 151.25 * cy / 3600) * RADS);
                break;
            case "PLUTO":
                planet.set_mean_longitude(Mod2Pi ((238.92881 + 522747.90 * cy / 3600) * RADS));
                planet.set_semiMajor_axis(39.48168677 - 0.00076912 * cy);
                planet.set_eccentricity(0.24880766 + 0.00006465 * cy);
                planet.set_inclination(( 17.14175  +  11.07 * cy / 3600) * RADS);
                planet.set_perihelion((224.06676  - 132.25 * cy / 3600) * RADS);
                planet.set_longitude_ascending((110.30347  -  37.33 * cy / 3600) * RADS);
                break;
            default:
                return false;
        }
        return true;
    }
    
    public double getCY(double JulianDate) {
        return JulianDate/36525.0;
    }
    
    public double Mod2Pi(double radians) {
        double b = radians/(2*Math.PI);
        double a = (2*Math.PI)*(b-abs_floor(b));
        if(a < 0) {
            a = (2*Math.PI)+a;
        }
        return a;
    }
    
    double abs_floor(double x) {
        if(x >= 0){
            return Math.floor(x);
        }
        else {
            return Math.ceil(x);
        }
    }   
}