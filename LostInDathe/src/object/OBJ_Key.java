package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Key extends SuperObject {
	
	public OBJ_Key(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
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
