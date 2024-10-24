package entity;

import java.util.Random;

import main.GamePanel;


public class NPC_Test extends Entity{
	
	public NPC_Test(GamePanel gp) {
		super(gp);
		setName("test");
		direction = "";
		speed = 1;
		
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/oldman_up_1");
	up2 = setup("/npc/oldman_up_2");
	down1 = setup("/npc/oldman_down_1");
	down2 = setup("/npc/oldman_down_2");
	left1 = setup("/npc/oldman_left_1");
	left2 = setup("/npc/oldman_left_2");
	right1 = setup("/npc/oldman_right_1");
	right2 = setup("/npc/oldman_right_2");

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
}
