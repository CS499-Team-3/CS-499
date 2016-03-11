/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;
import java.util.*;
import java.awt.Color;

/**
 *
 * @author Joseph, Lindsey
 */
public class SkyBox {
    private static final List<Star> starList = new ArrayList();
    private static final List<Planet> planetList = new ArrayList();
    private static final Moon m = new Moon("MOON");

    //implemented as a singleton, since there should only be one SkyBox
    //private static instance
    private static SkyBox SB = null;
    
    //private constructor
    private SkyBox()
    {
        Planet Mercury = new Planet("MERCURY");
        Planet Venus = new Planet("VENUS");
        Planet Earth = new Planet("EARTH");
        Planet Mars = new Planet("MARS");
        Planet Jupiter = new Planet("JUPITER");
        Planet Saturn = new Planet("SATURN");
        Planet Urans = new Planet("URANS");
        Planet Neptune = new Planet("NEPTUNE");
        
        //now add each one to the planet list
        planetList.add(Mercury);
        planetList.add(Venus);
        planetList.add(Earth);
        planetList.add(Mars);
        planetList.add(Jupiter);
        planetList.add(Saturn);
        planetList.add(Urans);
        planetList.add(Neptune);
        
    }
    
    //function to get the instance of SkyBox
    public static SkyBox getSkyBox()
    {
        if(SB == null)
        {
            SB = new SkyBox();
        }
        
        return SB;
    }
    
    //function to get the entire list
    public List<Star> getStarList()
    {
        return starList;
    }
    
    public List<Planet> getPlanetList()
    {
        return planetList;
    }
    
    public Moon getMoon()
    {
        return m;
    }
    
    //function to add to the Skylist, used to populate the SkyBox
    public void addStar(Star S)
    {
        starList.add(S);
    }
    
    public void addPlanet(Planet P)
    {
        planetList.add(P);
    }  
}
