package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Map extends TileManager {

    GamePanel gp;
    BufferedImage worldMap[];
    public boolean miniMapOn = false;

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        worldMap = new BufferedImage[gp.getMaxMap()];
    }

    public BufferedImage getWorldMap(int mapIndex) {
        if (worldMap[mapIndex] == null) {
            int worldMapWidth = gp.getTileSize() * gp.getMaxWorldCol();
            int worldMapHeight = gp.getTileSize() * gp.getMaxWorldRow();

            worldMap[mapIndex] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = worldMap[mapIndex].createGraphics();

            int col = 0;
            int row = 0;
            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                int tileNum = getMapTileNum()[mapIndex][col][row];
                int x = gp.getTileSize() * col;
                int y = gp.getTileSize() * row;
                g2.drawImage(getTile()[tileNum].image, x, y, null);

                col++;
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            g2.dispose(); // Speicher freigeben
        }
        return worldMap[mapIndex];
    }


    public void clearUnusedWorldMaps() {
        for (int i = 0; i < worldMap.length; i++) {
            if (i != gp.getCurrentMap()) {
                worldMap[i] = null; // Speicher für ungenutzte Karten freigeben
            }
        }
    }

    public void drawFullMapScreen(Graphics2D g2) {
        // Hintergrund
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Karte Zeichnen
        int width = 600;
        int height = 600;
        int x = gp.getScreenWidth() / 2 - width / 2;
        int y = gp.getScreenHeight() / 2 - height / 2;

        BufferedImage currentMapImage = getWorldMap(gp.getCurrentMap());
        g2.drawImage(currentMapImage, x, y, width, height, null);
    }
}
