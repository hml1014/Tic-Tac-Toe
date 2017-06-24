package tictactoe;

/*
 * This class creates Node objects used
 * for constructing a Min-Max game tree
 * and contains methods for calculating
 * the AI's move
 */

import java.util.LinkedList;

public class Node {
	private TicTacToe game;
	private Node parent;
	private LinkedList<Node> children; 
	private int score;
	private Pair coords;
	
	/*
	 * This constructor creates a
	 * Node containing a TicTacToe
	 * board state.
	 */
	public Node(TicTacToe game){
		children = new LinkedList<Node>();
		this.game = game;
		this.score=2000;
		this.coords = new Pair(-6,-6);
	}

	public TicTacToe getGame() {
		return game;
	}

	public Node getParent() {
		return parent;
	}

	public LinkedList<Node> getChildren() {
		return children;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Pair getCoords() {
		return coords;
	}

	public void setCoords(Pair coords) {
		this.coords = coords;
	}
	
	/*
	 * This method takes in a Node and adds
	 * it to the children LinkedList of another
	 * Node. It then assigns the Node as the
	 * parent Node of the input Node.
	 */
	public void addChild(Node n) {
		n.parent = this;
		this.children.add(n);
	}
	
	/*
	 * This method returns true if a Node
	 * has a parent Node.
	 */
	public boolean hasParent() {
		return this.parent!=null;
	}
	
	/*
	 * This method returns true if a Node
	 * has child Nodes in its children
	 * LinkedList.
	 */
	public boolean hasChildren() {
		return this.children.size() > 0;
	}
	
	/*
	 * This method returns true if a Node
	 * has a child Node that has child Nodes
	 * in its own children LinkedList.
	 */
	public boolean hasGrandChildren() {
		LinkedList<Node> n = getChildren();
		for (int i=0;i<n.size();i++)
		{
			if (n.get(i).hasChildren()) return true;
		}
		return false;
	}
	
	/*
	 * This method takes in a Node
	 * and returns an int representing
	 * the Node's distance from the
	 * root Node.
	 */
	private int getLevel(Node n){
    	if (n==null) return 0;
    	return 1 + getLevel(n.parent);
	}

	/*
	 * This method creates a tree of Nodes
	 * with all the possible game moves given
	 * an input current game state.
	 */
	public void createTree(Node n, boolean o){
		TicTacToe t = n.getGame();
		if (t.isXWin()){
			n.setScore(-100);
		}
		else if (t.isOWin()){
			n.setScore(100- getLevel(n));
		}
		else if (t.isDraw()){
			n.setScore(0);
		}
		else{
			for (int i=0;i<3;i++){
				for (int j=0;j<3;j++){
					if (t.isBlank(i, j)){
						TicTacToe tempT = t.clone();
						if (o) tempT.setO(i, j);
						else tempT.setX(i, j);
						Node tempN = new Node(tempT);
						Pair p = new Pair(i,j);
						tempN.setCoords(p);
						n.addChild(tempN);
						createTree(tempN, !o);
					}
				}
			}
		}
	}
	
	/*
	 * This method searches through a Node
	 * min max tree until it finds the 
	 * parent Nodes of all the leaf Nodes.
	 * Then it calls the setAllScores method
	 * to assign the values of all the Nodes.
	 */
	public void findSecondToLast(Node n){
		 if (n.hasChildren()){
			 if(n.hasGrandChildren()){
				 LinkedList<Node> l = n.getChildren();
				 for (int i=0;i<l.size();i++){
					 Node t = l.get(i);
					 findSecondToLast(t);
				 }
			 } else setAllScores(n);
		 }
	 }
	
	/*
	 * This method assigns the score values
	 * of all the Nodes in the min max tree
	 * by calculating their distance from
	 * the root node and then selecting either
	 * the lowest or highest score of their
	 * child Nodes.
	 */
	public void setAllScores(Node n){
		 int level = getLevel(n);
		 if (n.hasParent()){
			 if (level%2 == 0){
				 setToMinValue(n);
				 setAllScores(n.getParent());
			 } else {
				 setToMaxValue(n);
				 setAllScores(n.getParent());
			 }
		 }
	}
	
	/*
	 * This method takes in a Node and searches
	 * its child Nodes for the lowest score.
	 * Then it sets the parent Node's score
	 * to the lowest score.
	 */
	public void setToMinValue(Node n) {
		 LinkedList<Node> l = n.getChildren();
		 int min = l.get(0).getScore();
		 for (int i=0;i<l.size();i++){
			 int t = l.get(i).getScore();
			 if (t<min) min = t;
		 }
		 n.setScore(min);
	 }
	
	/*
	 * This method takes in a Node and searches
	 * its child Nodes for the highest score.
	 * Then it sets the parent Node's score
	 * to the highest score.
	 */
	 public void setToMaxValue(Node n) {
		 LinkedList<Node> l = n.getChildren();
		 int max = l.get(0).getScore();
		 for (int i=0;i<l.size();i++){
			 int t = l.get(i).getScore();
			 if (t>max) max = t;
		 }
		 n.setScore(max);
	 }
	 
	 /*
	  * This method takes in a Node and
	  * searches its children for which
	  * child Node has the highest Score.
	  * Then it returns the coords Pair
	  * of the child Node.
	  */
	 public Pair selectMax(Node n){
		 LinkedList<Node> l = n.getChildren();
		 Pair p = l.get(0).getCoords();
		 int max = l.get(0).getScore();
		 for (int i=0;i<l.size();i++){
			 int t = l.get(i).getScore();
			 if (t>max) p = l.get(i).getCoords();
		 }
		 return p;
	 }	
}
