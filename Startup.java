import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startup extends JFrame{
    public static final int WIDTH = 12;
    public static final int HEIGHT = 24;
    public static final int SQUARE_SIZE = 26;
    
    public static final int WINDOW_WIDTH = 319;
    public static final int WINDOW_HEIGHT = 670;
 
    private int x=2;
    private int y=2;
    private Color[][] box;
    private Color[][] miniGrid;
    Gridy gr;
    public Startup(){
        //Gridy gr=new Gridy();
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	//setTitle("RUN");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void start(){
	Gridy gr=new Gridy();
	TetrisPiece tp=new TetrisPiece();
	miniGrid=tp.getMiniGrid();
	x=tp.getX();
	y=tp.getY();
	box=gr.Grid();
	repaint();
	Timer timer = new Timer(200,
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
					//Boolean hit=gr.getHitBottom();
					//if(hit==true){
					    //TetrisPiece tp=new TetrisPiece();
					//}
	    tp.dropping();
	    //tp.setMovement("d");
	    miniGrid=tp.getMiniGrid();	    
	    x=tp.getX();
	    y=tp.getY();
	    box=gr.Grid();
	   
	    
	    repaint();
	}
	});
	timer.setInitialDelay(1000);
	timer.start();
	
    }

    //@Override
    public void paint(Graphics g) {
	super.paint(g);	
	for(int i = 0; i < WIDTH; i++) {
	    for(int k = 0; k < HEIGHT ; k++) {
		g.setColor(box[i][k]);
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
    }
    
    
    public static void main(String[] args){
	Startup st=new Startup();        	    	    

	
	//f.setResizable(false);
	//st.setLocationRelativeTo(null);
	st.setVisible(true);
	st.start();
	//Gridy run = new Gridy();
	//run.Grid();
	//f.add(run);
    }
}
