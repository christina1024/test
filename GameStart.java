import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * This class, GameStart, contains the GUI graphical features of the starting page. It also contain the main method of the game.
 *
 * Description of the game
 *     This is a GUI-based Tetris game.
 *     The game is played on a 10 x 21 grid
 *
 * There are 4 main controls for this game:
 *     >> left arrow key to move the piece left
 *     >> right arrow key to move the piece right
 *     >> down arrow key to move the piece down
 *     >> up arrow key to rotate the piece clockwise
 *
 * The Tetrominoes are represented in seven different colors:
 *     T-shape: blue
 *     I-shape: yellow
 *     O-shape: cyan
 *     L-shape: red
 *     J-shape: magenta
 *     S-shape: orange
 *     Z-shape: green
 *
 * Once the piece either hits the bottom of the grid or hits another piece,
 * the Tetrominoes will then turned to the color black
 *
 * The scoring system of this version of this game is based on the amounts of lines the player has cleared.
 * One line cleared is worth 100 points.
 *
 * @author Group 32
 *
 */
public class GameStart extends JFrame {

	public static final int WINDOW_WIDTH = 311;
	public static final int WINDOW_HEIGHT = 602;

	/**
	 * This method, Scores, reads the highScore.txt file that stores the top three high scores
	 * Contains a try-catch block to catch exception in reading the highScore.txt file
	 */
	public static void Scores() {

		String filename = "highScore.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		        String line = null;
			line = new Scanner(new File (filename)).useDelimiter("\\A").next(); 
			    JOptionPane.showMessageDialog(null, line);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method, GUI, is the design for the starting page of the game
	 * It contains the JFrame, two JPanels, JLabel, and three JButtons and their respective code
	 * The three buttons are: start, score, and quit
	 *
	 */
	public static void GUI() {

		JFrame frame = new JFrame("Tetris");

		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();

		JLabel text = new JLabel("TETRIS", SwingConstants.CENTER);
		text.setFont(text.getFont().deriveFont(64.0f));

		JButton start = new JButton("Start");
		JButton score = new JButton("HighScore");
		JButton quit = new JButton("Quit");

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				new Player();
				frame.dispose();
			}
		});

		score.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Scores();
			}
		});

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		panel.setLayout(new BorderLayout());
		panel.add(text);

		panel2.setLayout(new GridLayout());
		panel2.add(start, BorderLayout.WEST);
		panel2.add(score, BorderLayout.CENTER);
		panel2.add(quit, BorderLayout.EAST);

		frame.add(panel);
		frame.add(panel2, BorderLayout.SOUTH);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Application entry point
	 *
	 * @param args This method have an array of command-line arguments being passed to
	 */
	public static void main(String[] args) {

		GUI();
	}
}
