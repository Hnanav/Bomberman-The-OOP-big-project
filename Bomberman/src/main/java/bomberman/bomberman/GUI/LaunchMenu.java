package bomberman.bomberman.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.FileSystems;

import static bomberman.bomberman.Algorithm.RandMap.Create;
import static bomberman.bomberman.Main.*;
import bomberman.bomberman.Entities.EntityImages;
import bomberman.bomberman.GameWindow;
import bomberman.bomberman.Sound;

import static bomberman.bomberman.Sound.*;

public class LaunchMenu implements ActionListener{

    JLabel background;
    MenuButt playButton = new MenuButt("Play");
    MenuButt tutorialButton = new MenuButt("Tutorial");
    MenuButt settingButton = new MenuButt("Settings");
    MenuButt exitButton = new MenuButt("Exit");

    public static Sound sound_music = new Sound();
    public static Sound sound_place_bomb = new Sound();
    public static Sound sound_defeat = new Sound();
    public static Sound sound_explosion = new Sound();
    public static Sound sound_power_up = new Sound();
    public static Sound sound_victory = new Sound();

    public static EntityImages entityImages;

    static {
        try {
                entityImages = new EntityImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void SetSound() {
        sound_music.setFile(Music);
        sound_victory.setFile(Victory);
        sound_place_bomb.setFile(PlaceBomb);
        sound_defeat.setFile(Defeat);
        sound_explosion.setFile(Explosion);
        sound_power_up.setFile(Powerup);
    }

    public LaunchMenu() {
        SetSound();
        window = new JFrame();
        Create();

        String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "/src/main/resources";
        ImageIcon img = new ImageIcon(path + "/Utils/Sprites/Menu/menuImage.png");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,800,800);

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


        window.add(background);
        window.add(playButton);
        window.add(tutorialButton);
        window.add(settingButton);
        window.add(exitButton);
        window.add(background);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800,800);
        window.setLayout(null);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playButton) {
            window.dispose();
            try {
                GameWindow Gamewin = new GameWindow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == settingButton) {
            window.dispose();
            LevelWindow lv = new LevelWindow();
        }
        if(e.getSource() == exitButton) {
            window.dispose();
        }
    }
}
