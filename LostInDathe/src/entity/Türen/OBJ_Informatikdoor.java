package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Informatikdoor extends Entity {
	
	
	public OBJ_Informatikdoor(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Informatikdoor");
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[5][0] = "Informatikraum 115.\nIch benötige einen Schlüssel, um hier herein \nzu kommen...";
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