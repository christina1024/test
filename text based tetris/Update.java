import java.util.ArrayList;
    
public class Update{
    private String test="ok";
    private String test2="ok";
    private String sideCollision="ok";
    private int lineNum=0;
    private ArrayList<Integer> Location=new ArrayList<Integer>();

    /**Checking if the piece reach the bottom.
     */
    public String Check(ArrayList<Integer> Location1){
	Location=Location1;
	for (int size=0; size<=7; size++){
	    if(Location.get(size)>9){
		test="hit";
		return(test);
	    }
	    else{}
	}
	return(test);
    }
    
    /** Checking if the moving piece hit the two side of the border.
    */
    public String CheckSide(ArrayList<Integer> Location1){
	test2="ok";
	Location=Location1;
	if(Location.get(1)<0 || Location.get(1)>9){
		test2="hit";
		return(test2);
	    }
	if(Location.get(3)<0 || Location.get(3) >9){
	    test2="hit";
	    return(test2);}
	if(Location.get(5)<0 || Location.get(5) >9){
	    test2="hit";
	    return(test2);}
	
	if(Location.get(7)<0 || Location.get(7)>9){
		test2="hit";
		return(test2);
	    }
	else{
	    return(test2);
	}
    }

    /** Checking the collision between the moving piece and the other settled pieces.
     */
    public String SideCollision(char[][] grid, ArrayList<Integer> TLocation){
	 sideCollision="ok";
	for (int row = 0; row < 10; row++) {
		for (int column = 0; column < 10; column++) {		    
		    if(grid[row][column]=='F' && TLocation.get(0)==row && TLocation.get(1)==column){			
			sideCollision="hit";
			return sideCollision;
		    }
		    if(grid[row][column]=='F' && TLocation.get(2)==row && TLocation.get(3)==column){
			sideCollision="hit";
			return sideCollision;
			}
		    if(grid[row][column]=='F' && TLocation.get(4)==row && TLocation.get(5)==column){
			sideCollision="hit";
			return sideCollision;
			}
		    if(grid[row][column]=='F' && TLocation.get(6)==row && TLocation.get(7)==column){
			sideCollision="hit";
			return sideCollision;
			}
		    }
	}
	return sideCollision;
    }


    public int fullLineRemoval(char[][] grid){
	boolean lineIsFull;
	int row=9;
	while(row>0){
	    for (row = 9; row >= 0; row--){
		lineIsFull = true;
		for (int column = 0; column < 10; column++){
		    if (grid[row][column] == '*'){
			lineIsFull = false;
			break;
		    }
		}
		if (lineIsFull){
		    lineNum=lineNum+1;
		    for( int column2 =0;column2<10; column2++){
			grid[row][column2]='*';
		    }
		    for (int i = row; i > 0; i--){
			for (int j = 0; j < 10; j++){
			    if(grid[i][j]=='F'){
				grid[i][j]='*';
				int newi=i+1;
				grid[newi][j]='F';
			    }
			}
		    }
		    break;
		}
	    }
	}
	return lineNum;
    }
    
}
