package bomberman.bomberman;

import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static bomberman.bomberman.CheckCollision.Collision;
import static bomberman.bomberman.EntityImages.*;
import static bomberman.bomberman.Game.*;

public class Bomber extends DynamicEntity{

    private int level_bomb;
    private int num_bomb;
    private boolean isDied;
    private final List<Integer> next_bomb = new ArrayList<>();
    private final Game gp;
    private final KeyHandler input;
    private int fly_distance;
    private boolean fly_up;
    private final BufferedImage fly_death;
    private final BufferedImage death;

    public Bomber(int x, int y, int speed, Game gp, KeyHandler input) {
        super(x, y, speed);
        width = bomber_tileSize;
        height = bomber_tileSize;
        this.gp = gp;
        this.input = input;
        level_bomb = 1;
        num_bomb = 1;
        direction = "right";
        getBomberImage();
        isDied = false;
        fly_distance = 1;
        fly_up = true;
        fly_death = bomber_death_fly;
        death = bomber_death;
    }

    private void getBomberImage() {
        try {
            up1 = bomber_up_1;
            up2 = bomber_up_2;
            up3 = bomber_up_3;
            up4 = bomber_up_4;
            down1 = bomber_down_1;
            down2 = bomber_down_2;
            down3 = bomber_down_3;
            down4 = bomber_down_4;
            left1 = bomber_left_1;
            left2 = bomber_left_2;
            left3 = bomber_left_3;
            left4 = bomber_left_4;
            right1 = bomber_right_1;
            right2 = bomber_right_2;
            right3 = bomber_right_3;
            right4 = bomber_right_4;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDied() {
        return isDied;
    }

    public void update() {
        Rectangle rectangle = this.getPos();
        for (BombExplosion i : BombExplosions) {

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

        for (Snake i : snakes) {
            if (Collision(rectangle, i.getPos())) {
                isDied = true;
                break;
            }
        }

        for (Beast i : beasts) {
            if (Collision(rectangle, i.getPos())) {
                isDied = true;
                break;
            }
        }

        for (Axolot i : axolots) {
            if (Collision(rectangle, i.getPos())) {
                isDied = true;
                break;
            }
        }

        List<Item> items = itemList.getItems();
        for (Item i : items) {
            if (Collision(this.getPos(), i.getPos())) {
                switch (i.getType()) {
                    case 1 -> level_bomb = Math.min(++level_bomb, 3);
                    case 2 -> num_bomb = Math.min(++num_bomb, 5);
                    case 3 -> speed = Math.min(++speed, 8);
                    case 4 -> speed = Math.max(--speed, 1);
                }
                itemed[(int)i.getPos().getY() / tileSize][(int)i.getPos().getX() / tileSize] = false;
                itemList.remove(i);
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

            if (x < 0 || x > screenWidth - width) x = luuX;
            if (y < 0 || y > screenHeight - height) y = luuY;

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
                Rectangle rec2 = new Rectangle(luuX, luuY, width, height);
                Rectangle tam = i.getPos();
                if (Collision(rec1, tam) && !Collision(rec2, tam)) {
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
            spriteCounter = 0;
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
            int vtX = Math.round(((float) x) / tileSize);
            int vtY = Math.round(((float) y) / tileSize);
            if (!bombed[vtX][vtY]) {
                Bomb bomb = new Bomb(vtX * tileSize,  vtY * tileSize, level_bomb);
                bombList.add(bomb);
                num_bomb--;
                next_bomb.add(bomb_time);
                bombed[vtX][vtY] = true;
            }
        }

        if (!next_bomb.isEmpty()) {
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
        g2.drawImage(image, x, y, width, height, null);
    }

    public void render_death(Graphics2D g2) {
        if (fly_up) {
            g2.drawImage(fly_death, x, y - fly_distance, width, height, null);
            fly_distance += 1;
            fly_up = fly_distance <= 80;
        }
        else {
            fly_distance = (--fly_distance >= 0) ? fly_distance : 0;
            g2.drawImage(death, x, y - fly_distance, width, height, null);
            if(fly_distance==0) {
                finished = true;
            }
        }
    }
}
