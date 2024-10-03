package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
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
		
		worldX = gp.getTileSize() * 23;
		worldY = gp.getTileSize() * 21;
		speed = 4;
		direction = "down";
		
	}
	public void getPlayerImage() { //TODO: HIER NOCH ECHTE PLAYER-TEXTUR EINFÜGEN!!!
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Hinten1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Hinten2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Vorne1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Vorne2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Links1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Links2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Rechts1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Rechts2.png"));
			tleft1 = ImageIO.read(getClass().getResourceAsStream("/player/HintenLinks1.png"));
			tleft2 = ImageIO.read(getClass().getResourceAsStream("/player/HintenLinks2.png"));
			tright1 = ImageIO.read(getClass().getResourceAsStream("/player/HintenRechts1.png"));
			tright2 = ImageIO.read(getClass().getResourceAsStream("/player/HintenRechts2.png"));
			bleft1 = ImageIO.read(getClass().getResourceAsStream("/player/VorneLinks1.png"));
			bleft2 = ImageIO.read(getClass().getResourceAsStream("/player/VorneLinks2.png"));
			bright1 = ImageIO.read(getClass().getResourceAsStream("/player/VorneRechts1.png"));
			bright2 = ImageIO.read(getClass().getResourceAsStream("/player/VorneRechts2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() { // Methode wird 60-mal pro Sekunde aufgerufen

	    double diagonalSpeed = speed / Math.sqrt(2); // Geschwindigkeit bei diagonaler Bewegung

	    // Initialisierung der x- und y-Bewegung
	    double moveX = 0;
	    double moveY = 0;

	    // Überprüfe Kollisionen der Objekte
	    int objectIndex = gp.cChecker.checkObject(this, true); // Check object collision first
	    pickUpObject(objectIndex);
	    
	    setCollisionOn(false);

	    // Vertikale Bewegungsrichtung prüfen
	    if (keyH.upPressed) {
	        direction = "up";
	        moveY -= speed; // Bewege temporär nach oben
	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der oberen Bewegung
	        gp.cChecker.checkObject(this, true);
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

	    // Position aktualisieren
	    worldX += moveX;
	    worldY += moveY;

	    
			
			
		
	    // Richtung setzen
		if(moveX != 0 || moveY != 0) {
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
		}
		else {
			spriteNum = 1;
		}
	}
	
	// Aufsammeln / Interagieren mit Objekten
	public void pickUpObject(int i) { 
		
		if (i != 999) {
			
			String objectName = gp.getObj()[i].getName();
			switch(objectName) {
			case "Key":
				hasKey++; // virtuelles Inventar
				gp.getObj()[i] = null;
				System.out.println("Schlüssel: " + hasKey);
				break;
			case "Bathroomdoor":
				//TODO: Bugfix benötigt: Wenn man keinen Schlüssel hat, ist es möglich, sich zu softlocken...
				if(hasKey > 0) {
					gp.getObj()[i] = null;
					hasKey--;
				}
				else {
			        System.out.println("Nicht genug Schlüssel");
			    }
				System.out.println("Schlüssel: " + hasKey);
				break;
			}
			
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
		}
		g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
	}
}
