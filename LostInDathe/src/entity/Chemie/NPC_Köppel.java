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
		setSolidArea(new Rectangle(0, 20, 48, 28));
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
		dialogues[4][0] = "[Selbstgespräch] \nWer hat denn hier schon wieder was rumstehen \nlassen? War das etwa Frau Köppel?";
		dialogues[4][1] = "Ach, du bist auch noch hier. \nDu kannst mir bestimmt behilflich sein! \nIch will die Lösung nicht einfach wegschütten.";
		dialogues[4][2] = "Wer weiß, was das für eine schädliche \nLösung sein könnte. \nHmm... Ich habe da eine Idee!";
		dialogues[4][3] = "Bring mir mal bitte die Indikatorlösung.\nDie müsste dahinten auf einem Tisch stehen \nund hat eine rötliche Färbung...";
	}
	
	public void setDialogue2() {
		dialogues[4][0] = "War deine Aufgabe nicht klar?";
	}
	
	public void setDialogue3() {
		dialogues[4][0] = "Ich habe nicht ewig Zeit.";
	}
	
	public void setDialogue4() {
		dialogues[4][0] = "Das ist der Falsche. Mit Phenolphthalein kann \nich jetzt ja gar nichts anfangen...\n";
		dialogues[4][1] = "Geh noch mal, wir brauchen:\nU-ni-ver-sal-in-di-ka-tor!\nDer müsste auch dahinten sein...";
	}
	
	public void setDialogue5() {
		dialogues[4][0] = "Wie? Der stand genau da, wo der andere stand?\nMerkwürdig... Naja, es ist spät.";
		dialogues[4][1] = "Moment mal, nicht so schnell!\nWo ist denn deine Schutzbrille!\n";
		dialogues[4][2] = "Wenn das jetzt eine hoch konzentrierte Säure \noder Base ist, reicht ein Tropfen und deine \nHornhaut ist verätzt. Eine neue ist nicht billig.";
		dialogues[4][3] = "Offenbar ist es eine neutralisierbare Lösung.\nBring mir aus dem Vorbereitungsraum die\nneutralisierende Lösung!";
	}
	
	public void setDialogue6() {
		dialogues[4][0] = "Nee du. Also das wäre ja jetzt schon sehr\nlustig, wenn diese Lösung dadurch neutralisiert würde.\nNimm dir lieber eine andere...";
	}
	
	public void setDialogue7() {
		dialogues[4][0] = "Aiaiai... Und du willst Abitur machen?!";
	}
	
	public void setDialogue8() {
		dialogues[4][0] = "Suuper - wie du sieht, wird die Lösung neutral.";
		dialogues[4][1] = "Ich habe gehört, Herr Klinkhardt hat in\nRaum 115 Probleme mit dem Licht. Schau doch da mal\nmit diesem Schlüssel vorbei, ja?";
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
