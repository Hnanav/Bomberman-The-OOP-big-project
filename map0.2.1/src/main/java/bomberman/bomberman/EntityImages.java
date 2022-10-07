package bomberman.bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EntityImages {
    public BufferedImage floor;
    public BufferedImage brick;
    public BufferedImage wall;
    public BufferedImage bomb_1;
    public BufferedImage bomb_exploded_1, explosion_horizontal_1, explosion_horizontal_left_last_1,
                         explosion_horizontal_right_last_1, explosion_vertical_1, explosion_vertical_down_last_1,
                         explosion_vertical_top_last_1;

    public BufferedImage bomb_exploded_2, explosion_horizontal_2, explosion_horizontal_left_last_2,
            explosion_horizontal_right_last_2, explosion_vertical_2, explosion_vertical_down_last_2,
            explosion_vertical_top_last_2;

    public BufferedImage bomb_exploded_3, explosion_horizontal_3, explosion_horizontal_left_last_3,
            explosion_horizontal_right_last_3, explosion_vertical_3, explosion_vertical_down_last_3,
            explosion_vertical_top_last_3;


    public EntityImages() throws IOException {
        floor = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/floor.png"));

        brick = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/brick.png"));

        wall = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/wall.png"));

        bomb_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_1.png"));

        bomb_exploded_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_1.png"));
        explosion_horizontal_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_1.png"));
        explosion_horizontal_left_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_1.png"));
        explosion_horizontal_right_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_1.png"));
        explosion_vertical_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_1.png"));
        explosion_vertical_top_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_1.png"));
        explosion_vertical_down_last_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_1.png"));

        bomb_exploded_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_2.png"));
        explosion_horizontal_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_2.png"));
        explosion_horizontal_left_last_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_2.png"));
        explosion_horizontal_right_last_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_2.png"));
        explosion_vertical_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_2.png"));
        explosion_vertical_top_last_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_2.png"));
        explosion_vertical_down_last_2 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_2.png"));

        bomb_exploded_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_3.png"));
        explosion_horizontal_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_3.png"));
        explosion_horizontal_left_last_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_3.png"));
        explosion_horizontal_right_last_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_3.png"));
        explosion_vertical_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_3.png"));
        explosion_vertical_top_last_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_3.png"));
        explosion_vertical_down_last_3 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_3.png"));
    }
}
