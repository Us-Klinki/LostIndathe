package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bathroomdoor extends SuperObject {
	
	public OBJ_Bathroomdoor() {
		
		setName("Bathroomdoor");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/bathroomdoor.png")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
	}
}
