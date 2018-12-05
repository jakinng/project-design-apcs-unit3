
public class Player 
{
	private int level;
 	private int tries;
	private int points;
	
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
	 * Returns the tries of the user
	 * @return the tries of the user
	 */
	public int getTries()
	{
		return this.tries;
	}
	
	/**
	 * Returns the points of the user
	 * @return the points of the user
	 */
	public int getPoints()
	{
		return this.points;
	}
}
