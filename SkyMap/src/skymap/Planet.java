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

public class Planet extends space_object {
    public double semiMajor_axis;
    public double eccentricity;
    public double inclination;
    public double perihelion;
    public double longitude_ascending;
    public double mean_longitude;
    
    public Planet()
    {
        
    }
    
    public void set_semiMajor_axis(double axis)
    {
        this.semiMajor_axis = axis;
    }
    
    public void set_eccentricity(double ecc)
    {
        this.eccentricity = ecc;
    }
    
    public void set_inclination(double inc)
    {
        this.inclination = inc;
    }
    
    public void set_perihelion(double peri)
    {
        this.perihelion = peri;
    }
    
    public void set_longitude_ascending(double la)
    {
        this.longitude_ascending = la;
    }
    
    public void set_mean_longitude(double L)
    {
        this.mean_longitude = L;
    }
}