package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.wall;

public class Wall extends StaticEntity{
    Wall(int x, int y) {
        super(x, y);
        getImage();
        isDestroyable = false;
    }

    boolean IsDestroyable() {
        return isDestroyable;
    }

    @Override
    void getImage() {
        image = wall;
    }
}
