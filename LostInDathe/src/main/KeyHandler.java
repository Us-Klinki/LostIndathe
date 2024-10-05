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
		
		//TITEL STATE
		if(gp.gameState == gp.titleState) {
			
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				//if(gp.ui.getCommandNum() < 1) {
				gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
				if(gp.ui.getCommandNum() == -1) {
					gp.ui.setCommandNum(2);
				}
			}
			
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
				if(gp.ui.getCommandNum() == 3) {
					gp.ui.setCommandNum(0);
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.getCommandNum() == 0) {
					gp.gameState = gp.playState;
					gp.stopMusic(3);
					gp.playMusic(2);
				}
				if(gp.ui.getCommandNum() == 1) {
					// für Savegames später
				}
				if(gp.ui.getCommandNum() == 2) {
					System.exit(0);
				}
			}
		}
		
		
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
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
		
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
	}


}
