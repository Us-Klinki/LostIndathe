package main;

import java.net.URL;
import java.util.ArrayList;

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
	private ArrayList<Clip> activeClips;
	
	public Sound() {
		
		soundURL[0] = pfad("soundtrack/Credit_Scene_Song.wav"); // Credit Scene
		soundURL[1] = pfad("soundeffect/Toilettenspuelung.wav");
		soundURL[2] = pfad("soundtrack/Overworld_Flur_Song.wav"); // ersetzen durch Musik im Gang
		soundURL[3] = pfad("soundtrack/Hauptmenue_Song.wav"); // ersetzen durch Musik im Hauptmenü
		soundURL[4] = pfad("switch.wav"); // nicht mehr genutzt
		soundURL[5] = pfad("soundtrack/Chemie_Song.wav");
		soundURL[6] = pfad("subArea.wav"); // nicht mehr genutzt
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
		soundURL[23] = pfad("voiceover/peil/Peil_Dialog1.wav");
		soundURL[24] = pfad("voiceover/peil/Peil_Dialog2.wav");
		soundURL[25] = pfad("voiceover/peil/Peil_Dialog3.wav");
		soundURL[26] = pfad("voiceover/peil/Peil_Dialog4.wav");
		soundURL[27] = pfad("voiceover/peil/Peil_Dialog5.wav");
		soundURL[28] = pfad("voiceover/peil/Peil_Dialog6.wav");
		soundURL[29] = pfad("voiceover/peil/Peil_Dialog7.wav");
		soundURL[30] = pfad("voiceover/peil/Peil_Dialog8.wav");
		soundURL[31] = pfad("voiceover/peil/Peil_Dialog9.wav");
		soundURL[32] = pfad("voiceover/peil/Peil_Dialog10.wav");
		soundURL[33] = pfad("voiceover/peil/Peil_Dialog11.wav");
		soundURL[34] = pfad("voiceover/klinki/Klinki_Dialog1.wav");
		soundURL[35] = pfad("voiceover/klinki/Klinki_Dialog2.wav");
		soundURL[36] = pfad("voiceover/klinki/Klinki_Dialog3.wav");
		soundURL[37] = pfad("voiceover/klinki/Klinki_Dialog4.wav");
		soundURL[38] = pfad("voiceover/klinki/Klinki_Dialog5.wav");
		soundURL[39] = pfad("voiceover/klinki/Klinki_Dialog6.wav");
		soundURL[40] = pfad("voiceover/klinki/Klinki_Dialog7.wav");
		soundURL[41] = pfad("voiceover/klinki/Klinki_Dialog8.wav");
		soundURL[42] = pfad("voiceover/krecic/Krecic_Dialog1.wav");
		soundURL[43] = pfad("voiceover/krecic/Krecic_Dialog2.wav");
		soundURL[44] = pfad("voiceover/krecic/Krecic_Dialog3.wav");
		soundURL[45] = pfad("voiceover/krecic/Krecic_Dialog4.wav");
		soundURL[46] = pfad("voiceover/krecic/Krecic_Dialog5.wav");
		soundURL[47] = pfad("voiceover/krecic/Krecic_Dialog6.wav");
		soundURL[48] = pfad("soundtrack/Informatik_Song.wav");
		soundURL[49] = pfad("soundtrack/Geschichte_Song.wav");
		soundURL[50] = pfad("soundeffect/Tuer_Oeffnen.wav");
		soundURL[51] = pfad("soundeffect/Waschbecken.wav");
		soundURL[52] = pfad("soundtrack/Bad_Song.wav");
		soundURL[53] = pfad("soundeffect/Tuer_Ist_Verschlossen.wav");
		soundURL[54] = pfad("soundeffect/Chemikalien_Blubb_Glubb.wav");
		soundURL[55] = pfad("soundeffect/Laufen_Klack_Klack_Flur.wav");
		soundURL[56] = pfad("soundeffect/Laufen_Klack_Klack_Raum.wav");
		soundURL[57] = pfad("soundtrack/Biologie_Song.wav");
		// vorerst die Indexe oben überschreiben (nicht mehr genutzte Sounds/Soundtracks)
		
		activeClips = new ArrayList<>();
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			activeClips.add(clip);
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
        for (Clip clip : activeClips) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
        activeClips.clear(); // Clear the list after stopping
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

	public int getSoundURLLength() {
		return soundURL.length;
	}
}
