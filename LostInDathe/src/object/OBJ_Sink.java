package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class OBJ_Sink extends SuperObject {
	
	public OBJ_Sink(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);
		setName("Sink");
		try {
			
			
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/sink.png")));
			uToolObjects.getScaledImage(getImage(), gp.getTileSize(), gp.getTileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
		
	}
}
