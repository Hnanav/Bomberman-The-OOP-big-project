package bomberman.bomberman;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;

public class Snake extends DynamicEntity {
    private boolean isDied;

    public Snake(int x, int y, int speed) throws IOException {
        super(x, y, speed);
        direction = setDirection();
        getSnakeImage();
        isDied = false;
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

        return "right";
    }
    private void getSnakeImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_up_4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_down_4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_left_4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake/Snake_right_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                System.out.println(222);
                isDied = true;
                break;
            }
            else {
                //System.out.println(111);
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
                    for (Snake i : snaker)
                        if (this != i) {
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
                    for (Axolot i : axolots) {
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
                            //System.out.println("loz");
                            break;
                        case 4:
                            ndirection = "right";
                            //System.out.println("lozzzz");
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
}
