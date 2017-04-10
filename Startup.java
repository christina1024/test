import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Startup extends JFrame implements ActionListener, KeyListener{
    public static final int WIDTH = 12;
    public static final int HEIGHT = 24;
    public static final int SQUARE_SIZE = 26;
    
    public static final int WINDOW_WIDTH = 319;
    public static final int WINDOW_HEIGHT = 670;
 
    private int x=2;
    private int y=2;
    private int score;
    private int dropSpeed=500 ;

    private Color[][] miniGrid;
    private Color[][] grid;

    Gridy gr=new Gridy();
    TetrisPiece tp=new TetrisPiece();


    public Startup(){
	addKeyListener(this);
	miniGrid=tp.getMiniGrid();      
	x=tp.getX();
	y=tp.getY();
	grid=gr.getUpdate();
	repaint();
	 ActionListener listener = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
		  score=gr.getScore();
		  tp.dropping();	  
		  miniGrid=tp.getMiniGrid();
	    
		  x=tp.getX();
		  y=tp.getY();
		  
		  grid=gr.getUpdate();
		  
		  repaint();
	      
	      }
	     };
        
	 Timer timer=new Timer(dropSpeed, listener);       
	 timer.start();
	 setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 setLocationRelativeTo(null);
	 setVisible(true);
	
    }
    public void actionPerformed(ActionEvent n){
	miniGrid=tp.getMiniGrid();
	
	x=tp.getX();
	y=tp.getY();
	
	grid=gr.getUpdate();
	repaint();
      
      
    }


    public void keyPressed(KeyEvent l){
      int k = l.getKeyCode();
      if(k == KeyEvent.VK_DOWN){
	  String move="s";
	  tp.setMovement(move);
      }
      if(k == KeyEvent.VK_LEFT){
        String move="a";
	tp.setMovement(move);
      }
      if(k == KeyEvent.VK_RIGHT){
        String move="d";
	tp.setMovement(move);
      }
      if(k == KeyEvent.VK_UP){
        String move="w";
	tp.setMovement(move);
      }
    }

    public void keyTyped(KeyEvent l){}
    public void keyReleased(KeyEvent l){}

    //@Override{
	public void paint(Graphics g) {
	    super.paint(g);	    
        
	for(int i = 0; i < WIDTH; i++) {
	    for(int k = 0; k < HEIGHT; k++) {
        
		g.setColor(grid[i][k]);
		g.fillRect(i * SQUARE_SIZE, k * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
	    }
	    
	}
	
	for(int i = 0; i < 4; i++) {
	    for(int k = 0; k < 4; k++) {
		if(miniGrid[i][k]!=Color.WHITE){
		    g.setColor(miniGrid[i][k]);
		    g.fillRect((i+x)* SQUARE_SIZE, (k+y) * SQUARE_SIZE, HEIGHT + 1, HEIGHT + 1);
		}
	    }
	}
	Font currentFont = g.getFont();
	Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.8F);
	g.setFont(newFont);
	g.setColor(Color.BLACK);
	g.drawString(""+(score*100),250,50);
    }

    public static void main(String[] args){
	
	EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Startup();
            }
	    });
        }        
    
}
