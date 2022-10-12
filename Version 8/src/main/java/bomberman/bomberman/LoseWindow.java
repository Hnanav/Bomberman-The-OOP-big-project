package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import static bomberman.bomberman.Main.*;

public class LoseWindow implements ActionListener {
  //  JFrame LoseWindow = new JFrame();
    JLabel lvImage;

    MenuButt BackToMenu = new MenuButt("Back To Menu");
    MenuButt ExitButt = new MenuButt("Exit");
 
    LoseWindow() {
        window = new JFrame();
        String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";
        ImageIcon img = new ImageIcon(path + "/Utils/Sprites/Menu/YouLose.png");
        lvImage = new JLabel("",img,JLabel.CENTER);
        lvImage.setBounds(0,0,800,800);

        BackToMenu.setBounds(200, 500, 200, 80);
        BackToMenu.setFocusable(false);
        BackToMenu.setHorizontalTextPosition(JButton.CENTER);
        BackToMenu.setVerticalTextPosition(JButton.BOTTOM);
        BackToMenu.setBorder(BorderFactory.createEtchedBorder());
        BackToMenu.setRadius(90);
        BackToMenu.addActionListener(this);
        BackToMenu.setBorderPainted(false);
        BackToMenu.setFont(new Font("debussy", Font.PLAIN, 20));
        BackToMenu.setText("Back To Menu");

        window.add(BackToMenu);
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
    }

}
