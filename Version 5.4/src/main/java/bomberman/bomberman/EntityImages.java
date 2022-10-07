package bomberman.bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

public class EntityImages {
    public static BufferedImage floor;
    public static BufferedImage brick;
    public static BufferedImage wall;
    public static BufferedImage power_up, bomb_up, speed_up, speed_down;
    public static BufferedImage bomb_1, bomb_2, bomb_3;
    public static BufferedImage bomb_exploded_1, explosion_horizontal_1, explosion_horizontal_left_last_1,
                         explosion_horizontal_right_last_1, explosion_vertical_1, explosion_vertical_down_last_1,
                         explosion_vertical_top_last_1;

    public static BufferedImage bomb_exploded_2, explosion_horizontal_2, explosion_horizontal_left_last_2,
            explosion_horizontal_right_last_2, explosion_vertical_2, explosion_vertical_down_last_2,
            explosion_vertical_top_last_2;

    public static BufferedImage bomb_exploded_3, explosion_horizontal_3, explosion_horizontal_left_last_3,
            explosion_horizontal_right_last_3, explosion_vertical_3, explosion_vertical_down_last_3,
            explosion_vertical_top_last_3;


    public EntityImages() throws IOException {
        try {
            floor = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/floor.png")));
            brick = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/brick.png")));
            wall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/wall.png")));

            power_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/power_up.png")));
            bomb_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_up.png")));
            speed_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/speed_up.png")));
            speed_down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/speed_down.png")));

            bomb_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_1.png")));
            bomb_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_2.png")));
            bomb_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_3.png")));

            bomb_exploded_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_1.png")));
            explosion_horizontal_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_1.png")));
            explosion_horizontal_left_last_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_1.png")));
            explosion_horizontal_right_last_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_1.png")));
            explosion_vertical_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_1.png")));
            explosion_vertical_top_last_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_1.png")));
            explosion_vertical_down_last_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_1.png")));

            bomb_exploded_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_2.png")));
            explosion_horizontal_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_2.png")));
            explosion_horizontal_left_last_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_2.png")));
            explosion_horizontal_right_last_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_2.png")));
            explosion_vertical_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_2.png")));
            explosion_vertical_top_last_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_2.png")));
            explosion_vertical_down_last_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_2.png")));

            bomb_exploded_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded_3.png")));
            explosion_horizontal_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_3.png")));
            explosion_horizontal_left_last_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last_3.png")));
            explosion_horizontal_right_last_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last_3.png")));
            explosion_vertical_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_3.png")));
            explosion_vertical_top_last_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last_3.png")));
            explosion_vertical_down_last_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last_3.png")));
        } catch (IOException e) {
            throw new RemoteException();
        }
    }
}