package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class LevelWindow implements ActionListener {
    JFrame levelWindow = new JFrame();
    JLabel lvImage;

   //enuButt lvs = new MenuButt("Lv1")//
    MenuButt[] lvs = new MenuButt[50];
    public static int level = 1;
    LevelWindow () {
        ImageIcon img = new ImageIcon("D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\assets\\Menu\\Levels.jpg");
        lvImage = new JLabel("",img,JLabel.CENTER);
        lvImage.setBounds(0,0,800,800);


        levelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelWindow.setSize(800,800);
        levelWindow.setLayout(null);
        levelWindow.setVisible(true);
        int x_ = 10 , y_= 130, w_ = 50, h_ = 50;
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
            levelWindow.add(lvs[i]);
        }
            levelWindow.add(lvImage);
        }

    public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 50; i++)
            if(e.getSource() == lvs[i]) {
               levelWindow.dispose();
                level = i;
                try {
                    GameWindow Gamewin = new GameWindow();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

}
