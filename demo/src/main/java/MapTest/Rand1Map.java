package MapTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class Rand1Map {

    public static char[][] map = new char[20][20];
    public static int Wall = 0;
    public static int Enemy = 0;
    public static int level = 1;
    public static int Brick = 0;
    /** W - tường không phá được . */
    /** B - gạch phá được. */
    /** E - Enemy. */
    /** I - Item. */
    public static List<Entity> Enemies = new ArrayList<Entity>();
    public static boolean checkDistance(int x, int y) {
        int can = Math.max((20 * 20) / level, 4);
        can = Math.min(can, 9 - level / 10);
        can = Math.max(can, 4);
        int xycan = 20 / can + 2;
        int Pxcan = 1;
        int Pycan = 1;
        //System.out.println(can);
        for (Entity get : Enemies) {
            double dis = (get.x - x) * (get.x - x) +
                    (get.y - y) * (get.y - y);
            long distance = Math.round(Math.sqrt(dis));
            if (get.x == x) Pxcan ++;
            if (get.y == y) Pycan ++;
            if (can > distance) return false;
        }
        if (x < can && y < can) return false;
        if (x < can && 19 - y < can) return false;
        if (19 - x < can && y < can) return false;
        if (19 - x < can && 19 - y < can) return false;
        if (Pxcan >= xycan || Pycan >= xycan) return false;
        return true;
    }
    public static void randmap()  {
        for (int i = 0; i < 20; ++ i) {
            for (int j = 0; j < 20; ++ j) {
                map[i][j] = '.';
            }
        }

        level = 50;
        Wall = ThreadLocalRandom.current().nextInt(50,101);
        Brick = ThreadLocalRandom.current().nextInt(50,101);
        Enemy = level * 10;
        System.out.println(Enemy);
        System.out.println(Brick);
        map[0][0] = 'P'; map[0][19] = 'P'; map[19][0] = 'P'; map[19][19] = 'P';
        int d = 0;
        for (int k = 1; k <= Enemy; ++ k) {
            for (int i = 0; i < 20; ++ i) {
                for (int j = 0; j < 20; ++ j) {
                    if (checkDistance(i, j)) {
                        d++;
                        map[i][j] = 'E';
                        Enemies.add(new Entity(i, j));
                        break;
                    }
                }
            }
        }
        System.out.println(d);
    }
    public static void main(String arg[]) {

        randmap();
        for (int i = 0; i < 20; ++ i) {
            for (int j = 0; j < 20; ++ j) {
                System.out.print(map[i][j]);
            }
            System.out.print('\n');
        }
    }
}
