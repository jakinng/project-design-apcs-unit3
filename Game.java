//importing what is necessary
import java.util.Scanner;
import java.lang.NumberFormatException; 

/**
 * Simulates a Game
 * @author Taerim Eom and Jakin Ng
 * @version 12.8.2018
 */
public class Game 
{
	// the number of tries per turn
	public final static int TRIES = 2; 
	// the number of points necessary to advance to the next level
	public final static int LEVEL_THRESHOLD = 5;  
	
	public static void main(String[] args)
	{
		//makes a new Scanner and Player with correct values
		Scanner sc = new Scanner(System.in); 
		Player player = new Player(1, TRIES, 0);
		//while the player hasn't won, it keeps asking questions
		while (player.getLevel() <= 3)
		{
			System.out.println(player);
			checkQuestion(sc, player);
			player.setTries(TRIES);
		}
		//ends the game
		System.out.print("You win! Thank you for playing.");
		sc.close();
	}
	
	/**
	 * Checking the answer for the Question of the Problem
	 * @param sc The Scanner being used for input
	 * @param player The Player playing the Game
	 */
	public static void checkQuestion(Scanner sc, Player player)
	{
		//generate a problem at the right level
		Problem problem = ProblemGenerator.generateProblem(player.getLevel());
		System.out.println(problem.getQuestion());
		//for the first try, gets the player's answer and performs the necessary actions
		String playerAnswer = sc.nextLine();
		testingAnswer(sc, player, problem, playerAnswer);
		//uses the rest of the tries and performs necessary actions (could be cleaner?)
		while (player.getTries() != TRIES && player.getTries() > 0)
		{
			System.out.println("Try again.");
			System.out.println("\n" + player);
			System.out.println(problem.getQuestion());
			playerAnswer = sc.nextLine();
			testingAnswer(sc, player, problem, playerAnswer);
		}
	}
	
	/**
	 * Helper method for checkQuestion - ask Mr. Deppe about how to format
	 * @param sc The Scanner being used for input
	 * @param player The Player playing the Game
	 * @param problem The Problem being given
	 * @param playerAnswer The player's answer
	 */
	private static void testingAnswer(Scanner sc, Player player, 
			Problem problem, String playerAnswer)
	{
		//if the player has entered an integer value: 
		try
		{
			//sets the player's answer in integer form
			int intAnswer = Integer.parseInt(playerAnswer);
			//if they're right, they do what they need to for a correct answer
			if (intAnswer == problem.getAnswer())
			{
				player.correctAnswer();
				System.out.println("\n" + problem);
			}
			//otherwise consider it a wrong answer
			else
			{
				player.wrongAnswer();
				if (player.getTries() == 0)
				{
					System.out.println("\n" + problem);
				}
			}
		}
		//otherwise it prompts them simply to enter an integer
		catch (NumberFormatException ex)
		{
			System.out.println("Please enter an integer.");
			player.wrongAnswer();
		}
	}
}