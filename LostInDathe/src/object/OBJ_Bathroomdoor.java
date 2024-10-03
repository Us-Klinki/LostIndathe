package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bathroomdoor extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Bathroomdoor(GamePanel gp) {
		this.gp = gp;
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
