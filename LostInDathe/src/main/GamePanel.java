package main;

import entity.Entity;

import entity.Player;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tile.Map;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	//DEBUG
	private int currentFPS = 0;
	
	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
  
	final int tileSize = originalTileSize * scale; // 48*48 tile size
	final int maxScreenCol = 32;
	final int maxScreenRow = 18;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
  
	// WORLD SETTINGS
	final int maxWorldCol = 50;
	final int maxWorldRow = 50;
	private final int maxMap = 16;
	private int currentMap = 0;
	
	// Für Vollbild
	int screenWidthVollbild = screenWidth;
	int screenHeightVollbild = screenHeight;
	VolatileImage tempScreen;
	//BufferedImage tempScreen;
	Graphics2D g2;
	private boolean fullScreenOn = false;
	
	
	//GETTER-SETTER
	public int getTileSize() {
		return tileSize;
	}
	public int getMaxScreenCol() {
		return maxScreenCol;
	}
  
	public int getMaxScreenRow() {
		return maxScreenRow;
	}
  
	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
  
	public int getMaxWorldCol() {
		return maxWorldCol;
	}
  
 	public int getMaxWorldRow() {
 		return maxWorldRow;
 	}
 	
 	/**
 	 * @return the player
 	 */
 	public Player getPlayer() {
 		return player;
 	}
 	/**
 	 * @param player the player to set
 	 */
 	public void setPlayer(Player player) {
 		this.player = player;
 	}
 	/**
 	 * @return the obj
 	 */
 	public Entity[][] getObj() {
 		return obj;
 	}
 	/**
 	 * @param obj the obj to set
 	 */
 	public void setObj(Entity obj[][]) {
 		this.obj = obj;
 	}
	public Entity[][] getNpc() {
		return npc;
	}
	public void setNpc(Entity npc[][]) {
		this.npc = npc;
	}
 	/**
	 * @return the fullScreenOn
	 */
	public boolean isFullScreenOn() {
		return fullScreenOn;
	}
	/**
	 * @param fullScreenOn the fullScreenOn to set
	 */
	public void setFullScreenOn(boolean fullScreenOn) {
		this.fullScreenOn = fullScreenOn;
	}
	/**
	 * @return the maxMap
	 */
	public int getMaxMap() {
		return maxMap;
	}
	/**
	 * @return the currentMap
	 */
	public int getCurrentMap() {
		return currentMap;
	}
	/**
	 * @param currentMap the currentMap to set
	 */
	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}
  
 	// FPS
 	int FPS = 60;
 	
 	// System
 	TileManager tileM = new TileManager(this);
 	KeyHandler keyH = new KeyHandler(this);
 	Sound music = new Sound();
 	Sound se = new Sound();
 	public CollisionChecker cChecker = new CollisionChecker(this);
 	private AssetPlacer aPlacer = new AssetPlacer(this, keyH);
 	public UI ui = new UI(this);
 	public EventHandler eHandler = new EventHandler(this);
 	Config config = new Config(this);
 	Thread gameThread;        //Thread ist nötig damit das Spiel durchgehend läuft
 	Map map = new Map(this);
 	
 	
 	
 	
 	
 	//ENTITY AND OBJECTS
 	private Player player = new Player(this, keyH);
 	private Entity obj[][] = new Entity[maxMap][30];
 	private Entity npc[][] = new Entity[maxMap][10];
 	ArrayList<Entity> entityList = new ArrayList<>();
 	Font debug = new Font("Bahnschrift", Font.BOLD, 24);
 	
  
 	//Game State
 	public int gameState;
 	public final int titleState = 0;
 	public final int playState = 1;
 	public final int pauseState = 2;
 	public final int optionsState = 3;
 	public final int dialogueState = 4;
 	public final int mapState = 5;
 	
 	//private BufferedImage lastFrame;
 	private Color transparentblack = new Color(0, 0, 0, 128);
  
  
 	public GamePanel() {      //GamePanel Compiler
    
 		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
 		this.setBackground(Color.BLACK);
 		this.setDoubleBuffered(true);
 		this.addKeyListener(keyH);
 		this.setFocusable(true);
 		Toolkit.getDefaultToolkit().setDynamicLayout(true);
 		System.setProperty("sun.java2d.opengl", "true"); // OpenGL aktivieren
 		System.setProperty("sun.java2d.translaccel", "true"); // Transparenzbeschleunigung
 		System.setProperty("sun.java2d.ddforcevram", "true"); // Video-RAM-Zwänge
 	}
  
 	public void setupGame() {
 		aPlacer.setObject();
 		aPlacer.setNPC();
	  
 		playMusic(3);
 		gameState = titleState;
 		
 		tempScreen = createVolatileImage(screenWidth, screenHeight);
 		//tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
 		g2 = (Graphics2D)tempScreen.getGraphics();
 		
 		//Hier kann Full Screen ausgeschalten werden
 		if(fullScreenOn == true) {
 			setFullScreen();
 		}
 	}
 	
 	public void setFullScreen() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		double width = screenSize.getWidth();

		double height = screenSize.getHeight();

		Main.getWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);

		screenWidthVollbild = (int) width;

		screenHeightVollbild = (int) height;
                //offset factor to be used by mouse listener or mouse motion listener if you are using cursor in your game. Multiply your e.getX()e.getY() by this.
		//fullScreenOffsetFactor = (float) screenWidth / (float) screenWidth2;

	}
 	public void startGameThread() {       //Thread Compiler
  
 		gameThread = new Thread(this);
 		gameThread.start();
 	}
  
 	@Override
 	public void run() {
    
 		double drawIntervall = 1000000000/FPS;
 		double delta = 0;
 		long lastTime = System.nanoTime();
 		long currentTime;
 		long timer = 0;
 		int drawCount = 0; 
  
 		while(gameThread != null) {
      
 			currentTime = System.nanoTime();
      
 			delta += (currentTime - lastTime)/drawIntervall;
 			if(keyH.isDebug()) {
 				timer += (currentTime-lastTime);
 			}
 			lastTime = currentTime;
      
 			if(delta >= 1) {
    		
 				if (gameState == playState) {
 					update();
 				}
 				drawToTempScreen(); // alles wird temporär in dem Buffer gespeichert
 				drawToScreen();	// Buffer wird auf den Bildschirm gezeichnet
 				delta = 0;
 				if(keyH.isDebug()) {
 					drawCount++;
 				}
 			}
      
 			if(keyH.isDebug()) {
 				if(timer >= 1000000000) {
 					currentFPS = drawCount;
 					drawCount = 0;
 					timer = 0;
 				} 
 			}
 		}
 	}
 	
 	public void update() {              //Darf der Charakter schräg laufen?
    
 		if(gameState == playState) {
 			/*lastFrame = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
 			Graphics2D g2LastFrame = lastFrame.createGraphics();
 			tileM.draw(g2LastFrame);
 			for(int i = 0; i < obj.length; i++) {
 				if(obj[i] != null) {
 					obj[i].draw(g2LastFrame, this, keyH);
 				}
 			}*/

 			getPlayer().update();
 			
 			for(int i = 0; i < npc[1].length; i++) {
 				if(npc[currentMap][i] != null) {
 					npc[currentMap][i].update();
 				}
 			}
 			eHandler.checkEvent();
 			//g2LastFrame.dispose();/* 		
 		}
 		
 			  
	 }
  
 	
 	public void drawToTempScreen() {
 		// DEBUG
 	 	long drawStart = 0;
 	 	if(keyH.isDebug() == true) {
 			drawStart = System.nanoTime();
 	 	}
 	    //g2.clearRect(0, 0, getWidth(), getHeight()); // Clear the entire graphics context	
 	 	// TITLE SCREEN
 	 	if(gameState == titleState) {
 	 		ui.draw(g2);	
 	 	}
 	 	// Map screen 
 	 	else if (gameState == mapState) {
 	 		map.drawFullMapScreen(g2);
 	 		
 	 	}
 	 	// RESTLICHES SPIEL
 	 	else {
 	 		//Überpfrüfung vom game state
 	 	 	if(gameState == playState || gameState == dialogueState) {
 	 	    	// Hier werden die Tiles erzeugt
 	 	 		tileM.draw(g2);
 	 	 		
 	 	 		// Entitäten werden zur Liste hinzugefügt
 	 	 		entityList.add(getPlayer());
 	 	 		
 	 	 		for(int i = 0; i < npc[currentMap].length; i++) {
 	 	 			if(npc[currentMap][i] != null) {
 	 	 			entityList.add(npc[currentMap][i]);
 	 	 			}			
 	 	 		}
 	 	 		
 	 	 		for(int i = 0; i < obj[currentMap].length; i++) {
	 	 			if(obj[currentMap][i] != null) {
	 	 			entityList.add(obj[currentMap][i]);
	 	 			}	
	 	 		}
 	 	 		
 	 	 		// Sortieren
 	 	 		Collections.sort(entityList, new Comparator<Entity>() {

					@Override
					public int compare(Entity e1, Entity e2) {

						int result = Integer.compare(e1.worldY, e2.worldY);
						return result;
					}	 	 			
 	 	 		});
 	 	 		
 	 	 		// Zeichne Entitäten
 	 	 		for(int i = 0; i < entityList.size(); i++) {
 	 	 			entityList.get(i).draw(g2);
 	 	 		}
 	 	 		
 	 	 		// Entitylist leeren
 	 	 		entityList.clear();
 	 	 		
 	 	 		
 	 	 		// ALTE METHODE
 	 	 		
 	 	 		/* Hier werden die Objekte platziert
 	 	 		for(int i = 0; i < obj[1].length; i++) {
 	 	 			if(obj[currentMap][i] != null) {	// sicherstellen, dass Arrayindex immer gefüllt ist
 	 	 				obj[currentMap][i].draw(g2);
 	 	 			}
 	 	 		}
 	 	 		// Hier werden die NPCs platziert
 	 	 		for(int i = 0; i < npc[1].length; i++) {
 	 	 			if(npc[currentMap][i] != null) {	// sicherstellen, dass Arrayindex immer gefüllt ist
 	 	 				npc[currentMap][i].draw(g2);
 	 	 			}
 	 	 		}  
 	 	 		
 	 	 		
 	 	 		// Hier wird der Spieler gespawnt
 	 	 	getPlayer().draw(g2); */
 	 	 	}
 	 	 	else if (gameState == pauseState) {
 	 	 		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
 	 	 		g2.setColor(transparentblack);
 	 	 		g2.fillRect(0, 0, getWidth(), getHeight());
 	 	 		// Den letzten Frame zeichnen
 	 	 		//if (lastFrame != null) {
 	 	 			//g2.drawImage(lastFrame, 0, 0, null); // Zeige den letzten Frame an
 	 	 		//}
 	 	 		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Volle Deckkraft für Text
 	 	 		ui.draw(g2);
 	 	 	}
 	 	 	else if(gameState == optionsState) {
 	 	 		ui.draw(g2);
 	 	    }
 	 	 	if(gameState == dialogueState) {
 	 	 		ui.draw(g2);
 	 	 		for(int i = 0; i < npc[1].length; i++) {
 	 	 			if(npc[currentMap][i] != null) {	// sicherstellen, dass Arrayindex immer gefüllt ist
 	 	 				npc[currentMap][i].draw(g2);
 	 	 			}
 	 	 		}
 	 	 	 	getPlayer().draw(g2);
 	 	 	}
 	 	}
 	
 	 	// DEBUG
 	 	if(keyH.isDebug() == true) {
 	 		long drawEnd = System.nanoTime();
 	 		long passed = drawEnd - drawStart;
 	 		g2.setColor(Color.red);
 	 		g2.setFont(debug);
 	 		g2.drawString("Debug Infos", 10, 100);
 	 		g2.setColor(Color.green);
 	 		g2.drawString("Renderzeit: " + passed + " Nano-Sekunden", 10, 125);
 	 		System.out.println("Draw Time: " + passed);
 	 		g2.setColor(Color.cyan);
 	 		g2.drawString(currentFPS + " FPS", 680, 30);
 	 		g2.setColor(Color.magenta);
 	 		g2.drawString("x: " + player.worldX / tileSize, 10, 30);
 	 		g2.drawString("y: " + (player.worldY + 48) / tileSize, 10, 55);
 	 	}
 	    
 	 }

 	public void drawToScreen() {
 		
 		Graphics g = getGraphics();
 		g.drawImage(tempScreen, 0, 0, screenWidthVollbild, screenHeightVollbild, null);
 		
 		g.dispose();
 	}
 	public void playMusic(int i) {
	  
 		music.setFile(i);
 		music.play();
 		music.loop();
 	}
 	public void stopMusic(int i) {
	  
 		music.stop();
 	}
 	public void playSE(int i) {
	  
 		se.setFile(i);
 		se.play();
 	}

	
	
	

}