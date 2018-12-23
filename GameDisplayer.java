import java.awt.*;
import java.text.*;
import java.util.concurrent.Semaphore;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.concurrent.TimeUnit;

public class GameDisplayer
{
	// the number of tries per turn
	public final static int TRIES = 2; 
	// the number of points necessary to advance to the next level
	public final static int LEVEL_THRESHOLD = 5; 
	
	private GameFrame gameFrame;
	
	public static void main(String[] args)
	{
		GameDisplayer gd = new GameDisplayer();
		while (//gd.gameFrame.getGame().getPlayer().getTries() > 1 && 
				gd.gameFrame.getGame().getPlayer().getLevel() <= Game.MAX_LEVEL)
		{
			gd.gameFrame.playGame();
//			if (gd.gameFrame.getGame().getPlayer().getTries() <= 1)
//			{
//				gd.gameFrame.getGame().getPlayer().setTries(Game.TRIES);
//			}
		}
		/*GameDisplayer gd = new GameDisplayer();
		while (gd.gameFrame.getGame().getPlayer().getLevel() <= 3)
		{
			while (gd.gameFrame.isAnswered() == true)
			{	
				//gd.gameFrame.checkQuestion();
				gd.gameFrame.getGame().getPlayer().setTries(TRIES);
			}
		}
		gd.gameFrame.displayText("You win!");*/
	}
	
	public GameDisplayer()
	{
		gameFrame = new GameFrame();
	}
}
