package bomberman.bomberman;

import java.io.IOException;

public class StaticEntity extends Entity{
    boolean isDestroyable;
    StaticEntity(int x, int y) {
        super(x, y);
    }

    boolean IsDestroyable() {
        return isDestroyable;
    }

    void getImage() throws IOException {}
}
