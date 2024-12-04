package entity.Bio;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class NPC_Maus extends Entity{
	
	public static final String npcName = "maus";
	
	public NPC_Maus(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName(npcName);
		direction = "";
		speed = 6;
		setCollisionOn(false);
		setSolidArea(new Rectangle(22, 24, 8, 16));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/Maus/Maus_Hinten1");
	up2 = setup("/npc/Maus/Maus_Hinten2");
	down1 = setup("/npc/Maus/Maus_Vorne1");
	down2 = setup("/npc/Maus/Maus_Vorne2");
	left1 = setup("/npc/Maus/Maus_links1");
	left2 = setup("/npc/Maus/Maus_links2");
	right1 = setup("/npc/Maus/Maus_rechts1");
	right2 = setup("/npc/Maus/Maus_rechts2");
	idle1 = setup("/npc/Maus/Maus_Hinten1");
	idle2 = setup("/npc/Maus/Maus_Hinten1");

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
		
		if(getActionLockCounter() == 30) {
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
