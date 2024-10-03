package main;

import entity.Player;
import object.SuperObject;
import main.AssetPlacer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

  //SCREEN SETTINGS
  final int originalTileSize = 16;
  final int scale = 3;
  
  final int tileSize = originalTileSize * scale; // 48*48 tile size
  final int maxScreenCol = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenCol;
  final int screenHeight = tileSize * maxScreenRow;
  
  // WORLD SETTINGS
  final int maxWorldCol = 50;
  final int maxWorldRow = 50;
  
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
  
  // FPS
  int FPS = 60;
  
  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();
  Sound sound = new Sound();
  public CollisionChecker cChecker = new CollisionChecker(this);
  private AssetPlacer aPlacer = new AssetPlacer(this);
  Thread gameThread;        //Thread ist nötig damit das Spiel durchgehend läuft
  //ENTITY AND OBJECTS
  private Player player = new Player(this, keyH);
  private SuperObject obj[] = new SuperObject[30];
  

  
  public GamePanel() {      //GamePanel Compiler
    
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }
  
  public void setupGame() {
	  aPlacer.setObject();
	  
	  playMusic(2);
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
    //long timer = 0;
    //int drawCount = 0;
    
    while(gameThread != null) {
      
      currentTime = System.nanoTime();
      
      delta += (currentTime - lastTime)/drawIntervall;
      //timer += (currentTime-lastTime);
      lastTime = currentTime;
      
      if(delta >= 1) {
        update();
        repaint();
        delta--;
        //drawCount++;
      }
      
       /*if(timer >= 1000000000) {
        System.out.println("FPS:" + drawCount);
        drawCount = 0;
        timer = 0;
      }*/
      // hallo text (kann weg)
    }
    
  }
  public void update() {              //Darf der Charakter schräg laufen?
    
    getPlayer().update();
    
  }
  
  public void paintComponent(Graphics g) {
    
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D)g;
    
    // Hier werden die Tiles erzeugt
    tileM.draw(g2);
    
    // Hier werden die Objekte platziert
    for(int i = 0; i < obj.length; i++) {
    	if(obj[i] != null) {	// sicherstellen, dass Arrayindex immer gefüllt ist
    		obj[i].draw(g2, this);
    	}
    }
    
    // Hier wird der Spieler gespawnt
    getPlayer().draw(g2);
    
    g2.dispose();
  }
  
  public void playMusic(int i) {
	  
	  sound.setFile(i);
	  sound.play();
	  sound.loop();
  }
  public void stopMusic() {
	  
	  sound.stop();
  }
  public void playSE(int i) {
	  
	  sound.setFile(i);
	  sound.play();
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
  // end methods
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
}





