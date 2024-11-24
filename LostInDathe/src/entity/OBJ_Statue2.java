package entity;

import java.awt.Rectangle;


import main.GamePanel;
import main.KeyHandler;

public class OBJ_Statue2 extends Entity {
	
	public OBJ_Statue2(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Statuezwei");
		idle1 = setup("/npc/objects/Statue2");
		idle2 = setup("/npc/objects/Statue2");
		setCollisionOn(true);
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		setDialogue1();
	}
	
	public void setDialogue1() {
		dialogues[2][0] = "*Ächz*...\nWärme, Licht, ich taue auf.\nSchau, wie golden ich bin!";
		dialogues[2][1] = "Geh zu Priebe";
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
