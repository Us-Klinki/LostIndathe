package main;

import entity.NPC_Klinkhardt;
import entity.NPC_Priebe;
import entity.OBJ_Sink;
import entity.OBJ_Statue;
import entity.OBJ_Statue2;
import entity.OBJ_Toilet;
import entity.Bio.NPC_Krecic;
import entity.Bio.NPC_Maus;
import entity.Chemie.NPC_Köppel;
import entity.Chemie.OBJ_Base;
import entity.Chemie.OBJ_Neutral;
import entity.Chemie.OBJ_NeutralReplacer;
import entity.Chemie.OBJ_Phenolphthalein;
import entity.Chemie.OBJ_Säure;
import entity.Chemie.OBJ_SäureReplacer;
import entity.Chemie.OBJ_Unbekannt;
import entity.Chemie.OBJ_Universalindikator;
import entity.Türen.OBJ_Bathroomdoor;
import entity.Türen.OBJ_Biodoor;
import entity.Türen.OBJ_Chemiedoor;

import entity.Türen.OBJ_Door112;
import entity.Türen.OBJ_Door117;
import entity.Türen.OBJ_Door311;
import entity.Türen.OBJ_Door312;
import entity.Türen.OBJ_Door314;
import entity.Türen.OBJ_Door315;
import entity.Türen.OBJ_Door316a;
import entity.Türen.OBJ_DoorMZH;
import entity.Türen.OBJ_DoorSchulhof;
import entity.Türen.OBJ_DoorVorbereitung;
import entity.Türen.OBJ_GesDoor;
import entity.Türen.OBJ_Informatikdoor;


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
		gp.getObj()[mapNum][0].worldX = 17*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 11*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 16*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 15*gp.getTileSize();

		gp.getObj()[mapNum][2] = new OBJ_Door315(gp, keyH);
		gp.getObj()[mapNum][2].worldX = 36*gp.getTileSize();
		gp.getObj()[mapNum][2].worldY = 15*gp.getTileSize();
		
		gp.getObj()[mapNum][3] = new OBJ_Chemiedoor(gp, keyH);
		gp.getObj()[mapNum][3].worldX = 72*gp.getTileSize();
		gp.getObj()[mapNum][3].worldY = 41*gp.getTileSize();
		
		gp.getObj()[mapNum][4] = new OBJ_Door316a(gp, keyH);
		gp.getObj()[mapNum][4].worldX = 14*gp.getTileSize();
		gp.getObj()[mapNum][4].worldY = 13*gp.getTileSize();
		
		gp.getObj()[mapNum][5] = new OBJ_Door314(gp, keyH);
		gp.getObj()[mapNum][5].worldX = 50*gp.getTileSize();
		gp.getObj()[mapNum][5].worldY = 15*gp.getTileSize();
		
		gp.getObj()[mapNum][7] = new OBJ_DoorVorbereitung(gp, keyH);
		gp.getObj()[mapNum][7].worldX = 70*gp.getTileSize();
		gp.getObj()[mapNum][7].worldY = 11*gp.getTileSize();
		
		gp.getObj()[mapNum][8] = new OBJ_Door312(gp, keyH);
		gp.getObj()[mapNum][8].worldX = 72*gp.getTileSize();
		gp.getObj()[mapNum][8].worldY = 14*gp.getTileSize();
		
		gp.getObj()[mapNum][9] = new OBJ_Door311(gp, keyH);
		gp.getObj()[mapNum][9].worldX = 72*gp.getTileSize();
		gp.getObj()[mapNum][9].worldY = 20*gp.getTileSize();
		
		gp.getObj()[mapNum][10] = new OBJ_Door311(gp, keyH);
		gp.getObj()[mapNum][10].worldX = 72*gp.getTileSize();
		gp.getObj()[mapNum][10].worldY = 28*gp.getTileSize();
		
		gp.getObj()[mapNum][11] = new OBJ_Chemiedoor(gp, keyH);
		gp.getObj()[mapNum][11].worldX = 72*gp.getTileSize();
		gp.getObj()[mapNum][11].worldY = 34*gp.getTileSize();
		
		gp.getObj()[mapNum][12] = new OBJ_Chemiedoor(gp, keyH);
		gp.getObj()[mapNum][12].worldX = 72*gp.getTileSize();
		gp.getObj()[mapNum][12].worldY = 55*gp.getTileSize();
		
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
		gp.getObj()[mapNum][0].worldX = 45*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 71.6*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_Base(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 49*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 71.6*gp.getTileSize();
		
		gp.getObj()[mapNum][2] = new OBJ_Neutral(gp, keyH);
		gp.getObj()[mapNum][2].worldX = 47*gp.getTileSize();
		gp.getObj()[mapNum][2].worldY = 71.6*gp.getTileSize();
		
		gp.getObj()[mapNum][3] = new OBJ_Phenolphthalein(gp, keyH);
		gp.getObj()[mapNum][3].worldX = 44*gp.getTileSize();
		gp.getObj()[mapNum][3].worldY = 54.6*gp.getTileSize();
		
		gp.getObj()[mapNum][4] = new OBJ_Unbekannt(gp, keyH);
		gp.getObj()[mapNum][4].worldX = 47*gp.getTileSize();
		gp.getObj()[mapNum][4].worldY = 63.6*gp.getTileSize();
		
		gp.getObj()[mapNum][5] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][5].worldX = 53*gp.getTileSize();
		gp.getObj()[mapNum][5].worldY = 64*gp.getTileSize();
		
		gp.getObj()[mapNum][6] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][6].worldX = 53*gp.getTileSize();
		gp.getObj()[mapNum][6].worldY = 53*gp.getTileSize();
		
		gp.getObj()[mapNum][7] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][7].worldX = 53*gp.getTileSize();
		gp.getObj()[mapNum][7].worldY = 71*gp.getTileSize();
		
		gp.getObj()[mapNum][8] = new OBJ_DoorVorbereitung(gp, keyH);
		gp.getObj()[mapNum][8].worldX = 51*gp.getTileSize();
		gp.getObj()[mapNum][8].worldY = 51*gp.getTileSize();
		
		gp.getObj()[mapNum][9] = new OBJ_Door311(gp, keyH);
		gp.getObj()[mapNum][9].worldX = 51*gp.getTileSize();
		gp.getObj()[mapNum][9].worldY = 77*gp.getTileSize();
		
		/*gp.getObj()[mapNum][5] = new OBJ_Säure(gp, keyH);
		gp.getObj()[mapNum][5].setCollisionOn(false);
		gp.getObj()[mapNum][5].worldX = 43*gp.getTileSize();
		gp.getObj()[mapNum][5].worldY = 66*gp.getTileSize();*/
	
		// EG
		mapNum = 5;
		
		gp.getObj()[mapNum][0] = new OBJ_Door117(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 24*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 32*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_Door117(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 22*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 24*gp.getTileSize();
		
		gp.getObj()[mapNum][2] = new OBJ_DoorVorbereitung(gp, keyH);
		gp.getObj()[mapNum][2].worldX = 27*gp.getTileSize();
		gp.getObj()[mapNum][2].worldY = 20*gp.getTileSize();
		
		gp.getObj()[mapNum][3] = new OBJ_Informatikdoor(gp, keyH);
		gp.getObj()[mapNum][3].worldX = 42*gp.getTileSize();
		gp.getObj()[mapNum][3].worldY = 24*gp.getTileSize();
		
		gp.getObj()[mapNum][4] = new OBJ_Informatikdoor(gp, keyH);
		gp.getObj()[mapNum][4].worldX = 55*gp.getTileSize();
		gp.getObj()[mapNum][4].worldY = 24*gp.getTileSize();
		
		gp.getObj()[mapNum][5] = new OBJ_DoorVorbereitung(gp, keyH);
		gp.getObj()[mapNum][5].worldX = 60*gp.getTileSize();
		gp.getObj()[mapNum][5].worldY = 24*gp.getTileSize();
		
		gp.getObj()[mapNum][6] = new OBJ_DoorMZH(gp, keyH);
		gp.getObj()[mapNum][6].worldX = 49*gp.getTileSize();
		gp.getObj()[mapNum][6].worldY = 20*gp.getTileSize();
		
		gp.getObj()[mapNum][7] = new OBJ_Door112(gp, keyH);
		gp.getObj()[mapNum][7].worldX = 79*gp.getTileSize();
		gp.getObj()[mapNum][7].worldY = 29*gp.getTileSize();
		
		gp.getObj()[mapNum][8] = new OBJ_DoorVorbereitung(gp, keyH);
		gp.getObj()[mapNum][8].worldX = 79*gp.getTileSize();
		gp.getObj()[mapNum][8].worldY = 34*gp.getTileSize();
		
		gp.getObj()[mapNum][9] = new OBJ_Biodoor(gp, keyH);
		gp.getObj()[mapNum][9].worldX = 79*gp.getTileSize();
		gp.getObj()[mapNum][9].worldY = 40*gp.getTileSize();
		
		gp.getObj()[mapNum][10] = new OBJ_Biodoor(gp, keyH);
		gp.getObj()[mapNum][10].worldX = 79*gp.getTileSize();
		gp.getObj()[mapNum][10].worldY = 59*gp.getTileSize();
		
		gp.getObj()[mapNum][11] = new OBJ_DoorSchulhof(gp, keyH);
		gp.getObj()[mapNum][11].worldX = 77*gp.getTileSize();
		gp.getObj()[mapNum][11].worldY = 61*gp.getTileSize();
		
		gp.getObj()[mapNum][12] = new OBJ_DoorSchulhof(gp, keyH);
		gp.getObj()[mapNum][12].worldX = 71*gp.getTileSize();
		gp.getObj()[mapNum][12].worldY = 32*gp.getTileSize();
		
		// Bioraum
		mapNum = 6;
		gp.getObj()[mapNum][0] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][0].worldX = 43*gp.getTileSize();
		gp.getObj()[mapNum][0].worldY = 47*gp.getTileSize();
		
		gp.getObj()[mapNum][1] = new OBJ_GesDoor(gp, keyH);
		gp.getObj()[mapNum][1].worldX = 43*gp.getTileSize();
		gp.getObj()[mapNum][1].worldY = 58*gp.getTileSize();
	}
	
		
	void setStatue() {
		gp.getObj()[2][0] = new OBJ_Statue2(gp, keyH);
		gp.getObj()[2][0].worldX = 20*gp.getTileSize();
		gp.getObj()[2][0].worldY = 32*gp.getTileSize();
	}
	
	void setUniversalindikator() {
		gp.getObj()[4][3] = new OBJ_Universalindikator(gp, keyH);
		gp.getObj()[4][3].worldX = 44*gp.getTileSize();
		gp.getObj()[4][3].worldY = 54.6*gp.getTileSize();
	}
	
	void setRoteLösung() {
		gp.getObj()[4][4] = new OBJ_SäureReplacer(gp, keyH);
		gp.getObj()[4][4].worldX = 47*gp.getTileSize();
		gp.getObj()[4][4].worldY = 63.6*gp.getTileSize();
	}
	
	void setBase() {
		gp.getObj()[4][4] = new OBJ_NeutralReplacer(gp, keyH);
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
		
		gp.getNpc()[3][0] = new NPC_Klinkhardt(gp, keyH);
		gp.getNpc()[3][0].worldX = 48*gp.getTileSize();
		gp.getNpc()[3][0].worldY = 50*gp.getTileSize();
		
		gp.getNpc()[4][0] = new NPC_Köppel(gp, keyH);
		gp.getNpc()[4][0].worldX = 47*gp.getTileSize();
		gp.getNpc()[4][0].worldY = 63*gp.getTileSize();
		
		gp.getNpc()[6][0] = new NPC_Krecic(gp, keyH);
		gp.getNpc()[6][0].worldX = 50*gp.getTileSize();
		gp.getNpc()[6][0].worldY = 44*gp.getTileSize();
		
		gp.getNpc()[6][1] = new NPC_Maus(gp, keyH);
		gp.getNpc()[6][1].worldX = 44*gp.getTileSize();
		gp.getNpc()[6][1].worldY = 59*gp.getTileSize();
		
		gp.getNpc()[6][2] = new NPC_Maus(gp, keyH);
		gp.getNpc()[6][2].worldX = 49*gp.getTileSize();
		gp.getNpc()[6][2].worldY = 48*gp.getTileSize();
		
		gp.getNpc()[6][3] = new NPC_Maus(gp, keyH);
		gp.getNpc()[6][3].worldX = 48*gp.getTileSize();
		gp.getNpc()[6][3].worldY = 53*gp.getTileSize();
		
		gp.getNpc()[6][4] = new NPC_Maus(gp, keyH);
		gp.getNpc()[6][4].worldX = 53*gp.getTileSize();
		gp.getNpc()[6][4].worldY = 54*gp.getTileSize();
		
		gp.getNpc()[6][5] = new NPC_Maus(gp, keyH);
		gp.getNpc()[6][5].worldX = 50*gp.getTileSize();
		gp.getNpc()[6][5].worldY = 58*gp.getTileSize();
	}
	public void mausDispose(int i) {
		gp.getNpc()[6][i] = null;
	}
}
