package entity.Chemie;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Phenolphthalein extends Entity {
	
	public OBJ_Phenolphthalein(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Phenolphthalein");
		idle1 = setup("/npc/objects/chemie/Phenolphthalein");
		idle2 = setup("/npc/objects/chemie/Phenolphthalein");
		setCollisionOn(true);
		setSolidArea(new Rectangle(0, 0, 48, 48));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
	
	public void setDialogue1() {
		dialogues[4][0] = "Ich habe eine Indikatorlösung gefunden...\nIch sollte sie zügig zu Frau Köppel bringen.";
		
	}
	
	public void setDialogue2() {
		dialogues[4][0] = "Bevor ich hier irgendwas anfasse, \nsollte ich besser mit Frau Köppel sprechen.";
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
	
	

	

}
