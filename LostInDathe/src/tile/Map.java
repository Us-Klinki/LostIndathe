package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager{
	
	GamePanel gp;
	BufferedImage worldMap[];
	public boolean miniMapOn = false;
	
	
	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		createWorldMap();
	}
	
	public void createWorldMap() {
		
		worldMap = new BufferedImage[gp.getMaxMap()];
		int worldMapWidth = gp.getTileSize() * gp.getMaxWorldCol();
		int worldMapHeight = gp.getTileSize() * gp.getMaxWorldRow();
		
		for(int i = 0; i < gp.getMaxMap(); i++) {
			
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;
			
			while(col < gp.getMaxWorldCol()  && row < gp.getMaxWorldRow()) {
				
				int tileNum = getMapTileNum()[i][col][row];
				int x = gp.getTileSize() * col;
				int y = gp.getTileSize() * row;
				g2.drawImage(getTile()[tileNum].image, x, y, null);
				
				col++;
				if(col == gp.getMaxWorldCol()) {
					col = 0;
					row++;	
				}
				
			}
				
		}
		
	}
	public void drawFullMapScreen(Graphics2D g2) {
		
		//Hintergrund
		g2.setColor(Color.black);
		g2.fillRect(0, 0,gp.getScreenWidth(), gp.getScreenHeight());
		
		
		//Map Zeichnen
		int width = 600;
		int height = 600;
		int x = gp.getScreenWidth()/2 - width/2;
		int y = gp.getScreenHeight()/2 - height/2;
		g2.drawImage(worldMap[gp.getCurrentMap()], x, y, width, height, null);
		
	}
}























