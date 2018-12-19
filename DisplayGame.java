import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Displays the Game
 * @author Jakin Ng 
 * @version 12.19.2018
 */
public class DisplayGame 
{	
	static GraphicsConfiguration gc;
	
	public DisplayGame()
	{
		JFrame frame = new JFrame(gc);
		frame.setTitle("Numbers Game");
		frame.setSize(300, 200);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new DisplayGame();
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
