import java.awt.*;
import java.util.Random;

public class Ball
{
    double px, py;
    double vx, vy;
    double mass;
    int radius;
    double t = 0.0;
    Color color;
    String name;
    static int count = 0;
    Ball()
    {
	color = new Color(255,0,0);
	px = 50;
	py = 60;
	vx = 8;
	vy = 9;
	name = "default";
	set_radius(17);
    }
    Ball(int px, int py, int vx, int vy, Color color)
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
    public void randomize(double v)
    {
	Random r = new Random();
	px = 400*r.nextDouble();
	py = 400*r.nextDouble();
	double theta = r.nextDouble()*2.0*Math.PI;
	vx = v*Math.cos(theta);
	vy = v*Math.sin(theta);
    }
    public void set_radius(int r)
    {
	radius = r;
	mass = r*r;
    }

    public String toString()
    {
	return String.format("%s pos (%.0f, %.0f) vel (%.1f %.1f) ",name,px,py,vx,vy);
    }
    
    public void move()
    {
	px += vx;
	py += vy;
    }

    public void move(double delta)
    {
	px += vx*delta;
	py += vy*delta;
    }
    
    public void draw(Graphics g) 
    {
	g.setColor(color);
	//System.out.format("%s radius %g%n",name, radius
	g.fillOval((int)(px-radius), (int) (py-radius), (int)2*radius, (int)2*radius);
	double qx, qy;
	if (t>0.0) {
	   qx = px + vx*t;
	   qy = py + vy*t;
           g.setColor(Color.BLACK);
	   g.drawOval((int)(qx-radius),(int)(qy-radius),(int)2*radius,(int)2*radius);
	}
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
	double r = radius + b.radius - 1;
	double C =  dx*dx + dy*dy - r*r;
	if (C<0.0) return 0.0; // already intersecting
	double B = rx*dx+ry*dy;
	double A = rx*rx+ry*ry;
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
}