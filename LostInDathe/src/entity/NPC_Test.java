package entity;

import java.util.Random;



import main.GamePanel;
import main.KeyHandler;

public class NPC_Test extends Entity{ //Man schaft es mit dem NPC das Game zu crashen aber schwer
	
	public static final String npcName = "Test";
	
	public NPC_Test(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName(npcName);
		direction = "";
		speed = 1;
		
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/Test/oldman_up_1");
	up2 = setup("/npc/Test/oldman_up_2");
	down1 = setup("/npc/Test/oldman_down_1");
	down2 = setup("/npc/Test/oldman_down_2");
	left1 = setup("/npc/Test/oldman_left_1");
	left2 = setup("/npc/Test/oldman_left_2");
	right1 = setup("/npc/Test/oldman_right_1");
	right2 = setup("/npc/Test/oldman_right_2");
	idle1 = setup("/npc/Test/oldman_down_1");
	idle2 = setup("/npc/Test/oldman_down_2");

	}
	//Dialog Test 
	
	public void setDialogue1() {
		dialogues[0][0] = "Hello World!";
		dialogues[0][1] = "Adventurer";
		dialogues[0][2] = "You fool";
		dialogues[0][3] = "You have to save the village, it burn in flames an we dont know what to do. You have to leave the school and play more \nvideo games to find a way to help us. Please i beg u.";
		
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
