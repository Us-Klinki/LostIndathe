package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font yoster, yoster_s, yoster_l, yoster_sl, yoster_xs; 
	Color signal, white;
	
	public static boolean messageOn = false;
	public String message = "Interagieren - ENTER";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public BufferedImage logo;
	private String currentDialogue = "";
	private int commandNum = 0;
	int subState = 0;
	int subStateTitle = 0;
	private String displayedDialogue = "";
	private int dialogueIndex = 0;
	private int dialogueSpeed = 2; 
	private int dialogueCounter = 0;

	public UI(GamePanel gp) {
		 this.gp = gp; 
		 
		 try (InputStream FontLoader = getClass().getResourceAsStream("/font/yoster.ttf")) {
			 yoster = Font.createFont(Font.TRUETYPE_FONT, FontLoader);
			    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    ge.registerFont(yoster);
			} catch (FontFormatException | IOException e) {
			    e.printStackTrace();
			}
		 yoster_s = yoster.deriveFont(Font.PLAIN, 40);
		 yoster_sl = yoster.deriveFont(Font.BOLD, 60);
		 yoster_l = yoster.deriveFont(Font.BOLD, 80);
		 yoster_xs = yoster.deriveFont(Font.PLAIN, 27);
		 signal = new Color(255, 208, 0);
		 white = new Color(255, 255, 255);
		 
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
		this.displayedDialogue = "";
		this.dialogueIndex = 0;
	}
	
	public void showMessage(String text, Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(yoster_s);
		g2.setColor(Color.white);
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(yoster_s);
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
			if(messageOn == true) {
				g2.drawRoundRect(gp.getTileSize() * 12, gp.getTileSize() * 14, gp.getTileSize() * 8, gp.getTileSize() * 2, 12, 12);
			}
		}
		// Pausen-Status
		else if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// Optionen-Status
		else if(gp.gameState == gp.optionsState) {
			
			drawOptionsScreen();
		}
		
		// Dialog-Status
		if(gp.gameState == gp.dialogueState) {
			updateDialogue();
			drawDialogueScreen();
		}
		
		if(gp.gameState == gp.endState) {
			drawEndScreen();
		}
	}
	
	public void drawTitleScreen() {
	    g2.setColor(new Color(170, 110, 170)); 
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);  		
		// Spielname
		g2.setFont(yoster_l);
		String text = "Lost In Dathe";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 8;
		
		g2.drawImage(logo, getXforCenteredText(text) + 44, 58, 512, 512, null);
		// Schatten
		g2.setColor(Color.black);
		g2.drawString(text, x + 5, y + 5);
		// Klarer Text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// Logo + Player
		//x = gp.screenWidth / 2;
		//y += gp.tileSize * 2;
		g2.drawImage(gp.getPlayer().down1, gp.tileSize * 23, gp.tileSize * 6 + 16, gp.tileSize * 2, gp.tileSize * 2, null);
		
		// Menü
		g2.setFont(yoster_s);
		if(subStateTitle == 0) {title_top(x, y, text);}
		else if(subStateTitle == 1) {title_infos(x, text);}
		gp.keyH.enterPressed = false;

		
		
	}
	public void title_top(int x, int y, String text) {
		if(commandNum == 0) {
			g2.setColor(signal);
		}
		text = "Neues Spiel";
		x = getXforCenteredText(text);
		y = getYforCenteredText(text) +gp.tileSize;
		g2.drawString(text, x, y);
		int xArrow = x-gp.tileSize;
		if(getCommandNum() == 0) {
			g2.drawString(">", xArrow, y);
		}
		g2.setColor(white);
		if(commandNum == 1) {
			g2.setColor(signal);
		}
		text = "Spiel laden";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(getCommandNum() == 1) {
			g2.drawString(">", xArrow, y);
		}
		g2.setColor(white);
		if(commandNum == 2) {
			g2.setColor(signal);
		}
		text = "Beenden";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(getCommandNum() == 2) {
			g2.drawString(">", xArrow, y);
		}
		g2.setColor(white);
		if(commandNum == 3) {
			g2.setColor(signal);
		}
		text = "Infos";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(getCommandNum() == 3) {
			g2.drawString(">", xArrow, y);
		}
		
		
	}
	
	public void title_infos(int x, String text) {
		int frameWidth = gp.getTileSize() * 18;
		int frameHeight = gp.getTileSize() * 16;
		int frameX = getXforCenteredText("") - frameWidth/2 + 10;
		int frameY = (gp.getTileSize() * 9) - (frameHeight/2);
		int textX;
		int textY;
		boolean transparent = false;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, transparent);
		int abstand = gp.getTileSize();
		int absatz = gp.getTileSize() / 4 + gp.getTileSize() / 2;
		g2.setFont(yoster_sl);
		text = "Infos";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		g2.setFont(yoster_s);
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz *2;
		int textXlinks = textX;
		g2.drawString("Entwicklung", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.drawString("- Thu An Phung", textX, textY);
		textY += absatz;
		g2.drawString("- Tom Speer", textX, textY);
		textY += absatz;
		g2.drawString("- Gustav Kluge", textX, textY);
		int textYlinks = textY;
		textX = getXforCenteredText("") + gp.getTileSize();
		textY = textY - absatz * 2 - abstand;
		g2.setFont(yoster_s);
		g2.drawString("Grafikdesign", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.drawString("- Marika Uhrig", textX, textY);
		textY += absatz;
		g2.drawString("- Karoline Schiemann", textX, textY);
		g2.setFont(yoster_s);
		textX = textXlinks;
		textY = textYlinks + 2*absatz;
		int textYrechts = textY;
		g2.drawString("Stimmen", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.drawString("- Felix Peil", textX, textY);
		textY += absatz;
		g2.drawString("- Sören Priebe", textX, textY);
		textY += absatz;
		g2.drawString("- Anke Köppel", textX, textY);
		textY += absatz;
		g2.drawString("- Jörg Klinkhardt", textX, textY);
		textY += absatz;
		g2.drawString("- Dina Krecic", textX, textY);
		textY = textYrechts;
		textX = getXforCenteredText("") + gp.getTileSize();
		g2.setFont(yoster_s);
		g2.drawString("Sounddesign", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.drawString("- Max Rosenhauer", textX, textY);
		textY += 2 * abstand;
		textX -= gp.getTileSize();
		g2.setFont(yoster_s);
		g2.drawString("Ober'be'lehrer", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.drawString("- Klinki", textX, textY);
		textX = textXlinks;
		textY += absatz * 1.5;
		g2.drawString("Ein Informatikprojekt mit Passion im Rahmen des", textX, textY);
		textY += absatz / 2 + absatz / 4;
		g2.drawString("Projektsemesters Q3. Präsentiert von Us and Klinki.", textX, textY);
		textY += absatz;
		g2.drawString("Version alpha 0.9.0-20241127.", textX, textY);
		textY += 2 * absatz;
		g2.setFont(yoster_s);
		
		g2.setColor(signal);
		textX += gp.getTileSize();
		g2.drawString("Zurück [ESC]", textX, textY);
		textX -= gp.getTileSize();
		
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			
			if(gp.keyH.enterPressed == true) {
				subStateTitle = 0;
			}
		}
	}
	
	public void drawPauseScreen() {
	    
	    g2.setColor(new Color(0, 0, 0, 220)); 
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);  

	    
	    String text = "Spiel pausiert";
	    g2.setFont(yoster_l);

	    int x = getXforCenteredText(text);
	    int y = getYforCenteredText(text); 

	    
	    
	    g2.setColor(Color.yellow); 
	    g2.drawString(text, x, y); 
	}
	
	public void drawOptionsScreen() {
		g2.setFont(yoster_sl);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.86f));
		
		//SUB Window
		
		int frameWidth = gp.getTileSize() * 10;
		int frameHeight = gp.getTileSize() * 13;
		int frameX = getXforCenteredText("") - frameWidth/2 + 10;
		int frameY = (gp.getTileSize() * 9) - (frameHeight/2);
		boolean transparent = true;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, transparent);
		int abstand = gp.getTileSize() + gp.getTileSize() / 2;
		int absatz = gp.getTileSize() * 2;
		int auswahlAbstand = gp.getTileSize() / 2 + gp.getTileSize() / 8 + 10 ;
		
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
		
		g2.setFont(yoster_s);
		
		// Vollbild an/aus
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz;
		if(commandNum == 0) {
			g2.setColor(signal);
		}
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
		g2.setColor(white);
		textY += abstand;
		if(commandNum == 1) {
			g2.setColor(signal);
		}
		g2.drawString("Musik", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - auswahlAbstand, textY);
		}
		
		// Soundeffekte
		textY += abstand;
		g2.setColor(white);
		if(commandNum == 2) {
			g2.setColor(signal);
		}
		g2.drawString("Sounds", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - auswahlAbstand, textY);
		}
		
		// Steuerung
		textY += abstand;
		g2.setColor(white);
		if(commandNum == 3) {
			g2.setColor(signal);
		}
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
		g2.setColor(white);
		if(commandNum == 4) {
			g2.setColor(signal);
		}
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
		g2.setColor(white);
		if(commandNum == 5) {
			g2.setColor(signal);
		}
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
		g2.setColor(new Color(110, 9, 20));
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
		int textX;
		int textY;
		String text = "Bildmodus";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.getTileSize();
		textY = frameY + gp.getTileSize() * 4;
		
		g2.setFont(yoster_xs);
		g2.setColor(Color.white);
		setCurrentDialogue("Das Spiel muss neugestartet\nwerden, um die Änderung \nzu realisieren.");
		
		 for (String line: getCurrentDialogue().split("\n")) {
		 	g2.drawString(line, textX, textY);
		 	textY+= 40;
		 }
		 
		 g2.setFont(yoster_s);
		 textY += abstand * 3.9 - 40;
		 if(commandNum == 0) {
				g2.setColor(signal);
			}
		 g2.drawString("Zurück", textX, textY);
		 if(commandNum == 0) {
		 	g2.drawString(">", textX-auswahlAbstand, textY);
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
		
		g2.setFont(yoster_s);
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz;
		g2.drawString("Bewegen", textX, textY); textY += abstand;
		g2.drawString("Interaktion", textX, textY); textY += abstand;
		g2.drawString("Optionen", textX, textY); textY += abstand;
		g2.drawString("Pause", textX, textY); textY += abstand;
		g2.drawString("Debug", textX, textY); textY += abstand;
		
		g2.setFont(yoster_s);
		g2.setColor(new Color(110, 9, 20));
		textX = frameX + gp.getTileSize() * 7;
		textY = frameY + gp.getTileSize() * 2 + abstand;
		g2.drawString("WASD", textX, textY); textY += abstand;
		g2.drawString("ENTER", textX, textY); textY += abstand;
		g2.drawString("ESC", textX, textY); textY += abstand;
		g2.drawString("K", textX, textY); textY += abstand;
		g2.drawString("Q", textX, textY); textY += abstand;
		
		// Zurück
		g2.setFont(yoster_s);
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz - abstand;
		if(commandNum == 0) {
			g2.setColor(signal);
		}
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
		int textX;
		int textY;
		String text = "Beenden?";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		g2.setFont(yoster_xs);
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY = frameY + 2* abstand + abstand/2;
		
		setCurrentDialogue("Spiel verlassen und zum \nDesktop zurückkehren? \n\nSpielstand wird\nzwischengespeichert.");
		
		for (String line: getCurrentDialogue().split("\n")) {
			 	g2.drawString(line, textX, textY);
			 	textY+= 40;
			 } 
		g2.setFont(yoster_s);
		if(commandNum == 0) {
			g2.setColor(signal);
		}
		 text = "Nein";
		textX = getXforCenteredText(text);
		textY += absatz/2 + absatz/4;
		auswahlAbstand = textX - auswahlAbstand;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.setColor(signal);
			g2.drawString(">", auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
		}
		
		g2.setColor(signal);
		if(commandNum == 0) {
			g2.setColor(white);
		}
		text = "Ja";
		textX = getXforCenteredText(text);
		textY += absatz;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.setColor(signal);
			g2.drawString(">", auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				//subState = 0;
				//commandNum = 0;
				gp.saveLoad.save();
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
		int x = gp.getTileSize() * 4 + gp.getTileSize() / 2;
		int y = gp.getTileSize();
		int width = gp.getScreenWidth() - (gp.getTileSize() * 9);
		int height = gp.getTileSize() * 4;
		boolean transparent = true;
		
		drawSubWindow(x, y, width, height, transparent);
		
		g2.setFont(yoster_s);
		
		g2.setColor(Color.white);
		
		x += gp.getTileSize() / 2;
		y += gp.getTileSize() * 1.1;
		
		for (String line: displayedDialogue.split("\n")) {
		 	g2.drawString(line, x, y);
		 	y += 56;
		} 
		//g2.drawString(currentDialogue, x, y);
		
		
	}
	
	public void drawEndScreen() {
		String text;
		int frameX = gp.getTileSize() / 2;
		int frameY = gp.getTileSize() / 2; 
		int textX;
		int textY;
		boolean transparenz = true;
		drawSubWindow(frameX, frameY, gp.getTileSize() * 31, gp.getTileSize() * 17, transparenz);
		int abstand = gp.getTileSize();
		int absatz = gp.getTileSize() / 4 + gp.getTileSize() / 2;
		int umbruch = absatz / 2 + absatz / 4;
		g2.setFont(yoster_sl);
		text = "Gratulation! Du bist draußen!";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		g2.setFont(yoster_s);
		g2.setColor(Color.white);
		textX = frameX + gp.getTileSize();
		textY += absatz *2;
		int textXlinks = textX;
		g2.drawString("Vielen Dank, dass du LostInDathe gespielt hast.", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.drawString("LostInDathe ist ein Informatikprojekt des Projektsemesters Q3. Nach vielen Herausfor-", textX, textY);
		textY += umbruch;
		g2.drawString("derungen und intensivem Arbeiten, sind wir stolz, dieses Spiel präsentieren zu können.", textX, textY);
		textY += umbruch;
		g2.drawString("Es war anstrengend, keine Frage, aber es hat sich sehr gelohnt. Wir danken dem Fachbe-", textX, textY);
		textY += umbruch;
		g2.drawString("reich Informatik für diese Möglichkeit. Logisches Denken ist der Weg zum Ziel.", textX, textY);
		textY += 2 * absatz;
		textX -= gp.getTileSize();
		g2.setFont(yoster_s);
		g2.drawString("Das Team", textX, textY);
		textY += abstand;
		textX += gp.getTileSize();
		g2.setFont(yoster_xs);
		g2.drawString("Programm:", textX, textY);
		g2.drawImage(gp.getPlayer().bleft1, gp.tileSize * 21, gp.tileSize * 8 + 16, gp.tileSize * 2, gp.tileSize * 2, null);
		g2.drawImage(gp.getPlayer().idle1, gp.tileSize * 23 + 8, gp.tileSize * 8 + 16, gp.tileSize * 2, gp.tileSize * 2, null);
		g2.drawImage(gp.getPlayer().bright1, gp.tileSize * 25 + 16, gp.tileSize * 8 + 16, gp.tileSize * 2, gp.tileSize * 2, null);
		g2.drawImage(gp.getPlayer().up1, gp.tileSize * 27 + 24, gp.tileSize * 8 + 16, gp.tileSize * 2, gp.tileSize * 2, null);
		int xTab = textX + gp.getTileSize() * 5;
		g2.drawString("Thu An Phung, Tom Speer, Gustav Kluge", xTab, textY);
		textY += absatz;
		g2.drawString("Grafik:", textX, textY);
		g2.drawString("Marika Uhrig, Karoline Schiemann", xTab, textY);
		textY += absatz;
		g2.drawString("Sound:", textX, textY);
		g2.drawString("Max Rosenhauer", xTab, textY);
		textY += absatz;
		g2.drawString("Ober'be'lehrer:", textX, textY);
		g2.drawString("Klinki", xTab, textY);
		textY += 2 * absatz;
		textX -= gp.getTileSize();
		g2.setFont(yoster_s);
		g2.drawString("Mitwirkende Lehrkräfte", textX, textY);
		textY += abstand;
		textX += gp.getTileSize();
		g2.setFont(yoster_xs);
		g2.drawString("Felix Peil, Sören Priebe, Anke Köppel, Jörg Klinkhardt, Dina Krecic", textX, textY);
		textY += 2 * absatz;
		textX -= gp.getTileSize();
		
		
		g2.drawString("Dathe-Gymnasium, Dez. 2024. Version alpha 0.9.0-20241127. Präsentiert von [Us and Klinki]", textX, textY);
		textY += 2 * absatz;
		g2.setFont(yoster_s);
		g2.setColor(signal);
		textX += gp.getTileSize();
		g2.drawString("Spiel beenden", textX, textY);
		textX -= gp.getTileSize();
		
		if(commandNum == 0) {
			g2.drawString(">", textX, textY);
			
			if(gp.keyH.enterPressed == true) {
				subStateTitle = 0;
			}
		}
	}
	
	// letter by letter
	public void updateDialogue() {
		try {
		if(currentDialogue != null) {
			if (gp.gameState == gp.dialogueState && dialogueIndex < currentDialogue.length()) {
				dialogueCounter++;
	        
				if (dialogueCounter >= dialogueSpeed) {
					displayedDialogue += currentDialogue.charAt(dialogueIndex);
					dialogueIndex++;
					dialogueCounter = 0;
				}
			}
		}

		}
		catch(Exception e) {
			System.out.println("Dialog hat nicht geklappt.");
		}
	}

	public void drawSubWindow(int x, int y, int width, int height, boolean transparent) {
		int strokeWidth = 3;
		int bogen = 35;
		
		
		if(transparent == true) {
			Color c = new Color(0, 0, 0, 220);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, bogen, bogen);
		}
		else {
			Color c = new Color(0, 0, 0);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, bogen, bogen);
		}
		
		Color d = new Color(110, 9, 20);
		g2.setColor(d);
		g2.setStroke(new BasicStroke(strokeWidth)); 
		g2.drawRoundRect(x + strokeWidth, y + strokeWidth, width - 2 * strokeWidth, height - (2 * strokeWidth), 25, 25);
	}


	
}

	
