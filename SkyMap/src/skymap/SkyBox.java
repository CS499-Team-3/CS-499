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
    List<SpaceObject> skyList;

    //implemented as a singleton, since there should only be one SkyBox
    //private static instance
    private static SkyBox SB = null;
    
    //Java list object to hold SpaceObjects
    private final List<SpaceObject> SkyList = new ArrayList<>();
    
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
    public List<SpaceObject> getSkyBoxList()
    {
        return SkyList;
    }
    
    //function to add to the Skylist, used to populate the SkyBox
    public boolean addSpaceObject(SpaceObject SO)
    {
        SkyList.add(SO);
        return true;  //true value sent to determine if opertaion was successful
    }
}
