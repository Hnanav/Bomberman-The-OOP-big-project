package bomberman.bomberman;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LaunchMenu implements ActionListener{

    JFrame frame = new JFrame();

    JLabel background;


    MenuButt playButton = new MenuButt("Play");
    MenuButt tutorialButton = new MenuButt("Tutorial");
    MenuButt settingButton = new MenuButt("Settings");
    MenuButt exitButton = new MenuButt("Exit");

    public static EntityImages entityImages;

    LaunchMenu() {

        ImageIcon img = new ImageIcon("D:\\Projects\\Bomberman\\Bomberman-The-OOP-big-project\\Version 5.1\\src\\main\\java\\bomberman\\bomberman\\menuImage.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,800,800);

        try {
            if (entityImages == null) {
                System.out.println(2);
                entityImages = new EntityImages();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        playButton.setBounds(300, 300, 180, 65);
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.BOTTOM);
        playButton.setBorder(BorderFactory.createEtchedBorder());
        playButton.setRadius(60);
        playButton.setBorderPainted(false);
        playButton.setText("Play");
        playButton.setFont(new Font("debussy", Font.BOLD, 30));


        settingButton.setBounds(300, 390, 180, 65);
        settingButton.setFocusable(false);
        settingButton.addActionListener(this);
        settingButton.setHorizontalTextPosition(JButton.CENTER);
        settingButton.setVerticalTextPosition(JButton.BOTTOM);
        settingButton.setBorder(BorderFactory.createEtchedBorder());
        settingButton.setRadius(60);
        settingButton.setBorderPainted(false);
        settingButton.setText("Setting");
        settingButton.setFont(new Font("debussy", Font.BOLD, 30));

        exitButton.setBounds(300, 480, 180, 65);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        exitButton.setHorizontalTextPosition(JButton.CENTER);
        exitButton.setVerticalTextPosition(JButton.BOTTOM);
        exitButton.setBorder(BorderFactory.createEtchedBorder());
        exitButton.setRadius(60);
        exitButton.setBorderPainted(false);
        exitButton.setText("Exit");
        exitButton.setFont(new Font("debussy", Font.BOLD, 30));


        frame.add(background);
        frame.add(playButton);
        frame.add(tutorialButton);
        frame.add(settingButton);
        frame.add(exitButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.add(background);
        frame.setLayout(null);
        frame.setVisible(true);


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
            LevelWindow lv = new LevelWindow();
        }
        if(e.getSource() == exitButton) {
            frame.dispose();
        }
    }
}
