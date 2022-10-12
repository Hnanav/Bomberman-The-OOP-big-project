package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.skeletal_beast;

public class SkeletalAxolot extends BackgroundEntity{
    SkeletalAxolot(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    void getImage() {
        image = skeletal_beast;
    }
}

