package bomberman.bomberman.Entities.Static;

import bomberman.bomberman.Entities.Entity;

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
