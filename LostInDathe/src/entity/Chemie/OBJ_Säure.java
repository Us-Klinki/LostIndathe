package entity.Chemie;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Säure extends Entity {
	
	public OBJ_Säure(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Säure");
		idle1 = setup("/npc/objects/chemie/Säure");
		idle2 = setup("/npc/objects/chemie/Säure");
		setCollisionOn(true);
		setSolidArea(new Rectangle(0, 0, 48, 48));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
	
	public void setDialogue1() {
		dialogues[4][0] = "";
		
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
