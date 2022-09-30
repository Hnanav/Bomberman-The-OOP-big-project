package bomberman.bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DynamicEntity extends Entity{
    protected int speed;
    String direction;
    protected BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3,
                            right1, right2, right3;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    public DynamicEntity(int x, int y, int speed) throws IOException {
        super(x, y);
        this.speed = speed;
    }
}
