package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();

	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
		
	}
	public void getPlayerImage() { //TODO: HIER NOCH ECHTE PLAYER-TEXTUR EINFÜGEN!!!
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/oben.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/oben2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/unten.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/unten2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/links.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/links2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/rechts.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/rechts2.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() { // Methode wird 60-mal pro Sekunde aufgerufen
		
	    double diagonalSpeed = speed/ Math.sqrt(2); // Geschwindigkeit bei diagonaler Bewegung
	    
	    // Initialisierung der x- und y-Bewegung
	    double moveX = 0;
	    double moveY = 0;

	    // Horizontale Bewegungsrichtung
	    if (keyH.leftPressed) {
	        moveX -= speed; // Normale Geschwindigkeit
	    }
	    if (keyH.rightPressed) {
	        moveX += speed; // Normale Geschwindigkeit
	    }

	    // Vertikale Bewegungsrichtung
	    if (keyH.upPressed) {
	        moveY -= speed; // Normale Geschwindigkeit
	    }
	    if (keyH.downPressed) {
	        moveY += speed; // Normale Geschwindigkeit
	    }

	    // Diagonalbewegung: Wenn sowohl horizontal als auch vertikal bewegt wird, normalisieren
	    if (moveX != 0 && moveY != 0) {
	        double totalMovement = Math.sqrt(moveX * moveX + moveY * moveY);
	        moveX = (moveX / totalMovement) * diagonalSpeed;
	        moveY = (moveY / totalMovement) * diagonalSpeed;
	    }

	    // Position aktualisieren
	    x += moveX;
	    y += moveY;

	    // Richtung setzen
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
	
		
		
		/*if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
		
			if(keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			if(keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
			if(keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			if(keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			} */
			/*else if(keyH.rightPressed == true && keyH.upPressed == true) {
				direction = "tright";
				x += speed/2;
				y -= speed/2;
			}
			else if(keyH.rightPressed == true && keyH.downPressed == true) {
				direction = "bright";
				x += speed/2;
				y += speed/2;
			}
			else if(keyH.leftPressed == true && keyH.upPressed == true) {
				direction = "tleft";
				x -= speed/2;
				y -= speed/2;
			}
			else if(keyH.leftPressed == true && keyH.downPressed == true) {
				direction = "tright";
				x -= speed/2;
				y += speed/2;
			}*/
		
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
	
	
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.RED);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);	
		
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
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
