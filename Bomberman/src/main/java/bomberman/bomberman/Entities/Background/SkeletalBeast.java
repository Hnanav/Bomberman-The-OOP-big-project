package bomberman.bomberman.Entities.Background;

import static bomberman.bomberman.Entities.EntityImages.*;

public class SkeletalBeast extends BackgroundEntity{
    public SkeletalBeast(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    public void getImage() {
        image = skeletal_beast;
    }
}
