package erstesProjekt;

import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    public static void playSound(String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.err.println("Fehler beim Abspielen des Sounds: " + e.getMessage());
        }
    }
}

