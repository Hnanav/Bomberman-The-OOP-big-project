package bomberman.bomberman.Entities.Background;

import static bomberman.bomberman.Entities.EntityImages.*;

public class SkeletalAxolot extends BackgroundEntity{
    public SkeletalAxolot(int x, int y) {
        super(x, y);
        getImage();
    }

    @Override
    public void getImage() {
        image = skeletal_beast;
    }
}

