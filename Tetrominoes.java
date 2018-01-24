import java.util.Random;
import java.awt.Color;

/**
 * The Tetrominoes class constructs the tetrominoes, stores and updates the tetrominoes location in an array list, and setting the new coordinates depending on the movements,
 * such as, rotation, left, right, and down
 *
 * @author Group 32
 *
 */
public class Tetrominoes extends Grid {

	private String shape;
	private String direction;

	private int rotation = 0;
	private int x = 5;
	private int y = 0;

	private Boolean gameEnd = false;
	private Boolean hitSide = false;
	private Boolean hitBottom = false;

	private Color[][] miniGrid;

	/**
	 * This constructor, Tetrominoes,  takes no arguments.
	 * However, every time the constructor is called, it invokes the method random() in this current class
	 * and will generate a new tetromino and restores the value inside the instance variable shape
	 *
	 * @see #random()
	 * @see #pieceShow()
	 */
	public Tetrominoes() {

		shape = random();
		pieceShow();
	}

	/**
	 * The random method randomly selects a tetromino based on the randomization
	 * Which is stored in the num variable but is called by the Tetrominoes() constructor for usage
	 *
	 * @return This returns shape
	 */
	private String random() {

		Random rand = new Random();
		int num = rand.nextInt(7);

		if (num == 0) {
			shape = "O";
		}
		else if (num == 1) {
			shape = "L";
		}
		else if (num == 2) {
			shape = "S";
		}
		else if (num == 3) {
			shape = "Z";
		}
		else if (num == 4) {
			shape = "I";
		}
		else if (num == 5) {
			shape = "J";
		}
		else if (num == 6) {
			shape = "T";
		}
		return shape;
	}

	/**
	 * getter for x
	 *
	 * @return This returns x
	 */
	public int getX() {

		return x;
	}

	/**
	 * getter for y
	 *
	 * @return This returns y
	 */
	public int getY() {

		return y;
	}

	/**
	 * getter for miniGrid
	 *
	 * @return This returns miniGrid
	 */
	public Color[][] getMiniGrid() {

		return miniGrid;
	}

	/**
	 * setter for movement of pieces
	 *
	 * @param direction1 This is a string parameter to setMovement method
	 */
	public void setMovement(String direction1) {

		direction = direction1;
		pieceMovement();
	}

	/**
	 * getter for hitBottom
	 *
	 * @return This method returns hitBottom
	 */
	public Boolean getHitBottom() {

		return hitBottom;
	}

