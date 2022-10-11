package bomberman.bomberman;

import javafx.scene.shape.Rectangle;

public class CheckCollision {
    public static boolean Collision(Rectangle a, Rectangle b) {
        int a1 = (int) a.getX();
        int a2 = (int) (a.getX() + a.getWidth());
        int a3 = (int) a.getY();
        int a4 = (int) (a.getY() + a.getHeight());
        int b1 = (int) b.getX();
        int b2 = (int) (b.getX() + b.getWidth());
        int b3 = (int) b.getY();
        int b4 = (int) (b.getY() + b.getHeight());

       // if (a1 >= b1 && a2 <= b2 && a3 >= b3 && a4 <= b4) return true;
       // if (b1 >= a1 && b2 <= a2 && b3 >= a3 && b4 <= a4) return true;
        if (a2 <= b1) return false;
        if (a1 >= b2) return false;
        if (a3 >= b4) return false;
        if (a4 <= b3) return false;

        return true;
    }
}
