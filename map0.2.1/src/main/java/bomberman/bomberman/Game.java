package bomberman.bomberman;

import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static bomberman.bomberman.Rand1Map.*;

public class Game extends JPanel implements Runnable {
    public static final int originalTileSize = 20;
    public static final int scale = 2;
    public static final int tileSize = originalTileSize * scale;
    public static final int screenCol = 20;
    public static final int screenRow = 20;
    public static final int screenWidth = screenCol * tileSize;
    public static final int screenHeight = screenRow * tileSize;
    public static final int FPS = 60;

    public static int snakecooldown = 5;
    public static final int bomb_time = 150;
    public static final int explosion_time = 20;

    public static int level = 1;
    public static int refresh = 0;
    KeyHandler input = new KeyHandler();
    Thread gameThread;
    public static EntityImages entityImages;

    static {
        try {
            entityImages = new EntityImages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static char[][] map = new char[21][21];
    Bomber bomber = new Bomber(0, 0, 5,this, input);
    public static StaticEntityList staticEntityList = new StaticEntityList();
    public static List<BackgroundEntity> BackgroundEntities = new ArrayList<>();
    public static BombList bombList = new BombList();

    public static List<Snake> snaker = new ArrayList<>();
    public static boolean[][] bombed = new boolean[20][20];
    public static List<BombExplosion> BombExplosions = new ArrayList<>();

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

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++)
                bombed[i][j] = false;

        while (gameThread != null) {
            if (bomber.isDied() == true) {
                gameThread.stop();
            }
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterVal;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            //System.out.println(delta);
            if (delta >= 1) {
                refresh --;
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
        staticEntityList.update();
        if (refresh <= 0) {
            if (snakecooldown <= 0) {
                snakecooldown = 50;
                System.out.println("ddddmm");
                for (Snake i : snaker) {
                    i.speed = 30;
                    i.update(level);
                    i.speed = 1;
                }
            } else {
                for (Snake i : snaker) {
                    i.update(level);
                }
            }
            snakecooldown --;
            refresh = 3;
        }

        if (bombList.isEmpty() == false) bombList.update();

        int order = 0;
        while (order < BombExplosions.size()) {
            BombExplosion tam = BombExplosions.get(order);
            if (tam.isComplete()) {
                BombExplosions.remove(order);
            }
            else {
                order++;
            }
        }

        for (BombExplosion i : BombExplosions) {
            i.update();
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
        create();
        FileInputStream fin = null;
        String text = "test" + Integer.toString(level) + ".txt";

        try {
            System.out.println(text);
            fin = new FileInputStream(text);
            int data = fin.read(); int i = 0; int j = 0;
            //data = fin.read(); data = fin.read();
            while (data != -1) {
                //System.out.print((char)data);
                if ((char) data == '\n') {
                    i = 0; j ++;
                    data = fin.read();
                    continue;
                }
                map[i][j] = (char) data;
                data = fin.read();
                i ++;
                if (i > 19 && j > 19) break;
            }

        }catch (IOException e) {
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == 'W') {
                    StaticEntity tam = new Wall(j * tileSize, i * tileSize);
                    staticEntityList.add(tam, 'W');
                }
                else
                    if (map[i][j] == 'B') {
                            StaticEntity tam = new Brick(j * tileSize, i * tileSize);
                            staticEntityList.add(tam, 'B');
                        }

                else {
                    if (map[i][j] == 'E') {
                        Snake tam = new Snake(j * tileSize, i * tileSize, 1);
                        snaker.add(tam);
                    }
                    BackgroundEntity tam = new Floor(j * tileSize, i * tileSize);
                    BackgroundEntities.add(tam);
                }
            }
        }
    }

    void drawmap(Graphics2D g2) throws IOException {

        for (BackgroundEntity i : BackgroundEntities) {
            i.render(g2);
        }

        bomber.render(g2);

        staticEntityList.render(g2);

        for (Snake i : snaker) {
            i.render(g2);
        }
        if (bombList.isEmpty() == false) bombList.render(g2);

        for (BombExplosion i : BombExplosions) {
            i.render(g2);
        }
    }

}
