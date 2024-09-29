package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Entity {

  public int worldX, worldY;
  public int speed;
  
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tright1, tright2, tleft1, tleft2, bright1, bright2, bleft1, bleft2;
  public String direction;
  
  public int spriteCounter = 0;
  public int spriteNum = 1;
  private Rectangle solidArea;
  private boolean collisionOn = false;
  
  
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
  
}
