package project.bomberman;

import static project.bomberman.Constant.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    char[][] map = new char[20][20];
    Canvas canvas = new Canvas(SCREEN_SIZEX, SCREEN_SIZEY);
    GraphicsContext gc;
    Image img2 = new Image("Wall.png");
    Image img3 = new Image("Grass.png");
    static List<Entity> StaticEntities = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setX(SCREEN_POSX);
        stage.setY(SCREEN_POSY);

        creatmap();
        drawmap();

        Player bomber = new Player(0, 0, 5, 5, "bomber");
        bomber.render(gc);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                bomber.handleEvent(event);
                gc.clearRect(0, 0, SCREEN_SIZEX, SCREEN_SIZEY);
                drawmap();
                bomber.render(gc);
            }
        });
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
    }

    void creatmap() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++)
                map[i][j] = ' ';
        }

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                int tam = (int) (Math.random() * 3);
                if (tam == 1 && i != 0 && j != 0) {
                    Entity obj = new Entity(j * 50, i * 40, "wall.png");
                    StaticEntities.add(obj);
                    map[i][j] = '*';
                }
            }
        }
    }

    void drawmap() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map[i][j] == '*') gc.drawImage(img2, j * SIZEX, i * SIZEY, SIZEX, SIZEY);
                else gc.drawImage(img3, j * SIZEX, i * SIZEY, SIZEX, SIZEY);
            }
        }
    }
}
