package bomberman.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class Sound {
    Clip clip;
    public static String music;
    String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";

    public static String Explosion;
    public static String PlaceBomb;
    public static String Powerup;
    AudioInputStream sound;

    Sound() {
        music = path + "/Sounds/BGM.mp3";
        Explosion = "/Sounds/Explosion.ogg";
        PlaceBomb = "/Sounds/PlaceBomb.ogg";
        Powerup = "/Sounds/Powerup.ogg";
    }
    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void playMusic() {
        clip.start();
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-12.0f);
        clip.loop(100);
    }

    public void playEffect() {
        clip.start();
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(+2.0f);
    }


    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}
