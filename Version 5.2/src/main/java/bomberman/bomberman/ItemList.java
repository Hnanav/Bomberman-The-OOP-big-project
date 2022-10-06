package bomberman.bomberman;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private List<Item> items;
    ItemList() {
        items = new ArrayList<>();
    }

    void add(Item item) {
        items.add(item);
    }

    void update() {

    }
}
