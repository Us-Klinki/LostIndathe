package entity;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Bathroomdoor extends Entity {
	
	
	public OBJ_Bathroomdoor(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Bathroomdoor");
		idle1 = setup("/npc/objects/bathroomdoor");
		idle2 = setup("/npc/objects/bathroomdoor");
		setCollisionOn(true);
	}
}
