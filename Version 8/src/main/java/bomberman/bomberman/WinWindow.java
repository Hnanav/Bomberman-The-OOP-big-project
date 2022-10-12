package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;

import static bomberman.bomberman.Main.*;

public class WinWindow implements ActionListener {
    JLabel lvImage;

    MenuButt BackToMenu = new MenuButt("Back To Menu");
    MenuButt ExitButt = new MenuButt("Exit");

    WinWindow() {
        window = new JFrame();
        String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";
        ImageIcon img = new ImageIcon(path + "/Utils/Sprites/Menu/youWin.jpg");
        lvImage = new JLabel("",img,JLabel.CENTER);
        lvImage.setBounds(0,0,800,800);

        BackToMenu.setBounds(300, 350, 200, 80);
        BackToMenu.setFocusable(false);
        BackToMenu.setHorizontalTextPosition(JButton.CENTER);
        BackToMenu.setVerticalTextPosition(JButton.BOTTOM);
        BackToMenu.setBorder(BorderFactory.createEtchedBorder());
        BackToMenu.setRadius(90);
        BackToMenu.addActionListener(this);
        BackToMenu.setBorderPainted(false);
        BackToMenu.setFont(new Font("debussy", Font.PLAIN, 25));
        BackToMenu.setColor(new Color(103, 206, 77));
        BackToMenu.setBorderColor(new Color(6, 87, 25));
        BackToMenu.setColorClick(new Color(189, 242,140));
        BackToMenu.setColorOver(new Color(189, 242,140));
        BackToMenu.setText("Back To Menu");

        ExitButt.setBounds(300, 450, 200, 80);
        ExitButt.setFocusable(false);
        ExitButt.setHorizontalTextPosition(JButton.CENTER);
        ExitButt.setVerticalTextPosition(JButton.BOTTOM);
        ExitButt.setBorder(BorderFactory.createEtchedBorder());
        ExitButt.setRadius(90);
        ExitButt.addActionListener(this);
        ExitButt.setBorderPainted(false);
        ExitButt.setFont(new Font("debussy", Font.PLAIN, 25));
        ExitButt.setColor(new Color(103, 206, 77));
        ExitButt.setBorderColor(new Color(6, 87, 25));
        ExitButt.setColorClick(new Color(189, 242,140));
        ExitButt.setColorOver(new Color(189, 242,140));
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
