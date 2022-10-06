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
    boolean isDied;
    Game gp;
    KeyHandler input;
    public Snake(int x, int y, int speed) throws IOException {
        super(x, y, speed);
        direction = setDirection();
        getSnakeImage();
        isDied = false;
    }
    public Snake(int x, int y, int speed, Game gp, KeyHandler input) throws IOException {
        super(x, y, speed);
        this.gp = gp;
        this.input = input;
        direction = setDirection();
        getSnakeImage();
        isDied = false;
    }
    public String setDirection() {
        /*for (int i = 0; i < 20; ++ i)
            for (int j = 0; j < 20; ++ j)
                if (map[i][j] == 'S') return null;*/
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
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_up_3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_up_4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_down_3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_down_4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_left_3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_left_4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_right_3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Snake_right_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isDied() {
        return isDied;
    }
    public void update(int level) {
        if (level <= 10) {
            List<StaticEntity> walls = staticEntityList.getDesEntity();
            List<StaticEntity> bricks = staticEntityList.getUndesEntity();
            //BombExplosions;
            boolean check = false;
            while (!check) {
                int luux = x, luuy = y; String ndirection = direction;
                boolean ft = true;
                Rectangle now = new Rectangle();
                /*switch (ndirection) {
                    case "up":
                        luuy = y - speed;
                        now = new Rectangle(luux, luuy, tileSize, tileSize);
                        for (StaticEntity i : walls) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (StaticEntity i : bricks) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (BombExplosion i : BombExplosions) {
                            Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                            Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                            if (Collision(now, next1)) {ft = false; break;}
                            if (Collision(now, next2)) {ft = false; break;}
                        }
                        if (ft) { check = true; direction = ndirection; y -= speed;}
                        break;
                    case "down":
                        luuy = y + speed;
                        now = new Rectangle(luux, luuy, tileSize, tileSize);
                        for (StaticEntity i : walls) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (StaticEntity i : bricks) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (BombExplosion i : BombExplosions) {
                            Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                            Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                            if (Collision(now, next1)) {ft = false; break;}
                            if (Collision(now, next2)) {ft = false; break;}
                        }
                        if (ft) { check = true; direction = ndirection; y += speed;}
                        break;
                    case "left":
                        luux = x - speed;
                        now = new Rectangle(luux, luuy, tileSize, tileSize);
                        for (StaticEntity i : walls) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (StaticEntity i : bricks) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (BombExplosion i : BombExplosions) {
                            Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                            Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                            if (Collision(now, next1)) {ft = false; break;}
                            if (Collision(now, next2)) {ft = false; break;}
                        }
                        if (ft) { check = true; direction = ndirection; x -= speed;}
                        break;
                    case "right":
                        luux = x + speed;
                        now = new Rectangle(luux, luuy, tileSize, tileSize);
                        for (StaticEntity i : walls) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (StaticEntity i : bricks) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {ft = false; break;}
                        }
                        for (BombExplosion i : BombExplosions) {
                            Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                            Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                            if (Collision(now, next1)) {ft = false; break;}
                            if (Collision(now, next2)) {ft = false; break;}
                        }
                        if (ft) { check = true; direction = ndirection; x += speed;}
                        break;
                }*/
                /** move tam. */
                switch (direction) {
                    case "up":
                        y = y - speed;
                        check = true;
                        break;
                    case "down":
                        y = y + speed;
                        check = true;
                        break;
                    case "left":
                        x = x - speed;
                        check = true;
                        break;
                    case "right":
                        x = x + speed;
                        check = true;
                        break;
                }
                int pos = ThreadLocalRandom.current().nextInt(1, 4);
                switch (pos) {
                    case 1:
                        ndirection = "up";
                        break;
                    case 2:
                        ndirection = "down";
                        break;
                    case 3:
                        ndirection = "left";
                    case 4:
                        ndirection = "right";
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
