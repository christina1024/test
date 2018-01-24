public class EndGame{
    private Boolean test=true;
    public boolean endGame(char[][] grid){
	for (int column = 0; column < 10; column++) {
	    if(grid[0][column]=='F'){
		test=false;
		System.out.println("end game");
		for (int row=0; row<10; row++){
		    for(column=0; column<10; column++){
			grid[row][column]='*';
		    }
		}
		return test;
	    }
	}
	return test;
    }

}
