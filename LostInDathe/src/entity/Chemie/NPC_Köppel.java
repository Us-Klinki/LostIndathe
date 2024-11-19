package entity.Chemie;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class NPC_Köppel extends Entity {
	
	
	public NPC_Köppel(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName("Köppel");
		direction = "";
		speed = 0;
		setCollisionOn(true);
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/Köppel/Köppel_Hinten");
	up2 = setup("/npc/Köppel/Köppel_Hinten");
	down1 = setup("/npc/Köppel/Köppel_Stehend1");
	down2 = setup("/npc/Köppel/Köppel_Stehend2");
	left1 = setup("/npc/Köppel/Köppel_Links");
	left2 = setup("/npc/Köppel/Köppel_Links");
	right1 = setup("/npc/Köppel/Köppel_Rechts");
	right2 = setup("/npc/Köppel/Köppel_Rechts");
	idle1 = setup("/npc/Köppel/Köppel_Stehend1");
	idle2 = setup("/npc/Köppel/Köppel_Stehend2");

	}
	//Dialog Test 
	
	public void setDialogue1() {
		dialogues[4][0] = "Das Sonnenlicht fehlt wohl.";		
	}
	
	public void setDialogue2() {
		dialogues[4][0] = "Sie ist sooo blass...";
	}
	
	public void setDialogue3() {
		dialogues[4][0] = "Wir haben nicht ewig Zeit.";
	}
	
	public void setDialogue4() {
		dialogues[4][0] = "Hilf ihr doch!!!";
	}
	
	public void setDialogue5() {
		dialogues[4][0] = "Danke für deine Hilfe! \nIch habe gehört, in der Chemie gibt es Probleme.\nSchau dort mal vorbei, hier haste nen Schlüssel.";
	}
	
	public void setDialogue20() {
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j < 20; j++) {
				dialogues[i][j] = null;
			}
		}
	}
	
	
	public void setAction() {
		
		setActionLockCounter(getActionLockCounter()+1);
		
		if(getActionLockCounter() == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; 
			if(i <= 25) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			setActionLockCounter(0);
		}
	}
	
	public void speak(int i) {
		super.speak(i, true);
	}
}
