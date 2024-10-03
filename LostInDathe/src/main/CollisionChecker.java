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
    		tileNum1 = gp.tileM.getMapTileNum()[entityLeftCol][entityTopRow];
    		tileNum2 = gp.tileM.getMapTileNum()[entityRightCol] [entityTopRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    	
    		break;
    	case "down":
    		entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[entityLeftCol][entityBottomRow];
    		tileNum2 = gp.tileM.getMapTileNum()[entityRightCol] [entityBottomRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    		break;
    	
    	case "left":
    		entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[entityLeftCol][entityTopRow];
    		tileNum2 = gp.tileM.getMapTileNum()[entityLeftCol] [entityBottomRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    		break;
    	
    	case "right":
    		entityRightCol = (entityRightWorldX + entity.speed)/ gp.tileSize;
    		tileNum1 = gp.tileM.getMapTileNum()[entityRightCol][entityTopRow];
    		tileNum2 = gp.tileM.getMapTileNum()[entityRightCol] [entityBottomRow];
    		if(gp.tileM.getTile()[tileNum1].getCollision() == true || gp.tileM.getTile()[tileNum2].getCollision() == true) {
    	
    			entity.setCollisionOn(true);
    		}
    		break;
    	
    	}
  
	}

public int checkObject(Entity entity, boolean player) {

    int objectIndex = 999; // Standardwert, falls kein Objekt gefunden wird

    // Überprüfe alle Objekte im Spiel
    for (int i = 0; i < gp.getObj().length; i++) {

        if (gp.getObj()[i] != null) {
            // Bestimme die Hitbox (SolidArea) für die Entität
            entity.getSolidArea().x = entity.worldX + entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.worldY + entity.getSolidAreaDefaultY();
            // Bestimme die Hitbox (SolidArea) für das Objekt
            gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getWorldX() + gp.getObj()[i].getSolidAreaDefaultX();
            gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getWorldY() + gp.getObj()[i].getSolidAreaDefaultY();

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
            if (entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
                if (gp.getObj()[i].isCollision()) {
                    entity.setCollisionOn(true);
                }
                if (player) {
                    objectIndex = i; // Wenn der Spieler das Objekt ist, speichere den Index
                }
            }

            // Setze die Hitboxen zurück
            entity.getSolidArea().x = entity.getSolidAreaDefaultX();
            entity.getSolidArea().y = entity.getSolidAreaDefaultY();
            gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getSolidAreaDefaultX();
            gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getSolidAreaDefaultY();
        }
    }

    return objectIndex;
}





}

