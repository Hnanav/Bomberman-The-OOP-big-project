package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow {

    JFrame window = new JFrame();
    GameWindow() throws IOException {
        window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Bomberman");

        Game game = new Game();
        window.add(game);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.startGameThread();
    }

}