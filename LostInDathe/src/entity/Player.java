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
import main.UI;

public class Player extends Entity {

	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	int hasKeyChemie = 0;
	boolean hasKeyInfo = false;
	boolean holErstenIndikator = false;
	boolean hatErstenIndikator = false;
	public boolean holZweitenIndikator = false;
	boolean hatZweitenIndikator = false;
	public boolean IndikatorReaktion = false;
	boolean hatSäure = false;
	boolean hatBase = false;
	boolean hatNeutral = false;
	public boolean holLösung = false;
	boolean holSäure = false;
	boolean holNeutral = false;
	boolean basePlacen = false;
	boolean chemieGelöst = false;
	int fehler = 0;
	//boolean Universalindikator = false;
	public int dialogueCounter = 1;
	boolean oneTimeDialogue = true;
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
		//worldX = gp.getTileSize() * 20;
		//worldY = gp.getTileSize() * 25;
		
		// IM CHEMIERAUM
		worldX = gp.getTileSize() *  52;
		worldY = gp.getTileSize() * 64;
		
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
	    else {
		    playerMovement();
	    }
	    
	    pullStatue();
		
	    
	    // Richtung setzen
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
			case "Key":
				gp.playSE(1);
				hasKey++; // virtuelles Inventar
				gp.getObj()[gp.getCurrentMap()][i] = null;
				System.out.println("Schlüssel: " + hasKey);
				break;
			case "Bathroomdoor":
				if(hasKey > 0 && keyH.enterPressed) {		//TODO: Indikator für Enter  drücken
					gp.playSE(0);
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
					hasKey--;
					System.out.println("Schlüssel: " + hasKey);
				}
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed){
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					
			        System.out.println("Nicht genug Schlüssel");
			    }
				break;
			case "Informatikdoor":
				if(hasKeyChemie > 0 && keyH.enterPressed) {
					gp.playSE(0);
					gp.getObj()[gp.getCurrentMap()][i].setCollisionOn(false);
					hasKeyChemie--;
					//System.out.println("Schlüssel: " + hasKey);
				}
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollisionOn() == true && keyH.enterPressed){
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			case "Toilet":
				if(gp.getObj()[gp.getCurrentMap()][i].isKeyInside() && keyH.enterPressed) {	//TODO: Indikator für Enter  drücken
					gp.playSE(10);
					hasKey++;
					gp.gameState = gp.dialogueState;
					if(gp.getObj()[gp.getCurrentMap()][i].isKeyInside()) {
						gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);

					}
					gp.getObj()[gp.getCurrentMap()][i].setKeyInside(false);
				}
				else if(keyH.enterPressed) {
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
			
				if(keyH.enterPressed && gp.gameState == gp.playState && holErstenIndikator == true) {
					hatErstenIndikator = true;
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					System.out.println("hatErstenIndikator = " + hatErstenIndikator);
					System.out.println("hatZweitenIndikator = " + hatZweitenIndikator);
					System.out.println("holZweitenIndikator = " + holZweitenIndikator);
				}
				if(keyH.enterPressed && gp.gameState == gp.playState && holErstenIndikator == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				break;
			case "Base":
				if(keyH.enterPressed && gp.gameState == gp.playState && holLösung == false && chemieGelöst == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holLösung == true && hatSäure == false && hatNeutral == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holLösung = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && chemieGelöst == true) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holLösung = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holLösung == true) {
					if(hatSäure == true || hatBase == true || hatNeutral == true) {
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue4();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
				}
				
				break;
			case "Säure":
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holSäure == false && chemieGelöst == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holSäure == true && hatBase == false && hatNeutral == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holSäure = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && chemieGelöst == true) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holSäure = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holSäure == true) {
					if(hatSäure == true || hatBase == true || hatNeutral == true) {
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue4();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
				}
				break;
			case "Neutral":
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holNeutral == false && chemieGelöst == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holNeutral == true && hatSäure == false && hatBase == false) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holNeutral = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && chemieGelöst == true) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					holNeutral = false;
				}
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holNeutral == true) {
					if(hatSäure == true || hatBase == true || hatNeutral == true) {
						gp.gameState = gp.dialogueState;
						gp.getObj()[gp.getCurrentMap()][i].setDialogue4();
						gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
					}
				}
				break;
			case "Universalindikator":
				
				if(keyH.enterPressed && gp.gameState == gp.playState && holZweitenIndikator == true) {
					hatZweitenIndikator = true;
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
					gp.getObj()[gp.getCurrentMap()][i].speak(i, false);
				}
				if(keyH.enterPressed && gp.gameState == gp.playState && holZweitenIndikator == false) {
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
			gp.gameState = gp.dialogueState;
			String npcName = gp.getNpc()[gp.getCurrentMap()][i].getName();
			switch(npcName) {
			case "priebe":
				if(EventHandler.gesGelöst == true) {
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue5();
					gp.playSE(10);
					hasKeyChemie++;
				}
				else {
					switch(dialogueCounter) {
						case 1:
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
							//System.out.println("Hallo");
							break;
						case 2:
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue2();
							//System.out.println("Ballo");
							break;
						case 3: 
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue3();
							break;
						case 4:
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue4();
							break;
					}
				}
				gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				if(dialogueCounter < 4) {
					dialogueCounter++;
				}
				else {
					dialogueCounter = 1;
				}
				break;
			case "Köppel":
				/*if(EventHandler.gesGelöst == true) {
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue5();
					gp.playSE(10);
					hasKeyChemie++;
				}
				else {*/

				switch(dialogueCounter) {
					case 1:
						if(holErstenIndikator == false) {
						holErstenIndikator = true;
						gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
						}
							//System.out.println("Hallo");
						break;
					case 2:
						gp.getNpc()[gp.getCurrentMap()][i].setDialogue2();
							//System.out.println("Ballo");
						break;
					case 3: 
						gp.getNpc()[gp.getCurrentMap()][i].setDialogue3();
						break;
				}
				
				if(hatErstenIndikator == true && hatZweitenIndikator == false) {
			
					if(holZweitenIndikator == false) {
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue4();
				
					}
				}
				
				if(holZweitenIndikator == true) {
					//holZweitenIndikator = false;
					hatZweitenIndikator = true;
					if(hatSäure == false || hatBase == false || hatNeutral == false) {
						if(holLösung == false) {
							gp.getNpc()[gp.getCurrentMap()][i].setDialogue5();
							if(hatZweitenIndikator) {
							holLösung = true;
							holSäure = true;
							holNeutral = true;
							}
						}
					}
				}
				
				if(hatErstenIndikator == true && hatZweitenIndikator == false) {
					if(holZweitenIndikator == false) {
							holZweitenIndikator = true;
				
					}
				}
				
				
				
				
				if(hatSäure == true) {
					fehler++;
					if(fehler == 1) {
						gp.getNpc()[gp.getCurrentMap()][i].setDialogue6();
					}
					if(fehler == 2) {
						gp.getNpc()[gp.getCurrentMap()][i].setDialogue7();
					}
					hatSäure = false;
				}
				
				if(hatNeutral == true) {
					fehler++;
					if(fehler == 1) {
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue6();
					}
					if(fehler == 2) {
						gp.getNpc()[gp.getCurrentMap()][i].setDialogue7();
					}
					hatNeutral = false;
				}
				
				if(hatBase == true) {
					gp.getNpc()[gp.getCurrentMap()][i].setDialogue8();
					hasKeyInfo = true;
				}
				
				
				gp.gameState = gp.dialogueState;
				gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				
				
				
				//if(hatErstenIndikator == false) {
					if(dialogueCounter < 3) {
						dialogueCounter++;
					}
					else {
						dialogueCounter = 2;
					}
				//}
				
				
				
				break;
			case "Test":
				gp.getNpc()[gp.getCurrentMap()][i].setDialogue1();
				gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
				break;
			}
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
	    	direction = "";
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
	    
	    if(moveX == 0 && moveY == 0 && !keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed) {
	    	direction = "";
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
}

