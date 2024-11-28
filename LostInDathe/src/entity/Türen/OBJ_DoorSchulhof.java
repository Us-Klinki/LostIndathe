package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_DoorSchulhof extends Entity {
	
	public static final String objName = "DoorSchulhof";
	
	public OBJ_DoorSchulhof(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[5][0] = "Wo willstn du jetzt hin? Aufn Schulhof? Jut.\nHaste mir am Anfang nicht zugehört?";
		dialogues[5][1] = "Hier brauchst du einen Schlüssel,\num rauszukommen. Hast du einen?\nNein.";
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