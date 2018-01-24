import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

/**
 * This class, Player, contains ActionListener and KeyListener for the game. It also contain a timer to move the pieces down by one block at a time.
 * It paints the grid and also display game over and the score when the game is over. There is also a method that writes to and update a text file that contains the top 3 scores
 *
 * @author Group 32
 *
 */
public class Player extends JFrame implements ActionListener, KeyListener {

	public static final int SQUARE_SIZE = 26;
	public static final int WIDTH = 12;
	public static final int HEIGHT = 24;

	private int rank = 0;

	private Boolean scoreSettle = false;
	private Boolean InputScore = false;
	private Boolean endGame = false;

	private String highestScore;
	private String secondplace;
	private String secondScore;

	private ArrayList<String> HighScore = new ArrayList<String>();

	private int x = 2;
	private int y = 2;
	private int score;
	private int dropSpeed = 380;

	private Color[][] miniGrid;
	private Color[][] grid;

	Timer timer;

	Grid gr = new Grid();
	Tetrominoes tp = new Tetrominoes();



	/**
	 *
	 */
	public Player() {
	    
		addKeyListener(this);
		miniGrid = tp.getMiniGrid();
		x = tp.getX();
		y = tp.getY();
		grid = gr.getUpdate();
		repaint();

		ActionListener listener = new AbstractAction() {
			public void actionPerformed (ActionEvent e) {
				endGame = gr.getEndGame();
				if(endGame == true) {
				        
					timer.stop();
				}
				score = gr.getScore();
				tp.dropping();
				miniGrid = tp.getMiniGrid();
				x = tp.getX();
				y = tp.getY();
				grid = gr.getUpdate();
				repaint();

			}
		};

		timer = new Timer(dropSpeed, listener);
		timer.start();

		setSize(GameStart.WINDOW_WIDTH, GameStart.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * An unimplemented method
	 */
	public void actionPerformed (ActionEvent n) {}

	/**
	 * Assigning keys to a string and to movement of the tetrominoes
	 */
	public void keyPressed(KeyEvent l) {

		int k = l.getKeyCode();

		if (k == KeyEvent.VK_DOWN) {
			String move = "down";
			tp.setMovement(move);
		}
		if (k == KeyEvent.VK_LEFT) {
			String move = "left";
			tp.setMovement(move);
		}
		if (k == KeyEvent.VK_RIGHT) {
			String move = "right";
			tp.setMovement(move);
		}
		if (k == KeyEvent.VK_UP) {
			String move = "up";
			tp.setMovement(move);
		}
	}

	/**
	 * An unimplemented method
	 */
	public void keyTyped(KeyEvent l) {}

	/**
	 * An unimplemented method
	 */
	public void keyReleased(KeyEvent l) {}

	/**
	 *
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for(int i = 0; i < WIDTH; i++) {
			for(int k = 0; k < HEIGHT; k++) {

				g.setColor(grid[i][k]);
				g.fillRect(i * SQUARE_SIZE, k * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
			}

		}

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				if (miniGrid[i][k] != Color.WHITE) {
					g.setColor(miniGrid[i][k]);
					g.fillRect((i+x)* SQUARE_SIZE, (k + y) * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
				}
			}
		}

		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		g.drawString("" + (score*100),250,50);

		if (endGame == true) {
			currentFont = g.getFont();
			newFont = currentFont.deriveFont(currentFont.getSize() *2.0F);
			g.setFont(newFont);
			g.setColor(Color.RED);
			g.drawString("GAME OVER",26,250);
			currentFont = g.getFont();
			newFont = currentFont.deriveFont(currentFont.getSize() *0.8F);
			g.setFont(newFont);
			g.setColor(Color.RED);
			g.drawString("Score: "+(score*100),70,300);
			if(InputScore == false) {
				FileE();
			}
		}

		if (InputScore == true) {
			currentFont = g.getFont();
			newFont = currentFont.deriveFont(currentFont.getSize() *0.7F);
			g.setFont(newFont);
			g.setColor(Color.RED);
			int scoreLocation=350;
			for(int placeRank=0; placeRank < HighScore.size();placeRank++){
			    g.drawString(HighScore.get(placeRank),70,scoreLocation);
			    scoreLocation=scoreLocation+40;
			}
		        
		}
	}

	/**
	 * This method, FileE, updates the highScore.txt file
	 * After the game ends, it prompts for the users name
	 * Contains a while loop to check if the file has previous information and will
	 * compare the user's score with the scores that already exist inside the highScore.txt file
	 * If the user's score is higher, then a new top three score will be recorded in the format of rank(1,2 or3) firstName score.
	 */
	public void FileE() {
		int inputScore = score * 100;
		String name = JOptionPane.showInputDialog("Enter Your First Name: ");
		if(name==null){
		    name="unknown";
		}
		String[] name1 = name.split(" ");
		String firstName = null;

		if (name1.length > 0) {
			firstName=name1[0];
		}
		else {
			firstName = name;
		}

		String fileName = "highScore.txt";
		Scanner input = null;

		try {
			input = new Scanner(new File(fileName));
		}

		catch(FileNotFoundException fnfe) {
			return;
		}

		catch(NullPointerException npe) {
			return;
		}

		while(input.hasNextLine()) {
			rank = rank +1 ;
			int rankScore = 0;
			String line = input.nextLine();
			String[] Scores = line.split(" ");
			String recordScore = Scores[2];
			int highScore = Integer.parseInt(recordScore);

			if (scoreSettle == false) {
				if (inputScore > highScore) {
					highestScore = rank + " " + firstName + " " + inputScore;
					scoreSettle = true;
					secondplace = Scores[1];
					secondScore = Scores[2];
				}
				else {
					highestScore = rank + " " + Scores[1] + " " + Scores[2];
				}
			}
			else {
				highestScore = rank +" "+ secondplace +" "+ secondScore;
			}
			secondplace = Scores[1];
			secondScore = Scores[2];
			HighScore.add(highestScore);
			rankScore = rankScore+1;
		}		

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(fileName);
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return;
		}

		finally {
		    if(HighScore==null||HighScore.size()<3){
		    highestScore=(rank+1)+" "+firstName+" "+inputScore;
		    HighScore.add(highestScore);
		}
		    for (int scoreRank = 0; scoreRank < HighScore.size(); scoreRank++) {
				writer.print(HighScore.get(scoreRank));
				writer.println();}
			input.close();
			writer.close();
			InputScore = true;
			repaint();
			String[] choices=new String[]{"Quit","Return to the Menu"};
			String choice=askUser(choices);
			if(choice.equals("Quit")){
			    System.exit(0);
			}			
			else if(choice.equals("Return to the Menu")||choice==null){
			    gr.setScore(0);
			    dispose();
			    GameStart gs=new GameStart();
			    gs.GUI();
			}
		}
	}
    private String askUser(String[] choices){
	String userChoice=(String)JOptionPane.showInputDialog(null,
						     "Options",
						     "input",
						     JOptionPane.PLAIN_MESSAGE,
						     null,
						     choices,
						     choices[0]);
	if(userChoice==null){userChoice="Return to the Menu";}
	return userChoice;
    }

}
