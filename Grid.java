import java.awt.Color;

/**
 * This class, Grid, updates the grid, checks for collision to the side walls and the bottom wall, as well as the top wall
 * It also check for full lines and removes them accordingly
 *
 * @author Group 32
 *
 */
public class Grid {

	public static final int WIDTH = 12;
	public static final int HEIGHT = 24;
	public static final int SQUARE_SIZE = 26;

	private static Boolean gameEnd;
	private Boolean hitBottom;
	private Boolean hitSide = false;
	private Boolean hitPiece = false;
	private Boolean lineIsFull = false;

	private int x;
	private int y;

	private static Color[][] box;
	private static Color[][] grid;
	private Color[][] miniGrid;

	private int lineNeedToBeClear;

	private static int score = 0 ;


	/**
	 *
	 */
	public Grid() {

		gameEnd = false;
		Grid();
	}

	/**
	 * Updates the grid
	 *
	 * @return This returns grid
	 */
	public Color[][] getUpdate() {

		return grid;
	}

	/**
	 * getter for score
	 *
	 * @return This returns score
	 */
	public int getScore() {

		return score;
	}

	/**
	 * getter for gameEnd
	 *
	 * @return This returns gameEnd
	 */
	public Boolean getEndGame() {

		return gameEnd;
	}

        public void setScore(int score1){
	    score=score1;
	}

	/**
	 * This method, Grid, for graphical purpose, the borders are pink and the remaining grid is white
	 */
	public void Grid() {

		box = new Color[WIDTH][HEIGHT];
		for(int i = 0; i < WIDTH; i++) {
			for(int k = 0; k < HEIGHT - 1; k++) {
				if(i == 0 || i == WIDTH - 1 || k == HEIGHT - 2) {
					box[i][k] = Color.PINK;
				}
				else {
					box[i][k] = Color.WHITE;
				}
			}
		}
		grid = new Color[WIDTH][HEIGHT];
		for (int i = 0; i < WIDTH; i++) {
			for (int k = 0; k < HEIGHT - 1; k++) {
				grid[i][k] = box[i][k];

			}
		}
	}

	/**
	 * This method, update, remove the lines and dropping the following above lines down by how many lines were removed
	 *
	 * @param height This is an int parameter to update method
	 */
	public void update (int height) {

		lineNeedToBeClear = height;
		if (lineIsFull == true){
			for (int i = 1; i < WIDTH - 1; i++) {
				grid[i][lineNeedToBeClear] = Color.WHITE;
			}
			for (int k = lineNeedToBeClear; k >= 0 ;k--) {
				for (int i = 1; i < WIDTH - 1; i++) {
					if (grid[i][k] == Color.BLACK){
						grid[i][k] = Color.WHITE;
						grid[i][k+1] = Color.BLACK;

					}
				}
			}
		}
	}

	/**
	 * This method, update,
	 *
	 * @param x This is an int parameter to update method
	 * @param y This is an int parameter to update method
	 * @param miniGrid2 This is a Color array parameter to update method
	 */
	public void update (int x, int y, Color[][] miniGrid2) {

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k++) {
				if (miniGrid2[i][k] != Color.WHITE) {
					grid[(i + x)][(k + y - 1)] = Color.BLACK;
				}

			}
		}
	}

	/**
	 * This method, wallCollision, takes in three arguments to check if a tetromino has hit either side of the border
	 *
	 * @param x This is an int parameter to wallCollision method
	 * @param y This is an int parameter to wallCollision method
	 * @param miniGrid2 This is a Color array parameter to wallCollision method
	 * @return This returns hitSide
	 */
	public boolean wallCollision (int x, int y, Color[][]miniGrid2) {

		miniGrid = miniGrid2;
		hitSide = false;
		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4 ; k ++) {
				if (miniGrid[i][k] != Color.WHITE){
					if (grid[(i+x)][(k+y)] == Color.PINK || grid[(i + x)][(k + y)] == Color.BLACK) {
						hitSide = true;
						return hitSide;
					}
				}

			}
		}
		return hitSide;

	}

	/**
	 * This method, bottomCollision, takes in three arguments to check if a tetromino has hit the bottom border
	 *
	 * @param x1 This is an int parameter to bottomCollision method
	 * @param y 1This is an int parameter to bottomCollision method
	 * @param miniGrid2 This is a Color array parameter to bottomCollision method
	 * @return This returns hitBottom
	 */
	public boolean bottomCollision (int x1, int y1, Color[][] miniGrid2) {

		x = x1;
		y = y1;
		miniGrid = miniGrid2;
		hitBottom = false;

		for (int i = 0; i < 4; i++) {
			for (int k = 0; k < 4; k ++) {
				if (miniGrid[i][k] != Color.WHITE) {
					if (grid[(i + x)][(k + y)] == Color.PINK || grid[(i + x)][(k + y)] == Color.BLACK) {
						hitBottom = true;
						return hitBottom;
					}
				}

			}
		}
		return hitBottom;
	}

	/**
	 * This method, endGame, will be invoked when the boolean of gameEnd is true
	 */
	public void endGame() {

		for (int i = 1; i < WIDTH - 1; i++) {
			if (grid[i][1] == Color.BLACK) {
				gameEnd = true;
			}
		}
	}

	/**
	 * This method, lineRemover, check and counts if the rows are full that contains the black coloured tetorminoes,
	 * the score is also calculated as it checks for full rows
	 * Moving lines down by how many lines and  removal, is in update(int) method in the current class
	 *
	 * @see #update(int)
	 */
	public void lineRemover() {

		int k = 1;

		for (k = HEIGHT - 1; k >= 0; k --) {
			lineIsFull = true;
			lineNeedToBeClear = 0;
			for (int i = 1; i < WIDTH - 1; i++) {
				if (grid[i][k] != Color.BLACK) {
					lineIsFull = false;
					break;
				}
			}
			if (lineIsFull == true) {
				score = score + 1;
				lineNeedToBeClear = k;
				update(k);
				lineRemover();
			}
		}
	}
}
