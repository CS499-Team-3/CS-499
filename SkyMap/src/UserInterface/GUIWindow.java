/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import skymap.AstroDraw;
import javax.swing.JFileChooser;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.util.*;
import skymap.AstronomyCalculator;
import skymap.Planet;
import skymap.SkyBox;
import skymap.Moon;
import skymap.Star;
import skymap.SpaceObject;

/**
 *
 * @author Emma
 */
public final class GUIWindow extends JFrame {

    AstroDraw astroDraw = new AstroDraw();
    BufferedImage skyMapImg;
    BufferedImage moonImage;
    Graphics2D mGraphics;
    JLabel label;
    JPanel jpegPanel;
    JPanel timePanel = new JPanel();
    JPanel latPanel = new JPanel();
    JPanel lonPanel = new JPanel();
    JPanel mainBtnPanel = new JPanel();
    JPanel moonPanel = new JPanel();
    JLabel moonLabel;
    JScrollPane skyMapScrollPane;
    JTextField dayField, monthField, yearField;
    JSpinner dateSpin, monthSpin, yearSpin;
    JSpinner hourSpinner, minuteSpinner, secondSpinner;
    SpinnerDateModel hourModel, minuteModel, secondModel;
    SpinnerDateModel spinnerModel;
    JLabel dateLbl, backslashLbl1, backslashLbl2;
    JPanel btnPanel1 = new JPanel();
    JPanel btnPanel2 = new JPanel();
    JButton saveBtn;
    JButton generateMapBtn;
    JLabel lonLbl = new JLabel("Longitude: ");
    JComboBox latSignCombo, lonSignCombo, latDegCombo, lonDegCombo,
            latMinCombo, lonMinCombo, latSecCombo, lonSecCombo;
    JLabel latLbl = new JLabel("Latitude: ");
    String latSign = null;
    Integer latDeg = 0;
    Integer latMin = 0;
    Integer latSec = 0;
    String lonSign = null;
    Integer lonDeg = 0;
    Integer lonMin = 0;
    Integer lonSec = 0;
    String[] signs = {"+", "-"};
    String[] degrees = new String[182];
    String[] mins = new String[62];
    String[] seconds = new String[62];
    Font comboFont = new Font(Font.DIALOG, Font.PLAIN, 12);
    Date date = null;

