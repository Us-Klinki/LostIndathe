package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.EventHandler;
import main.GamePanel;

public class SaveLoad {
	
	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {
			DataStorage ds = new DataStorage();
			
			// Spielervariablen
			ds.map = gp.getCurrentMap();
			ds.playerX = gp.getPlayer().worldX;
			ds.playerY = gp.getPlayer().worldY;
			ds.hasKey = gp.getPlayer().getHasKey();
			ds.hasKeyChemie = gp.getPlayer().getHasKeyChemie();
			ds.hasKeyInfo = gp.getPlayer().isHasKeyInfo();
			ds.hasKeyBio = gp.getPlayer().isHasKeyBio();
			ds.hasKeySchulhof = gp.getPlayer().isHasKeySchulhof();
			ds.currentKöppelDialog = gp.getPlayer().getCurrentKöppelDialog();
			ds.currentBaseDialog = gp.getPlayer().getCurrentBaseDialog();
			ds.currentNeutralDialog = gp.getPlayer().getCurrentNeutralDialog();
			ds.currentSäureDialog = gp.getPlayer().getCurrentSäureDialog();
			ds.currentPhenolphthaleinDialog = gp.getPlayer().getCurrentPhenolphthaleinDialog();
			ds.currentUniversalindikatorDialog = gp.getPlayer().getCurrentUniversalindikatorDialog();
			ds.hatSäure = gp.getPlayer().hatNeutral;
			ds.hatNeutral = gp.getPlayer().hatNeutral;
			ds.holZweitenIndikator = gp.getPlayer().holZweitenIndikator;
			ds.holLösung = gp.getPlayer().holLösung;
			ds.basePlacen = gp.getPlayer().basePlacen;
			ds.fehler = gp.getPlayer().getFehler();
			ds.currentKlinkiDialog = gp.getPlayer().getCurrentKlinkiDialog();
			ds.licht = gp.getPlayer().isLicht();
			ds.Mäusefang = gp.getPlayer().getMäusefang();
			ds.countdown = gp.getPlayer().getCountdown();
			ds.counter = gp.getPlayer().getCounter();
			ds.currentKrecicDialogue = gp.getPlayer().getCurrentKrecicDialogue();
			ds.timerstart = gp.getPlayer().isTimerstart();
			ds.krecicStart = gp.getPlayer().isKrecicStart();
			ds.dialogueCounter = gp.getPlayer().dialogueCounter;
			ds.gesGelöst = EventHandler.gesGelöst;
			ds.startState = gp.startState;
			
			ds.mapObjectNames = new String[gp.getMaxMap()][gp.getObj()[1].length];
			ds.mapObjectWorldX = new int[gp.getMaxMap()][gp.getObj()[1].length];
			ds.mapObjectWorldY = new int[gp.getMaxMap()][gp.getObj()[1].length];
			for(int mapNum = 0; mapNum < gp.getMaxMap(); mapNum++) {
				for(int i = 0; i < gp.getObj()[1].length; i++) {
					if(gp.getObj()[mapNum][i] == null) {
						ds.mapObjectNames[mapNum][i] = "NA";
					}
					else {
						ds.mapObjectNames[mapNum][i] = gp.getObj()[mapNum][i].getName();
						ds.mapObjectWorldX[mapNum][i] = gp.getObj()[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = (int) gp.getObj()[mapNum][i].worldY;
					}
				}
			}
			
			ds.mapNPCNames = new String[gp.getMaxMap()][gp.getNpc()[1].length];
			ds.mapNPCWorldX = new int[gp.getMaxMap()][gp.getNpc()[1].length];
			ds.mapNPCWorldY = new int[gp.getMaxMap()][gp.getNpc()[1].length];
			for(int mapNum = 0; mapNum < gp.getMaxMap(); mapNum++) {
				for(int i = 0; i < gp.getNpc()[1].length; i++) {
					if(gp.getNpc()[mapNum][i] == null) {
						ds.mapNPCNames[mapNum][i] = "NA";
					}
					else {
						ds.mapNPCNames[mapNum][i] = gp.getNpc()[mapNum][i].getName();
						ds.mapNPCWorldX[mapNum][i] = gp.getNpc()[mapNum][i].worldX;
						ds.mapNPCWorldY[mapNum][i] = (int) gp.getNpc()[mapNum][i].worldY;
					}
				}
			}
		
			oos.writeObject(ds);
		}
		catch(Exception e) {
			System.err.println("Fehler beim Speichern: " + e.getMessage());
		}
		
	}
	
