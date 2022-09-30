package bomberman.bomberman;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static JFrame window = new JFrame();
    public static void main(String[] args) throws IOException {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