    public GUIWindow() {
        // Look & Feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        // Set window properties
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("SkyMap");
        setMinimumSize(new Dimension(1200, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create arrays for drop downs
        degrees[0] = "Degrees";
        for (int i = 1; i <= 181; i++) {
            degrees[i] = Integer.toString(i - 1);
        }
        mins[0] = "Minutes";
        seconds[0] = "Seconds";
        for (int i = 1; i <= 61; i++) {
            mins[i] = String.valueOf(i - 1);
            seconds[i] = String.valueOf(i - 1);
        }

        // Temporary Splash Screen
        //BufferedImage splashScreen = new BufferedImage(1345, 635, BufferedImage.TYPE_INT_ARGB);              
        //Graphics2D g = splashScreen.createGraphics();
// ImageIcon icon = new ImageIcon(myImg);
        /*g.setColor(Color.darkGray);
         g.fillRect(0, 0, splashScreen.getWidth(), splashScreen.getHeight());
         g.setColor(Color.WHITE);
         g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
         g.drawString("SkyMap", 630, 300);        
         skyMapImg = splashScreen;*/
        jpegPanel = new JPanel();
        label = new JLabel(new ImageIcon(this.getClass().getResource("/Images/8bit-starwars-resized.jpg")));
        jpegPanel.add(label);
        skyMapScrollPane = new JScrollPane(jpegPanel);

        // Add scroll pane and main panel to window 
        add(skyMapScrollPane, BorderLayout.CENTER);
        add(makeMainPanel(), BorderLayout.SOUTH);
        addListeners();
        setVisible(true);
        saveBtn.setEnabled(false);
    }

    public JPanel makeMoonPanel() {
        moonPanel.setPreferredSize(new Dimension(40, 40));
        moonLabel = new JLabel();
        moonLabel.setPreferredSize(new Dimension(40, 40));
        moonLabel.setBorder(new EtchedBorder());
        moonPanel.add(moonLabel);
        return moonPanel;
    }

    public JPanel makeDatePanel() {
        spinnerModel = new SpinnerDateModel();
        dateLbl = new JLabel("Date: ");
        dateLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        dateLbl.setForeground(Color.DARK_GRAY);
        dateSpin = new JSpinner(spinnerModel);

        ((DefaultEditor) dateSpin.getEditor()).getTextField().setEditable(false);

        JPanel datePanel = new JPanel();
        datePanel.add(dateLbl);
        datePanel.add(dateSpin);

        return datePanel;
    }

    public JPanel makeLatPanel() {
        latPanel.setPreferredSize(new Dimension(325, 50));
        latPanel.add(latLbl);
        latSignCombo = new JComboBox(signs);
        latDegCombo = new JComboBox(degrees);
        latMinCombo = new JComboBox(mins);
        latSecCombo = new JComboBox(seconds);
        // W = 101, H = 20
        latSignCombo.setPreferredSize(new Dimension(40, 30));
        latDegCombo.setPreferredSize(new Dimension(84, 30));
        latMinCombo.setPreferredSize(new Dimension(80, 30));
        latSecCombo.setPreferredSize(new Dimension(84, 30));

        latSignCombo.setFont(comboFont);
        latDegCombo.setFont(comboFont);
        latMinCombo.setFont(comboFont);
        latSecCombo.setFont(comboFont);

        latPanel.add(latSignCombo);
        latPanel.add(latDegCombo);
        latPanel.add(latMinCombo);
        latPanel.add(latSecCombo);
        return latPanel;
    }

    public JPanel makeLonPanel() {
        lonPanel.setPreferredSize(new Dimension(325, 50));
        lonPanel.add(lonLbl);
        lonSignCombo = new JComboBox(signs);
        lonDegCombo = new JComboBox(degrees);
        lonMinCombo = new JComboBox(mins);
        lonSecCombo = new JComboBox(seconds);

        lonSignCombo.setPreferredSize(new Dimension(40, 30));
        lonDegCombo.setPreferredSize(new Dimension(84, 30));
        lonMinCombo.setPreferredSize(new Dimension(80, 30));
        lonSecCombo.setPreferredSize(new Dimension(84, 30));

        lonSignCombo.setFont(comboFont);
        lonDegCombo.setFont(comboFont);
        lonMinCombo.setFont(comboFont);
        lonSecCombo.setFont(comboFont);

        lonPanel.add(lonSignCombo);
        lonPanel.add(lonDegCombo);
        lonPanel.add(lonMinCombo);
        lonPanel.add(lonSecCombo);
        return lonPanel;
    }

    public JPanel makeMainPanel() {
        btnPanel1.setLayout(new BoxLayout(btnPanel1, BoxLayout.X_AXIS));
        btnPanel1.setPreferredSize(new Dimension(250, 50));
        btnPanel1.add(makeDatePanel());
        btnPanel1.add(makeLatPanel());
        btnPanel1.add(makeLonPanel());
        btnPanel1.add(makeMoonPanel());

        btnPanel2.setLayout(new BoxLayout(btnPanel2, BoxLayout.Y_AXIS));
        //btnPanel2.setBackground(Color.DARK_GRAY);
        btnPanel2.setPreferredSize(new Dimension(210, 50));
        saveBtn = new JButton("Save");
        generateMapBtn = new JButton("Generate Map");
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateMapBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateMapBtn.setPreferredSize(new Dimension(77, 20));
        saveBtn.setPreferredSize(new Dimension(77, 20));
        btnPanel2.add(generateMapBtn);
        btnPanel2.add(saveBtn);

        mainBtnPanel.setLayout(new BoxLayout(mainBtnPanel, BoxLayout.X_AXIS));
        mainBtnPanel.add(btnPanel1);
        mainBtnPanel.add(btnPanel2);
        //mainBtnPanel.add(new JPanel());
        return mainBtnPanel;
    }

    public void addListeners() {
        generateMapBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //get and format the date
                SimpleDateFormat df = new SimpleDateFormat("MM dd YYYY hh mm ss a");
                Date userDate = spinnerModel.getDate();
                String userDateString = df.format(userDate);

                //Parse the date, place them in the correct variables
                List<String> dateList = Arrays.asList(userDateString.split(" "));
                int month = Integer.parseInt((dateList.get(0)));
                int day = Integer.parseInt((dateList.get(1)));
                int year = Integer.parseInt((dateList.get(2)));
                int hour = Integer.parseInt((dateList.get(3)));
                int minutes = Integer.parseInt((dateList.get(4)));
                int seconds = Integer.parseInt((dateList.get(5)));
                String am_pm = dateList.get(6);

                //convert hours to military time
                AstronomyCalculator ac = new AstronomyCalculator();
                hour = ac.time2Milarary(hour, am_pm);

                //calculate exact Julian and Relative Julian dates
                double exactJulian = ac.calExactJulianDate(year, month, day, hour, minutes);
                double relativeJulian = ac.calRelativeJulian(year, month, day, hour, minutes, seconds);

                //get a skybox, set the values for planets and stars
                SkyBox skyBox = SkyBox.getSkyBox();
                List<Planet> planetList = skyBox.getPlanetList();
                List<Star> starList = skyBox.getStarList();
                ac.calculateClosestPhase(year, month, day);
                Moon moon = skyBox.getMoon();


                
                //for each planet in the Skybox, set the values
                for (int i = 0; i < planetList.size(); i++) {
                    ac.usePrecalculatedPlanetElems(planetList.get(i), relativeJulian);
                    planetList.get(i).location = ac.getPlanetPos(planetList.get(i), relativeJulian);
                }
                //for each star in the Skybox, set the values
                for (int i = 0; i < starList.size(); i++) {
                    starList.get(i).location
                            = ac.getPosition(starList.get(i), relativeJulian);
                }

                if (latDegCombo.getSelectedIndex() != 0 && latMinCombo.getSelectedIndex() != 0
                        && latSecCombo.getSelectedIndex() != 0 && lonDegCombo.getSelectedIndex() != 0
                        && latMinCombo.getSelectedIndex() != 0 && latSecCombo.getSelectedIndex() != 0) {
                    latDeg = Integer.parseInt((String) latDegCombo.getSelectedItem());
                    if (latSignCombo.getSelectedItem().equals("-")) {
                        latDeg *= -1;
                    }
                    latMin = Integer.parseInt((String) latMinCombo.getSelectedItem());
                    latSec = Integer.parseInt((String) latSecCombo.getSelectedItem());
                    System.out.println("Latitude:");
                    System.out.println(latDeg + " " + latMin + ":"
                            + latSec);

                    lonDeg = Integer.parseInt((String) lonDegCombo.getSelectedItem());

                    if (lonSignCombo.getSelectedItem().equals("-")) {
                        lonDeg *= -1;
                    }
                    lonMin = Integer.parseInt((String) lonMinCombo.getSelectedItem());
                    lonSec = Integer.parseInt((String) lonSecCombo.getSelectedItem());
                    System.out.println("Longitude:");
                    System.out.println(lonDeg + " " + lonMin + ":"
                            + lonSec);
                }
                // Draw SkyMap    
                astroDraw.drawSkyMap();
                skyMapImg = astroDraw.getImage();
                JLabel skyMapLabel = new JLabel(new ImageIcon(skyMapImg));
                JPanel mapJPEGpanel = new JPanel();
                mapJPEGpanel.add(skyMapLabel);
                skyMapScrollPane.getViewport().remove(jpegPanel);
                skyMapScrollPane.getViewport().add(mapJPEGpanel);

                // Update Moon Panel
                moonImage = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
                mGraphics = moonImage.createGraphics();
                mGraphics.setColor(Color.BLACK);
                mGraphics.fillRect(0, 0, moonImage.getWidth(), moonImage.getHeight());
                moonLabel.setIcon(new ImageIcon(moonImage));
                moonPanel.add(moonLabel);
                saveBtn.setEnabled(true);
                astroDraw.drawMoon(moon, mGraphics);
            }

        });

        //action listener for the save button
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //get a file chooser, add a filter for jpg only
                JFileChooser c = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("jpg image files", "jpg");
                c.setDialogTitle("Save StarMap as a JPEG");
                c.setFileFilter(filter);

                //if user enters a correct filename
                int saveValue = c.showSaveDialog(GUIWindow.this);
                if (saveValue == JFileChooser.APPROVE_OPTION) {
                    //Test the string for .jpg extention
                    String fileName = (c.getSelectedFile().toString());
                    if (fileName.contains(".jpg") == false) {
                        fileName += ".jpg";
                    }

                    //get new instance of AstroDraw and create the offscreen image
                    AstroDraw ad = new AstroDraw();
                    ad.createOffScreenImage(fileName);
                }
            }
        });

    }
}
