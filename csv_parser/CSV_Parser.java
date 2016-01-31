 /*****************************************************************************
 * CSV_Reader - reads the CSV file provided by the customer. The .csv file    *
 * contains information used to create the space objects and how to calculate *
 * their positions relative to a location on Earth.                           *
 * ***************************************************************************/
package csv_reader;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Joseph Noyes
 */
public class CSV_Parser {

    //single instance of parser
    private static CSV_Parser parser = null;

    //private constructor - CSV_Parser implemented as a singleton
    private CSV_Parser() {

    }

    //function to get instance of CSV_Parser
    static public CSV_Parser getParser() {
        if (parser == null) {
            parser = new CSV_Parser();
        }
        return parser;
    }

    public void readFile() {
        // TODO code application logic here
        InputStream IS = CSV_Parser_Tester.class.getResourceAsStream("hyg.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(IS));

        try {
            //read the first line that contains the labels
            String line = reader.readLine();

            //while loop to obtain the other lines and break them into variables
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

            }
            reader.close();
        } catch (IOException IO) {

        }
    }
}
