package main;

import java.awt.Color;
import java.awt.Dimension;

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
		
		Lol//GameLoop muss erstellt werden
		
	}
}
