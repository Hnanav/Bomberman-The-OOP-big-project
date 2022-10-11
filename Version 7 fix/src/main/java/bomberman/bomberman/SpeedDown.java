package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.speed_down;

public class SpeedDown extends Item{
    SpeedDown(int x, int y) {
        super(x, y);
        image = speed_down;
        type = 4;
    }
}
