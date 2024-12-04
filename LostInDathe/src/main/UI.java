package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font yoster, yoster_s, yoster_l, yoster_sl, yoster_xs, yoster_t; 
	Color signal, white, header, transparent, dunkellila, menü, menüdunkel;
	
	int counter = 0;
	public static boolean messageOn = false;
	public String message = "Interagieren - ENTER";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public boolean hudOn = true;
	public BufferedImage logo;
	private String currentDialogue = "";
	private int commandNum = 0;
	int subState = 0;
	int subStateTitle = 0;
	private String displayedDialogue = "";
	private int dialogueIndex = 0;
	private int dialogueSpeed = 2; 
	private int dialogueCounter = 0;
	public BufferedImage phenol, key, universal, base, neutral, säure, maus, gustav, karo, kiki, max, thuan, tom, klinki, priebe, köppel, krecic;

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
		 yoster_t = yoster.deriveFont(Font.ITALIC, 15);
		 signal = new Color(255, 208, 0);
		 white = new Color(255, 255, 255);
		 header = new Color(23, 224, 172);
		 transparent = new Color(0, 0, 0, 0);
		 dunkellila = new Color(148, 18, 144);
		 menü = new Color(170, 110, 170);
		 menüdunkel = new Color(175, 112, 175);
		 
		 // Fenster erstellen
		 
		/* final int frameX = gp.tileSize *2;
		 final int frameY = gp.tileSize;
		 final int frameWidth = gp.tileSize * 5;
		 final int frameHeight = gp.tileSize * 10;
		 //drawSubWindow (frameX, frameY, frameWidth, frameHeight); */
		 getUiImage(); 
		 phenol = setupHud("/npc/objects/chemie/Phenolphthalein");
		 key = setupHud("/npc/objects/key");
		 universal = setupHud("/npc/objects/chemie/Universalindikator");
		 säure = setupHud("/npc/objects/chemie/Säure");
		 base = setupHud("/npc/objects/chemie/Base");
		 neutral = setupHud("/npc/objects/chemie/Neutral");
		 gustav = setupHud("/pictures/Gustav");
		 karo = setupHud("/pictures/Karo");
		 kiki = setupHud("/pictures/Kiki");
		 max = setupHud("/pictures/Max");
		 thuan = setupHud("/pictures/ThuAn");
		 tom = setupHud("/pictures/Tom");
		 klinki = setupHud("/npc/Klinki/Klinkhardt_Stehend1");
		 priebe = setupHud("/npc/Priebe/Priebe_Stehend1");
		 köppel = setupHud("/npc/Köppel/Köppel_Stehend1");
		 krecic = setupHud("/npc/Krecic/Krecic_Stehend1");
		 
		 
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
			if(gp.startState) {
				drawPlayStartScreen();
			}
			if(hudOn) {
				drawHud();
			}
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
		else if(gp.gameState == gp.dialogueState) {
			updateDialogue();
			drawDialogueScreen();
			drawHud();
		}
		
		else if(gp.gameState == gp.endState) {
			drawEndScreen();
		}
	}
	
	public void drawTitleScreen() {
	    g2.setColor(menü); 
	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);  		
		// Spielname
		g2.setFont(yoster_l);
		String text = "Lost In Dathe";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 8;
		int picX = gp.getTileSize() * 3 + gp.getTileSize() / 2;
		int picY = gp.getTileSize() * 15;
		int textY = picY + gp.getTileSize() * 2 + gp.getTileSize() / 2;
		int picTab = gp.getTileSize() * 2 + gp.getTileSize() / 2;
		int picSize = gp.getTileSize() * 2;
		
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
		g2.setColor(Color.black);
		int[] xPoints = {0, gp.getTileSize() * 3, gp.getTileSize() * 29, gp.getTileSize() * 32}; // x-Koordinaten der Ecken
        int[] yPoints = {gp.getTileSize() * 18, gp.getTileSize() * 14, gp.getTileSize() * 14, gp.getTileSize() * 18};
		int width = gp.getTileSize() * 32;
		int height = gp.getTileSize() * 4;
		
		// Erstelle ein BufferedImage für den Farbverlauf
        BufferedImage gradientImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gImage = gradientImage.createGraphics();
        
     // Farbverlauf erstellen (von Blau unten nach Weiß oben)
        GradientPaint gradient = new GradientPaint(0, height, dunkellila, 0, 0, menüdunkel);
        gImage.setPaint(gradient);
        gImage.fillRect(0, 0, width, height);
        gImage.dispose();
        
        TexturePaint texturePaint = new TexturePaint(gradientImage, new Rectangle(0, gp.getTileSize() * 18, width, height));
        g2.setPaint(texturePaint);

        // Trapez zeichnen
        g2.fillPolygon(xPoints, yPoints, 4);
        
        g2.fillPolygon(xPoints, yPoints, 4);
		
		g2.setColor(Color.white);
        g2.setFont(yoster_t);
		g2.drawImage(klinki, picX, picY, picSize, picSize, null);
		g2.drawString("Klinki", picX + 24, textY);
		picX += picTab;
		g2.drawImage(köppel, picX, picY, picSize, picSize, null);
		g2.drawString("Köppel", picX + 20, textY);
		picX += picTab;
		g2.drawImage(gustav, picX, picY, picSize, picSize, null);
		g2.drawString("Gustav", picX + 16, textY);
		picX += picTab;
		g2.drawImage(karo, picX, picY, picSize, picSize, null);
		g2.drawString("Karo", picX + 27, textY);
		picX += picTab;
		g2.drawImage(kiki, picX, picY, picSize, picSize, null);
		g2.drawString("Kiki", picX + 30, textY);
		picX += picTab;
		g2.drawImage(max, picX, picY, picSize, picSize, null);
		g2.drawString("Max", picX + 32, textY);
		picX += picTab;
		g2.drawImage(thuan, picX, picY, picSize, picSize, null);
		g2.drawString("Thu An", picX + 18, textY);
		picX += picTab;
		g2.drawImage(tom, picX, picY, picSize, picSize, null);
		g2.drawString("Tom", picX + 32, textY);
		picX += picTab;
		g2.drawImage(krecic, picX, picY, picSize, picSize, null);
		g2.drawString("Krecic", picX + 23, textY);
		picX += picTab;
		g2.drawImage(priebe, picX, picY, picSize, picSize, null);
		g2.drawString("Priebe", picX + 20, textY);
		
		
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
		boolean hellTransparent = false;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, transparent, hellTransparent, false);
		int abstand = gp.getTileSize();
		int absatz = gp.getTileSize() / 4 + gp.getTileSize() / 2;
		g2.setFont(yoster_sl);
		text = "Infos";
		textX = getXforCenteredText(text);
		textY = frameY +  gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		g2.setFont(yoster_s);
		g2.setColor(header);
		textX = frameX + gp.getTileSize();
		textY += absatz *2;
		int textXlinks = textX;
		
		g2.drawString("Entwicklung", textX, textY);
		g2.setFont(yoster_xs);
		g2.setColor(Color.white);
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
		g2.setColor(header);
		g2.drawString("Grafikdesign", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.setColor(Color.white);
		g2.drawString("- Marika Uhrig", textX, textY);
		textY += absatz;
		g2.drawString("- Karoline Schiemann", textX, textY);
		g2.setFont(yoster_s);
		textX = textXlinks;
		textY = textYlinks + 2*absatz;
		int textYrechts = textY;
		g2.setColor(header);
		g2.drawString("Stimmen", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.setColor(Color.white);
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
		g2.setColor(header);
		g2.drawString("Sounddesign", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.setColor(Color.white);
		g2.drawString("- Max Rosenhauer", textX, textY);
		textY += 2 * abstand;
		textX -= gp.getTileSize();
		g2.setFont(yoster_s);
		g2.setColor(header);
		g2.drawString("Ober'be'lehrer", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.setColor(Color.white);
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
		boolean hellTransparent = false;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight, transparent, hellTransparent, false);
		
		int abstand = gp.getTileSize() + gp.getTileSize() / 4;
		int abstandSub = gp.getTileSize() + gp.getTileSize() / 2;
		int absatz = gp.getTileSize() * 2;
		int auswahlAbstand = gp.getTileSize() / 2 + gp.getTileSize() / 8 + 10 ;
		
		switch(subState) {
		case 0: options_top(abstand, absatz, frameX, frameY, auswahlAbstand); break;
		case 1: options_fullScreenNotification(frameX, frameY, auswahlAbstand, abstandSub, absatz); break;
		case 2: options_control(absatz, abstandSub, frameX, frameY, auswahlAbstand); break;
		case 3: options_endGameConfirmation(absatz, abstandSub, frameX, frameY, auswahlAbstand); break;
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
		// Soundeffekte
		textY += abstand;
		g2.setColor(white);
		if(commandNum == 3) {
			g2.setColor(signal);
		}
		g2.drawString("Hud", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				if(hudOn) {
					hudOn = false;		
				}
				else if (!hudOn) {
					hudOn = true;
				}
			}
		}
		
		// Steuerung
		textY += abstand;
		g2.setColor(white);
		if(commandNum == 4) {
			g2.setColor(signal);
		}
		g2.drawString("Steuerung", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		// Spiel beenden
		textY += abstand;
		g2.setColor(white);
		if(commandNum == 5) {
			g2.setColor(signal);
		}
		g2.drawString("Spiel beenden", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX - auswahlAbstand, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		// Zurück
		textY += absatz - 12;
		g2.setColor(white);
		if(commandNum == 6) {
			g2.setColor(signal);
		}
		g2.drawString("Zurück zum Spiel", textX, textY);
		if(commandNum == 6) {
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
		//g2.setColor(new Color(110, 9, 20));
		g2.setColor(header);
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
		
		// Hud
		textY += abstand;
		g2.drawRect(textX, textY, gp.getTileSize() / 2, gp.getTileSize() / 2);
		if(hudOn) {
			g2.fillRect(textX, textY, gp.getTileSize() / 2, gp.getTileSize() / 2);
		}
		
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
		g2.drawString("MapViewer", textX, textY); textY += abstand;
		g2.drawString("Debug", textX, textY); textY += abstand;
		
		g2.setFont(yoster_s);
		g2.setColor(header);
		textX = frameX + gp.getTileSize() * 7;
		textY = frameY + gp.getTileSize() * 2 + abstand;
		g2.drawString("WASD", textX, textY); textY += abstand;
		g2.drawString("ENTER", textX, textY); textY += abstand;
		g2.drawString("ESC", textX, textY); textY += abstand;
		g2.drawString("M", textX, textY); textY += abstand;
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
				commandNum = 4;
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
				commandNum = 5;
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
		boolean transparent = false;
		boolean hellTransparent = true;
		
		drawSubWindow(x, y, width, height, transparent, hellTransparent, false);
		
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
	public void drawPlayStartScreen() {
		String text;
		int frameX = gp.getTileSize() * 6;
		int frameY = gp.getTileSize() * 4; 
		int textX;
		int textY;
		boolean transparenz = true;
		boolean hellTransparent = false;
		drawSubWindow(frameX, frameY, gp.getTileSize() * 20, gp.getTileSize() * 10, transparenz, hellTransparent, false);
		int abstand = gp.getTileSize();
		int absatz = gp.getTileSize() / 4 + gp.getTileSize() / 2;
		int umbruch = absatz / 2 + absatz / 4 + absatz /8;
		g2.setFont(yoster_sl);
		text = "Herzlich Willkommen!";
		textX = getXforCenteredText(text);
		textY = frameY + gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		g2.setFont(yoster_s);
		g2.setColor(header);
		textX = frameX + gp.getTileSize();
		textY += absatz *2;
		g2.drawString("Es ist Freitag, 16:57 Uhr...", textX, textY);
		textY += abstand;
		g2.setColor(Color.WHITE);
		g2.setFont(yoster_xs);
		g2.drawString("Du hattest fünf Blöcke von mir reingedrückt bekommen", textX, textY);
		textY += umbruch;
		g2.drawString("und jetzt willste mit deiner Restkraft auch noch aufs", textX, textY);
		textY += umbruch;
		g2.drawString("Klo. Aber wie du siehst: Das Klo wurde in der Zwischen-", textX, textY);
		textY += umbruch;
		g2.drawString("zeit von außen abgeschlossen, also musst du ir-gend-wie", textX, textY);
		textY += umbruch;
		g2.drawString("hier einen Schlüssel finden. Schauste dich mal um, wenn", textX, textY);
		textY += umbruch;
		g2.drawString("du Probleme hast, kommste wieder...", textX, textY);
		textY += 2 * absatz;
		g2.setFont(yoster_s);
		g2.setColor(signal);
		textX += gp.getTileSize();
		g2.drawString("Weiter", textX, textY);
		textX -= gp.getTileSize();
		g2.drawString(">", textX, textY);
		
	
	
	}
	public void drawEndScreen() {
		String text;
		int frameX = gp.getTileSize() / 2;
		int frameY = gp.getTileSize() / 2; 
		int textX;
		int textY;
		boolean transparenz = true;
		boolean hellTransparent = false;
		drawSubWindow(frameX, frameY, gp.getTileSize() * 31, gp.getTileSize() * 17, transparenz, hellTransparent, false);
		int abstand = gp.getTileSize();
		int absatz = gp.getTileSize() / 4 + gp.getTileSize() / 2;
		int umbruch = absatz / 2 + absatz / 4;
		g2.setFont(yoster_sl);
		text = "Gratulation! Du bist draußen!";
		textX = getXforCenteredText(text);
		textY = frameY + gp.getTileSize() + gp.getTileSize()/2;
		g2.drawString(text, textX, textY);
		g2.setFont(yoster_s);
		g2.setColor(header);
		textX = frameX + gp.getTileSize();
		textY += absatz *2;
		int textXlinks = textX;
		g2.drawString("Vielen Dank, dass du LostInDathe gespielt hast.", textX, textY);
		g2.setFont(yoster_xs);
		textX += gp.getTileSize();
		textY += abstand;
		g2.setColor(Color.white);
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
		g2.setColor(header);
		g2.drawString("Das Team", textX, textY);
		textY += abstand;
		textX += gp.getTileSize();
		g2.setFont(yoster_xs);
		g2.setColor(Color.white);
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
		g2.setColor(header);
		g2.drawString("Mitwirkende Lehrkräfte", textX, textY);
		textY += abstand;
		g2.setColor(Color.white);
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
	
	public BufferedImage setupHud(String imagePath) {
			
		UtilityTool uTool = new UtilityTool();
		BufferedImage Sprite = null;
		try {
			Sprite = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
			Sprite = uTool.getScaledImage(Sprite, gp.getTileSize(), gp.getTileSize());
				
		} catch (IOException e) {
			e.printStackTrace();	
		}
		return Sprite;
	}
	
	public void drawHud() {
		int subX = gp.getTileSize()*25-5;
		int subY = gp.getTileSize()*16+10;
		int subWidth = gp.getTileSize()*7-4;
		int subHeight = gp.getTileSize();
		int imageHeight = gp.getTileSize()/2+5;
		int imageWidth = gp.getTileSize()/2+5;
		int imageY = subY + 10;
		int imageX = subX + 10;
		int textX = imageX + gp.getTileSize()/2+8;
		int textY = imageY + 25;
		int abstand = gp.getTileSize()/2+8;
		
		if(gp.getPlayer().getHasKey() == 1) { 
			drawSubWindow(subX, subY, subWidth, subHeight*2-19, false, false, true);
			g2.drawImage(key, imageX, imageY, imageWidth, imageHeight, null);
			g2.setColor(Color.black);
			g2.setFont(yoster_xs);
			g2.drawString("Kloschlüssel", textX, textY);
			g2.drawString("> Geh' in R 317", textX, textY + abstand);
		}
		if(gp.getPlayer().getHasKey() == 2 && gp.getCurrentMap() <= 1 && gp.getPlayer().getHasKeyChemie() == 0) {
			drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
			g2.setColor(Color.black);
			g2.setFont(yoster_xs);
			g2.drawString("> Geh' in R 317", textX, textY + gp.getTileSize()/2+5);
		}
		if(gp.getPlayer().getHasKeyChemie() == 1 && gp.getCurrentMap() <= 2 && gp.getPlayer().getCurrentKöppelDialog()  < 7 && !gp.getPlayer().isHasKeyInfo()) {
			drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
			g2.drawImage(key, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
			g2.setColor(Color.black);
			g2.setFont(yoster_xs);
			g2.drawString("R 309 [Chemie]", textX, textY + gp.getTileSize()/2+5);
		}
		if(gp.getPlayer().getHasKeyChemie() == 1) { 
			if(gp.getPlayer().getCurrentKöppelDialog() == 4) {
				drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
				g2.drawImage(phenol, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
				g2.setColor(Color.black);
				g2.setFont(yoster_xs);
				g2.drawString("Phenolphthalein", textX, textY + gp.getTileSize()/2+5);
			} 
			else if(gp.getPlayer().getCurrentKöppelDialog() == 5) {
				drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
				g2.drawImage(universal, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
				g2.setColor(Color.black);
				g2.setFont(yoster_xs);
				g2.drawString("Universalindikator", textX, textY + gp.getTileSize()/2+5);
			}
			else if(gp.getPlayer().hatSäure && gp.getPlayer().getCurrentKöppelDialog() != 2 
					&& (gp.getPlayer().getCurrentKöppelDialog() == 6 || gp.getPlayer().getCurrentKöppelDialog() == 7)) {
				drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
				g2.drawImage(säure, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
				g2.setColor(Color.black);
				g2.setFont(yoster_xs);
				g2.drawString("Saure Lösung", textX, textY + gp.getTileSize()/2+5);
			}
			else if(gp.getPlayer().hatNeutral && gp.getPlayer().getCurrentKöppelDialog() != 2 
					&& (gp.getPlayer().getCurrentKöppelDialog() == 6 || gp.getPlayer().getCurrentKöppelDialog() == 7)) {
				drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
				g2.drawImage(neutral, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
				g2.setColor(Color.black);
				g2.setFont(yoster_xs);
				g2.drawString("Neutrale Lösung", textX, textY + gp.getTileSize()/2+5);
			}
			else if(gp.getPlayer().getCurrentKöppelDialog() > 5 && (gp.getPlayer().getCurrentBaseDialog() == 1 ||
					gp.getPlayer().getCurrentBaseDialog() == 3)) {
				drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
				g2.drawImage(base, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
				g2.setColor(Color.black);
				g2.setFont(yoster_xs);
				g2.drawString("Base", textX, textY + gp.getTileSize()/2+5);
			}
		}
		
		if(gp.getPlayer().isHasKeyInfo() == true && gp.getCurrentMap() != 3 && !gp.getPlayer().isHasKeyBio()) { 
			drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
			g2.drawImage(key, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
			g2.setColor(Color.black);
			g2.setFont(yoster_xs);
			g2.drawString("R 115 [Informatik]", textX, textY + gp.getTileSize()/2+5);
		}
		
		if(gp.getPlayer().isHasKeyBio() == true && gp.getCurrentMap() != 6 && !gp.getPlayer().isHasKeySchulhof()) { 
			drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
			g2.drawImage(key, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
			g2.setColor(Color.black);
			g2.setFont(yoster_xs);
			g2.drawString("R 110 [Bio]", textX, textY + gp.getTileSize()/2+5);
		}
		
		if(gp.getPlayer().isHasKeySchulhof() == true && gp.gameState != gp.endState) { 
			drawSubWindow(subX, subY+gp.getTileSize()/2+5, subWidth, subHeight, false, false, true);
			g2.drawImage(key, imageX, imageY + gp.getTileSize()/2+5, imageWidth, imageHeight, null);
			g2.setColor(Color.black);
			g2.setFont(yoster_xs);
			g2.drawString("Schulhofschlüssel", textX, textY + gp.getTileSize()/2+5);
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

	public void drawSubWindow(int x, int y, int width, int height, boolean transparent, boolean hellTransparent, boolean clear) {
		int strokeWidth = 3;
		int bogen = 35;
		
		
		if(hellTransparent) {
			Color c = new Color(0, 0, 0, 160);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, bogen, bogen);
		}
		
		else if(transparent == true) {
			Color c = new Color(0, 0, 0, 220);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, bogen, bogen);
		}
		
		else if(clear == true) {
			Color c = new Color(0, 0, 0, 0);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, bogen, bogen);
		}
		
		else {
			Color c = new Color(0, 0, 0);
			g2.setColor(c);
			g2.fillRoundRect(x, y, width, height, bogen, bogen);
		}
		
		Color d = new Color(177, 0, 57);
		g2.setColor(d);
		g2.setStroke(new BasicStroke(strokeWidth)); 
		g2.drawRoundRect(x + strokeWidth, y + strokeWidth, width - 2 * strokeWidth, height - (2 * strokeWidth), 25, 25);
	}


	
}

	
