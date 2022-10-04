package bomberman.bomberman;

import static bomberman.bomberman.Game.*;

import java.io.IOException;

public class Wall extends StaticEntity{
    Wall(int x, int y) throws IOException {
        super(x, y);
        getImage();
        isDestroyable = false;
    }

    boolean IsDestroyable() {
        return isDestroyable;
    }

    @Override
    void getImage() throws IOException {
        image = entityImages.wall;
    }
}
