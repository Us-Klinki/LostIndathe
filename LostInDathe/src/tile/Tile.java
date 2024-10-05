package tile;

import java.awt.image.VolatileImage;

public class Tile {
	
	public VolatileImage image;
	private boolean collision = false;
	
	public boolean getCollision() {
		return collision;
	}
	
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}

