package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	
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
		
		// Startbildschirm STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		
		// OPTIONS STATE
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}

	}
	
	public void titleState(int code) {
		
			
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			//if(gp.ui.getCommandNum() < 1) {
			gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
			gp.playSE(4);
			if(gp.ui.getCommandNum() == -1) {
				gp.ui.setCommandNum(2);
			}
		}
			
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
			gp.playSE(4);
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
		debug(code);
	}
		
		
	public void pauseState(int code) {
		if(code == KeyEvent.VK_K) {
			gp.gameState = gp.playState;
		}
		debug(code);
	}	
		
	public void playState(int code) {
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
		if(code == KeyEvent.VK_K) {
			System.out.println("K");
			gp.gameState = gp.pauseState;
			System.out.println(gp.gameState);
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
			System.out.println(gp.gameState);
		}
		debug(code);
	}
	
		// DEBUG
	public void debug(int code)	{
		if(code == KeyEvent.VK_Q) {
			if(isDebug() == false) {
				setDebug(true);
			}
			else if(isDebug() == true) {
				setDebug(false);
			}
		}
	}
	
	public void optionsState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
			gp.playSE(4);
			if(gp.ui.getCommandNum() == -1) {
				gp.ui.setCommandNum(maxCommandNum);
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
			gp.playSE(4);
			if(gp.ui.getCommandNum() > maxCommandNum) {
				gp.ui.setCommandNum(0);
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.subState == 0) {
				if(gp.ui.getCommandNum() == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(4);
				}
				if(gp.ui.getCommandNum() == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(4);
				}
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.subState == 0) {
				if(gp.ui.getCommandNum() == 1 && gp.music.volumeScale < 11) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(4);
				}
				if(gp.ui.getCommandNum() == 2 && gp.se.volumeScale < 11) {
					gp.se.volumeScale++;
					gp.playSE(4);
				}
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
