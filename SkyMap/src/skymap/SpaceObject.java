/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

/**
 *
 * @author Lindsey Harris
 */

class coordinate {
    int x;
    int y;
}

public class SpaceObject {
    String name;
    float magnitude;
    coordinate location;
    boolean isVisible;
    
    public SpaceObject()
    {
        this.name = "";
        this.magnitude = 0;
        this.location.x = -1;
        this.location.y = -1;
        this.isVisible = false;
    }
    
    public SpaceObject(String objName)
    {
        this.name = objName;
        this.magnitude = 0;
        this.location.x = -1;
        this.location.y = -1;
        this.isVisible = false;
    }
}

