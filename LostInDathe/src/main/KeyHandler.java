package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	
	//GAME STATE
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	// DEBUG
	private boolean debug = false;
	
	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @param debug the debug to set
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
	
		//GAME STATE
		if(code == KeyEvent.VK_K) {
			System.out.println("K wurde gedrückt");
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
				System.out.println("Spiel pausiert");
			}
			else if (gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
				
			}
		}
	
		// DEBUG
		if(code == KeyEvent.VK_Q) {
			if(isDebug() == false) {
				setDebug(true);
			}
			else if(isDebug() == true) {
				setDebug(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}


}
