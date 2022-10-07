package bomberman.bomberman;

import static bomberman.bomberman.Game.entityImages;

public class IncreasePower extends Item{
    public IncreasePower(int x, int y) {
        super(x, y);
        image = entityImages.increase_power;
        type = 1;
    }
}
