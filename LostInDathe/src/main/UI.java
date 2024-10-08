package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
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
	Font bahn_s, bahn_l, bahn_sl, bahn_xs;
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public BufferedImage logo;
	private String currentDialogue = "";
	private int commandNum = 0;
	private Color black = new Color(0, 0, 0, 0);                    
	int subState = 0;
	
	public UI(GamePanel gp) {
		 this.gp = gp; 
		 
		 bahn_s = (new Font("Bahnschrift", Font.BOLD, 40));
		 bahn_sl = (new Font("Bahnschrift", Font.BOLD | Font.ITALIC, 60));
		 bahn_l = (new Font("Bahnschrift", Font.BOLD, 80));
		 bahn_xs = (new Font("Bahnschrift", Font.PLAIN, 20));
		 
		 // Fenster erstellen
		 
		/* final int frameX = gp.tileSize *2;
		 final int frameY = gp.tileSize;
		 final int frameWidth = gp.tileSize * 5;
		 final int frameHeight = gp.tileSize * 10;
		 //drawSubWindow (frameX, frameY, frameWidth, frameHeight); */
		 getUiImage(); 
		 
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


	/**
	 * @return the currentDialogue
	 */
	public String getCurrentDialogue() {
		return currentDialogue;
	}


	/**
	 * @param currentDialogue the currentDialogue to set
	 */
	public void setCurrentDialogue(String currentDialogue) {
		this.currentDialogue = currentDialogue;
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
		else if(gp.gameState == gp.playState) {
			//Platzhalter
		}
		// Pausen-Status
		else if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// Optionen-Status
		else if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
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
	
	public void drawOptionsScreen() {
		g2.setFont(bahn_sl);
		
		
		//SUB Window
		
		int frameWidth = gp.getTileSize() * 10;
		int frameHeight = gp.getTileSize() * 13;
		int frameX = getXforCenteredText("") - frameWidth/2;
		int frameY = (gp.getTileSize() * 9) - (frameHeight/2);
		int abstand = gp.getTileSize() + gp.getTileSize() / 2;
		int absatz = gp.getTileSize() * 2;
		int auswahlAbstand = gp.getTileSize() / 2 + gp.getTileSize() / 8;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		switch(subState) {
		case 0: options_top(abstand, absatz, frameX, frameY, auswahlAbstand); break;
		case 1: options_fullScreenNotification(frameX, frameY, auswahlAbstand, abstand, absatz); break;
		case 2: options_control(absatz, abstand, frameX, frameY, auswahlAbstand); break;
		case 3: options_endGameConfirmation(absatz, abstand, frameX, frameY, auswahlAbstand); break;
		}
		
		gp.keyH.enterPressed = false;
	}
	
	/*public void drawDialogueScreen() {
		
	} */
	public void options_top(int abstand, int absatz, int frameX, int frameY, int auswahlAbstand) {
		int textX;
		int textY;
		
		int musicAuswahlBreite = gp.getTileSize() * 3;
		
		// TITLE
		String text = "Optionen";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		int checkBoxY = (int) (textY + gp.getTileSize() * 1.46);
		g2.drawString(text, textX, textY);
		
		g2.setFont(bahn_s);
		
		// Vollbild an/aus
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz;
		g2.drawString("Vollbild", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.isFullScreenOn() == true) {
					gp.setFullScreenOn(false);		
				}
				else if (gp.isFullScreenOn() == false) {
					gp.setFullScreenOn(true);
				}
				subState = 1;
			}
		}
		
		// Musik
		textY += abstand;
		g2.drawString("Musik", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - auswahlAbstand, textY);
		}
		
		// Soundeffekte
		textY += abstand;
		g2.drawString("Sounds", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - auswahlAbstand, textY);
		}
		
		// Steuerung
		textY += abstand;
		g2.drawString("Steuerung", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		// Spiel beenden
		textY += abstand;
		g2.drawString("Spiel beenden", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// Zurück
		textY += absatz;
		g2.drawString("Zurück zum Spiel", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		// Vollbild Checkbox
		textX = frameX + gp.getTileSize() * 6;
		textY = checkBoxY;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(190, 60, 190));
		g2.drawRect(textX, textY, gp.getTileSize() / 2, gp.getTileSize() / 2);
		if(gp.isFullScreenOn() == true) {
			g2.fillRect(textX, textY, gp.getTileSize() / 2, gp.getTileSize() / 2);
		}
		
		// Musik Lautstärke
		textY += abstand;
		g2.drawRect(textX, textY, musicAuswahlBreite, gp.getTileSize() / 2);
		float volumeWidth = (musicAuswahlBreite + 5)/ 11 * gp.music.volumeScale;
		g2.fillRect(textX, textY, (int) volumeWidth, gp.getTileSize() / 2);
		
		// Sounds Lautstärke
		textY += abstand;
		g2.drawRect(textX, textY, musicAuswahlBreite, gp.getTileSize() / 2);
		volumeWidth = (musicAuswahlBreite + 5) / 11 * gp.se.volumeScale;
		g2.fillRect(textX, textY, (int) volumeWidth, gp.getTileSize() / 2);
		
		gp.config.saveConfig();
	}
	
	public void options_fullScreenNotification(int frameX, int frameY, int auswahlAbstand, int abstand, int absatz) {
		
		int textX = frameX + gp.getTileSize();
		int textY = frameY + gp.getTileSize() * 4;
		
		g2.setFont(bahn_xs);
		g2.setColor(Color.white);
		setCurrentDialogue("Das Spiel muss neugestartet werden, \num die Änderung zu realisieren.");
		
		 for (String line: getCurrentDialogue().split("\n")) {
		 	g2.drawString(line, textX, textY);
		 	textY+= 40;
		 }
		 
		 g2.setFont(bahn_s);
		 textY += abstand * 3.9;
		 g2.drawString("Zurück", textX, textY);
		 if(commandNum == 0) {
		 	g2.drawString(">", textX-25, textY);
		 	if(gp.keyH.enterPressed == true) {
		 		subState = 0;
		 	}
		 }	
		 
		
	}
	
	public void options_control(int absatz, int abstand, int frameX, int frameY, int auswahlAbstand) {
		
		int textX;
		int textY;
		
		//TITLE
		String text = "Steuerung";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		
		g2.setFont(bahn_s);
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz;
		g2.drawString("Bewegen", textX, textY); textY += abstand;
		g2.drawString("Optionen", textX, textY); textY += abstand;
		g2.drawString("Pause", textX, textY); textY += abstand;
		g2.drawString("Debug-Modus", textX, textY); textY += abstand;
		
		g2.setFont(bahn_xs);
		textX = frameX + gp.getTileSize() * 7;
		textY = frameY + gp.getTileSize() * 2 + abstand;
		g2.drawString("WASD/Pfeile", textX, textY); textY += abstand;
		g2.drawString("ESC", textX, textY); textY += abstand;
		g2.drawString("K", textX, textY); textY += abstand;
		g2.drawString("Q", textX, textY); textY += abstand;
		
		// Zurück
		g2.setFont(bahn_s);
		textX = frameX + gp.getTileSize();
		textY += absatz;
		g2.drawString("Zurück", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	public void options_endGameConfirmation(int absatz, int abstand, int frameX, int frameY, int auswahlAbstand) {
		g2.setFont(bahn_xs);
		g2.setColor(Color.white);
		int textX = frameX + gp.getTileSize();
		int textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		
		setCurrentDialogue("Spiel verlassen und zum Desktop \nzurückkehren? \n\nUngespeicherter Fortschritt geht verloren.");
		
		for (String line: getCurrentDialogue().split("\n")) {
			 	g2.drawString(line, textX, textY);
			 	textY+= 40;
			 } 
		g2.setFont(bahn_s);
		// Nein
		String text = "Nein";
		textX = getXforCenteredText(text);
		textY += absatz;
		auswahlAbstand = textX - auswahlAbstand;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
		// Ja
		text = "Ja";
		textX = getXforCenteredText(text);
		textY += absatz;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				//subState = 0;
				//commandNum = 0;
				System.exit(1);
				
				//gp.music.stop();
				//gp.playMusic(3);
				
				//gp.gameState = gp.titleState;
			}
		}
		
		
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
	
	public void drawDialogueScreen() {
		
		// Fenster
		int x = gp.getTileSize() * 3;
		int y = gp.getTileSize();
		int width = gp.getScreenWidth() - (gp.getTileSize() * 8);
		int height = gp.getTileSize() * 6;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(bahn_xs);
		
		x += gp.getTileSize();
		y += gp.getTileSize();
		
		for (String line: getCurrentDialogue().split("\n")) {
		 	g2.drawString(line, x, y);
		 	y += 40;
		 } 
		g2.drawString(currentDialogue, x, y);
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		int strokeWidth = 5;
		int bogen = 35;
		
		
		
		Color c = new Color(0, 0, 0);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, bogen, bogen);
		
		
		Color d = new Color(190, 60, 190);
		g2.setColor(d);
		g2.setStroke(new BasicStroke(strokeWidth)); 
		g2.drawRoundRect(x + strokeWidth, y + strokeWidth, width - 2 * strokeWidth, height - (2 * strokeWidth), 25, 25);
	}


	
}

	
