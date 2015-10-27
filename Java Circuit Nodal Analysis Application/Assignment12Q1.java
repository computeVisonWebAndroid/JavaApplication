
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.NumberFormatException;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.filechooser.*;
import java.lang.Math;
import java.io.*;


public class Assignment12Q1
{
	public static void main(String[] args) 
	{
		mainFrame mf = new mainFrame();
	}
}



class mainFrame 
{
	//GUI part
	private JFrame win;
	private JMenuBar mbar;
	private TextArea ta;
	private JMenu fileMenu,EditMenu,HelpMenu,subAddMenu,Caculation;
	private JMenuItem addReItem,addCuItem,addVoItem,addWiItem,
					  EdReItem,EdCuItem,EdVoItem,
					  openItem,saveItem,removeAllItem,caculationItem;
	public Dialog d;
	// File Part
	public  String filename;
	public 	paintAll paintPanel;
	 
	private File file;

	// register Par
	public ArrayList<Resistor> Rlist = new ArrayList<Resistor>();
	public ArrayList<Voltage>  Vlist = new ArrayList<Voltage>();
	public ArrayList<Icurrent> Ilist = new ArrayList<Icurrent>();
	public ArrayList<Point> Plist = new ArrayList<Point>();
	public ArrayList<Line1> Llist = new ArrayList<Line1>();


	public Resistor Rcurrent;
	public Voltage  Vcurrent;
	public Icurrent Icurrent;
	
	public Line1 Lcurrent,Lcurrent1;
	public Line1 Ltemp = new Line1();
	private boolean pj = true;
	private Point[] pl = new Point[2];



	public int x = 0;
	public int y = 0;


	boolean addRe; 
	boolean addCu; 
	boolean addVo; 
	boolean addWi; 
	
	boolean EdRe; 
	boolean EdCu; 
	boolean EdVo;
	 

	

    mainFrame() 
	{
		initState();
		init();
	}

	public void init()
	{
		win = new JFrame("Circuit Draw");
		win.setBounds(300,100,600,600);

		paintPanel = new paintAll();

		mbar = new JMenuBar();
		fileMenu = new JMenu("File (F)");
		EditMenu = new JMenu("Edit (E)");
		HelpMenu = new JMenu("Help (W)");
			
		subAddMenu =  new JMenu("Add (A)");
		Caculation = new JMenu("CaculationPanel (CP)");
				
				addReItem = new JMenuItem("Resistor");
				addCuItem = new JMenuItem("Cuttent");
				addVoItem = new JMenuItem("Voltage");
				addWiItem = new JMenuItem("Wires");
				
				openItem = new JMenuItem("Open (O)");
				saveItem = new JMenuItem("Save As (S)");
				removeAllItem = new JMenuItem("RemoveAll");

				EdReItem = new JMenuItem("Resistor");
				EdCuItem = new JMenuItem("Cuttent");
				EdVoItem = new JMenuItem("Voltage");

				caculationItem = new JMenuItem("Result");


		mbar.add(fileMenu);
		mbar.add(subAddMenu);
		mbar.add(EditMenu);
		mbar.add(Caculation);
		mbar.add(HelpMenu);

			fileMenu.add(openItem);
			fileMenu.add(saveItem);
			fileMenu.add(removeAllItem);
				
				subAddMenu.add(addReItem);
				subAddMenu.add(addCuItem);
				subAddMenu.add(addVoItem);
				subAddMenu.add(addWiItem);


			EditMenu.add(EdReItem);
			EditMenu.add(EdCuItem);
			EditMenu.add(EdVoItem);

			Caculation.add(caculationItem);

			
		win.add(mbar,BorderLayout.NORTH);
		win.add(paintPanel);

		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myEvent();
		win.setVisible(true);
		

		if(filename != null)
		{
		}

	}

	private void initState()
	{		
		 addRe = false; 
		 addCu = false; 
		 addVo = false; 
		 addWi = false; 
		
		 EdRe = false; 
		 EdCu = false; 
		 EdVo = false;

	}

   /**
	for test print
   */ 
	private void print(String ss)
	{
		System.out.println(ss);
	}

	/**
	for iner search funciton
	*/

	public Resistor findR(Point p)
	{
		double x = p.getX();
		double y = p.getY();
		for(Resistor r : Rlist)
		{
			if(x >= (r.xloc-30) && x<=(r.xloc+30) && y >= (r.yloc-7) && y <= (r.yloc + 7)) 
			{
				return r;
			}
		}
		return null;
	}

	public Voltage findV(Point p)
	{
		double x = p.getX();
		double y = p.getY();
		for(Voltage v: Vlist)
		{
			if(x >= (v.xloc-15) && x <=(v.xloc+15) && y >= (v.yloc -15) && y <= (v.yloc+15)) 
			{
				return v;
			}
		}
		return null;
	}

	public Icurrent findI(Point p)
	{
		double x = p.getX();
		double y = p.getY();
		for(Icurrent ic : Ilist)
		{
			if(x >= (ic.xloc-15) && x <=(ic.xloc+15) && y >= (ic.yloc -15) && y <= (ic.yloc+15)) 
			{
				return ic;
			}
		}
		return null;
	}

	public Line1 findL(Point p)
	{
		double x = p.getX();
		double y = p.getY();
		for (Line1 l1 : Llist)
		{
			if ((x>= (l1.begin.x -2) && x <=(l1.begin.x+2) && y >= (l1.end.y) && y <= l1.begin.y) || ( x >= l1.begin.x && x <= l1.end.x && y >= (l1.end.y-2) && y <= (l1.end.y+2)))
			{
				return l1;
			}
		}
	
		return null;
	
	}


	private void clear()
	{
				Rlist.clear();
				Vlist.clear();
				Ilist.clear();
				Llist.clear();
	}


