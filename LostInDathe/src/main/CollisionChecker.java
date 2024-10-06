package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	
	
	public CollisionChecker (GamePanel gp) {
		this.gp = gp;
	}
	
public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.worldX + entity.getSolidArea().x;
    int entityRightWorldX = entity.worldX + entity.getSolidArea().x + entity.getSolidArea().width;
    int entityTopWorldY = entity.worldY + entity.getSolidArea().y;
    int entityBottomWorldY = entity.worldY + entity.getSolidArea().y + entity.getSolidArea().height;

    int entityLeftCol = entityLeftWorldX/gp.tileSize;
    int entityRightCol = entityRightWorldX/gp.tileSize;
    int entityTopRow = entityTopWorldY/gp.tileSize;
    int entityBottomRow = entityBottomWorldY/gp.tileSize;
    
    int tileNum1, tileNum2;
    
    switch(entity.direction) {
    	case "up":
    		entityTopRow = (entityTopWorldY - entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityTopRow];
    		tileNum2 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityRightCol] [entityTopRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    	
    		break;
    	case "down":
    		entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityBottomRow];
    		tileNum2 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityRightCol] [entityBottomRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    		break;
    	
    	case "left":
    		entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityTopRow];
    		tileNum2 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityLeftCol] [entityBottomRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    		break;
    	
    	case "right":
    		entityRightCol = (entityRightWorldX + entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityRightCol][entityTopRow];
    		tileNum2 = gp.tileM.getMapTileNum()[gp.getCurrentMap()][entityRightCol] [entityBottomRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    		break;
    	
    	}
  
	}

public int checkObject(Entity entity, boolean player) {

    int objectIndex = 999; // Standardwert, falls kein Objekt gefunden wird

    // Überprüfe alle Objekte im Spiel
    for (int i = 0; i < gp.getObj()[1].length; i++) { // AUF MEHRERE MAPS ANGEPASST

        if (gp.getObj()[gp.getCurrentMap()][i] != null) {
            // Bestimme die Hitbox (SolidArea) für die Entität
            entity.getSolidArea().x = entity.worldX + entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.worldY + entity.getSolidAreaDefaultY();
            // Bestimme die Hitbox (SolidArea) für das Objekt
            gp.getObj()[gp.getCurrentMap()][i].getSolidArea().x = gp.getObj()[gp.getCurrentMap()][i].getWorldX() + gp.getObj()[gp.getCurrentMap()][i].getSolidAreaDefaultX();
            gp.getObj()[gp.getCurrentMap()][i].getSolidArea().y = gp.getObj()[gp.getCurrentMap()][i].getWorldY() + gp.getObj()[gp.getCurrentMap()][i].getSolidAreaDefaultY();

            // Überprüfe die Richtung der Entität
            switch (entity.direction) {
            	case "up":
            		entity.getSolidArea().y -= entity.speed;
            		break;
            	case "down":
            		entity.getSolidArea().y += entity.speed;
            		break;   
                 case "left":
                    entity.getSolidArea().x -= entity.speed;
                    break;
                 case "right":
                    entity.getSolidArea().x += entity.speed;
                    break;
            }

            // Prüfe, ob die Hitboxen sich überlappen
            if (entity.getSolidArea().intersects(gp.getObj()[gp.getCurrentMap()][i].getSolidArea())) {
                if (gp.getObj()[gp.getCurrentMap()][i].isCollision()) {
                    entity.setCollisionOn(true);
                }
                if (player) {
                    objectIndex = i; // Wenn der Spieler das Objekt ist, speichere den Index
                }
            }

            // Setze die Hitboxen zurück
            entity.getSolidArea().x = entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.getSolidAreaDefaultY();
            gp.getObj()[gp.getCurrentMap()][i].getSolidArea().x = gp.getObj()[gp.getCurrentMap()][i].getSolidAreaDefaultX();
            gp.getObj()[gp.getCurrentMap()][i].getSolidArea().y = gp.getObj()[gp.getCurrentMap()][i].getSolidAreaDefaultY();
        }
    }

    return objectIndex;
}





}

