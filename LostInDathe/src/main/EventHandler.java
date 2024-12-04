package main;


public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];
	EventRect eventRect1[][][];
	EventRect eventRect2[][][];
	EventRect eventRect3[][][];
	EventRect eventRectTreppe[][][];
	
	KeyHandler keyH = new KeyHandler(gp);
	int previousEventX;
	double previousEventY;
	boolean canTouchEvent = true;
	boolean canTouchTreppe = true;
	public static boolean gesGelöst = false;
	public static boolean gesZuDunkel = false;
	public static boolean playerNotColliding = true;
	public static boolean treppeNichtBegehbar = false;
	public static int i = 0;
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRect1 = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRect2 = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRect3 = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRectTreppe = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while (map < gp.getMaxMap() && col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = gp.getTileSize() / 2 - gp.getTileSize() / 48;
			eventRect[map][col][row].y = gp.getTileSize() / 2 - gp.getTileSize() / 48;
			eventRect[map][col][row].width = gp.getTileSize() / 24;
			eventRect[map][col][row].height = gp.getTileSize() / 24;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			eventRect1[map][col][row] = new EventRect();
			eventRect1[map][col][row].x = gp.getTileSize() / 2 - gp.getTileSize() / 48;
			eventRect1[map][col][row].y = 0;
			eventRect1[map][col][row].width = gp.getTileSize() / 24;
			eventRect1[map][col][row].height = gp.getTileSize();
			eventRect1[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect1[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			eventRect2[map][col][row] = new EventRect();
			eventRect2[map][col][row].x = 0;
			eventRect2[map][col][row].y = 0;
			eventRect2[map][col][row].width = gp.getTileSize();
			eventRect2[map][col][row].height = gp.getTileSize();
			eventRect2[map][col][row].eventRectDefaultX = eventRect2[map][col][row].x;
			eventRect2[map][col][row].eventRectDefaultY = eventRect2[map][col][row].y;
			
			eventRect3[map][col][row] = new EventRect();
			eventRect3[map][col][row].x = 0;
			eventRect3[map][col][row].y = 0;
			eventRect3[map][col][row].width = gp.getTileSize();
			eventRect3[map][col][row].height = gp.getTileSize();
			eventRect3[map][col][row].eventRectDefaultX = eventRect3[map][col][row].x;
			eventRect3[map][col][row].eventRectDefaultY = eventRect3[map][col][row].y;
			
			eventRectTreppe[map][col][row] = new EventRect();
			eventRectTreppe[map][col][row].x = gp.getTileSize() / 2 - gp.getTileSize() / 48;
			eventRectTreppe[map][col][row].y = 0;
			eventRectTreppe[map][col][row].width = gp.getTileSize() / 24;
			eventRectTreppe[map][col][row].height = gp.getTileSize() * 3;
			eventRectTreppe[map][col][row].eventRectDefaultX = eventRect3[map][col][row].x;
			eventRectTreppe[map][col][row].eventRectDefaultY = eventRect3[map][col][row].y;
			
			col ++;
			if(col == gp.getMaxWorldCol()) {
				col = 0;
				row++;
				
				if (row == gp.getMaxWorldRow()) {
					row = 0;
					map++;
				}
			}
		}
		
		
	}
	
	public void checkEvent() {
		
		// Überprüfe, ob Player mindestens 1 Tile vom letzten Event weg ist
		int xDistance = Math.abs(gp.getPlayer().worldX - previousEventX); //abs = Betrag
		int yDistance = (int) Math.abs(gp.getPlayer().worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.getTileSize()) {
			canTouchEvent = true;
		}
		
		int xDistanceTreppe = Math.abs(gp.getPlayer().worldX - previousEventX); //abs = Betrag
		int yDistanceTreppe = (int) Math.abs(gp.getPlayer().worldY - previousEventY);
		int distanceTreppe = Math.max(xDistanceTreppe, yDistanceTreppe);
		if(distanceTreppe >= gp.getTileSize() * 3) {
			canTouchTreppe = true;
		}
		
		if(canTouchEvent == true) {
			//if(hit(0, 21, 31, "any") == true) { intraTeleport(gp.dialogueState); }
			// Teleport Bad -> OG; OG -> Bad
			if(hit(0, 20, 30, "any") == true) { interTeleport(1, 17, 11); /*gp.stopSE(24);*/ gp.stopMusic(52);  gp.playMusic(6); }
			else if(hit(1, 17, 11, "any") == true) { interTeleport(0, 20, 30); gp.stopMusic(6); gp.playMusic(52); }
			// Teleport OG -> Geschichte; Geschichte -> OG
			else if(hit(1, 16, 15, "any") == true) { interTeleport(2, 29, 18); gp.stopMusic(6); gp.playMusic(49); }
			else if(hit(2, 29, 18, "any") == true) { interTeleport(1, 16, 15); gp.stopMusic(49); gp.playMusic(6); }
			// Teleport OG -> Chemie: Chemie -> OG
			else if(hit(1, 72, 41, "any") == true) { interTeleport(4, 53, 63.9); gp.stopMusic(6); gp.playMusic(5); }
			else if(hit(4, 53, 64, "any") == true) { interTeleport(1, 72, 40.9); gp.stopMusic(5); gp.playMusic(6); }
			
			else if(hit(1, 72, 34, "any") == true) { interTeleport(4, 53, 70.9); gp.stopMusic(6); gp.playMusic(5); }
			else if(hit(4, 53, 71, "any") == true) { interTeleport(1, 72, 33.9); gp.stopMusic(5); gp.playMusic(6); }
			
			else if(hit(1, 72, 55, "any") == true) { interTeleport(4, 53, 52.9); gp.stopMusic(6); gp.playMusic(5); }
			else if(hit(4, 53, 53, "any") == true) { interTeleport(1, 72, 54.9); gp.stopMusic(5); gp.playMusic(6); }
			// Teleport 2x EG -> Info; Info -> OG
			else if(hit(5, 42, 24, "any") == true) { interTeleport(3, 50, 49.9); gp.stopMusic(6); gp.playMusic(48); keyH.licht = false; }
			else if(hit(5, 55, 24, "any") == true) { interTeleport(3, 50, 49.9); gp.stopMusic(6); gp.playMusic(48); keyH.licht = false; }
			else if(hit(3, 50, 50, "any") == true) { interTeleport(5, 42, 24); gp.stopMusic(48); gp.playMusic(6); }
			// Teleport 2x EG -> Bio; 2x Bio -> EG
			else if(hit(5, 79, 40, "any") == true) { interTeleport(6, 43, 46.9); gp.stopMusic(6); gp.playMusic(57); }
			else if(hit(5, 79, 59, "any") == true) { interTeleport(6, 43, 57.9); gp.stopMusic(6); gp.playMusic(57); }
			else if(hit(6, 43, 47, "any") == true) { interTeleport(5, 79, 39.9); gp.stopMusic(57); gp.playMusic(6); }
			else if(hit(6, 43, 58, "any") == true) { interTeleport(5, 79, 58.9); gp.stopMusic(57); gp.playMusic(6); }
			

		}
		
		if(canTouchTreppe) {
			/* Teleport Aufgang D OG -> EG; EG -> OG
			 * Information: hitTreppe nutzt ein EventRectangle, dass 3 Tiles hoch ist. Daher immer das oberste Tile nutzen, dann sind alle
			 * Treppenstufen abgedeckt. (hitTreppe(1, 22, 22, "any) funktioniert auch für y = 23 oder y = 24)
			 */
			if(hitTreppe(1, 22, 22, "any") == true) { interTeleport(5, 28, 26); }
			else if(hitTreppe(5, 28, 25, "any") == true) { interTeleport(1, 22, 23); }
			// Teleport Aufgang C OG -> EG; EG -> OG
			else if(hitTreppe(1, 65, 22, "any") == true) { interTeleport(5, 72, 26); }
			else if(hitTreppe(5, 72, 25, "any") == true) { interTeleport(1, 65 , 23); }
		}
		
		if(hitTileGroß(1, 22, 16) == true || hitTileGroß(1, 22, 17) == true || hitTileGroß(1, 22, 18) == true || 
			hitTileGroß(1, 64, 16) == true || hitTileGroß(1, 64, 17) == true || hitTileGroß(1, 64, 18) == true) { treppeNichtBegehbar = true; }
		
		if(hitTileGroß(2, 20, 31) == true || hitTileGroß(2, 20, 32) == true) {
			playerNotColliding = false;
		}
		else if(hitTileGroß(2, 20, 31) == false || hitTileGroß(2, 20, 32) == false) {
			playerNotColliding = true;
		}
		
		if(hitObject(2, 20, 31) == true || hitObject(2, 20, 32) == true) {
			gesGelöst = true;
		}
		
		if(hitObject(2, 20, 27) == true || hitObject(2, 20, 26) == true || hitObject(2, 20, 23) == true || hitObject(2, 20, 22) == true) {
			gesZuDunkel = true;
		}
		else if(hitObject(2, 20, 27) == false || hitObject(2, 20, 26) == false || hitObject(2, 20, 23) == false || hitObject(2, 20, 22) == false) {
			gesZuDunkel = false;
		}
	}
	
	public boolean hit(int eventMap, int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		if(eventMap == gp.getCurrentMap()) {
			gp.getPlayer().getSolidArea().x = gp.getPlayer().worldX + gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = (int) (gp.getPlayer().worldY + gp.getPlayer().getSolidAreaDefaultY());
			eventRect[eventMap][eventCol][eventRow].x = eventCol * gp.getTileSize() + eventRect[eventMap][eventCol][eventRow].x;
			eventRect[eventMap][eventCol][eventRow].y = eventRow * gp.getTileSize() + eventRect[eventMap][eventCol][eventRow].y;
		
			if(gp.getPlayer().getSolidArea().intersects(eventRect[eventMap][eventCol][eventRow]) && eventRect[eventMap][eventCol][eventRow].eventDone == false) {
				if(gp.getPlayer().direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
				
					previousEventX = gp.getPlayer().worldX;
					previousEventY = gp.getPlayer().worldY;
				
				}
			}
		
			gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
			eventRect[eventMap][eventCol][eventRow].x = eventRect[eventMap][eventCol][eventRow].eventRectDefaultX;
			eventRect[eventMap][eventCol][eventRow].y = eventRect[eventMap][eventCol][eventRow].eventRectDefaultY;
		}
				
		return hit;
	}
	
	public boolean hitTreppe(int eventMap, int eventCol, int eventRow, String reqDirection) {
		
		boolean hitTreppe = false;
		
		if(eventMap == gp.getCurrentMap()) {
			gp.getPlayer().getSolidArea().x = gp.getPlayer().worldX + gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = (int) (gp.getPlayer().worldY + gp.getPlayer().getSolidAreaDefaultY());
			eventRectTreppe[eventMap][eventCol][eventRow].x = eventCol * gp.getTileSize() + eventRectTreppe[eventMap][eventCol][eventRow].x;
			eventRectTreppe[eventMap][eventCol][eventRow].y = eventRow * gp.getTileSize() + eventRectTreppe[eventMap][eventCol][eventRow].y;
		
			if(gp.getPlayer().getSolidArea().intersects(eventRectTreppe[eventMap][eventCol][eventRow]) && eventRectTreppe[eventMap][eventCol][eventRow].eventDone == false) {
				if(gp.getPlayer().direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hitTreppe = true;
				
					previousEventX = gp.getPlayer().worldX;
					previousEventY = gp.getPlayer().worldY;
				
				}
			}
		
			gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
			eventRectTreppe[eventMap][eventCol][eventRow].x = eventRectTreppe[eventMap][eventCol][eventRow].eventRectDefaultX;
			eventRectTreppe[eventMap][eventCol][eventRow].y = eventRectTreppe[eventMap][eventCol][eventRow].eventRectDefaultY;
		}
				
		return hitTreppe;
	}
	
	public boolean hitTileGroß(int eventMap, int eventCol, int eventRow) {
		
		boolean hitTileGroß = false;
		
		if(eventMap == gp.getCurrentMap()) {
			gp.getPlayer().getSolidArea().x = gp.getPlayer().worldX + gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = (int) (gp.getPlayer().worldY + gp.getPlayer().getSolidAreaDefaultY());
			eventRect2[eventMap][eventCol][eventRow].x = eventCol * gp.getTileSize() + eventRect2[eventMap][eventCol][eventRow].x;
			eventRect2[eventMap][eventCol][eventRow].y = eventRow * gp.getTileSize() + eventRect2[eventMap][eventCol][eventRow].y;
		
			if(gp.getPlayer().getSolidArea().intersects(eventRect2[eventMap][eventCol][eventRow]) && eventRect2[eventMap][eventCol][eventRow].eventDone == false) {
				
				hitTileGroß = true;
				
				
			}
		
			gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
			eventRect2[eventMap][eventCol][eventRow].x = eventRect2[eventMap][eventCol][eventRow].eventRectDefaultX;
			eventRect2[eventMap][eventCol][eventRow].y = eventRect2[eventMap][eventCol][eventRow].eventRectDefaultY;
		}
				
		return hitTileGroß;
	}
	
	public boolean hitObject(int eventMap, int eventCol, int eventRow) {
		boolean hitObject = false;
			if(eventMap == gp.getCurrentMap()) {
			
				gp.getObj()[eventMap][0].getSolidArea().x = gp.getObj()[eventMap][0].worldX + gp.getObj()[eventMap][0].getSolidAreaDefaultX();
				gp.getObj()[eventMap][0].getSolidArea().y = (int) (gp.getObj()[eventMap][0].worldY + gp.getObj()[eventMap][0].getSolidAreaDefaultY());
				eventRect1[eventMap][eventCol][eventRow].x = eventCol * gp.getTileSize() + eventRect1[eventMap][eventCol][eventRow].x;
				eventRect1[eventMap][eventCol][eventRow].y = eventRow * gp.getTileSize() + eventRect1[eventMap][eventCol][eventRow].y;
		
				if(gp.getObj()[eventMap][0].getSolidArea().intersects(eventRect1[eventMap][eventCol][eventRow]) && eventRect1[eventMap][eventCol][eventRow].eventDone == false) {
					hitObject = true;
				
		
				
					
				}
			}
		
			gp.getObj()[eventMap][0].getSolidArea().x = gp.getObj()[eventMap][0].getSolidAreaDefaultX();
			gp.getObj()[eventMap][0].getSolidArea().y = gp.getObj()[eventMap][0].getSolidAreaDefaultY();
			eventRect1[eventMap][eventCol][eventRow].x = eventRect1[eventMap][eventCol][eventRow].eventRectDefaultX;
			eventRect1[eventMap][eventCol][eventRow].y = eventRect1[eventMap][eventCol][eventRow].eventRectDefaultY;
				
		return hitObject;
	}
	
	
	// TELEPORT innerhalb einer Map
	/*public void intraTeleport(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.setCurrentDialogue("Magie!");
		gp.getPlayer().worldX = gp.getTileSize() * [Zahl];
		gp.getPlayer().worldY = gp.getTileSize() * [Zahl];
		eventRect[eventCol][eventRow].eventDone = true; // ODER:
		canTouchEvent = false;
	} */
	
	// TELEPORT in eine andere Map
	public void interTeleport(int map, int col, double row) {
		
		gp.setCurrentMap(map);
		gp.getPlayer().worldX = gp.getTileSize() * col;
		gp.getPlayer().worldY = gp.getTileSize() * row;
		previousEventX = gp.getPlayer().worldX;
		previousEventY = gp.getPlayer().worldY;
		canTouchEvent = false;
		canTouchTreppe = false;
		
		
	}
	
	/*public void nextDialogue {
		
	} */
}
