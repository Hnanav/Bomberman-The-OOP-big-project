package bomberman.bomberman;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Rand1Map {
static  int d = 0;
    public static char[][] map = new char[20][20];
    public static int[] maze1D = new int[20 * 20 + 1];
    public static int Wall = 0;
    public static int[] limit = new int[5];
    public static int Enemy = 0;
    public static int can = 0;

    private static int level = 1;
    public static int Brick = 0;
    public static int AIEnemy = 1;
    /** W - tường không phá được . */
    /** B - gạch phá được. */
    /** E - Enemy. */
    /** I - Item. */
    /** S - Enemy Snake. */
    /** M - Enemy Monkey. */
    /** A - Enemy Beast. */
    /** T - Enemy Axolot. */
    public static List<Entity> Enemies = new ArrayList<Entity>();

    private static int twoForOne(int u, int v) {
        return (u * 20) + v + 1;
    }
    public static int randomInt(int limit) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * limit * limit;
        return (int) randomDouble;
    }
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
    public static void SetUpWall() {
        int components;
        int block = 0;
        SetMaze1D();
        int cnt = 0;
        for (Entity get : Enemies)
            if (map[get.x][get.y] == 'E') {
                int i = twoForOne(get.x, get.y);
                maze1D[i] = 1;
                cnt += 1;
            }
        while (block < Wall) {
            UnionFind unionFind = new UnionFind(20 * 20 + 1);

            block++;
            int randomInt = randomInt(20) + 1;
            int x = (randomInt - 1) / 20;
            int y = (randomInt - 1) % 20;
            if (maze1D[randomInt] > 0) {
                block--;
                continue;
            }
            maze1D[randomInt] = 2;
            map[x][y] = 'W';
            for (int i = 1; i <= 20 * 20; i++) {
                if (maze1D[i] > 0) continue;
                int u = (i - 1) / 20;
                int v = (i - 1) % 20;
                if (u > 0) {
                    int p = twoForOne(u - 1, v);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
                if (u < 20 - 1) {
                    int p = twoForOne(u + 1, v);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
                if (v > 0) {
                    int p = twoForOne(u, v - 1);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
                if (v < 20 - 1) {
                    int p = twoForOne(u, v + 1);
                    if (maze1D[p] == 0 && !(unionFind.find(i) == unionFind.find(p))) unionFind.union(i, p);
                }
            }
            components = unionFind.count();
            if (components - block - 4 * AIEnemy - cnt * 2 > 2) {
                maze1D[randomInt] = 0;
                map[x][y] = '.';
                block --;
            }
        }
    }

    public static void getEntities(int level) {
        /**Reset map nguyên bản. */
        for (int i = 0; i < 20; ++ i) {
            for (int j = 0; j < 20; ++ j) {
                map[i][j] = '.';
            }
        }

        /** Phân bố Enemy theo level. */
        if (level >= 1 && level <= 10) {
            Enemy = ThreadLocalRandom.current().nextInt(2,5);
            can = 7;
            Wall = 50;
            Brick = 70;
            AIEnemy = 1;
            limit[1] = 5; limit[2] = 2;
            map[0][0] = '*';
        }
        if (level >= 11 && level <= 20) {
            Enemy = ThreadLocalRandom.current().nextInt(5,8);
            can = 5;
            Wall = 50;
            Brick = 80;
            AIEnemy = 2;
            limit[1] = 10; limit[2] = 5; limit[3] = 8;
            map[0][0] = '*'; map[19][19] = '*';
        }
        if (level >= 21 && level <= 30) {
            Enemy = ThreadLocalRandom.current().nextInt(8,13);
            can = 5;
            Wall = 40;
            Brick = 100;
            AIEnemy = 3;
            limit[1] = 5; limit[2] = 5; limit[3] = 8; limit[4] = 8;
            map[0][0] = '*'; map[19][19] = '*'; map[0][19] = '*';
        }
        if (level >= 31 && level <= 40) {
            Enemy = ThreadLocalRandom.current().nextInt(12,17);
            can = 4;
            Wall = 40;
            Brick = 80;
            AIEnemy = 4;
            limit[1] = 5; limit[2] = 5; limit[3] = 12; limit[4] = 12;
            map[0][0] = '*'; map[19][19] = '*'; map[0][19] = '*'; map[19][0] = '*';
        }
        if (level >= 41 && level <= 50) {
            Enemy = ThreadLocalRandom.current().nextInt(16,23);
            can = 3;
            Wall = 40;
            Brick = 90;
            AIEnemy = 4;
            limit[1] = 5; limit[2] = 5; limit[3] = 20; limit[4] = 20;
            map[0][0] = '*'; map[19][19] = '*'; map[0][19] = '*'; map[19][0] = '*';
        }
    }
    /** check de dat brick. */
    public static boolean check(int x, int y) {
        for (Entity get : Enemies) {
            int cnt = 0;
            if (map[get.x][get.y] == 'E') {
                if (!(get.x + 1 == x && get.y == y) || !(get.x - 1 == x && get.y == y)
            || !(get.x == x && get.y + 1 == y) || !(get.x == x && get.y - 1 == y)) continue;
                if (map[get.x + 1][get.y] != '.') cnt ++;
                if (map[get.x][get.y + 1] != '.') cnt ++;
                if (map[get.x - 1][get.y] != '.') cnt ++;
                if (map[get.x][get.y - 1] != '.') cnt ++;
            }
            if (cnt > 3) return false;
        }
        return true;
    }
    /** check cho dep. */
    public static boolean check1() {
        for (Entity get : Enemies) {
            int cnt = 0;
            if (map[get.x][get.y] == 'E') {
                if (map[get.x + 1][get.y] != '.') cnt ++;
                if (map[get.x][get.y + 1] != '.') cnt ++;
                if (map[get.x - 1][get.y] != '.') cnt ++;
                if (map[get.x][get.y - 1] != '.') cnt ++;
            }
            if (cnt > 3) return false;
        }
        return true;
    }
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
                map[x][y] = 'B';
            }
        }
    }
    public static void RandomHole() {
        while (true) {
            int randomInt = randomInt(20) + 1;
            int x = (randomInt - 1) / 20;
            int y = (randomInt - 1) % 20;
            if (x < 10 || y < 10) continue;
            int cnt = 0;
            if (x - 1 > 0) {
                if (map[x - 1][y] == 'W') cnt ++;
            }
            if (x + 1 < 19) {
                if (map[x + 1][y] == 'W') cnt ++;
            }
            if (y - 1 > 0) {
                if (map[x][y - 1] == 'W') cnt ++;
            }
            if (y + 1 < 19) {
                if (map[x][y + 1] == 'W') cnt ++;
            }
            if (cnt > 3) continue;
            map[x][y] = 'H';
            break;
        }
    }
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
                    if (map[i][j] != '.') continue;
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
    public static boolean checkPos(int x, int y, int type) {
        limit[type] --;
        if (limit[type] <= 0) {
            limit[type] ++;
            return false;
        }
        if (type == 2) {
            int num = 1;
            boolean check = false;
            while (num <= 3) {
                if (!(x - num > 0)) {
                    break;
                } else if (map[x - num][y] == '.') break;
                num ++; if (num > 3) check = true;
            }
            num = 1;
            while (num <= 3) {
                if (!(x + num < 20)) {     break;
                } else if (map[x + num][y] == '.') break;
                num ++; if (num > 3) check = true;
            }
            num = 1;
            while (num <= 3) {
                if (!(y - num > 0))      {break;
                } else if (map[x][y - num] == '.') break;
                num ++; if (num > 3) check = true;
            }
            num = 1;
            while (num <= 3) {
                if (!(y + num < 20))      {break;
                } else if (map[x][y + num] == '.') break;
                num ++; if (num > 3) check = true;
            }
            if (!check)  limit[type] ++;
            return check;
        }
        if (type == 1) {
            int num = 1;
            boolean check = false;
            while (num <= 1) {
                if (!(x - num > 0)) {
                    break;
                } else if (map[x - num][y] == '.') break;
                num ++; if (num > 1) check = true;
            }
            num = 1;
            while (num <= 1) {
                if (!(x + num < 20)) {     break;
            } else if (map[x + num][y] == '.') break;
                num ++; if (num > 1) check = true;
            }
            num = 1;
            while (num <= 1) {
                if (!(y - num > 0))      {break;
            } else if (map[x][y - num] == '.') break;
                num ++; if (num > 1) check = true;
            }
            num = 1;
            while (num <= 1) {
                if (!(y + num < 20))      {break;
                } else if (map[x][y + num] == '.') break;
                num ++; if (num > 1) check = true;
            }
            if (!check)  limit[type] ++;
            return check;
        }
        if (type == 3) return true;
        return true;
    }
    public static void SetUpEnemy(int level) {
        int minRand = 1;
        int maxRand = 2;
        if (level <= 10) maxRand = 2;
        else if (level <= 20) maxRand = 3;
        else maxRand = 4;
        for (int i = 0; i < 20; ++ i)
            for (int j = 0; j < 20; ++ j) {
                if (map[i][j] == 'E') {
                    while (true) {
                        int type = ThreadLocalRandom.current().nextInt(minRand, maxRand);
                        //System.out.println(level + " " + type );
                        if (checkPos(i, j, type)) {
                            switch (type) {
                                case 1 :
                                    map[i][j] = 'A';
                                    break;
                                case 2 :
                                    map[i][j] = 'S';
                                    break;
                                case 3 :
                                    map[i][j] = 'M';
                                    break;
                                case 4 :
                                    map[i][j] = 'T';
                                    break;
                            }
                            if (map[i][j] != 'E') break;
                        }
                    }
                }
            }
    }
    public static void RandDomMap(int level) {

        getEntities(level);
        RandomEnemy();
        SetUpWall();
        SetUpBrick();
        RandomHole();
        //SetUpEnemy(level);

        /**debug.*/
        /*if (!check1()) {
            System.out.println(level);
        }*/
    }

    public static void create() {
        for (int ii = 1; ii <= 35; ++ ii) {
            RandDomMap(ii);
            String res = "";
            //res += Integer.toString(ii) + '\n';
            for (int i = 0; i < 20; ++ i) {
                for (int j = 0; j < 20; ++ j) {
                    res += map[i][j];
                }
                res += '\n';
            }
            System.out.println(ii);
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
    /*public static void main(String arg[]) {

        String res = "";
        for (int ii = 1; ii <= 50; ++ ii) {
            RandDomMap(ii);
            res += Integer.toString(ii) + '\n';
            for (int i = 0; i < 20; ++ i) {
                for (int j = 0; j < 20; ++ j) {
                    res += map[i][j];
                }
                res += '\n';
            }
        }
        try {
            Formatter f = new Formatter("test.txt");
            f.format(res);
            f.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }*/
}
