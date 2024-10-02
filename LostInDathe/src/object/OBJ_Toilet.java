package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Toilet extends SuperObject {
	
	public OBJ_Toilet() {
		
		setName("Toilet");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/toilet.png")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
	}
}
