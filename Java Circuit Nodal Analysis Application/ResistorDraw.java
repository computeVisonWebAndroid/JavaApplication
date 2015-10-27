import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import java.util.Random;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.NumberFormatException;
import java.io.IOException;
import java.awt.geom.Point2D;


public class ResistorDraw extends JPanel
{
	public int R_x;
	public int R_y;
	public double angle = 0;
	public int width = 50;
	public int hight = 15;
	public int[] L = new int[5];
	public String S = "R";
	private	ArrayList<Resistor> Rstore = new ArrayList<Resistor>();
	int x,y;
	
	public ResistorDraw(ArrayList<Resistor> r)
	{
		Rstore = r;
	
	}

	public ResistorDraw(Point p)
	{
		R_x = p.x-width/2;
		R_y = p.y-hight/2;
		L[0] = p.x - width;
		L[1] = R_x;
		L[2] = p.x + width; 
		L[3] = p.x + width/2;
		L[4] = p.y;
		
		this.x = p.x;
		this.y = p.y;

	}


	public ResistorDraw(int x, int y, double angle,String s)
	{
		 setParameter(x,y,angle,s);
	
	}

	private void setParameter(int x, int y, double angle,String s)
	{	
		// x -= 6;
		// y -= 64;//chang 64 from 68
		R_x = x-width/2;
		R_y = y-hight/2;
		L[0] = x - width;
		L[1] = R_x;
		L[2] = x + width; 
		L[3] = x + width/2;
		L[4] = y; // for line y coordinate
		this.angle = angle;
		this.S =s;
		
		this.x = x;
		this.y = y;

	}

	public void drawResistor(Graphics g)
	{	
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.rotate( Math.toRadians(angle),x,y); // rotate coordinate system
		g2d.drawString(S,R_x+15,R_y);
		g.fillOval(x,y,2,2);
////		System.out.println("g-x="+ x +"g-y" + y);
		g2d.drawRect(R_x,R_y,width,hight);
		g2d.drawLine(L[0],L[4],L[1],L[4]);
		g2d.drawLine(L[2],L[4],L[3],L[4]);
		g2d.rotate( Math.toRadians(-angle),x,y); // rotate coordinate system

		
	}

	public void DrawAresisotr(int x, int y, double theta, String name,Graphics g)
	{
		
			setParameter(x,y,theta,name);
			drawResistor(g);
	}
	
	
	public void paintComponent(Graphics g)
	{	
		drawall(g);
//		drawResistor(g);
		
	}
	
	public void drawall(Graphics g)
	{
		for(Resistor RS : Rstore)
		{
			setParameter(RS.xloc,RS.yloc,RS.orientation_angle,RS.name);
			drawResistor(g); 
		}
	}
	

	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);

		ResistorDraw rd = new ResistorDraw(100,100,0,"R");
		frame.add(rd);
	}
	
}
