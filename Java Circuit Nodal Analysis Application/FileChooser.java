// Fig. 17.20: FileChooser.java
// Demonstrating JFileChooser.
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileChooser extends JFrame
{
   public  String filename;
   
   // set up GUI
   public FileChooser()
   {
	 getFileOrDirectory();

   } // end FileChooser constructor

   // allow user to specify file or directory name
   private void getFileOrDirectory()
   {
      // display file dialog, so user can choose file or directory to open
      JFileChooser fileChooser = new JFileChooser();
	  fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
      fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );

      int result = fileChooser.showOpenDialog( this );

      // if user clicked Cancel button on dialog, return
      if ( result == JFileChooser.CANCEL_OPTION )
         System.exit( 1 );

      File name = fileChooser.getSelectedFile(); // get File

      // display error if invalid
      if ( ( name == null ) || ( name.getName().equals( "" ) ) )
      {
         JOptionPane.showMessageDialog( this, "Invalid Name",
            "Invalid Name", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      } // end if
	  
	  
	  filename = name.getPath();

   } // end method getFile

} // end class FileChooser

/*************************************************************************
* (C) Copyright 1992-2012 by Deitel & Associates, Inc. and               *
* Pearson Education, Inc. All Rights Reserved.                           *
*                                                                        *
* DISCLAIMER: The authors and publisher of this book have used their     *
* best efforts in preparing the book. These efforts include the          *
* development, research, and testing of the theories and programs        *
* to determine their effectiveness. The authors and publisher make       *
* no warranty of any kind, expressed or implied, with regard to these    *
* programs or to the documentation contained in these books. The authors *
* and publisher shall not be liable in any event for incidental or       *
* consequential damages in connection with, or arising out of, the       *
* furnishing, performance, or use of these programs.                     *
*************************************************************************/