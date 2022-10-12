package bomberman.bomberman.Entities.Background;

import static bomberman.bomberman.Entities.EntityImages.*;

public class Floor extends BackgroundEntity {
    public Floor(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    public void getImage() {
        image = floor;
    }
}
