

package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	
	private static JFrame window;
	/**
	 * @return the window
	 */
	public static JFrame getWindow() {
		return window;
	}
	/**
	 * @param window the window to set
	 */
	public static void setWindow(JFrame window) {
		Main.window = window;
	}

  public static void main(String[] args) {
    
    setWindow(new JFrame());               //JFrame wird erstellt
    getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Das Fenster kann geschlossen werden
    getWindow().setResizable(false);                  //Das Fenster kann nicht vergrößert werden
    getWindow().setTitle("Lost in Dathe | Version 1.0.2 / 20241210"); 
    new Main().setIcon();
    //GUI Leiste oben zum Fenster schließen
    
    
    GamePanel gamePanel = new GamePanel();            //GamePanel wird in Main Klasse eingefügt
    getWindow().add(gamePanel);
    
    gamePanel.config.loadConfig();
    if(gamePanel.isFullScreenOn() == true) {
    	getWindow().setUndecorated(true);
    }
    
    getWindow().pack();
    
    getWindow().setLocationRelativeTo(null);             //Fenster wird in der Mitte des Bildschirms erstellt
    getWindow().setVisible(true);
    
    gamePanel.setupGame();
    gamePanel.startGameThread();
    
  }
  
  public void setIcon() {
	  
	  ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("pictures/piktogramm.png"));
	  window.setIconImage(icon.getImage());
  }


}
