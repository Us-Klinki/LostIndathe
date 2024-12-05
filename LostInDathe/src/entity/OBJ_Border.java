package entity;

import java.awt.Rectangle;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Border extends Entity {
	
	public static final String objName = "invisibleBorder";
	
	public OBJ_Border(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		setCollisionOn(true);
		setSolidArea(new Rectangle(0, 40, 48, 8));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
}
