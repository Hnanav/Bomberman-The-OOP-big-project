package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.speed_up;

public class SpeedUp extends Item{
    SpeedUp(int x, int y) {
        super(x, y);
        image = speed_up;
        type = 3;
    }
}
