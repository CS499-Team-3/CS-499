/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

/**
 *
 * @author Zein Sleiman
 */
public class SpaceFactory {

    //function to make a star
    public Star makeStar(int ID, int h, double hd, double hr, String g,
            String bf, String pn, double ra, double DEC, double dist, double MAG,
            double aMag, String s, double ci) {
            Star newStar = new Star(ID, h, hd, hr, g, bf, pn, ra, DEC, dist, MAG, 
                    aMag, s, ci);
            return newStar;
    }

    public static Planet makePlanet() {
        Planet p = new Planet();
        return p;
    }

}
