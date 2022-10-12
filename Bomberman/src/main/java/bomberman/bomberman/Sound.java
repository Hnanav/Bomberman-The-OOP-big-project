package bomberman.bomberman;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.util.Objects;

public class Sound {
    public Clip clip;
    static String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";
    public static String Music, Explosion, PlaceBomb, Powerup, Defeat, Victory;
    static AudioInputStream sound;
    private String melody;

    public Sound() {
    }

    static  {
        Music = path + "/Sounds/BGM.wav";
        Victory = path + "/Sounds/Victory.wav";
        Explosion = path + "/Sounds/Explosion.wav";
        PlaceBomb = path + "/Sounds/PlaceBomb.wav";
        Powerup = path + "/Sounds/Powerup.wav";
        Defeat = path + "/Sounds/Defeat.wav";
    }
    public void setFile(String soundFileName) {
        try {
            melody = soundFileName;
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void playMusic() {
        if (clip == null) System.out.println(1);
        clip.start();

        if (Objects.equals(melody, Music)) {
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            clip.loop(100);
        }
        else {
            /*FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(2.0f);*/
            clip.loop(1);
        }
    }


    public void stop() throws IOException {
        clip.close();
    }
}
