package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * --------------------------------------------------------------------------------------												
 * 					Zouhair Ijaad
 * --------------------------------------------------------------------------------------
 * ------------------------------------ TIC TAC TOE GAME --------------------------------
 * --------------------------------------------------------------------------------------
 */

public class XOFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BoardJPanel boardJPanel = new BoardJPanel();
	
	private JButton neuSpiel = new JButton("Start new");
	
	XOTimer timer = XOTimer.getInstance();
	private JLabel timeLabel = new JLabel();

	/**
	 * Constructor
	 */
	public XOFrame() {
		this.setSize(400,400);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setIconImage(new XOIcon());
		timer.start();
		timeLabel = timer.refreshDisplay("time on");
		this.getContentPane().add(this.timeLabel,BorderLayout.NORTH);
		
		this.getContentPane().add(this.boardJPanel, BorderLayout.CENTER);
		this.getContentPane().add(this.neuSpiel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.neuSpiel.addActionListener(this);

	}


	/**
	 * Override the method, and trigger the event of the button "Start new" which starts a new game
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.out.println("new Game");
		@SuppressWarnings("unused")
		XOFrame newGame = new XOFrame();
		
	}

	/**
	 * @param args : MAIN METHODE
	 */
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		XOFrame myFrame = new XOFrame();
	}



}
