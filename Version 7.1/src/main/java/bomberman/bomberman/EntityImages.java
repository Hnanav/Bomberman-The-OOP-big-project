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
    public static BufferedImage bomber_death_fly, bomber_death;
    public static BufferedImage bomb;
    public static BufferedImage bomb_exploded, explosion_horizontal, explosion_horizontal_left_last,
            explosion_horizontal_right_last, explosion_vertical, explosion_vertical_down_last,
            explosion_vertical_top_last;
    public EntityImages() throws IOException {
        try {
            floor = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/floor.png")));
            brick = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/brick.png")));
            wall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/wall.png")));

            bomber_death_fly = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_death_fly.png")));
            bomber_death = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_death.png")));

            power_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/power_up.png")));
            bomb_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_up.png")));
            speed_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/speed_up.png")));
            speed_down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/speed_down.png")));

            bomb = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb.png")));

            bomb_exploded = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomb_exploded.png")));
            explosion_horizontal = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal.png")));
            explosion_horizontal_left_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_left_last.png")));
            explosion_horizontal_right_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_horizontal_right_last.png")));
            explosion_vertical = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical.png")));
            explosion_vertical_top_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_top_last.png")));
            explosion_vertical_down_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/explosion_vertical_down_last.png")));
        } catch (IOException e) {
            throw new RemoteException();
        }
    }
}
