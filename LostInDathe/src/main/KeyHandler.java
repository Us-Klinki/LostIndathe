package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import entity.Entity;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, pushPressed, pullPressed, licht, lichtDialog;
	private boolean canPressKey = true;
	private long lastKeyPress = 0;
	private final long keyCooldown = 300;
	File saveFile = new File("save.dat");
	
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
		
		// START STATE
		else if(gp.startState) {
			startState(code);
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
		
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		
		// MAP STATE
		else if(gp.gameState == gp.mapState) {
			mapState(code);
		}
		
		// END SCREEN
		else if(gp.gameState == gp.endState) {
			endState(code);
		}

	}
	
	public void titleState(int code) {
	    int maxCommandNum = 0;
	    switch (gp.ui.subStateTitle) {
	        case 0: maxCommandNum = 3; break; // Hauptmenü
	        case 1: maxCommandNum = 0; break; // Infos-Menü
	    }

	    // Navigation
	    if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
	        gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
	        gp.playSE(8);
	        if (gp.ui.getCommandNum() < 0) {
	            gp.ui.setCommandNum(maxCommandNum);
	        }
	    }
	    if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
	        gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
	        gp.playSE(8);
	        if (gp.ui.getCommandNum() > maxCommandNum) {
	            gp.ui.setCommandNum(0);
	        }
	    }

	    // Auswahl im Hauptmenü
	    if (code == KeyEvent.VK_ENTER && gp.ui.subStateTitle == 0) {
	        if (gp.ui.getCommandNum() == 0) {
	            gp.gameState = gp.playState;
	            gp.stopMusic(3);
	            gp.playSE(7);
	            gp.playSE(23);
	            //gp.playMusic(2);
	            /*if(gp.gameState == gp.dialogueState) {
	            	gp.stopSE(23);
	            }*/
	        }
	        
	        
	        if(gp.ui.getCommandNum() == 1 && saveFile.exists()) {
	        	gp.saveLoad.load();
	        	gp.gameState = gp.playState;
	            gp.stopMusic(3);
	            gp.playSE(7);
	            gp.playMusic(52);
	        }
	        if (gp.ui.getCommandNum() == 2) {
	            System.exit(0);
	        }
	        if (gp.ui.getCommandNum() == 3) { // "Infos" auswählen
	            gp.ui.subStateTitle = 1;
	            gp.ui.setCommandNum(0);
	            enterPressed = false;
	            //gp.repaint();
	        }
	    }

	    // Rückkehr aus dem Infos-Menü
	    if (code == KeyEvent.VK_ESCAPE && gp.ui.subStateTitle == 1) {
	        gp.ui.subStateTitle = 0;
	        gp.ui.setCommandNum(3); // Zurück zu "Infos"
	        //System.out.println("Hauptmenü wird erneut angezeigt.");
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
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_K) {
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.mapState;
		}
		if(code == KeyEvent.VK_COMMA) { 
		    pushPressed = true;
		}
		if(code == KeyEvent.VK_PERIOD) {
			pullPressed = true;
		}
		
		if(code == KeyEvent.VK_L) {
			lichtDialog = true;
		}
		
		if(code == KeyEvent.VK_L && gp.getCurrentMap() == 3) {
			licht = true;
		}

		if(code == KeyEvent.VK_ESCAPE) {
	        long currentTime = System.currentTimeMillis();
			if (canPressKey && (currentTime - lastKeyPress) >= keyCooldown) {
	            canPressKey = false;
	            lastKeyPress = currentTime;
				gp.gameState = gp.optionsState;
			}
		}
		debug(code);
		if(code == KeyEvent.VK_R) {
			switch(gp.getCurrentMap()) {
			case 0: gp.tileM.loadMap("/maps/bathroom.txt", 0); break;
			case 1: gp.tileM.loadMap("/maps/3OG_Gang.txt", 1); break;
			case 2: gp.tileM.loadMap("/maps/Geschitsraum.txt", 2); break;
			case 3: gp.tileM.loadMap("/maps/Informatikraum.txt", 3); break;
			case 4: gp.tileM.loadMap("/maps/Chemieraum.txt", 4); break;
			case 5: gp.tileM.loadMap("/maps/1OG_Gang.txt", 5); break;
			case 6: gp.tileM.loadMap("/maps/Bioraum.txt", 6); break;
			}
		}
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
		
		if(code == KeyEvent.VK_ESCAPE) {						//Presscooldown
	        long currentTime = System.currentTimeMillis();
			if (canPressKey && (currentTime - lastKeyPress) >= keyCooldown) {
	            canPressKey = false;
	            lastKeyPress = currentTime;
	            gp.gameState = gp.playState;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 6; break;
		case 3: maxCommandNum = 1; break;
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.setCommandNum(gp.ui.getCommandNum() - 1);
			gp.playSE(8);
			if(gp.ui.getCommandNum() == -1) {
				gp.ui.setCommandNum(maxCommandNum);
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.setCommandNum(gp.ui.getCommandNum() + 1);
			gp.playSE(8);
			if(gp.ui.getCommandNum() > maxCommandNum) {
				gp.ui.setCommandNum(0);
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.subState == 0) {
				if(gp.ui.getCommandNum() == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(8);
				}
				if(gp.ui.getCommandNum() == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(8);
				}
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.subState == 0) {
				if(gp.ui.getCommandNum() == 1 && gp.music.volumeScale < 11) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(8);
				}
				if(gp.ui.getCommandNum() == 2 && gp.se.volumeScale < 11) {
					gp.se.volumeScale++;
					gp.playSE(8);
				}
			}
		}
		debug(code);
	}
	
	public void dialogueState(int code) {
		int i = gp.cChecker.checkEntity(gp.getPlayer(), gp.getNpc());
		int j = gp.cChecker.checkObject(gp.getPlayer(), true);
	    if(code == KeyEvent.VK_ENTER) {
	        long currentTime = System.currentTimeMillis();						//PressCooldown
			if (canPressKey && (currentTime - lastKeyPress) >= keyCooldown) {	
	            canPressKey = false;
	            lastKeyPress = currentTime;
	            if(Entity.isDialogueStarted() == true) {
	            	if(gp.gameState == gp.dialogueState && i != 999) {
	            		gp.getNpc()[gp.getCurrentMap()][i].speak(i, true);
	            	}
	            	else if(gp.gameState == gp.dialogueState && j != 999) {
	            		gp.getObj()[gp.getCurrentMap()][j].speak(j, false);
	            	}
	            }
			}	            
	    }
	    debug(code);
	}
	
	public void mapState (int code) {
		
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.playState;
		}
		debug(code);
	}
	
	public void endState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			System.exit(0);
		}
		debug(code);
	}
	
	public void startState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.startState = false;
			gp.stopSE(23);
			gp.playMusic(52);
		}
		debug(code);
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
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
			canPressKey = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			canPressKey = true;
		}
		if(code == KeyEvent.VK_COMMA) {
		    pushPressed = false;
		}
		if(code == KeyEvent.VK_PERIOD) {
			pullPressed = false;
		}

	}


}
