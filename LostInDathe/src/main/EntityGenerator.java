package main;

import entity.Entity;
import entity.NPC_Klinkhardt;
import entity.NPC_Priebe;
import entity.NPC_Test;
import entity.OBJ_Key;
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

public class EntityGenerator {

		GamePanel gp;
		KeyHandler keyH;
		
		public EntityGenerator(GamePanel gp, KeyHandler keyH) {
			this.gp = gp;
			this.keyH = keyH;
		}
		
		public Entity getObject(String itemName) {
			
			Entity obj = null;
			
			switch(itemName) {
			case OBJ_Key.objName: obj = new OBJ_Key(gp, keyH); break;
			case OBJ_Sink.objName: obj = new OBJ_Sink(gp, keyH); break;
			case OBJ_Statue.objName: obj = new OBJ_Statue(gp, keyH); break;
			case OBJ_Statue2.objName: obj = new OBJ_Statue2(gp, keyH); break;
			case OBJ_Toilet.objName: obj = new OBJ_Toilet(gp, keyH); break;
			case OBJ_Base.objName: obj = new OBJ_Base(gp, keyH); break;
			case OBJ_Neutral.objName: obj = new OBJ_Neutral(gp, keyH); break;
			case OBJ_NeutralReplacer.objName: obj = new OBJ_NeutralReplacer(gp, keyH); break;
			case OBJ_Phenolphthalein.objName: obj = new OBJ_Phenolphthalein(gp, keyH); break;
			case OBJ_Säure.objName: obj = new OBJ_Säure(gp, keyH); break;
			case OBJ_SäureReplacer.objName: obj = new OBJ_SäureReplacer(gp, keyH); break;
			case OBJ_Unbekannt.objName: obj = new OBJ_Unbekannt(gp, keyH); break;
			case OBJ_Universalindikator.objName: obj = new OBJ_Universalindikator(gp, keyH); break;
			case OBJ_Bathroomdoor.objName: obj = new OBJ_Bathroomdoor(gp, keyH); break;
			case OBJ_Biodoor.objName: obj = new OBJ_Biodoor(gp, keyH); break;
			case OBJ_Chemiedoor.objName: obj = new OBJ_Chemiedoor(gp, keyH); break;
			case OBJ_Door112.objName: obj = new OBJ_Door112(gp, keyH); break;
			case OBJ_Door117.objName: obj = new OBJ_Door117(gp, keyH); break;
			case OBJ_Door311.objName: obj = new OBJ_Door311(gp, keyH); break;
			case OBJ_Door312.objName: obj = new OBJ_Door312(gp, keyH); break;
			case OBJ_Door314.objName: obj = new OBJ_Door314(gp, keyH); break;
			case OBJ_Door315.objName: obj = new OBJ_Door315(gp, keyH); break;
			case OBJ_Door316a.objName: obj = new OBJ_Door316a(gp, keyH); break;
			case OBJ_DoorMZH.objName: obj = new OBJ_DoorMZH(gp, keyH); break;
			case OBJ_DoorSchulhof.objName: obj = new OBJ_DoorSchulhof(gp, keyH); break;
			case OBJ_DoorVorbereitung.objName: obj = new OBJ_DoorVorbereitung(gp, keyH); break;
			case OBJ_GesDoor.objName: obj = new OBJ_GesDoor(gp, keyH); break;
			case OBJ_Informatikdoor.objName: obj = new OBJ_Informatikdoor(gp, keyH); break;
			
			}
			
			return obj;
		}
		
		public Entity getNPC(String itemName) {
			
			Entity npc = null;
			
			switch(itemName) {
			case NPC_Klinkhardt.npcName: npc = new NPC_Klinkhardt(gp, keyH); break;
			case NPC_Priebe.npcName: npc = new NPC_Priebe(gp, keyH); break;
			case NPC_Test.npcName: npc = new NPC_Test(gp, keyH); break;
			case NPC_Krecic.npcName: npc = new NPC_Krecic(gp, keyH); break;
			case NPC_Maus.npcName: npc = new NPC_Maus(gp, keyH); break;
			case NPC_Köppel.npcName: npc = new NPC_Köppel(gp, keyH); break;
			
			}
			
			return npc;
		}
}
