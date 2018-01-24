import java.util.ArrayList;
import java.util.Random;
public class TetrisPiece{
    private String shape;
    private String direction;
    private int rotation=0;
    private int _pieceX=0;
    private int _pieceY=0;
    private int P2X=0;
    private int P2Y=0;
    private int P3X=0;
    private int P3Y=0;
    private int P4X=0;
    private int P4Y=0;
    private ArrayList<Integer> location = new ArrayList<Integer>();
   
    /** This constructor takes no argument. However, every time the constructor is called, it invokes the method random in the current class 
     ** and which will generate a new Tetris piece and restores the value inside the instance variable shape. 
     */
    public TetrisPiece(){
        shape=random();
    }

    private String random(){
	Random rand=new Random();
	 int num=rand.nextInt(7);
	 if(num==0){
	     shape="O";}
	 else if(num==1){
	     shape="L";}
	 else if(num==2){
	     shape="S";}
	 else if(num==3){
	     shape="Z";}
	 else if(num==4){
	     shape="I";}
	 else if(num==5){
	     shape="J";}
	 else if(num==6){
	     shape="T";}
	 return shape;
    }

    public ArrayList<Integer> getLocation(){
	location=PieceShow();
	return location;
    }
    
    public ArrayList<Integer> getLocation(String Direction){
	location=PieceMovement(Direction);
	return location;
    }
    
    
    
    //store cordinates of 7 shapes in an array list
    private ArrayList<Integer> PieceShow(){	
	switch(shape){
	case "O":
	    _pieceX=0;
	    _pieceY=4;
	    P2X=_pieceX+1;
	    P2Y=_pieceY;
	    P3X=_pieceX;
	    P3Y=_pieceY+1;
	    P4X=_pieceX+1;
	    P4Y=_pieceY+1;
	    break;
	case "L":
	    _pieceX=1;
	    _pieceY=4;
	    P2X=_pieceX;
	    P2Y=_pieceY-1;
	    P3X=_pieceX;
	    P3Y=_pieceY+1;
	    P4X=_pieceX-1;
	    P4Y=_pieceY+1;
	    break;
	case "S":
	    _pieceX=1;
	    _pieceY=4;
	    P2X=_pieceX;
	    P2Y=_pieceY-1;
	    P3X=_pieceX+1;
	    P3Y=_pieceY-1;
	    P4X=_pieceX-1;
	    P4Y=_pieceY;
	    break;
	case "Z":
	    _pieceX=1;
	    _pieceY=4;
	    P2X=_pieceX-1;
	    P2Y=_pieceY-1;
	    P3X=_pieceX;
	    P3Y=_pieceY-1;
	    P4X=_pieceX+1;
	    P4Y=_pieceY;
	    break;
	case "J":
	    _pieceX=1;
	    _pieceY=4;
	    P2X=_pieceX-1;
	    P2Y=_pieceY;
	    P3X=_pieceX+1;
	    P3Y=_pieceY;
	    P4X=_pieceX+1;
	    P4Y=_pieceY-1;
	    break;
	case "I":
	    _pieceX=1;
	    _pieceY=4;
	    P2X=_pieceX;
	    P2Y=_pieceY-1;
	    P3X=_pieceX;
	    P3Y=_pieceY+1;
	    P4X=_pieceX;
	    P4Y=_pieceY+2;
	    break;
	case "T":
	    _pieceX=1;
	    _pieceY=4;
	    P2X=_pieceX-1;
	    P2Y=_pieceY;
	    P3X=_pieceX;
	    P3Y=_pieceY-1;
	    P4X=_pieceX;
	    P4Y=_pieceY+1;
	    break;
	}

	//update location
        location.add(_pieceX);
	location.add(_pieceY);
	location.add(P2X);
	location.add(P2Y);
	location.add(P3X);
	location.add(P3Y);
	location.add(P4X);
	location.add(P4Y);

	return (location);
    }

