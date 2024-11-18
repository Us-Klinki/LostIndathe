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
	private int mapTileNum[] [] [];
	
	public Tile[] getTile() {
		return tile;
	}
	
	public int [][][]getMapTileNum() {
		return mapTileNum;
	}
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[50];
		mapTileNum = new int[gp.getMaxMap()][gp.getMaxWorldCol()] [gp.getMaxWorldRow()];
		
		getTileImage();
		loadMap("/maps/bathroom.txt", 0);
		loadMap("/maps/subArea.txt", 1);
		loadMap("/maps/Geschitsraum.txt", 2);
		
	}
	

	public void getTileImage() {
			
		// setup(TileIndex, "Dateiname", Kollision true/false);
		setup(0, "11_bathground", false);
		setup(1, "12_bathwall_plain_bl", true);
		setup(2, "12_bathwall_plain_br", true);
		setup(3, "12_bathwall_plain_tl", true);
		setup(4, "12_bathwall_plain_tr", true);
		setup(5, "13_bathwall_bl", true);
		setup(6, "13_bathwall_br", true);
		setup(7, "13_bathwall_tl", true);
		setup(8, "13_bathwall_tr", true);
		setup(9, "14_bathwall", true);
		setup(10, "15_bathteleport", false);
		setup(11, "21_Background", true);
		setup(12, "22_Wand", true);
		setup(13, "23_Boden1", false);
		setup(14, "23_Boden2", false);
		setup(15, "24_Tisch", true);
		setup(16, "25_Stuhl", true);
		setup(17, "26_Pult1", true);
		setup(18, "26_Pult2", true);
		setup(19, "26_Pult3", true);
		setup(20, "27_Schrank1", true);
		setup(21, "27_Schrank2", true);
		setup(22, "27_Schrank3", true);
		setup(23, "27_Schrank4", true);
		setup(24, "28_Tafel", true);
		setup(25, "28_Tafel2", true);
		setup(26, "291_Mülleimer", true);
		setup(27, "291_Mülleimer2", true);
		setup(28, "291_Mülleimer3", true);
		setup(29, "292_Fenster1", true);
		setup(30, "292_Fenster2", true);
		setup(31, "293_Schrank1", true);
		setup(32, "293_Schrank2", true);
		setup(33, "31_Chemie_Background", true);
		setup(34, "32_Info_Background", true);
		setup(35, "33_Bio_Background", true);
		setup(36, "34_Chemie_Boden", false);
		setup(37, "34_Chemie_Wand", true);
		setup(38, "35_Info_Wand", true);
		setup(39, "36_Bio_Wand", true);
		setup(40, "37_Chemie_Boden", false);
		setup(41, "38_Info_Boden", false);
		setup(42, "39_Bio_Boden", false);
		
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
	public void loadMap(String filePath, int map) {
		
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
					
					mapTileNum[map][col] [row] = num;
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
			int tileNum = mapTileNum[gp.getCurrentMap()][worldCol] [worldRow];
			
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
