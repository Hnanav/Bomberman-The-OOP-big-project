package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow {

    JFrame gameWindow = new JFrame();
    Sound sound = new Sound();
    GameWindow() throws IOException {
        gameWindow.setDefaultCloseOperation(gameWindow.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("2D Bomberman");

        sound.setFile("D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\Version 6\\src\\main\\resources\\Sounds\\BGM.wav");
        sound.playMusic();

        Game game = new Game();
        gameWindow.add(game);

        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);





        game.startGameThread();
    }

}