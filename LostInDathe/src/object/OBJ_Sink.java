package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Sink extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_Sink(GamePanel gp) {
		this.gp = gp;
		setName("Sink");
		try {
			
			
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/sink.png")));
			uToolObjects.getScaledImage(getImage(), gp.getTileSize(), gp.getTileSize());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
