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

public class Star extends SpaceObject {
    
    //variables needed for the star, comes from csv file
    double RA;
    double dec;
    double mag;
    
    String constellation_name;
    private int starID;
    private int hip;
    private double HD;
    private double HR;
    private String gliese;
    private String bayFlamsteed;
    private String properName;
    private double distance;
    private double absMag;
    private String spectrum;
    private double colorIndex;
    
    public Star(int ID, int h, double hd, double hr, String g, String bf,
            String pn, double ra, double DEC, double dist, double MAG,
            double aMag, String s, double ci)
    {
        super();
        starID = ID;
        hip = h;
        HD = hd;
        HR = hr;
        gliese = g;
        bayFlamsteed = bf;
        properName = pn;
        RA = ra;
        dec = DEC;
        distance = dist;
        mag = MAG;
        absMag = aMag;
        spectrum = s;
        colorIndex = ci;
    }
    public Star(String name)
    {
        super(name);
    }
    
    public double getMagnitude(){
        return mag;
    }
}