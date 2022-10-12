package bomberman.bomberman.Entities.Item;

import static bomberman.bomberman.Entities.EntityImages.speed_down;

public class SpeedDown extends Item{
    SpeedDown(int x, int y) {
        super(x, y);
        image = speed_down;
        type = 4;
    }
}
