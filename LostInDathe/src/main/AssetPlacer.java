package main;

import object.*;

public class AssetPlacer {
	
	GamePanel gp;
	
	public AssetPlacer (GamePanel gp) {
		this.gp = gp;
	}
	
	void setObject() { // platzieren eines Objektes mit Array-Index
		
		gp.getObj()[0] = new OBJ_Key();
		gp.getObj()[0].setWorldX(13 * gp.getTileSize());
		gp.getObj()[0].setWorldY(9 * gp.getTileSize());
		
		gp.getObj()[1] = new OBJ_Key();
		gp.getObj()[1].setWorldX(34 * gp.getTileSize());
		gp.getObj()[1].setWorldY(32 * gp.getTileSize());
		
		gp.getObj()[2] = new OBJ_Bathroomdoor();
		gp.getObj()[2].setWorldX(13 * gp.getTileSize());
		gp.getObj()[2].setWorldY(5 * gp.getTileSize());
		
		gp.getObj()[3] = new OBJ_Toilet();
		gp.getObj()[3].setWorldX(18 * gp.getTileSize());
		gp.getObj()[3].setWorldY(1 * gp.getTileSize());
		
		gp.getObj()[4] = new OBJ_Toilet();
		gp.getObj()[4].setWorldX(19 * gp.getTileSize());
		gp.getObj()[4].setWorldY(1 * gp.getTileSize());
		
		gp.getObj()[5] = new OBJ_Toilet();
		gp.getObj()[5].setWorldX(20 * gp.getTileSize());
		gp.getObj()[5].setWorldY(1 * gp.getTileSize());
	}
}
