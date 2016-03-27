/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

import UserInterface.GUIWindow;
import java.util.List;
/**
 *
 * @author Lindsey and Zein
 */
public class SkyMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AstronomyCalculator calc = new AstronomyCalculator();
        double julianDate = calc.calRelativeJulian(2009,1,15,5,30,0);
        DataParser parser = DataParser.getParser();
        parser.readFile();
        
        SkyBox skyBox = SkyBox.getSkyBox();
        List<Planet> planetList = skyBox.getPlanetList();
        List<Star> starList = skyBox.getStarList();
        
        for(int i = 0; i < planetList.size(); i++)
        {
            calc.usePrecalculatedPlanetElems(planetList.get(i), julianDate);
            planetList.get(i).location = calc.getRectEquatCoord(planetList.get(i), julianDate);  
        }
        for(int i = 0; i < starList.size(); i++)
        {
            starList.get(i).location = 
                    calc.getPosition(starList.get(i), julianDate);
        }
        GUIWindow window = new GUIWindow();
    }


    public static void testPlanetElementFunctions(Planet planet)
    {
        AstronomyCalculator calc = new AstronomyCalculator();

        double d2d = calc.date2Decimal(2016, 3, 11);
        System.out.println("Date2Decimal: " + d2d);
        double reljd = calc.calRelativeJulian(1997, 8, 8, 16, 4, 0);
        System.out.println("JulianDate: " + reljd);
        System.out.println(planet.name);
        calc.usePrecalculatedPlanetElems(planet, reljd);
        System.out.println("Mean Longitude: " + planet.mean_longitude);
        System.out.println("Semi-Major Axis: " + planet.semiMajor_axis);
        System.out.println("Eccentricity: " + planet.eccentricity);
        System.out.println("Inclination: " + planet.inclination);
        System.out.println("Perihelion: " + planet.perihelion);
        System.out.println("Longitude Ascending: " + planet.longitude_ascending);

    }


    
    public static void testLindseysFunctions(Planet planet, double julianDate)
    {
        AstronomyCalculator calc = new AstronomyCalculator();
        Coordinate coord = calc.getRectEquatCoord(planet, julianDate);
        double dec = calc.getDeclination(planet, julianDate);
        double rightAscen = calc.getRightAscension(planet, julianDate);

        System.out.println("Planet: "+planet.name);
        System.out.println("JulianDate: "+julianDate);
        System.out.println("Coordinates:");
        System.out.println(  "     x: "+coord.x+
                           "\n     y: "+coord.y+
                           "\n     z: "+coord.z);
        System.out.println("declination: "+dec);
        System.out.println("right ascension: "+rightAscen);
    }
}
