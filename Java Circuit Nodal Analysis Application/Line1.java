import javax.swing.*;
import java.awt.*;


class Line1 
{
	public Point begin,end;
	public int x_b,y_b,x_e,y_e; 

    Line1(){};

	Line1(Point b, Point e)
	{
		begin = b;
		end = e;
		x_b = b.x;
		y_b = b.y;
		x_e = e.x;
		y_e = e.y;
	}

	Line1(int x1,int y1,int x2, int y2)
	{
		begin = new Point(x1,y1);
		end = new Point(x2,y2);
	}
	
	public void setLine(Point b, Point e)
	{
		begin = b;
		end = e;
	}

	public void setLineb(Point b)
	{
		begin = b;
	}

	public void setLinee(Point e)
	{
		end = e;
	}
	
	public void clear()
	{
		begin = null;
		end = null;
	}

	public void setLine(int x1,int y1,int x2, int y2)
	{
		x_b = x1;
		y_b = y1;
		x_e = x2;
		y_e = y2;
	}

	
	public String toString() // should put public befor String toSting(),otherwise the error will happen
	{
		return("begin= "+begin+"\nend= "+end+"\nx_b= "+x_b+"\ty_b= "+y_b+"\tx_e= "+x_e+"\ty_e="+y_e);
	}

	public static void main(String[] args) 
	{

//		Line1 l = new Line1();
//		l.setLineb(new Point(3,4));
//		l.setLinee(new Point(5,6));
		Line1 l = new Line1(1,2,3,4);
		System.out.println(l);


	}
}
