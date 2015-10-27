import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Ball
{
    double px, py;
    double vx, vy;
    double mass;
    int radius;
    Color color;
    String name;
    static int count = 0;
    Ball()
    {
	color = new Color(255,180,0);
	px = 50;
	py = 60;
	vx = 8;
	vy = 9;
	name = "default";
	set_radius(17);
    }
    Ball(double px, double py, double vx, double vy, Color color)
    {
	this.px = px;
	this.py = py;
	this.vx = vx;
	this.vy = vy;
	this.color = color;
	name = "ball" + (++count);	
	set_radius(17);
    }
    Ball(int px, int py)
    {
	this.px = px;
	this.py = py;
	vx = vy = 0.0;
	color = Color.BLACK;
	set_radius(1);
    }
    public void randomize(double v, int wd, int ht)
    {
	Random r = new Random();
	px = wd*r.nextDouble();
	py = ht*r.nextDouble();
	double theta = r.nextDouble()*2.0*Math.PI;
	vx = v*Math.cos(theta);
	vy = v*Math.sin(theta);
    }
    public void set_name(String name)
    {
	this.name = name;
    }
    public void set_radius(int r)
    {
	radius = r;
	mass = r*r;
    }
    public void set_point(double x, double y)
    {
	px = x;
	py = y;
    }

    public String toString()
    {
	return String.format(" %s ",name);
    }

    public String info()
    {
	return String.format(" %s  pos (%.1f, %.1f) vel (%.1f %.1f) ",name,px,py,vx,vy);
    }
/*    
    public void move()
    {
	px += vx;
	py += vy;
    }
*/
    public void move(double delta)
    {
	px += vx*delta;
	py += vy*delta;
    }

    public void draw_outline(Graphics g)
    {
	g.drawOval((int)(px-radius), (int) (py-radius), (int)2*radius, (int)2*radius);
    }
    
    public void draw(Graphics g) 
    {
	g.setColor(color);
	//System.out.format("%s radius %g%n",name, radius
	g.fillOval((int)(px-radius), (int) (py-radius), (int)2*radius, (int)2*radius);
    }

    public boolean contains(Point2D pt)
    {
	double dist = Math.hypot(pt.getX()-px,pt.getY()-py);
	return (dist<radius);
    }

    public double dist(Ball b)
    {
	// relative position
	double dx = b.px - px;
	double dy = b.py - py;
	double dist = Math.hypot(dx,dy);
	// test radius
	double r = radius + b.radius;
        return dist-r;
    }

    public double intersect(Ball b)
    {
	double tx = 1000.0;
	// relative velocity
	double rx = b.vx - vx;
	double ry = b.vy - vy;
	// relative position
	double dx = b.px - px;
	double dy = b.py - py;
	// test radius
	double r = radius + b.radius;
	double A = rx*rx+ry*ry;
	double B = rx*dx+ry*dy;
	double C =  dx*dx + dy*dy - r*r;
	if (B>0.0) return -1.0; // balls moving apart
	if (C<0.0) return -1.0; // already intersecting
	if (A<=0.0) return tx; // no relative velocity
	// quadratic At^2 + 2Bt + C = 0
	double radical = B*B-A*C;
	if (radical<=0.0) return tx; // no intersection (balls miss)
	double R = Math.sqrt(radical);
	double t;
	if (B>0) t = (-B + R)/A;
	else t = -(B+R)/A;
	if (t<=0.0) return tx; // no intersection in future
	return t;
    }

    public double intersect_window_vertical(double width)
    {
	if (vx<0) {
	    return (px-radius)/(-vx);
	}
	else if (vx>0) {
	    return (width - px - radius)/(vx);
	}
	return -1.0;
   }

    public double intersect_window_horizontal(double height)
    {
	if (vy<0) {
	    return (py-radius)/(-vy);
	}
	else if (vy>0) {
	    return (height - py - radius)/vy;
	}
	return -1.0;
   }
}