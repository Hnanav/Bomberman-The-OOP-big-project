package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;

public class SettingWindow {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Hello setting");

    SettingWindow () {
        label.setBounds(0,0,100,50);
        label.setFont(new Font(null, Font.BOLD,25 ));

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}