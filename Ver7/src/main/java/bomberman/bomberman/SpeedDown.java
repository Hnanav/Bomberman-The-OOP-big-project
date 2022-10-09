package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.*;

public class SpeedDown extends Item{
    SpeedDown(int x, int y) {
        super(x, y);
        image = speed_down;
        type = 4;
    }
}
