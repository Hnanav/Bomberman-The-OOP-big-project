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
import java.util.concurrent.ThreadLocalRandom;

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

    public static int level = 34;
    public static int refresh = 0;
    public static int refresh2 = 0;
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
    public static List<Tele> hole = new ArrayList<>();

    public static List<Snake> snaker = new ArrayList<>();
    public static List<Beast> beasts = new ArrayList<>();
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
                refresh2 --;
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
        for (Tele i : hole) {
            i.update();
        }

        int order = 0;
        while (order < snaker.size()) {
            if (snaker.get(order).isDied()) {
                snaker.remove(order);
                //System.out.println(111);
            }
            else order ++;
        }
        order = 0;
        while (order < beasts.size()) {
            if (beasts.get(order).isDied()) {
                beasts.remove(order);
                //System.out.println(111);
            }
            else order ++;
        }
        if (refresh <= 0) {
            if (snakecooldown <= 0) {
                snakecooldown = 50;
                for (Snake i : snaker) {
                    i.speed = 20;
                    i.update(level);
                    i.speed = 4;
                }
            } else {
                for (Snake i : snaker) {
                    i.update(level);
                }
            }
            snakecooldown --;
            refresh = 3;
        }
        if (refresh2 <= 0) {
                for (Beast i : beasts) {
                    i.update(level);
                }
            refresh2 = 3;
        }

        if (bombList.isEmpty() == false) bombList.update();

        order = 0;
        while (order < BombExplosions.size()) {
            BombExplosion tam = BombExplosions.get(order);
            if (tam.isComplete()) {
                BombExplosions.remove(order);
            }
            else {
                order++;
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
                else if (map[i][j] == 'B') {
                    StaticEntity tam = new Brick(j * tileSize, i * tileSize);
                    staticEntityList.add(tam, 'B');
                }
                else if (map[i][j] == 'H') {
                            Tele tam = new Tele(j * tileSize, i * tileSize);
                            hole.add(tam);
                } else {
                    int cnt = ThreadLocalRandom.current().nextInt(1,3);
                    if (map[i][j] == 'E' && cnt == 1) {
                        Snake tam = new Snake(j * tileSize, i * tileSize, 4);
                        snaker.add(tam);
                    }
                        if (map[i][j] == 'E' && cnt == 2) {
                            Beast tam = new Beast(j * tileSize, i * tileSize, 8);
                            beasts.add(tam);
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
        for (Tele i : hole) {
            i.render(g2);
        }
        staticEntityList.render(g2);

        for (Snake i : snaker) {
            i.render(g2);
        }
        for (Beast i : beasts) {
            i.render(g2);
        }
        if (bombList.isEmpty() == false) bombList.render(g2);

        for (BombExplosion i : BombExplosions) {
            i.render(g2);
        }
    }

}
