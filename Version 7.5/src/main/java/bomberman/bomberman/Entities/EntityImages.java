package bomberman.bomberman.Entities;

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
    public static BufferedImage bomber_up_1, bomber_up_2, bomber_up_3, bomber_up_4, bomber_down_1, bomber_down_2,
            bomber_down_3, bomber_down_4, bomber_left_1, bomber_left_2, bomber_left_3, bomber_left_4,
            bomber_right_1, bomber_right_2, bomber_right_3, bomber_right_4,
            bomber_death_fly, bomber_death;

    public static BufferedImage axolot_up_1, axolot_up_2, axolot_up_3, axolot_up_4, axolot_down_1, axolot_down_2,
            axolot_down_3, axolot_down_4, axolot_left_1, axolot_left_2, axolot_left_3, axolot_left_4,
            axolot_right_1, axolot_right_2, axolot_right_3, axolot_right_4;

    public static BufferedImage beast_up_1, beast_up_2, beast_up_3, beast_up_4, beast_down_1, beast_down_2,
            beast_down_3, beast_down_4, beast_left_1, beast_left_2, beast_left_3, beast_left_4,
            beast_right_1, beast_right_2, beast_right_3, beast_right_4;

    public static BufferedImage snake_up_1, snake_up_2, snake_up_3, snake_up_4, snake_down_1, snake_down_2,
            snake_down_3, snake_down_4, snake_left_1, snake_left_2, snake_left_3, snake_left_4,
            snake_right_1, snake_right_2, snake_right_3, snake_right_4;

    public static BufferedImage bomb;
    public static BufferedImage bomb_exploded, explosion_horizontal, explosion_horizontal_left_last,
            explosion_horizontal_right_last, explosion_vertical, explosion_vertical_down_last,
            explosion_vertical_top_last;
    public static BufferedImage skeletal_beast, skeletal_axolot, skeletal_snake;
    public EntityImages() throws IOException {
        try {
            floor = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Background/floor.png")));
            brick = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Background/brick.png")));
            wall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Background/wall.png")));

            bomber_up_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_up_1.png")));
            bomber_up_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_up_2.png")));
            bomber_up_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_up_3.png")));
            bomber_up_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_up_4.png")));
            bomber_down_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_down_1.png")));
            bomber_down_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_down_2.png")));
            bomber_down_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_down_3.png")));
            bomber_down_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_down_4.png")));
            bomber_left_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_left_1.png")));
            bomber_left_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_left_2.png")));
            bomber_left_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_left_3.png")));
            bomber_left_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_left_4.png")));
            bomber_right_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_right_1.png")));
            bomber_right_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_right_2.png")));
            bomber_right_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_right_3.png")));
            bomber_right_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_right_4.png")));
            bomber_death_fly = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_death_fly.png")));
            bomber_death = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomber/bomber_death.png")));

            axolot_up_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_1.png")));
            axolot_up_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_2.png")));
            axolot_up_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_3.png")));
            axolot_up_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_4.png")));
            axolot_down_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_1.png")));
            axolot_down_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_2.png")));
            axolot_down_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_3.png")));
            axolot_down_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_4.png")));
            axolot_left_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_1.png")));
            axolot_left_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_2.png")));
            axolot_left_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_3.png")));
            axolot_left_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_4.png")));
            axolot_right_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_1.png")));
            axolot_right_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_2.png")));
            axolot_right_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_3.png")));
            axolot_right_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_4.png")));

            beast_up_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_up_1.png")));
            beast_up_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_up_2.png")));
            beast_up_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_up_3.png")));
            beast_up_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_up_4.png")));
            beast_down_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_down_1.png")));
            beast_down_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_down_2.png")));
            beast_down_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_down_3.png")));
            beast_down_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_down_4.png")));
            beast_left_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_left_1.png")));
            beast_left_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_left_2.png")));
            beast_left_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_left_3.png")));
            beast_left_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_left_4.png")));
            beast_right_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_right_1.png")));
            beast_right_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_right_2.png")));
            beast_right_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_right_3.png")));
            beast_right_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Beast/Beast_right_4.png")));

            snake_up_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_1.png")));
            snake_up_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_2.png")));
            snake_up_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_3.png")));
            snake_up_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_4.png")));
            snake_down_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_1.png")));
            snake_down_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_2.png")));
            snake_down_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_3.png")));
            snake_down_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_4.png")));
            snake_left_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_1.png")));
            snake_left_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_2.png")));
            snake_left_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_3.png")));
            snake_left_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_4.png")));
            snake_right_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_1.png")));
            snake_right_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_2.png")));
            snake_right_3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_3.png")));
            snake_right_4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_4.png")));

            power_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Item/power_up.png")));
            bomb_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Item/bomb_up.png")));
            speed_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Item/speed_up.png")));
            speed_down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Item/speed_down.png")));

            bomb = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/bomb.png")));
            bomb_exploded = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/bomb_exploded.png")));
            explosion_horizontal = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/explosion_horizontal.png")));
            explosion_horizontal_left_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/explosion_horizontal_left_last.png")));
            explosion_horizontal_right_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/explosion_horizontal_right_last.png")));
            explosion_vertical = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/explosion_vertical.png")));
            explosion_vertical_top_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/explosion_vertical_top_last.png")));
            explosion_vertical_down_last = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Bomb/explosion_vertical_down_last.png")));

            skeletal_snake = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Skeletal/skeletal_snake.png")));
            skeletal_beast = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Skeletal/skeletal_beast.png")));
            skeletal_axolot = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Skeletal/skeletal_axolot.png")));
        } catch (IOException e) {
            throw new RemoteException();
        }
    }
}
