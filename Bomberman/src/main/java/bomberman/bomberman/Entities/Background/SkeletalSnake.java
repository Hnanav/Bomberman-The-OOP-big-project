package bomberman.bomberman.Entities.Background;

import static bomberman.bomberman.Entities.EntityImages.*;

public class SkeletalSnake extends BackgroundEntity{
    public SkeletalSnake(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    public void getImage() {
        image = skeletal_snake;
    }
}
