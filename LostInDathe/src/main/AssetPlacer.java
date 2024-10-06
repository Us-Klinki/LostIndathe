package main;

import object.*;

public class AssetPlacer {
	
	GamePanel gp;
	
	public AssetPlacer (GamePanel gp) {
		this.gp = gp;
	}
	
	void setObject() { // platzieren eines Objektes mit Array-Index
		
		int mapNum = 0;
		gp.getObj()[mapNum][0] = new OBJ_Key(gp);
		gp.getObj()[mapNum][0].setWorldX(26 * gp.getTileSize());
		gp.getObj()[mapNum][0].setWorldY(22 * gp.getTileSize());
		
		gp.getObj()[mapNum][1] = new OBJ_Sink(gp);
		gp.getObj()[mapNum][1].setWorldX(21 * gp.getTileSize());
		gp.getObj()[mapNum][1].setWorldY(22 * gp.getTileSize());
		
		gp.getObj()[mapNum][2] = new OBJ_Sink(gp);
		gp.getObj()[mapNum][2].setWorldX(21 * gp.getTileSize());
		gp.getObj()[mapNum][2].setWorldY(23 * gp.getTileSize());
		
		gp.getObj()[mapNum][3] = new OBJ_Toilet(gp);
		gp.getObj()[mapNum][3].setWorldX(24 * gp.getTileSize());
		gp.getObj()[mapNum][3].setWorldY(21 * gp.getTileSize());
		
		gp.getObj()[mapNum][4] = new OBJ_Toilet(gp);
		gp.getObj()[mapNum][4].setWorldX(26 * gp.getTileSize());
		gp.getObj()[mapNum][4].setWorldY(21 * gp.getTileSize());
		
		gp.getObj()[mapNum][5] = new OBJ_Toilet(gp);
		gp.getObj()[mapNum][5].setWorldX(28 * gp.getTileSize());
		gp.getObj()[mapNum][5].setWorldY(21 * gp.getTileSize());
		
		gp.getObj()[mapNum][6] = new OBJ_Bathroomdoor(gp);
		gp.getObj()[mapNum][6].setWorldX(20 * gp.getTileSize());
		gp.getObj()[mapNum][6].setWorldY(30 * gp.getTileSize());
		

	}
}
