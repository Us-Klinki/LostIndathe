package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Chemiedoor extends Entity {
	
	public static final String objName = "Chemiedoor";
	
	public OBJ_Chemiedoor(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);

	}
	
	public void setDialogue1() {
		dialogues[1][0] = "Chemieraum 309. Auf die Gefahr hin, dass ich \nmich wiederhole: Auch für diesen Raum brauchst \ndu einen Schlüssel.";
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