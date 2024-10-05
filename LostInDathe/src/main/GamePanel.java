package main;

import entity.Player;
import object.SuperObject;
import main.AssetPlacer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
	
	// Für Vollbild
	int screenWidthVollbild = screenWidth;
	int screenHeightVollbild = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	
	
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
 	public SuperObject[] getObj() {
 		return obj;
 	}
 	/**
 	 * @param obj the obj to set
 	 */
 	public void setObj(SuperObject obj[]) {
 		this.obj = obj;
 	}
  
 	// FPS
 	int FPS = 60;
  
 	TileManager tileM = new TileManager(this);
 	KeyHandler keyH = new KeyHandler(this);
 	Sound music = new Sound();
 	Sound se = new Sound();
 	public CollisionChecker cChecker = new CollisionChecker(this);
 	private AssetPlacer aPlacer = new AssetPlacer(this);
 	Thread gameThread;        //Thread ist nötig damit das Spiel durchgehend läuft
  
 	//ENTITY AND OBJECTS
 	private Player player = new Player(this, keyH);
 	private SuperObject obj[] = new SuperObject[30];
 	Font debug = new Font("Bahnschrift", Font.BOLD, 24);
 	public UI ui = new UI(this);
  
 	//Game State
 	public int gameState;
 	public final int titleState = 0;
 	public final int playState = 1;
 	public final int pauseState = 2;
 	//private BufferedImage lastFrame;
  
  
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
	  
 		playMusic(3);
 		gameState = titleState;
 		
 		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
 		g2 = (Graphics2D)tempScreen.getGraphics();
 		
 		//Hier kann Full Screen ausgeschalten werden
 		setFullScreen();
 		
	 
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
 				delta--;
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
 			//g2LastFrame.dispose();
 		}
 		
 		if(gameState == pauseState) { 
 		}	  
	 }
  
 	
 	public void drawToTempScreen() {
 		// DEBUG
 	 	long drawStart = 0;
 	 	if(keyH.isDebug() == true) {
 			drawStart = System.nanoTime();
 	 	}
 	 		
 	 	// TITLE SCREEN
 	 	if(gameState == titleState) {
 	 		ui.draw(g2);	
 	 	}
 	 	// RESTLICHES SPIEL
 	 	else {
 	 		//Überpfrüfung vom game state
 	 	 	if(gameState == playState) {
 	 	    	// Hier werden die Tiles erzeugt
 	 	 		tileM.draw(g2);
 	 	 		// Hier werden die Objekte platziert
 	 	 		for(int i = 0; i < obj.length; i++) {
 	 	 			if(obj[i] != null) {	// sicherstellen, dass Arrayindex immer gefüllt ist
 	 	 				obj[i].draw(g2, this, keyH);
 	 	 			}
 	 	 		}
 	 	    
 	 	 		// Hier wird der Spieler gespawnt
 	 	 	getPlayer().draw(g2);
 	 	 	}
 	 	 	else if (gameState == pauseState) {
 	 	 		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
 	 	 		g2.setColor(new Color(0, 0, 0, 128));
 	 	 		g2.fillRect(0, 0, getWidth(), getHeight());
 	 	 		// Den letzten Frame zeichnen
 	 	 		//if (lastFrame != null) {
 	 	 			//g2.drawImage(lastFrame, 0, 0, null); // Zeige den letzten Frame an
 	 	 		//}
 	 	 		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Volle Deckkraft für Text
 	 	 		ui.draw(g2);
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