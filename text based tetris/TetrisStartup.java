import java.util.Scanner;
public class TetrisStartup{
    
    private String check="start";
    Scanner scan = new Scanner(System.in);

    
    public static void main(String[] args){
	TetrisStartup ne1=new TetrisStartup();
	ne1.Startup();
    }

    //This method provides user the option to start the game and quit the game.
    public void Startup(){
	while (check.equals("start")){
	    check="start";
	    System.out.println("\nWelcome to Tetris!\n Press p to play\n Press q to quit");
	    String input = scan.next();
	    if (input.equals("p")){
		Start();
	    }
	    else if(input.equals("q")){	    
		check=quit();
	    }	    
	    else{
		System.out.println("Invalid input");
	    }
	}
    }

    
    public String quit(){
	while(true){
	    System.out.println("\nAre you sure you want to quit the game? \n Enter q to quit \n Enter no to back to the previous page.");		    
	    String check2=scan.next();
	    if(check2.equals("q")){
		check="end";
		return check;
	    }
	    else if(check2.equals("no")){	    
		return check;
	    }
	    else{
		System.out.println("Invalid input");
	    }
	}
    }
    public void Start(){
	player play=new player();	
	play.player();
        
    }
}
