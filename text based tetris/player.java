/** 

THIS ONLY METHOD ONLY WORKS FOR A 10X10 GRID

Text based tetris

Movements are the following:
a: move left
s: move down
d: move right

counts number of turns

there are 7 pieces: box shape, L shape, mirrored L shape, S shape, mirrored S shape, T shape, and the I shape

*/
import java.util.ArrayList;
import java.util.Scanner;
public class player{
    
    private static int score = 0;
    private String W = "W";
    private String D = "D";
    private String S = "S";
    private String A = "A";
    private int row = 0;
    
    private int lineNum;
    private String checkBorder="ok";
    private String sideCollision="ok";
    private String checkSide="ok";
    private String bottomCollision="ok";
        
    private int column = 0;
    private char[][] grid = startConfig;
        
    TetrisPiece piece=new TetrisPiece();
    EndGame eg=new EndGame();
    Update update=new Update();
   
	
    //prints grid
  static char[][] startConfig = {
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'},
    {'*','*','*','*','*','*','*','*','*','*'}
  };

   
    static ArrayList<Integer> NLocation=new ArrayList<Integer>();
    
    //user input, update location on grid, changes * to P
    public void player(){        
	Scanner user = new Scanner(System.in);   
	System.out.println();
	for (row = 0; row < 10; row++) {
	    for (column = 0; column < 10; column++) {	 
		ArrayList<Integer> TLocation=new ArrayList<Integer>();
		TLocation= piece.getLocation();	 
		grid[TLocation.get(0)][TLocation.get(1)] = 'P';
		grid[TLocation.get(2)][TLocation.get(3)] = 'P';
		grid[TLocation.get(4)][TLocation.get(5)] = 'P';
		grid[TLocation.get(6)][TLocation.get(7)] = 'P';
		System.out.print(grid[row][column]);
		NLocation=TLocation;
	    }
	    System.out.println();
	}

	while(checkBorder.equals("ok")&& bottomCollision.equals("ok")){	      
	    System.out.println();
	    System.out.println("Score "+score);
	    System.out.print("Enter either A/S/D/W: ");
	    String move = user.next();
	    move = move.toUpperCase();

	    //moves piece right
	    if(move.equals(D)){      
		Right();	        
	    }

	    //moves piece down
	    else if(move.equals(S)){
		Down();		    		
	    }

	    //moves left
	    else if(move.equals(A)){	
		Left();
	    }

	    else if(move.equals(W)){	
		Rotation();
	    }

	    //input validation
	    else{
		System.out.println();
		System.out.println("Input not valid, Enter either A/S/D");
		System.out.println(" ");
	    }

	    //updates grid
	    for (row = 0; row < 10; row++) {
		for (column = 0; column < 10; column++) {
		    System.out.print(grid[row][column]);
		}
		System.out.println();
	    }  
	}
	if (eg.endGame(grid)){	
	    lineNum=update.fullLineRemoval(grid);
	    score=100*lineNum+score;
	    player p1=new player();
	    p1.player();
	    System.out.println();
	}
	else{
	    System.out.println("your score is "+score);
	    score=0;
	}
    }
    
    private void Left(){
	String move="a";
	ArrayList<Integer> TLocation=new ArrayList<Integer>();
	TLocation=piece.getLocation(move);
	checkSide=update.CheckSide(TLocation);
	sideCollision=update.SideCollision(grid,TLocation);
	
	if (checkSide.equals("ok")&&sideCollision.equals("ok")){
	    printLetter(TLocation);
	}
	else if(checkSide.equals("hit")||sideCollision.equals("hit")){
	    ArrayList<Integer> SLocation=new ArrayList<Integer>();
	    SLocation=TLocation;
	    move="d";
	    NLocation=piece.getLocation(move);
	    }
    }
    
    private void Right(){
    	String move="d";
	ArrayList<Integer> TLocation=new ArrayList<Integer>();
	TLocation=piece.getLocation(move);	
	checkSide=update.CheckSide(TLocation);
	sideCollision=update.SideCollision(grid,TLocation);
	if (checkSide.equals("ok")&&sideCollision.equals("ok")){
	    printLetter(TLocation);
	}
	else if(checkSide.equals("hit")||sideCollision.equals("hit")){
	    move="a";
	    NLocation=piece.getLocation(move);
	    }
    }

    private void Rotation(){
    	String move="w";
	ArrayList<Integer> TLocation=new ArrayList<Integer>();
	TLocation=piece.getLocation(move);	
	checkSide=update.CheckSide(TLocation);
	sideCollision=update.SideCollision(grid,TLocation);
	if (checkSide.equals("ok")&&sideCollision.equals("ok")){
	    printLetter(TLocation);
	}
	else if(checkSide.equals("hit")||sideCollision.equals("hit")){
	    move="w";
	    NLocation=piece.getLocation(move);
	    }
    }
    
    private void Down(){
	String move="s";
	ArrayList<Integer> TLocation=new ArrayList<Integer>();
	TLocation=piece.getLocation(move);		
	checkBorder=update.Check(TLocation);
	bottomCollision=update.SideCollision(grid,TLocation);
	if (checkBorder.equals("ok")&& bottomCollision.equals("ok")){
	    printLetter(TLocation);
	}
	
	else if(checkBorder.equals("hit")|| bottomCollision.equals("hit")){
	    grid[NLocation.get(0)][NLocation.get(1)] = 'F';
	    grid[NLocation.get(2)][NLocation.get(3)] = 'F';
	    grid[NLocation.get(4)][NLocation.get(5)] = 'F';
	    grid[NLocation.get(6)][NLocation.get(7)] = 'F';
	    NLocation=TLocation;
	}
    }

    private void printLetter(ArrayList<Integer>TLocation){
	grid[NLocation.get(0)][NLocation.get(1)] = '*';
	    grid[NLocation.get(2)][NLocation.get(3)] = '*';
	    grid[NLocation.get(4)][NLocation.get(5)] = '*';
	    grid[NLocation.get(6)][NLocation.get(7)] = '*';
		    
	    grid[TLocation.get(0)][TLocation.get(1)] = 'P';
	    grid[TLocation.get(2)][TLocation.get(3)] = 'P';
	    grid[TLocation.get(4)][TLocation.get(5)] = 'P';
	    grid[TLocation.get(6)][TLocation.get(7)] = 'P';
	    NLocation=TLocation;}
    
}
