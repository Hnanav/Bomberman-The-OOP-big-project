package bomberman.bomberman.Entities.Item;

import static bomberman.bomberman.Entities.EntityImages.bomb_up;

public class BombUp extends Item{
    BombUp(int x, int y) {
        super(x, y);
        image = bomb_up;
        type = 2;
    }
}
