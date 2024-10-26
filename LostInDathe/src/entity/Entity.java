package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
public class Entity {

  GamePanel gp;
  public int worldX;
  public int worldY;
  public int speed;
  private String Name;
  
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tright1, tright2, tleft1, tleft2, bright1, bright2, bleft1, bleft2;
  public String direction;
  
  public int spriteCounter = 0;
  public int spriteNum = 1;
  private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  private int solidAreaDefaultX, solidAreaDefaultY;
  private boolean collisionOn = false;
  private int actionLockCounter = 0;
  String dialogues[][];
  int dialogueSet = 0;
  
  public Entity(GamePanel gp) {
	  this.gp = gp;
	  this.dialogues = new String[gp.getMaxMap()][20];
  }
  public void setAction() {}
  public void speak() {}
  public void update () {
	  
	  setAction();
	  
	  setCollisionOn(false);
	  gp.cChecker.checkTile(this);
	  gp.cChecker.checkObject(this, false);
	  gp.cChecker.checkPlayer(this);
	  
	  double moveX = 0;
	  double moveY = 0;
	  
	  if(isCollisionOn() == false) {
		  
		  switch(direction) {
		  case "up": moveY -= speed; break;
		  case "down": moveY += speed; break;
		  case "left": moveX -= speed; break;
		  case "right": moveX += speed; break;
		  }
	  }
	  
	  worldX += moveX;
	  worldY += moveY;
	  
	  if(moveX != 0 || moveY != 0) {
		  spriteCounter++;
			if(spriteCounter > 12) { // jede 1/5-Sekunde
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		else {
			spriteNum = 1; //Stillstand
		}
  }
  public void draw(Graphics2D g2) {
	 BufferedImage image = null;
	 int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
	 int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
	
		// Renderdistanz: So groß wie Bildschirm
		
	 	if(worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().screenX &&
			worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().screenX &&
			worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().screenY &&
			worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;	 
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;	
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
			case "":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
			}
			g2.drawImage (image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
		}
  }
  
  public BufferedImage setup(String imagePath) {
		
	UtilityTool uTool = new UtilityTool();
	BufferedImage Sprite = null;
		
	try {
		Sprite = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
		Sprite = uTool.getScaledImage(Sprite, gp.getTileSize(), gp.getTileSize());
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	return Sprite;
}
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
  public int getActionLockCounter() {
	  return actionLockCounter;
  }
  public void setActionLockCounter(int actionLockCounter) {
	  this.actionLockCounter = actionLockCounter;
  }
  public String getName() {
	  return Name;
  }
  public void setName(String name) {
	  Name = name;
  }
}
