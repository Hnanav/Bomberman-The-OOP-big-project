package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import bomberman.bomberman.Sound.*;

import static bomberman.bomberman.RandMap.Create;
import static bomberman.bomberman.Sound.Powerup;
import static bomberman.bomberman.Sound.music;

public class GameWindow {

    public static JFrame window_game;


    GameWindow() throws IOException {
        window_game = new JFrame();
        window_game.setDefaultCloseOperation(window_game.EXIT_ON_CLOSE);
        window_game.setResizable(false);
        window_game.setTitle("2D Bomberman");

        Create();

        Sound sound = new Sound();
        sound.setFile(music);
        sound.playMusic();
        Game game = new Game();
        window_game.add(game);

        window_game.pack();
        window_game.setLocationRelativeTo(null);
        window_game.setVisible(true);

        game.startGameThread();
        if(game.gameThread == null) window_game.dispose();
    }

}