	/**
	 * This method, pieceShow, stores the coordinates of the 7 shapes in an ArrayList
	 */
	private void pieceShow() {

		switch (shape) {
			case "O":
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 1 && r == 2) || (c == 2 && r == 1) || (c == 2 && r == 2)) {
							miniGrid[c][r] = Color.CYAN;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case "L":
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r=0; r<4; r++) {
						if ((r == 1 && c == 1) || (c == 0 && r == 1) || (c == 2 && r == 1) || (c == 2 && r == 0)) {
							miniGrid[c][r] = Color.RED;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}

				break;

			case "S":
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if((r == 1 && c == 1) || (c == 0 && r == 1) || (c == 0 && r == 2) || (c == 1 && r == 0)) {
							miniGrid[c][r] = Color.GREEN;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case "Z":
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 0 && r == 0) || (c == 0 && r == 1) || (c == 1 && r == 2)) {
							miniGrid[c][r] = Color.ORANGE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case "J":
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4 ; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 1 && r == 0) || (c == 1 && r == 2) || (c == 0 && r == 2)) {
							miniGrid[c][r] = Color.MAGENTA;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}

				break;

			case "I":
				miniGrid=new Color[4][4];
				for (int c = 0; c < 4; c++){
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 0 && r == 1) || (c == 2 && r == 1) || (c == 3 && r == 1)) {
							miniGrid[c][r] = Color.YELLOW;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case "T":
				miniGrid = new Color[4][4];
				for (int c = 0; c<4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 1 && r == 0) || (c == 0 && r == 1) || (c == 2 && r == 1)) {
							miniGrid[c][r] = Color.BLUE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
		}
	}

	/**
	 * This method, pieceMovement, stores new coordinates of the tetrominoes when a tetrominoe is moved in a direction (rotation, left, right, and down)
	 * For the cases, a refers to the left direction, s refers to the down direction, d refers to the right direction, and w refers to rotation
	 * For case s, dropping method refer to dropping()
	 * For case w, there are four different possible tetromino rotation, if user choose to rotate the tetromino again, it will add one to the rotation variable. Also,
	 * the tetromino are rotating clockwise. For getShape method refer to getShape()
	 *
	 * @see #dropping()
	 * @see #getShape()
	 */
	private void pieceMovement() {

		switch (direction) {

			case "left":
				x = x - 1;
				hitSide = wallCollision(x, y, miniGrid);
				if (hitSide == true) {
					x = x + 1;
				}
				break;

			case "down":
				dropping();
				break;

			case "right":
				x = x + 1;
				hitSide = wallCollision(x, y, miniGrid);
				if(hitSide == true) {
					x = x - 1;
				}
				break;

			case "up":
				if (rotation > 4) {
					rotation = 1;
				}
				else {
					rotation=rotation + 1;
				}
				getShape();
				hitSide = wallCollision(x, y, miniGrid);

				if (hitSide == true) {

					if (rotation == 1) {
						rotation = 4;
					}
					else {
						rotation = rotation - 1;
					}
					getShape();
				}
				hitSide = false;

		 }
	}

	/**
	 * This method, dropping, is a general method to move the tetrominoes down one line and stores those locations to an ArrayList
	 */
	public void dropping() {

		y = y + 1;
		hitBottom = bottomCollision(x, y, miniGrid);
		gameEnd = getEndGame();

		if (hitBottom == true) {
			update(x, y, miniGrid);
			lineRemover();
			endGame();
			shape = random();
			pieceShow();
			rotation = 0;
			y = 0;
			x = 5;
		}
		if (gameEnd == true) {
			for (int c = 0; c < 4; c++) {
				for (int r = 0; r < 4; r++) {
					miniGrid[c][r] = Color.WHITE;

				}
			}
		}
	}

	/**
	 * This method, getShape, contains all possible rotational positions for all seven tetrominoes,
	 * and will invoke one of the statement when the method is called based on the current tetromino in play
	 * Since O-shaped have the same structure when rotated in four different positions, it does not required it's own rotation method
	 */
	private void getShape() {

		if (shape.equals("O")) {
		}
		else if (shape.equals("L")) {
			LShape();
		}
		else if (shape.equals("S")) {
			SShape();
		}
		else if (shape.equals("Z")) {
			ZShape();
		}
		else if (shape.equals("I")) {
			IShape();
		}
		else if (shape.equals("J")) {
			JShape();
		}
		else if (shape.equals("T")) {
			TShape();
		}
	}

	/**
	 * This method, LShape, stores the coordinates of the four different rotation shape of a L-shaped tetromino in an ArrayList
	 * set L-shaped tetromino is set to the color red
	 * Rotation is clockwise
	 * This method will be invoked when getShape() method is called, which is in this current class
	 */
	private void LShape() {

		switch (rotation) {
			case 1:
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 1 && r == 0) || (c == 1 && r == 2) || (c == 2 && r == 2)) {
							miniGrid[c][r] = Color.RED;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 2:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if((r==1 && c==1)||(c==2 && r==1)||(c==0 && r==1)||(c==0 && r==2)) {
							miniGrid[c][r]=Color.RED;
						}
						else{
							miniGrid[c][r]=Color.WHITE;
						}
					}
				}
				break;

			case 3:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if((r==1 && c==1)||(c==1 && r==2)||(c==1 && r==0)||(c==0 && r==0)) {
							miniGrid[c][r]=Color.RED;
						}
						else{
							miniGrid[c][r]=Color.WHITE;
						}
					}
				}

				break;

			case 4:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==2 && r==0)) {
							miniGrid[c][r] = Color.RED;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
		}
	}

	/**
	 * This method, SShape, stores the coordinates of the four different rotation shape of a S-shaped tetromino in an ArrayList,
	 * but since an S-shaped tetromino have two possible rotation placements. Thus, case 1 and case 3 are the same, similarly for case 2 and case 4
	 * S-shaped tetromino is set to the color green
	 * Rotation is clockwise
	 * This method will be invoked when getShape() method is called, which is in this current class
	 */
	private void SShape() {

		switch(rotation) {
			case 1: case 3:
				miniGrid = new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==0 && r==0)||(c==1 && r==0)||(c==2 && r==1)) {
							miniGrid[c][r] = Color.GREEN;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
			case 2: case 4:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++){
					for (int r=0; r<4; r++){
						if((r==1 && c==1)||(c==0 && r==1)||(c==0 && r==2)||(c==1 && r==0)){
							miniGrid[c][r]=Color.GREEN;
						}
						else{
							miniGrid[c][r]=Color.WHITE;
						}
					}
				}
				break;
		}
	}

	/**
	 * This method, ZShape, stores the coordinates of the four different rotation shape of a Z-shaped tetromino in an ArrayList,
	 * but since an Z-shaped tetromino have two possible rotation placements. Thus, case 1 and case 3 are the same, similarly for case 2 and case 4
	 * Z-shaped tetromino is set to the color orange
	 * Rotation is clockwise
	 * This method will be invoked when getShape() method is called, which is in this current class
	 */
	private void ZShape() {

		switch(rotation) {
			case 1: case 3:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++){
					for (int r=0; r<4; r++){
						if((r==1 && c==1)||(c==2 && r==0)||(c==1 && r==0)||(c==0 && r==1)){
							miniGrid[c][r]=Color.ORANGE;
						}
						else{
							miniGrid[c][r]=Color.WHITE;
						}
					}
				}
				break;

			case 2: case 4:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r == 1 && c == 1) || (c==0 && r==0)||(c==0 && r==1)||(c==1 && r==2)) {
							miniGrid[c][r] = Color.ORANGE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
		}
	}

	/**
	 * This method, IShape, stores the coordinates of the four different rotation shape of a I-shaped tetromino in an ArrayList,
	 * but since an I-shaped tetromino have two possible rotation placements. Thus, case 1 and case 3 are the same, similarly for case 2 and case 4
	 * I-shaped tetromino is set to the color yellow
	 * Rotation is clockwise
	 * This method will be invoked when getShape() method is called, which is in this current class
	 */
	private void IShape() {

		switch(rotation) {
			case 1: case 3:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==1 && r==3)) {
							miniGrid[c][r] = Color.YELLOW;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 2: case 4:
				miniGrid = new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==3 && r==1)) {
							miniGrid[c][r] = Color.YELLOW;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
		}
	}

	/**
	 * This method, JShape, stores the coordinates of the four different rotation shape of a J-shaped tetromino in an ArrayList
	 * J-shaped tetromino is set to the color cyan
	 * Rotation is clockwise
	 * This method will be invoked when getShape() method is called, which is in this current class
	 */
	private void JShape() {

		switch (rotation) {
			case 1:
				miniGrid = new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==2 && r==1)||(c==0 && r==1)||(c==0 && r==0)) {
							miniGrid[c][r] = Color.MAGENTA;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 2:
				miniGrid = new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==2 && r==0)) {
							miniGrid[c][r] = Color.MAGENTA;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 3:
				miniGrid=new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==0 && r==1)||(c==2 && r==1)||(c==2 && r==2)) {
							miniGrid[c][r] = Color.MAGENTA;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 4:
				miniGrid = new Color[4][4];
				for (int c=0; c<4; c++) {
					for (int r=0; r<4; r++) {
						if ((r==1 && c==1)||(c==1 && r==0)||(c==1 && r==2)||(c==0 && r==2)) {
							miniGrid[c][r] = Color.MAGENTA;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
		}

	}

	/**
	 * This method, TShape, stores the coordinates of the four different rotation shape of a T-shaped tetromino in an ArrayList
	 * T-shaped tetromino is set to the color blue
	 * Rotation is clockwise
	 * This method will be invoked when getShape() method is called, which is in this current class
	 */
	private void TShape() {

		switch (rotation) {
			case 1:
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 2 && r == 1) || (c == 1 && r == 0) || (c == 1 && r == 2)) {
							miniGrid[c][r] = Color.BLUE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 2:
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 1 && r == 2) || (c == 0 && r == 1) || (c == 2 && r == 1)) {
							miniGrid[c][r] = Color.BLUE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 3:
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 0 && r == 1) || (c == 1 && r == 0) || (c == 1 && r == 2)) {
							miniGrid[c][r] = Color.BLUE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;

			case 4:
				miniGrid = new Color[4][4];
				for (int c = 0; c < 4; c++) {
					for (int r = 0; r < 4; r++) {
						if ((r == 1 && c == 1) || (c == 1 && r == 0) || (c == 0 && r == 1) || (c == 2 && r == 1)) {
							miniGrid[c][r] = Color.BLUE;
						}
						else {
							miniGrid[c][r] = Color.WHITE;
						}
					}
				}
				break;
		}
	}
}
