/**
Read class to read Cuicuit list to keep in Resistor list , Icurrent list, or Votage list.
*/

import java.util.*;
import java.io.*;
import java.lang.NumberFormatException;



	
class ReadFile
{

	public int nodeNum,Inumber,Vnumber;
	public int Elementlenth;
	
	public	ArrayList<Resistor> R = new ArrayList<Resistor>();
	public ArrayList<Icurrent> C = new ArrayList<Icurrent>();
	public ArrayList<Voltage> V = new ArrayList<Voltage>();
	public ArrayList<Line1> L = new ArrayList<Line1>();
	
	public String FilePath;


	ReadFile(String filename)
	{
		FilePath = filename;
		GetResisor();
	}
	
	public void GetResisor()
	{
		int i = 10,j = 10,k=10;
				try(BufferedReader br = new BufferedReader(new FileReader(FilePath));)
				{
					String sCurrentLine;
					while((sCurrentLine = br.readLine()) != null)
					{
						String delims = "[ \t]+";
						String[]tokens = sCurrentLine.split(delims);
						System.out.println("tokens.lenth=\t"+ tokens.length);
						Elementlenth = tokens.length;

								if (tokens[0].substring(0,1).equals("R"))
								{
									if (tokens.length == 4 )
									{
												R.add(new Resistor(
														tokens[0],
														Integer.parseInt(tokens[1]),
														Integer.parseInt(tokens[2]),

															100,
															i+=50,
															0,

														Double.parseDouble(tokens[3])
														)
											   );
									}
			
									else
									{
									
										R.add(new Resistor(
															tokens[0],
															Integer.parseInt(tokens[1]),
															Integer.parseInt(tokens[2]),
															Integer.parseInt(tokens[3]),
															Integer.parseInt(tokens[4]),
															Double.parseDouble(tokens[5]),
															Double.parseDouble(tokens[6])
															)
											  );
									}

								}
							
								if(tokens[0].substring(0,1).equals("I"))
								{
									
									if (tokens.length == 4)
									{
											C.add(new Icurrent(
														tokens[0],
														Integer.parseInt(tokens[1]),
														Integer.parseInt(tokens[2]),

															300,
															j+=100,
															0,

														Double.parseDouble(tokens[3])
														)
										  );
									}
					
								else
								{
								
									C.add(new Icurrent(
														tokens[0],
														Integer.parseInt(tokens[1]),
														Integer.parseInt(tokens[2]),
														Integer.parseInt(tokens[3]),
														Integer.parseInt(tokens[4]),
														Double.parseDouble(tokens[5]),
														Double.parseDouble(tokens[6])
														)
										  );
								}

								}
	
								if (tokens[0].substring(0,1).equals("V"))
								{
	

									if (tokens.length == 4)
									{
										V.add(new Voltage(
														tokens[0],
														Integer.parseInt(tokens[1]),
														Integer.parseInt(tokens[2]),

															500,
															k+=100,
															0,

														Double.parseDouble(tokens[3])
														)
										  );
									}

									else
									{
									
										V.add(new Voltage(
															tokens[0],
															Integer.parseInt(tokens[1]),
															Integer.parseInt(tokens[2]),
															Integer.parseInt(tokens[3]),
															Integer.parseInt(tokens[4]),
															Double.parseDouble(tokens[5]),
															Double.parseDouble(tokens[6])
															)
											  );
									
									}
								}

								if (tokens[0].equals("wire"))
								{
									L.add(new Line1(
													Integer.parseInt(tokens[1]),
													Integer.parseInt(tokens[2]),
													Integer.parseInt(tokens[3]),
													Integer.parseInt(tokens[4])							
													)
										
									
									);
								}

								
							}

						
						
						br.close();
						
						Inumber = C.size();
						Vnumber = V.size();
						
						for(Resistor r: R)
						{   
							nodeNum = Math.max(nodeNum,Math.max(r.node1,r.node2));
							System.out.println(r);
						}
						
					System.out.println("Max node num = "+ nodeNum + "\t Inumber= " + Inumber+"\t Vnumber = "+Vnumber);
					
						for(Icurrent c : C)
						{
							System.out.println(c);

						}
						for(Voltage v : V)
						{
							System.out.println(v);

						}

						for (Line1 l : L )
						{
							System.out.println(l);

						}
				 }

			catch (IOException e)
				{
					e.printStackTrace();
				}

	}


		private int getMax(int a, int b)
		{
			if(a>b)
				return a;
			else
				return b;
		}



	public static void main(String[] args) 
	{
		ReadFile RF = new ReadFile("333.txt");
			RF.GetResisor();
	}
}
