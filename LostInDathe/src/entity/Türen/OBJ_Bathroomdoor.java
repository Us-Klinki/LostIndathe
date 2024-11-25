package entity.Türen;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Bathroomdoor extends Entity {
	
	
	public OBJ_Bathroomdoor(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Bathroomdoor");
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(true);
		
		

	}
	
	public void setDialogue1() {
		dialogues[0][0] = "Zum Schulflur. Auf die Gefahr hin, dass ich \nmich wiederhole: Auch für diesen Raum brauchst \ndu einen Schlüssel.";
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
