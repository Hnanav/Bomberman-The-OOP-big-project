package bomberman.bomberman;

import static bomberman.bomberman.Game.*;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity{
    private int countdown;
    private int level;
    private boolean exploded;
    Bomb (int x, int y, BufferedImage bufferedImage, int level) {
        super(x, y);
        image = bufferedImage;
        this.level = level;
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
