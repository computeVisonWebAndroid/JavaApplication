import java.awt.*;
import javax.swing.*;
import java.util.*;

class VoltageDraw extends JPanel
{

	public int vcenter_x;
	public int vcenter_y;
	public double angle = 0;
	public int width = 20;
	public int hight= 20;
	public String S = "V";
	private	ArrayList<Voltage> Vstore = new ArrayList<Voltage>();
	int x,y;

	VoltageDraw(ArrayList<Voltage> v)
	{
		Vstore = v;
	}

	VoltageDraw(Point p)
	{
		vcenter_x = p.x;
		vcenter_y = p.y;	
	}
	VoltageDraw(int xx, int yy)
	{
		vcenter_x = xx;
		vcenter_y = yy;	
	}


	public void setParameter(int x, int y, double angle,String s)
	{
		vcenter_x = x;
		vcenter_y = y;	
		this.angle = angle;
		this.S =s;
	}


	public void drawVOL(Graphics g)
	{
		Graphics2D g2d = ( Graphics2D ) g;

		Font orignal = g.getFont();
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g2d.drawString(S,vcenter_x-3*width,vcenter_y);
		g.setFont(orignal);

		g2d.rotate(Math.toRadians(angle),vcenter_x,vcenter_y); // rotate coordinate system
		
		int x_topl = vcenter_x -width;
		int y_topl = vcenter_y -hight;
	
/**
for test the position
*/		

//		g.setColor(Color.red);
//		g.fillOval(vcenter_x,vcenter_y,5,5);
//
//		g.setColor(Color.green);
//		g.fillOval(x_topl,y_topl,5,5);
//		g2d.drawRect(x_topl,y_topl,40,40);


//		g.setColor(Color.BLACK);

      	g.drawArc(x_topl,y_topl,40,40,0,360);
		g.drawLine(x_topl+20,y_topl,x_topl+20,y_topl-15);
		g.drawLine(x_topl+20,y_topl+40,x_topl+20,y_topl+55);
		
		g.drawLine(x_topl+15,y_topl+10,x_topl+25,y_topl+10);
		g.drawLine(x_topl+20,y_topl+5,x_topl+20,y_topl+15);
		g.drawLine(x_topl+15,y_topl+30,x_topl+25,y_topl+30);

		g2d.rotate( Math.toRadians(-angle),vcenter_x,vcenter_y); // rotate coordinate system


	}

	public void drawall(Graphics g)
	{
		for(Voltage vs : Vstore)
		{
			setParameter(vs.xloc,vs.yloc,vs.orientation_angle,vs.name);
			drawVOL(g); 
		}
		
	}


   public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
		drawall(g);
   
	}


	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);

		VoltageDraw vd = new VoltageDraw(100,100);
		frame.add(vd);
	}
}
