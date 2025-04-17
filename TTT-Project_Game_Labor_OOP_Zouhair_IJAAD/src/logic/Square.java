package logic;

import java.awt.Color;
import java.util.Random;

/**
 * 
 * Class SQUARE
 *
 */
public class Square {

	private int xPos;
	private int yPos;
	private char value;
	private Color color;
	private boolean isClicked;
	
	/**
	 * Square - Constructor
	 * @param xPos: each square has x - position
	 * @param yPos: each square has y -  position
	 */
	public Square(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * 
	 * @return  the x position of the Square
  	 * 
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 *  
	 * @return the position of the Square
	 */
	public int getyPos() {
		return yPos;
	}


	/**
	 * Set a boolean value to the attribute "isClicked" (
	 * @param isClicked
	 */
	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	/** Get the value of "isClicked"
	 * @return isClicked
	 */
	public boolean isClicked() {
		return isClicked;
	}
	
	/**
	 * Set the value of the square (x / o)
	 * @param value
	 * 
	 */
	public void setValue(char value) {
		this.value = value;
	}
	
	/**
	 * Set the "color" value 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Get the chosen color value 
	 * @return color
	 */
	public Color getColor() {
		return this.color;
	}
	
	
	/**
	 * Get the square's value (x - o)
	 * @return
	 */
	public char getValue() {
		return this.value;
	}

	/**
	 * @param max
	 * @return
	 * The Method getRandom returns a new square with a random position x-y. 
	 */
	public static Square getRandom(int max) {
		Random random = new java.util.Random();
		int x = random.nextInt(max);
		int y = random.nextInt(max);

		return new Square(x,y);
				
	}
	
}
