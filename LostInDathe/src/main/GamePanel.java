package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale; // 48*48 tile size
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;				//Thread ist nötig damit das Spiel durchgehend läuft
	
	
	//Default Spieler Position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {			//GamePanel Compiler
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {				//Thread Compiler
	
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
			
			/* if(timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			} */
			
		}
		
	}
	public void update() {							//Darf der Charakter schräg laufen?
		
		if(keyH.upPressed == true) {
			playerY = playerY - playerSpeed;
		}
		if(keyH.leftPressed == true) {
			playerX = playerX - playerSpeed;
		}
		if(keyH.downPressed == true) {
			playerY = playerY + playerSpeed;
		}
		if(keyH.rightPressed == true) {
			playerX = playerX + playerSpeed;
		}
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		ImageIcon icon = new ImageIcon("images/Charakter.png");
		
	    
		
		g2.drawImage(icon.getImage(), 100, 100, this);
		//g2.setColor(Color.WHITE);
		
		//g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}
}





