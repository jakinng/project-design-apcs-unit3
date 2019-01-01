/**
 * Simulates a Player of the Game
 * @author Taerim Eom and Jakin Ng
 * @version 12.08.2018
 */
public class Player 
{
	private int level;
 	private int tries;
	private int points;
	
	/**
	 * Creates a Player
	 * @param level The level at which the Player is
	 * @param tries The number of tries the Player has used up
	 * @param points The number of points for that level a Player has
	 */
	public Player(int level, int tries, int points)
	{
		this.level = level;
		this.tries = tries;
		this.points = points;
	}
	
	/**
	 * Returns the level of the user
	 * @return the level of the user
	 */
	public int getLevel()
	{
		return this.level;
	}
	
	/**
	 * Sets the level of the user
	 * @param level The new level to be set 
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	/**
	 * Returns the tries of the user
	 * @return The tries of the user
	 */
	public int getTries()
	{
		return this.tries;
	}
	
	/**
	 * Sets the tries of the user
	 * @param tries The new tries to be set
	 */
	public void setTries(int tries)
	{
		this.tries = tries;
	}
	
	/**
	 * Returns the points of the user
	 * @return The points of the user
	 */
	public int getPoints()
	{
		return this.points;
	}
	
	/**
	 * Sets the points of the user
	 * @param points The new Points to be set
	 */
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	/**
	 * Returns the PIVs of Player in String form
	 * @return The PIVs of Player in String form
	 */
	public String toString()
	{
		//outputs the PIVs
		String playerString = "Level: " + level + "\nTries: " + tries
			+ "\nPoints: " + points;
		return playerString;
	}
	
	/**
	 * Performs the necessary actions when a correct answer is given
	 */
	public void correctAnswer()
	{
		points++;  //increases points
		if (points == Game.LEVEL_THRESHOLD) //increments level if points is 5
		{
			level++;
			//resets points for that level
			points = 0;
		}
		//tries reset if answer is correct
		tries = Game.TRIES;
	}
	
	/**
	 * Performs the necessary actions when a wrong answer is given
	 */
	public void wrongAnswer()
	{
		//decreases the number of tries they have left
		tries--;
	}
}