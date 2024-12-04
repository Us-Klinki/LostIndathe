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
		
		tile = new Tile[200];
		mapTileNum = new int[gp.getMaxMap()][gp.getMaxWorldCol()] [gp.getMaxWorldRow()];
		
		getTileImage();
		loadMap("/maps/bathroom.txt", 0);
		loadMap("/maps/3OG_Gang.txt", 1);
		loadMap("/maps/Geschitsraum.txt", 2);
		loadMap("/maps/Informatikraum.txt", 3);
		loadMap("/maps/Chemieraum.txt", 4);
		loadMap("/maps/1OG_Gang.txt", 5);
		loadMap("/maps/Bioraum.txt", 6);
		
		
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
		setup(36, "34_Chemie_Boden", true);
		setup(37, "34_Chemie_Wand", true);
		setup(38, "35_Info_Wand", true);
		setup(39, "36_Bio_Wand", true);
		setup(40, "37_Chemie_Boden", false);
		setup(41, "38_Info_Boden", false);
		setup(42, "39_Bio_Boden", false);
		setup(43, "41_Gang_Background", true);
		setup(44, "42_Gang_Boden", false);
		setup(45, "43_Gang_Wand", true);
		setup(46, "44_Fenster1", true);
		setup(47, "44_Fenster2", true);
		setup(48, "44_Fenster3", true);
		setup(49, "44_Fenster4", true);
		setup(50, "45_Gang_Treppe1", false);
		setup(51, "45_Gang_Treppe2", true);
		setup(52, "46_Spind1", true);
		setup(53, "46_Spind2", true);
		setup(54, "47_Heizung1", true);
		setup(55, "47_Heizung2", true);
		setup(56, "47_Heizung3", true);
		setup(57, "47_Heizung4", true);
		setup(58, "51_Fenster", true);
		setup(59, "52_Whiteboard1", true);
		setup(60, "52_Whiteboard2", true);
		setup(61, "53_Tisch1", true);
		setup(62, "53_Tisch2", true);
		setup(63, "53_Tisch3", true);
		setup(64, "53_Tisch4", true);
		setup(65, "53_Tisch5", true);
		setup(66, "53_Tisch6", true);
		setup(67, "53_Tisch7", true);
		setup(68, "53_Tisch8", true);
		setup(69, "53_Tisch91", true);
		setup(70, "53_Tisch92", true);
		setup(71, "53_Tisch93", true);
		setup(72, "53_Tisch94", true);
		setup(73, "54_Schrank1", true);
		setup(74, "54_Schrank2", true);
		setup(75, "54_Schrank3", true);
		setup(76, "54_Schrank4", true);
		setup(77, "54_Schrank5", true);
		setup(78, "54_Schrank6", true);
		setup(79, "551_Tisch", true);
		setup(80, "552_Stuhl", true);
		setup(81, "553_Pult.", true);
		setup(82, "553_Pult1", true);
		setup(83, "553_Pult3", true);
		setup(84, "56_Schrank1", true);
		setup(85, "56_Schrank2", true);
		setup(86, "56_Schrank3", true);
		setup(87, "56_Schrank4", true);
		setup(88, "61_Schrank1", true);
		setup(89, "61_Schrank2", true);
		setup(90, "62_Tisch", true);
		setup(91, "63_Stuhl", true);
		setup(92, "64_Pult1", true);
		setup(93, "64_Pult2", true);
		setup(94, "64_Pult3", true);
		setup(95, "65_Schrank1", true);
		setup(96, "65_Schrank2", true);
		setup(97, "66_Waschbecken", true);
		setup(98, "67_Pult1", true);
		setup(99, "67_Pult2 Copy", true);
		setup(100, "67_Pult3", true);
		setup(101, "67_Pult4", true);
		setup(102, "67_Pult5", true);
		setup(103, "67_Pult6", true);
		setup(104, "67_Pult7", true);
		setup(105, "67_Pult8", true);
		setup(106, "67_Pult9", true);
		setup(107, "67_Pult91", true);
		setup(108, "67_Pult92", true);
		setup(109, "71_Fenster1", true);
		setup(110, "71_Fenster2", true);
		setup(111, "72_Tafel1", true);
		setup(112, "72_Tafel2", true);
		setup(113, "72_Tafel3", true);
		setup(114, "73_Schrank1", true);
		setup(115, "73_Schrank2", true);
		setup(116, "73_Schrank3", true);
		setup(117, "73_Schrank4", true);
		setup(118, "73_Schrank5", true);
		setup(119, "73_Schrank6", true);
		setup(120, "73_Schrank7", true);
		setup(121, "73_Schrank8", true);
		setup(122, "73_Schrank9", true);
		setup(123, "73_Schrank91", true);
		setup(124, "74_Gang_Fade", false);
		setup(125, "75_Schrank1", true);
		setup(126, "75_Schrank2", true);
		setup(127, "76_Schrank1", true);
		setup(128, "76_Schrank2", true);
		
		setup(129, "77_Schrank1", true);
		setup(130, "77_Schrank2", true);
		setup(131, "77_Schrank3", true);
		setup(132, "77_Schrank4", true);
		setup(133, "77_Schrank5", true);
		setup(134, "77_Schrank7", true);
		setup(135, "77_Schrank8", true);
		setup(136, "77_Schrank9", true);
		setup(137, "78_Schrank1", true);
		setup(138, "78_Schrank2", true);
		setup(139, "78_Schrank3", true);
		setup(140, "78_Schrank4", true);
		setup(141, "78_Schrank5", true);
		setup(142, "78_Schrank6", true);
		setup(143, "79_Waschbecken1", true);
		setup(144, "79_Waschbecken2", true);
		setup(145, "81_Gang_Fade2", false);
		setup(146, "81_Gang_Fade3", true);
		setup(147, "81_Gang_Fade4", true);
		setup(148, "82_bad_trennwand", true);
		setup(149, "82_bad_trennwand2", true);
		setup(150, "82_bad_trennwand3", true);
		
		
		
		

	
		
		
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
			int screenY = (int) (worldY - gp.getPlayer().worldY + gp.getPlayer().screenY);
			
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
