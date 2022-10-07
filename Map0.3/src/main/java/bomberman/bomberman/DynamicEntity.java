package bomberman.bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DynamicEntity extends Entity{
    protected int speed;
    String direction;
    protected BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4,
                            right1, right2, right3, right4;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public DynamicEntity(int x, int y) throws IOException {
        super(x, y);
    }
    public DynamicEntity(int x, int y, int speed) throws IOException {
        super(x, y);
        this.speed = speed;
    }
}
