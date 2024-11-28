package entity.Chemie;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Säure extends Entity {
	
	public static final String objName = "Säure";
	
	public OBJ_Säure(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/chemie/Säure");
		idle2 = setup("/npc/objects/chemie/Säure");
		setCollisionOn(false);
		setSolidArea(new Rectangle(0, 0, 48, 48));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
	
	public void setDialogue1() {
		dialogues[4][0] = "Ich habe eine saure Lösung gefunden.\nIch sollte mit Frau Köppel sprechen.";
		
	}
	public void setDialogue2() {
		dialogues[4][0] = "Bevor ich hier irgendwas anfasse, \nsollte ich besser mit Frau Köppel sprechen.";
	}
	public void setDialogue3() {
		dialogues[4][0] = "Ich brauche dieses Objekt nicht.";
	}
	
	public void setDialogue4() {
		dialogues[4][0] = "Ich trage bereits eine Lösung mit mir und\nsollte zunächst mit Frau Köppel sprechen.";
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
