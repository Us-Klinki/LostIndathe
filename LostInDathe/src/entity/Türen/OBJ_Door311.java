package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Door311 extends Entity {
	
	public static final String objName = "Door311";
	
	public OBJ_Door311(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[1][0] = "Chemieraum 311.\nHier scheint nichts spannendes zu passieren...";
	}
	
	public void setDialogue2() {
		dialogues[1][0] = "Dein Ernst jetzt?\nDa ist je jeglicher Hopfen und Malz verloren, \nwas die Auffassungsgabe angeht.";
		dialogues[1][1] = "Du sollst in Raum 309: 3-0-9;\nnicht in Raum 311: 3-1-1...";
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