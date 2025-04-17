package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

import logic.Board;
import logic.Player;
import logic.Square;

/**
 *
 * Class BoardJPanel, extends JPanel-class, and implements two interfaces, MouseListener and ComponentListener
 *
 */
public class BoardJPanel extends JPanel implements MouseListener, ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board myBoard = new Board();

	private Player player = new Player("PLAYER-1", 'X', Color.BLUE);
	private Player computer = new Player("Computer", 'O', Color.orange);

	private int squareWidth;
	private int squareHeight;

	private boolean gameover;
	private boolean won;
	private boolean tie;
	
	// An ArrayList containing the GUI coordinates(pixels) of the board's squares
	private ArrayList<Point> coordinates = new ArrayList<Point>(Board.SIZE); 
	private int squareCounter = Board.SIZE * Board.SIZE; 

	/**
	 * Constructor
	 */
	public BoardJPanel() {
		// TODO Auto-generated constructor stub
		this.addMouseListener(this);
		this.addComponentListener(this);
	}

	/**
	 * The paint method from the super Class JComponent. Paints the game
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		drawBoard(g);
		drawResult();
	}

	/**
	 * drawResult has an Instance of the XOTimer class, and checks if one of the three possibilities is fulfilled.
	 * At each state, the timer is stopped, and the background of the panel is colored
	 */
	private void drawResult() {
		XOTimer.getInstance();
		if (this.gameover) {
			XOTimer.getInstance().refreshDisplay("Game Over, You Lost!");

			XOTimer.getInstance().stop();
			setBackground(Color.RED);

		}
		if (this.won) {
			XOTimer.getInstance().refreshDisplay("!!! Congratulations, You Won !!!");
			XOTimer.getInstance().stop();
			setBackground(Color.GREEN);

		}
		if (this.tie) {
			XOTimer.getInstance().refreshDisplay("It's a tie !");
			XOTimer.getInstance().stop();
			setBackground(Color.GRAY);
		}
	}


	/**
	 * @param g
	 * the method, is called constantly in the paint method, and will draw at every call.
	 * it runs through the length and width board and draws each square at its correspondent position, display its value if it's already clicked 
	 * It also stores the squares coordinates(pixels) for further processing.
	 */
	private void drawBoard(Graphics g) {
		Font font = new Font("Serif", Font.CENTER_BASELINE, 30);
		g.setFont(font);

		squareWidth = getWidth() / Board.SIZE;
		squareHeight = getHeight() / Board.SIZE;

		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				Square square = this.myBoard.get(i, j); // 0 1 2 3
				int xsquare = square.getxPos() * squareWidth;
				int ysquare = square.getyPos() * squareHeight;
				// gets the square and his x,y coordinates
				g.drawRect(xsquare, ysquare, squareWidth, squareHeight);
				if (square.isClicked()) {
					int x = square.getxPos() * (squareWidth) + (squareWidth / 2);
					int y = (square.getyPos() + 1) * (squareHeight) - (squareHeight / 2);

					g.setColor(square.getColor());

					String str = String.valueOf(square.getValue());

					g.drawString(str, x, y);
				}
				g.setColor(Color.BLACK);
				this.coordinates.add(new Point(xsquare, ysquare));

			}
		}
	}

	/**
     * Performs an action whenever the user clicks on a square. First, the algorithm checks if the game is finished, if not it generates
     * the clicked square x-y positions using the coordinates ArrayList.
     * Second, performs the player action by setting the sqaure's attributes (value, color, isClicked).
     * Third, checks if the player has made a winner move. If not, performs the computer move.
     * Finally, checks if the computer made a winner move. if not, checks if all the squares has been clicked without anyone winning. If that's
     * the case, then it's a tie. If not, the game continues until we reach one of the states at the end : WON, GAME OVER or TIE.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub	
		if(this.won || this.gameover || this.tie) 
					return;
		
		squareWidth = getWidth() / Board.SIZE;
		squareHeight = getHeight() / Board.SIZE;

		int mousePressX = e.getX();
		int mousePressY = e.getY();

		for (int i = 0; i < this.coordinates.size(); i++) {
			int minX = (int) this.coordinates.get(i).getX();
			int maxX = (int) this.coordinates.get(i).getX() + squareWidth;

			int minY = (int) this.coordinates.get(i).getY();
			int maxY = (int) this.coordinates.get(i).getY() + squareHeight;

			if ((mousePressX >= minX && mousePressX <= maxX) && (mousePressY >= minY && mousePressY <= maxY)) {
				int x = minX / squareWidth;
				int y = minY / squareHeight;

				this.myBoard.get(x, y).setValue(player.getSign());
				this.myBoard.get(x, y).setColor(player.getColor());

				this.myBoard.get(x, y).setClicked(true);

				this.squareCounter--;

				// check if the player won
				if (this.myBoard.checkIfWin(player.getSign())) {
					this.won = true;
					break;
				}

				else if (squareCounter > 0) {
					//Computer's Turn: gets a random square (random x - y ) and checks if this square has already been clicked
					Square computerSquare = Square.getRandom(Board.SIZE);			
					while (this.myBoard.get(computerSquare.getxPos(), computerSquare.getyPos()).isClicked()) {			
						computerSquare = Square.getRandom(Board.SIZE);		
					}
					
					this.myBoard.get(computerSquare.getxPos(), computerSquare.getyPos()).setValue(computer.getSign());
					this.myBoard.get(computerSquare.getxPos(), computerSquare.getyPos()).setColor(computer.getColor());
					this.myBoard.get(computerSquare.getxPos(), computerSquare.getyPos()).setClicked(true);

					this.squareCounter--;

					boolean computerWon = this.myBoard.checkIfWin(this.computer.getSign());					
					if (computerWon) {
						this.gameover = true;
						break;
					}
				}

				else {
					this.tie = true;
					break;
				}
				break;
			}

		}
		repaint();
	}

	/**
	 * Overwrite the method from the Interface ComponentListener to provide scalability
	 */
	@Override
	public void componentResized(ComponentEvent e) {

		int squareWidth = getWidth() / Board.SIZE, squareHeight = getHeight() / Board.SIZE;
		int xsquare = 0, ysquare = 0;

		this.coordinates = new ArrayList<Point>(Board.SIZE);

		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				xsquare = i * squareWidth;
				ysquare = j * squareHeight;

				this.coordinates.add(new Point(xsquare, ysquare));
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

}
