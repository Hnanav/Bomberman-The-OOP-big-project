package bomberman.bomberman;

import javax.swing.*;
import java.io.IOException;

import static bomberman.bomberman.Sound.music;
import static bomberman.bomberman.Main.*;

public class GameWindow {

  //  public static JFrame window_game;

    GameWindow() throws IOException {
        window = new JFrame();
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Bomberman");


        Sound sound = new Sound();
        sound.setFile(music);
        sound.playEffect();

        Game game = new Game();
        window.add(game);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,800);
        window.setLayout(null);
        window.setVisible(true);

        game.startGameThread();
        if(game.gameThread == null) {
            if (sound == null) System.out.println(1);
            window.dispose();
        }
    }

}