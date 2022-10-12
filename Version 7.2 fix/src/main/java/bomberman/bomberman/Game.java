package bomberman.bomberman;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bomberman.bomberman.GameWindow.*;
import static bomberman.bomberman.AIEnemy.*;
import static bomberman.bomberman.CheckCollision.*;
import static bomberman.bomberman.LevelWindow.*;

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

    private int refreshSnake = 0;
    private int refreshAxolot = 0;
    private int refreshBeast = 0;
    private int SnakeCooldown = 0;
    private int AxolotCooldown = 0;
    private int LimitrefreshSnake = 0;
    private int LimitrefreshAxolot = 0;
    private int LimitrefreshBeast = 0;
    private int LimitSnakeCooldown = 0;
    private int LimitAxolotCooldown = 0;
    private int LimitTeleSpeedSnake = 0;
    private int LimitSpeedSnake = 0;
    private int LimitSpeedAxolot = 0;
    private int LimitSpeedBeast = 0;
    private int CountAxolotInMap = 0;
    private int CountBeastInMap = 0;
    private int CountSnakeInMap = 0;
    private int CountEnemyInMap = 0;

    public boolean loser = false;
    KeyHandler input = new KeyHandler();
    Thread gameThread;
    public static Alg alg = new Alg();
    public static char[][] map = new char[20][20];
    Bomber bomber = new Bomber(0, 0, 4,this, input);
    public static List<Pair<Integer, Integer> >  Cannot = new ArrayList<>();
    public static StaticEntityList staticEntityList = new StaticEntityList();
    public static List<BackgroundEntity> BackgroundEntities = new ArrayList<>();
    public static BombList bombList = new BombList();
    public static boolean[][] bombed = new boolean[20][20];
    public static boolean[][] itemed = new boolean[20][20];
    public static ItemList itemList = new ItemList();
    public static List<BombExplosion> BombExplosions = new ArrayList<>();
    public static List<Tele> hole = new ArrayList<>();
    public static List<Snake> snakes = new ArrayList<>();
    public static List<Beast> beasts = new ArrayList<>();
    public static List<Axolot> axolots = new ArrayList<>();

    public static boolean finished;
    Game() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        this.setFocusable(true);
    }

    public void startGameThread() {
        finished = false;
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
            CountEnemyInMap = 0; CountAxolotInMap = 0;
            CountBeastInMap = 0; CountSnakeInMap = 0;
            Creatmap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                bombed[i][j] = false;
                itemed[i][j] = false;
            }

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterVal;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                if (!bomber.isDied()) {
                    refreshSnake--;
                    refreshBeast--;
                    refreshAxolot--;
                    update();
                }
                if(finished) {
                    clear();
                    gameThread = null;
                    window_game.dispose();
                    LoseWindow lose = new LoseWindow();
                }

                repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }
    public void SetUpDifficult(int level) {
        if (level <= 50) {
            refreshSnake = 0; refreshAxolot = 0; refreshBeast = 0;
            LimitrefreshAxolot = 3; LimitrefreshSnake = 3; LimitrefreshBeast = 3;
            SnakeCooldown = 20; AxolotCooldown = 40;
            LimitSnakeCooldown = 20; LimitAxolotCooldown = 40;
            LimitTeleSpeedSnake = 20;
            LimitSpeedSnake = 2; LimitSpeedAxolot = 4; LimitSpeedBeast = 8;
        }
        if (level <= 10) {
            refreshSnake = 0;  refreshBeast = 0; //refreshAxolot = 0;
            LimitrefreshAxolot = 3; LimitrefreshSnake = 3; LimitrefreshBeast = 5;
            SnakeCooldown = 20; //AxolotCooldown = 40;
            LimitSnakeCooldown = 20; //LimitAxolotCooldown = 40;
            LimitTeleSpeedSnake = 20;
            LimitSpeedSnake = 2; LimitSpeedAxolot = 4; LimitSpeedBeast = 8;
        }
    }
    public void update() {

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
        order = 0;
        while (order < snakes.size()) {
            if (snakes.get(order).isDied()) {
                int x1 = (int) snakes.get(order).getPos().getX();
                int y1 = (int) snakes.get(order).getPos().getY();
                BackgroundEntity tam = new SkeletalSnake(x1, y1);
                BackgroundEntities.add(tam);
                snakes.remove(order);
            }
            else order ++;
        }

        order = 0;
        while (order < beasts.size()) {
            if (beasts.get(order).isDied()) {
                int x1 = (int) beasts.get(order).getPos().getX();
                int y1 = (int) beasts.get(order).getPos().getY();
                BackgroundEntity tam = new SkeletalBeast(x1, y1);
                BackgroundEntities.add(tam);
                beasts.remove(order);
            }
            else order ++;
        }

        order = 0;
        while (order < axolots.size()) {
            if (axolots.get(order).isDied()) {
                int x1 = (int) axolots.get(order).getPos().getX();
                int y1 = (int) axolots.get(order).getPos().getY();
                BackgroundEntity tam = new SkeletalAxolot(x1, y1);
                BackgroundEntities.add(tam);
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
        if (refreshSnake <= 0) {
            int bx = (int)bomber.getPos().getX();
            int by = (int)bomber.getPos().getY();
            if (level > 20) {
                int speed = LimitSpeedSnake;
                if (SnakeCooldown <= 0) speed = LimitTeleSpeedSnake;
                Reset(bx, by, speed);
                f[bx][by] = 0;
                if (NextMove(bx - speed, by, speed)) {
                    FindSnake(bx - speed, by, speed);
                }
                if (NextMove(bx + speed, by, speed)) {
                    FindSnake(bx + speed, by, speed);
                }
                if (NextMove(bx, by - speed, speed)) {
                    FindSnake(bx, by - speed, speed);
                }
                if (NextMove(bx, by + speed, speed)) {
                    FindSnake(bx + speed, by, speed);
                }
            }
            if (SnakeCooldown <= 0) {
                SnakeCooldown = LimitSnakeCooldown;
                for (Snake i : snakes) {
                    i.speed = LimitTeleSpeedSnake;
                    i.update(level, bx, by);
                    i.speed = LimitSpeedSnake;
                }
            } else {
                for (Snake i : snakes) {
                    i.update(level, bx, by);
                }
            }
            SnakeCooldown --;
            refreshSnake = LimitrefreshSnake;
        }

        if (refreshBeast <= 0) {
            for (Beast i : beasts) {
                i.update(level);
            }
            refreshBeast = LimitrefreshBeast;
        }

        if (refreshAxolot <= 0) {
            if (AxolotCooldown <= 0) {
                AxolotCooldown = LimitAxolotCooldown;
                for (Axolot i : axolots) {
                    i.update_(level);
                }
            } else {
                for (Axolot i : axolots) {
                    i.update(level);
                }
            }
            AxolotCooldown --;
            refreshAxolot = LimitrefreshAxolot;
        }

        itemList.sinh();

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
                    itemed[(int) item.getPos().getY() / tileSize][(int) item.getPos().getX() / tileSize] = false;
                    itemList.remove(item);
                    break;
                }
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

    void Creatmap() throws IOException {
        //Create();
        FileInputStream fin = null;
        String text = "test" + Integer.toString(level) + ".txt";

        try {
            fin = new FileInputStream(text);
            int data = fin.read();
            int i = 0;
            int j = 0;
            while (data != -1) {

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

        } catch (IOException e) {
        }

        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == 'S') CountSnakeInMap ++;
                else if (map[i][j] == 'A') CountBeastInMap ++;
                else if (map[i][j] == 'T') CountAxolotInMap ++;
            }

        SetUpDifficult(level);
        alg.setMap(map); alg.CanMove(0, 0); Cannot = alg.getTrace();
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
                else {
                    //int cnt = ThreadLocalRandom.current().nextInt(1,3);
                    if (map[i][j] == 'S') {
                        Snake tam = new Snake(j * tileSize, i * tileSize, LimitSpeedSnake);
                        snakes.add(tam);
                    }
                    if (map[i][j] == 'A') {
                        Beast tam = new Beast(j * tileSize, i * tileSize, LimitSpeedBeast);
                        beasts.add(tam);
                    }
                    if (map[i][j] == 'T') {
                        Axolot tam = new Axolot(j * tileSize, i * tileSize, LimitSpeedAxolot);
                        axolots.add(tam);
                    }
                    if (map[i][j] != ' ') CountEnemyInMap ++;
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

        if (!hole.isEmpty())
            for (Tele i : hole) {
                i.render(g2);
            }

        if (!axolots.isEmpty())
            for (Axolot i : axolots) {
                if (i.getStatus()) i.render(g2);
                else i.render_(g2);
            }

        if (!snakes.isEmpty())
            for (Snake i : snakes) {
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

        if (!bomber.isDied()) {
            bomber.render(g2);
        }
        else  {
            bomber.render_death(g2);
        }
    }

    void clear() {
        Cannot.clear();
        BackgroundEntities.clear();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                bombed[i][j] = false;
                itemed[i][j] = false;
            }
        }

        BombExplosions.clear();
        snakes.clear();
        beasts.clear();
        axolots.clear();
        staticEntityList.clear();
        bombList.clear();
        itemList.clear();
    }
}