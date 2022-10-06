package bomberman.bomberman;

import static bomberman.bomberman.Game.*;

import java.awt.*;

public class Item extends Entity{
    protected int vertical;
    protected int type;

    public Item(int x, int y) {
        super(x, y);
        vertical = 0;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image, x, y, tileSize, tileSize, null);
        vertical = (++vertical >= 20) ? 0 : vertical;
    }
}
