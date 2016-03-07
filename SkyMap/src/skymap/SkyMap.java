/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

import UserInterface.GUIWindow;
/**
 *
 * @author Lindsey and Zein
 */
public class SkyMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //GUIWindow window = new GUIWindow();
        GUIWindow window = new GUIWindow();
        //Lindsey Tests
        Planet p = new Planet("sun");
        AstronomyCalculator c = new AstronomyCalculator();
        c.usePrecalculatedPlanetElems(p, 2448000.5); // 2415128.5
        testLindseysFunctions(p,2448000.5);

        // TODO code application logic here

        //testing the SpaceFactory class

        //SpaceFactory factory = new SpaceFactory();

        //SpaceObject x = factory.makeSpaceObject("planet");


    }

    public static void doSomething(SpaceObject object){
        System.out.println("You made an object.");
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
