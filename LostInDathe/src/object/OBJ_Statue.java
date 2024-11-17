package object;

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
	
	public void move(String direction, int speed) {
	    Entity.moveObject(this, direction, speed); 
	}


	public void pull(String direction, int speed) {
		Entity.pullObject(this, direction, speed);
		
	}

}
