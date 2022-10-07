package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.*;

public class SpeedUp extends Item{
    SpeedUp(int x, int y) {
        super(x, y);
        image = speed_up;
        type = 3;
    }
}
