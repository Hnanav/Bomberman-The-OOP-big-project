package bomberman.bomberman;

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
    List<Integer> next_bomb = new ArrayList<>();
    Game gp;
    KeyHandler input;

    public Bomber(int x, int y, int speed, Game gp, KeyHandler input) throws IOException {
        super(x, y, speed);
        this.gp = gp;
        this.input = input;
        level_bomb = 1;
        max_bomb = 5;
        num_bomb = 5;
        direction = "right";
        getBomberImage();
    }

    private void getBomberImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_up_3.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_down_3.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_left_3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/bomber_right_3.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
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
            } else if (input.leftPressed) {
                direction = "left";
                x -= speed;
            }

            if (x < 0 || x > screenWidth - tileSize) x = luuX;
            if (y < 0 || y > screenHeight - tileSize) y = luuY;
            for (Entity i : StaticEntities) {
                if (Collision(this.getPos(), i.getPos())) {
                    x = luuX;
                    y = luuY;
                    break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 7) {
                spriteNum = (spriteNum % 3) + 1;
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
            Bomb bomb = new Bomb(x, y, entityImages.bomb_1, level_bomb);
            Bombs.add(bomb);
            num_bomb--;
            next_bomb.add(100);
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
                break;
        }
        g2.drawImage(image, x, y, tileSize, tileSize, null);
    }
}
