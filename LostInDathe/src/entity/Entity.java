package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Entity {

  public int worldX;
  public int worldY;
  public int speed;
  
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tright1, tright2, tleft1, tleft2, bright1, bright2, bleft1, bleft2;
  public String direction;
  
  public int spriteCounter = 0;
  public int spriteNum = 1;
  private Rectangle solidArea;
  private int solidAreaDefaultX, solidAreaDefaultY;
  private boolean collisionOn = false;
  String dialogues[] = new String[20];
  
  
/**
 * @return the solidArea
 */
  public Rectangle getSolidArea() {
	  return solidArea;
  }
/**
 * @param solidArea the solidArea to set
 */
  public void setSolidArea(Rectangle solidArea) {
	  this.solidArea = solidArea;
  }
  
/**
 * @return the collisionOn
 */
  public boolean isCollisionOn() {
	  return collisionOn;
  }
/**
 * @param collisionOn the collisionOn to set
 */
  public void setCollisionOn(boolean collisionOn) {
	  this.collisionOn = collisionOn;
  }
/**
 * @return the solidAreaDefaultX
 */
public int getSolidAreaDefaultX() {
	return solidAreaDefaultX;
}
/**
 * @param solidAreaDefaultX the solidAreaDefaultX to set
 */
public void setSolidAreaDefaultX(int solidAreaDefaultX) {
	this.solidAreaDefaultX = solidAreaDefaultX;
}
/**
 * @return the solidAreaDeafaultY
 */
public int getSolidAreaDefaultY() {
	return solidAreaDefaultY;
}
/**
 * @param solidAreaDeafaultY the solidAreaDeafaultY to set
 */
public void setSolidAreaDefaultY(int solidAreaDefaultY) {
	this.solidAreaDefaultY = solidAreaDefaultY;
}
  
}
