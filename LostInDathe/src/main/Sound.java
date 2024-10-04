package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/door.wav");
		soundURL[1] = getClass().getResource("/sound/key.wav");
		soundURL[2] = getClass().getResource("/sound/music.wav");
		soundURL[3] = getClass().getResource("/sound/mainmenu.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
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
}
