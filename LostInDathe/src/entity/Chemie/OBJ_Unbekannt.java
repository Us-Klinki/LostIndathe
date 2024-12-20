package entity.Chemie;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Unbekannt extends Entity {
	
	public static final String objName = "Unbekannt";
	
	public OBJ_Unbekannt(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/chemie/Unbekannt");
		idle2 = setup("/npc/objects/chemie/Unbekannt");
		setCollisionOn(false);
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
