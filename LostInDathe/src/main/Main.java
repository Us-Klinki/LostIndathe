package main;

import javax.swing.JFrame;

public class Main {
  
  public static void main(String[] args) {
    
    JFrame window = new JFrame();               //JFrame wird erstellt
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Das Fenster kann geschlossen werden
    window.setResizable(true);                  //Das Fenster kann nicht vergrößert werden
    window.setTitle("Lost in Dathe");             
    
    GamePanel gamePanel = new GamePanel();            //GamePanel wird in Main Klasse eingefügt
    window.add(gamePanel);
    
    window.pack();
    
    window.setLocationRelativeTo(null);             //Fenster wird in der Mitte des Bildschirms erstellt
    window.setVisible(true);
    
    gamePanel.setupGame();
    gamePanel.startGameThread();
    
  }

}