    //store new coordinates of shapes when pieces are moved to new coords
    private ArrayList<Integer> PieceMovement(String Direction){
	direction=Direction;
	switch (direction){
	    
	    //moves left
	case "a":
	    _pieceX=location.get(0);
	    _pieceY=location.get(1)-1;
	    P2X=location.get(2);
	    P2Y=location.get(3)-1;
	    P3X=location.get(4);
	    P3Y=location.get(5)-1;
	    P4X=location.get(6);
	    P4Y=location.get(7)-1;
	    break;
	    
	    //moves dows
	case "s":	    
	    location=dropping();
	    return location;

	    //moves right
	case "d":
	    _pieceX=location.get(0);
	    _pieceY=location.get(1)+1;
	    P2X=location.get(2);
	    P2Y=location.get(3)+1;
	    P3X=location.get(4);
	    P3Y=location.get(5)+1;
	    P4X=location.get(6);
	    P4Y=location.get(7)+1;
	    break;
        case "w":
	    if (rotation > 4){
		rotation=1;}
	    else{
		rotation=rotation+1;}
	    location=GetShape();
	    return location;
	    
	default:
	    _pieceX=location.get(0);
	    _pieceY=location.get(1)+1;
	    P2X=location.get(2);
	    P2Y=location.get(3)+1;
	    P3X=location.get(4);
	    P3Y=location.get(5)+1;
	    P4X=location.get(6);
	    P4Y=location.get(7)+1;
	    break;
	}

	//updates new location coordinates
	ArrayList<Integer> location = new ArrayList<Integer>();
	location.add(_pieceX);
	location.add(_pieceY);
	location.add(P2X);
	location.add(P2Y);
	location.add(P3X);
	location.add(P3Y);
	location.add(P4X);
	location.add(P4Y);

	return (location);
    }

    private ArrayList<Integer> dropping(){
        
	    _pieceX=location.get(0)+1;
	    _pieceY=location.get(1);
	    P2X=location.get(2)+1;
	    P2Y=location.get(3);
	    P3X=location.get(4)+1;
	    P3Y=location.get(5);
	    P4X=location.get(6)+1;
	    P4Y=location.get(7);        
	ArrayList<Integer> location = new ArrayList<Integer>();
	location.add(_pieceX);
	location.add(_pieceY);
	location.add(P2X);
	location.add(P2Y);
	location.add(P3X);
	location.add(P3Y);
	location.add(P4X);
	location.add(P4Y);

	return (location);
    }

   public ArrayList<Integer> GetShape(){
	if (shape.equals("O")){
	    location=location;
	}
	else if (shape.equals("L")){
	    location=Lshape();
	}
	else if (shape.equals("S")){
	    location=Sshape();
	}
	else if (shape.equals("Z")){
	    location=Zshape();
	}
	else if (shape.equals("I")){
	    location=Ishape();
	}
	else if (shape.equals("J")){
	    location=Jshape();
	}
	else if (shape.equals("T")){
	    location=Tshape();
	}
	return location;
    }

