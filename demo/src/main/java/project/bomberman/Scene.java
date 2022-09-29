package project.bomberman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;

public class Scene {
    static void Up() {
        System.out.println("Movin UP");
    }

    static void Down() {
        System.out.println("Movin DOWN");
    }

    static void Left() {
        System.out.println("Movin LEFT");
    }

    static void Right() {
        System.out.println("Movin RIGHT");
    }
}
