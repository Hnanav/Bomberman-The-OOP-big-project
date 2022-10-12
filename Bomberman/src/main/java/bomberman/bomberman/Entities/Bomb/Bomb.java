package bomberman.bomberman.Entities.Bomb;

import java.awt.*;
import java.io.IOException;

import static bomberman.bomberman.Game.bomb_time;
import static bomberman.bomberman.Game.paused;

import bomberman.bomberman.Entities.Entity;
import bomberman.bomberman.Entities.EntityImages;
import bomberman.bomberman.Entities.Dynamic.Bomber;

public class Bomb extends Entity{
    private int countdown;
    private final int level;
    private boolean exploded;
    public Bomb (int x, int y, int level) {
        super(x, y);
        this.level = level;
        image = EntityImages.bomb;
        exploded = false;
        countdown = bomb_time;
    }

    void update(Boolean exploded) {
        this.exploded = exploded;
    }

    public void render(Graphics2D g2) throws IOException {
        if (countdown > 0) {
            super.render(g2);
            if (!paused) countdown--;
        }
        else exploded = true;
    }

    public int getLevel() {
        return level;
    }

    public boolean isExploded() {
        return exploded;
    }
}
