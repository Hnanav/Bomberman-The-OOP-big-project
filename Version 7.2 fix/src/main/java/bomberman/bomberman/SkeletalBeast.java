package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.*;

public class SkeletalBeast extends BackgroundEntity{
    SkeletalBeast(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    void getImage() {
        image = skeletal_beast;
    }
}
