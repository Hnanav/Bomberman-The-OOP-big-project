package bomberman.bomberman;

import static bomberman.bomberman.Game.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BombExplosion extends Entity{
    private int level;

    private int cout_down;

    private boolean complete;
    private BufferedImage center, horizontal, last_left, last_right, vertical, last_top, last_down;

    BombExplosion (int x, int y, int level) {
        super(x, y);
        this.level = level;
        this.cout_down = explosion_time;
        complete = false;

        if (level == 1) {
            center = entityImages.bomb_exploded_1;
            horizontal = entityImages.explosion_horizontal_1;
            last_left = entityImages.explosion_horizontal_left_last_1;
            last_right = entityImages.explosion_horizontal_right_last_1;
            vertical = entityImages.explosion_vertical_1;
            last_top = entityImages.explosion_vertical_top_last_1;
            last_down = entityImages.explosion_vertical_down_last_1;
        }
    }

    public void render(Graphics2D g2) {
        if (cout_down > 0) {
            g2.drawImage(center, x, y, tileSize, tileSize, null);
            for (int i = 1; i <= level; i++) {

                int x1 = x - (i * tileSize);
                int x2 = x + (i * tileSize);

                if (x1 >= 0) {
                    boolean renderable = true;

                    int vtX = x / tileSize;
                    int vtY = y / tileSize;
                    int vtX1 = x1 / tileSize;

                    for (int j = vtX - 1; j >= vtX1; j--)
                        if (map[vtY][j] == '*') {
                            renderable = false;
                            break;
                        }
                    if (renderable) {
                        if (i != level) {
                            g2.drawImage(horizontal, x1, y, tileSize, tileSize, null);
                        }
                        else  {
                            g2.drawImage(last_left, x1, y, tileSize, tileSize, null);
                        }
                    }
                }

                if (x2 <= screenWidth) {
                    boolean renderable = true;

                    int vtX = x / tileSize;
                    int vtY = y / tileSize;
                    int vtX2 = x2 / tileSize;

                    for (int j = vtX + 1; j <= vtX2; j++)
                        if (map[vtY][j] == '*') {
                            renderable = false;
                            break;
                        }
                    if (renderable) {
                        if (i != level) {
                            g2.drawImage(vertical, x2, y, tileSize, tileSize, null);
                        }
                        else {
                            g2.drawImage(last_right, x2, y, tileSize, tileSize, null);
                        }
                    }
                }


                int y1 = y - (i * tileSize);
                int y2 = y + (i * tileSize);

                if (y1 >= 0) {
                    boolean renderable = true;

                    int vtX = x / tileSize;
                    int vtY = y / tileSize;
                    int vtY1 = y1 / tileSize;

                    for (int j = vtY - 1; j >= vtY1; j--)
                        if (map[j][vtX] == '*') {
                            renderable = false;
                            break;
                        }
                    if (renderable) {
                        if (i != level) {
                            g2.drawImage(horizontal, x, y1, tileSize, tileSize, null);
                        }
                        else  {
                            g2.drawImage(last_top, x, y1, tileSize, tileSize, null);
                        }
                    }
                }

                if (y2 <= screenHeight) {
                    boolean renderable = true;

                    int vtX = x / tileSize;
                    int vtY = y / tileSize;
                    int vtY2 = y2 / tileSize;

                    for (int j = vtY + 1; j <= vtY2; j++)
                        if (map[j][vtX] == '*') {
                            renderable = false;
                            break;
                        }
                    if (renderable) {
                        if (i != level) {
                            g2.drawImage(vertical, x, y2, tileSize, tileSize, null);
                        }
                        else  {
                            g2.drawImage(last_down, x, y2, tileSize, tileSize, null);
                        }
                    }
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

    public int getLevel() {
        return level;
    }
}
