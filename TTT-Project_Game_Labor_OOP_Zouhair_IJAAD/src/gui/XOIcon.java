package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 * Class XOIcon, draws an Icon -XO- on the frame of the game
 *
 */
public final class XOIcon extends BufferedImage {

	public XOIcon() {
		super(120, 120, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = createGraphics();
		
		g2d.setColor(Color.RED);
		g2d.fill3DRect(10, 10, 110, 110,true);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Segoe UI", Font.BOLD, 80));
		g2d.drawString("XO", 10, 88);
		
		g2d.dispose();
	}

}
