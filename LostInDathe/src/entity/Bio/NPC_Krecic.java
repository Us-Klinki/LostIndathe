package entity.Bio;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class NPC_Krecic extends Entity{
	
	public static final String npcName = "krecic";
	
	public NPC_Krecic(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName(npcName);
		direction = "";
		speed = 1;
		setCollisionOn(true);
		setSolidArea(new Rectangle(8, 20, 32, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/Krecic/Krecic_Hinten1");
	up2 = setup("/npc/Krecic/Krecic_Hinten2");
	down1 = setup("/npc/Krecic/Krecic_Vorne1");
	down2 = setup("/npc/Krecic/Krecic_Vorne2");
	left1 = setup("/npc/Krecic/Krecic_Links1");
	left2 = setup("/npc/Krecic/Krecic_Links2");
	right1 = setup("/npc/Krecic/Krecic_Rechts1");
	right2 = setup("/npc/Krecic/Krecic_Rechts2");
	idle1 = setup("/npc/Krecic/Krecic_Stehend1");
	idle2 = setup("/npc/Krecic/Krecic_Stehend2");

	}
	//Dialog Test 
	
	public void setDialogue1() {
		dialogues[6][0] = "Och, nee… Ich kann das nicht mehr… \nIch will mehr Geld für den Quatsch!";
		dialogues[6][1] = "Hast du etwa den Käfig offengelassen? \nDu darfst die gleich wieder einsammeln. \nAber zackig! Du weißt ja, wie spät es ist.";
	}
	
	public void setDialogue2() {
		dialogues[6][0] = "Mach doch mal!!!";
	}
	
	public void setDialogue3() {
		dialogues[6][0] = "Hmm... Not bad! Das gibt dann die 1 Plus! \nWillst auch nach Hause, oder? Komm, \nschnapp dir mal den Schlüssel hier, der passt. ";
	}
	
	public void setDialogue4() {
		dialogues[6][0] = "Na geht doch! \nUnd jetzt husch husch raus hier.";
		dialogues[6][1] = "Ausnahmsweise haste mal den Schlüssel \nfür den Außenhof. Aber keinen Quatsch machen!";
	}
	
	public void setDialogue5() {
		dialogues[6][0] = "Ich hab mich jetzt gerade mental schon von \nmeiner Rente verabschiedet, so lang hat das\nhier gedauert.";
		dialogues[6][1] = "[Sie gibt dir trotzdem einen Schlüssel für den \nAußenhof!]";
	}
	public void setDialogue6() {
		dialogues[6][0] = "Och jetzt muss ich hier aufräumen…\nIch hab dafür die Nerven nich...";
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
