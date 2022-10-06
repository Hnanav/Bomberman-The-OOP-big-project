package bomberman.bomberman;

import static bomberman.bomberman.Game.*;
import static bomberman.bomberman.CheckCollision.*;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
