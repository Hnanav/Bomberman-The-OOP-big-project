package bomberman.bomberman;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;

public class BombList {
    List<Bomb> Bombs;

    public BombList() {
        Bombs = new ArrayList<>();
    }

    public void add(Bomb bomb) {
        Bombs.add(bomb);
    }
    public List<Bomb> getBombs() {
        return Bombs;
    }

    public boolean is_Empty() {
        return !Bombs.isEmpty();
    }

    public void get_explosions() {
        for (int j = 0; j < Bombs.size(); j++) {
            Rectangle rectangle = Bombs.get(j).getPos();
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

                if (Collision(rectangle, rec1_horizontal) || Collision(rectangle, rec1_vertical)) {
                    kt = true;
                    break;
                }
            }

            if (kt) {
                Bomb tam = Bombs.get(j);
                tam.update(true);
                Bombs.set(j, tam);
            }
        }
    }

    public void update() {
        get_explosions();

        int order = 0;
        while (order < Bombs.size()) {
            Bomb tam = Bombs.get(order);
            if (tam.isExploded()) {
                int vtX = ((int) tam.getPos().getX());
                int vtY = ((int) tam.getPos().getY());
                bombed[vtX / tileSize][vtY / tileSize] = false;

                BombExplosion bombExplosion = new BombExplosion(vtX, vtY, tam.getLevel());
                BombExplosions.add(bombExplosion);

                Bombs.remove(order);
            }
            else {
                order++;
            }
        }
    }

    public void render(Graphics2D g2) throws IOException {
        for (Bomb i : Bombs) {
            i.render(g2);
        }
    }

    void clear() {
        Bombs.clear();
    }
}
