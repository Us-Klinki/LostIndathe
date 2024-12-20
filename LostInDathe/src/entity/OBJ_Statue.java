package entity;

import java.awt.Rectangle;


import main.GamePanel;
import main.KeyHandler;

public class OBJ_Statue extends Entity {
	
	public static final String objName = "Statue";
	
	public OBJ_Statue(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/Statue1");
		idle2 = setup("/npc/objects/Statue1");
		down1 = setup("/npc/objects/Statue1");
		down2 = setup("/npc/objects/Statue1");
		up1 = setup("/npc/objects/Statue1");
		up2 = setup("/npc/objects/Statue1");
		left1 = setup("/npc/objects/Statue1");
		left2 = setup("/npc/objects/Statue1");
		right1 = setup("/npc/objects/Statue1");
		right2 = setup("/npc/objects/Statue1");
		setCollisionOn(true);
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
	
	public void setDialogue1() {
		dialogues[2][0] = "Ich. Will. Zum. Licht. Kannst du mir nicht helfen?\nMit ',' kannst du mich schieben \nund mit '.' ziehen.";
		dialogues[2][1] = "Sonne! Sommer! Kaktus!";
		dialogues[2][2] = "Also was ist jetzt? Ich warte...";
	}
	
	public void setDialogue2() {
		dialogues[2][0] = "Das ist mir noch zu dunkel...";
	}
	
	public void setDialogue20() {
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j < 20; j++) {
				dialogues[i][j] = null;
			}
		}
	}
	public void speak(int i) {
		super.speak(i, false);
	}
	
	public void move(Entity object, String direction1, int speed) {
		int moveX = 0;
		int moveY = 0;
		
		setCollisionOn(false);
			switch (direction1) {
        	case "up":
    	        direction = "up";
    	        moveY -= speed; // Bewege temporär nach unten
    	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der unteren Bewegung
    	        gp.cChecker.checkObject(this, false);
    	        gp.cChecker.checkEntity(this, gp.getNpc());
    	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
    	            moveY = 0; // Setze Bewegung zurück
    	            setCollisionOn(false);
    	        }
        		break;
        	case "down":
    	        direction = "down";
    	        moveY += speed; // Bewege temporär nach unten
    	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der unteren Bewegung
    	        gp.cChecker.checkObject(this, false);
    	        gp.cChecker.checkEntity(this, gp.getNpc());
    	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
    	            moveY = 0; // Setze Bewegung zurück
    	            setCollisionOn(false);
    	        }
        		break;
        	case "left":
    	        direction = "left";
    	        moveX -= speed; // Bewege temporär nach unten
    	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der unteren Bewegung
    	        gp.cChecker.checkObject(this, false);
    	        gp.cChecker.checkEntity(this, gp.getNpc());
    	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
    	            moveX = 0; // Setze Bewegung zurück
    	            setCollisionOn(false);
    	        }
        		break;
        	case "right":
    	        direction = "right";
    	        moveX += speed; // Bewege temporär nach unten
    	        gp.cChecker.checkTile(this); // Prüfe Kollision mit der unteren Bewegung
    	        gp.cChecker.checkObject(this, false);
    	        gp.cChecker.checkEntity(this, gp.getNpc());
    	        if (isCollisionOn()) { // Wenn eine Kollision vorliegt, Bewegung zurücksetzen
    	            moveX = 0; // Setze Bewegung zurück
    	            setCollisionOn(false);
    	        }
        		break;
	    	}
			worldX += moveX;
			worldY += moveY;
		this.setCollisionOn(true);

	}

	// TODO: Ziehen
	public void pull(Entity object, Entity player, int speed) {
		int deltaX = player.worldX - object.worldX;
	    int deltaY = (int) (player.worldY - object.worldY);

        // Calculate the distance to the player
	    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	    // Normalize the direction vector if distance is greater than zero
        if (distance > 45) {
        	// Normalize the direction vector
	        double normalizedX = deltaX / distance;
	        double normalizedY = deltaY / distance;
	        // Move the object towards the player
	        object.worldX = object.worldX + (int)(normalizedX * speed);
	        object.worldY = object.worldY + (int)(normalizedY * speed);
        }
	}

}
