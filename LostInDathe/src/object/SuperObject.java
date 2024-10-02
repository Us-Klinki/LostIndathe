package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	
	private BufferedImage image;
	private String name;
	private boolean collision = false;
	private int worldX, worldY;
	private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	private int solidAreaDefaultX = 0;
	private int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
		int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
	
		// Renderdistanz: So groß wie Bildschirm
		
		if(worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().screenX &&
				worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().screenX &&
				worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().screenY &&
				worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
		}
		
	}
	
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

}
