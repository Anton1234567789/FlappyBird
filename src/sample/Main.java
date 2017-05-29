package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.birds.Bird;
import sample.birds.Wall;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    public static Pane appRoot = new Pane(); // само приложение
    public static Pane gameRoot = new Pane(); // поле где будут все стены

    public static ArrayList<Wall> walls = new ArrayList<>(); // список стен

    Bird bird = new Bird(); //птица
    public  static int score = 0;  // отображение счета
    public Label scoreLabel = new Label("Score: " + score ); // вывод счета на лейбл


    public Parent createContent(){   // создание сцены передаем в конструктор сцены

        gameRoot.setPrefSize(600,600);

        for (int i = 0; i< 100; i++){

            int enter = (int)(Math.random()*100+50); // 50 - 150
            int height = new Random().nextInt(600-enter); // высота первой стенки

            Wall wall = new Wall(height);
            wall.setTranslateX(i*350+600);
            wall.setTranslateY(0);
            walls.add(wall);

            Wall wall2 = new Wall(600-enter-height);
            wall2.setTranslateX(i*350+600);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall,wall2);

        }

        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot, scoreLabel);

        return appRoot;
    }

    public void update(){

        if (bird.velocity.getY()<5){
            bird.velocity = bird.velocity.add(0,1);
        }

        bird.moveX((int)bird.velocity.getX());
        bird.moveY((int)bird.velocity.getY());
        scoreLabel.setText("Score: "+ score);

        bird.translateXProperty().addListener((oba,old , newValue)->{
            int offset = newValue.intValue();

            if (offset > 200)gameRoot.setLayoutX(-(offset-200));


        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        Scene scene = new Scene(createContent());
        scene.setOnMouseClicked(event -> bird.jamp()); // при клике на сцену будет прыжок птицы
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {  // вызывается каждый кадр
                update();
            }
        };
        timer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
