package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Door112 extends Entity {
	
	public static final String objName = "Door112";
	
	public OBJ_Door112(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[5][0] = "Bioraum 112.\nHier wirst du keinen Schlüssel nach draußen \nfinden!";
	}
	
	public void setDialogue2() {
		dialogues[5][0] = "110 und 112... Ist wie Polizei und Feuerwehr...\nKann man ja mal verwechseln... 110 ist Polizei,\n112 ist Feuerwehr. So.";
		dialogues[5][1] = "Ganz im Ernst: R 110 1-1-0 ist ein Stückchen\nweiter rechts.";
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