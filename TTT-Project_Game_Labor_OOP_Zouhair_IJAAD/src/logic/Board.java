package logic;

/**
 *
 * Class Board
 *
 */
public class Board{

	public static final int SIZE = 3;
	Square[][] myBoard = new Square[SIZE][SIZE];
	
	/**
	 * Constructor of the Board class
	 */
	public Board() {
		for(int i = 0; i < SIZE ; i++) {
			for(int j = 0 ; j < SIZE ; j++) {
				this.myBoard[i][j] = new Square(i, j); 
			}
		}
	}
	
	// gets the square at the position(i,j) in the Board 
	/**
	 * @param i
	 * @param j
	 * @return the square at the position(i,j)
	 */
	public Square get(int i, int j) {	
		return this.myBoard[i][j];
	}

	/**
	 * @param sign
	 * @return true if the player/Computer has won, otherwise false.
	 * the method checks if there are 3 consecutive squares that have the same value on each horizontal, vertical, diagonal and anti-diagonal line
	 */
	public boolean checkIfWin(char sign) {
	
		//check horizontal
		for(int i=0; i<this.myBoard.length; i++) {
			int count = 0;
			if(this.myBoard[i][0].getValue()!= sign)
				continue;
			
			for(int j=0; j<this.myBoard[0].length; j++) {
				if(this.myBoard[i][j].getValue()==sign)
					count++;			
				if(count==3)
					return true;
			}
		}
		
		//check vertical
		for(int j=0; j<this.myBoard.length; j++) {
			int count = 0;
			if(this.myBoard[0][j].getValue()!=sign)
				continue;
			
			for(int i=0; i<this.myBoard[0].length; i++) {
				if(this.myBoard[i][j].getValue()==sign)
					count++;			
				if(count==3)
					return true;
			}
		}
		
		
		// check diagonal
		int count = 0;
		for(int i=0,j=0; (i<SIZE && j<SIZE); i++,j++) {		
			if(this.myBoard[i][j].getValue()==sign)
				count++;		
			if(count==3)
				return true;
		}
		
		// check anti-diagonal
		if(this.myBoard[0][2].getValue()==sign && this.myBoard[1][1].getValue()==sign && this.myBoard[2][0].getValue()==sign)
			return true;
		
		return false;
	}	
}
