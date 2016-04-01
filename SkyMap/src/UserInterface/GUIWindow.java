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

/**
 *
 * @author Emma
 */

public final class GUIWindow extends JFrame{    
    
    
    
    JPanel timePanel = new JPanel();
    JPanel latPanel = new JPanel();
    JPanel longPanel = new JPanel();
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
    JLabel longLbl =  new JLabel("Longitude: ");  
    JComboBox latSignCombo;
    JComboBox lonSignCombo = new JComboBox();
    JComboBox latDegCombo;
    JComboBox lonDegCombo = new JComboBox();
    JComboBox latMinCombo;
    JComboBox lonMinCombo = new JComboBox();
    JComboBox latSecCombo;
    JComboBox lonSecCombo = new JComboBox();
    JLabel latLbl = new JLabel("Latitude: "); 
    String latSign = "";
    Integer latDeg = 0;
    Integer latMin = 0;
    Integer latSec = 0;
    
    public GUIWindow() {
        // Draw SkyMap
        AstroDraw astroDraw = new AstroDraw();
        astroDraw.drawSkyMap();
        BufferedImage skyMapImg = astroDraw.getImage();
        
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
        /* minuteSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.YEAR));
        secondSpinner = new JSpinner();
        timePanel = new JPanel(); 
        hourModel = new SpinnerDateModel(new Date(), 1, 12, Calendar.HOUR_OF_DAY);
        hourSpinner = new JSpinner(hourModel); */
        spinnerModel = new SpinnerDateModel();
        
        dateLbl = new JLabel("Date: ");
        //dayField = new JTextField("DD");
        //monthField = new JTextField("MM");
        //yearField = new JTextField("YYYY");
        daySpin = new JSpinner(spinnerModel);
        ((DefaultEditor) daySpin.getEditor()).getTextField().setEditable(false);
        //monthSpin = new JSpinner();
        //yearSpin = new JSpinner();
        
        backslashLbl1 = new JLabel("/");  
        backslashLbl2 = new JLabel("/");
        
        JPanel datePanel = new JPanel();
        datePanel.add(dateLbl);
        /*datePanel.add(dayField);
        datePanel.add(backslashLbl1);
        datePanel.add(monthField);
        datePanel.add(backslashLbl2);
        datePanel.add(yearField);*/
        datePanel.add(daySpin);
        
        return datePanel;
    }
    
    public JPanel makeLatPanel() { 
        latPanel.setPreferredSize(new Dimension(1000, 200));
        latPanel.add(latLbl);
        String[] signs = { "+", "-" };
        latSignCombo = new JComboBox(signs);
        Integer[] degrees = new Integer[181];
        for (int i = 0; i <= 180; i++) {
            degrees[i] = i;
        }
        latDegCombo = new JComboBox(degrees);
        Integer[] minsAndSeconds = new Integer[61];
        for (int i = 0; i <= 60; i++) {
            minsAndSeconds[i] = i;
        }
        latMinCombo = new JComboBox(minsAndSeconds);
        latSecCombo = new JComboBox(minsAndSeconds);
        latPanel.add(latSignCombo); 
        latPanel.add(latDegCombo);
        latPanel.add(latMinCombo);
        latPanel.add(latSecCombo);
        return latPanel;
    }
    
    public JPanel makeLongPanel() {
        longPanel.add(longLbl);
        //longPanel.add(longField);
        return longPanel;
    }
    
    public JPanel makeMainPanel() {        
        btnPanel1.setLayout(new GridLayout(1, 5, 1, 1));        
        btnPanel1.setPreferredSize(new Dimension(50, 75));
        btnPanel1.add(makeDatePanel());        
        btnPanel1.add(makeLatPanel());
        btnPanel1.add(makeLongPanel());
        
        btnPanel2.setLayout(new GridLayout(2, 1, 1, 1));
        btnPanel2.add(generateMapBtn);
        btnPanel2.add(saveBtn);
        
        mainBtnPanel.setLayout(new GridLayout(1, 3, 1, 1));
        //mainBtnPanel.add(new JPanel());
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
                    latMin = (Integer) latMinCombo.getSelectedItem();
                    latSec = (Integer) latSecCombo.getSelectedItem();
                    System.out.println("Latitude:");
                    System.out.println(latSign + latDeg + " " + latMin + ":" + 
                            latSec);
            }
        });
        //latDegField.addInputMethodListener(null);
    }
}
