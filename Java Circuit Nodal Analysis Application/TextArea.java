import java.awt.*;
import javax.swing.*;
import Jama.*; 

public class TextArea  extends JPanel
{
   public JTextArea area = new JTextArea(20, 80);
   public String text;
        
    public TextArea() 
	{
        //... Set textarea's scrolling, and border.
		area.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
        JScrollPane scrollingArea = new JScrollPane(area);
		area.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(true);
	
        
        //... Get the content pane, set layout, add to center
        setLayout(new BorderLayout());
        add(scrollingArea, BorderLayout.CENTER);
        
    }
	
	public String getText()
	{
		return area.getText();
	
	}
	public void clear()
	{
		area.setText("");
	
	}
    void  print(String str)
	{
		area.append(str);
	}

//	void  print()

   public void print(Matrix M, String fmt) 
   {
     //String fmt = "%8.4f";
     int nrows = M.getRowDimension();
     int ncols = M.getColumnDimension();
     print("\n");
     for (int i = 0;  i<nrows; i++) 
	 {
		for (int j=0; j<ncols; j++) 
		{
          print(String.format(fmt,M.get(i,j)));
        }
        print("\n");
     }
   }

 
    
    public static void main(String[] args) 
	{
        JFrame win = new JFrame();
		TextArea content = new TextArea();
        win.setContentPane(content);
        win.setTitle("TextArea Demo");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.pack();
        win.setVisible(true);
    }
}