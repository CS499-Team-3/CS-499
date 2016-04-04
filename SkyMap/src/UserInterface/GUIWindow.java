/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
    JSpinner dateSpin, monthSpin, yearSpin;
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
    String[] degrees = new String[182];
    String[] mins = new String[62];
    String[] seconds = new String[62];
    Font comboFont = new Font(Font.DIALOG, Font.PLAIN, 12);
    
    public GUIWindow() {
        // Set window properties
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setTitle("SkyMap");
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());    
        
        // Draw SkyMap
        AstroDraw astroDraw = new AstroDraw();
        astroDraw.drawSkyMap();
        BufferedImage skyMapImg = astroDraw.getImage();
        
        // Create arrays for drop downs
        degrees[0] = "Degrees";
        for (int i = 1; i <= 181; i++) {
            degrees[i] = Integer.toString(i-1);
        }
        mins[0] = "Minutes";
        seconds[0] = "Seconds";
        for (int i = 1; i <= 61; i++) {
            mins[i] = String.valueOf(i-1);
            seconds[i] = String.valueOf(i-1);
        }
       
        // Make JPEG panel        
        JPanel jpegPanel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(skyMapImg));
        jpegPanel.add(label);
        skyMapScrollPane = new JScrollPane(jpegPanel);
        //skyMapScrollPane.setPreferredSize(new Dimension(100, 100));             
        jpegPanel.setMinimumSize(new Dimension(800, 800));        
        
        // Add scroll pane and main panel to window 
        add(skyMapScrollPane, BorderLayout.CENTER);
        add(makeMainPanel(), BorderLayout.SOUTH);   
        addListeners();
    }
    public JPanel makeDatePanel() {
        spinnerModel = new SpinnerDateModel();
        
        dateLbl = new JLabel("Date: ");
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
        latSignCombo.setPreferredSize(new Dimension(35, 20));
        latDegCombo.setPreferredSize(new Dimension(77, 20));
        latMinCombo.setPreferredSize(new Dimension(77, 20));
        latSecCombo.setPreferredSize(new Dimension(77, 20));        
        
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
        
        lonSignCombo.setPreferredSize(new Dimension(35, 20));
        lonDegCombo.setPreferredSize(new Dimension(77, 20));
        lonMinCombo.setPreferredSize(new Dimension(77, 20));
        lonSecCombo.setPreferredSize(new Dimension(77, 20));        
        
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
        btnPanel1.setPreferredSize(new Dimension(325, 50));
        btnPanel1.add(makeDatePanel());        
        btnPanel1.add(makeLatPanel());
        btnPanel1.add(makeLonPanel());
        
        btnPanel2.setLayout(new BoxLayout(btnPanel2, BoxLayout.Y_AXIS));
        btnPanel2.setPreferredSize(new Dimension(200, 50));
        btnPanel2.add(generateMapBtn);
        btnPanel2.add(saveBtn);
        
        //mainBtnPanel.setLayout(new GridLayout(1, 2, 1, 1));
        mainBtnPanel.setLayout(new BoxLayout(mainBtnPanel, BoxLayout.X_AXIS));
        mainBtnPanel.add(btnPanel1);
        mainBtnPanel.add(new JPanel());
        mainBtnPanel.add(btnPanel2);
        
        return mainBtnPanel;
    }
    
    public void addListeners() {
        generateMapBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) { 
                    System.out.println(getWidth());
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
                        System.out.println(latDeg + " " + latMin + ":" + 
                                latSec);
                        
                        lonDeg = Integer.parseInt((String) lonDegCombo.getSelectedItem());

                        if (lonSignCombo.getSelectedItem().equals("-")) {
                            lonDeg *= -1;
                        }
                        lonMin = Integer.parseInt((String) lonMinCombo.getSelectedItem());
                        lonSec = Integer.parseInt((String) lonSecCombo.getSelectedItem());
                        System.out.println("Longitude:");
                        System.out.println(lonDeg + " " + lonMin + ":" + 
                                lonSec);
                    }
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
