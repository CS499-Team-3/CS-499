/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.io.File;
import javax.imageio.ImageIO;

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
    
    public boolean drawLine(Graphics g, double x1, double y1, double x2, double y2)
    {
        set_color(Color.WHITE);
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        return true;
    }

    public void createLabel(String label, int x, int y) {
        graphics.drawString(label, x, y + 15);
    }

    public void drawStar(Star star) {
        if (star.location.x < 0) {
            return;
        }
        set_color(Color.WHITE);
//        int x = (int) (star.location.y * 5000) + 1200;//constants TBD
//        int y = (int) (star.location.z * 5000) + 1650;//constants TBD
        int magnitude = (int) star.getMagnitude();
        draw_circle(graphics, (int)(star.location.x*100), (int)(star.location.y*-100), magnitude);
    }

    public void drawPlanet(Planet planet) {
        if (planet.location.x < 0) {
            planet.isVisible = false;
            return;
        }
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
        x = (int)(planet.location.y * 100)+1200;
        y = (int)(planet.location.z * 100)+1650;
        //TODO: use RA and dec as x, y
        // Add RA and dec to planet
        set_color(color);
        draw_circle(graphics, x, y, radius);
    }
    
    public void drawMoon(Moon m)
    {
        int x = (int)(m.location.y * 100)+1200;
        int y = (int)(m.location.z * 100)+1650;
        set_color(Color.cyan); //TBD
        switch (m.phase)
        {
            case NEW_MOON:
                break;
            case FIRST_QUARTER:
                break;
            case FULL_MOON:
                break;
            case LAST_QUARTER:
                break;
            default:
                break;
        }
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

        System.out.println(sList.size());

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
                    createLabel(p.name, (int) (p.location.y * 100 + 1180),
                            (int) (p.location.z * 100 + 1660));
                }
                System.out.println(p.name + ":\nx: " + p.location.x
                        + "\ny: " + p.location.y + "\nz: "
                        + p.location.z + "\n\n");
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
        offscreenImage = new BufferedImage(2400, 3300, BufferedImage.TYPE_INT_ARGB);
        
        //set class boolean to true so drawSkyMap draws to offscreen image
        drawOffScreenImage = true;
        
        //draw the skymap
        drawSkyMap();
        
        //save image
        saveOffScreenImage(fname);
        
        //set class boolean to false so that drawSkyMap draws to the screen again
        drawOffScreenImage = false;
    }

    //saves an image to the harddrive, is called by create offscreen image
    private void saveOffScreenImage(String fname) {
        File imgFile = new File(fname);
        try {
            ImageIO.write(offscreenImage, "jpg", imgFile);
        } catch (Exception e) {
            System.out.println("Failed to write image to disk.");
        }
    }

}
