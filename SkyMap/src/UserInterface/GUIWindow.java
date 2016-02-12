/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author Emma
 */
// GUI - full screen window
public final class GUIWindow extends JFrame{
    JPanel jpegPanel, btnPanel1, btnPanel2, datePanel, latPanel, longPanel;
    JScrollPane skyMapScrollPane;
    JButton saveBtn, generateMapBtn;
    JTextField dayField, monthField, yearField, latField, longField;
    JLabel dateLbl, latLbl, longLbl, backslashLbl1, backslashLbl2;
    public GUIWindow() {
        btnPanel1 = new JPanel();
        jpegPanel = new JPanel();
        btnPanel2 = new JPanel();
        datePanel = new JPanel();
        saveBtn = new JButton("Save");
        generateMapBtn = new JButton("Generate\nMap");
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
        datePanel = new JPanel();
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
        setMinimumSize(new Dimension(500, 500)); // Full-Screen Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());        
        jpegPanel.setMinimumSize(new Dimension(800, 800));
        btnPanel1.setLayout(new GridLayout(3,1,1,1));

        btnPanel1.setPreferredSize(new Dimension(50, 75));
        btnPanel1.add(datePanel);
        btnPanel1.add(latPanel);
        btnPanel1.add(longPanel);
        add(skyMapScrollPane, BorderLayout.CENTER);
        JPanel mainBtnPanel = new JPanel();
        mainBtnPanel.setLayout(new GridLayout(1, 2, 1, 1));
        mainBtnPanel.add(btnPanel1);
        add(mainBtnPanel, BorderLayout.SOUTH);       
        
    }
}
