package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Door316a extends Entity {
	
	
	public OBJ_Door316a(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Door316a");
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[1][0] = "Toilette für männlich gelesene Personen.\nHier wirst du keinen Schlüssel nach draußen \nfinden!";
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