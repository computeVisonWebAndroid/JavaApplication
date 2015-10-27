import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Resistor
{
	String name;
	int node1;
	int node2;
	int xloc;
	int yloc;
	double orientation_angle;
	double value;


	Resistor(String name,int ND1, int ND2,int xloc,int yloc,double angle,double value)
	{
		this.name = name;
		this.value = value;
		this.node1 = ND1;
		this.node2 = ND2;
		this.xloc = xloc;
		this.yloc = yloc;
		this.orientation_angle = angle;
		//this.value = value;
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
		//this.value = value;
	}
	
	public void setParameter(Resistor r)
	{
		this.name = r.name;
		this.value = r.value;
		this.node1 = r.node1;
		this.node2 = r.node1;
		this.xloc  = r.xloc;
		this.yloc  = r.yloc;
		this.orientation_angle = r.orientation_angle;
	}
	
	public void SetLocation(int x,int y)
	{
		this.xloc=x;
		this.yloc=y;
	}
	public void SetLocation(Point p)
	{
		this.xloc=p.x;
		this.yloc=p.y;
	}
	
	public String toString() // should put public befor String toSting(),otherwise the error will happen
	{
		//return String.format("%g",value);
		return(name + "\t"+node1+ "\t"+node2 + "\t"+xloc+"\t"+yloc+"\t"+orientation_angle+"\t"+value+"ohm");
	}
}