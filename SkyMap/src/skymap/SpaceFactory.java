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

    public static SpaceObject makeSpaceObject(String type){
        if(type.equals("planet")) {
            return new Planet();
        }
        else if(type.equals("star")){
            System.out.println("Made a new star");
            return new Star();
        }
        else{
            return null;
        }
    }
    
}
