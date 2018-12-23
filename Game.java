
public class Game 
{
	public final static int TRIES = 2;
	public final static int LEVEL_THRESHOLD = 5;  
	public final static int MAX_LEVEL = 3;
	
	private Player player;
	private Problem currentProb;
	private String playerAnswer;

	public Game()
	{
		player = new Player(1, TRIES, 0);
		currentProb = ProblemGenerator.generateProblem(1);
	}
	
	public Player getPlayer() 
	{
		return player;
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	public Problem getCurrentProb() 
	{
		return currentProb;
	}

	public void setCurrentProb(Problem currentProb) 
	{
		this.currentProb = currentProb;
	}

	public String getPlayerAnswer() {
		return playerAnswer;
	}

	public void setPlayerAnswer(String playerAnswer) {
		this.playerAnswer = playerAnswer;
	}
}
