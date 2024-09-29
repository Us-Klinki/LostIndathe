package entity;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import tile.TileManager;

public class Entity {

  public int worldX, worldY;
  public int speed;
  
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, tright1, tright2, tleft1, tleft2, bright1, bright2, bleft1, bleft2;
  public String direction;
  
  public int spriteCounter = 0;
  public int spriteNum = 1;

}
