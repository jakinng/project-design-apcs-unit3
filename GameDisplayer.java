import java.awt.*;
import java.text.*;
import java.util.concurrent.Semaphore;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Displays the Game
 * @author Jakin Ng 
 * @version 12.19.2018
 */
public class GameDisplayer implements ActionListener
{	
	final static Font PROBLEM_FONT = new Font("Tahoma", Font.PLAIN, 40);
	final static Font STATS_FONT = new Font("Tahoma", Font.PLAIN, 15);
	final static EmptyBorder STATS_BORDER = new EmptyBorder(10, 10, 5, 5);
	final static int TEXTFIELD_SIZE = 2;
	final static int GAME_FRAME_H_SIZE = 400;
	final static int GAME_FRAME_V_SIZE = 250;
	final static int GAME_FRAME_X = 500;
	final static int GAME_FRAME_Y = 150;
	final static int DISPLAY_H_SIZE = 200;
	final static int DISPLAY_V_SIZE = 100;
	final static int DISPLAY_X = 500;
	final static int DISPLAY_Y = 150;
	
	private JFrame gameFrame;
	private JTextField textfield;
	private Player gamePlayer;
	private String playerAnswer;
	private Semaphore semaphore;
	
	public static void main(String[] args) 
	{
		GameDisplayer yo = new GameDisplayer(new Player(3, 3, 2));
		yo.displayText("hello");
		yo.updateGame(3, 3, 3, "3");
	}
	
	public GameDisplayer(Player player)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		gameFrame = new JFrame();
		textfield = new JTextField(TEXTFIELD_SIZE);
		gamePlayer = player;
		playerAnswer = "-1";
		semaphore = new Semaphore(0);
	}
	
	public void updateGame(int level, int triesRem, 
			int points, String problem)
	{
		JPanel centerPanel = gamePanel(problem);
		JPanel topPanel = statsPanel(level, triesRem, points);
		setGameFrame(topPanel, centerPanel);
	}
	
	public void setGameFrame(JPanel topPanel, JPanel centerPanel)
	{
		gameFrame.getContentPane().setLayout(new BorderLayout());
		gameFrame.setTitle("Numbers Game");
		gameFrame.setSize(GAME_FRAME_H_SIZE, GAME_FRAME_V_SIZE);
		gameFrame.setLocation(GAME_FRAME_X, GAME_FRAME_Y);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new BorderLayout());
		gameFrame.add(topPanel, BorderLayout.PAGE_START);
		gameFrame.add(centerPanel, BorderLayout.CENTER);
		gameFrame.setVisible(true);
	}
	
	public void checkQuestion(Player player, int tries)
	{
		//generate a problem at the right level
		Problem problem = ProblemGenerator.generateProblem(player.getLevel());
		updateGame(player.getLevel(), player.getTries(), 
				player.getPoints(), problem.getQuestion());
		try
		{
			semaphore.acquire();
		}
		catch (InterruptedException ex) 
		{ 
			System.out.println("ok");
		}	
		//for the first try, gets the player's answer and performs the necessary actions
		Game.testingAnswer(gamePlayer, problem, playerAnswer, this);
		//uses the rest of the tries and performs necessary actions 
		while (gamePlayer.getTries() != tries && gamePlayer.getTries() > 0)
		{
			displayText("Try again.");
			try
			{
				semaphore.acquire();
			}
			catch (InterruptedException ex) { }
			updateGame(player.getLevel(), player.getTries(), 
					player.getPoints(), problem.getQuestion());
			testingAnswer(problem);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		playerAnswer = textfield.getText();
		System.out.println(playerAnswer);
		textfield.setText("");
		semaphore.release();
	}
	
	public void displayText(String toDisplay)
	{
		JOptionPane.showMessageDialog(null, toDisplay, "", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void testingAnswer(Problem problem)
	{
		//if the player has entered an integer value: 
		try
		{
			//sets the player's answer in integer form
			int intAnswer = Integer.parseInt(playerAnswer);
			//if they're right, they do what they need to for a correct answer
			if (intAnswer == problem.getAnswer())
			{
				gamePlayer.correctAnswer();
			}
			//otherwise consider it a wrong answer
			else
			{
				gamePlayer.wrongAnswer();
			}
		}
		//otherwise it prompts them simply to enter an integer
		catch (NumberFormatException ex)
		{
			displayText("You must enter an integer.");
		}
	}
	
	
	private JPanel gamePanel(String problem)
	{
		JPanel gamePanel = new JPanel(new FlowLayout());
		JLabel problemLabel = new JLabel();
		problemLabel.setText(problem);
		problemLabel.setFont(PROBLEM_FONT);
		gamePanel.add(problemLabel);
		
		textfield.setFont(PROBLEM_FONT);
		textfield.setHorizontalAlignment(JTextField.RIGHT);
		textfield.addActionListener(this);
		gamePanel.add(textfield);
		
		return gamePanel;
	}
	
	private static JPanel statsPanel(int level, int triesRem, int points)
	{
		JLabel statsLabel = new JLabel();
		statsLabel.setText("<html> Level " + level + "<br> Tries Remaining: "
				+ triesRem + "<br> Points: " + points + "</html>");
		statsLabel.setFont(STATS_FONT);
		statsLabel.setBorder(STATS_BORDER);
		JPanel stats = new JPanel();
		stats.setLayout(new BorderLayout());
		stats.add(statsLabel, BorderLayout.LINE_START);
		return stats;
	}
}