package bomberman.bomberman.Entities;
import bomberman.bomberman.Entity;

public class StaticEntity extends Entity {
    boolean isDestroyable;
    StaticEntity(int x, int y) {
        super(x, y);
    }

    boolean IsDestroyable() {
        return isDestroyable;
    }

    void getImage() {}
}
