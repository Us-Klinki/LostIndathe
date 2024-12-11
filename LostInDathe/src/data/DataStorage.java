package data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataStorage implements Serializable {
	
	// Spielervariablen
	int map;
	int playerX;
	double playerY;
	int hasKey;
	int hasKeyChemie;
	boolean hasKeyInfo;
	boolean hasKeyBio;
	boolean hasKeySchulhof;
	int currentKöppelDialog;
	int currentBaseDialog;
	int currentNeutralDialog;
	int currentSäureDialog;
	int currentPhenolphthaleinDialog;
	int currentUniversalindikatorDialog;
	boolean hatSäure;
	boolean hatNeutral;
	boolean holZweitenIndikator;
	boolean holLösung;
	boolean basePlacen;
	int fehler;
	int currentKlinkiDialog;
	boolean licht;
	int Mäusefang;
	int countdown;
	int counter;
	int currentKrecicDialogue;
	boolean timerstart;
	boolean krecicStart;
	int dialogueCounter;
	boolean keyInside;
	
	// Objekte auf der Map
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	
	// NPCs auf der Map
	String mapNPCNames[][];
	int mapNPCWorldX[][];
	int mapNPCWorldY[][];
	
	//Startstate
	boolean startState;
}
