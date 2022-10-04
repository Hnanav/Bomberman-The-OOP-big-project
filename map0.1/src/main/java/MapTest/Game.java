package MapTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static MapTest.Rand1Map.randmap;

public class Game extends JPanel implements Runnable {

    public static final int originalTileSize = 20;
    public static final int scale = 2;
    public static final int tileSize = originalTileSize * scale;
    public static final int screenCol = 20;
    public static final int screenRow = 20;
    public static final int screenWidth = screenCol * tileSize;
    public static final int screenHeight = screenRow * tileSize;
    public static final int FPS = 60;
    public static final int bomb_time = 150;
    public static final int explosion_time = 20;

    Thread gameThread;
    public static List<BackgroundEntity> BackgroundEntities = new ArrayList<>();
    public static List<StaticEntity> StaticEntities = new ArrayList<>();


    public Game() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public static EntityImages entityImages;

    static {
        try {
            entityImages = new EntityImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        double drawInterVal = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        try {
            creatmap();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterVal;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {

                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        try {
            drawmap(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        g2.dispose();
    }
    void creatmap() throws IOException {
        randmap();
    }

    void drawmap(Graphics2D g2) throws IOException {

        for (BackgroundEntity i : BackgroundEntities) {
            i.render(g2);
        }
        for (StaticEntity i : StaticEntities) {
            i.render(g2);
        }

    }

}
