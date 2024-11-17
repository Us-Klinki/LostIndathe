package main;


public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	public static boolean gesGelöst = false;
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.getMaxMap()][gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		
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
		int yDistance = Math.abs(gp.getPlayer().worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.getTileSize()) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			//if(hit(0, 21, 31, "any") == true) { intraTeleport(gp.dialogueState); }
			if(hit(0, 20, 30, "any") == true) { interTeleport(2, 10, 44); gp.stopMusic(2); gp.playMusic(6); }
			else if(hit(2, 10, 44, "any") == true) { interTeleport(0, 20, 25); gp.stopMusic(6); gp.playMusic(2); }
		}
		
		if(hitObject(2, 16, 35) == true) {
			gesGelöst = true;
		}
	}
	
	public boolean hit(int eventMap, int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		if(eventMap == gp.getCurrentMap()) {
			gp.getPlayer().getSolidArea().x = gp.getPlayer().worldX + gp.getPlayer().getSolidAreaDefaultX();
			gp.getPlayer().getSolidArea().y = gp.getPlayer().worldY + gp.getPlayer().getSolidAreaDefaultY();
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
	
	public boolean hitObject(int eventMap, int eventCol, int eventRow) {
		boolean hitObject = false;
			if(eventMap == gp.getCurrentMap()) {
			
				gp.getObj()[eventMap][0].getSolidArea().x = gp.getObj()[eventMap][0].worldX + gp.getObj()[eventMap][0].getSolidAreaDefaultX();
				gp.getObj()[eventMap][0].getSolidArea().y = gp.getObj()[eventMap][0].worldY + gp.getObj()[eventMap][0].getSolidAreaDefaultY();
				eventRect[eventMap][eventCol][eventRow].x = eventCol * gp.getTileSize() + eventRect[eventMap][eventCol][eventRow].x;
				eventRect[eventMap][eventCol][eventRow].y = eventRow * gp.getTileSize() + eventRect[eventMap][eventCol][eventRow].y;
		
				if(gp.getObj()[eventMap][0].getSolidArea().intersects(eventRect[eventMap][eventCol][eventRow]) && eventRect[eventMap][eventCol][eventRow].eventDone == false) {
					hitObject = true;
				
					previousEventX = gp.getPlayer().worldX;
					previousEventY = gp.getPlayer().worldY;
				
					
				}
			}
		
			gp.getObj()[eventMap][0].getSolidArea().x = gp.getObj()[eventMap][0].getSolidAreaDefaultX();
			gp.getObj()[eventMap][0].getSolidArea().y = gp.getObj()[eventMap][0].getSolidAreaDefaultY();
			eventRect[eventMap][eventCol][eventRow].x = eventRect[eventMap][eventCol][eventRow].eventRectDefaultX;
			eventRect[eventMap][eventCol][eventRow].y = eventRect[eventMap][eventCol][eventRow].eventRectDefaultY;
				
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
	public void interTeleport(int map, int col, int row) {
		
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
