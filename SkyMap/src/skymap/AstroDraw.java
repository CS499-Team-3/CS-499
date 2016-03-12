/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author Lindsey
 */
public class AstroDraw 
{
    Image background;
    Color c;
    BufferedImage image;
    Graphics2D graphics;
    
    public AstroDraw()
    {
        
    }
    
    public void set_background_color(Graphics g, BufferedImage image, Color color){
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }
    
    public void set_background(Image background)
    {
        this.background = background;
        this.c = Color.WHITE;
    }
    
    public void set_color(Color color)
    {
        this.c = color;
        // not sure if the next line is necessary 
        graphics.setColor(color);
    }
    
    public boolean draw_circle(Graphics g, int x, int y, int radius)
    {
        int diameter = radius * 2;
        x = x-radius;
        y = y-radius;
        g.setColor(c);
        g.fillOval(x, y, diameter, diameter);
        return true;
    }
    
    public boolean draw_line(double start_x, double start_y, double end_x, double end_y)
    {
        return true;
    }
    
    public void createLabel(String label, int x, int y) {         
         graphics.drawString(label, x, y);
     }
    
     public void drawStar(Star star) {
        set_color(Color.WHITE);
        int x = (int) star.location.x;
        int y = (int) star.location.y;
        int magnitude = (int) star.getMagnitude();
        draw_circle(graphics, x, y, magnitude);
    }
    
    public void drawPlanet(Planet planet) {
        Color color = null;
        int x = 0; 
        int y = 0;
        int radius = 0;
        String planetName = planet.name.toLowerCase();
        switch(planetName) {
            case "mercury":
                color = new Color(0xFF6600);
                // passing in temp values for testing
                x = 250;
                y = 350;
                radius = 12;
                break;
            case "venus":
                color = new Color(0xFFCC99);
                // passing in temp values for testing
                x = 450;
                y = 250;
                radius = 12;
                break;
            case "earth/sun":
                color = new Color(0x009900);
                // passing in temp values for testing
                x = 150;
                y = 550;
                radius = 12;
                break;
            case "mars":
                color = new Color(0xCC3300);
                // passing in temp values for testing
                x = 250;
                y = 350;
                radius = 12;
                break;
            case "jupiter":
                color = new Color(0xFF9933);
                // passing in temp values for testing
                x = 200;
                y = 210;
                radius = 12;
                break;
            case "saturn":
                color = new Color(0xFF3333);
                // passing in temp values for testing
                x = 410;
                y = 350;
                radius = 12;
                break;
            case "uranus":
                color = new Color(0x00FF99);
                // passing in temp values for testing
                x = 100;
                y = 350;
                radius = 12;
                break;
            case "neptune":
                color = new Color(0x0099CC);
                // passing in temp values for testing
                x = 500;
                y = 70;
                radius = 12;
                break;
            case "pluto":
                color = new Color(0x996633);
                // passing in temp values for testing
                x = 70;
                y = 200;
                radius = 12;
                break;
            default: break;
        }
        set_color(color);
        draw_circle(graphics, x, y, radius);
    }
    
    public void drawSkyMap() {
        image = new BufferedImage(2400, 3300, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();       
        set_background_color(graphics, image, new Color(0x000080));
        Planet planet = new Planet("Mercury");
        drawPlanet(planet);
        //createLabel("Star", 90, 350);
        set_color(Color.BLACK);
        createLabel("Mercury", 230, 372);
    } 
    

    public BufferedImage getImage() {
        return image;
    }
    
    
    
}