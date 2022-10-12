package bomberman.bomberman.Entities.Item;

import static bomberman.bomberman.Entities.EntityImages.power_up;

public class PowerUp extends Item{
    public PowerUp(int x, int y) {
        super(x, y);
        image = power_up;
        type = 1;
    }
}
