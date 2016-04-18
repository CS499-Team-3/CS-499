/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

import UserInterface.GUIWindow;
import java.util.List;
/**
 *
 * @author Lindsey and Zein
 */
public class SkyMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //read embeded csv file, call then calls GUI Window to start program
        DataParser parser = DataParser.getParser();
        parser.readFile();
        GUIWindow window = new GUIWindow();
    }

}
