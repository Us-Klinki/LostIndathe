package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tile.TileManager;

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
    
    gamePanel.startGameThread();
    
  }

}
