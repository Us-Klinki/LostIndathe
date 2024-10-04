package main;

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
    getWindow().setResizable(true);                  //Das Fenster kann nicht vergrößert werden
    getWindow().setTitle("Lost in Dathe"); 
    getWindow().setUndecorated(true);
    
    GamePanel gamePanel = new GamePanel();            //GamePanel wird in Main Klasse eingefügt
    getWindow().add(gamePanel);
    
    getWindow().pack();
    
    getWindow().setLocationRelativeTo(null);             //Fenster wird in der Mitte des Bildschirms erstellt
    getWindow().setVisible(true);
    
    gamePanel.setupGame();
    gamePanel.startGameThread();
    
  }


}
