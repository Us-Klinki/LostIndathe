package entity;

import java.awt.Rectangle;
import java.util.Random;


import main.GamePanel;
import main.KeyHandler;

public class NPC_Klinkhardt extends Entity {
	
	
	public NPC_Klinkhardt(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName("Klinki");
		direction = "";
		speed = 0;
		setCollisionOn(true);
		setSolidArea(new Rectangle(0, 20, 48, 28));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getNPCImage();
	}
	public void getNPCImage() { 
		
	up1 = setup("/npc/Klinki/Klinkhardt_Stehend1");
	up2 = setup("/npc/Klinki/Klinkhardt_Stehend2");
	down1 = setup("/npc/Klinki/Klinkhardt_Vorne1");
	down2 = setup("/npc/Klinki/Klinkhardt_Vorne2");
	left1 = setup("/npc/Klinki/Klinkhardt_Stehend1");
	left2 = setup("/npc/Klinki/Klinkhardt_Stehend2");
	right1 = setup("/npc/Klinki/Klinkhardt_Stehend1");
	right2 = setup("/npc/Klinki/Klinkhardt_Stehend2");
	idle1 = setup("/npc/Klinki/Klinkhardt_Stehend1");
	idle2 = setup("/npc/Klinki/Klinkhardt_Stehend2");

	}
	
	public void setDialogue1() {
		dialogues[3][0] = "Komm mir nicht zu nahe!";
	}
	
	public void setDialogue2() {
		dialogues[3][0] = "Jetzt mach doch mal das Licht an Kind, \nich kann ja gar nichts sehen.";
	}
	
	public void setDialogue3() {
		dialogues[3][0] = "Was machst du denn?! Hier stehe ICH!";
	}
	
	public void setDialogue4() {
		dialogues[3][0] = "Merkst du nicht, dass du dich im Dunkeln besser \nnicht bewegen solltest?";
	}
	
	public void setDialogue5() {
		dialogues[3][0] = "Ach herrjemine, du willst Abitur machen?! \nJetzt denk doch mal ganz simpel…";
	}
	
	public void setDialogue6() {
		dialogues[3][0] = "Schau mal auf der Tastatur nach dem 'L'icht…";
	}
	
	public void setDialogue7() {
		dialogues[3][0] = "Na endlich! Deine Klassenarbeit war übrigens \nnicht so prickelnd… Und dein Schriftbild… \nIch konnt' nicht mal deinen Namen lesen…";
		dialogues[3][1] = "Das ist mir in 25 Jahren noch nicht passiert.\nSo und jetzt raus hier!! \nNerv lieber mal Frau Krecic im Bioraum 110.";
	}
	
	public void setDialogue8() {
		dialogues[3][0] = "Mach dich nützlich.";
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
