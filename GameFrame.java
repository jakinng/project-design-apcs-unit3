import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Simulates the Game Frame where the game is displayed
 * @author Jakin Ng
 * @version 12.23.2018
 */
public class GameFrame extends JFrame implements ActionListener
{
	//Needed to extend JFrame
	private static final long serialVersionUID = 1L;
	
	//Constants for the fonts and sizes of the various components
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

	/**
	 * The game that the GameFrame is utilizing
	 */
	private Game game;
	
	/**
	 * The text field for the game frame
	 */
	private JTextField gameText;
	
	
	/**
	 * Creates a new Game Frame
	 */
	public GameFrame()
	{
		//Sets the look and feel based on the device playing the game
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//Creates a new Game and text field and initializes the graphics
		game = new Game();    
		gameText = new JTextField(TEXTFIELD_SIZE);
		initialize();
	}
	
	/**
	 * Returns the Game used by this GameFrame
	 * @return The Game used by this GameFrame
	 */
	public Game getGame() 
	{
		return game;
	}

	/**
	 * Sets game to the game parameter
	 * @param game The new game that the PIV is being set to
	 */
	public void setGame(Game game) 
	{
		this.game = game;
	}
	
	/**
	 * Returns the text field of the game
	 * @return The text field of the game
	 */
	public JTextField getGameText()
	{
		return gameText;
	}
	
	/**
	 * Sets gameText to newTextField
	 * @param newTextField The updated JTextField
	 */
	public void setGameText(JTextField newTextField)
	{
		gameText = newTextField;
	}
	
	/**
	 * Initializes the font, alignments, etc. for the 
	 * text field and the frame
	 */
	public void initialize()
	{
		//sets the fonts and alignments
		gameText.setFont(PROBLEM_FONT);
		gameText.setHorizontalAlignment(JTextField.RIGHT);
		//allows gameText to sense when the enter key is pressed
		gameText.addActionListener(this);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Numbers Game");
		setSize(GAME_FRAME_H_SIZE, GAME_FRAME_V_SIZE);
		setLocation(GAME_FRAME_X, GAME_FRAME_Y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());	
	}
	
	/**
	 * Displays the text toDisplay
	 * @param toDisplay The text to be displayed
	 */
	public void displayText(String toDisplay)
	{
		//creates a new window containing the message
		JOptionPane.showMessageDialog(null, toDisplay, "", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Sets the game's problem to a new problem based on the player's level
	 */
	public void newProblem()
	{
		game.setCurrentProb(ProblemGenerator.generateProblem(game.getPlayer().getLevel()));
	}
	
	/**
	 * Sets the Frame to show the statsPanel in the top left 
	 * and the gamePanel in the center
	 */
	public void setFrame()
	{
		//It clears the panel 
		getContentPane().removeAll();
		//then adds back the statsPanel and the gamePanel
		add(statsPanel(), BorderLayout.PAGE_START);
		add(returnGamePanel(), BorderLayout.CENTER);
		//shows the panel again
		getContentPane().revalidate();
		getContentPane().repaint();
		setVisible(true);
	}
	
	/**
	 * Called when the enter key is pressed 
	 * Resets the text field and checks the answer in the text field
	 * Implements the method in the interface
	 */
	public void actionPerformed(ActionEvent e)
	{	
		game.setPlayerAnswer(gameText.getText());
		gameText.setText(null);
		testAnswer();	
	}
	
	/**
	 * Checks whether the playerAnswer is correct 
	 * and performs the corresponding actions
	 */
	public void testAnswer()
	{
		//If the answer is an integer, it checks the answer
		try
		{
			int intAnswer = Integer.parseInt(game.getPlayerAnswer());
			//if the answer matches the problem's answer, it is correct
			if (intAnswer == game.getCurrentProb().getAnswer())
			{
				correctAnswer();
			}
			//otherwise it is wrong
			else
			{
				displayText("Wrong answer.");
				wrongAnswer();
			}
		}
		//if the answer is not an integer it is counted as wrong
		catch (NumberFormatException ex)
		{
			displayText("Wrong; the answer is not an integer.");
			wrongAnswer();
		}
	}
	
	/**
	 * Returns this GameFrame in String form
	 */
	@Override
	public String toString()
	{
		String gameFrameStr = game.toString() + "Text: " + gameText.getText();
		return gameFrameStr;
	}
	
	/**
	 * Checks if two GameFrames are equal
	 */
	@Override
	public boolean equals(Object gameFrame)
	{
		if (!(gameFrame instanceof GameFrame))
		{
			return false;
		}
		// checks if game and gameText are equal
		else if (game == ((GameFrame)gameFrame).getGame() && gameText.equals(((GameFrame)gameFrame).getGameText()))
		{
			return true;
		}			
		else
		{
			return false;
		}
	}
	
	/**
	 * Helper method called when there is an incorrect answer
	 */
	private void wrongAnswer()
	{
		Player player = game.getPlayer();
		player.wrongAnswer();
		//If there are still tries left, it simply refreshes the frame
		if (player.getTries() > 0)
		{
			setFrame();
		}
		//otherwise it resets tries and creates a new problem
		else
		{
			player.setTries(Game.TRIES);
			displayText("No tries left. " + game.getCurrentProb());
			newProblem();
			setFrame();
		}
	}
	
	/**
	 * Helper method called when there is a correct answer
	 */
	private void correctAnswer()
	{
		Player player = game.getPlayer();
		displayText("Correct Answer.");
		player.correctAnswer();
		//If the player has won, the game ends
		if (player.getLevel() > Game.MAX_LEVEL)
		{
			displayText("You win!");
			setVisible(false);
			dispose();
		}
		//otherwise a new problem is created
		else
		{
			newProblem();
			setFrame();
		}
	}
	
	/**
	 * Helper method for setFrame
	 * @return The JPanel containing the statistics of this player
	 */
	private JPanel statsPanel()
	{
		//Creates the new JLabel and sets the text
		Player player = game.getPlayer();
		JLabel statsLabel = new JLabel();
		statsLabel.setText("<html> Level " + player.getLevel() + 
				"<br> Tries Remaining: " + player.getTries() + 
				"<br> Points: " + player.getPoints() + "</html>");
		//sets font and border
		statsLabel.setFont(STATS_FONT);
		statsLabel.setBorder(STATS_BORDER);
		//creates a new JPanel and adds the stats Label
		JPanel stats = new JPanel();
		stats.setLayout(new BorderLayout());
		stats.add(statsLabel, BorderLayout.LINE_START);
		stats.setVisible(true);
		return stats;
	}
	
	/**
	 * Helper method for setFrame
	 * @return The Panel including the question and answer text field 
	 */
	private JPanel returnGamePanel()
	{
		//creates a new JLabel for the problem
		JLabel problemLabel = new JLabel();
		problemLabel.setText(game.getCurrentProb().getQuestion());
		problemLabel.setFont(PROBLEM_FONT);
		//creates a new game Panel and adds the problem 
		JPanel gamePanel = new JPanel(new FlowLayout());
		gamePanel.add(problemLabel);
		//and answer text field
		gamePanel.add(gameText);
		gamePanel.setVisible(true);
		return gamePanel;
	}
}	
