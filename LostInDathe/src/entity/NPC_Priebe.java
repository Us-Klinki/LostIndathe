package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;
import main.KeyHandler;

public class NPC_Priebe extends Entity {
	
	
	public NPC_Priebe(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName("priebe");
		direction = "";
		speed = 1;
		setCollisionOn(true);
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/Priebe/Priebe_Hinten1");
	up2 = setup("/npc/Priebe/Priebe_Hinten2");
	down1 = setup("/npc/Priebe/Priebe_Vorne1");
	down2 = setup("/npc/Priebe/Priebe_Vorne2");
	left1 = setup("/npc/Priebe/Priebe_Links1");
	left2 = setup("/npc/Priebe/Priebe_Links2");
	right1 = setup("/npc/Priebe/Priebe_Rechts1");
	right2 = setup("/npc/Priebe/Priebe_Rechts2");
	idle1 = setup("/npc/Priebe/Priebe_Stehend1");
	idle2 = setup("/npc/Priebe/Priebe_Stehend2");

	}
	//Dialog Test 
	
	public void setDialogue1() {
		dialogues[2][0] = "Das Sonnenlicht fehlt wohl.";		
	}
	
	public void setDialogue2() {
		dialogues[2][0] = "Sie ist sooo blass...";
	}
	
	public void setDialogue3() {
		dialogues[2][0] = "Wir haben nicht ewig Zeit.";
	}
	
	public void setDialogue4() {
		dialogues[2][0] = "Hilf ihr doch!!!";
	}
	
	public void setDialogue5() {
		dialogues[2][0] = "Danke für deine Hilfe! \nIch habe gehört, in der Chemie gibt es Probleme.\nSchau dort mal vorbei, hier haste nen Schlüssel.";
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
