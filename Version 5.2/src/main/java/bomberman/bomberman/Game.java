package bomberman.bomberman;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
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
    public static final int bomb_time = 150;
    public static final int explosion_time = 20;
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

    public static char[][] map = new char[20][20];
    Bomber bomber = new Bomber(0, 0, 5,this, input);
    public static StaticEntityList staticEntityList = new StaticEntityList();
    public static List<BackgroundEntity> BackgroundEntities = new ArrayList<>();
    public static BombList bombList = new BombList();
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
        double drawInterVal = 1000000000.0 / FPS;
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
            if (bomber.isDied()) {
                gameThread.stop();
            }

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
      //  staticEntityList.update();

        if (bombList.is_Empty()) bombList.update();

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

       /* for (BombExplosion i : BombExplosions) {
            i.update();
        }*/
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
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int rand = ((int) (Math.random() * 5)) + 1;
                if (rand == 2 && (i != 0 && j != 0)) {
                    map[i][j] = '#';
                    StaticEntity tam = new Wall(j * tileSize, i * tileSize);

                    int rand1 = ((int) (Math.random() * 2)) + 1;
                    if (rand1 == 2) {
                        map[i][j] = '*';
                        tam = new Brick(j * tileSize, i * tileSize);
                        staticEntityList.add(tam, '*');
                    }
                    else {
                        staticEntityList.add(tam, '#');
                    }
                }
                else {
                    map[i][j] = ' ';
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

        if (bombList.is_Empty()) bombList.render(g2);

        for (BombExplosion i : BombExplosions) {
            i.render(g2);
        }
    }

}
