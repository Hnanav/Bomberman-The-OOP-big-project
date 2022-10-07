package bomberman.bomberman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.scene.shape.Rectangle;

public class Entity {
    protected int x;
    protected int y;
    BufferedImage image;

    public Entity() {
        x = 0;
        y = 0;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics2D g2) throws IOException {
        g2.drawImage(image, x, y, Game.tileSize, Game.tileSize, null);
    }

    public Rectangle getPos() {
        Rectangle pos = new Rectangle(x, y, Game.tileSize, Game.tileSize);
        return pos;
    }
}
