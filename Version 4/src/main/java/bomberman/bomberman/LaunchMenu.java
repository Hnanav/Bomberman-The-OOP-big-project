package bomberman.bomberman;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LaunchMenu implements ActionListener{
    Image backgroundImage = Toolkit.getDefaultToolkit().getImage("mong.jpg");
    ImageIcon playIcon = new ImageIcon("play.png");
    JFrame frame = new JFrame();

    JLabel playLabel = new JLabel();



    JButton playButton = new JButton("Play");
    JButton tutorialButton = new JButton("Tutorial");
    JButton settingButton = new JButton("Settings");
    JButton exitButton = new JButton("Exit");

    LaunchMenu() {
        playButton.setBounds(300, 300, 200, 45);
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.BOTTOM);
        playButton.setForeground(Color.cyan);
        playButton.setBackground(Color.LIGHT_GRAY);
        playButton.setBorder(BorderFactory.createEtchedBorder());

        tutorialButton.setBounds(300,360, 200, 45);
        tutorialButton.setFocusable(false);
        tutorialButton.addActionListener(this);
        tutorialButton.setHorizontalTextPosition(JButton.CENTER);
        tutorialButton.setVerticalTextPosition(JButton.BOTTOM);
        tutorialButton.setForeground(Color.BLACK);
        tutorialButton.setBackground(Color.LIGHT_GRAY);
        tutorialButton.setBorder(BorderFactory.createEtchedBorder());

        settingButton.setBounds(300, 420, 200, 45);
        settingButton.setFocusable(false);
        settingButton.addActionListener(this);
        settingButton.setHorizontalTextPosition(JButton.CENTER);
        settingButton.setVerticalTextPosition(JButton.BOTTOM);
        settingButton.setForeground(Color.black);
        settingButton.setBackground(Color.GREEN);
        settingButton.setBorder(BorderFactory.createEtchedBorder());

        exitButton.setBounds(300, 480, 200, 45);




        frame.add(playButton);
        frame.add(tutorialButton);
        frame.add(settingButton);
        frame.add(exitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(null);
        frame.setVisible(true);

        // elsewhere
       // BufferedImage myImage = ImageIO.read("menu_background.jpg");
     //   frame.setContentPane(new ImagePanel(myImage));

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            frame.dispose();
            try {
                GameWindow Gamewin = new GameWindow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        if(e.getSource() == settingButton) {
            frame.dispose();
            SettingWindow Setwin = new SettingWindow();
        }
    }
}
