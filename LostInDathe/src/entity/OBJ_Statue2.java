package entity;

import java.awt.Rectangle;


import main.GamePanel;
import main.KeyHandler;

public class OBJ_Statue2 extends Entity {
	
	public static final String objName = "Statuezwei";
	
	public OBJ_Statue2(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/Statue2");
		idle2 = setup("/npc/objects/Statue2");
		setCollisionOn(true);
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		setDialogue1();
	}
	
	public void setDialogue1() {
		dialogues[2][0] = "Ich hab die Sonne so vermisst...\nSchau, wie golden ich bin!\nVielen Dank dir!";
		dialogues[2][1] = "Geh' zu Herrn Priebe!\nEr wird dich gütig belohnen!";
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
