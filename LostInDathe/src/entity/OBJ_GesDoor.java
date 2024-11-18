package entity;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_GesDoor extends Entity {
	
	
	public OBJ_GesDoor(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("GesDoor");
		idle1 = setup("/npc/objects/Tür");
		idle2 = setup("/npc/objects/Tür");
		setCollisionOn(false);
	}
}