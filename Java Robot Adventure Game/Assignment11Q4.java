import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import javax.swing.Timer;
import javax.swing.*;



public class Assignment11Q4
{
    static Gameboard panel;
    static Robot robot;
    public void start() {
	panel.startAnimation();
    }

    public void stop() {
	panel.stopAnimation();
    }

    public static void main(String[] args)
   {
	panel = new Gameboard();
	robot = panel.robot;
	EventQueue.invokeLater(new Runnable() {
	   public void run()
	   {
	       JFrame frame = new JFrame("Game 2");
	       frame.add(panel);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(760,540);
	       frame.setVisible(true);
	       panel.startAnimation();
	   }
       });
   }
}
