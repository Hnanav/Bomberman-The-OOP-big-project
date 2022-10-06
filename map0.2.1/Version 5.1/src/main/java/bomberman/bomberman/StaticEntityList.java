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
        if (c == 'W') undesEntity.add(staticEntity);
        else desEntity.add(staticEntity);
    }

    public void update() {
        int order = 0;
        while (order < desEntity.size()) {
            Rectangle tam = desEntity.get(order).getPos();
            boolean kt = false;

            for (BombExplosion i : BombExplosions) {
                Rectangle pos = i.getPos();
                int left = i.getLeft_pole();
                int right = i.getRight_pole();
                int up = i.getUp_pole();
                int down = i.getDown_pole();

                Rectangle rec1_horizontal = new Rectangle(left, pos.getY(),
                        right - left + tileSize, tileSize);
                Rectangle rec1_vertical = new Rectangle(pos.getX(), up,
                        tileSize, down - up + tileSize);

                if ((Collision(tam, rec1_horizontal) || Collision(tam, rec1_vertical))) {
                    kt = true;
                    break;
                }
            }

            if (kt) {
                int vtX = (int) tam.getX() / tileSize;
                int vtY = (int) tam.getY() / tileSize;
                map[vtY][vtX] = ' ';
                BackgroundEntity backgroundEntity = new Floor(vtX * tileSize, vtY * tileSize);
                BackgroundEntities.add(backgroundEntity);
                desEntity.remove(order);
            }
            else {
                order++;
            }
        }
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
