package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import entity.Entity;

public class OBJ_Statue extends SuperObject {
	
	public OBJ_Statue(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Statue");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/statue.png")));
			uToolObjects.getScaledImage(getImage(), gp.getTileSize(), gp.getTileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
		setSolidArea(new Rectangle(12, 12, 24, 24));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
	
	public void setDialogue1() {
		dialogues[0][0] = "Ich. Will. Zum. Licht.";
		dialogues[0][1] = "Sonne! Sommer! Kaktus!";
	}
	
	public void setDialogue2() {
		dialogues[0][0] = "Ich bin so dankbar... Gehe zu Priebe...";
	}
	
	public void setDialogue3() {
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j < 20; j++) {
				dialogues[i][j] = null;
			}
		}
	}
	public void speak(int i) {
		super.speak(i);
	}
	
	public void move(SuperObject object, String direction, int speed) {
	    switch (direction) {
        case "up":
            object.setWorldY(object.getWorldY() - speed);  
            break;
        case "down":
            object.setWorldY(object.getWorldY() + speed); 
            break;
        case "left":
            object.setWorldX(object.getWorldX() - speed);  
            break;
        case "right":
            object.setWorldX(object.getWorldX() + speed);  
            break;
	    }
	}

	// TODO: Ziehen
	public void pull(SuperObject object, Entity player, int speed) {
		int deltaX = player.worldX - object.getWorldX();
	    int deltaY = player.worldY - object.getWorldY();

        // Calculate the distance to the player
	    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	    // Normalize the direction vector if distance is greater than zero
        if (distance > 40) {
        	// Normalize the direction vector
	        double normalizedX = deltaX / distance;
	        double normalizedY = deltaY / distance;
	        // Move the object towards the player
	        object.setWorldX(object.getWorldX() + (int)(normalizedX * speed));
	        object.setWorldY(object.getWorldY() + (int)(normalizedY * speed));
        }
	}

}
