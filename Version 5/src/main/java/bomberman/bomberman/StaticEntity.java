package bomberman.bomberman;

import java.awt.image.BufferedImage;

public class StaticEntity extends Entity{
    boolean isDestroyable;
    StaticEntity(int x, int y, BufferedImage bufferedImage, boolean isDestroyable) {
        super(x, y);
        image = bufferedImage;
        this.isDestroyable = true;
    }

    boolean IsDestroyable() {
        return isDestroyable;
    }
}
