package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		this.gp = gp;
		setName("Key");
		try {
			
			
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/key.png")));
			uToolObjects.getScaledImage(getImage(), gp.getTileSize(), gp.getTileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
