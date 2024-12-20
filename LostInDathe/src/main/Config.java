package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	GamePanel gp;
	UI ui;
	
	Config(GamePanel gp, UI ui) {
		this.gp = gp;
		this.ui = ui;
	}
	
	public void saveConfig() {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
		
			// Vollbild
			if(gp.isFullScreenOn() == true) {
				bw.write("On");
			}
			if(gp.isFullScreenOn() == false) {
				bw.write("Off");
			}
			bw.newLine();
			
			// Musik Lautstärke
			bw.write(String.valueOf(gp.music.volumeScale));
			bw.newLine();
			
			// Sounds Lautstärke
			bw.write(String.valueOf(gp.se.volumeScale));
			bw.newLine();
			
			// Hud an?
			if(ui.hudOn) {
				bw.write("True");
			}
			if(!ui.hudOn) {
				bw.write("False");
			}
			
			bw.newLine();
			bw.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
		
			String configRead = br.readLine();
			
			// Vollbild
			if(configRead.equals("On")) {
				gp.setFullScreenOn(true);
			}
			
			if(configRead.equals("Off")) {
				gp.setFullScreenOn(false);
			}
			
			// Musik Lautstärke
			configRead = br.readLine();
			gp.music.volumeScale = Integer.parseInt(configRead);
			
			// Sounds Lautstärke
			configRead = br.readLine();
			gp.se.volumeScale = Integer.parseInt(configRead);

			configRead = br.readLine();
			// Hud
			if(configRead.equals("True")) {
				ui.hudOn = true;
			}
			
			if(configRead.equals("False")) {
				ui.hudOn = false;;
			}
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
