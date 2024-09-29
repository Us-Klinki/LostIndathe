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
		mapTileNum = new int[gp.getMaxScreenCol()] [gp.getMaxScreenRow()];
		
		getTileImage();
		loadMap("/maps/Map.txt");
		
	}
	

	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/boden_textur.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wand_textur.png"));
			
			
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
			
			while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
				
				String line = br.readLine();
				
				while(col < gp.getMaxScreenCol()) {
					
				
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col] [row] = num;
					col++;
				}
				if(col == gp.getMaxScreenCol()) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	public void draw(Graphics g2) {
		
		int x = 0;
		int y = 0;
		int col = 0;
		int row = 0;
		
		while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
			int tileNum = mapTileNum[col] [row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.getMaxScreenCol()) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}
