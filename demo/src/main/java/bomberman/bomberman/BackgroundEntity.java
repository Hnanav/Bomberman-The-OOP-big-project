package bomberman.bomberman;

import java.awt.image.BufferedImage;

public class BackgroundEntity extends Entity{
    BackgroundEntity(int x, int y, BufferedImage bufferedImage) {
        super(x, y);
        image = bufferedImage;
    }
}
