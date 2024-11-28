package entity;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Sink extends Entity {
	
	public static final String objName = "Sink";
	
	public OBJ_Sink(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		idle1 = setup("/npc/objects/sink");
		idle2 = setup("/npc/objects/sink");
		setCollisionOn(true);
	}
}
