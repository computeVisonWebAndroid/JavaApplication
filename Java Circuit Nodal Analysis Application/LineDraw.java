import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class LineDraw extends JPanel
{
	
	public	ArrayList<Line1> Lstore = new ArrayList<Line1>();
	Point b,e;
	int xb,yb,xe,ye;


	public LineDraw(ArrayList<Line1> l)
	{
		Lstore = l;
	
	}

	public LineDraw(Point p1, Point p2)
	{
		b = p1;
		e = p2;
	}


	public LineDraw(int x1, int y1, int x2, int y2)
	{
		b.x = x1;
		b.y = y1;
		e.x = x2;
		e.y = y2;
	
	}

	public void setParameter(Point p1, Point p2)
	{
		
		b = p1;
		e = p2;
	
	}


	public void drawLine(Graphics g)
	{
			Graphics2D g2d = (Graphics2D) g;

			g2d.setColor(Color.blue);
			g2d.fillRect(b.x-2,b.y-2,4,4);

			g2d.setColor(Color.red);
			g2d.fillRect(e.x-2,e.y-2,4,4);
				
			g2d.setColor(Color.black);

		    g.drawLine(b.x,b.y,b.x,e.y);
			g.drawLine(b.x,e.y,e.x,e.y);
	
	}

	public void drawall(Graphics g)
	{
		
			for (Line1 ll : Lstore)
			{
				setParameter(ll.begin,ll.end);
				drawLine(g);
			}

	
	}


	public void paintComponent(Graphics g)
	{	
		drawall(g);
		
	}


	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		Line1 l = new Line1();
		l.setLineb(new Point(50,200));
		l.setLinee(new Point(70,150));
		ArrayList<Line1> ll = new ArrayList<Line1>();
		ll.add(l);

		LineDraw lll = new LineDraw(ll);
		frame.add(lll);
	
		frame.setVisible(true);


	}
}
