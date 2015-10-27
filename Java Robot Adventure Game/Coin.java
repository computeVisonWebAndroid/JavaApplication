import java.awt.*;
import java.awt.geom.*;

public class Coin extends Ball
{
    Coin(int px, int py)
    {
	this.px = px;
	this.py = py;
	vx = 0;
	vy = 0;
	name = "destination";
	set_radius(9);
	color = new Color(255,200,0);
    }
    public void draw(Graphics g) 
    {
	Graphics2D g2 = (Graphics2D) g;
	BasicStroke s =
        new BasicStroke(4.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
	Stroke old_stroke = g2.getStroke();
	g2.setStroke(s);
	g2.setColor(color);
	g2.draw(new Line2D.Double(px-radius, py-radius, px+radius, py+radius));
	g2.draw(new Line2D.Double(px-radius, py+radius, px+radius, py-radius));
    }
}