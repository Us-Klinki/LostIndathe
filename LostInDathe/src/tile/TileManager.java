package tile;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityToolTiles;

public class TileManager {

	GamePanel gp;
	private Tile[] tile;
	private int mapTileNum[] [];
	
	public Tile[] getTile() {
		return tile;
	}
	
	public int [][]getMapTileNum() {
		return mapTileNum;
	}
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[20];
		mapTileNum = new int[gp.getMaxWorldCol()] [gp.getMaxWorldRow()];
		
		getTileImage();
		loadMap("/maps/bathroom.txt");
		
	}
	

	public void getTileImage() {
			
		// setup(TileIndex, "Dateiname", Kollision true/false);
		setup(0, "1_bathground", false);
		setup(1, "2_bathwall_plain_bl", true);
		setup(2, "2_bathwall_plain_br", true);
		setup(3, "2_bathwall_plain_tl", true);
		setup(4, "2_bathwall_plain_tr", true);
		setup(5, "3_bathwall_bl", true);
		setup(6, "3_bathwall_br", true);
		setup(7, "3_bathwall_tl", true);
		setup(8, "3_bathwall_tr", true);
		setup(9, "4_bathwall", true);
	}
	
	public void setup(int tileIndex, String imageName, boolean collision) {

	    UtilityToolTiles uToolTiles = new UtilityToolTiles();
	    try {
	        // Initialize the tile
	        tile[tileIndex] = new Tile();
	        
	        // Load the image as BufferedImage first
	        BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream("/Tiles/" + imageName + ".png"));
	        
	        // Scale the image
	        VolatileImage scaledImage = uToolTiles.getScaledImage(bufferedImage, gp.getTileSize(), gp.getTileSize());

	        // Convert the BufferedImage to VolatileImage
	        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment()
	                                                       .getDefaultScreenDevice()
	                                                       .getDefaultConfiguration();
	        VolatileImage volatileImage = gc.createCompatibleVolatileImage(scaledImage.getWidth(), scaledImage.getHeight());
	        volatileImage.createGraphics().drawImage(scaledImage, 0, 0, null);

	        // Assign the VolatileImage to the tile
	        tile[tileIndex].image = volatileImage;
	        
	        // Set collision property
	        tile[tileIndex].setCollision(collision);
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	//grad am telefonieren
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
				
				String line = br.readLine();
				
				while(col < gp.getMaxWorldCol()) {
					
				
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col] [row] = num;
					col++;
				}
				if(col == gp.getMaxWorldCol()) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	public void draw(Graphics g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
			int tileNum = mapTileNum[worldCol] [worldRow];
			
			int worldX = worldCol * gp.getTileSize();
			int worldY = worldRow * gp.getTileSize();
			int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
			int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
			
			// Renderdistanz: So groß wie Bildschirm
			
			if(worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().screenX &&
					worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().screenX &&
					worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().screenY &&
					worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.getMaxWorldCol()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
