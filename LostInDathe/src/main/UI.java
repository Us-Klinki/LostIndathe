package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40,arial_80B;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public UI(GamePanel gp) {
		 this.gp = gp; 
		 
		 arial_40 = (new Font("Arial", Font.PLAIN, 40));
		 arial_80B = (new Font("Arial", Font.BOLD, 80));
		 
		 }
	
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	

	public void drawPauseScreen(Graphics2D g2) {
	    
	    g2.setColor(new Color(0, 0, 0, 0)); 
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);  

	    
	    String text = "PAUSED";
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));

	    int length = g2.getFontMetrics().stringWidth(text);
	    int x = gp.screenWidth / 2 - length / 2; 
	    int ascent = g2.getFontMetrics().getAscent(); 
	    int y = (gp.screenHeight + ascent) / 2; 

	    
	    
	    g2.setColor(Color.yellow); 
	    g2.drawString(text, x, y); 
	}

}
