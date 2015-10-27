import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.Timer;
import javax.swing.*;


import javax.sound.sampled.*;
import java.io.*;



public class Gameboard extends JPanel implements KeyListener
{
    Timer myTimer;
    int wait, wd, ht;
	int velL = 20, velR = 24;
	int VtempL = 2, VtempR = 2;
	int acceleration = 80;


    Robot  robot;
     ArrayList<Ball> pts = new ArrayList<Ball>();
    ArrayList<WallSeg> segs = new ArrayList<WallSeg>();
    Coin c;
    boolean done = false;
    double simtime = 0.0;

    
    Gameboard()
    {
	robot = new Robot(690,465,24,23,new Color(180,180,255));
	robot.theta = -45;


	Ball pt1 = new Ball(10, 10);
	pts.add(pt1);
	Ball pt2 = new Ball(730, 10);
	pts.add(pt2);
	Ball pt3 = new Ball(730, 490);
	pts.add(pt3);
	Ball pt4 = new Ball(10, 490);
	pts.add(pt4);


	c = new Coin(690, 460);


	segs.add(new WallSeg(pt1,pt2));
	segs.add(new WallSeg(pt2,pt3));
	segs.add(new WallSeg(pt3,pt4));
	segs.add(new WallSeg(pt4,pt1));
	
	
	
	pt1 = new Ball(730, 390);
	pt2 = new Ball(620, 390);
	segs.add(new WallSeg(pt1,pt2));


	pt1 = new Ball(550, 390);
	pt2 = new Ball(550, 490);
	segs.add(new WallSeg(pt1,pt2));
	pt1 = new Ball(400, 420);
	pt2 = new Ball(640, 200);
	segs.add(new WallSeg(pt1,pt2));
	pt1 = new Ball(360, 300);
	pt2 = new Ball(220, 100);
	segs.add(new WallSeg(pt1,pt2));
	pt1 = new Ball(440, 240);
	pt2 = new Ball(350, 100);
	segs.add(new WallSeg(pt1,pt2));
	pt1 = new Ball(200, 490);
	pt2 = new Ball(200, 200);
	segs.add(new WallSeg(pt1,pt2));
	pt1 = new Ball(200, 200);
	pt2 = new Ball(100, 200);
	segs.add(new WallSeg(pt1,pt2));
	pt1 = new Ball(540, 10);
	pt2 = new Ball(540, 160);
	segs.add(new WallSeg(pt1,pt2));


	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);


    }


	public void keyPressed(KeyEvent e)
	{
		int KeyCode = e.getKeyCode();
		if (KeyCode == e.VK_UP)		
		{
			velL = acceleration;
			velR = acceleration;

			startAnimation();
		}
		if (KeyCode == e.VK_DOWN)
		{
			
			velL = -acceleration;
			velR = -acceleration;

			startAnimation();

		}

		if (KeyCode == e.VK_RIGHT)
		{
			if (velL <0 || velR <0)
			{
				velL = -velL;
				velR = -velR;
			}
			velL +=1;
			startAnimation();
		}

		if (KeyCode == e.VK_LEFT)
		{
			if (velR <0 || velL <0)
			{
				velL = -velL;
				velR = -velR;
			}

			velR += 1;
			startAnimation();
		}
	}

	public void keyTyped(KeyEvent e)
	{
	
	}
    public void keyReleased(KeyEvent e)
	{
	
			stopAnimation();
			velL = VtempL;
			velR = VtempR;

	}



    public void update()
    {
	wd = getWidth();
	ht = getHeight();
	double tstep = 0.05;
	double tmin = 1.0;
	robot.setVelocity(velR,velL);
	robot.updateVelocity();

	double t = robot.intersect_window(wd,ht);
	if (t<tmin) tmin = t;
	for (WallSeg ws: segs) {
	    t = ws.intersect(robot);
	    if (t<tmin) tmin = t;
 	}
	for (Ball b: pts) {
	    t = b.intersect(robot);
	    if (t<tmin) tmin = t;
        }
	t = c.intersect(robot);
	if (t<tmin && t<=tstep) {
		done = true;
		tmin = t;
	}
	if (tmin<=tstep) {
                robot.move(tmin);
		robot.setVelocity(0,0);
	}
	else robot.move(tstep);
	simtime += tstep;

	repaint();
	if (done) {

	   playsound("Congratulation.wav");

			
	   stopAnimation();
	   String str = String.format("Congratulate you have completed in %g time units",simtime);
	   JOptionPane.showMessageDialog(this, str);
	   System.out.println(str);
//在这加上声音


        }
    }


	public void playsound(String filename)
	{
		try{
		AudioInputStream aut = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
		Clip cp = AudioSystem.getClip();
		cp.open(aut);
		cp.start();

		}
	
	catch(Exception ex)
		{
			System.out.println("ssss");
//			ex.printsTackTrace();
		}
	}





/*

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
	int w = 80;
	for (n=0; n<7; n++) {
	    g.drawLine(10,10+n*w,10+9*w,10+n*w);
	}
	for (n=0; n<10; n++) {
	    g.drawLine(10+n*w,10,10+n*w,10+6*w);
        }
	for (Ball b: pts) b.draw(g);
	for (WallSeg ws: segs) ws.draw(g);
	c.draw(g);
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
