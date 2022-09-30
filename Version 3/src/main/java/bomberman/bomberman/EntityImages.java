package bomberman.bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EntityImages {
    public BufferedImage grass;
    public BufferedImage wall;
    public BufferedImage bomb_1;

    public EntityImages() throws IOException {
        grass = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/grass.png"));
        wall = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/wall.png"));
        bomb_1 = ImageIO.read(getClass().getResourceAsStream("/Utils/Sprites/bomb_1.png"));
    }
}
