package com.example.gamepractice;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class test123a extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    Group root;

    double SceneWidth = 600;//400
    double SceneHeight = 600;//400

    int BoardWidth = 30;//20
    int BoardHeight = 30;//20

    Timeline animation;


    /*
    NOTES:

    Create a Simulation class, that deals with both endgame, reset game,
    as well as multiple snakes, foods, the board, generations, etc

    can make

    Need to look at end game method, and alter or remove it
    Need to alter Snake checkAte()
    Need to alter Snake isDead()

    Eventually alter board methods

     */

    Simulation MainGame;

    @Override
    public void start(Stage primaryStage)
    {

        root = new Group();
        Scene scene = new Scene(root, SceneWidth, SceneHeight);
        scene.setFill(Color.FORESTGREEN);//Color.FORESTGREEN

        MainGame = new Simulation(20,2,BoardWidth,BoardHeight,SceneWidth,SceneHeight,root);


        animation = new Timeline(new KeyFrame(Duration.millis(50), this::handle));//
        animation.setCycleCount(Timeline.INDEFINITE);//look into lambda button expressions
        animation.play();


        primaryStage.setScene(scene);//setup
        primaryStage.show();//setup

    }

    @Override
    public void handle(ActionEvent event)
    {
        MainGame.updateGame();

    }


}