    private ArrayList<Integer> Lshape(){
	switch(rotation){
	case 1:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)+2;
	    P4X=location.get(7);
	    break;
	case 2:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6);
	    P4X=location.get(7)-2;
	    break;
	case 3:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)-2;
	    P4X=location.get(7);
	    break;
	case 4:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6);
	    P4X=location.get(7)+2;
	    break;
	}
	ArrayList<Integer> NewLocation = new ArrayList<Integer>();
	NewLocation.add(_pieceY);
	NewLocation.add(_pieceX);
	NewLocation.add(P2Y);
	NewLocation.add(P2X);
	NewLocation.add(P3Y);
	NewLocation.add(P3X);
	NewLocation.add(P4Y);
	NewLocation.add(P4X);
	return (NewLocation);
    }

     private ArrayList<Integer> Sshape(){
	switch(rotation){
	case 1: case 3:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3);
	    P3Y=location.get(4)-2;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)+1;
	    P4X=location.get(7)+1;
	    break;
	case 2: case 4:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3);
	    P3Y=location.get(4)+2;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)-1;
	    P4X=location.get(7)-1;
	    break;
	}
	ArrayList<Integer> NewLocation = new ArrayList<Integer>();
	NewLocation.add(_pieceY);
	NewLocation.add(_pieceX);
	NewLocation.add(P2Y);
	NewLocation.add(P2X);
	NewLocation.add(P3Y);
	NewLocation.add(P3X);
	NewLocation.add(P4Y);
	NewLocation.add(P4X);
	return (NewLocation);
    }

    private ArrayList<Integer> Zshape(){
	switch(rotation){
	case 1: case 3:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2);
	    P2X=location.get(3)+2;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)-1;
	    P4X=location.get(7)-1;
	    break;
	case 2: case 4:
	     _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2);
	    P2X=location.get(3)-2;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)+1;
	    P4X=location.get(7)+1;
	    break;
	}
	ArrayList<Integer> NewLocation = new ArrayList<Integer>();
	NewLocation.add(_pieceY);
	NewLocation.add(_pieceX);
	NewLocation.add(P2Y);
	NewLocation.add(P2X);
	NewLocation.add(P3Y);
	NewLocation.add(P3X);
	NewLocation.add(P4Y);
	NewLocation.add(P4X);
	return (NewLocation);
    }

     private ArrayList<Integer> Ishape(){
	switch(rotation){
	case 1: case 3:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)+2;
	    P4X=location.get(7)-2;
	    break;
	case 2: case 4:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)-2;
	    P4X=location.get(7)+2;
	    break;
	}
	ArrayList<Integer> NewLocation = new ArrayList<Integer>();
	NewLocation.add(_pieceY);
	NewLocation.add(_pieceX);
	NewLocation.add(P2Y);
	NewLocation.add(P2X);
	NewLocation.add(P3Y);
	NewLocation.add(P3X);
	NewLocation.add(P4Y);
	NewLocation.add(P4X);
	return (NewLocation);
    }

     private ArrayList<Integer> Jshape(){
	switch(rotation){
	case 1:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)-2;
	    P4X=location.get(7);
	    break;
	case 2:
	     _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6);
	    P4X=location.get(7)+2;
	    break;
	case 3:
	     _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)+2;
	    P4X=location.get(7);
	    break;
	case 4:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6);
	    P4X=location.get(7)-2;
	    break;
	}
	ArrayList<Integer> NewLocation = new ArrayList<Integer>();
	NewLocation.add(_pieceY);
	NewLocation.add(_pieceX);
	NewLocation.add(P2Y);
	NewLocation.add(P2X);
	NewLocation.add(P3Y);
	NewLocation.add(P3X);
	NewLocation.add(P4Y);
	NewLocation.add(P4X);
	return (NewLocation);
    }

     private ArrayList<Integer> Tshape(){
	switch(rotation){
	case 1:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)+1;
	    P4X=location.get(7)-1;
	    break;
	case 2:
	     _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)+1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)+1;
	    P4Y=location.get(6)-1;
	    P4X=location.get(7)-1;
	    break;
	case 3:
	     _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)-1;
	    P3Y=location.get(4)+1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)-1;
	    P4X=location.get(7)+1;
	    break;
	case 4:
	    _pieceY=location.get(0);
	    _pieceX=location.get(1);
	    P2Y=location.get(2)-1;
	    P2X=location.get(3)+1;
	    P3Y=location.get(4)-1;
	    P3X=location.get(5)-1;
	    P4Y=location.get(6)+1;
	    P4X=location.get(7)+1;
	    break;
	}
	ArrayList<Integer> NewLocation = new ArrayList<Integer>();
	NewLocation.add(_pieceY);
	NewLocation.add(_pieceX);
	NewLocation.add(P2Y);
	NewLocation.add(P2X);
	NewLocation.add(P3Y);
	NewLocation.add(P3X);
	NewLocation.add(P4Y);
	NewLocation.add(P4X);
	return (NewLocation);
    }
}

	
	


	



   

    

