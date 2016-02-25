/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;
import java.awt.*;
import javax.swing.*;
import java.util.List;
/**
 *
 * @author Lindsey
 */
public abstract class DrawingTool extends Graphics
{
    Image background;
    Color c;
    public DrawingTool()
    {
        
    }
    
    public void set_background(Image background)
    {
        this.background = background;
        this.c = Color.WHITE;
    }
    
    public void set_color(Color color)
    {
        this.c = color;
    }
    
    public boolean draw_circle(int x, int y, int radius)
    {
        int diameter = radius * 2;
        x = x-radius;
        y = y-radius;
        setColor(c);
        this.fillOval(x, y, diameter, diameter);
        return true;
    }
    
    public boolean draw_line(double start_x, double start_y, double end_x, double end_y)
    {
        return true;
    }
}