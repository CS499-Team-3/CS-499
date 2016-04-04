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
enum lunar_phase {
    NEW_MOON,
    FIRST_QUARTER,
    FULL_MOON,
    LAST_QUARTER
}

public class Moon extends SpaceObject {
    lunar_phase phase;
    
    public Moon()
    {
        super();
    }
    public Moon(String name)
    {
        super(name);
    }
    public void setPhase(lunar_phase p)
    {
        phase = p;
    }
}