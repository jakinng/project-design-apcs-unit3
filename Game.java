/**
 * Simulates the Game 
 * @author Jakin Ng
 * @version 12.23.2018
 */
public class Game 
{
	//the number of tries remaining
	public final static int TRIES = 2;
	//the number of points needed to advance a level
	public final static int LEVEL_THRESHOLD = 5;
	//the maximum level that can be reached
	public final static int MAX_LEVEL = 3;
	
	//the player, current problem, and player answer of this game
	private Player player;
	private Problem currentProb;
	private String playerAnswer;

	/**
	 * Constructs a new Game
	 */
	public Game()
	{
		player = new Player(1, TRIES, 0);
		currentProb = ProblemGenerator.generateProblem(1);
	}
	
	/**
	 * Returns the current player of the Game
	 * @return The current player of the Game
	 */
	public Player getPlayer() 
	{
		return player;
	}

	/**
	 * Changes the player playing the Game to the parameter player
	 * @param player The new player playing the Game
	 */
	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	/**
	 * Returns the current problem
	 * @return The current problem
	 */
	public Problem getCurrentProb() 
	{
		return currentProb;
	}

	/**
	 * Sets currentProb to a new problem, currentProb
	 * @param currentProb The new problem
	 */
	public void setCurrentProb(Problem currentProb) 
	{
		this.currentProb = currentProb;
	}

	/**
	 * Returns the answer given by the player
	 * @return The answer given by the player
	 */
	public String getPlayerAnswer() {
		return playerAnswer;
	}

	/**
	 * Updates the playerAnswer
	 * @param playerAnswer The new playerAnswer
	 */
	public void setPlayerAnswer(String playerAnswer) {
		this.playerAnswer = playerAnswer;
	}
	
	/**
	 * Converts the game to a String
	 */
	@Override
	public String toString()
	{
		String gameString = "Player: " + player + "\nCurrent Problem: " 
				+ currentProb + "\nPlayer Answer: " + playerAnswer;
		return gameString;
	}
	
	/**
	 * Checks if two Games are equivalent
	 */
	@Override
	public boolean equals(Object game)
	{
		if (!(game instanceof Game))
		{
			return false;
		}
		// checks if player, currentProb, and playerAnswer are equal
		else if (player == ((Game)game).getPlayer() &&
				currentProb.equals(((Game)game).getCurrentProb()) && 
				playerAnswer.equals(((Game)game).getPlayerAnswer()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
