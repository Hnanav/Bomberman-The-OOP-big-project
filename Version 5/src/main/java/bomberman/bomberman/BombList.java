package bomberman.bomberman;

import static bomberman.bomberman.Game.*;
import static bomberman.bomberman.CheckCollision.*;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombList {
    List<Bomb> Bombs;

    BombList() {
        Bombs = new ArrayList<>();
    }

    void add(Bomb bomb) {
        Bombs.add(bomb);
    }

    boolean isEmpty() {
        return Bombs.isEmpty();
    }

    void get_explosions(int order) {
        if (order >= Bombs.size()) return;

        Bomb bomb = Bombs.get(order);
        int level = bomb.getLevel();

        if (bomb.isExploded() == true) {
            Rectangle pos = bomb.getPos();


            Rectangle rec1_horizontal = new Rectangle(pos.getX() - level * tileSize, pos.getY(),
                                                    tileSize * (level * 2 + 1), tileSize);
            Rectangle rec1_vertical = new Rectangle(pos.getX(), pos.getY() - level * tileSize,
                                                 tileSize, tileSize * (level * 2 + 1));

            for (int i = 0; i < Bombs.size(); i++) {
                if (i != order && Bombs.get(i).isExploded() == false) {
                    Rectangle rec2 = Bombs.get(i).getPos();

                    if (Collision(rec1_horizontal, rec2) || Collision(rec1_vertical, rec2)) {
                        Bomb tam = Bombs.get(i);
                        tam.update(true);
                        Bombs.set(i, tam);
                    }
                }
            }
        }
        else if (order == 0) return;

        get_explosions(order + 1);
    }

    void update() {
        get_explosions(0);

        int order = 0;
        while (order < Bombs.size()) {
            Bomb tam = Bombs.get(order);
            if (tam.isExploded()) {
                int vtX = ((int) tam.getPos().getX());
                int vtY = ((int) tam.getPos().getY());
                bombed[vtX / 40][vtY / 40] = false;

                BombExplosion bombExplosion = new BombExplosion(vtX, vtY, tam.getLevel());
                BombExplosions.add(bombExplosion);

                Bombs.remove(order);
            }
            else {
                order++;
            }
        }
    }

    void render(Graphics2D g2) throws IOException {
        for (Bomb i : Bombs) {
            i.render(g2);
        }
    }
}
