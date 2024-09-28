package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	
	Thread gameThread;				//Thread ist nötig damit das Spiel durchgehend läuft
	
	
	public GamePanel() {			//GamePanel Compiler
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
	}

	public void startGameThread() {				//Thread Compiler
	
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		while(gameThread != null) {
			
			
//			System.out.println("DerGameloop funktioniert");
			
			// 1 UPDATE: update information
			update();
			
			// 2 DRAW: draw the screen with the updated Information
			repaint();
		}
	}
	public void update() {
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.WHITE);
		
		g2.fillRect(100, 100, tileSize, tileSize);
		
		g2.dispose();
	}
}

/*  try {
			    int secondsToSleep = 1;					//Delay für Gameloop Test
				Thread.sleep(secondsToSleep * 100);
			} catch (InterruptedException ie) {
			    Thread.currentThread().interrupt();
			} 
*/



