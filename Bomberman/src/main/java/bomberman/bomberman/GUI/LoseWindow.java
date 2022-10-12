package bomberman.bomberman.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import static bomberman.bomberman.Main.*;
import static bomberman.bomberman.GUI.LaunchMenu.*;

public class LoseWindow implements ActionListener {
  //  JFrame LoseWindow = new JFrame();
    JLabel lvImage;

    MenuButt BackToMenu = new MenuButt("Back To Menu");
    MenuButt ExitButt = new MenuButt("Exit");
 
    public LoseWindow() {
        sound_defeat.playMusic();

        window = new JFrame();
        String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";
        ImageIcon img = new ImageIcon(path + "/Utils/Sprites/Menu/YouLose.png");
        lvImage = new JLabel("",img,JLabel.CENTER);
        lvImage.setBounds(0,0,800,800);

        BackToMenu.setBounds(300, 400, 200, 80);
        BackToMenu.setFocusable(false);
        BackToMenu.setHorizontalTextPosition(JButton.CENTER);
        BackToMenu.setVerticalTextPosition(JButton.BOTTOM);
        BackToMenu.setBorder(BorderFactory.createEtchedBorder());
        BackToMenu.setRadius(90);
        BackToMenu.addActionListener(this);
        BackToMenu.setBorderPainted(false);
        BackToMenu.setFont(new Font("debussy", Font.PLAIN, 25));
        BackToMenu.setColor(new Color(223,103,103));
        BackToMenu.setBorderColor(new Color(87,6,6));
        BackToMenu.setColorClick(new Color(242,140,140));
        BackToMenu.setColorOver(new Color(242,140,140));
        BackToMenu.setText("Back To Menu");

        ExitButt.setBounds(300, 500, 200, 80);
        ExitButt.setFocusable(false);
        ExitButt.setHorizontalTextPosition(JButton.CENTER);
        ExitButt.setVerticalTextPosition(JButton.BOTTOM);
        ExitButt.setBorder(BorderFactory.createEtchedBorder());
        ExitButt.setRadius(90);
        ExitButt.addActionListener(this);
        ExitButt.setBorderPainted(false);
        ExitButt.setFont(new Font("debussy", Font.PLAIN, 25));
        ExitButt.setColor(new Color(223,103,103));
        ExitButt.setBorderColor(new Color(87,6,6));
        ExitButt.setColorClick(new Color(242,140,140));
        ExitButt.setColorOver(new Color(242,140,140));
        ExitButt.setText("Exit");

        window.add(BackToMenu);
        window.add(ExitButt);
        window.add(lvImage);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,800);
        window.setLayout(null);
        window.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BackToMenu) {
            window.dispose();
            LaunchMenu launchMenu = new LaunchMenu();
        }
        if(e.getSource() == ExitButt) {
            window.dispose();
        }
    }

}
