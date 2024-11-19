package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];
	EventRect eventRect1[][][];
	EventRect eventRect2[][][];
	EventRect eventRect3[][][];
	
	int previousEventX;
	double previousEventY;
	boolean canTouchEvent = true;
	public static boolean gesGelöst = false;
	public static boolean gesZuDunkel = false;
	public static boolean playerNotColliding = true;
	public static int i = 0;
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRect1 = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRect2 = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		eventRect3 = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		
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
		
		if(canTouchEvent == true) {
			//if(hit(0, 21, 31, "any") == true) { intraTeleport(gp.dialogueState); }
			// Teleport Bad -> OG; OG -> Bad
			if(hit(0, 20, 30, "any") == true) { interTeleport(1, 13, 11); gp.stopMusic(2); gp.playMusic(6); }
			else if(hit(1, 13, 11, "any") == true) { interTeleport(0, 20, 30); gp.stopMusic(6); gp.playMusic(2); }
			// Teleport OG -> Geschichte; Geschichte -> OG
			else if(hit(1, 12, 15, "any") == true) { interTeleport(2, 29, 18); gp.stopMusic(6); gp.playMusic(2); }
			else if(hit(2, 29, 18, "any") == true) { interTeleport(1, 12, 15); gp.stopMusic(2); gp.playMusic(6); }
			// TEMP Teleport OG -> Info; Info -> OG
			else if(hit(1, 32, 15, "any") == true) { interTeleport(3, 50, 49.9); gp.stopMusic(6); gp.playMusic(2); }
			else if(hit(3, 50, 50, "any") == true) { interTeleport(1, 32, 15); gp.stopMusic(2); gp.playMusic(6); }
			// Teleport OG -> Chemie: Chemie -> OG
			else if(hit(1, 68, 41, "any") == true) { interTeleport(4, 53, 63.9); gp.stopMusic(6); gp.playMusic(2); }
			else if(hit(4, 53, 64, "any") == true) { interTeleport(1, 68, 40.9); gp.stopMusic(2); gp.playMusic(6); }
		}
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
		gp.playSE(5);
		
	}
	
	/*public void nextDialogue {
		
	} */
}
