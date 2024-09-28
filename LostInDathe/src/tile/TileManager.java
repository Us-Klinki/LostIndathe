package tile;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;

	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		
		getTileImage();
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
	public void draw(Graphics g2) {
		
		int x = 0;
		int y = 0;
		
		for(int i = 1; i < this.gp.getMaxScreenRow()-1; i++) {
			for(int j = 1; j < this.gp.getMaxScreenCol()-1; j++) {
				g2.drawImage(tile[0].image, x + 48 * j, y + 48 * i, gp.tileSize, gp.tileSize, null);
			}
		}
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < this.gp.getMaxScreenCol(); i++) {
				g2.drawImage(tile[1].image, x + 48 * i, y + 48*11*j, gp.tileSize, gp.tileSize, null);
			}
		}
		for(int j = 0; j < 2; j++) {
			for(int i = 1; i < this.gp.getMaxScreenRow()-1; i++) {
				g2.drawImage(tile[1].image, x + 48*15*j, y + 48*i, gp.tileSize, gp.tileSize, null);
			}
		}
		//g2.drawImage(tile[0].image, xTile, yTile, gp.tileSize, gp.tileSize, null);
		//g2.drawImage(tile[1].image, xTile + 48, yTile, gp.tileSize, gp.tileSize, null);
	}
}
