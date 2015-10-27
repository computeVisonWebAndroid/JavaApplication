import javax.swing.*;
import java.awt.*;

public class Voltage
{
	String name;
	int node1;
	int node2;
	int xloc;
	int yloc;
	double orientation_angle;
	double value;


	Voltage(String name,int ND1, int ND2,int xloc,int yloc,double angle,double value)
	{
		this.name = name;
		this.value = value;
		this.node1 = ND1;
		this.node2 = ND2;
		this.xloc = xloc;
		this.yloc = yloc;
		this.orientation_angle = angle;

	}

	public void setParameter(String name,int ND1, int ND2,int xloc,int yloc,double angle,double value)
	{
		this.name = name;
		this.value = value;
		this.node1 = ND1;
		this.node2 = ND2;
		this.xloc = xloc;
		this.yloc = yloc;
		this.orientation_angle = angle;
	}

	public void setParameter(Voltage v)
	{
		this.name = v.name;
		this.value = v.value;
		this.node1 = v.node1;
		this.node2 = v.node1;
		this.xloc  = v.xloc;
		this.yloc  = v.yloc;
		this.orientation_angle = v.orientation_angle;
	}

	public void SetLocation(int x,int y)
	{
		this.xloc=x;
		this.yloc=y;
	}
	
	
	public void SetLocation(Point p)
	{
		this.xloc= p.x;
		this.yloc= p.y;
	}
	
	public String toString() // should put public befor String toSting(),otherwise the error will happen
	{
		//return String.format("%g",value);
		return(name + "\t"+node1+ "\t"+node2 + "\t"+value+"V"+"\tx= "+xloc+"\ty= "+yloc+"\tAngle= "+ orientation_angle);
	}
}