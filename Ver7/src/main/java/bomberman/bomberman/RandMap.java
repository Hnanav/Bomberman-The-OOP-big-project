package bomberman.bomberman;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import static bomberman.bomberman.SetUpLevel.*;

public class RandMap {

    public static char[][] map = new char[20][20];
    public static int[] maze1D = new int[20 * 20 + 1];
    public static int[][] f = new int[21][21];
    public static int Wall = 0;
    public static int Brick = 0;
    public static int Enemy = 0;
    public static int AIEnemy = 1;
    /** Cận khoảng cách. */
    public static int can = 0;

    /** Cận Random thực hiện. */
    private static int count = 0;

    /** Cận vật thể. */

    private static int none = 0;

    public static int level = 1;

    /** # - tường không phá được . */
    /** * - gạch phá được. */
    /** E - Enemy. */
    /** I - Item. */
    /** S - Enemy Snake. */
    /** M - Enemy Monkey. */
    /** A - Enemy Beast. */
    /** T - Enemy Axolot. */
    public static List<Entity> Enemies = new ArrayList<Entity>();

    /** Lấy số lượng vật thể theo level. */
    public static void getEntities(int level) {
        /**Reset map nguyên bản. */
        for (int i = 0; i < 20; ++ i) {
            for (int j = 0; j < 20; ++ j) {
                map[i][j] = ' ';
            }
        }
        /** Phân bố Enemy theo level. */
        Enemy = getCountEnemy(level);
        can = getDistance(level);
        Wall = getCountWall(level);
        Brick = getCountBrick(level);
        AIEnemy = getNumAIEnemy(level);
    }
    /** Nhặt cho vui :)) -> Nén mảng */
    private static int twoForOne(int u, int v) {
        return (u * 20) + v + 1;
    }
    /** Nhặt cho vui :)) -> Random theo giới hạn N * N. */
    public static int randomInt(int limit) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * limit * limit;
        return (int) randomDouble;
    }
    /** Reset mảng đánh dấu 1 chiều và setup cho tọa độ tối đa 4 AIPLayer. */
    public static void SetMaze1D() {

        for (int i = 0; i < 20 * 20; i++) maze1D[i] = 0;

        maze1D[twoForOne(0, 0)] = 999;
        maze1D[twoForOne(0, 1)] = 999;
        maze1D[twoForOne(1, 0)] = 999;
        maze1D[twoForOne(1, 1)] = 999;

        if (AIEnemy == 1) return;

        maze1D[twoForOne(19, 19)] = 999;
        maze1D[twoForOne(19, 18)] = 999;
        maze1D[twoForOne(18, 19)] = 999;
        maze1D[twoForOne(18, 18)] = 999;

        if (AIEnemy == 2) return;

        maze1D[twoForOne(0, 19)] = 999;
        maze1D[twoForOne(1, 19)] = 999;
        maze1D[twoForOne(0, 18)] = 999;
        maze1D[twoForOne(1, 18)] = 999;

        if (AIEnemy < 4) return;

        maze1D[twoForOne(19, 0)] = 999;
        maze1D[twoForOne(18, 0)] = 999;
        maze1D[twoForOne(19, 1)] = 999;
        maze1D[twoForOne(18, 1)] = 999;
    }
    /** check de dat Wall. */
    public static void SetUpWall() {
        SetMaze1D();
        /** Số lượng thành phần liên thông. */
        int components;
        /** số lượng wall cần đặt. */
        int block = 0;
        /** số lượng enemy. */
        int cnt = 0;
        /** Reset map ngoài Enemy */
        for (int i = 0; i < 20; ++ i)
            for (int j = 0; j < 20; ++ j)
                if (map[i][j] != 'E' && i != 0 && j != 0) map[i][j] = ' ';
        /** Đếm số lượng Enemy */
        for (Entity get : Enemies)
            if (map[get.x][get.y] == 'E') {
                int i = twoForOne(get.x, get.y);
                maze1D[i] = 1;
                cnt += 1;
            }
        while (block < Wall) {

            UnionFind unionFind = new UnionFind(20 * 20 + 1);
            count ++;
            /** Cận */
            if (count > 1000) break;
            block ++;
            int randomInt = randomInt(20) + 1;
            int x = (randomInt - 1) / 20;
            int y = (randomInt - 1) % 20;
            if (maze1D[randomInt] > 0) {
                block--;
                continue;
            }
            maze1D[randomInt] = 2;
            map[x][y] = '#';
            for (int i = 1; i <= 20 * 20; i++) {
                if (maze1D[i] > 0) continue;
                int u = (i - 1) / 20;
                int v = (i - 1) % 20;
                /** Check các đỉnh xung quanh i (trên, dưới, trái, phải).
                 *  Nếu đỉnh đó chưa được bố trí
                 *  và (i và đỉnh đó chưa thuộc 1 thành phần liên thông)
                 *  -> ghép chúng thành 1 thành phần liên thông */
                if (u > 0) {
                    int p = twoForOne(u - 1, v);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
                if (u < 19) {
                    int p = twoForOne(u + 1, v);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
                if (v > 0) {
                    int p = twoForOne(u, v - 1);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
                if (v < 19) {
                    int p = twoForOne(u, v + 1);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
            }
            components = unionFind.count();
            /** 0 là đỉnh chưa đánh dấu, 1 là đã đánh dấu */
            /** Coi các đỉnh được đánh dấu như 1 block */
            /** none là cận ~ 1 */
            /** Nếu số lượng thành phần trong 2 thành phần liên thông (0, 1) bằng nhau
             *  -> điều kiên trong If không được thực hiện. */
            if (components - block - 4 * AIEnemy - cnt - none > 2) {
                maze1D[randomInt] = 0;
                map[x][y] = ' ';
                block --;
            }
        }
    }

    /** check de dat Brick. */
    public static boolean check(int x, int y) {
        for (Entity get : Enemies) {
            int cnt = 0;
            if (map[get.x][get.y] == 'E') {
                if (!(get.x + 1 == x && get.y == y) || !(get.x - 1 == x && get.y == y)
            || !(get.x == x && get.y + 1 == y) || !(get.x == x && get.y - 1 == y)) continue;
                if (map[get.x + 1][get.y] != ' ') cnt ++;
                if (map[get.x][get.y + 1] != ' ') cnt ++;
                if (map[get.x - 1][get.y] != ' ') cnt ++;
                if (map[get.x][get.y - 1] != ' ') cnt ++;
            }
            if (cnt > 3) return false;
        }
        return true;
    }

    /** Bố trí vật thể phá được trong map. */
    public static void SetUpBrick() {
        int block = 0;
        while (block < Brick) {
            int randomInt = randomInt(20) + 1;
            int x = (randomInt - 1) / 20;
            int y = (randomInt - 1) % 20;
            if (maze1D[randomInt] == 0) {
                if (!check(x, y)) continue;
                block++;
                maze1D[randomInt] = 3;
                map[x][y] = '*';
            }
        }
    }

    /** Bố trí vị trí qua ải. */
    public static void RandomHole() {
        while (true) {
            int randomInt = randomInt(20) + 1;
            int x = (randomInt - 1) / 20;
            int y = (randomInt - 1) % 20;
            if (x < 10 || y < 10) continue;
            int cnt = 0;
            if (x - 1 > 0) {
                if (map[x - 1][y] == '#') cnt ++;
            }
            if (x + 1 < 19) {
                if (map[x + 1][y] == '#') cnt ++;
            }
            if (y - 1 > 0) {
                if (map[x][y - 1] == '#') cnt ++;
            }
            if (y + 1 < 19) {
                if (map[x][y + 1] == '#') cnt ++;
            }
            if (cnt > 3) continue;
            map[x][y] = 'H';
            break;
        }
    }

    /** Check và bố trí số lượng Enemy. */
    public static boolean checkDistance(int x, int y) {
        /** Số lượng Enemy tối đa trên 1 hàng or 1 cột. */
        int xycan = 20 / can + 5;
        int Pxcan = 1;
        int Pycan = 1;
        for (Entity get : Enemies) {
            double dis = (get.x - x) * (get.x - x) +
                    (get.y - y) * (get.y - y);
            long distance = Math.round(Math.sqrt(dis));
            if (get.x == x) Pxcan ++;
            if (get.y == y) Pycan ++;
            if (can > distance) return false;
        }
        if (Pxcan >= xycan || Pycan >= xycan) return false;
        return true;
    }
    public static void RandomEnemy() {
        Enemies.clear();
        Enemies = new ArrayList<Entity>();
        int k = 0;
        while (k < Enemy) {
            for (int i = 3; i < 19; ++ i) {
                for (int j = 3; j < 19; ++ j) {
                    if (map[i][j] != ' ') continue;
                    if (checkDistance(i, j)) {
                        map[i][j] = 'E';
                        Enemies.add(new Entity(i, j));
                        k++;
                        break;
                    }
                }
                if (k >= Enemy) break;
            }
        }
    }
    /** S - Enemy Snake. */
    /** M - Enemy Monkey. */
    /** A - Enemy Beast. */
    /** T - Enemy Axolot. */
    public static int bfs(int x, int y) {
        if (x < 0 || y < 0 || x > 19 || y > 19) {
            return 0;
        }
        if (f[x][y] != -1) return f[x][y];
        if (map[x][y] == '#' || map[x][y] == '*' || map[x][y] == 'H') {
            f[x][y] = 0;
            return f[x][y];
        }
        if (map[x][y] != '#' && map[x][y] != '*' && map[x][y] != 'H' && map[x][y] != ' ') {
            f[x][y] = 1;
            return f[x][y];
        }

        //dd[x][y] = 1;
        //if (x > 0 && map[x - 1][y] == '.' && dd[x - 1][y] == 0) {
            f[x][y] = bfs(x - 1, y);
            if (f[x][y] == 1) return f[x][y];
        //}
        //if (y > 0 && map[x][y - 1] == '.' && dd[x][y - 1] == 0) {
            f[x][y] = bfs(x, y - 1);
            if (f[x][y] == 1) return f[x][y];
        //}
        //if (x < 19 && map[x + 1][y] == '.' && dd[x + 1][y] == 0) {
            f[x][y] = bfs(x + 1, y);
            if (f[x][y] == 1) return f[x][y];
        //}
        //if (y < 19 && map[x][y + 1] == '.' && dd[x][y + 1] == 0) {
            f[x][y] = bfs(x, y + 1);
            if (f[x][y] == 1) return f[x][y];
        //}
        return f[x][y];
    }
    public static boolean checkAround(int x, int y) {
        f[x][y] = 2;
        /** 1 trong 4 đường đi bằng true -> return true */
        return ((bfs(x - 1, y) == 1)
                || (bfs(x + 1, y) == 1) || (bfs(x, y - 1) == 1) || (bfs(x, y + 1) == 1));
    }
    public static void SetUpEnemy(int level) {

        if (level <= 10) {
            //System.out.println(level + "   hiiii");
            for (int i = 0; i < 20; ++i)
                for (int j = 0; j < 20; ++j)
                    if (map[i][j] == 'E') {
                        int type = Percentage(level, Enemy);
                        if (type == 1) map[i][j] = 'A';
                        if (type == 2) map[i][j] = 'S';
                    }
            return;
        }
        if (level <= 50) {
            for (int i = 0; i < 20; ++ i)
                for (int j = 0; j < 20; ++ j) f[i][j] = -1;
            //System.out.println(level + "   hiiii");
            for (int i = 0; i < 20; ++ i)
                for (int j = 0; j < 20; ++ j)
                    if (map[i][j] == 'E') {
                        /** Nếu ko có đường đi. */
                        int type = Percentage(level, Enemy);
                        if (type == 1) map[i][j] = 'A';
                        if (type == 2) map[i][j] = 'S';
                        if (type == 3) map[i][j] = 'T';
                        if (checkAround(i, j) == false) map[i][j] = 'T';
                    }
        }
    }

    /** check cho dep. */
    public static boolean check1() {
        for (Entity get : Enemies) {
            int cnt = 0;
            if (map[get.x][get.y] == 'E') {
                if (map[get.x + 1][get.y] != ' ') cnt ++;
                if (map[get.x][get.y + 1] != ' ') cnt ++;
                if (map[get.x - 1][get.y] != ' ') cnt ++;
                if (map[get.x][get.y - 1] != ' ') cnt ++;
            }
            if (cnt > 3) return false;
        }
        return true;
    }

    /** Sinh Map */
    public static void RandDomMap(int level) {
        none = 0;
        getEntities(level);
        RandomEnemy();
        while (true) {
            count = 0;
            SetUpWall();
            if (count < 1000) break;
            none ++;
        }
        SetUpBrick();
        RandomHole();
        SetUpEnemy(level);
        //System.out.println(none);
        /**debug.*/
        /*if (!check1()) {
            System.out.println(level);
        }*/
    }

    public static void Create() {
        for (int ii = 1; ii <= 50; ++ ii) {
            RandDomMap(ii);
            String res = "";
            //res += Integer.toString(ii) + '\n';
            for (int i = 0; i < 20; ++ i) {
                for (int j = 0; j < 20; ++ j) {
                    res += map[i][j];
                }
                res += '\n';
            }
            //System.out.println(ii);
            try {
                String text = "test" + Integer.toString(ii) + ".txt";
                Formatter f = new Formatter(text);
                f.format(res);
                f.close();
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}
