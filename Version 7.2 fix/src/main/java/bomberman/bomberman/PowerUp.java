package bomberman.bomberman;

import static bomberman.bomberman.EntityImages.power_up;

public class PowerUp extends Item{
    public PowerUp(int x, int y) {
        super(x, y);
        image = power_up;
        type = 1;
    }
}
