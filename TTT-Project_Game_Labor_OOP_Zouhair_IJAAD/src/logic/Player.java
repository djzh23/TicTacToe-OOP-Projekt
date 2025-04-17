package logic;

import java.awt.Color;

/**
 * 
 * Player Class 
 *
 */
public class Player {

	@SuppressWarnings("unused")
	private String name;
	private char sign;
	private Color color;
	
	/**
	 *  Player - CONSTRUCTOR 
	 * @param name : name of the Player 
	 * @param sign : sign associated with the player, can be 'X' or 'O' 
	 * @param color : Color of the sign (will be used later in the GUI)
	 * 
	 */

	public Player(String name, char sign, Color color) {
		this.name = name;
		this.sign = sign;
		this.color = color;
	}

	
	/**
	 * @return GETTER of the "sign" attribute
	 * return the sign (X or O)
	 */
	public char getSign() {
		return sign;
	}
	

	/**
	 * @return GETTER of the "color" attribute
	 * return the color value
	 */
	public Color getColor() {
		return this.color;
	}
	
	
	
	
}
