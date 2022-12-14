package bomberman.bomberman.Entities.Dynamic;

import static bomberman.bomberman.Entities.EntityImages.*;
import static bomberman.bomberman.Algorithm.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;
import bomberman.bomberman.Entities.BombExplosion.BombExplosion;
import bomberman.bomberman.Entities.Static.*;
import bomberman.bomberman.Entities.Bomb.Bomb;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Axolot extends DynamicEntity {
    private boolean isDied;
    private static boolean status;

    public Axolot(int x, int y, int speed) {
        super(x, y, speed);
        direction = setDirection();
        getAxolotImage();
        isDied = false;
        status = true;
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

        return "down";
    }
    private void getAxolotImage() {
        up1 = axolot_up_1; up2 = axolot_up_2; up3 = axolot_up_3; up4 = axolot_up_4;
        down1 = axolot_down_1; down2 = axolot_down_2; down3 = axolot_down_3; down4 = axolot_down_4;
        left1 = axolot_left_1; left2 = axolot_left_2; left3 = axolot_left_3; left4 = axolot_left_4;
        right1 = axolot_right_1; right2 = axolot_right_2; right3 = axolot_right_3; right4 = axolot_right_4;
    }
    public boolean getStatus() {
        return status;
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
                //System.out.println(222);
                isDied = true;
                break;
            }
        }
        return isDied;
    }

    public void update_() {
        status = false;
        List<StaticEntity> walls = staticEntityList.getDesEntity();
        List<StaticEntity> bricks = staticEntityList.getUndesEntity();
        List<Bomb> Bombs = bombList.getBombs();
        Rectangle now = new Rectangle(x, y, tileSize, tileSize);
        int mx = 0;
        while (true) {
            boolean dd = false; mx ++;
            if (mx > 1000) break;
            int nextx = ThreadLocalRandom.current().nextInt(1, 19) * tileSize;
            int nexty = ThreadLocalRandom.current().nextInt(1, 19) * tileSize;
            Rectangle next = new Rectangle(nextx, nexty, tileSize, tileSize);
            if (Collision(next, now)) continue;
            for (Pair<Integer, Integer> i : Cannot) {
                if (i.equals(new Pair<>(nextx / tileSize, nexty / tileSize))) {
                    dd = true; break;
                }
            }
            if (dd) continue;
            for (Axolot i : axolots)
                if (this != i) {
                    if (Collision(i.getPos(), next)) {dd = true; break;}
                }
            if (dd) continue;
            for (StaticEntity i : walls)
                if (Collision(i.getPos(), next)) { dd = true; break;}
            if (dd) continue;

            for (StaticEntity i : bricks)
                if (Collision(i.getPos(), next)) { dd = true; break;}

            if (dd) continue;
            for (Bomb i : Bombs) {
                if (Collision(i.getPos(), next)) { dd = true; break; }
                if (nextx <= 3 * i.getLevel() * tileSize + i.getPos().getX() &&
                        nextx >= i.getPos().getX() - 3 * i.getLevel() * tileSize &&
                        nexty <= 3 * i.getLevel() * tileSize + i.getPos().getY() &&
                        nexty >= i.getPos().getY() - 3 * i.getLevel() * tileSize) {dd = true; break;}
            }

            if (dd) continue;
            for (Snake i : snakes)
                if (Collision(i.getPos(), next)) {dd = true; break;}

            if (dd) continue;
            for (Beast i : beasts)
                if (Collision(i.getPos(), next)) {dd = true; break;}

            if (dd) continue;
            x = nextx; y = nexty; break;
        }
    }
    public void update(int level) {
        status = true;
        if (level <= 50) {
            List<StaticEntity> walls = staticEntityList.getDesEntity();
            List<StaticEntity> bricks = staticEntityList.getUndesEntity();
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
                    for (Beast i : beasts) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                    }
                }
                if (ft) {
                    for (Axolot i : axolots)
                        if (this != i) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {ft = false; x = luux; y = luuy; break;}
                    }
                }
                if (ft) {
                    check = true;
                    direction = ndirection;
                    if (direction.equals(nowdirection)) {
                        spriteCounter++;
                        if (spriteCounter > 3) {
                            spriteNum = (spriteNum % 4) + 1;
                            spriteCounter = 0;
                        }
                    }
                    else spriteNum = 1;
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
    public void render_(Graphics2D g2) {

        g2.drawImage(image, x, y, tileSize, tileSize , null);
    }
}
