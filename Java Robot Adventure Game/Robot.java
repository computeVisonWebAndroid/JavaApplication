import java.awt.*;
import java.awt.geom.*;


public class Robot extends Ball
{
    double vl, vr, theta;
/*
    double vl, vr;
    double vx, vy;
    double mass;
    int radius;
    Color color;
*/
    Robot()
    {
	color = new Color(255,0,0);
	px = 50;
	py = 50;
	vr = 7;
	vl = -7;
	set_radius(18);
    }
    Robot(int px, int py, int vr, int vl, Color color)
    {
	setPose(px,py,0);
	setVelocity(vr,vl);
	this.color = color;
	set_radius(18);
    }
    
    public String toString()
    {
      return String.format("px,py (%3.0f,%4.0f) vr,vl (%6.1f,%6.1f)",px,py,vr,vl);
    }
    

    public void setPose(double px, double py, double theta)
    {
	this.px = px;
	this.py = py;
	this.theta = theta;
    }

    public void updateVelocity()
    {
	double v = 0.5*(vr+vl);
	double rtheta = Math.toRadians(theta);
	vx = v*Math.cos(rtheta);
	vy = v*Math.sin(rtheta);
    }

    public void setVelocity(double vr, double vl)
    {
	boolean debug = false;
        this.vr = vr;
	this.vl = vl;
	double ell = 12.0;
	if (!debug) return;
	if (vr==vl) {
	  if (vr==0.0) System.out.println("robot stopped");
	  else System.out.format("v %g %n",vr);
        }
        else {
	   double RADIUS = ell/2*(vr+vl)/(vr-vl);
	   double PERIOD = 2*ell/(vr-vl);
	   System.out.printf("vr %g vl %g radius %g period %g PI%n",vr,vl,RADIUS,PERIOD);
        }
    }

    public void move(double delta)
    {
	double wp = 12.0;
	double  dv, dtheta, rtheta;

	dtheta = (vl-vr)*Math.toDegrees(delta/wp);
	dv = 0.5*(vr+vl)*delta;
	rtheta = Math.toRadians(theta);
	px += dv*Math.cos(rtheta);
	py += dv*Math.sin(rtheta);
	theta += dtheta;
    }
    
    public void draw(Graphics g) 
    {
	Graphics2D g2 = (Graphics2D) g;
/*
	BasicStroke s =
        new BasicStroke(8.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
	Stroke old_stroke = g2.getStroke();
	g2.setStroke(s);
	g2.setColor(Color.YELLOW);
	g2.draw(new Line2D.Double(prevx, prevy, px, py));
	//System.out.format("%6.2f %6.2f %n",px-prevx,py-prevy);
	g2.setColor(Color.BLACK);
	g2.setStroke(old_stroke);
*/

	AffineTransform saveXform = g2.getTransform();
	AffineTransform at = new AffineTransform();
	at.translate(px,py);
	at.rotate(Math.toRadians(theta));
	g2.transform(at);
	Path2D body = new Path2D.Double();
	body.moveTo(-8,10);
	body.lineTo(12,10);
	body.lineTo(16,0);
	body.lineTo(12,-10);
	body.lineTo(-8,-10);
	body.lineTo(-8,10);
	g2.setColor(color);
	g2.fill(body);
	g2.setColor(Color.BLACK);
	g2.draw(body);
	Path2D.Double wheel = new Path2D.Double();
	wheel.moveTo(-4,-2);
	wheel.lineTo(-4,2);
	wheel.lineTo(4,2);
	wheel.lineTo(4,-2);
	wheel.lineTo(-4,-2);
	AffineTransform at2 = new AffineTransform();
	at2.setToTranslation(0,-6);
	wheel.transform(at2);
	g2.fill(wheel);
	at2.setToTranslation(0,12);
	wheel.transform(at2);
	g2.fill(wheel);

	// draw robot enclosing circle
	/*
	Ellipse2D elip = new Ellipse2D.Double(-radius,-radius,2*radius,2*radius);
	g2.draw(elip);
	*/	

	// draw robot axes
	/*
	Line2D line = new Line2D.Double(-20,0,20,0);
	g2.draw(line);
	line.setLine(0,-20,0,20);
	g2.draw(line);
	*/

	g2.setTransform(saveXform);	
    }

    public double intersect_window(int width, int height)
    {
	double t;
	double tx = 1000;
	if (vx<0) {
	    t = (px-radius)/(-vx);
	    if (t<tx) tx = t;
	}
	else if (vx>0) {
	    t = (width - px - radius)/vx;
            if (t<tx) tx = t;
      
	}
	if (vy<0) {
	    t = (py-radius)/(-vy);
	    if (t<tx) tx = t;
	}
	else if (vy>0) {
	    t = (height - py - radius)/vy;
            if (t<tx) tx = t;
	}
	return tx;
    }    
}