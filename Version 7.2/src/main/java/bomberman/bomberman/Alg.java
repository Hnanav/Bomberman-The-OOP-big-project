package bomberman.bomberman;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Alg {
    private static int[][] f = new int[30][30];
    private static List<Pair<Integer, Integer> > trace;
    private static char[][] map;
    Alg() {
        map = new char[30][30];
    }
    public static int[][] getF() {
        return f;
    }
    public void setMap(char[][] map) {
        this.map = map;
    }
    public char[][] getMap() {
        return this.map;
    }
    public List<Pair<Integer, Integer> > getTrace() {
        return trace;
    }
    public static void bfs(int x, int y) {
        if (x > 19 || x < 0 || y > 19 || y < 0) return;
        if (map[x][y] == '#' || map[x][y] == '*' || map[x][y] == 'H') return;
        if (f[x][y] != -1) return;
        f[x][y] = 1;
        bfs(x - 1, y);
        bfs(x + 1, y);
        bfs(x, y - 1);
        bfs(x, y + 1);
    }
    public void CanMove(int x, int y) {
        trace = new ArrayList<>();
        for (int i = 0; i < 20; ++ i)
            for (int j = 0; j < 20; ++ j) f[i][j] = -1;
        bfs(0, 0);
        for (int i = 0; i < 20; ++ i)
            for (int j = 0; j < 20; ++j)
                if (f[i][j] == -1 && map[i][j] != '*' &&
                        map[i][j] != '#') trace.add(new Pair<>(j, i));
    }
}
