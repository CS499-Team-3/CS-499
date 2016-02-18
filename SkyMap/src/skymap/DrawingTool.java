/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skymap;
import java.awt.Image;
import java.awt.Color;
import java.util.List;
/**
 *
 * @author Lindsey
 */
public class DrawingTool 
{
    Image background;
    
    public DrawingTool()
    {
        
    }
    
    public void set_background(Image background)
    {
        this.background = background;
    }
    
    public boolean draw_SpaceObjects(List<SpaceObject> stars)
    {
        // Implement. I don't think I like this function here.
        return true;
    }
    
    public boolean draw_circle(double x, double y, float diameter)
    {
        return true;
    }
    
    public boolean draw_line(double start_x, double start_y, double end_x, double end_y)
    {
        return true;
    }
}
