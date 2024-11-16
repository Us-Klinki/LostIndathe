package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class SuperObject {
	KeyHandler keyH;
	GamePanel gp;
	
	private BufferedImage image;
	private String name;
	private boolean collision = false;
	private boolean key_inside = false;
	private boolean dialogueStarted = false;
	private int worldX, worldY;
	private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	private int solidAreaDefaultX = 0;
	private int solidAreaDefaultY = 0;
	public String dialogues[][];
	private int dialogueSet = 0;
	UtilityTool uToolObjects = new UtilityTool();
	
	public SuperObject (GamePanel gp, KeyHandler keyH) {
		this.keyH = keyH;
		this.gp = gp;
		this.dialogues = new String[30][20];
	}
	public void draw(Graphics2D g2) {		
		int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
		int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
	
		// Renderdistanz: So groß wie Bildschirm
		
		if(worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().screenX &&
				worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().screenX &&
				worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().screenY &&
				worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
			if(keyH.isDebug() == true) {
				g2.setColor(Color.YELLOW);
				g2.drawRect(screenX + getSolidArea().x, screenY + getSolidArea().y, getSolidArea().width, getSolidArea().height);
			} 
		}
		
	}

	public void speak(int i) {

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
			gp.getObj()[gp.getCurrentMap()][i].setDialogue3();
	        gp.gameState = gp.playState; 
	        return;
	    }

	    gp.ui.setCurrentDialogue(dialogues[gp.getCurrentMap()][getDialogueSet()]);
	}
	
	public void setDialogue1() {}
	
	public void setDialogue2() {}
	
	public void setDialogue3() {}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	/**
	 * @return the worldX
	 */
	public int getWorldX() {
		return worldX;
	}
	/**
	 * @param worldX the worldX to set
	 */
	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	public void setWorldY(int worldY) {
		this.worldY = worldY;
		// TODO Auto-generated method stub	
	}
	public int getWorldY() {
		return worldY;
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
	 * @return the solidAreaDefaultY
	 */
	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}

	/**
	 * @param solidAreaDefaultY the solidAreaDefaultY to set
	 */
	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}

	/**
	 * @return the collision
	 */
	public boolean isCollision() {
		return collision;
	}

	/**
	 * @param collision the collision to set
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public boolean isKey_inside() {
		return key_inside;
	}

	public void setKey_inside(boolean key_inside) {
		this.key_inside = key_inside;
	}
	public boolean isDialogueStarted() {
		return dialogueStarted;
	}
	public void setDialogueStarted(boolean dialogueStarted) {
		this.dialogueStarted = dialogueStarted;
	}
	public int getDialogueSet() {
		return dialogueSet;
	}
	public void setDialogueSet(int dialogueSet) {
		this.dialogueSet = dialogueSet;
	}

}
