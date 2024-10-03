package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Toilet extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Toilet(GamePanel gp) {
		this.gp = gp;
		setName("Toilet");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/toilet.png")));
			uToolObjects.getScaledImage(getImage(), gp.getTileSize(), gp.getTileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
	}
}
