package entity;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Toilet extends Entity {
	
	public OBJ_Toilet(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Toilet");
		idle1 = setup("/npc/objects/toilet");
		idle2 = setup("/npc/objects/toilet");
		setCollisionOn(true);
	}
	
	public void setDialogue1() {
		dialogues[0][0] = "Congratulations";
		dialogues[0][1] = "Yo found:";
		dialogues[0][2] = "A key";
	}
	
	public void setDialogue2() {
		dialogues[0][0] = "There is nothing to find here";
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
