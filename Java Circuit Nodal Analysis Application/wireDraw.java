import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class  wireDraw 
{
	public int w_x1;
	public int w_y1;
	public int w_x2;
	public int w_y2;
	public ArrayList<Point> pstore = new ArrayList<Point>();
	
	
	public wireDraw(){};
	
	public wireDraw(int x1, int y1,int x2, int y2)
	{
		w_x1 = x1;
		w_y1 = y1;
		w_x2 = x2;
		w_y2 = y2;
	}
	
	public wireDraw(ArrayList<Point> pp)
	{
		pstore= pp;
	
	}
	
	
	public void setParameter(Point b, Point e)
	{
		
		 w_x1 = b.x;
		 w_y1 = b.y;
		 w_x2 = e.x;
		 w_y2 = e.y;
	
	}
	
	public String toString() 
	{
		return String.format("%d %d %d %d\n",w_x1,w_y1,w_x2,w_y2);
	}
	
	public void drawLine(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		g2d.fillRect(w_x1,w_y1,5,5);
		g2d.setColor(Color.red);
		g2d.fillRect(w_x2,w_y2,5,5);
		g2d.setColor(Color.black);
		g2d.drawLine(w_x1,w_y1,w_x1,w_y2);
		g2d.drawLine(w_x1,w_y2,w_x2,w_y2);
	}
	
	public void drawSLine(Graphics g)
	{
		g.setXORMode(Color.white);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(w_x1,w_y1,w_x2,w_y2);
	}
	

	public void drawall(Graphics g)
	{
		if(pstore.size()%2 == 0)
		{
			for(int i = 0 ; i< pstore.size();i+=2)
			{
				setParameter(pstore.get(i),pstore.get(i+1));
				 drawLine(g);
			}
		}
	}

	

	public void paintComponent(Graphics g)
	{		
				drawall(g);
	}

}
