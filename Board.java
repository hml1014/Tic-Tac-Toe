package tictactoe;

/*
 * This class constructs the GUI for
 * playing a game of Tic-Tac-Toe and
 * runs it.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame {
	private static final long serialVersionUID = 1L;
	final Container cp;
	private JButton[][] squares;
	private JPanel view, buttonPanel;
	private JButton newG;
	private TicTacToe t;
	private boolean AIturn;
	
	/*
	 * This constructor creates the Tic-Tac-Toe
	 * game GUI.
	 */
	public Board(){
		t = new TicTacToe();
		AIturn = false;
		cp = getContentPane();
	    cp.setLayout(new FlowLayout()); 
	    
	    view = new JPanel(new GridLayout(3,3,2,2));
	    view.setBackground(Color.BLACK);
	    squares = new JButton[3][3];
	    
	    for (int i=0;i<3;i++){
	    	for (int j=0;j<3;j++){
	    		JButton temp = new JButton();
	    		temp.setPreferredSize(new Dimension(90,90));
	    		temp.setBackground(Color.WHITE);
	    		temp.setFont(new Font("Century", Font.PLAIN, 70));
	    		temp.addMouseListener(click);
	    		temp.putClientProperty("Row", i);
				temp.putClientProperty("Col", j);
				squares[i][j] = temp;
				view.add(squares[i][j]);
	    	}
	    }
	    
	    cp.add(view);
	    
	    buttonPanel = new JPanel();
	    newG = new JButton("New Game");
	    newG.setEnabled(false);
	    buttonPanel.add(newG);
	    cp.add(buttonPanel);
	    
	    setSize(300,360);
	    setTitle("Tic Tac Toe");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    
	    newG.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent evt) {
	    		reset();
	    	}
	    });
	}
	
	/*
	 * This method detects a click on the board
	 * and sets the square to "X" if it isn't
	 * already marked. Then it calls the AIMove()
	 * method.
	 */
	MouseAdapter click = new MouseAdapter(){
		public void mousePressed(MouseEvent e) 
		{
			if (e.getSource() instanceof JButton) {
                Object r = ((JButton) e.getSource()).getClientProperty("Row");
                Object c = ((JButton) e.getSource()).getClientProperty("Col");
                int i = (int) r;
                int j = (int) c;
                if (t.isBlank(i, j) && AIturn == false){
                	t.setX(i, j);
                	squares[i][j].setText("X");
                	
                	if (t.isXWin()){
                		JOptionPane.showMessageDialog(null, "Congratulations, you won!");
                		newG.setEnabled(true);
                	}else if (t.isDraw()) {
                		JOptionPane.showMessageDialog(null, "It's a draw!");
                		newG.setEnabled(true);
                	}
                	else{ 
                		AIturn = true;
                		AIMove();
                	}
                }
			}
		}
	};
	
	/*
	 * This method calls functions to calculate
	 * the AI's move and then sets the AI's choice
	 * to "O".
	 */
	public void AIMove(){
		Pair p = new Pair(6,6);
		p = p.getAIMove(t);
		int i = p.getX();
		int j = p.getY();
		t.setO(i, j);
    	squares[i][j].setText("O");
    	
    	if (t.isOWin()){
    		JOptionPane.showMessageDialog(null, "Sorry, you lost. Better luck next time!");
    		newG.setEnabled(true);
    	}
    	else if (t.isDraw()) {
    		JOptionPane.showMessageDialog(null, "It's a draw!");
    		newG.setEnabled(true);
    	}
    	AIturn = false;
	}
	
	/*
	 * This method resets the board to
	 * all blanks and creates a new 
	 * TicTacToe object to play a
	 * new game.
	 */
	public void reset(){
		t = new TicTacToe();
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				squares[i][j].setText("");
			}
		}
		newG.setEnabled(false);
		AIturn = false;
	}
	
	/*
	 * This main method launches the
	 * Board GUI.
	 */
	public static void main(String[] args){
		new Board();
	}
}
