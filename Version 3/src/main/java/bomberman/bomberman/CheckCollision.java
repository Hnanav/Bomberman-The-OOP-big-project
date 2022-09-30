package bomberman.bomberman;

import javafx.scene.shape.Rectangle;

public class CheckCollision {
    public static boolean Collision(Rectangle a, Rectangle b) {
        int a1 = (int) a.getX();
        int a2 = (int) a.getX() + Game.tileSize;
        int a3 = (int) a.getY();
        int a4 = (int) a.getY() + Game.tileSize;
        int b1 = (int) b.getX();
        int b2 = (int) b.getX() + Game.tileSize;
        int b3 = (int) b.getY();
        int b4 = (int) b.getY() + Game.tileSize;
        if (a2 <= b1) return false;
        if (a1 >= b2) return false;
        if (a3 >= b4) return false;
        if (a4 <= b3) return false;
        return true;
    }
}
