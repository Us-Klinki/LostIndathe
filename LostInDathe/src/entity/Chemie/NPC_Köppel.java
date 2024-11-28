package entity.Chemie;

import java.awt.Rectangle;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

public class NPC_Köppel extends Entity {
	
	public static final String npcName = "Köppel";
	
	
	public NPC_Köppel(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		
		setName(npcName);
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
		dialogues[4][1] = "Ach, du bist ja auch noch hier. \nDu kannst mir bestimmt behilflich sein! \nIch will die Lösung nicht einfach wegschütten.";
		dialogues[4][2] = "Wer weiß, was das für eine schädliche Lösung \nsein könnte. \nHmm... Ich habe da eine Idee!";
		dialogues[4][3] = "Bring mir doch mal bitte die Indikatorlösung.\nDie müsste dahinten auf einem Tisch stehen \nund hat eine grüne Färbung...";
	}
	
	public void setDialogue2() {
		dialogues[4][0] = "War deine Aufgabe nicht klar?";
	}
	
	public void setDialogue3() {
		dialogues[4][0] = "Vielen Dank für deine Hilfe!";
	}
	
	public void setDialogue4() {
		dialogues[4][0] = "Das ist der falsche Indikator. Mit Phenolphtha- \nlein kann ich jetzt gar nichts anfangen...";
		dialogues[4][1] = "Phenolphthalein ändert seine Farbe im \nph-Bereich zwischen 8 und 10. Ph-Werte unter 8 \nkönnen wir damit nicht genau bestimmen.";
		dialogues[4][2] = "Geh noch mal, wir brauchen:\nU-ni-ver-sal-in-di-ka-tor!\nDer müsste auch dahinten sein...";
	}
	
	public void setDialogue5() {
		dialogues[4][0] = "Wie? Der stand genau da, wo der andere stand?\nMerkwürdig... Naja, es ist schon spät.";
		dialogues[4][1] = "Moment mal, nicht so schnell!\nWo ist denn deine Schutzbrille?!\n";
		dialogues[4][2] = "Wenn das jetzt eine hoch konzentrierte Säure o. \nBase ist, reicht ein Tropfen und deine Hornhaut \nist verätzt. Eine neue gibt es nicht so einfach.";
		dialogues[4][3] = "Sieh mal: Offenbar ist es eine neutralisierbare\nLösung. Bring mir mal aus dem Vorbereitungsraum \ndie neutralisierende Lösung!";
	}
	
	public void setDialogue6() {
		dialogues[4][0] = "Nee du. Das wäre jetzt sehr lustig, wenn diese \nLösung dadurch neutralisiert werden würde. \nNimm dir lieber eine andere...";
	}
	
	public void setDialogue7() {
		dialogues[4][0] = "Aiaiai... Und du willst Abitur machen?!";
	}
	
	public void setDialogue8() {
		dialogues[4][0] = "Suuper - wie du siehst, wird die Lösung neutral.";
		dialogues[4][1] = "Ich habe gehört, Herr Klinkhardt hat in R 115 \nProbleme mit dem Licht. Schau doch mal\nmit diesem Schlüssel vorbei, ja?";
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
