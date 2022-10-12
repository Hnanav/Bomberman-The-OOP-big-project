package bomberman.bomberman.Entities.Static;

import static bomberman.bomberman.Entities.EntityImages.wall;

public class Wall extends StaticEntity{
    public Wall(int x, int y) {
        super(x, y);
        getImage();
        isDestroyable = false;
    }

    public boolean IsDestroyable() {
        return isDestroyable;
    }

    @Override
    void getImage() {
        image = wall;
    }
}
