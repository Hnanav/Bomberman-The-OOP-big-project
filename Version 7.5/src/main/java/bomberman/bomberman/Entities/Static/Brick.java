package bomberman.bomberman.Entities.Static;

import static bomberman.bomberman.Entities.EntityImages.brick;

public class Brick extends StaticEntity{
    public Brick(int x, int y) {
        super(x, y);
        getImage();
        isDestroyable = true;
    }

    public boolean IsDestroyable() {
        return isDestroyable;
    }

    @Override
    void getImage() {
        image = brick;
    }
}
