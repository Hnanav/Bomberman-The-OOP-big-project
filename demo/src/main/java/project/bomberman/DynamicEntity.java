package project.bomberman;

import javafx.scene.image.Image;

public class DynamicEntity extends  Entity{
    protected int vx;
    protected int vy;
    protected int UpImageOrder;
    protected int DownImageOrder;
    protected int LeftImageOrder;
    protected int RightImageOrder;

    DynamicEntity(int x, int y, int vx, int vy, String path) {
        super(x, y);
        this.vx = vx;
        this.vy = vy;
        this.path = path;
    }

    protected void update(String source, String status, int order) {
        order++;
        order = (order) % 3 + 1;
        switch (status) {

            case "up":
                UpImageOrder = order;
                DownImageOrder = -1;
                LeftImageOrder = -1;
                RightImageOrder = -1;
                break;

            case "down":
                DownImageOrder = order;
                UpImageOrder = -1;
                LeftImageOrder = -1;
                RightImageOrder = -1;
                break;

            case "right":
                RightImageOrder = order;
                UpImageOrder = -1;
                DownImageOrder = -1;
                LeftImageOrder = -1;
                break;

            case "left":
                LeftImageOrder = order;
                UpImageOrder = -1;
                DownImageOrder = -1;
                RightImageOrder = -1;
                break;

        }
        String s = source + "_" + status + "_" + order + ".png";
        image = new Image(s);
    }
}
