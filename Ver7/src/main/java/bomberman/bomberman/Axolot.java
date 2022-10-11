package bomberman.bomberman;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;

public class Axolot extends DynamicEntity {
    private boolean isDied;
    private static boolean status;

    public Axolot(int x, int y, int speed) throws IOException {
        super(x, y, speed);
        direction = setDirection();
        getAxolotImage();
        isDied = false;
        status = true;
    }

    public String setDirection() {

        int i = y / tileSize;
        int j = x / tileSize;
        //System.out.println(i + " " + j);

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
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_up_4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_down_4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_left_4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Axolot/Axolot_right_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void update_(int level) {
        status = false;
        int far = 4;
        /*if (level <= 30) far = 5;
        else if (level <= 40) far = 10;
        else if (level <= 50) far = 15;*/
        List<StaticEntity> walls = staticEntityList.getDesEntity();
        List<StaticEntity> bricks = staticEntityList.getUndesEntity();
        List<Bomb> Bombs = bombList.getBombs();
        Rectangle now = new Rectangle(x, y, tileSize, tileSize);
        if (far < 5) {
            while (true) {
                boolean dd = false;
                int nextx = ThreadLocalRandom.current().nextInt(1, 20) * tileSize;
                int nexty = ThreadLocalRandom.current().nextInt(1, 20) * tileSize;
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
                    if (nextx <= 3 * i.getLevel() * tileSize + i.x &&
                            nextx >= i.x - 3 * i.getLevel() * tileSize &&
                            nexty <= 3 * i.getLevel() * tileSize + i.y &&
                            nexty >= i.y - 3 * i.getLevel() * tileSize) {dd = true; break;}
                }

                if (dd) continue;
                for (Snake i : snaker)
                    if (Collision(i.getPos(), next)) {dd = true; break;}

                if (dd) continue;
                for (Beast i : beasts)
                    if (Collision(i.getPos(), next)) {dd = true; break;}

                if (dd) continue;
                x = nextx; y = nexty; break;
            }
        }
    }
    public void update(int level) {
        status = true;
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
                if (ndirection == "up") cnt ++;
                if (ndirection == "down") cnt ++;
                if (ndirection == "left") cnt ++;
                if (ndirection == "down") cnt ++;
                boolean ft = true;
                Rectangle now = new Rectangle();
                switch (ndirection) {
                    case "up":
                        y = y - speed;
                        break;
                    case "down":
                        y = y + speed;
                        break;
                    case "left":
                        x = x - speed;
                        break;
                    case "right":
                        x = x + speed;
                        break;
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
                    for (Snake i : snaker) {
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
                    if (direction == nowdirection) {
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
                        case 1:
                            ndirection = "up";
                            break;
                        case 2:
                            ndirection = "down";
                            break;
                        case 3:
                            ndirection = "left";
                            break;
                        case 4:
                            ndirection = "right";
                            break;
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