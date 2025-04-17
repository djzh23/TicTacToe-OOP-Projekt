package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import javax.swing.Timer;


/**
 * 
 * Class XOTimer, extends from JLabel and implements ActionListener Interface
 * This Class, is singleton class, has a private constructor, and can be instantiated through the "getInstance" Method.
 * This class provides the following methods: start(), stop(), refreshDisplay(), and actionPerformed(). 
 *
 */
public class XOTimer extends JLabel implements ActionListener{
	private static final long serialVersionUID = 1L;
	static private XOTimer instance = null;
	JLabel counterLabel = new JLabel("time passed :");
	
	private int counter = 0;
	Timer timerTask = new Timer(0,null);

	private XOTimer() {		
	}
	
	static public XOTimer getInstance(){
		if(instance == null) {
			instance = new XOTimer();
				
		}return instance;
	}

	public void start() {
		this.timerTask = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {	
	            counter++;
	            refreshDisplay("Seconds Counter");
	        }
	     });
		timerTask.start();
		counter = 0;
	}
	
	public int stop() {
		timerTask.stop();
		return counter;
	}
	
	public JLabel refreshDisplay(String message) {
        this.counterLabel.setText(message+ " : "+counter+ " seconds");
		return counterLabel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
