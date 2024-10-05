package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font bahn_s, bahn_l;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public BufferedImage logo;
	private int commandNum = 0;
	private Color black = new Color(0, 0, 0, 0);
	
	public UI(GamePanel gp) {
		 this.gp = gp; 
		 
		 bahn_s = (new Font("Bahnschrift", Font.BOLD, 40));
		 bahn_l = (new Font("Bahnschrift", Font.BOLD, 80));
		 
		 
		 // Fenster erstellen
		 
		/* final int frameX = gp.tileSize *2;
		 final int frameY = gp.tileSize;
		 final int frameWidth = gp.tileSize * 5;
		 final int frameHeight = gp.tileSize * 10;
		 //drawSubWindow (frameX, frameY, frameWidth, frameHeight); */
		 getUiImage(); 
		 
		 }
	
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(bahn_s);
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// Titelstatus
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// Spielstatus
		if(gp.gameState == gp.playState) {
			//Platzhalter
		}
		// Pausen-Status
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}

	public void drawTitleScreen() {
	    g2.setColor(new Color(170, 110, 170)); 
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);  		
		// Spielname
		g2.setFont(bahn_l);
		String text = "Lost In Dathe";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 8;
		
		g2.drawImage(logo, getXforCenteredText(text), 58, 512, 512, null);
		// Schatten
		g2.setColor(Color.black);
		g2.drawString(text, x + 5, y + 5);
		// Klarer Text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// Logo + Player
		//x = gp.screenWidth / 2;
		//y += gp.tileSize * 2;
		g2.drawImage(gp.getPlayer().down1, gp.tileSize * 21 + 20, gp.tileSize * 6 + 16, gp.tileSize * 2, gp.tileSize * 2, null);
		
		// Menü
		g2.setFont(bahn_s);
		
		text = "Neues Spiel";
		x = getXforCenteredText(text);
		y = getYforCenteredText(text) +gp.tileSize;
		g2.drawString(text, x, y);
		int xArrow = x-gp.tileSize;
		if(getCommandNum() == 0) {
			g2.drawString(">", xArrow, y);
		}
		
		text = "Spiel laden";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(getCommandNum() == 1) {
			g2.drawString(">", xArrow, y);
		}
		
		text = "Beenden";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(getCommandNum() == 2) {
			g2.drawString(">", xArrow, y);
		}
		
	}
	
	public void drawPauseScreen() {
	    
	    g2.setColor(black); 
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);  

	    
	    String text = "Spiel pausiert";
	    g2.setFont(bahn_l);

	    int x = getXforCenteredText(text);
	    int y = getYforCenteredText(text); 

	    
	    
	    g2.setColor(Color.yellow); 
	    g2.drawString(text, x, y); 
	}
	
	public int getXforCenteredText (String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
	
	public int getYforCenteredText (String text) {
		int ascent = g2.getFontMetrics().getAscent();
		int y = (gp.screenHeight + ascent) / 2;
		return y;
	}
	
	public void getUiImage() { 
		
		logo = setup("Hinten1");

	}
	
	private BufferedImage setup(String imageName) {
		
			UtilityTool uToolUI = new UtilityTool();
			BufferedImage uiPicture = null;
		
			try {
				uiPicture = ImageIO.read(getClass().getResourceAsStream("/pictures/logo.png"));
				uiPicture = uToolUI.getScaledImage(uiPicture, 512, 512);
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return uiPicture;
	}


	/**
	 * @return the commandNum
	 */
	public int getCommandNum() {
		return commandNum;
	}


	/**
	 * @param commandNum the commandNum to set
	 */
	public void setCommandNum(int commandNum) {
		this.commandNum = commandNum;
	}
}

	
