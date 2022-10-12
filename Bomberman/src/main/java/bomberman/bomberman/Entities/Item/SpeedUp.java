package bomberman.bomberman.Entities.Item;

import static bomberman.bomberman.Entities.EntityImages.speed_up;

public class SpeedUp extends Item{
    SpeedUp(int x, int y) {
        super(x, y);
        image = speed_up;
        type = 3;
    }
}
