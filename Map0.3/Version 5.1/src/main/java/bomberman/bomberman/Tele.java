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

public class Tele extends DynamicEntity {
    private boolean isDied;

    public Tele(int x, int y) throws IOException {
        super(x, y);
        direction = "up";
        isDied = false;
        getTeleImage();
    }

    public boolean isDied() {
        return isDied;
    }
    private void getTeleImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Tele_up_1")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Tele_up_2")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Tele_up_3")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Tele_up_4")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Tele_down_1")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Utils/Sprites/Tele_down_2")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
       spriteNum = spriteNum % 7 + 1;
    }
    public void render(Graphics2D g2) {
        image = null;
        if (direction == "up") {
            if (spriteNum == 1) {
                image = up1;
            } else if (spriteNum == 2) {
                image = up2;
            } else if (spriteNum == 3) {
                image = up3;
            } else if (spriteNum == 4) {
                image = up4;
            } else if (spriteNum == 5) {
                image = down1;
            } else if (spriteNum == 6) {
                image = down2;
            }
            if (spriteNum == 6) isDied = true;
        }
        g2.drawImage(image, x, y, tileSize, tileSize , null);
    }
}

