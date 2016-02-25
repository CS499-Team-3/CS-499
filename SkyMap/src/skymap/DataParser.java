/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Lindsey
 */
public class DataParser {

    //single instance of parser

    private static DataParser parser = null;
    private SpaceFactory SF = new SpaceFactory();

    //private constructor - CSV_Parser implemented as a singleton
    private DataParser() {

    }

    //function to get instance of CSV_Parser
    static public DataParser getParser() {
        if (parser == null) {
            parser = new DataParser();
        }
        return parser;
    }

    public void readFile() {
        // TODO code application logic here
        InputStream IS = SkyMap.class.getResourceAsStream("hyg.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(IS));

        try {
            //read the first line that contains the labels
            String line = reader.readLine();

            //while loop to obtain the other lines and break them into variables
            int i = 0;
            while ((line = reader.readLine()) != null) {
                List<String> dataList = Arrays.asList(line.split(","));

                //place variables in correct variable based on position
                int StarID = Integer.parseInt(dataList.get(0));
                int Hip = 0;

                //Hip could not be defined for some stars, leave at 0
                if (!dataList.get(1).isEmpty()) {
                    Integer.parseInt(dataList.get(1));
                }
                long HD = 0;
                long HR = 0;

                //check to see if HD is empty, if it is, leave as 0
                if (!dataList.get(2).isEmpty()
                        && !dataList.get(2).contentEquals(" ")) {
                    HD = Long.parseLong(dataList.get(2));
                }

                //check to see if HR is empty, if it is, leave as 0
                if (!dataList.get(3).isEmpty()
                        && !dataList.get(3).contentEquals(" ")
                        && !dataList.get(3).contentEquals("-")) {
                    HR = Long.parseLong(dataList.get(3));
                }

                String Gliese = dataList.get(4);
                String BayerFlam = dataList.get(5);
                String ProperName = dataList.get(6);
                Double RA = Double.parseDouble(dataList.get(7));
                Double Distance = Double.parseDouble(dataList.get(8));
                float Mag = Float.parseFloat(dataList.get(9));
                float absMag = Float.parseFloat(dataList.get(10));
                String Spect = dataList.get(11);

                float colorInd = 0;
                //check to see if colorInd is empty, if it is, leave as 0
                if (!dataList.get(12).isEmpty()) {
                    colorInd = Float.parseFloat(dataList.get(11));
                }

                if (Mag < 6) {
                    //Send information to the SpaceObject Factory
                    SpaceObject testStar = null;
                    testStar = SF.makeSpaceObject("star");
                }

            }
            reader.close();
        } //Catch any file read errors
        catch (IOException IO) {

        }
    }
}
