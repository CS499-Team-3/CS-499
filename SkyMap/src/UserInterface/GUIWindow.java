/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import skymap.AstroDraw;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Emma
 */

public final class GUIWindow extends JFrame{    
    
    
    
    JPanel timePanel = new JPanel();
    JPanel latPanel = new JPanel();
    JPanel lonPanel = new JPanel();
    JPanel mainBtnPanel = new JPanel();
    JScrollPane skyMapScrollPane;
    JTextField dayField, monthField, yearField;
    JSpinner daySpin, monthSpin, yearSpin;
    JSpinner hourSpinner, minuteSpinner, secondSpinner;
    SpinnerDateModel hourModel, minuteModel, secondModel;
    SpinnerDateModel spinnerModel;
    JLabel dateLbl, backslashLbl1, backslashLbl2;
    JPanel btnPanel1 = new JPanel();
    JPanel btnPanel2 = new JPanel(); 
    JButton saveBtn = new JButton("Save");
    JButton generateMapBtn = new JButton("Generate Map");     
    JLabel lonLbl =  new JLabel("Longitude: ");  
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
    String[] signs = { "+", "-" };
    Integer[] degrees = new Integer[181];
    Integer[] minsAndSeconds = new Integer[61];
    
    public GUIWindow() {
        // Draw SkyMap
        AstroDraw astroDraw = new AstroDraw();
        astroDraw.drawSkyMap();
        BufferedImage skyMapImg = astroDraw.getImage();
        
        // Create arrays for drop downs
        for (int i = 0; i <= 180; i++) {
            degrees[i] = i;
        }
        for (int i = 0; i <= 60; i++) {
            minsAndSeconds[i] = i;
        }
        
        // Set window properties
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("SkyMap");
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());   

        // Make JPEG panel        
        JPanel jpegPanel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(skyMapImg));
        jpegPanel.add(label);
        skyMapScrollPane = new JScrollPane(jpegPanel);
        skyMapScrollPane.setPreferredSize(new Dimension(100, 100));             
        jpegPanel.setMinimumSize(new Dimension(800, 800));        
        
        // Add scroll pane and main panel to window 
        add(skyMapScrollPane, BorderLayout.CENTER);
        add(makeMainPanel(), BorderLayout.SOUTH);   
        addListeners();
    }
    public JPanel makeDatePanel() {
        spinnerModel = new SpinnerDateModel();
        
        dateLbl = new JLabel("Date: ");
        daySpin = new JSpinner(spinnerModel);
        ((DefaultEditor) daySpin.getEditor()).getTextField().setEditable(false);
        
        //backslashLbl1 = new JLabel("/");  
        //backslashLbl2 = new JLabel("/");
        
        JPanel datePanel = new JPanel();
        datePanel.add(dateLbl);
        datePanel.add(daySpin);
        
        return datePanel;
    }
    
    public JPanel makeLatPanel() { 
        latPanel.setPreferredSize(new Dimension(1000, 200));
        latPanel.add(latLbl);
        latSignCombo = new JComboBox(signs);        
        latDegCombo = new JComboBox(degrees);        
        latMinCombo = new JComboBox(minsAndSeconds);
        latSecCombo = new JComboBox(minsAndSeconds);
        latPanel.add(latSignCombo); 
        latPanel.add(latDegCombo);
        latPanel.add(latMinCombo);
        latPanel.add(latSecCombo);
        return latPanel;
    }
    
    public JPanel makeLonPanel() {
        lonPanel.setPreferredSize(new Dimension(1000, 200));
        lonPanel.add(lonLbl);        
        lonSignCombo = new JComboBox(signs);        
        lonDegCombo = new JComboBox(degrees);        
        lonMinCombo = new JComboBox(minsAndSeconds);
        lonSecCombo = new JComboBox(minsAndSeconds);
        lonPanel.add(lonSignCombo); 
        lonPanel.add(lonDegCombo);
        lonPanel.add(lonMinCombo);
        lonPanel.add(lonSecCombo);
        return lonPanel;
    }
    
    public JPanel makeMainPanel() {        
        btnPanel1.setLayout(new GridLayout(1, 3, 1, 1));        
        btnPanel1.setPreferredSize(new Dimension(50, 75));
        btnPanel1.add(makeDatePanel());        
        btnPanel1.add(makeLatPanel());
        btnPanel1.add(makeLonPanel());
        
        btnPanel2.setLayout(new GridLayout(2, 1, 1, 1));
        btnPanel2.add(generateMapBtn);
        btnPanel2.add(saveBtn);
        
        mainBtnPanel.setLayout(new GridLayout(1, 2, 1, 1));
        mainBtnPanel.add(btnPanel1);
        mainBtnPanel.add(new JPanel());
        mainBtnPanel.add(btnPanel2);
        
        return mainBtnPanel;
    }
    
    public void addListeners() {
        generateMapBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) { 
                    latSign = (String) latSignCombo.getSelectedItem();
                    latDeg = (Integer) latDegCombo.getSelectedItem();
                    if (latSign.equals("-")) {
                        latDeg *= -1;
                    }
                    latMin = (Integer) latMinCombo.getSelectedItem();
                    latSec = (Integer) latSecCombo.getSelectedItem();
                    System.out.println("Latitude:");
                    System.out.println(latDeg + " " + latMin + ":" + 
                            latSec);
                    lonSign = (String) lonSignCombo.getSelectedItem();
                    lonDeg = (Integer) lonDegCombo.getSelectedItem();
                    if (lonSign.equals("-")) {
                        lonDeg *= -1;
                    }
                    lonMin = (Integer) lonMinCombo.getSelectedItem();
                    lonSec = (Integer) lonSecCombo.getSelectedItem();
                    System.out.println("Longitude:");
                    System.out.println(lonDeg + " " + lonMin + ":" + 
                            lonSec);
            }
            
        });
        
        //action listener for the save button
        saveBtn.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                //get a file chooser, add a filter for jpg only
                JFileChooser c = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("jpg image files", "jpg");
                c.setDialogTitle("Save StarMap as a JPEG");
                c.setFileFilter(filter);
                
                //if user enters a correct filename
                int saveValue = c.showSaveDialog(GUIWindow.this);
                if(saveValue == JFileChooser.APPROVE_OPTION) {
                    //Test the string for .jpg extention
                    String fileName = (c.getSelectedFile().toString());
                    if(fileName.contains(".jpg") == false)
                    {
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
