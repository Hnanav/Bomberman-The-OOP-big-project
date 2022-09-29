package project.bomberman;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import static project.bomberman.Main.*;
import static project.bomberman.checkCollision.*;
import static project.bomberman.Constant.*;

public class Player extends DynamicEntity{
    Player(int x, int y, int vx, int vy, String path) {
        super(x, y, vx, vy, path);
        image = new Image(path + "_right_1.png");
        UpImageOrder = -1;
        DownImageOrder = -1;
        LeftImageOrder = -1;
        RightImageOrder = 0;
    }

    void handleEvent(KeyEvent event) {
        switch (event.getCode()) {
            case W -> {
                y -= vy;
                if (y < 0) {
                    y += vy;
                }
                else {
                    for (Entity i : StaticEntities) {
                        if (Collision(this.getPos(), i.getPos()) == true) {
                            y += vy;
                            break;
                        }
                    }
                }
                update(path, "up", UpImageOrder);
            }
            case S -> {
                y += vy;
                if (y > SCREEN_SIZEY - SIZEY) {
                    y -= vy;
                }
                else {
                    for (Entity i : StaticEntities) {
                        if (Collision(this.getPos(), i.getPos()) == true) {
                            y -= vy;
                            break;
                        }
                    }
                }
                update(path, "down", DownImageOrder);
            }
            case D -> {
                x += vx;
                if (x > SCREEN_SIZEX - SIZEX) {
                    x -= vx;
                }
                else {
                    for (Entity i : StaticEntities) {
                        if (Collision(this.getPos(), i.getPos()) == true) {
                            x -= vx;
                            break;
                        }
                    }
                }
                update(path, "right", RightImageOrder);
            }
            case A -> {
                x -= vx;
                if (x < 0) {
                    x += vx;
                }
                else {
                    for (Entity i : StaticEntities) {
                        if (Collision(this.getPos(), i.getPos()) == true) {
                            x += vx;
                            break;
                        }
                    }
                }
                update(path, "left", LeftImageOrder);
            }
            default -> {
            }
        }
    }

}
