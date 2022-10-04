package MapTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EntityImages {
    public BufferedImage grass;
    public BufferedImage wall;
    public BufferedImage bomb_1;
    public BufferedImage bomb_exploded_1, explosion_horizontal_1, explosion_horizontal_left_last_1,
            explosion_horizontal_right_last_1, explosion_vertical_1, explosion_vertical_down_last_1,
            explosion_vertical_top_last_1;

    public EntityImages() throws IOException {
        grass = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/grass.png"));

        wall = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/wall.png"));


    }
}
