import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.util.concurrent.TimeUnit;

public class GameFrame extends JFrame implements ActionListener
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

	private Game game;
	private JTextField gameText;
	private boolean running;
	
	public static void main(String[] args)
	{
	}
	
	public void playGame()
	{
		if (game.getPlayer().getLevel() > Game.MAX_LEVEL)
		{
			displayText("You win!");
		}
//		if (game.getPlayer().getTries() <= 1)
//		{
//			game.setCurrentProb(ProblemGenerator.generateProblem(game.getPlayer().getLevel()));
//			setFrame();
//		}
	}
	
	public GameFrame()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		game = new Game();    
		gameText = new JTextField(TEXTFIELD_SIZE);
		initialize();
		running = false;
		setFrame(statsPanel(), returnGamePanel());
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		if (game.getPlayer().getTries() > 1)
		{
			game.setPlayerAnswer(gameText.getText());
			gameText.setText(null);
			testAnswer();
		}
		else
		{
			displayText("no tries left.");
			game.getPlayer().setTries(Game.TRIES);
			game.setCurrentProb(ProblemGenerator.generateProblem(game.getPlayer().getLevel()));
			setFrame();
			setVisible(true);
		}		
	}
	
	public void askQuestion()
	{
		//sets the problem of the game to a new problem, depending on player's level
		game.setCurrentProb(ProblemGenerator.generateProblem(game.getPlayer().getLevel()));
		setFrame();
		setVisible(true);
	}
	
	public void testAnswer()
	{
		try
		{
			int intAnswer = Integer.parseInt(game.getPlayerAnswer());
			if (intAnswer == game.getCurrentProb().getAnswer())
			{
				displayText("Correct Answer.");
				game.getPlayer().correctAnswer();
				game.setCurrentProb(ProblemGenerator.generateProblem(game.getPlayer().getLevel()));
				setFrame();
			}
			else
			{
				displayText("Wrong Answer. Try Again.");
				game.getPlayer().wrongAnswer();
				setFrame();
			}
		}
		catch (NumberFormatException ex)
		{
			displayText("Please enter an integer. Try Again.");
			game.getPlayer().wrongAnswer();
			setFrame();
		}
	}
	
//ok
	
	public Game getGame() 
	{
		return game;
	}

	public void setGame(Game game) 
	{
		this.game = game;
	}
	
	public void initialize()
	{
		gameText.setFont(PROBLEM_FONT);
		gameText.setHorizontalAlignment(JTextField.RIGHT);
		gameText.addActionListener(this);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Numbers Game");
		setSize(GAME_FRAME_H_SIZE, GAME_FRAME_V_SIZE);
		setLocation(GAME_FRAME_X, GAME_FRAME_Y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());	
	}
	
	public void setFrame(JPanel topPanel, JPanel centerPanel)
	{
		add(topPanel, BorderLayout.PAGE_START);
		add(centerPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void setFrame()
	{
		getContentPane().removeAll();
		add(statsPanel(), BorderLayout.PAGE_START);
		add(returnGamePanel(), BorderLayout.CENTER);
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public void displayText(String toDisplay)
	{
		JOptionPane.showMessageDialog(null, toDisplay, "", JOptionPane.PLAIN_MESSAGE);
	}
	
	public JPanel statsPanel()
	{
		JLabel statsLabel = new JLabel();
		statsLabel.setText("<html> Level " + game.getPlayer().getLevel() + "<br> Tries Remaining: "
				+ game.getPlayer().getTries() + "<br> Points: " + 
				game.getPlayer().getPoints() + "</html>");
		statsLabel.setFont(STATS_FONT);
		statsLabel.setBorder(STATS_BORDER);
		JPanel stats = new JPanel();
		stats.setLayout(new BorderLayout());
		stats.add(statsLabel, BorderLayout.LINE_START);
		stats.setVisible(true);
		return stats;
	}
	
	public JPanel returnGamePanel()
	{
		JPanel gamePanel = new JPanel(new FlowLayout());
		JLabel problemLabel = new JLabel();
		problemLabel.setText(game.getCurrentProb().getQuestion());
		problemLabel.setFont(PROBLEM_FONT);
		gamePanel.add(problemLabel);
		gamePanel.add(gameText);
		gamePanel.setVisible(true);
		return gamePanel;
	}
}	
