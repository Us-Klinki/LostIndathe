package main;

import entity.NPC_Priebe;
import entity.NPC_Test;
import entity.OBJ_Bathroomdoor;
import entity.OBJ_GesDoor;
import entity.OBJ_Informatikdoor;
import entity.OBJ_Sink;
import entity.OBJ_Statue;
import entity.OBJ_Statue2;
import entity.OBJ_Toilet;
import entity.Chemie.NPC_Köppel;
import entity.Chemie.OBJ_Base;
import entity.Chemie.OBJ_Neutral;
import entity.Chemie.OBJ_Phenolphthalein;
import entity.Chemie.OBJ_Säure;
import entity.Chemie.OBJ_Unbekannt;
import entity.Chemie.OBJ_Universalindikator;
import object.*;

public class AssetPlacer {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public AssetPlacer (GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
	}
	
	void setObject() { // platzieren eines Objektes mit Array-Index
		
		// BAD
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
		
		// Schulhaus OG
		mapNum = 1;
		gp.getObj()[mapNum][0] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 13*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 11*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 12*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 15*gp.getTileSize();

		gp.getObj()[mapNum][2] = new OBJ_Informatikdoor(gp, keyH);
		gp.getObj()[mapNum][2].worldX = 32*gp.getTileSize();
		gp.getObj()[mapNum][2].worldY = 15*gp.getTileSize();
		
		// GESCHICHTE
		mapNum = 2;
		gp.getObj()[mapNum][0] = new OBJ_Statue(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 31*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 20*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 29*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 18*gp.getTileSize();
		
		// INFORMATIK
		mapNum = 3;
		gp.getObj()[mapNum][0] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 50*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 50*gp.getTileSize();
		
		// CHEMIE
		mapNum = 4;
		gp.getObj()[mapNum][0] = new OBJ_Säure(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 47*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 66*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_Base(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 46*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 66*gp.getTileSize();
		
		gp.getObj()[mapNum][2] = new OBJ_Neutral(gp, keyH);
		gp.getObj()[mapNum][2].worldX = 45*gp.getTileSize();
		gp.getObj()[mapNum][2].worldY = 66*gp.getTileSize();
		
		gp.getObj()[mapNum][3] = new OBJ_Phenolphthalein(gp, keyH);
		gp.getObj()[mapNum][3].worldX = 44*gp.getTileSize();
		gp.getObj()[mapNum][3].worldY = 66*gp.getTileSize();
		
		gp.getObj()[mapNum][4] = new OBJ_Unbekannt(gp, keyH);
		gp.getObj()[mapNum][4].worldX = 47*gp.getTileSize();
		gp.getObj()[mapNum][4].worldY = 63.6*gp.getTileSize();
		
		/*gp.getObj()[mapNum][5] = new OBJ_Säure(gp, keyH);
		gp.getObj()[mapNum][5].setCollisionOn(false);
		gp.getObj()[mapNum][5].worldX = 43*gp.getTileSize();
		gp.getObj()[mapNum][5].worldY = 66*gp.getTileSize();*/
	
	
	
	}
	
		
	void setStatue() {
		gp.getObj()[2][0] = new OBJ_Statue2(gp, keyH);
		gp.getObj()[2][0].worldX = 20*gp.getTileSize();
		gp.getObj()[2][0].worldY = 32*gp.getTileSize();
	}
	
	void setUniversalindikator() {
		gp.getObj()[4][3] = new OBJ_Universalindikator(gp, keyH);
		gp.getObj()[4][3].worldX = 44*gp.getTileSize();
		gp.getObj()[4][3].worldY = 66*gp.getTileSize();
	}
	
	void setRoteLösung() {
		gp.getObj()[4][4] = new OBJ_Säure(gp, keyH);
		gp.getObj()[4][4].setCollisionOn(false);
		gp.getObj()[4][4].worldX = 47*gp.getTileSize();
		gp.getObj()[4][4].worldY = 63.6*gp.getTileSize();
	}
	public void setNPC() {
		/*gp.getNpc()[0][0] = new NPC_Test(gp, keyH);
		gp.getNpc()[0][0].worldX = 24*gp.getTileSize();
		gp.getNpc()[0][0].worldY = 24*gp.getTileSize();*/
		
		gp.getNpc()[2][0] = new NPC_Priebe(gp, keyH);
		gp.getNpc()[2][0].worldX = 24*gp.getTileSize();
		gp.getNpc()[2][0].worldY = 20*gp.getTileSize();
		
		gp.getNpc()[4][0] = new NPC_Köppel(gp, keyH);
		gp.getNpc()[4][0].worldX = 47*gp.getTileSize();
		gp.getNpc()[4][0].worldY = 63*gp.getTileSize();
	}
}
