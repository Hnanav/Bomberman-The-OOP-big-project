package bomberman.bomberman;

import java.awt.*;
import java.io.IOException;

import static bomberman.bomberman.Game.bomb_time;
import static bomberman.bomberman.Game.entityImages;

public class Bomb extends Entity{
    private int countdown;
    private final int level;
    private boolean exploded;
    Bomb (int x, int y, int level) {
        super(x, y);
        this.level = level;
        if (level == 1) image = entityImages.bomb_1;
        else if (level == 2) image = entityImages.bomb_2;
        else if (level == 3) image = entityImages.bomb_3;
        exploded = false;
        countdown = bomb_time;
    }

    void update(Boolean exploded) {
        this.exploded = exploded;
    }

    public void render(Graphics2D g2) throws IOException {
        if (countdown > 0) {
            super.render(g2);
            countdown--;
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
