package bomberman.bomberman;

import static bomberman.bomberman.Game.entityImages;

public class Floor extends BackgroundEntity{
    Floor(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    void getImage() {
        image = entityImages.floor;
    }
}
