/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.RescaleOp;


/**
 *
 * @author Lindsey
 */
public class AstroDraw extends JFrame {

    Image background;
    Color c;
    BufferedImage image;
    Graphics2D graphics;
    Image offscreen;
    Graphics2D offscreenGraphics;
    boolean drawOffScreenImage;
    BufferedImage offscreenImage;

    public AstroDraw() {
        drawOffScreenImage = false;
    }

    public void set_background_color(Graphics g, BufferedImage image, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }

    public void set_background(Image background) {
        this.background = background;
        this.c = Color.WHITE;
    }

    public void set_color(Color color) {
        this.c = color;
        // not sure if the next line is necessary 
        graphics.setColor(color);
    }

    public boolean draw_circle(Graphics g, int x, int y, int radius) {
        int diameter = radius * 2;
        x = x - radius;
        y = y - radius;
        g.setColor(c);
        g.fillOval(x, y, diameter, diameter);
        return true;
    }

    public boolean drawLine(Graphics g, double x1, double y1, double x2, double y2) {
        set_color(Color.WHITE);
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        return true;
    }

    public boolean drawRectangle(Graphics g, int x, int y, int width, int height, Color color){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        return true;
    }

    public void createLabel(String label, int x, int y) {
        graphics.drawString(label, x, y + 15);
    }

    public void drawStar(Star star) {
        set_color(Color.WHITE);
        int magnitude = (int) star.getMagnitude();
        System.out.println("x: "+star.location.x+"\ny: "+star.location.y);
        draw_circle(graphics, (int) (star.location.x * 1000), (int) (star.location.y * -1000)+3300, magnitude);
    }

    public void drawMoon(Moon moon, Graphics graphics){
        //int x = (int)(moon.location.y* 5000) + 1200;
        //int y = (int)(moon.location.z * 5000) + 1650;
        //moon.setPhase(lunar_phase.LAST_QUARTER);
        int diameter = 20;
        if(moon.phase == lunar_phase.NEW_MOON){
            graphics.setColor(Color.BLACK);
            graphics.fillOval(8, 10, diameter, diameter);
        }
        else if(moon.phase == lunar_phase.FIRST_QUARTER){
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillOval(8, 10, diameter, diameter);
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 10, diameter, diameter);
        }
        else if(moon.phase == lunar_phase.LAST_QUARTER){
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillOval(8, 10, diameter, diameter);
            graphics.setColor(Color.BLACK);
            graphics.fillRect(18, 10, diameter, diameter);

        }
        else{
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillOval(8, 10, diameter, diameter);
        }
    }

    public void drawPlanet(Planet planet) {
        AstronomyCalculator calc = new AstronomyCalculator();
        Color color = null;
        int x;
        int y;
        int radius = 12;
        String planetName = planet.name.toLowerCase();
        switch (planetName) {
            case "mercury":
                color = new Color(0xFF6600);
                break;
            case "venus":
                color = new Color(0xFFCC99);
                break;
            case "earth/sun":
                color = new Color(0x009900);
                break;
            case "mars":
                color = new Color(0xCC3300);
                break;
            case "jupiter":
                color = new Color(0xFF9933);
                break;
            case "saturn":
                color = new Color(0xFF3333);
                break;
            case "uranus":
                color = new Color(0x00FF99);
                break;
            case "neptune":
                color = new Color(0x0099CC);
                break;
            case "pluto":
                color = new Color(0x996633);
                break;
            default:
                break;
        }
        x = (int) (planet.location.x)+1200;
        y = (int) (planet.location.y*-1)+1650;
        set_color(color);
        draw_circle(graphics, x, y, radius);
    }


    public void drawSkyMap() {

        if (drawOffScreenImage == false) {
            image = new BufferedImage(2400, 3300, BufferedImage.TYPE_INT_ARGB);
        } else {
            image = offscreenImage;
        }
        graphics = image.createGraphics();
        //new Color(0x000080)
        set_background_color(graphics, image, new Color(0x000000));

        SkyBox sBox = SkyBox.getSkyBox();
        List<Planet> pList = new ArrayList();
        pList = sBox.getPlanetList();
        List<Star> sList = new ArrayList();
        sList = sBox.getStarList();

        Moon moon = sBox.getMoon();
        
        set_color(Color.WHITE);
        //draw stars
        for (int i = 0; i < sList.size(); i++) {
            Star s = sList.get(i);
            drawStar(s);
            //System.out.println("star x: "+s.location.x +"\nstar y: "+s.location.y);
        }
        for (int i = 0; i < pList.size(); i++) {
            Planet p = pList.get(i);
            p.isVisible = true;
            if (p.name.toUpperCase().equalsIgnoreCase("EARTH")) {
            } else {
                drawPlanet(p);
                if (p.isVisible) {
                    createLabel(p.name, (int) (p.location.x * 1)+1200,
                            (int) (p.location.y * -1)+1650);
                }
            }

        }
    }

    public BufferedImage getImage() {
        return image;
    }

    //Function to create an offscreen image
    public void createOffScreenImage(String fname) {
        //create the image
        Toolkit tk = Toolkit.getDefaultToolkit();
        offscreen = tk.createImage("fname");
        offscreenImage = new BufferedImage(2400, 3300, BufferedImage.TYPE_INT_RGB);

        //set class boolean to true so drawSkyMap draws to offscreen image
        drawOffScreenImage = true;

        //set up filter to ensure the colors of the image are accerate
        RescaleOp op = new RescaleOp(1.1f, 0.0f, null);

        //draw the skymap
        drawSkyMap();
        
        //run the image through the filter
        BufferedImage tempImage = op.filter(offscreenImage, null);

        //save image
        saveOffScreenImage(fname, tempImage);

        //set class boolean to false so that drawSkyMap draws to the screen again
        drawOffScreenImage = false;
    }

    //saves an image to the harddrive, is called by create offscreen image
    private void saveOffScreenImage(String fname, BufferedImage temp) {
        File imgFile = new File(fname);
        try {
            ImageIO.write(temp, "jpg", imgFile);
        } catch (Exception e) {

        }
    }

}
