package bomberman.bomberman;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private final List<Item> items;

    public ItemList() {
        items = new ArrayList<>();
    }

    public void add(Item item) {
        items.add(item);
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
}
