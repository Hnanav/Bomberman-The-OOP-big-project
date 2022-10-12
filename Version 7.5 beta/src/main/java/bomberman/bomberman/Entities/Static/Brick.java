package bomberman.bomberman;

import bomberman.bomberman.Entities.StaticEntity;

import static bomberman.bomberman.EntityImages.brick;

public class Brick extends StaticEntity {
    Brick(int x, int y) {
        super(x, y);
        getImage();
        isDestroyable = true;
    }

    boolean IsDestroyable() {
        return isDestroyable;
    }

    @Override
    void getImage() {
        image = brick;
    }
}
