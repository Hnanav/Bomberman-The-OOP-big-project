package bomberman.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;
import java.net.URL;



public class Sound {
    Clip clip;
    public static String music = "D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\Version 6\\src\\main\\resources\\Sounds\\BGM.wav";
    public static String Explosion = "D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\assets\\Sound\\Explosion.ogg";
    public static String PlaceBomb = "D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\assets\\Sound\\PlaceBomb.ogg";
    public static String Powerup = "D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\assets\\Sound\\Powerup.ogg";
    AudioInputStream sound;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {

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
