package tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[] [];
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.getMaxWorldCol()] [gp.getMaxWorldRow()];
		
		getTileImage();
		loadMap("/maps/test.txt");
		
	}
	

	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/boden_textur.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wand_textur.png"));
			tile[1].setCollision(true);
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
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
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
			}
			
			worldCol++;
			
			if(worldCol == gp.getMaxWorldCol()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
