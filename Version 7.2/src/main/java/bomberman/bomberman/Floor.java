package bomberman.bomberman;

import static bomberman.bomberman.LaunchMenu.*;

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
