package entity;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Sink extends Entity {
	
	public OBJ_Sink(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Sink");
		idle1 = setup("/npc/objects/sink");
		idle2 = setup("/npc/objects/sink");
		setCollisionOn(true);
		
	}
}
