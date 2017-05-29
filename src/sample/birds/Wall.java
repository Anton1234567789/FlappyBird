package sample.birds;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Wall extends Pane{
    Rectangle rect;
    public int heigh;

    public Wall(int heigh) {
        rect = new Rectangle(50,heigh);
        this.heigh = heigh;

        getChildren().add(rect);
    }

}
