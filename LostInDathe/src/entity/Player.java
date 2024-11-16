package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	
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
		
		worldX = gp.getTileSize() * 20;
		worldY = gp.getTileSize() * 25;
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

	    double diagonalSpeed = speed / Math.sqrt(2); // Geschwindigkeit bei diagonaler Bewegung

	    // Initialisierung der x- und y-Bewegung
	    double moveX = 0;
	    double moveY = 0;

	    // Überprüfe Kollisionen der Objekte zum aufheben
	    int objectIndex = gp.cChecker.checkObject(this, true); // Check object collision first
	    pickUpObject(objectIndex);
	    
	    // Überprüfe Kollision der NPCs zum interagieren
	    int npcIndex = gp.cChecker.checkEntity(this, gp.getNpc());
	    if(keyH.enterPressed) {
			interactNPC(npcIndex);
	    }
	    
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
	    // Position aktualisieren
	    worldX += moveX;
	    worldY += moveY;

	    
			
			
		
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
					gp.getObj()[gp.getCurrentMap()][i].setCollision(false);
					hasKey--;
					System.out.println("Schlüssel: " + hasKey);
				}
				else if(gp.getObj()[gp.getCurrentMap()][i].isCollision() == true && keyH.enterPressed){
			        System.out.println("Nicht genug Schlüssel");
			    }
				break;
			case "Toilet":
				if(gp.getObj()[gp.getCurrentMap()][i].isKey_inside() && keyH.enterPressed) {	//TODO: Indikator für Enter  drücken
					gp.playSE(1);
					hasKey++;
					gp.gameState = gp.dialogueState;
					if(gp.getObj()[gp.getCurrentMap()][i].isKey_inside()) {
						gp.getObj()[gp.getCurrentMap()][i].setDialogue1();
						gp.getObj()[gp.getCurrentMap()][i].speak(i);

					}
					gp.getObj()[gp.getCurrentMap()][i].setKey_inside(false);
				}
				else if(keyH.enterPressed) {
					gp.gameState = gp.dialogueState;
					gp.getObj()[gp.getCurrentMap()][i].setDialogue2();
					gp.getObj()[gp.getCurrentMap()][i].speak(i);
				}
			}
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			gp.gameState = gp.dialogueState;
			
			/*if(getName() == "priebe") {
				gp.getNpc()[gp.getCurrentMap()][i].speak(1);
			}
			else {*/
				gp.getNpc()[gp.getCurrentMap()][i].speak();
			//}
		}
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
}
