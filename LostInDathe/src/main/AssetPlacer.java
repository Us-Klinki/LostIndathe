package main;

import entity.NPC_Priebe;
import entity.NPC_Test;
import entity.OBJ_Bathroomdoor;
import entity.OBJ_Sink;
import entity.OBJ_Statue;
import entity.OBJ_Toilet;
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
		// Objekte Bad
		/*gp.getObj()[mapNum][0] = new OBJ_Key(gp);
		gp.getObj()[mapNum][0].setWorldX(26 * gp.getTileSize());
		gp.getObj()[mapNum][0].setWorldY(22 * gp.getTileSize());*/
		
		gp.getObj()[mapNum][1] = new OBJ_Sink(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 21*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 22*gp.getTileSize();
		
		gp.getObj()[mapNum][2] = new OBJ_Sink(gp, keyH);
		gp.getObj()[mapNum][2].worldX = 21*gp.getTileSize();
		gp.getObj()[mapNum][2].worldY = 23*gp.getTileSize();
		
		gp.getObj()[mapNum][3] = new OBJ_Toilet(gp, keyH);
		gp.getObj()[mapNum][3].worldX = 24*gp.getTileSize();
		gp.getObj()[mapNum][3].worldY = 21*gp.getTileSize();
		
		gp.getObj()[mapNum][4] = new OBJ_Toilet(gp, keyH);
		gp.getObj()[mapNum][4].worldX = 26*gp.getTileSize();
		gp.getObj()[mapNum][4].worldY = 21*gp.getTileSize();
		gp.getObj()[mapNum][4].setKeyInside(true);
		
		gp.getObj()[mapNum][5] = new OBJ_Toilet(gp, keyH);
		gp.getObj()[mapNum][5].worldX = 28*gp.getTileSize();
		gp.getObj()[mapNum][5].worldY = 21*gp.getTileSize();
		
		gp.getObj()[mapNum][6] = new OBJ_Bathroomdoor(gp, keyH);
		gp.getObj()[mapNum][6].worldX = 20*gp.getTileSize();
		gp.getObj()[mapNum][6].worldY = 30*gp.getTileSize();
		
		mapNum = 2;
		// Objekte Geschichtsraum
		gp.getObj()[mapNum][0] = new OBJ_Statue(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 10*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 42*gp.getTileSize();
		

	}
	public void setNPC() {
		gp.getNpc()[0][0] = new NPC_Test(gp, keyH);
		gp.getNpc()[0][0].worldX = 24*gp.getTileSize();
		gp.getNpc()[0][0].worldY = 24*gp.getTileSize();
		
		gp.getNpc()[2][0] = new NPC_Priebe(gp, keyH);
		gp.getNpc()[2][0].worldX = 14*gp.getTileSize();
		gp.getNpc()[2][0].worldY = 40*gp.getTileSize();
	}
}