	private void myEvent()
	{

		/**
		add the button listner;
		*/
		openItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				
				print("openItem");
				FileChooser application = new FileChooser();
				ReadFile RF = new ReadFile(application.filename);
				application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				clear();

				Rlist = RF.R;
				Vlist = RF.V;
				Ilist = RF.C;
				Llist = RF.L;
				
				paintPanel.setPaintList(Rlist,Vlist,Ilist,Llist);
				paintPanel.repaint();


			}

		});


		addReItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				
				print("addReItem");
				addRe = true;

				Rlist.add(new Resistor("R",1,2,0,0,0,100));

			
			}

		});
		
		
		addCuItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				
				print("addCuItem");

				addCu = true;
				Ilist.add(new Icurrent("I",1,2,0,0,0,0.1));



			
			}

		});


		addVoItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				
				print("addVoItem");
				
				
				addVo = true;
				Vlist.add(new Voltage("V",1,2,0,0,0,1));		
			}

		});



		addWiItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				print("addWiItem");
				addWi = true;				
				paintPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));


			}
		
		});

	
		saveItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				print("saveItem");

				FileWirte FW = new FileWirte();
				FW.setParameter(Rlist,Vlist,Ilist,Llist);
				FW.WriteToFile();


			}	
		});

		removeAllItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				print("removeAllItem");
				Rlist.clear();
				Vlist.clear();
				Ilist.clear();
				Llist.clear();
				paintPanel.repaint();

			}	
		});

		EdReItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				
				print("EdReItem");

				EditR ER = new EditR(win,Rcurrent);
				paintPanel.repaint(); 			
			}

		});


		EdCuItem.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				
				print("EdCuItem");
				EditI EI = new EditI(win,Icurrent);
				paintPanel.repaint();

			
			}

		});



		EdVoItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			
				print("EdVoItem");
				EditV EV = new EditV(win,Vcurrent);
				paintPanel.repaint(); 
			}
		
		});


		caculationItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			
				print("caculationItem");
				if (!Rlist.isEmpty() || !Vlist.isEmpty() || !Ilist.isEmpty())
				{
					CaculationFrame mf = new CaculationFrame(Rlist,Ilist,Vlist);
				}

			}
		
		});		
	



	/**
		add the mouse Listen
	*/
		
		paintPanel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent event)
			{
				int x = event.getX();
			    int y = event.getY();
/**
for add component
*/

				  Rcurrent = findR(event.getPoint());
				  Vcurrent = findV(event.getPoint());
				  Icurrent = findI(event.getPoint());


				if (addRe)
				{
						
						 paintPanel.setColor(Color.black);
						 initState();


				}
				if(addCu)
				{
					 paintPanel.setColor(Color.black);
					 initState();
				
				}

				if (addVo)
				{
					 paintPanel.setColor(Color.black);
					 initState();
				}

				if (addWi && event.getButton() == event.BUTTON1)
				{

						Lcurrent = 	new Line1(event.getPoint(),event.getPoint());				;

						Llist.add(Lcurrent);
						paintPanel.setPaintList(Rlist,Vlist,Ilist,Llist);
				}

/**
for delet component
*/
				if (event.getButton() == event.BUTTON1 && event.getClickCount() >= 2)
				{

					
				  Lcurrent1 = findL(event.getPoint());

					
				  if (Rcurrent!= null)
				  {
						Rlist.remove(Rcurrent);

				  }
				  if (Icurrent != null)
				  {
					  Ilist.remove(Icurrent);
				  }

				  if (Vcurrent != null)
				  {
					  Vlist.remove(Vcurrent);
				  }

				  if (Lcurrent1 != null)
				  {
					  Llist.remove(Lcurrent1);
				  }

				}

				paintPanel.repaint();

				
			}


			public void mouseReleased(MouseEvent event)
			{
				paintPanel.setColor(Color.black);
				paintPanel.repaint(); 
				// new add;
				if (addWi)
				{
				   initState();
					paintPanel.setCursor(Cursor.getDefaultCursor());

				}


			}
		
		
		});				

		paintPanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent event)
			{
				int x = event.getX();
			    int y = event.getY();

				if(addRe)
				{	

					Rlist.get(Rlist.size()-1).SetLocation(event.getPoint());
				}
				
				if(addCu)
				{
					Ilist.get(Ilist.size()-1).SetLocation(event.getPoint());
				
				}
				
				if (addVo)
				{
					Vlist.get(Vlist.size()-1).SetLocation(event.getPoint());
				}
				


				if (addRe || addCu || addVo || addWi)
				{
					paintPanel.setColor(Color.blue);
					paintPanel.setPaintList(Rlist,Vlist,Ilist,Llist);
					paintPanel.repaint(); 
				}
				
			}



			public void mouseDragged(MouseEvent event)
			{
				System.out.println(event.getPoint());

				  if (Rcurrent != null)
				  {		
						Rlist.get(Rlist.indexOf(Rcurrent)).SetLocation(event.getX(),event.getY());

				  }
				  if (Icurrent != null)
				  {
						Ilist.get(Ilist.indexOf(Icurrent)).SetLocation(event.getX(),event.getY());
				  }

				  if (Vcurrent != null)
				  {
						Vlist.get(Vlist.indexOf(Vcurrent)).SetLocation(event.getX(),event.getY());
				  }

				 if (addWi)
				{
					Llist.get(Llist.indexOf(Lcurrent)).setLinee(event.getPoint());
					paintPanel.setPaintList(Rlist,Vlist,Ilist,Llist);
				}
				

				  paintPanel.setColor(Color.blue);
				  paintPanel.repaint(); 

			}
				
		
		});				




	}



	public static void main(String[] args) 
	{
		mainFrame mf = new mainFrame();
	}
} 