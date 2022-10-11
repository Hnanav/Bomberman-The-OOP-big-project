package bomberman.bomberman;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
public class Sound {
    Clip clip;
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
        clip.loop(10);
    }

    public void playEffect() {
        clip.start();
    }


    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
    }
