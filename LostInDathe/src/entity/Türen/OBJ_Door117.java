package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Door117 extends Entity {
	
	
	public OBJ_Door117(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Door117");
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[5][0] = "Toilette für männlich gelesene Personen.\nHier wirst du keinen Schlüssel nach draußen \nfinden!";
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