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
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import skymap.AstroDraw;
import sun.util.calendar.CalendarDate;

/**
 *
 * @author Emma
 */
// GUI - full screen window
public final class GUIWindow extends JFrame{
    JPanel jpegPanel, btnPanel1, btnPanel2, datePanel, timePanel, latPanel, longPanel;
    JScrollPane skyMapScrollPane;
    JButton saveBtn, generateMapBtn;
    JTextField dayField, monthField, yearField, latField, longField;
    JSpinner hourSpinner, minuteSpinner, secondSpinner;
    SpinnerDateModel hourModel, minuteModel, secondModel;
    JLabel dateLbl, latLbl, longLbl, backslashLbl1, backslashLbl2;
    public GUIWindow() {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        AstroDraw drawingTool;
        drawingTool = new AstroDraw();
        //drawingTool.set_background_color(graphics, image, Color.BLACK);
        drawingTool.draw_circle(graphics, 10, 10, 10);
        drawingTool.set_color(Color.BLACK);
        drawingTool.draw_circle(graphics, 15, 15, 10);
        String lab = "label";
        drawingTool.createLabel(graphics, lab, 20, 20);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        btnPanel1 = new JPanel();
        jpegPanel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(image));
        //label.setPreferredSize(new Dimension(500, 700));
        jpegPanel.add(label);
        btnPanel2 = new JPanel();
        datePanel = new JPanel();
        timePanel = new JPanel();
   

//        hourModel = new SpinnerDateModel(new Date(), 1, 12, Calendar.HOUR_OF_DAY);
//        hourSpinner = new JSpinner(hourModel);
        minuteSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.YEAR));
        secondSpinner = new JSpinner();

        saveBtn = new JButton("Save");
        generateMapBtn = new JButton("Generate Map");
        dayField = new JTextField("DD");
        monthField = new JTextField("MM");
        yearField = new JTextField("YYYY");
        latField = new JTextField("0");
        longField = new JTextField("0");
        dateLbl = new JLabel("Date: ");
        latLbl = new JLabel("Lat: ");
        longLbl =  new JLabel("Long: ");
        backslashLbl1 = new JLabel("/");  
        backslashLbl2 = new JLabel("/");
        latPanel = new JPanel();
        longPanel = new JPanel();
        datePanel.add(dateLbl);
        datePanel.add(dayField);
        datePanel.add(backslashLbl1);
        datePanel.add(monthField);
        datePanel.add(backslashLbl2);
        datePanel.add(yearField);
        latPanel.add(latLbl);
        latPanel.add(latField);
        longPanel.add(longLbl);
        longPanel.add(longField);
        skyMapScrollPane = new JScrollPane(jpegPanel);
        skyMapScrollPane.setPreferredSize(new Dimension(100, 100));
        setTitle("SkyMap");
        setMinimumSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());        
        jpegPanel.setMinimumSize(new Dimension(800, 800));
        btnPanel1.setLayout(new GridLayout(2, 2, 1, 1));        
        btnPanel1.setPreferredSize(new Dimension(50, 75));
        btnPanel1.add(datePanel);
        
        btnPanel1.add(latPanel);
        btnPanel1.add(longPanel);
        btnPanel2.setLayout(new GridLayout(2, 1, 1, 1));
        btnPanel2.add(generateMapBtn);
        btnPanel2.add(saveBtn);
        add(skyMapScrollPane, BorderLayout.CENTER);
        JPanel mainBtnPanel = new JPanel();
        mainBtnPanel.setLayout(new GridLayout(1, 2, 1, 1));
        mainBtnPanel.add(btnPanel1);
        mainBtnPanel.add(new JPanel());
        mainBtnPanel.add(btnPanel2);
        add(mainBtnPanel, BorderLayout.SOUTH);       
        
    }
}
