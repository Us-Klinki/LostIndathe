package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Bathroomdoor extends SuperObject {
	
	
	public OBJ_Bathroomdoor(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Bathroomdoor");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/bathroomdoor.png")));
			uToolObjects.getScaledImage(getImage(), gp.getTileSize(), gp.getTileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
	}
}
