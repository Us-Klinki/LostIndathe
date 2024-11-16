package main;

import entity.NPC_Priebe;
import entity.NPC_Test;
import object.*;

public class AssetPlacer {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public AssetPlacer (GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
	}
	
	void setObject() { // platzieren eines Objektes mit Array-Index
		
		int mapNum = 0;
		/*gp.getObj()[mapNum][0] = new OBJ_Key(gp);
		gp.getObj()[mapNum][0].setWorldX(26 * gp.getTileSize());
		gp.getObj()[mapNum][0].setWorldY(22 * gp.getTileSize());*/
		
		gp.getObj()[mapNum][1] = new OBJ_Sink(gp, keyH);
		gp.getObj()[mapNum][1].setWorldX(21 * gp.getTileSize());
		gp.getObj()[mapNum][1].setWorldY(22 * gp.getTileSize());
		
		gp.getObj()[mapNum][2] = new OBJ_Sink(gp, keyH);
		gp.getObj()[mapNum][2].setWorldX(21 * gp.getTileSize());
		gp.getObj()[mapNum][2].setWorldY(23 * gp.getTileSize());
		
		gp.getObj()[mapNum][3] = new OBJ_Toilet(gp, keyH);
		gp.getObj()[mapNum][3].setWorldX(24 * gp.getTileSize());
		gp.getObj()[mapNum][3].setWorldY(21 * gp.getTileSize());
		
		gp.getObj()[mapNum][4] = new OBJ_Toilet(gp, keyH);
		gp.getObj()[mapNum][4].setWorldX(26 * gp.getTileSize());
		gp.getObj()[mapNum][4].setWorldY(21 * gp.getTileSize());
		gp.getObj()[mapNum][4].setKey_inside(true);
		
		gp.getObj()[mapNum][5] = new OBJ_Toilet(gp, keyH);
		gp.getObj()[mapNum][5].setWorldX(28 * gp.getTileSize());
		gp.getObj()[mapNum][5].setWorldY(21 * gp.getTileSize());
		
		gp.getObj()[mapNum][6] = new OBJ_Bathroomdoor(gp, keyH);
		gp.getObj()[mapNum][6].setWorldX(20 * gp.getTileSize());
		gp.getObj()[mapNum][6].setWorldY(30 * gp.getTileSize());
		

	}
	public void setNPC() {
		gp.getNpc()[0][0] = new NPC_Test(gp, keyH);
		gp.getNpc()[0][0].worldX = 24*gp.getTileSize();
		gp.getNpc()[0][0].worldY = 24*gp.getTileSize();
		
		gp.getNpc()[0][1] = new NPC_Priebe(gp, keyH);
		gp.getNpc()[0][1].worldX = 26*gp.getTileSize();
		gp.getNpc()[0][1].worldY = 24*gp.getTileSize();
	}
}
