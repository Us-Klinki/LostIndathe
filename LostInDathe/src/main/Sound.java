package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[80];
	FloatControl fc;
	int volumeScale = 6;
	float volume;
	
	public Sound() {
		
		soundURL[0] = pfad("door.wav");
		soundURL[1] = pfad("key.wav");
		soundURL[2] = pfad("music.wav");
		soundURL[3] = pfad("mainmenu.wav");
		soundURL[4] = pfad("switch.wav");
		soundURL[5] = pfad("teleport.wav");
		soundURL[6] = pfad("subArea.wav");
		soundURL[7] = pfad("soundeffect/Menü_Spiel_Starten.wav");
		soundURL[8] = pfad("soundeffect/Menü_Button_Wechseln.wav");
		soundURL[9] = pfad("soundeffect/Menü_Button_Auswählen.wav");
		soundURL[10] = pfad("soundeffect/Schlüssel_Einsammeln.wav");
		soundURL[11] = pfad("voiceover/priebe/Priebe_Dialog1.wav");
		soundURL[12] = pfad("voiceover/priebe/Priebe_Dialog2.wav");
		soundURL[13] = pfad("voiceover/priebe/Priebe_Dialog3.wav");
		soundURL[14] = pfad("voiceover/priebe/Priebe_Dialog4.wav");
		soundURL[15] = pfad("voiceover/köppel/Köppel_Dialog1.wav");
		soundURL[16] = pfad("voiceover/köppel/Köppel_Dialog2.wav");
		soundURL[17] = pfad("voiceover/köppel/Köppel_Dialog3.wav");
		soundURL[18] = pfad("voiceover/köppel/Köppel_Dialog4.wav");
		soundURL[19] = pfad("voiceover/köppel/Köppel_Dialog5.wav");
		soundURL[20] = pfad("voiceover/köppel/Köppel_Dialog6.wav");
		soundURL[21] = pfad("voiceover/köppel/Köppel_Dialog7.wav");
		soundURL[22] = pfad("voiceover/köppel/Köppel_Dialog8.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
		}catch(Exception e) {
		}
		
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		
		clip.stop();
	}
	
	public void pause() {
        if (clip.isRunning()) {
            clip.stop(); // Stoppe die Wiedergabe
        }
    }
	
	public void resume() {
        if (!clip.isRunning()) {
            clip.start(); // Starte die Wiedergabe
        }
    }
	
	public void setPosition(long position) {
        clip.setFramePosition((int) position);
    }
	
	public long getPosition() {
        return clip != null ? clip.getMicrosecondPosition() : 0;
    }

	public boolean isPlaying() {
	    return clip != null && clip.isRunning();
	}
	public void checkVolume() {
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -30f; break;
		case 2: volume = -21f; break;
		case 3: volume = -16f; break;
		case 4: volume = -11f; break;
		case 5: volume = -7f; break;
		case 6: volume = -4f; break;
		case 7: volume = -1f; break;
		case 8: volume = 1f; break;
		case 9: volume = 3f; break;
		case 10: volume = 5f; break;
		case 11: volume = 6f; break;
		}
		fc.setValue(volume);
		
	}
	
	private URL pfad(String dateiname) {
		return getClass().getResource("/sound/" + dateiname);
	}
}
