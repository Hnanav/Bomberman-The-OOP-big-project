package bomberman.bomberman;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static bomberman.bomberman.Game.*;

public class ItemList {
    private final List<Item> items;

    public ItemList() {
        items = new ArrayList<>();
    }

    public void sinh() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == ' ' && !itemed[i][j]) {
                    int rand = ((int) (Math.random() * 1000000)) + 1;
                    if (rand == 1) {
                        Item item = new PowerUp(j * tileSize, i * tileSize);
                        itemed[i][j] = true;
                        items.add(item);
                    }
                    else if (rand == 2) {
                        Item item = new BombUp(j * tileSize, i * tileSize);
                        itemed[i][j] = true;
                        items.add(item);
                    }
                    else if (rand == 3) {
                        Item item = new SpeedUp(j * tileSize, i * tileSize);
                        itemed[i][j] = true;
                        items.add(item);
                    }
                    else if (rand == 4) {
                        Item item = new SpeedDown(j * tileSize, i * tileSize);
                        itemed[i][j] = true;
                        items.add(item);
                    }
                }
            }
        }
    }

    public void remove(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void render(Graphics2D g2) {
        for (Item i : items) {
            i.render(g2);
        }
    }

    public void clear() {
        items.clear();
    }
}
