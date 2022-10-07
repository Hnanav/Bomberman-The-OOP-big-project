package bomberman.bomberman;

import java.awt.*;

import static bomberman.bomberman.Game.tileSize;

public class Item extends Entity{
    protected int variable;
    protected int delay_Time;
    boolean inverse;
    protected int type;

    public Item(int x, int y) {
        super(x, y);
        variable = 0;
        delay_Time = 10;
        inverse = true;
    }

    public int getType() {
        return type;
    }

    protected void update() {
        if (variable < 0 ) System.out.println(variable);
        if (inverse) {
            variable++;
        }
        else {
            variable--;
        }

        if (variable > 5) {
            inverse = false;
        }

        if (variable == 0) {
            inverse = true;
        }

    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(image, x, y - variable, tileSize, tileSize, null);
        if (delay_Time == 0) {
            update();
            delay_Time = 10;
        }
        else delay_Time--;
    }
}
