package entity;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Key extends Entity {
	
	public OBJ_Key(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Key");
		idle1 = setup("/npc/objects/key");
		idle2 = setup("/npc/objects/key");
	}
}
