import java.awt.*;
import java.awt.geom.*;

public class WallSeg
{
   Ball pt0, pt1;
   Color color;
   String str;
   double kx, ky, scl;
   WallSeg() {
      pt0 = new Ball(250, 200);
      pt1 = new Ball(150, 50);
      color = Color.black;
      setup();
   }
   WallSeg(Ball pt1, Ball pt2) {
     this.pt0 = pt1;
     this.pt1 = pt2;
     color = Color.BLACK;
     setup();
   }
   public void setup()
   {
      kx = pt1.px-pt0.px;
      ky = pt1.py-pt0.py;
      scl = Math.hypot(kx,ky);
      kx = kx/scl;
      ky = ky/scl;
   }

   public void draw(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
 	BasicStroke s =
        new BasicStroke(4.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
	Stroke old_stroke = g2.getStroke();
	g2.setStroke(s);
	g2.setColor(color);
	g2.draw(new Line2D.Double(pt0.px, pt0.py, pt1.px, pt1.py));
	g2.setStroke(old_stroke);
	g2.setColor(Color.BLACK);
   }

   public double intersect(Ball ball) {
	double px = ball.px - pt0.px;
	double py = ball.py - pt0.py;
	double dist, radius, q;
	radius = ball.radius;
	double tx = 1000.0;
	// distance to line
	dist = (kx*py - ky*px);
	q = (px*kx+py*ky)/scl;
	str = String.format("dist %6.2f q %4.2f",dist,q);
	if (q>0 && q<1) {
 	   if (Math.abs(dist)<radius-0.5) {
		System.out.format("seg violation, dist = %.2f%n",dist);
		System.out.println(ball);
		System.exit(-1);
           }
        }
	// calculate time to intersection
	double t;
	double vperp = kx*ball.vy - ky*ball.vx;
	if (dist<0) t = -(radius+dist)/vperp;
	else t = (radius-dist)/vperp;
	str += String.format("  t %6.2f",t);
	// calculate q at intersection
	double qx, qy;
	qx = px + ball.vx*t;
	qy = py + ball.vy*t;
	q = (qx*kx+qy*ky)/scl;
        if (q<0 || q>1) return tx;
	if (t<1e-5) return tx;
	else return t;
   }

}
	