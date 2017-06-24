package tictactoe;

/*
 * This class serves as an object
 * to store "coordinates" for the
 * Tic-Tac-Toe board.
 */

public class Pair {
	private int x;
	private int y;
	
	/*
	 * This constructor initializes a Pair
	 * object that holds coordinates for
	 * the Tic Tac Toe board.
	 */
	public Pair(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/*
	 * This class takes in a Tic Tac Toe board
	 * and calculates the AI opponent's best
	 * next move. It then returns the coordinates
	 * of the square the AI should pick next.
	 */
	public Pair getAIMove(TicTacToe t){
		TicTacToe tic = t.clone();
		Node n = new Node(tic);
		n.createTree(n,true);
		n.findSecondToLast(n);
		Pair p = n.selectMax(n);
		return p;
	}
}
