package bomberman.bomberman;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bomberman.bomberman.Game.*;
import static bomberman.bomberman.Alg.*;
public class StaticEntityList {
    private List<StaticEntity> desEntity;
    private List<StaticEntity> undesEntity;

    StaticEntityList() {
        desEntity = new ArrayList<>();
        undesEntity = new ArrayList<>();
    }

    public List<StaticEntity> getDesEntity() {
        return desEntity;
    }

    public List<StaticEntity> getUndesEntity() {
        return undesEntity;
    }

    public void add(StaticEntity staticEntity, char c) {
        if (c == '#') undesEntity.add(staticEntity);
        else desEntity.add(staticEntity);
    }

    public int find_des(int x, int y) {
        int vt = 0;
        for (StaticEntity i : desEntity) {
            Rectangle pos = i.getPos();
            if (pos.getX() == x && pos.getY() == y) {
                vt = desEntity.indexOf(i);
                break;
            }
        }
        return vt;
    }

    public void remove_des(int x, int y) {
        char[][] map = alg.getMap();
        if (y == 0 && x == 0) map[0][0] = ' ';
        else if (y == 0) map[0][x / tileSize] = ' ';
        else if (x == 0) map[y / tileSize][0] = ' ';
        else map[y / tileSize][x / tileSize] = ' ';
        alg.setMap(map);
        alg.CanMove(0, 0);
        Cannot = alg.getTrace();
        desEntity.remove(find_des(x, y));
    }

    void render(Graphics2D g2) throws IOException {
        for (StaticEntity i : desEntity) {
            i.render(g2);
        }

        for (StaticEntity i : undesEntity) {
            i.render(g2);
        }
    }
}
