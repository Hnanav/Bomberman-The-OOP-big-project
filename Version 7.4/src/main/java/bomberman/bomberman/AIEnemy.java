package bomberman.bomberman;

import javafx.scene.shape.Rectangle;

import java.util.List;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;

public class AIEnemy {
    public static int lowx;
    public static int highx;
    public static int lowy;
    public static int highy;
    static int[][] f = new int[810][810];

    public static void Reset(int x, int y, int speed) {
        speed = Math.min(speed * 100, 250);
        lowx = Math.max(x - speed, 0);
        lowy = Math.max(y - speed, 0);
        highx = Math.min(x + speed, 19 * tileSize - 1);
        highy = Math.min(y + speed, 19 * tileSize - 1);
        for (int i = 0; i < 800;  i += speed)
            for (int j = 0; j < 800; j += speed) f[i][j] = 100000;

    }
    public static boolean NextMove(int x, int y, int speed) {

        if (x < lowx || x > highx || y < lowy || y > highy) return false;

        List<StaticEntity> walls = staticEntityList.getDesEntity();
        List<StaticEntity> bricks = staticEntityList.getUndesEntity();

        Rectangle now = new Rectangle(x, y, tileSize, tileSize);

        for (StaticEntity i : bricks) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) return false;
        }
        for (StaticEntity i : walls) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) return false;
        }

        for (Beast i : beasts) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) return false;
        }
        for (Axolot i : axolots) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) return false;
        }
        return true;
    }
    public static int FindSnake(int x, int y, int speed) {
        if (x < lowx || x > highx || y < lowy || y > highy) return 999;
        if (f[x][y] != 100000) return f[x][y];
        List<StaticEntity> walls = staticEntityList.getDesEntity();
        List<StaticEntity> bricks = staticEntityList.getUndesEntity();
        Rectangle now = new Rectangle(x, y, tileSize, tileSize);
        for (StaticEntity i : bricks) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) {
                f[x][y] = 999;
                return f[x][y];
            }
        }
        for (StaticEntity i : walls) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) {
                f[x][y] = 999;
                return f[x][y];
            }
        }
        for (Snake i : snakes) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) {
                f[x][y] = 0;
                return f[x][y];
            }
        }
        for (Beast i : beasts) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) {f[x][y] = 999; return f[x][y];}
        }
        for (Axolot i : axolots) {
            Rectangle next = i.getPos();
            if (Collision(now, next)) {f[x][y] = 999; return f[x][y];}
        }
        f[x][y] = Math.min(FindSnake(x - speed, y, speed) + speed, f[x][y]);
        f[x][y] = Math.min(FindSnake(x + speed, y, speed) + speed, f[x][y]);
        f[x][y] = Math.min(FindSnake(x, y - speed, speed) + speed, f[x][y]);
        f[x][y] = Math.min(FindSnake(x, y + speed, speed) + speed, f[x][y]);
        return f[x][y];
    }
    public static boolean checkMove(int nx, int ny, int x, int y, int speed, Snake ths) {
        if (nx < lowx || nx > highx || ny < lowy || ny > highy) return false;
        if (f[x][y] != 100000 && f[x][y] >= 999 ) return false;
        Rectangle now = new Rectangle(x, y, tileSize, tileSize);
        Rectangle next1 = new Rectangle(nx, ny, tileSize, tileSize);
        List<StaticEntity> walls = staticEntityList.getDesEntity();
        List<StaticEntity> bricks = staticEntityList.getUndesEntity();
        for (StaticEntity i : bricks) {
            Rectangle next = i.getPos();
            if (Collision(next1, next)) return false;
        }
        for (StaticEntity i : walls) {
            Rectangle next = i.getPos();
            if (Collision(next1, next))  return false;
        }
        for (Snake i : snakes)
            if (ths != i){
            Rectangle next = i.getPos();
            if (Collision(next1, next)) return false;
        }
        for (Beast i : beasts) {
            Rectangle next = i.getPos();
            if (Collision(next1, next)) return false;
        }
        for (Axolot i : axolots) {
            Rectangle next = i.getPos();
            if (Collision(next1, next)) return false;
        }

        if (Collision(now, next1)) return true;
        return false;
    }
}
