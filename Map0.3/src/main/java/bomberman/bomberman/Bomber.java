package bomberman.bomberman;

import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.Game.*;

public class Bomber extends DynamicEntity{

    int level_bomb;
    int max_bomb;
    int num_bomb;
    boolean isDied;
    List<Integer> next_bomb = new ArrayList<>();
    Game gp;
    KeyHandler input;

    public Bomber(int x, int y, int speed, Game gp, KeyHandler input) throws IOException {
        super(x, y, speed);
        this.gp = gp;
        this.input = input;
        level_bomb = 2;
        max_bomb = 5;
        num_bomb = 3;
        direction = "right";
        getBomberImage();
        isDied = false;
    }

    private void getBomberImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_4.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_4.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_4.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDied() {
        return isDied;
    }

    public void update() {
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

        if (input.upPressed || input.downPressed || input.leftPressed || input.rightPressed) {
            int luuX = x;
            int luuY = y;
            if (input.upPressed) {
                direction = "up";
                y -= speed;
            } else if (input.downPressed) {
                direction = "down";
                y += speed;
            } else if (input.rightPressed) {
                direction = "right";
                x += speed;
            } else {
                direction = "left";
                x -= speed;
            }

            if (x < 0 || x > screenWidth - tileSize) x = luuX;
            if (y < 0 || y > screenHeight - tileSize) y = luuY;

            for (Entity i : staticEntityList.getDesEntity()) {
                if (Collision(this.getPos(), i.getPos())) {
                    x = luuX;
                    y = luuY;
                    break;
                }
            }

            for (Entity i : staticEntityList.getUndesEntity()) {
                if (Collision(this.getPos(), i.getPos())) {
                    x = luuX;
                    y = luuY;
                    break;
                }
            }

            for (Entity i : bombList.Bombs) {
                Rectangle rec1 = this.getPos();
                Rectangle rec2 = new Rectangle(luuX, luuY, tileSize, tileSize);
                Rectangle rectangle = i.getPos();
                if (Collision(rec1, rectangle) && Collision(rec2, rectangle) == false) {
                    x = luuX;
                    y = luuY;
                    break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 7) {
                spriteNum = (spriteNum % 4) + 1;
                spriteCounter = 0;
            }
        }
        else {
            spriteNum = 1;
        }

        int order = 0;
        while (order < next_bomb.size()) {
            int tam = next_bomb.get(order);
            if (tam == 0) {
                next_bomb.remove(order);
                num_bomb++;
            }
            else {
                order++;
            }
        }

        if (input.spacePressed && num_bomb > 0) {
            int vtX = Math.round(((float) x) / 40);
            int vtY = Math.round(((float) y) / 40);
            if (bombed[vtX][vtY] == false) {
                Bomb bomb = new Bomb(vtX * tileSize,  vtY * tileSize, entityImages.bomb_1, level_bomb);
                bombList.add(bomb);
                num_bomb--;
                next_bomb.add(bomb_time);
                bombed[vtX][vtY] = true;
            }
        }

        if (next_bomb.isEmpty() == false) {
            for (int k = 0; k < next_bomb.size(); k++) {
                int tam = next_bomb.get(k);
                tam--;
                next_bomb.set(k, tam);
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
