package bomberman.bomberman.Entities;

import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static bomberman.bomberman.Game.tileSize;

public class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected BufferedImage image;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        width = tileSize;
        height = tileSize;
    }

    public void render(Graphics2D g2) throws IOException {
        g2.drawImage(image, x, y, width, height, null);
    }

    public Rectangle getPos() {
        return new Rectangle(x, y, width, height);
    }
}
