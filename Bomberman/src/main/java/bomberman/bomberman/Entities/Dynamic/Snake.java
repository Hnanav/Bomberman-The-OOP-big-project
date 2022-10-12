package bomberman.bomberman.Entities.Dynamic;

import javafx.scene.shape.Rectangle;
import javafx.util.Pair;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import static bomberman.bomberman.Entities.Dynamic.AIEnemy.checkMove;
import static bomberman.bomberman.Algorithm.CheckCollision.Collision;
import static bomberman.bomberman.Entities.EntityImages.*;
import static bomberman.bomberman.Game.*;
import bomberman.bomberman.Entities.BombExplosion.BombExplosion;
import bomberman.bomberman.Entities.Static.*;
import bomberman.bomberman.Entities.Bomb.Bomb;

public class Snake extends DynamicEntity {
    private boolean isDied;
    private final Queue<Pair<Integer, Integer>> pre = new LinkedList<>();
    public Snake(int x, int y, int speed) {
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
        up1 = snake_up_1; up2 = snake_up_2; up3 = snake_up_3; up4 = snake_up_4;
        down1 = snake_down_1; down2 = snake_down_2; down3 = snake_down_3; down4 = snake_down_4;
        left1 = snake_left_1; left2 = snake_left_2; left3 = snake_left_3; left4 = snake_left_4;
        right1 = snake_right_1; right2 = snake_right_2; right3 = snake_right_3; right4 = snake_right_4;
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

    public void update(int level, int bx, int by) {
        if (level <= 20) {
            List<StaticEntity> walls = staticEntityList.getDesEntity();
            List<StaticEntity> bricks = staticEntityList.getUndesEntity();
            //BombExplosions;
            boolean check = false;
            String ndirection = direction;
            String nowdirection = direction;
            int cnt = 0;
            while (!check) {
                int luux = x, luuy = y;
                if (ndirection.equals("up")) cnt++;
                if (ndirection.equals("down")) cnt++;
                if (ndirection.equals("left")) cnt++;
                if (ndirection.equals("down")) cnt++;
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
                    ft = false;
                    x = luux;
                    y = luuy;
                }
                if (ft)
                    if (y < 0 || y > screenHeight - tileSize) {
                        ft = false;
                        x = luux;
                        y = luuy;
                    }
                if (ft)
                    for (StaticEntity i : walls) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {
                            ft = false;
                            x = luux;
                            y = luuy;
                            break;
                        }
                    }
                if (ft)
                    for (StaticEntity i : bricks) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {
                            ft = false;
                            x = luux;
                            y = luuy;
                            break;
                        }
                    }
                if (ft)
                    for (BombExplosion i : BombExplosions) {
                        Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                        Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                        if (Collision(now, next1)) {
                            ft = false;
                            break;
                        }
                        if (Collision(now, next2)) {
                            ft = false;
                            break;
                        }
                    }
                if (ft) {
                    List<Bomb> Bombs = bombList.getBombs();
                    for (Bomb i : Bombs) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {
                            ft = false;
                            x = luux;
                            y = luuy;
                            break;
                        }
                    }
                }
                if (ft) {
                    for (Snake i : snakes)
                        if (this != i) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {
                                ft = false;
                                x = luux;
                                y = luuy;
                                break;
                            }
                        }
                }
                if (ft) {
                    for (Beast i : beasts) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {
                            ft = false;
                            x = luux;
                            y = luuy;
                            break;
                        }
                    }
                }
                if (ft) {
                    for (Axolot i : axolots) {
                        Rectangle next = i.getPos();
                        if (Collision(now, next)) {
                            ft = false;
                            x = luux;
                            y = luuy;
                            break;
                        }
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
                    } else spriteNum = 1;
                }
                if (!ft) {
                    if (cnt > 4) {
                        x = luux;
                        y = luuy;
                        check = true;
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
        /**.*/
        else {
            boolean posIn = false;
            Rectangle now = new Rectangle(x, y, tileSize, tileSize);
            for (Pair<Integer, Integer> i : Cannot) {
                Rectangle next = new Rectangle(i.getKey(), i.getValue(), tileSize, tileSize);
                if (Collision(now, next)) {
                    posIn = true;
                    break;
                }
            }
            if (posIn) {
                List<StaticEntity> walls = staticEntityList.getDesEntity();
                List<StaticEntity> bricks = staticEntityList.getUndesEntity();
                boolean check = false;
                String ndirection = direction;
                String nowdirection = direction;
                int cnt = 0;
                while (!check) {
                    int luux = x, luuy = y;
                    if (ndirection.equals("up")) cnt++;
                    if (ndirection.equals("down")) cnt++;
                    if (ndirection.equals("left")) cnt++;
                    if (ndirection.equals("down")) cnt++;
                    boolean ft = true;
                    switch (ndirection) {
                        case "up" -> y = y - speed;
                        case "down" -> y = y + speed;
                        case "left" -> x = x - speed;
                        case "right" -> x = x + speed;
                    }
                    now = new Rectangle(x, y, tileSize, tileSize);
                    if (x < 0 || x > screenWidth - tileSize) {
                        ft = false;
                        x = luux;
                        y = luuy;
                    }
                    if (ft)
                        if (y < 0 || y > screenHeight - tileSize) {
                            ft = false;
                            x = luux;
                            y = luuy;
                        }
                    if (ft)
                        for (StaticEntity i : walls) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {
                                ft = false;
                                x = luux;
                                y = luuy;
                                break;
                            }
                        }
                    if (ft)
                        for (StaticEntity i : bricks) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {
                                ft = false;
                                x = luux;
                                y = luuy;
                                break;
                            }
                        }

                    if (ft) {
                        for (Snake i : snakes)
                            if (this != i) {
                                Rectangle next = i.getPos();
                                if (Collision(now, next)) {
                                    ft = false;
                                    x = luux;
                                    y = luuy;
                                    break;
                                }
                            }
                    }

                    if (ft) {
                        for (Beast i : beasts) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {
                                ft = false;
                                x = luux;
                                y = luuy;
                                break;
                            }
                        }
                    }
                    if (ft) {
                        for (Axolot i : axolots) {
                            Rectangle next = i.getPos();
                            if (Collision(now, next)) {
                                ft = false;
                                x = luux;
                                y = luuy;
                                break;
                            }
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
                        } else spriteNum = 1;
                    }
                    if (!ft) {
                        if (cnt > 4) {
                            x = luux;
                            y = luuy;
                            check = true;
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
            } else {
                boolean check = false; boolean make = false;
                String nowdirection = direction;
                int limits = Math.min(speed * 4, 10);
                int lowx = Math.max(bx - limits, 0);
                int lowy = Math.max(by - limits, 0);
                int highx = Math.min(bx + limits, 19 * tileSize - 1);
                int highy = Math.min(by + limits, 19 * tileSize - 1);
                for (int i = lowx; i < highx; i += 2) {
                    for (int j = lowy; j < highy; j += 2) {
                        if (checkMove(x - speed, y, i, j, speed, this)) {
                            boolean hi = true;
                            for (Pair<Integer, Integer>  ii : pre)
                                if (ii.equals(new Pair<>(x + speed, y))) {
                                    hi = false; break;
                                }
                            if (hi) {
                                x -= speed;
                                direction = "left";
                                check = true;
                                break;
                            }
                        }
                        if (checkMove(x + speed, y, i, j, speed, this)) {
                            boolean hi = true;
                            for (Pair<Integer, Integer>  ii : pre)
                                if (ii.equals(new Pair<>(x + speed, y))) {
                                    hi = false; break;
                                }
                            if (hi) {
                                x += speed;
                                direction = "right";
                                check = true;
                                break;
                            }
                        }
                        if (checkMove(x, y - speed, i, j, speed, this)) {
                            boolean hi = true;
                            for (Pair<Integer, Integer>  ii : pre)
                                if (ii.equals(new Pair<>(x, y - speed))) {
                                    hi = false; break;
                                }
                            if (hi){
                                y -= speed;
                                direction = "up";
                                check = true;
                                break;
                            }
                        }
                        if (checkMove(x, y + speed, i, j, speed, this)) {
                            boolean hi = true;
                            for (Pair<Integer, Integer>  ii : pre)
                                if (ii.equals(new Pair<>(x, y + speed))) {
                                    hi = false; break;
                                }
                            if (hi) {
                                y += speed;
                                direction = "down";
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                }
                if (check) {
                    make = true;
                    if (direction.equals(nowdirection)) {
                        spriteCounter++;
                        if (spriteCounter > 3) {
                            spriteNum = (spriteNum % 4) + 1;
                            spriteCounter = 0;
                        }
                    } else {spriteNum = 1;spriteCounter = 0;}
                }
                else {
                    List<StaticEntity> walls = staticEntityList.getDesEntity();
                    List<StaticEntity> bricks = staticEntityList.getUndesEntity();
                    check = false;
                    String ndirection = direction;
                    nowdirection = direction;
                    int cnt = 0;
                    while (!check) {
                        int luux = x, luuy = y;
                        if (ndirection.equals("up")) cnt++;
                        if (ndirection.equals("down")) cnt++;
                        if (ndirection.equals("left")) cnt++;
                        if (ndirection.equals("down")) cnt++;
                        boolean ft = true;
                        switch (ndirection) {
                            case "up" -> y = y - speed;
                            case "down" -> y = y + speed;
                            case "left" -> x = x - speed;
                            case "right" -> x = x + speed;
                        }
                        now = new Rectangle(x, y, tileSize, tileSize);
                        if (x < 0 || x > screenWidth - tileSize) {
                            ft = false;
                            x = luux;
                            y = luuy;
                        }
                        if (ft)
                            if (y < 0 || y > screenHeight - tileSize) {
                                ft = false;
                                x = luux;
                                y = luuy;
                            }
                        if (ft)
                            for (StaticEntity i : walls) {
                                Rectangle next = i.getPos();
                                if (Collision(now, next)) {
                                    ft = false;
                                    x = luux;
                                    y = luuy;
                                    break;
                                }
                            }
                        if (ft)
                            for (StaticEntity i : bricks) {
                                Rectangle next = i.getPos();
                                if (Collision(now, next)) {
                                    ft = false;
                                    x = luux;
                                    y = luuy;
                                    break;
                                }
                            }
                        if (ft)
                            for (BombExplosion i : BombExplosions) {
                                Rectangle next1 = new Rectangle(i.getLeft_pole(), i.getRight_pole(), tileSize, tileSize);
                                Rectangle next2 = new Rectangle(i.getUp_pole(), i.getDown_pole(), tileSize, tileSize);
                                if (Collision(now, next1)) {
                                    ft = false;
                                    break;
                                }
                                if (Collision(now, next2)) {
                                    ft = false;
                                    break;
                                }
                            }
                        if (ft) {
                            List<Bomb> Bombs = bombList.getBombs();
                            for (Bomb i : Bombs) {
                                Rectangle next = i.getPos();
                                if (Collision(now, next)) {
                                    ft = false;
                                    x = luux;
                                    y = luuy;
                                    break;
                                }
                            }
                        }
                        if (ft) {
                            for (Snake i : snakes)
                                if (this != i) {
                                    Rectangle next = i.getPos();
                                    if (Collision(now, next)) {
                                        ft = false;
                                        x = luux;
                                        y = luuy;
                                        break;
                                    }
                                }
                        }
                        if (ft) {
                            for (Beast i : beasts) {
                                Rectangle next = i.getPos();
                                if (Collision(now, next)) {
                                    ft = false;
                                    x = luux;
                                    y = luuy;
                                    break;
                                }
                            }
                        }
                        if (ft) {
                            for (Axolot i : axolots) {
                                Rectangle next = i.getPos();
                                if (Collision(now, next)) {
                                    ft = false;
                                    x = luux;
                                    y = luuy;
                                    break;
                                }
                            }
                        }
                        if (ft) {
                            for (Pair<Integer, Integer>  ii : pre)
                                if (ii.equals(new Pair<>(x, y))) {
                                    ft = false; break;
                                }
                        }
                        if (ft) make = true;
                        if (ft) {
                            check = true;
                            direction = ndirection;
                            if (direction.equals(nowdirection)) {
                                spriteCounter++;
                                if (spriteCounter > 3) {
                                    spriteNum = (spriteNum % 4) + 1;
                                    spriteCounter = 0;
                                }
                            } else spriteNum = 1;
                        }
                        if (!ft) {
                            if (cnt > 4) {
                                x = luux;
                                y = luuy;
                                check = true;
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
                if (make && !pre.isEmpty() && pre.size() > 10) {
                    pre.remove();
                    pre.add(new Pair<>(x, y));
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
