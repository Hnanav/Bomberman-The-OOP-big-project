package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class LevelWindow {
    JFrame levelWindow = new JFrame();
    MenuButt lv1 = new MenuButt("Lv1");
    public static int level = 1;
    LevelWindow () {
        levelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelWindow.setSize(800,800);
        levelWindow.setLayout(null);
        levelWindow.setVisible(true);

        lv1.setBounds(100, 300, 180, 50);
        lv1.setFocusable(false);
        lv1.setHorizontalTextPosition(JButton.CENTER);
        lv1.setVerticalTextPosition(JButton.BOTTOM);
        lv1.setBorder(BorderFactory.createEtchedBorder());
        lv1.setRadius(60);
        lv1.setBorderPainted(false);
        lv1.setText("Level1");

        levelWindow.add(lv1);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == lv1) {
            levelWindow.dispose();
            level =1;
            try {
                GameWindow Gamewin = new GameWindow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
