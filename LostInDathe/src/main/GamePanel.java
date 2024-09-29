package main;

import entity.Player;
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
  final int worldWidth = tileSize * maxWorldCol;
  final int worldHeight = tileSize * maxWorldRow;
  
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
  
  public int getWorldWidth() {
    return worldWidth;
  }
  
  public int getWorldHeight() {
    return worldHeight;
  }
  // FPS
  int FPS = 60;
  
  TileManager tileM = new TileManager(this);
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;        //Thread ist nötig damit das Spiel durchgehend läuft
  CollisionChecker cChecker = new CollisionChecker
  private Player player = new Player(this, keyH);
  

  
  public GamePanel() {      //GamePanel Compiler
    
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread() {       //Thread Compiler
  
    gameThread = new Thread(this);
    gameThread.start();
  }
  
  @Override
  /* public void run() {
    
    double drawIntervall = 1000000000/FPS; // 0,016666666 Sekunden
    double nextDrawTime = System.nanoTime() + drawIntervall;
    
    while(gameThread != null) {
      
      update();
      
      repaint();
      
      try {
        
          double remainingTime = nextDrawTime - System.nanoTime();
          remainingTime = remainingTime/1000000;
          
          if(remainingTime < 0) {
            remainingTime = 0;
          }
          
        Thread.sleep((long) remainingTime);;
        
        nextDrawTime += drawIntervall;
        
      } catch (InterruptedException e) {
          e.printStackTrace();
      } 
    }
  }
  */
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
      
    }
    
  }
  public void update() {              //Darf der Charakter schräg laufen?
    
    getPlayer().update();
    
  }
  
  public void paintComponent(Graphics g) {
    
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D)g;
    
    tileM.draw(g2);
    
    getPlayer().draw(g2);
    
    g2.dispose();
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
}





