package bomberman.bomberman;

import static bomberman.bomberman.Game.*;

public class IncreaseSpeed extends Item{
    public IncreaseSpeed(int x, int y) {
        super(x, y);
        image = entityImages.increase_speed;
        type = 1;
    }
}
