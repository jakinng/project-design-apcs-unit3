/**
 * Simulates a Player of the Game
 * @author Taerim Eom and Jakin Ng
 * @version 12.8.2018
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
}