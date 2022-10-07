package bomberman.bomberman;

import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.RandMap.*;

public class Game extends JPanel implements Runnable {
/*    public static final int originalTileSize = 20;
    public static final int scale = 2;*/
//    public static final int tileSize = originalTileSize * scale;
    public static final int tileSize = 40;
    public static final int screenCol = 20;
    public static final int screenRow = 20;
    public static final int screenWidth = screenCol * tileSize;
    public static final int screenHeight = screenRow * tileSize;
    public static final int FPS = 60;
    public static final int bomb_time = 150;
    public static final int explosion_time = 20;
    public static final int bomber_tileSize = 30;
    public static int level = 45;
    public static int refresh = 0;
    public static int refresh2 = 0;
    public static int refresh3 = 40;
    public static int refresh4 = 0;
    public static int snakecooldown = 50;
    public static int axolotcooldown = 20;
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
    Bomber bomber = new Bomber(0, 0, 4,this, input);
    public static StaticEntityList staticEntityList = new StaticEntityList();
    public static List<BackgroundEntity> BackgroundEntities = new ArrayList<>();
    public static BombList bombList = new BombList();
    public static boolean[][] bombed = new boolean[20][20];
    public static ItemList itemList = new ItemList();
    public static List<BombExplosion> BombExplosions = new ArrayList<>();
    public static List<Tele> hole = new ArrayList<>();
    public static List<Snake> snaker = new ArrayList<>();
    public static List<Beast> beasts = new ArrayList<>();
    public static List<Axolot> axolots = new ArrayList<>();

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
                refresh --;
                refresh2 --;
                refresh3 --;
                refresh4 --;
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
        int order = 0;
        while (order < snaker.size()) {
            if (snaker.get(order).isDied()) {
                snaker.remove(order);
            }
            else order ++;
        }

        order = 0;
        while (order < beasts.size()) {
            if (beasts.get(order).isDied()) {
                beasts.remove(order);
            }
            else order ++;
        }

        order = 0;
        while (order < axolots.size()) {
            if (axolots.get(order).isDied()) {
                axolots.remove(order);
            }
            else order ++;
        }

        order = 0;
        while (order < hole.size()) {
            if (hole.get(order).isDied()) {
                hole.remove(order);
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

        if (refresh3 <= 0) {
            for (Tele i : hole) {
                i.update();
            }
            refresh3 = 10;
        }
        if (refresh4 <= 0) {
            if (axolotcooldown <= 0) {
                axolotcooldown = 20;
                for (Axolot i : axolots) {
                    i.update(level);
                }
            } else {
                for (Axolot i : axolots) {
                    i.update(level);
                }
            }
            axolotcooldown --;
            refresh4 = 3;
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == ' ') {
                    int rand = ((int) (Math.random() * 100000)) + 1;
                    if (rand == 1) {
                        Item item = new PowerUp(j * tileSize, i * tileSize);
                        itemList.add(item);
                    }
                    else if (rand == 2) {
                        Item item = new BombUp(j * tileSize, i * tileSize);
                        itemList.add(item);
                    }
                    else if (rand == 3) {
                        Item item = new SpeedUp(j * tileSize, i * tileSize);
                        itemList.add(item);
                    }
                    else if (rand == 4) {
                        Item item = new SpeedDown(j * tileSize, i * tileSize);
                        itemList.add(item);
                    }
                }
            }
        }

        bomber.update();

        if (bombList.is_Empty()) bombList.update();

        List<Item> items = itemList.getItems();
        for (BombExplosion i : BombExplosions) {
            Rectangle pos = i.getPos();
            int left = i.getLeft_pole();
            int right = i.getRight_pole();
            int up = i.getUp_pole();
            int down = i.getDown_pole();

            Rectangle rec_horizontal = new Rectangle(left, pos.getY(), right - left + tileSize, tileSize);
            Rectangle rec_vertical = new Rectangle(pos.getX(), up, tileSize, down - up + tileSize);

            for (Item item : items) {
                if (Collision(item.getPos(), rec_horizontal) || Collision(item.getPos(), rec_vertical)) {
                    itemList.remove(item);
                    break;
                }
            }
        }

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
            //System.out.println(text);
            fin = new FileInputStream(text);
            int data = fin.read();
            int i = 0;
            int j = 0;
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
                if (map[i][j] == '#') {
                    StaticEntity tam = new Wall(j * tileSize, i * tileSize);
                    staticEntityList.add(tam, '#');
                }
                else if (map[i][j] == '*') {
                    StaticEntity tam = new Brick(j * tileSize, i * tileSize);
                    staticEntityList.add(tam, '*');
                }
                else if (map[i][j] == 'H') {
                    Tele tam1 = new Tele(j * tileSize, i * tileSize);
                    hole.add(tam1);
                    BackgroundEntity tam = new Floor(j * tileSize, i * tileSize);
                    BackgroundEntities.add(tam);
                } else {
                    //int cnt = ThreadLocalRandom.current().nextInt(1,3);
                    if (map[i][j] == 'S') {
                        Snake tam = new Snake(j * tileSize, i * tileSize, 4);
                        snaker.add(tam);
                    }
                    if (map[i][j] == 'A') {
                        Beast tam = new Beast(j * tileSize, i * tileSize, 8);
                        beasts.add(tam);
                    }
                    if (map[i][j] == 'T') {
                        Axolot tam = new Axolot(j * tileSize, i * tileSize, 4);
                        axolots.add(tam);
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

        if (!hole.isEmpty())
            for (Tele i : hole) {
                i.render(g2);
            }
        if (!axolots.isEmpty())
            for (Axolot i : axolots) {
                i.render(g2);
            }
        if (!snaker.isEmpty())
            for (Snake i : snaker) {
                i.render(g2);
            }
        if (!beasts.isEmpty())
            for (Beast i : beasts) {
                i.render(g2);
            }

        if (bombList.is_Empty()) bombList.render(g2);
        staticEntityList.render(g2);
        for (BombExplosion i : BombExplosions) {
            i.render(g2);
        }

        itemList.render(g2);
    }

}
