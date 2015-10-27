import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class UserInstruction
{

	private	JDialog d;
	private	JTextArea ta;
	private String ss;


	UserInstruction(JFrame frame)
	{
		ss = "Using button (Wire & Resistor) to select wire or resistor model;System default is to draw resistance;\n\nUsing mouse left button to creat resistor\n\nSignal a click mouse left button to pick up begin poin of line,Next a click to pick up a point of end of the line;\n\nDouble clicks right mouse button to cancle the line you have draw before";
		d = new JDialog(frame,"play information",true);
		d.setBounds(200,300,600,300);
		ta = new JTextArea(100,80);
		ta.setEditable(false);
		ta.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));

		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		ta.setEditable(false);
		d.add(ta);

		ta.append(ss);
		d.setVisible(true);
	}

	public void setSting(String s)
	{
		 ss = s;
	
	}

	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
	}
}
