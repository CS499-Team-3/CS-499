/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;
import java.util.*;

/**
 *
 * @author Joseph, Lindsey
 */
public class SkyBox {
    private List<Star> starList = new ArrayList();
    private List<Planet> planetList = new ArrayList();

    //implemented as a singleton, since there should only be one SkyBox
    //private static instance
    private static SkyBox SB = null;
    
    //private constructor
    private SkyBox()
    {
        
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
