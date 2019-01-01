/**
 * Displays the Game
 * @author Jakin Ng
 * @version 12.23.2018
 */
public class GameDisplayer
{
	// the number of tries per turn
	public final static int TRIES = 2; 
	// the number of points necessary to advance to the next level
	public final static int LEVEL_THRESHOLD = 5; 
	
	//The GameFrame that the GameDisplayer is using
	private GameFrame gameFrame;
	
	public static void main(String[] args)
	{
		//creates a new game and plays the game
		GameDisplayer gd = new GameDisplayer();
		gd.gameFrame.setFrame();
	}
	
	/**
	 * Constructs a new GameDisplayer
	 * Creates a new GameFrame
	 */
	public GameDisplayer()
	{
		gameFrame = new GameFrame();
	}
}