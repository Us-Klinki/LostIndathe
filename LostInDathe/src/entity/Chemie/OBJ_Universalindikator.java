package entity.Chemie;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class OBJ_Universalindikator extends Entity {
	
	public static final String objName = "Universalindikator";
	
	public OBJ_Universalindikator(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/chemie/Universalindikator");
		idle2 = setup("/npc/objects/chemie/Universalindikator");
		setCollisionOn(false);
		setSolidArea(new Rectangle(0, 0, 48, 48));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		setDialogue1();
	}
	
	public void setDialogue1() {
		dialogues[4][0] = "Ich habe Universalindikatorlösung gefunden...\nIch sollte sie zügig zu Frau Köppel bringen\nund ihn gleich in die Lösung geben.";
		
		
	}
	
	public void setDialogue2() {
		dialogues[4][0] = "Diese Universalindikatorlösung nützt mir nichts \nmehr.";
	}
	
	
	
	public void setDialogue20() {
		for(int i = 0; i < 30; i++) {
			for(int j = 1; j < 20; j++) {
				dialogues[i][j] = null;
			}
		}
	}
	public void speak(int i) {
		super.speak(i, false);
	}
	
	

	

}
