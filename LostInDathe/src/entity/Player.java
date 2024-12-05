package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import main.EventHandler;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	public final int screenX;
	public final int screenY;
	
	// Schlüssel
	private int hasKey = 0;
	private int hasKeyChemie = 0;
	private boolean hasKeyInfo = false;
	private boolean hasKeyBio = false;
	private boolean hasKeySchulhof = false;
	
	// Rätsel Chemie
	private int currentKöppelDialog = 1;
	private int currentBaseDialog = 2;
	private int currentNeutralDialog = 2;
	private int currentSäureDialog = 2;
	private int currentPhenolphthaleinDialog = 2;
	private int currentUniversalindikatorDialog = 1;
	public boolean hatSäure = false;
	public boolean hatNeutral = false;
	public boolean holZweitenIndikator = false;
	public boolean holLösung = false;
	public boolean basePlacen = false;
	private int fehler = 1;
	
	// Rätsel Informatik
	private int currentKlinkiDialog = 1;
	private boolean licht = false;
	
	//Rätsel Bio
	private int Mäusefang = 0;	
	private int countdown = 0;
	private int counter = 0;
	private int currentKrecicDialogue = 1;
	private boolean timerstart;
	private boolean krecicStart;
	
	int a = 0;
	int b = 0;
	int c = 0;
	boolean isSEPlaying = false;
	private int idleCountdown = 30;
	public int dialogueCounter = 1;
	Font yoster;

	
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp, keyH);
		
		this.keyH = keyH;

		screenX = this.gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
		screenY = this.gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);
		
		
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		
		
		setDefaultValues();
		getPlayerImage();

	}
	
	public void setDefaultValues() {
		// TODO: IM BAD
		worldX = gp.getTileSize() * 20;
		worldY = gp.getTileSize() * 25;
		
		// IM CHEMIERAUM
		//worldX = gp.getTileSize() *  52;
		//worldY = gp.getTileSize() * 64;
		
		// IM BIORAUM
		//worldX = gp.getTileSize() * 47;
		//worldY = gp.getTileSize() * 44;
		
		// IM INFORAUM
		//worldX = gp.getTileSize() * 40;
		//worldY = gp.getTileSize() * 22.5;
		
		// IM EG vor der Exit-Door 
		//worldX = gp.getTileSize() * 77;
		//worldY = gp.getTileSize() * 59;
		
		speed = 4;
		direction = "";
		
	}
	public void getPlayerImage() { 
	
		up1 = setup("/player/Hinten1");
		up2 = setup("/player/Hinten2");
		down1 = setup("/player/Vorne1");
		down2 = setup("/player/Vorne2");
		left1 = setup("/player/Links1");
		left2 = setup("/player/Links2");
		right1 = setup("/player/Rechts1");
		right2 = setup("/player/Rechts2");
		tleft1 = setup("/player/HintenLinks1");
		tleft2 = setup("/player/HintenLinks2");
		tright1 = setup("/player/HintenRechts1");
		tright2 = setup("/player/HintenRechts2");
		bleft1 = setup("/player/VorneLinks1");
		bleft2 = setup("/player/VorneLinks2");
		bright1 = setup("/player/VorneRechts1");
		bright2 = setup("/player/VorneRechts2");
		idle1 = setup("/player/Stehend1");
		idle2 = setup("/player/Stehend2");
	}
	
	
	public void update() { // Methode wird 60-mal pro Sekunde aufgerufen
		/*if(oneTimeDialogue == true) {
			setDialogue1();
			speak(0, false);
			oneTimeDialogue = false;
		}*/

			

	    // Überprüfe Kollisionen der Objekte zum aufheben
	    int objectIndex = gp.cChecker.checkObject(this, true); // Check object collision first
	    pickUpObject(objectIndex);
	    
	    int npcIndex = gp.cChecker.checkEntity(this, gp.getNpc());
	    if(keyH.enterPressed) {
			interactNPC(npcIndex);
	    }
	    if(distanceStatue(this, gp.getObj()[2][0]) < 100 && keyH.pullPressed) {
	    	speed /= 2;
		    playerMovementPull();
		    speed = 4;
	    }
	    else if(gp.gameState == gp.playState){
		    playerMovement();
	    }
	    
	    pullStatue();
		
	    //Bio Rätsel Countdown
	    if(gp.getCurrentMap() == 6 && !isHasKeySchulhof()) {
	    	bioCountdown();
	    	if(isTimerstart() == true) {
	    		setCounter(getCounter() + 1);
	    		if(getCounter() >= 60) {
	    			setCountdown(getCountdown() - 1);
	    			setCounter(0);
	    		}
	    	}
	    }

		// Check Event
		gp.eHandler.checkEvent();
	}
	
	public void setDialogue1() {
		dialogues[0][0] = "Es ist Freitag, 16:57... Ich bin so erschöpft und\nwollte nur noch kurz auf Klo, aber...\nDie Tür ist plötzlich verschlossen!";
		dialogues[0][1] = "Ich will hier raus... Ich sollte mich umsehen -\nvielleicht kann ich irgendwas finden, das mir weiterhilft.";
	}
	
	
	public void setDialogue20() {
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j < 20; j++) {
				dialogues[i][j] = null;
			}
		}
	}
	
	// Aufsammeln / Interagieren mit Objekten
	public void pickUpObject(int i) { 
		if (i != 999) {
			
			String objectName = gp.getObj()[gp.getCurrentMap()][i].getName();
			switch(objectName) {
			case "Sink":
				if (keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(51);
				}
				break;
			case "Key":
				gp.playSE(1);
				setHasKey(getHasKey() + 1); // virtuelles Inventar
				gp.getObj()[gp.getCurrentMap()][i] = null;
				System.out.println("Schlüssel: " + getHasKey());
				break;
			case "Bathroomdoor":
				if(getHasKey() == 1 && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					//TODO: Indikator für Enter  drücken
					gp.playSE(50);
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
					setHasKey(getHasKey() + 1);
					System.out.println("Schlüssel: " + getHasKey());
				}
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed){
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(29);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					
			        System.out.println("Nicht genug Schlüssel");
			    }
				break;
			case "Informatikdoor":
				if(isHasKeyInfo() && keyH.enterPressed && gp.getObj()[gp.getCurrentMap()][i].isCollisionOn()) {
					
					gp.playSE(50);
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
					c = 1;
				}
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed){
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(29);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(c == 1) {
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
				}
				break;
			
			case "Chemiedoor":
				
				if(getHasKeyChemie() > 0 && keyH.enterPressed && gp.getObj()[gp.getCurrentMap()][i].isCollisionOn()) {
					
					gp.playSE(50);
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
					b = 1;
				}
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed){
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(29);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(b == 1) {
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
				}
				break;
				
			
			case "Biodoor":
				
				if(isHasKeyBio() && keyH.enterPressed && gp.getObj()[gp.getCurrentMap()][i].isCollisionOn()) {
					gp.playSE(50);
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
					a = 1;
				}
				
				if(a == 1) {
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
				}
				
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(29);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			
			case "Door112":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed && !isHasKeyBio()) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(28);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed && isHasKeyBio()) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(31);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "Door117":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(28);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "Door311":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed && getHasKeyChemie() == 0) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(53);
					gp.playSE(28);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed && getHasKeyChemie() == 1) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					if(gp.getCurrentMap() == 1) {
						gp.playSE(53);
						gp.playSE(30);
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
					if(gp.getCurrentMap() == 4) {
						gp.playSE(53);
						gp.playSE(30);
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
				}
			break;
			
			case "Door312":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(53);
					gp.playSE(25);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "Door314":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(53);
					gp.playSE(25);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "Door315":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(26);
					gp.playSE(53);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "Door316a":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(53);
					gp.playSE(28);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "DoorMZH":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(53);
					gp.playSE(28);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
			break;
			
			case "DoorSchulhof":
				if(isHasKeySchulhof() && keyH.enterPressed && gp.getObj()[gp.getCurrentMap()][i].isCollisionOn()) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.stopMusic(4);
					gp.playSE(50);
					gp.playSE(33);
					gp.playMusic(0);
					gp.gameState = gp.endState;
				}
				
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
						gp.stopSE(j);
					}
					gp.playSE(53);
					gp.playSE(32);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			
			case "DoorVorbereitung":
				if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed) {
					if(gp.getCurrentMap() == 1) {
						for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
							gp.stopSE(j);
						}
						gp.playSE(53);
						gp.playSE(27);
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
					if(gp.getCurrentMap() == 4) {
						for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
							gp.stopSE(j);
						}
						gp.playSE(53);
						gp.playSE(27);
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
					if(gp.getCurrentMap() == 5) {
						for(int j = 0; j < gp.getSoundURLLengthGP(); j++) {
							gp.stopSE(j);
						}
						gp.playSE(53);
						gp.playSE(27);
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
				}
				
			break;
			
			case "Toilet":
				if(gp.getObj()[gp.getCurrentMap()][i].isKeyInside() && keyH.enterPressed) {	//TODO: Indikator für Enter  drücken
					gp.stopSE(23);
					gp.playSE(1);
					gp.playSE(10);
					gp.playSE(24);
					setHasKey(getHasKey() + 1);
					gp.gameState = gp.dialogueState;
					if(gp.getObj()[gp.getCurrentMap()][i].isKeyInside()) {
						gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);

					}
					gp.getObj()[gp.getCurrentMap()][i].setKeyInside(false);
				}
				else if(keyH.enterPressed) {
					gp.stopSE(23);
					gp.playSE(1);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			case "Statue":
				
				if (keyH.pushPressed) {
                    speed = speed/2;
                    gp.getObj()[gp.getCurrentMap()][i].move(gp.getObj()[gp.getCurrentMap()][i], direction, speed);
                   	speed = 4;
                }
				if(EventHandler.gesZuDunkel == true) {
					gp.getObj()[gp.getCurrentMap()][i].setDialogue20();
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
				}
				else {
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
				}
				if(keyH.enterPressed) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			case "Statuezwei":
				
				if(keyH.enterPressed && gp.gameState == gp.playState) {
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.gameState = gp.dialogueState;
					
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			
			case "Phenolphthalein":
			
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentPhenolphthaleinDialog() == 1) {
					//hatErstenIndikator = true;
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					setCurrentKöppelDialog(4);
				}
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentPhenolphthaleinDialog() == 2) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);

				}
				break;
			case "Base":
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentBaseDialog() == 2) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentBaseDialog() == 1) {
					setCurrentNeutralDialog(4);
					setCurrentSäureDialog(4);
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holLösung = false;
					setCurrentKöppelDialog(8);
					setCurrentBaseDialog(3);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentBaseDialog() == 3) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holLösung = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentBaseDialog() == 4) {
					gp.playSE(54);	
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue4();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);

				}
				
				break;
			case "Säure":
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentSäureDialog() == 2) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				
				
				if (keyH.enterPressed && gp.gameState == gp.playState && getCurrentSäureDialog() == 1) {
					gp.playSE(54);
					setCurrentBaseDialog(4);
					setCurrentNeutralDialog(4);
				    gp.gameState = gp.dialogueState;
				    gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
				    gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				    hatSäure = true;
				    if(getFehler() == 1) {
						setCurrentKöppelDialog(6);
					}
					if(getFehler() == 2) {
						setCurrentKöppelDialog(7);
					}
					if(getFehler() < 2) {
						setFehler(getFehler() + 1);
					}
				    setCurrentSäureDialog(3);
				} 
				if (keyH.enterPressed && gp.gameState == gp.playState && getCurrentSäureDialog() == 3) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
				    gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
				    gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentSäureDialog() == 4) {
					gp.playSE(54);	
					gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue4();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			case "Neutral":
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentNeutralDialog() == 2) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentNeutralDialog() == 1) {
					gp.playSE(54);
					setCurrentBaseDialog(4);
					setCurrentSäureDialog(4);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					//holNeutral = false;
					hatNeutral = true;
					if(getFehler() == 1) {
						setCurrentKöppelDialog(6);
					}
					if(getFehler() == 2) {
						setCurrentKöppelDialog(7);
					}
					if(getFehler() < 2) {
						setFehler(getFehler() + 1);
					}
					setCurrentNeutralDialog(3);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentNeutralDialog() == 3) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentNeutralDialog() == 4) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue4();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);

				}
				break;
			case "Universalindikator":
				
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentUniversalindikatorDialog() == 1) {
					gp.playSE(54);
					setCurrentKöppelDialog(5);
					setCurrentUniversalindikatorDialog(getCurrentUniversalindikatorDialog() + 1);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				if(keyH.enterPressed && gp.gameState == gp.playState && getCurrentUniversalindikatorDialog() == 2) {
					gp.playSE(54);
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			}
		}
	}
	
	public double distanceStatue(Entity player, Entity object) {
		int deltaX = player.worldX - object.worldX;
	    int deltaY = (int) (player.worldY - object.worldY);

        // Calculate the distance to the player
	    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	    return distance;
	}
	
	public void pullStatue() {
		if (gp.getCurrentMap() == 2) {
		    double distance = distanceStatue(this, gp.getObj()[2][0]); 
		    if (distance < 60 && keyH.pullPressed) {
		        gp.getObj()[2][0].pull(gp.getObj()[2][0], this, speed);
		    }
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			String npcName = gp.getNpc()[gp.getCurrentMap()][i].getName();
			switch(npcName) {
			case "priebe":
				if(EventHandler.gesGelöst == true) {
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue4();
					gp.stopSE(14);
					gp.playSE(14);
					if(getHasKeyChemie() == 0) {
						gp.playSE(10);
					}
					setHasKeyChemie(getHasKeyChemie() + 1);
				}
				else {
					switch(dialogueCounter) {
						case 1:
							gp.stopSE(13);
							gp.playSE(11);
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();

							break;
						case 2:
							gp.stopSE(11);
							gp.playSE(12);
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue2();

							break;
						case 3: 
							gp.stopSE(12);
							gp.playSE(13);
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue3();
							break;

					}
				}
				gp.gameState = gp.dialogueState;
				gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				if(dialogueCounter < 3) {
					dialogueCounter++;
				}
				else {
					dialogueCounter = 1;
				}
				break;
			
			case "Köppel":	
				if(getCurrentKöppelDialog() == 2) {
					gp.stopSE(15);
					gp.stopSE(16);
					gp.stopSE(18);
					gp.stopSE(19);
					gp.playSE(16);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue2();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				}
	
				if(getCurrentKöppelDialog() == 1) {
					gp.playSE(15);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentPhenolphthaleinDialog(1);
					setCurrentKöppelDialog(getCurrentKöppelDialog() + 1);
				}

				if(getCurrentKöppelDialog() == 4) {
					gp.stopSE(15);
					gp.stopSE(16);
					gp.playSE(18);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue4();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					holZweitenIndikator = true;
					setCurrentKöppelDialog(2);
		
				}
		
				if (getCurrentKöppelDialog() == 5) {
					gp.stopSE(16);
					gp.stopSE(18);	
					gp.playSE(19);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue5();
					holLösung = true;
					setCurrentBaseDialog(1);
					setCurrentNeutralDialog(1);
					setCurrentSäureDialog(1);
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKöppelDialog(2);
				}
		
				if(getCurrentKöppelDialog() == 6) {
					System.out.println("1");
					gp.stopSE(19);
					gp.stopSE(16);
					gp.playSE(20);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue6();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKöppelDialog(2);
					hatNeutral = false;
					hatSäure = false;
					if(getCurrentSäureDialog() == 4) {
						setCurrentSäureDialog(1);
					}
					setCurrentBaseDialog(1);
					if(getCurrentNeutralDialog() == 4) {
						setCurrentNeutralDialog(1);
					}
				}
				
				if(getCurrentKöppelDialog() == 7) {
					gp.stopSE(16);
					gp.stopSE(20);
					gp.playSE(21);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue7();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKöppelDialog(2);
					setCurrentBaseDialog(1);
				}

				if(getCurrentKöppelDialog() == 8) {
					gp.stopSE(16);
					gp.stopSE(19);
					gp.stopSE(20);
					gp.stopSE(21);
					gp.playSE(22);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue8();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					gp.playSE(10);
					setCurrentKöppelDialog(3);
					setCurrentSäureDialog(3);
					setCurrentNeutralDialog(3);
					setHasKeyInfo(true);
					basePlacen = true;
					return; // Methode wird hier beendet, wenn dieser Fall zutrifft.
				}
		
				if(getCurrentKöppelDialog() == 3) {
					gp.stopSE(22);
					gp.playSE(17);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue3();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				}
				
				break;
				
			case "Klinki":
				if(getCurrentKlinkiDialog() == 8) {
					gp.stopSE(34);
					gp.stopSE(35);
					gp.stopSE(36);
					gp.stopSE(37);
					gp.stopSE(38);
					gp.stopSE(39);
					gp.stopSE(40);
					gp.playSE(41);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue8();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				}
				
				if(keyH.lichtDialog && getCurrentKlinkiDialog() <= 6) {
					keyH.lichtDialog = false;
					setCurrentKlinkiDialog(7);
				}
				if(getCurrentKlinkiDialog() == 7) {
					gp.stopSE(34);
					gp.stopSE(35);
					gp.stopSE(36);
					gp.stopSE(37);
					gp.stopSE(38);
					gp.stopSE(39);
					gp.playSE(40);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue7();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					gp.playSE(10);
					setHasKeyBio(true);
					setCurrentKlinkiDialog(8);
				}
				
				if(getCurrentKlinkiDialog() == 6) {
					gp.stopSE(34);
					gp.stopSE(35);
					gp.stopSE(36);
					gp.stopSE(37);
					gp.stopSE(38);
					gp.playSE(39);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue6();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				}
				
				if(getCurrentKlinkiDialog() == 5) {
					gp.stopSE(34);
					gp.stopSE(35);
					gp.stopSE(36);
					gp.stopSE(37);
					gp.playSE(38);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue5();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKlinkiDialog(getCurrentKlinkiDialog() + 1);
				}
				
				if(getCurrentKlinkiDialog() == 4) {
					gp.stopSE(34);
					gp.stopSE(35);
					gp.stopSE(36);
					gp.playSE(37);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue4();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKlinkiDialog(getCurrentKlinkiDialog() + 1);
				}
				if(getCurrentKlinkiDialog() == 3) {
					gp.stopSE(34);
					gp.stopSE(35);
					gp.playSE(36);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue3();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKlinkiDialog(getCurrentKlinkiDialog() + 1);
				}
				
				if(getCurrentKlinkiDialog() == 2) {
					gp.stopSE(34);
					gp.playSE(35);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue2();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKlinkiDialog(getCurrentKlinkiDialog() + 1);
				}
				
				
				if(getCurrentKlinkiDialog() == 1) {
					gp.playSE(34);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKlinkiDialog(getCurrentKlinkiDialog() + 1);
				}
				break;
			case "Test":
				gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
				gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				break;
			case "krecic":
				switch(getCurrentKrecicDialogue()) {
				case 1:
					gp.playSE(42);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKrecicDialogue(getCurrentKrecicDialogue() + 1);
					setKrecicStart(true);
					setTimerstart(true);
					setCounter(60);
					break;
				case 2: 
					if(getMäusefang() == 5) {
						if(getCountdown() >= 45) {
							setCurrentKrecicDialogue(3);
							break;
						} 
						if(getCountdown() >= 35) {
							setCurrentKrecicDialogue(4);
							break;
						} 
						if(getCountdown() < 35) {
							setCurrentKrecicDialogue(5);
							break;
						} 
					}	
					gp.playSE(43);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue2();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					break;
				case 3:
					gp.playSE(44);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue3();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKrecicDialogue(6);
					gp.playSE(10);
					setHasKeySchulhof(true);
					break;
				case 4:
					gp.playSE(45);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue4();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKrecicDialogue(6);
					gp.playSE(10);
					setHasKeySchulhof(true);
					break;
				case 5:
					gp.playSE(46);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue5();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					setCurrentKrecicDialogue(6);
					gp.playSE(10);
					setHasKeySchulhof(true);
					break;
				case 6:
					gp.playSE(47);
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue6();
					gp.gameState = gp.dialogueState;
					gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
					break;
				}
				break;
			case "maus":
				if(keyH.enterPressed == true && isTimerstart()) {
					gp.getAPlacer().mausDispose(i);
					setMäusefang(getMäusefang() + 1);
				}
				break;
			}
		}
	}
	
	public void bioCountdown() {
		if(isKrecicStart() == true && gp.gameState == gp.playState) {
			setTimerstart(true);
			setCountdown(60);
			setKrecicStart(false);
		}
	}
	public void playerMovement() {
		
	    double diagonalSpeed = speed / Math.sqrt(2); // Geschwindigkeit bei diagonaler Bewegung
	    // Initialisierung der x- und y-Bewegung
	    double moveX = 0;
	    double moveY = 0;
		
	    setCollisionOn(false);

	    // Vertikale Bewegungsrichtung prüfen
	    if (keyH.upPressed) {
	        direction = "up";
	        moveY -= speed; // Bewege temporär nach oben
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der oberen Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveY = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    if (keyH.downPressed) {
	        direction = "down";
	        moveY += speed; // Bewege temporär nach unten
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der unteren Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveY = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    // Horizontale Bewegungsrichtung prüfen
	    if (keyH.leftPressed) {
	        direction = "left";
	        moveX -= speed; // Bewege temporär nach links
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der linken Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveX = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    if (keyH.rightPressed) {
	        direction = "right";
	        moveX += speed; // Bewege temporär nach rechts
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der rechten Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveX = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    
	    //Schrittsound
	    /*if((keyH.rightPressed || keyH.leftPressed || keyH.upPressed || keyH.downPressed) && 
	    		(gp.getCurrentMap() == 1 || gp.getCurrentMap() == 5) && !isSEPlaying) {
	    	isSEPlaying = true; // Markiere, dass der Soundeffekt abgespielt wird
	        gp.playSE(55);

	        // Fügen Sie einen Listener oder Timer hinzu, um isSEPlaying zurückzusetzen, wenn der Soundeffekt endet
	        new Thread(() -> {
	            try {
	                Thread.sleep(200); // Methode zum Abrufen der Sounddauer
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            isSEPlaying = false; // Zurücksetzen, wenn der Sound fertig ist
	        }).start();
	    }
	    if((keyH.rightPressed || keyH.leftPressed || keyH.upPressed || keyH.downPressed) && 
	    		(gp.getCurrentMap() == 0 || (gp.getCurrentMap() >= 2 && gp.getCurrentMap() <= 4) || gp.getCurrentMap() == 6) && !isSEPlaying) {
	    	isSEPlaying = true; // Markiere, dass der Soundeffekt abgespielt wird
	        gp.playSE(6);

	        // Fügen Sie einen Listener oder Timer hinzu, um isSEPlaying zurückzusetzen, wenn der Soundeffekt endet
	        new Thread(() -> {
	            try {
	                Thread.sleep(200); // Methode zum Abrufen der Sounddauer
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            isSEPlaying = false; // Zurücksetzen, wenn der Sound fertig ist
	        }).start();
	    }*/



	    // Diagonalbewegung: Wenn sowohl horizontal als auch vertikal bewegt wird, normalisieren
	    if (moveX != 0 && moveY != 0) {
	        double totalMovement = Math.sqrt(moveX * moveX + moveY * moveY);
	        moveX = (moveX / totalMovement) * diagonalSpeed;
	        moveY = (moveY / totalMovement) * diagonalSpeed;
	    } 

	    // Setze Richtung basierend auf der Bewegung
	    if (moveY < 0) {
	        direction = "up";
	    } else if (moveY > 0) {
	        direction = "down";
	    } 
	    if (moveX < 0) {
	        direction = "left";
	    } else if (moveX > 0) {
	        direction = "right";
	    } 
	    if (moveX > 0 && moveY < 0) {
	        direction = "tright";
	    } else if (moveX > 0 && moveY > 0) {
	        direction = "bright";
	    } else if (moveX < 0 && moveY < 0) {
	        direction = "tleft";
	    } else if (moveX < 0 && moveY > 0) {
	        direction = "bleft";
	    }
	    
	    if(moveX == 0 && moveY == 0 && !keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
	    	idleCountdown--;
	    	if( idleCountdown < 1) {
	    		//Frameabwechslung
				spriteCounter++;
				if(spriteCounter > 12) { // jede 1/5-Sekunde
					if(spriteNum == 1) {
						spriteNum = 2;
					}
					else if(spriteNum == 2) {
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
	    		direction = "";
	    	}
	    }
	    else {
	    	//Frameabwechslung
			spriteCounter++;
			if(spriteCounter > 12) { // jede 1/5-Sekunde
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
	    	idleCountdown = 30;
	    }

	    
	    worldX += moveX;
	    worldY += moveY;
	}
	
	public void playerMovementPull() {
		
	    // Initialisierung der x- und y-Bewegung
	    double moveX = 0;
	    double moveY = 0;
		
	    setCollisionOn(false);

	    // Vertikale Bewegungsrichtung prüfen
	    if (keyH.upPressed) {
	        direction = "up";
	        moveY -= speed; // Bewege temporär nach oben
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der oberen Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveY = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    else if (keyH.downPressed) {
	        direction = "down";
	        moveY += speed; // Bewege temporär nach unten
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der unteren Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveY = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    // Horizontale Bewegungsrichtung prüfen
	    else if (keyH.leftPressed) {
	        direction = "left";
	        moveX -= speed; // Bewege temporär nach links
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der linken Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveX = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    else if (keyH.rightPressed) {
	        direction = "right";
	        moveX += speed; // Bewege temporär nach rechts
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der rechten Bewegung
	        gp.cChecker.checkObject(this, true);
	        gp.cChecker.checkEntity(this, gp.getNpc());
	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
	            moveX = 0; // Setze Bewegung zurück
	            setCollisionOn(false);
	        }
	    }
	    
	    //Schrittsound
	    /*if((keyH.rightPressed || keyH.leftPressed || keyH.upPressed || keyH.downPressed) && (gp.getCurrentMap() == 2) && !isSEPlaying) {
	    	isSEPlaying = true; // Markiere, dass der Soundeffekt abgespielt wird
	        gp.playSE(56);

	        new Thread(() -> {
	            try {
	                Thread.sleep(400); // Methode zum Abrufen der Sounddauer
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            isSEPlaying = false; // Zurücksetzen, wenn der Sound fertig ist
	        }).start();
	    }*/
	    
	    //Frameabwechslung
		spriteCounter++;
		if(spriteCounter > 12) { // jede 1/5-Sekunde
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
	    worldX += moveX;
	    worldY += moveY;
	}
	

	public void draw(Graphics2D g2) {
		
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;	 
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;	
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "tright":
			if(spriteNum == 1) {
				image = tright1;
			}
			if(spriteNum == 2) {
				image = tright2;
			}
			break;
		case "tleft":
			if(spriteNum == 1) {
				image = tleft1;
			}
			if(spriteNum == 2) {
				image = tleft2;
			}
			break;
		case "bright":
			if(spriteNum == 1) {
				image = bright1;
			}
			if(spriteNum == 2) {
				image = bright2;
			}
			break;
		case "bleft":
			if(spriteNum == 1) {
				image = bleft1;
			}
			if(spriteNum == 2) {
				image = bleft2;
			}
			
			break;
		case "":
			if(spriteNum == 1) {
				image = idle1;
			}
			if(spriteNum == 2) {
				image = idle2;
			}
		}
		g2.drawImage(image, screenX, screenY, null);
		
		//DEBUG
		if(keyH.isDebug() == true) {
			g2.setColor(Color.pink);
			g2.drawRect(screenX + getSolidArea().x, screenY + getSolidArea().y, getSolidArea().width, getSolidArea().height);
		}
	
	}

	public void renderInteractionPrompt(Graphics2D g2) {
	    // Setze die Farbe mit Transparenz (z. B. Schwarz mit 50% Deckkraft)
	    Color semiTransparentBlack = new Color(0, 0, 0, 220);
	    g2.setColor(semiTransparentBlack);
	    try (InputStream FontLoader = getClass().getResourceAsStream("/font/yoster.ttf")) {
			 yoster = Font.createFont(Font.TRUETYPE_FONT, FontLoader);
			    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    ge.registerFont(yoster);
			} catch (FontFormatException | IOException e) {
			    e.printStackTrace();
			}
	    yoster = yoster.deriveFont(Font.PLAIN, 40);
	    // Rechteck zeichnen (Position und Größe anpassen)
	    int rectX = gp.getTileSize() * 10; // X-Position
	    int rectY = gp.getTileSize() * 15; // Y-Position
	    int rectWidth = gp.getTileSize() * 12; // Breite
	    int rectHeight = gp.getTileSize() * 2; // Höhe
	    int rectArchWidth = 12;
	    int rectArchHeight = 12;
	    g2.fillRoundRect(rectX, rectY, rectWidth, rectHeight, rectArchWidth, rectArchHeight);
	    
	    // Schriftfarbe setzen (z. B. Weiß)
	    g2.setColor(Color.WHITE);
	    g2.setFont(yoster);

	    // Text zeichnen (zentriert im Rechteck)
	    String text = "Interagieren - ENTER";
	    FontMetrics fm = g2.getFontMetrics();
	    int textX = rectX + (rectWidth - fm.stringWidth(text)) / 2; // Zentrierung
	    int textY = rectY + (rectHeight - fm.getHeight()) / 2 + fm.getAscent(); // Vertikal zentriert
	    g2.drawString(text, textX, textY);
	}
	
	public void renderTreppeNichtBegehbar(Graphics2D g2) {
		Color semiTransparentBlack = new Color(0, 0, 0, 220);
	    g2.setColor(semiTransparentBlack);
	    try (InputStream FontLoader = getClass().getResourceAsStream("/font/yoster.ttf")) {
			 yoster = Font.createFont(Font.TRUETYPE_FONT, FontLoader);
			    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    ge.registerFont(yoster);
			} catch (FontFormatException | IOException e) {
			    e.printStackTrace();
			}
	    yoster = yoster.deriveFont(Font.PLAIN, 40);
	    // Rechteck zeichnen (Position und Größe anpassen)
	    int rectX = gp.getTileSize() * 7; // X-Position
	    int rectY = gp.getTileSize() * 15; // Y-Position
	    int rectWidth = gp.getTileSize() * 18; // Breite
	    int rectHeight = gp.getTileSize() * 2; // Höhe
	    int rectArchWidth = 12;
	    int rectArchHeight = 12;
	    g2.fillRoundRect(rectX, rectY, rectWidth, rectHeight, rectArchWidth, rectArchHeight);
	    
	    // Schriftfarbe setzen (z. B. Weiß)
	    g2.setColor(Color.WHITE);
	    g2.setFont(yoster);

	    // Text zeichnen (zentriert im Rechteck)
	    String text = "Treppe zum 4. OG ist nicht betretbar.";
	    FontMetrics fm = g2.getFontMetrics();
	    int textX = rectX + (rectWidth - fm.stringWidth(text)) / 2; // Zentrierung
	    int textY = rectY + (rectHeight - fm.getHeight()) / 2 + fm.getAscent(); // Vertikal zentriert
	    g2.drawString(text, textX, textY);
	}

	/**
	 * @return the hasKey
	 */
	public int getHasKey() {
		return hasKey;
	}

	/**
	 * @param hasKey the hasKey to set
	 */
	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}

	/**
	 * @return the hasKeyChemie
	 */
	public int getHasKeyChemie() {
		return hasKeyChemie;
	}

	/**
	 * @param hasKeyChemie the hasKeyChemie to set
	 */
	public void setHasKeyChemie(int hasKeyChemie) {
		this.hasKeyChemie = hasKeyChemie;
	}

	/**
	 * @return the hasKeyBio
	 */
	public boolean isHasKeyBio() {
		return hasKeyBio;
	}

	/**
	 * @param hasKeyBio the hasKeyBio to set
	 */
	public void setHasKeyBio(boolean hasKeyBio) {
		this.hasKeyBio = hasKeyBio;
	}

	/**
	 * @return the hasKeyInfo
	 */
	public boolean isHasKeyInfo() {
		return hasKeyInfo;
	}

	/**
	 * @param hasKeyInfo the hasKeyInfo to set
	 */
	public void setHasKeyInfo(boolean hasKeyInfo) {
		this.hasKeyInfo = hasKeyInfo;
	}

	/**
	 * @return the hasKeySchulhof
	 */
	public boolean isHasKeySchulhof() {
		return hasKeySchulhof;
	}

	/**
	 * @param hasKeySchulhof the hasKeySchulhof to set
	 */
	public void setHasKeySchulhof(boolean hasKeySchulhof) {
		this.hasKeySchulhof = hasKeySchulhof;
	}

	/**
	 * @return the currentKöppelDialog
	 */
	public int getCurrentKöppelDialog() {
		return currentKöppelDialog;
	}

	/**
	 * @param currentKöppelDialog the currentKöppelDialog to set
	 */
	public void setCurrentKöppelDialog(int currentKöppelDialog) {
		this.currentKöppelDialog = currentKöppelDialog;
	}

	/**
	 * @return the currentBaseDialog
	 */
	public int getCurrentBaseDialog() {
		return currentBaseDialog;
	}

	/**
	 * @param currentBaseDialog the currentBaseDialog to set
	 */
	public void setCurrentBaseDialog(int currentBaseDialog) {
		this.currentBaseDialog = currentBaseDialog;
	}

	/**
	 * @return the currentNeutralDialog
	 */
	public int getCurrentNeutralDialog() {
		return currentNeutralDialog;
	}

	/**
	 * @param currentNeutralDialog the currentNeutralDialog to set
	 */
	public void setCurrentNeutralDialog(int currentNeutralDialog) {
		this.currentNeutralDialog = currentNeutralDialog;
	}

	/**
	 * @return the currentSäureDialog
	 */
	public int getCurrentSäureDialog() {
		return currentSäureDialog;
	}

	/**
	 * @param currentSäureDialog the currentSäureDialog to set
	 */
	public void setCurrentSäureDialog(int currentSäureDialog) {
		this.currentSäureDialog = currentSäureDialog;
	}

	/**
	 * @return the currentPhenolphthaleinDialog
	 */
	public int getCurrentPhenolphthaleinDialog() {
		return currentPhenolphthaleinDialog;
	}

	/**
	 * @param currentPhenolphthaleinDialog the currentPhenolphthaleinDialog to set
	 */
	public void setCurrentPhenolphthaleinDialog(int currentPhenolphthaleinDialog) {
		this.currentPhenolphthaleinDialog = currentPhenolphthaleinDialog;
	}

	/**
	 * @return the currentUniversalindikatorDialog
	 */
	public int getCurrentUniversalindikatorDialog() {
		return currentUniversalindikatorDialog;
	}

	/**
	 * @param currentUniversalindikatorDialog the currentUniversalindikatorDialog to set
	 */
	public void setCurrentUniversalindikatorDialog(int currentUniversalindikatorDialog) {
		this.currentUniversalindikatorDialog = currentUniversalindikatorDialog;
	}

	/**
	 * @return the fehler
	 */
	public int getFehler() {
		return fehler;
	}

	/**
	 * @param fehler the fehler to set
	 */
	public void setFehler(int fehler) {
		this.fehler = fehler;
	}

	/**
	 * @return the currentKlinkiDialog
	 */
	public int getCurrentKlinkiDialog() {
		return currentKlinkiDialog;
	}

	/**
	 * @param currentKlinkiDialog the currentKlinkiDialog to set
	 */
	public void setCurrentKlinkiDialog(int currentKlinkiDialog) {
		this.currentKlinkiDialog = currentKlinkiDialog;
	}

	/**
	 * @return the licht
	 */
	public boolean isLicht() {
		return licht;
	}

	/**
	 * @param licht the licht to set
	 */
	public void setLicht(boolean licht) {
		this.licht = licht;
	}

	/**
	 * @return the mäusefang
	 */
	public int getMäusefang() {
		return Mäusefang;
	}

	/**
	 * @param mäusefang the mäusefang to set
	 */
	public void setMäusefang(int mäusefang) {
		Mäusefang = mäusefang;
	}

	/**
	 * @return the countdown
	 */
	public int getCountdown() {
		return countdown;
	}

	/**
	 * @param countdown the countdown to set
	 */
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the currentKrecicDialogue
	 */
	public int getCurrentKrecicDialogue() {
		return currentKrecicDialogue;
	}

	/**
	 * @param currentKrecicDialogue the currentKrecicDialogue to set
	 */
	public void setCurrentKrecicDialogue(int currentKrecicDialogue) {
		this.currentKrecicDialogue = currentKrecicDialogue;
	}

	/**
	 * @return the timerstart
	 */
	public boolean isTimerstart() {
		return timerstart;
	}

	/**
	 * @param timerstart the timerstart to set
	 */
	public void setTimerstart(boolean timerstart) {
		this.timerstart = timerstart;
	}

	/**
	 * @return the krecicStart
	 */
	public boolean isKrecicStart() {
		return krecicStart;
	}

	/**
	 * @param krecicStart the krecicStart to set
	 */
	public void setKrecicStart(boolean krecicStart) {
		this.krecicStart = krecicStart;
	}
	
	
}

	
