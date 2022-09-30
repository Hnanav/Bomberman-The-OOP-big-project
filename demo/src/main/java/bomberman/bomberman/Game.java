package bomberman.bomberman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends JPanel implements Runnable {

    public static final int originalTileSize = 20;
    public static final int scale = 2;
    public static final int tileSize = originalTileSize * scale;
    public static final int screenCol = 20;
    public static final int screenRow = 20;
    public static final int screenWidth = screenCol * tileSize;
    public static final int screenHeight = screenRow * tileSize;
    public static final int FPS = 60;
    KeyHandler input = new KeyHandler();
    Thread gameThread;

    char[][] map = new char[20][20];

    //Sprites Image
    public static EntityImages entityImages;

    static {
        try {
            entityImages = new EntityImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Bomber bomber = new Bomber(0, 0, 5,this, input);
    static List<StaticEntity> StaticEntities = new ArrayList<>();
    static List<BackgroundEntity> BackgroundEntities = new ArrayList<>();
    static List<Bomb> Bombs = new ArrayList<>();
    public Game() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
                update();
                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {
        bomber.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        try {
            drawmap(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bomber.render(g2);

        g2.dispose();
    }

    void creatmap() throws IOException {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int rand = ((int) (Math.random() * 5)) + 1;
                if (rand == 2 && (i != 0 && j != 0)) {
                    map[i][j] = '*';
                    StaticEntity tam = new StaticEntity(j * tileSize, i * tileSize, entityImages.wall, false);
                    StaticEntities.add(tam);
                } else {
                    map[i][j] = ' ';
                    BackgroundEntity tam = new BackgroundEntity(j * tileSize, i * tileSize, entityImages.grass);
                    BackgroundEntities.add(tam);
                }
            }
        }
    }

    void drawmap(Graphics2D g2) throws IOException {

        for (BackgroundEntity i : BackgroundEntities) {
            i.render(g2);
        }

        for (StaticEntity i : StaticEntities) {
            i.render(g2);
        }

        int order = 0;
        while (order < Bombs.size()) {
            Bomb tam = Bombs.get(order);
            if (tam.isExplosion()) {
                Bombs.remove(order);
            }
            else {
                order++;
            }
        }

        for (Bomb i : Bombs) {
            i.render(g2);
        }
    }
}
