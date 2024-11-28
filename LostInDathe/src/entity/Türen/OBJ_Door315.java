package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Door315 extends Entity {
	
	public static final String objName = "Door315";
	
	public OBJ_Door315(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Door315");
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[1][0] = "Matheraum 315. Du bist ermüdet, ich weiß... So, \nAchtung Wortwitz; Da würde dir eher ein Mate als \nein Matematikraum guttun. Nicht schlecht, 'ne?";
		dialogues[1][1] = "Hier wirst du auf jeden Fall nie-man-den finden.";
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