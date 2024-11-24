package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {
	
	GamePanel gp;
	BufferedImage darknessFilter;
	
	public Lighting(GamePanel gp) {

		// Create a buffered image
		darknessFilter = new BufferedImage(gp.getScreenWidth(), gp.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();


		

		Color color = new Color(0, 0, 0, 0.85f);

		g2.setColor(color);
		g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());



		g2.dispose();	

	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(darknessFilter, 0, 0, null);
	}
}
