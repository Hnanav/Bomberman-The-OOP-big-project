package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.skeletal_snake;

public class SkeletalSnake extends BackgroundEntity{
    SkeletalSnake(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    void getImage() {
        image = skeletal_snake;
    }
}
