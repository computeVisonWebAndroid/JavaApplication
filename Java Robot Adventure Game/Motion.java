import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.Timer;
import javax.swing.*;


class TestPanel extends JPanel
{
    Timer myTimer;
    int wait, wd, ht;
    Robot  robot;
    double simtime = 0.0;
    int stage = 0;

    
    TestPanel()
    {
	robot = new Robot(210,210,6,-6,Color.BLUE);
    }


    public void update()
    {
	wd = getWidth();
	ht = getHeight();
	double tstep, tmore;
	tmore = 1.0;
	/*
	while (tmore>0.0) {
	tmore = 0.0;
	}
	*/
	robot.move(0.05);
	simtime = simtime + 0.05;
	if (simtime>12*Math.PI && stage==2) {
		stage = 0;
		simtime = 0;
		robot.setPose(210,210,0);
		robot.setVelocity(6,-6);
	}
	else if (simtime>8*Math.PI && stage==1) {
		stage = 2;
		robot.setPose(210,210,0);
		robot.setVelocity(47,53);
	}
	else if (simtime>4*Math.PI && stage==0) {
		stage = 1;
		robot.setPose(210,204,0);
		robot.setVelocity(0,6);
	}

	repaint();
    }

/*
   Collision intersect_window(Ball ball, double tstep)
    {
	double [] t = new double[4];
	double tmax = 1000;
	if (ball.vx<0) {
	    t[0] = (ball.px-ball.radius)/(-ball.vx);
	}
	else t[0] = tmax;
	if (ball.vx>0) {
	    t[1] = (width - ball.px - ball.radius)/(ball.vx);
	}
	else t[1] = tmax;
	if (ball.vy<0) {
	    t[2] = (ball.py-ball.radius)/(-ball.vy);
	}
	else t[2] =  tmax;
	if (ball.vy>0) {
	    t[3] = (height - ball.py - ball.radius)/ball.vy;
	}
	else t[3] = tmax;
	int idx = 0;
	double tx = t[0];
	for (int i=1; i<4; i++) {
	    if (t[i]>tx) continue;
	    tx = t[i];
	    idx = i;
	}
	if (tx>tstep) {
	    return null; // does not intersect
	}
	return new Collision(tx,ball,idx);
    }

    Collision intersect_balls(Ball ball1, Ball ball2, double tstep)
    {
	// relative velocity
	double rx = ball2.vx - ball1.vx;
	double ry = ball2.vy - ball1.vy;
	// relative position
	double px = ball2.px - ball1.px;
	double py = ball2.py - ball1.py;
	// test radius
	double r = ball1.radius + ball2.radius - 1;
	double C =  px*px + py*py - r*r;
	if (C<0) return null; // already intersecting
	double B = rx*px+ry*py;
	double A = rx*rx+ry*ry;
	if (A<=0.0) return null; // no relative velocity
	// quadratic At^2 + 2Bt + C = 0
	double radical = B*B-A*C;
	if (radical<=0.0) return null; // no intersection (balls miss)
	double R = Math.sqrt(radical);
	double t;
	if (B>0) t = (-B + R)/A;
	else t = -(B+R)/A;
	if (t<=0.0 || t>tstep) return null; // no intersection within time limit
	return new Collision(t,ball1,ball2);
    }
*/
    

    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	robot.draw(g);
	g.setColor(Color.RED);
	int n;
	for (n=0; n<9; n++) {
	    g.drawLine(10,10+n*50,410,10+n*50);
	    g.drawLine(10+n*50,10,10+n*50,410);
        }
    }

    
    public void startAnimation()
    {
	if (myTimer == null)
	{
	    myTimer = new Timer(50,new TimerHandler() );
	    myTimer.start();
	}
	else if (!myTimer.isRunning()) myTimer.restart();
    }

    public void stopAnimation()
    {
	myTimer.stop();
    }

    private class TimerHandler implements ActionListener
    {
	public void actionPerformed(ActionEvent actionevent)
	{
	    update();
	}
    }
}

public class motion extends JApplet
{
    TestPanel panel;
    public void init()
    {
	try {
		EventQueue.invokeAndWait(new Runnable() {
	    		public void run()
	    		{
				panel = new TestPanel();
				add(panel);
	    		}
		});
	}
	catch (Exception e) {
	}
    }

    public void start() {
	panel.startAnimation();
    }

    public void stop() {
	panel.stopAnimation();
    }

    public static void main(String[] args)
   {
	EventQueue.invokeLater(new Runnable() {
	   public void run()
	   {
	       TestPanel panel = new TestPanel();
	       JFrame frame = new JFrame("Robot Motion");
	       frame.add(panel);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(440,460);
	       frame.setVisible(true);
	       panel.startAnimation();
	   }
       });
   }
}
