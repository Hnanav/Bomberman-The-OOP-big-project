package bomberman.bomberman;

import java.awt.*;
import java.awt.image.BufferedImage;

import static bomberman.bomberman.Game.*;

public class BombExplosion extends Entity{
    private final int level;
    private int left_pole, right_pole, up_pole, down_pole;
    private int cout_down;
    private boolean complete;
    private BufferedImage center, horizontal, last_left, last_right, vertical, last_top, last_down;

    public int getLeft_pole() {
        return left_pole;
    }

    public int getRight_pole() {
        return right_pole;
    }

    public int getUp_pole() {
        return up_pole;
    }

    public int getDown_pole() {
        return down_pole;
    }

    public BombExplosion (int x, int y, int level) {
        super(x, y);
        this.level = level;
        this.cout_down = explosion_time;
        complete = false;
        getPoles();

        switch (level) {
            case 1 -> {
                center = entityImages.bomb_exploded_1;
                horizontal = entityImages.explosion_horizontal_1;
                last_left = entityImages.explosion_horizontal_left_last_1;
                last_right = entityImages.explosion_horizontal_right_last_1;
                vertical = entityImages.explosion_vertical_1;
                last_top = entityImages.explosion_vertical_top_last_1;
                last_down = entityImages.explosion_vertical_down_last_1;
            }
            case 2 -> {
                center = entityImages.bomb_exploded_2;
                horizontal = entityImages.explosion_horizontal_2;
                last_left = entityImages.explosion_horizontal_left_last_2;
                last_right = entityImages.explosion_horizontal_right_last_2;
                vertical = entityImages.explosion_vertical_2;
                last_top = entityImages.explosion_vertical_top_last_2;
                last_down = entityImages.explosion_vertical_down_last_2;
            }
            case 3 -> {
                center = entityImages.bomb_exploded_3;
                horizontal = entityImages.explosion_horizontal_3;
                last_left = entityImages.explosion_horizontal_left_last_3;
                last_right = entityImages.explosion_horizontal_right_last_3;
                vertical = entityImages.explosion_vertical_3;
                last_top = entityImages.explosion_vertical_top_last_3;
                last_down = entityImages.explosion_vertical_down_last_3;
            }
            default -> {
            }
        }
    }

    public void getPoles() {
        int vtX = x / tileSize;
        int vtY = y / tileSize;
        left_pole = (vtX - level) * tileSize;
        right_pole = (vtX + level) * tileSize;
        up_pole = (vtY - level) * tileSize;
        down_pole = (vtY + level) * tileSize;

        for (int i = 1; i <= level; i++) {
            int x1 = vtX - i;

            if (x1 < 0) {
                left_pole = 0;
                break;
            }
            else if (map[vtY][x1] == '#') {
                left_pole = (x1 + 1) * tileSize;
                break;
            }
            else if (map[vtY][x1] == '*') {
                left_pole = (x1 + 1) * tileSize;
                staticEntityList.remove_des(x1 * tileSize, y);
                map[vtY][x1] = ' ';
                BackgroundEntity backgroundEntity = new Floor(x1 * tileSize, y);
                BackgroundEntities.add(backgroundEntity);
                break;
            }
        }

        for (int i = 1; i <= level; i++) {
            int x2 = vtX + i;

            if (x2 >= screenCol) {
                right_pole = (screenWidth - 1) * tileSize;
                break;
            }
            else if (map[vtY][x2] == '#') {
                right_pole = (x2 - 1) * tileSize;
                break;
            }
            else if (map[vtY][x2] == '*') {
                right_pole = (x2 - 1) * tileSize;
                staticEntityList.remove_des(x2 * tileSize, y);
                map[vtY][x2] = ' ';
                BackgroundEntity backgroundEntity = new Floor(x2 * tileSize, y);
                BackgroundEntities.add(backgroundEntity);
                break;
            }
        }

        for (int i = 1; i <= level; i++) {
            int y1 = vtY - i;

            if (y1 < 0) {
                up_pole = 0;
                break;
            }
            else if (map[y1][vtX] == '#') {
                up_pole = (y1 + 1) * tileSize;
                break;
            }
            else if (map[y1][vtX] == '*') {
                up_pole = (y1 + 1) * tileSize;
                staticEntityList.remove_des(x, y1 * tileSize);
                map[y1][vtX] = ' ';
                BackgroundEntity backgroundEntity = new Floor(x, y1 * tileSize);
                BackgroundEntities.add(backgroundEntity);
                break;
            }
        }

        for (int i = 1; i <= level; i++) {
            int y2 = vtY + i;

            if (y2 >= screenRow) {
                down_pole = (screenHeight - 1) * tileSize;
                break;
            }
            else if (map[y2][vtX] == '#') {
                down_pole = (y2 - 1) * tileSize;
                break;
            }
            else if (map[y2][vtX] == '*') {
                down_pole = (y2 - 1) * tileSize;
                staticEntityList.remove_des(x, y2 * tileSize);
                map[y2][vtX] = ' ';
                BackgroundEntity backgroundEntity = new Floor(x, y2 * tileSize);
                BackgroundEntities.add(backgroundEntity);
                break;
            }
        }
    }

    public void render(Graphics2D g2) {
        if (cout_down > 0) {
            g2.drawImage(center, x, y, tileSize, tileSize, null);

            int vtX = x / 40;
            int vtY = y / 40;
            int left = left_pole / tileSize;
            int right = right_pole / tileSize;
            int up = up_pole / tileSize;
            int down = down_pole / tileSize;

            for (int i = vtX - 1; i > left; i--) {
                g2.drawImage(horizontal, i * tileSize, y, tileSize, tileSize, null);
            }
            if (vtX > left) {
                if (vtX - left == level) {
                    g2.drawImage(last_left, left_pole, y, tileSize, tileSize, null);
                } else {
                    g2.drawImage(horizontal, left_pole, y, tileSize, tileSize, null);
                }
            }

            for (int i = vtX + 1; i < right; i++) {
                g2.drawImage(horizontal, i * tileSize, y, tileSize, tileSize, null);
            }
            if (right > vtX) {
                if (right - vtX == level) {
                    g2.drawImage(last_right, right_pole, y, tileSize, tileSize, null);
                } else {
                    g2.drawImage(horizontal, right_pole, y, tileSize, tileSize, null);
                }
            }

            for (int i = vtY - 1; i > up; i--) {
                g2.drawImage(vertical, x, i * tileSize, tileSize, tileSize, null);
            }
            if (vtY > up) {
                if (vtY - up == level) {
                    g2.drawImage(last_top, x, up_pole, tileSize, tileSize, null);
                } else {
                    g2.drawImage(vertical, x, up_pole, tileSize, tileSize, null);
                }
            }

            for (int i = vtY + 1; i < down; i++) {
                g2.drawImage(vertical, x, i * tileSize, tileSize, tileSize, null);
            }
            if (down > vtY) {
                if (down - vtY == level) {
                    g2.drawImage(last_down, x, down_pole, tileSize, tileSize, null);
                } else {
                    g2.drawImage(vertical, x, down_pole, tileSize, tileSize, null);
                }
            }

            cout_down--;
        }

        else  {
            complete = true;
        }
    }

    public boolean isComplete() {
        return complete;
    }

}
