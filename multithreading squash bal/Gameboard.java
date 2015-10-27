import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.Timer;
import javax.swing.*;


public class Gameboard extends JPanel
{
    Timer myTimer;
    int wd, ht;
    // Robot robot;
    ArrayList<Ball> balls = new ArrayList<Ball>();
    ArrayList<Ball> fixedPts = new ArrayList<Ball>();
    ArrayList<WallSeg> segs = new ArrayList<WallSeg>();
    ArrayList<Collision> collisions = new ArrayList<Collision>();
    Scanner sc = new Scanner(System.in);
    boolean done = false;
    double simtime = 0.0;

    
    Gameboard()
    {
			Ball b;

			int wd = 740;
			int ht = 500;
			
			make_area1();

			Color [] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.YELLOW};
			
			String [] names = {"red","green","blue","magneta","yellow"};
			
			for (int k=0; k<5; k++) 
			{
				b = new Ball();
				b.randomize(30.0,wd,ht);
				while (intersect_world(b)) b.randomize(30.0,wd,ht);
				b.color = colors[k];
				b.set_name(names[k]);
				balls.add(b);
			
			}
			


				// robot = new Robot(50,460,24,23,new Color(180,180,255));
				// robot.theta = -45;
	}

    void make_area1()
    {
		Ball pt1, pt2, pt3, pt4, pt5;
		// room boundaries
		pt1 = new Ball(10, 10);
		pt2 = new Ball(730, 10);
		pt3 = new Ball(730, 490);
		pt4 = new Ball(10, 490);
		
		segs.add(new WallSeg(pt1,pt2));
		segs.add(new WallSeg(pt2,pt3));
		segs.add(new WallSeg(pt3,pt4));
		segs.add(new WallSeg(pt4,pt1));
		
		// obstructions
		pt1 = new Ball(540, 300);
		//fixedPts.add(pt1);
		pt2 = new Ball(540, 160);
		//fixedPts.add(pt2);
		pt3 = new Ball(300, 160);
		//fixedPts.add(pt3);
		pt4 = new Ball(300, 390);
		//fixedPts.add(pt4);
		pt5 = new Ball(540, 390);

    }

   public boolean intersect_world(Ball bt)
   {
        for (Ball b: balls) 
		{
			if (b.dist(bt)<50) return true;
		
		}
		for (WallSeg seg: segs) 
		{
			if (seg.dist(bt)<0) return true;
		}
		
		return false;
   }


    public void update1()
    {
		wd = getWidth();
		ht = getHeight();
		double tstep = 0.05;
		double tmin = 1.0;
		// robot.updateVelocity();
		// double t = robot.intersect_window(wd,ht);
			
			// if (t<tmin) tmin = t;
		
			// for (WallSeg ws: segs) 
			// {
				// t = ws.intersect(robot);
				// if (t<tmin) tmin = t;
			// }
			// for (Ball b: fixedPts) 
			// {
				// t = b.intersect(robot);
				// if (t<tmin) tmin = t;
			// }
			
			// if (tmin<=tstep) 
			// {
				// robot.move(tmin);
				// robot.setVelocity(0,0);
			// }
			
			// else robot.move(tstep);
			
			simtime += tstep;
			repaint();
			
			if (done) 
			{
			   stopAnimation();
			   System.out.format("Task completed in %g time units%n",simtime);
			}
    }

    public boolean check_distance()
    {
		double dist;
		boolean flag = false;
		int nballs = balls.size();
			for (int i=0; i<nballs-1; i++) 
			{
				for (int j=i+1; j<nballs; j++) 
				{
					Ball b1 = balls.get(i);
					Ball b2 = balls.get(j);
					dist = b1.dist(b2);
					
						if (dist<-0.01) 
						{
							System.out.println("dist " + dist + " " + b1 + b2);
							double dx = b2.px-b1.px;
							double dy = b2.py-b1.py;
							System.out.println( b1.info());
							System.out.println( b2.info());
							System.out.println("dx " + dx + " dy " + dy);
							System.out.println("sep " + Math.hypot(dx,dy) + " radius " + (b1.radius+b2.radius));
							flag = true;
						}
				}

			}
			return flag;
    }

    void pause()
    {
		stopAnimation();
		repaint();
		sc.nextLine();
		startAnimation();
    }

	private Collision clast = null;

    public void update2()
    {
		wd = getWidth();
		ht = getHeight();
		double tstep, tmore;
		tstep = 1.0;
		tmore = tstep;
			while (tmore>0.0) 
			{
				update_collision_list(tmore);
				if (collisions.size()>0) 
				{
					java.util.List<Collision> list = collisions; 
					Collections.<Collision>sort(list);
					Collision c = collisions.get(0);
					tstep = c.timestep;

					tmore = tmore - tstep;
					boolean flag = check_distance();

					if (flag) 
					{
						if (clast!=null) System.out.println("last collision " + clast);
						for (Collision ct: collisions) System.out.println(ct);
						pause();
					}
					
					clast = c;

					
					for (Ball ball: balls) ball.move(tstep);
					
					c.update_velocity();
				}
				else 
				{
					tstep = tmore;
					tmore = 0.0;
					for (Ball ball: balls) ball.move(tstep);
				}
			}
			
			simtime += tstep;
			repaint();
    }

    public void update_collision_list(double tstep)
    {
		Collision c;
		collisions.clear();
		double twindow,tseg,tball;
			//System.out.println(tstep);
			for (Ball ball: balls)	
			{
					twindow = ball.intersect_window_vertical(wd);
					
					if (twindow>0.0 && twindow<1) collisions.add(new Collision(twindow,ball,1));
					
					twindow = ball.intersect_window_horizontal(ht);
					
					if (twindow>0.0 && twindow<1.0) collisions.add(new Collision(twindow,ball,2));

					for (WallSeg seg: segs) 
					{
						tseg = seg.intersect(ball);
						//System.out.println(tseg);
						if (tseg>0.0 && tseg<1.0) {collisions.add(new Collision(tseg,ball,seg));
							//System.out.println(tseg);
					}
				}
				
				int nballs = balls.size();
				int distep=5;
				for (int i=0; i<nballs-1; i++) 
				{
					for (int j=i+1; j<nballs; j++) 
					{
						Ball b1 = balls.get(i);
						Ball b2 = balls.get(j);
						tball= b1.intersect(b2);
						//System.out.println(tball);
						if (tball>0.0 && tball<1.0) 
						{collisions.add(new Collision(tball,b1,b2));
							//System.out.println(tball);
						}
					}
				}
				//label.setText(wallseg.str);
			}	
}
    

    public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		// draw grid
		g.setColor(Color.RED);
		int n;
		int w = 80;
			for (n=0; n<7; n++) 
			{
				g.drawLine(10,10+n*w,10+9*w,10+n*w);
			}
			for (n=0; n<10; n++) 
			{
				g.drawLine(10+n*w,10,10+n*w,10+6*w);
			}
			// draw walls
			for (WallSeg ws: segs) ws.draw(g);
			for (Ball b: fixedPts) b.draw(g);
			// draw balls
			for (Ball b: balls) b.draw(g);

    }

    
    public void startAnimation()
    {
		if (myTimer == null)
		{
			myTimer = new Timer(100,new TimerHandler() );
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
			update2();
		}
    }
}
