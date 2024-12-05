package entity;

import java.awt.Rectangle;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Border_2 extends Entity {
	
	public static final String objName = "invisibleBorder2";
	
	public OBJ_Border_2(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName(objName);
		setCollisionOn(true);
		setSolidArea(new Rectangle(20, 0, 8, 48));
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
	}
}