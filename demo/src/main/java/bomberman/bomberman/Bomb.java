package bomberman.bomberman;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity{
    private int dem;
    private int level;
    private boolean Explosion;

    Bomb (int x, int y, BufferedImage bufferedImage, int level) {
        super(x, y);
        image = bufferedImage;
        this.level = level;
        Explosion = false;
        dem = 100;
    }

    public void render(Graphics2D g2) throws IOException {
        if (dem > 0) {
            super.render(g2);
            dem--;
        }
        else Explosion = true;
    }

    public boolean isExplosion() {
        return Explosion;
    }
}
