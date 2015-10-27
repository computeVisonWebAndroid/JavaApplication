import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class paintAll extends JPanel
{	
	LineDraw ld;
	ResistorDraw rd;
	VoltageDraw vd;
	IcurrentDraw id;
	Color state;
	
	paintAll(){};
	
	/**
	new add
	*/
	void setPaintList(ArrayList<Resistor> r,ArrayList<Voltage> v,ArrayList<Icurrent> i,ArrayList<Line1> l)
	{
		if(!r.isEmpty())
		{		
		  rd = new ResistorDraw(r);
		}
		
		if(!v.isEmpty())
		{		
		  vd = new VoltageDraw(v);
		}
		
		
		if(!i.isEmpty())
		{		
		  id = new IcurrentDraw(i);
		}

		if(!l.isEmpty())
		{
			ld = new LineDraw(l);
		}
		
	}
	
	void setColor(Color c)
	{
		 state= c;
	}	
	


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		 if (ld != null)
		 {
			///System.out.println(wd);
			ld.drawall(g);
		 }
 
		if(rd != null)
		{	
			g.setColor(state);
			rd.drawall(g);		

		}
		
		if(vd != null)
		{

			g.setColor(state);
			vd.drawall(g);		
		
		}
		
		if(id != null)
		{
			g.setColor(state);
			id.drawall(g);		

		
		}
		
	}

	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}
}


