package bomberman.bomberman;

import static bomberman.bomberman.Game.*;

import javax.imageio.ImageIO;

public class Brick extends StaticEntity{
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
        image = entityImages.brick;
    }
}