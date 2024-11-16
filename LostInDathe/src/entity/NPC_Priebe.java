package entity;

import java.util.Random;

import main.GamePanel;
import main.KeyHandler;

public class NPC_Priebe extends Entity {
	
	
	public NPC_Priebe(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName("priebe");
		direction = "";
		speed = 1;
		
		getNPCImage();
		setDialogue();
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
	
	public void setDialogue() {
		dialogues[2][0] = "Das Sonnenlicht fehlt wohl.";
		dialogues[2][1] = "Sie ist sooo blass...";
		dialogues[2][2] = "Wir haben nicht ewig Zeit.";
		dialogues[2][3] = "Hilf ihr doch!!!";
		dialogues[2][4] = "Danke für deine Hilfe! \nIch habe gehört, in der Chemie gibt es Probleme.\nSchau dort mal vorbei, hier haste nen Schlüssel.";
		
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
	
	public void speak() {
		super.speak();
	}
}