	public void load() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {
			DataStorage ds = (DataStorage)ois.readObject();
			
			// Spielervariablen
			gp.setCurrentMap(ds.map);
			gp.getPlayer().worldX = ds.playerX;
			gp.getPlayer().worldY = ds.playerY;
			gp.getPlayer().setHasKey(ds.hasKey);
			gp.getPlayer().setHasKeyChemie(ds.hasKeyChemie);
			gp.getPlayer().setHasKeyInfo(ds.hasKeyInfo);
			gp.getPlayer().setHasKeyBio(ds.hasKeyBio);
			gp.getPlayer().setHasKeySchulhof(ds.hasKeySchulhof);
			gp.getPlayer().setCurrentKöppelDialog(ds.currentKöppelDialog);
			gp.getPlayer().setCurrentBaseDialog(ds.currentBaseDialog);
			gp.getPlayer().setCurrentNeutralDialog(ds.currentNeutralDialog);
			gp.getPlayer().setCurrentSäureDialog(ds.currentSäureDialog);
			gp.getPlayer().setCurrentPhenolphthaleinDialog(ds.currentPhenolphthaleinDialog);
			gp.getPlayer().setCurrentUniversalindikatorDialog(ds.currentUniversalindikatorDialog);
			gp.getPlayer().hatNeutral = ds.hatNeutral;
			gp.getPlayer().hatSäure = ds.hatSäure;
			gp.getPlayer().holZweitenIndikator = ds.holZweitenIndikator;
			gp.getPlayer().holLösung = ds.holLösung;
			gp.getPlayer().basePlacen = ds.basePlacen;
			gp.getPlayer().setFehler(ds.fehler);
			gp.getPlayer().setCurrentKlinkiDialog(ds.currentKlinkiDialog);
			gp.getPlayer().setLicht(ds.licht);
			gp.getPlayer().setMäusefang(ds.Mäusefang);
			gp.getPlayer().setCountdown(ds.countdown);
			gp.getPlayer().setCounter(ds.counter);
			gp.getPlayer().setCurrentKrecicDialogue(ds.currentKrecicDialogue);
			gp.getPlayer().setTimerstart(ds.timerstart);
			gp.getPlayer().setKrecicStart(ds.krecicStart);
			gp.getPlayer().dialogueCounter = ds.dialogueCounter;
			EventHandler.gesGelöst = ds.gesGelöst;
			gp.startState = ds.startState;
			
			// Objekte auf der Map
			for(int mapNum = 0; mapNum < gp.getMaxMap(); mapNum++) {
				for(int i = 0; i < gp.getObj()[1].length; i++) {
					if (ds.mapObjectNames[mapNum][i].equals("NA")) {
					    gp.setObj(null, mapNum, i);
					} else {
						System.out.println("Lade Objekt: " + ds.mapObjectNames[mapNum][i]);
					    gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
					    if (gp.obj[mapNum][i] != null) {
					        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
					        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
					    } else {
					        System.err.println("Objekt konnte nicht geladen werden: " + ds.mapObjectNames[mapNum][i]);
					    }
					}
				}
			}

			
			for(int mapNum = 0; mapNum < gp.getMaxMap(); mapNum++) {
				for(int i = 0; i < gp.getNpc()[1].length; i++) {
					if(ds.mapNPCNames[mapNum][i].equals("NA")) {
						gp.npc[mapNum][i] = null;
						
					}
					else {
						gp.npc[mapNum][i] = gp.eGenerator.getNPC(ds.mapNPCNames[mapNum][i]);
						gp.npc[mapNum][i].worldX = ds.mapNPCWorldX[mapNum][i];
						gp.npc[mapNum][i].worldY = ds.mapNPCWorldY[mapNum][i];
					}
				}
			}
		}
		
		catch(Exception e) {
			System.err.println("Fehler beim laden: " + e.getMessage());
		}
	}
}
