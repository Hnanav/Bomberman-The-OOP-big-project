package bomberman.bomberman;

import javax.swing.*;
import java.io.IOException;

import static bomberman.bomberman.GUI.LaunchMenu.*;
import static bomberman.bomberman.Main.*;

public class GameWindow {

    public GameWindow() throws IOException {
        window = new JFrame();
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Bomberman");

        sound_music.playMusic();

        Game game = new Game();
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(814,835);
        window.setLayout(null);
        window.setVisible(true);

        game.startGameThread();
        if(game.gameThread == null) {
            window.dispose();
        }
    }

}