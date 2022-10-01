package bomberman.bomberman;

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

        bomb_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_1.png"));

        bomb_exploded_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_1.png"));
        explosion_horizontal_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_1.png"));
        explosion_horizontal_left_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_1.png"));
        explosion_horizontal_right_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_1.png"));
        explosion_vertical_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_1.png"));
        explosion_vertical_top_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_1.png"));
        explosion_vertical_down_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_1.png"));
    }
}
