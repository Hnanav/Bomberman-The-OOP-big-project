package bomberman.bomberman.Entities.Dynamic;

import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static bomberman.bomberman.Entities.EntityImages.*;
import static bomberman.bomberman.Algorithm.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;
import bomberman.bomberman.Entities.BombExplosion.BombExplosion;
import bomberman.bomberman.Entities.Static.*;
import bomberman.bomberman.Entities.Bomb.Bomb;

public class Beast extends DynamicEntity {
    private boolean isDied;

    public Beast(int x, int y, int speed) {
        super(x, y, speed);
        direction = setDirection();
        getBeastImage();
        isDied = false;
    }
    public String setDirection() {

        int i = y / tileSize;
        int j = x / tileSize;

        if (j - 1 > 0)
            if (map[i][j - 1] == '.') return "left";
        if (j + 1 < 20)
            if (map[i][j + 1] == '.') return "right";
        if (i - 1 > 0)
            if (map[i - 1][j] == '.') return "down";
        if (i + 1 < 20)
            if (map[i + 1][j] == '.') return "up";
        return "right";
    }
    private void getBeastImage() {
        up1 = beast_up_1; up2 = beast_up_2; up3 = beast_up_3; up4 = beast_up_4;
        down1 = beast_down_1; down2 = beast_down_2; down3 = beast_down_3; down4 = beast_down_4;
        left1 = beast_left_1; left2 = beast_left_2; left3 = beast_left_3; left4 = beast_left_4;
        right1 = beast_right_1; right2 = beast_right_2; right3 = beast_right_3; right4 = beast_right_4;
    }
    public boolean isDied() {
        for (BombExplosion i : BombExplosions) {
            Rectangle rectangle = this.getPos();

            Rectangle pos = i.getPos();
            int left = i.getLeft_pole();
            int right = i.getRight_pole();
            int up = i.getUp_pole();
            int down = i.getDown_pole();

            Rectangle rec1_horizontal = new Rectangle(left, pos.getY(),
                    right - left + tileSize, tileSize);
            Rectangle rec1_vertical = new Rectangle(pos.getX(), up,
                    tileSize, down - up + tileSize);

            if (Collision(rectangle, rec1_horizontal) || Collision(rectangle, rec1_vertical)) {
                isDied = true;
                break;
            }

        }
        return isDied;
    }
    public void update(int level) {
        if (level <= 50) {
            List<StaticEntity> walls = staticEntityList.getDesEntity();
            List<StaticEntity> bricks = staticEntityList.getUndesEntity();
            //BombExplosions;
            boolean check = false;
            String ndirection = direction;
            String nowdirection = direction;
            int cnt = 0;
            while (!check) {
                int luux = x, luuy = y;
                if (ndirection.equals("up")) cnt ++;
                if (ndirection.equals("down")) cnt ++;
                if (ndirection.equals("left")) cnt ++;
                if (ndirection.equals("down")) cnt ++;
                boolean ft = true;
                Rectangle now;
                switch (ndirection) {
                    case "up" -> y = y - speed;
                    case "down" -> y = y + speed;
                    case "left" -> x = x - speed;
                    case "right" -> x = x + speed;
                }
                now = new Rectangle(x, y, tileSize, tileSize);
                if (x < 0 || x > screenWidth - tileSize) {
                    ft = false; x = luux; y = luuy;
                }
                if (ft)
                    if (y < 0 || y > screenHeight - tileSize) {
                        ft = false; x = luux; y = luuy;
                    }
                if (ft)
                    for (StaticEntity i : walls) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) { ft = false; x = luux; y = luuy; break;}
                    }
                if (ft)
                    for (StaticEntity i : bricks) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                    }
                if (ft)
                    for (BombExplosion i : BombExplosions) {
                        Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                        Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                        if (Collision(now, next1)) {ft = false; break;}
                        if (Collision(now, next2)) {ft = false; break;}
                    }
                if (ft) {
                    List<Bomb> Bombs = bombList.getBombs();
                    for (Bomb i : Bombs) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                    }
                }
                if (ft) {
                    for (Snake i : snakes) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                        }
                }
                if (ft) {
                    for (Beast i : beasts)
                        if (this != i) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                    }
                }
                if (ft) {
                    for (Axolot i : axolots) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                    }
                }
                if (ft) {
                    check = true;
                    direction = ndirection;
                    if (direction.equals(nowdirection)) {
                        spriteCounter++;
                        if (spriteCounter > 5) {
                            spriteNum = (spriteNum % 4) + 1;
                            spriteCounter = 0;
                        }
                    }
                    else {
                        spriteNum = 1;
                        spriteCounter = 0;
                    }
                }
                if (!ft) {
                    if (cnt > 4) {
                        x = luux; y = luuy; check = true;
                        break;
                    }
                    int pos = ThreadLocalRandom.current().nextInt(1, 5);
                    switch (pos) {
                        case 1 -> ndirection = "up";

                        case 2 -> ndirection = "down";

                        case 3 -> ndirection = "left";

                        case 4 -> ndirection = "right";

                    }
                }
            }
        }
    }
    public void render(Graphics2D g2) {
        image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
                else if (spriteNum == 3) {
                    image = up3;
                }
                else if (spriteNum == 4) {
                    image = up4;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                else if (spriteNum == 3) {
                    image = down3;
                }
                else if (spriteNum == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                else if (spriteNum == 3) {
                    image = left3;
                }
                else if (spriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
                else if (spriteNum == 3) {
                    image = right3;
                }
                else if (spriteNum == 4) {
                    image = right4;
                }
                break;
        }
        g2.drawImage(image, x, y, tileSize, tileSize , null);
    }
}
