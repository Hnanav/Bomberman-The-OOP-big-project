package bomberman.bomberman.Entities.Dynamic;

import bomberman.bomberman.Entities.Entity;

import java.awt.image.BufferedImage;

public class DynamicEntity extends Entity{
    protected int speed;
    String direction;
    protected BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4,
                            right1, right2, right3, right4;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    public DynamicEntity(int x, int y){
        super(x, y);
    }
    public DynamicEntity(int x, int y, int speed){
        super(x, y);
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
