//package Tetris;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

//import javax.swing.JFrame;
//import javax.swing.JPanel;

public class Gridy{
    
    public static final int WIDTH = 12;
    public static final int HEIGHT = 24;
    public static final int SQUARE_SIZE = 26;
    
    public static final int WINDOW_WIDTH = 319;
    public static final int WINDOW_HEIGHT = 670;
 
    private int x;
    private int y;
    private Color[][] box;
    private Color[][] miniGrid;
    private Boolean hitBottom=false;
    private Boolean hitSide=false;
    private Boolean hitPiece=false;
    private Boolean lineIsFull=false;
    private int lineNeedToBeClear;

    
    public Gridy(){
	
	//MiniGrid();
	
	Grid();
	//hitSide=wallCollision();
	//hitBottom=bottomCollision();
	//repaint();
	
    }
    /*
    public void MiniGrid(){
        TetrisPiece tp=new TetrisPiece();
	miniGrid=new Color[4][4];
	//tp.setMovement("w");
	miniGrid=tp.getMiniGrid();
    }
    */
    public Color[][]Grid() {
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
	box[(2)][(5)]= Color.BLACK;
	if(lineIsFull==true){
	    for(int i = 1; i < WIDTH-1; i++) {
		box[i][lineNeedToBeClear]=Color.WHITE;}
	    for(int k =HEIGHT - 3; k>=0 ;k--) {
		for(int i = 1; i < WIDTH-1; i++) {		
		    if(box[i][k]!=Color.WHITE){
			box[i][k+1]=box[i][k];
			box[i][k]=Color.WHITE;}
		}
	    }
	}
			
    
	if(hitBottom==true){	    
	    for(int i = 0; i < 4; i++) {
		for(int k = 0; k <4 ; k ++) {
		    if(miniGrid[i][k]!=Color.WHITE){
			System.out.println("hit bottom 56");
		        box[(i+x)][(k+y-2)]= Color.BLACK;						  
		    }
		}		        
	    }
	}

	return box;
    }

    
    /*
    @Override
    public void paint(Graphics g) {
	
	for(int i = 0; i < WIDTH; i++) {
	    for(int k = 0; k < HEIGHT - 1; k++) {
		g.setColor(box[i][k]);
		g.fillRect(i * SQUARE_SIZE, k * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
	    }
	}
    }
    */
    
    public boolean wallCollision(int x1, int y1,Color[][]miniGrid2) {
        //x=x1;
	//y=y1;
        //miniGrid=new Color[4][4];        
	//miniGrid=miniGrid2;
	    hitSide=false;
	    for(int i = 0; i < 4; i++) {
		for(int k = 0; k <4 ; k ++) {
		    if(miniGrid2[i][k]!=Color.WHITE){
			if(box[(i+x1)][(k+y1)]==Color.PINK){
			    System.out.println("hit border 1");
			    hitSide=true;
			    
			    return hitSide;
			}
		    }		        

		}
	    }
	    return hitSide;
	        
	}
    public Boolean getHitBottom(){
	return hitBottom;
    }
    public boolean bottomCollision(int x1, int y1, Color[][] miniGrid2){
	x=x1;
	y=y1;
        miniGrid=miniGrid2;
	hitBottom=false;
	 for(int i = 0; i < 4; i++) {
	     for(int k = 0; k < 4; k ++) {
		 if(miniGrid[i][k]!=Color.WHITE){
		     if(box[(i+x)][(k+y)]==Color.PINK){
			 System.out.println("hit bottom 2");
			 hitBottom=true;
			 box=Grid();
			 return hitBottom;
		     }
		 }		        
		 
	     }
	 }
	 return hitBottom;
    }
    
   

    public void LineRemover(){
	for(int k = HEIGHT-1; k >=0; k --) {	
	    lineIsFull=true;
	    lineNeedToBeClear=0;
	    for(int i = 0; i < WIDTH; i++) {
		if(box[i][k]==Color.WHITE){
			lineIsFull=false;
			break;
		     }		 
	     }
	    if(lineIsFull==true){
		lineNeedToBeClear=k;
		Grid();
	    }
	}
    }
    /*
	public static void main(String[] args) {
	    Gridy gr=new Gridy();
	    
	    
		JFrame f = new JFrame();
		f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		f.setTitle("RUN");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		Gridy run = new Gridy();
		run.Grid();
		f.add(run);
	    
	}
    */
}
