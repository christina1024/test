import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class test extends JFrame{
    private ArrayList<Integer> location = new ArrayList<Integer>();
    private String dr="w";
    private String shape;
    public static void main(String[] args){
	test t1=new test();
	t1.setVisible(true);
	t1.play();
    }
    public test(){
	setSize(200,400);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
}
    private void play(){
	TetrisPiece tp=new TetrisPiece();
	location=tp.getLocation();
        repaint();
	Timer timer = new Timer(200,
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
		    location=tp.getLocation(dr);
                    repaint();
                }
            });
        timer.setInitialDelay(1000);
        timer.start();
	          
    }

    public void paint(Graphics canvas){
	super.paint(canvas);
        canvas.setColor(Color.ORANGE);
	canvas.fillRect(location.get(1)*20,location.get(0)*20, 20, 20);
	canvas.fillRect(location.get(3)*20,location.get(2)*20, 20, 20);
	canvas.fillRect(location.get(5)*20,location.get(4)*20,20, 20);
	canvas.fillRect(location.get(7)*20,location.get(6)*20,20,20);
	canvas.setColor(Color.BLACK);
	canvas.drawRect(location.get(1)*20,location.get(0)*20, 20, 20);
	canvas.drawRect(location.get(3)*20,location.get(2)*20, 20, 20);
	canvas.drawRect(location.get(5)*20,location.get(4)*20,20, 20);
	canvas.drawRect(location.get(7)*20,location.get(6)*20,20,20);
    }
}
