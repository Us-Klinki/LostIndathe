package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
public class Entity {

  GamePanel gp;
  KeyHandler keyH;
  public int worldX;
  public double worldY;
  public int speed;
  private String Name;
  private BufferedImage image;
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tright1, tright2, tleft1, tleft2, bright1, bright2, bleft1, bleft2, idle1, idle2;
  public String direction = "";
  private boolean keyInside = false;
  
  public int spriteCounter = 0;
  public int spriteNum = 1;
  private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  private int solidAreaDefaultX, solidAreaDefaultY;
  private boolean collisionOn = false;
  private boolean hasDialogue = false;
  private static boolean dialogueStarted = false;
  private int actionLockCounter = 0;
  protected String dialogues[][];
  int dialogueSet = 0;
  private boolean pullLock;
  
  
  public Entity(GamePanel gp, KeyHandler keyH) {
	  this.gp = gp;
	  this.keyH = keyH;
	  this.dialogues = new String[30][20];
  }
  public void setAction() {}
  
  public void speak(int i, boolean isNPC) {
	  if(isNPC) {
		  switch(gp.getPlayer().direction) {
		  case "up": 
			  direction = "down";
		  	  break;
		  case "down": 
	  		  direction = "up";
	  		  break;
		  case "left": 
			  direction = "right";
			  break;
		  case "right": 
			  direction = "left";
			  break;
		  }
	  }

	  if (!isDialogueStarted()) {
		  setDialogueStarted(true);
	      if (dialogues[gp.getCurrentMap()][getDialogueSet()] == null) {
	    	  setDialogueSet(0);
	      }
	      gp.ui.setCurrentDialogue(dialogues[gp.getCurrentMap()][getDialogueSet()]);
	      return;
	  }

	  setDialogueSet(getDialogueSet() + 1);
	    
	  if (dialogues[gp.getCurrentMap()][getDialogueSet()] == null) {
		  setDialogueSet(0);
	      setDialogueStarted(false); 
	      if(gp.getObj()[gp.getCurrentMap()][i] != null) {
	    	  gp.getObj()[gp.getCurrentMap()][i].setDialogue20();
	      }
	      if(gp.getNpc()[gp.getCurrentMap()][i] != null) {
		      gp.getNpc()[gp.getCurrentMap()][i].setDialogue20();
	      }
	      gp.gameState = gp.playState;
	      gp.stopSE(1);
	      return;
	  }

	  gp.ui.setCurrentDialogue(dialogues[gp.getCurrentMap()][getDialogueSet()]);
  }
  
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
	  if(moveX == 0 && moveY == 0) {
		  direction = "";
	  }
	  worldX += moveX;
	  worldY += moveY;
	  
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
  public void draw(Graphics2D g2) {
	 BufferedImage image = null;
	 int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
	 int screenY = (int) (worldY - gp.getPlayer().worldY + gp.getPlayer().screenY);
	
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
					image = idle1;
				}
				if(spriteNum == 2) {
					image = idle2;
				}
				break;
			}
			g2.drawImage (image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
			if(keyH.isDebug() == true) {
				g2.setColor(Color.YELLOW);
				g2.drawRect(screenX + getSolidArea().x, screenY + getSolidArea().y, getSolidArea().width, getSolidArea().height);
			} 
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
  


	public void setDialogue1() {}
	
	public void setDialogue2() {}
	
	public void setDialogue3() {}
	
	public void setDialogue4() {}
	
	public void setDialogue5() {}
	
	public void setDialogue6() {}
	
	public void setDialogue7() {}
	
	public void setDialogue8() {}
	
	public void setDialogue9() {}
	
	public void setDialogue10() {}
	
	public void setDialogue20() {}
	
	public void pull(Entity object, Entity player, int speed) {}
	
	public void move(Entity object, String direction, int speed) {}
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
  public int getDialogueSet() {
	return dialogueSet;
  }
  public void setDialogueSet(int dialogueSet) {
	this.dialogueSet = dialogueSet;
  }
public static boolean isDialogueStarted() {
	return dialogueStarted;
}
public void setDialogueStarted(boolean dialogueStarted) {
	Entity.dialogueStarted = dialogueStarted;
}
/**
 * @return the pullLock
 */
public boolean isPullLock() {
	return pullLock;
}
/**
 * @param pullLock the pullLock to set
 */
public void setPullLock(boolean pullLock) {
	this.pullLock = pullLock;
}
public BufferedImage getImage() {
	return image;
}
public void setImage(BufferedImage image) {
	this.image = image;
}
public boolean isKeyInside() {
	return keyInside;
}
public void setKeyInside(boolean keyInside) {
	this.keyInside = keyInside;
}

/**
 * @return the hasDialogue
 */
public boolean isHasDialogue() {
	return hasDialogue;
}
/**
 * @param hasDialogue the hasDialogue to set
 */
public void setHasDialogue(boolean hasDialogue) {
	this.hasDialogue = hasDialogue;
}
}
