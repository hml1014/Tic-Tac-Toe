package tictactoe;

/*
 * This class is used to replicate a
 * Tic-Tac-Toe board and contains methods
 * to implement a game.
 */

public class TicTacToe {
	private String[][] squares;
	
	/*
	 * This constructor uses a double
	 * array to replicate a Tic-Tac-Toe
	 * board and sets the initial state
	 * of the board to being all blank.
	 */
	public TicTacToe(){
		squares = new String[3][3];
		
		reset();
	}
	
	public String[][] getSquares() {
		return squares;
	}

	/*
	 * This method takes in "coordinates"
	 * as two ints and places an "X"
	 * on the board at the given location.
	 */
	public void setX(int i, int j){
		squares[i][j] = "X";
	}
	
	/*
	 * This method takes in "coordinates"
	 * as two ints and places an "O"
	 * on the board at the given location.
	 */
	public void setO(int i, int j){
		squares[i][j] = "O";
	}
	
	/*
	 * This method takes in "coordinates"
	 * as two ints and returns true if there
	 * is an "X" at the given location.
	 */
	public boolean isX(int i, int j){
		String s = squares[i][j];
		return (s.equals("X"));
	}
	
	/*
	 * This method takes in "coordinates"
	 * as two ints and returns true if there
	 * is an "O" at the given location.
	 */
	public boolean isO(int i, int j){
		String s = squares[i][j];
		return (s.equals("O"));
	}
	
	/*
	 * This method takes in "coordinates"
	 * as two ints and returns true if there
	 * is an "X" or an "O" at the given 
	 * location.
	 */
	public boolean isMarked(int i, int j){
		String s = squares[i][j];
		return (s.equals("X") || s.equals("O"));
	}
	
	/*
	 * This method takes in "coordinates"
	 * as two ints and returns true if there
	 * is neither an "X" or an "O" at the
	 * given location.
	 */
	public boolean isBlank(int i, int j){
		String s = squares[i][j];
		return (s.equals(" "));
	}
	
	/*
	 * This method takes the "coordinates" of a row,
	 * column, or diagonal cross and returns true if
	 * the row, column, or diagonal cross has at least
	 * one "X" and one "O".
	 */
	public boolean isBlocked(int i, int j, int i2, int j2, int i3, int j3){
		int xCount = 0;
		int oCount = 0;
		
		if (isX(i,j)) xCount++;
		else if (isO(i,j)) oCount++;
		
		if (isX(i2,j2)) xCount++;
		else if (isO(i2,j2)) oCount++;
		
		if (isX(i3,j3)) xCount++;
		else if (isO(i3,j3)) oCount++;
		
		return (xCount >= 1 && oCount >= 1);
	}
	
	/*
	 * This method resets the board to be
	 * entirely blank.
	 */
	public void reset(){
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				squares[i][j] = " ";
			}
		}
	}
	
	/*
	 * This method returns true if any row,
	 * column, or diagonal cross contains
	 * three "X"s. 
	 */
	public boolean isXWin(){
		if (isX(0,0) && isX(0,1) && isX(0,2)) return true;
		if (isX(1,0) && isX(1,1) && isX(1,2)) return true;
		if (isX(2,0) && isX(2,1) && isX(2,2)) return true;
		if (isX(0,0) && isX(1,0) && isX(2,0)) return true;
		if (isX(0,1) && isX(1,1) && isX(2,1)) return true;
		if (isX(0,2) && isX(1,2) && isX(2,2)) return true;
		if (isX(0,0) && isX(1,1) && isX(2,2)) return true;
		if (isX(0,2) && isX(1,1) && isX(2,0)) return true;
		return false;
	}
	
	/*
	 * This method returns true if any row,
	 * column, or diagonal cross contains
	 * three "O"s. 
	 */
	public boolean isOWin(){
		if (isO(0,0) && isO(0,1) && isO(0,2)) return true;
		if (isO(1,0) && isO(1,1) && isO(1,2)) return true;
		if (isO(2,0) && isO(2,1) && isO(2,2)) return true;
		if (isO(0,0) && isO(1,0) && isO(2,0)) return true;
		if (isO(0,1) && isO(1,1) && isO(2,1)) return true;
		if (isO(0,2) && isO(1,2) && isO(2,2)) return true;
		if (isO(0,0) && isO(1,1) && isO(2,2)) return true;
		if (isO(0,2) && isO(1,1) && isO(2,0)) return true;
		return false;
	}
	
	/*
	 * This method returns true if every row,
	 * column, and diagonal cross is blocked,
	 * i.e., there each one has at least one 
	 * "X" and one "O".
	 */
	public boolean isDraw(){
		return (isBlocked(0,0,0,1,0,2) && isBlocked(1,0,1,1,1,2) && isBlocked(2,0,2,1,2,2) && isBlocked(0,0,1,0,2,0) 
				&& isBlocked(0,1,1,1,2,1) && isBlocked(0,2,1,2,2,2) && isBlocked(0,0,1,1,2,2) && isBlocked(0,2,1,1,2,0)); 
	}
	
	/*
	 * This method creates a new TicTacToe
	 * object that has all the same marks
	 * as another TicTacToe object.
	 */
	public TicTacToe clone(){
		TicTacToe t = new TicTacToe();
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				if (isX(i,j)) t.setX(i,j);
				else if (isO(i,j)) t.setO(i,j);
			}
		}
		return t;
	}
}
