package bomberman.bomberman;

import static bomberman.bomberman.Game.entityImages;

public class IncreaseBomb extends Item{
    IncreaseBomb(int x, int y) {
        super(x, y);
        image = entityImages.increase_bomb;
        type = 2;
    }
}
