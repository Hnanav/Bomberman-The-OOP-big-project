package project.bomberman;

import static project.bomberman.Constant.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Entity {
    protected  int x;
    protected  int y;
    protected String path;
    protected Image image;

    Entity(int x, int y) {
        x = 0;
        y = 0;
        path = "";
    }

    Entity (int x, int y, String path) {
        this.x = x;
        this.y = y;
        this.path = path;
        if (path != "") image = new Image(path);
    }
    protected void render(GraphicsContext gc) {
        gc.drawImage(image, x, y, SIZEX, SIZEY);
    }

    protected Rectangle getPos() {
        Rectangle a = new Rectangle(x, y, x + SIZEX, y + SIZEY);
        return a;
    }
}

