package bomberman.bomberman.GUI;

import bomberman.bomberman.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;

import static bomberman.bomberman.Main.*;

public class LevelWindow implements ActionListener {
    JLabel lvImage;
    MenuButt[] lvs = new MenuButt[51];
    public static int level = 1;
    LevelWindow () {
        window = new JFrame();
        String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";
        ImageIcon img = new ImageIcon(path + "/Utils/Sprites/Menu/Levels.png");
        lvImage = new JLabel("",img,JLabel.CENTER);
        lvImage.setBounds(0,0,800,800);

        int x_ = 10 , y_= 150, w_ = 50, h_ = 50;
        int vtX;
        int vtY;
        for (int i = 0; i < 50; i++) {
            vtX = (i % 10) * 29 + (i % 10) * w_;
            vtY = (i / 10) * 70;
            lvs[i] = new MenuButt("level" + i);
            lvs[i].setBounds(x_ + vtX, y_ + vtY, w_, h_);
            lvs[i].setFocusable(false);
            lvs[i].setHorizontalTextPosition(JButton.CENTER);
            lvs[i].setVerticalTextPosition(JButton.BOTTOM);
            lvs[i].setBorder(BorderFactory.createEtchedBorder());
            lvs[i].setRadius(90);
            lvs[i].addActionListener(this);
            lvs[i].setBorderPainted(false);
            lvs[i].setFont(new Font("debussy", Font.BOLD, 20));
            lvs[i].setText((i + 1) + "");

            window.add(lvs[i]);
        }

        window.add(lvImage);
        window.pack();
        window.setSize(800,800);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 50; i++)
            if(e.getSource() == lvs[i]) {
                window.dispose();
                level = i + 1;
                try {
                    GameWindow Gamewin = new GameWindow();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

}
