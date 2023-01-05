package com.example.gamepractice;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class test123a extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        launch(args);
    }

    Snake s1;
    Food f1;

    Group root;

    double SceneWidth = 400;//400
    double SceneHeight = 400;//400

    int BoardWidth = 20;//20
    int BoardHeight = 20;//20

    Timeline animation;

    Text text;

    Board b1;


    @Override
    public void start(Stage primaryStage)
    {
        root = new Group();
        Scene scene = new Scene(root, SceneWidth, SceneHeight);
        scene.setFill(Color.FORESTGREEN);//Color.FORESTGREEN

        text = new Text("Press Enter To replay");
        text.setX(SceneWidth/2.0);
        text.setY(SceneHeight/2.0);

        b1 = new Board(BoardWidth,BoardHeight,SceneWidth,SceneHeight);

        s1 = new Snake(b1);
        f1 = new Food(b1,b1.segmentSizeX,b1.segmentSizeY);

        f1.createFood(s1);

        root.getChildren().add(s1.parts.get(0));
        root.getChildren().add(f1);

        //
        EventHandler<KeyEvent> keyhandler = new EventHandler<KeyEvent>(){

            public void handle(KeyEvent event){//needs rewrite
                    switch(event.getCode())
                    {
                        case UP:{if(s1.heading!=2){s1.heading = 0;}break;}
                        case DOWN:{if(s1.heading!=0){s1.heading = 2;}break;}
                        case RIGHT: {if(s1.heading!=3){s1.heading = 1;}break;}
                        case LEFT:{if(s1.heading!=1){s1.heading = 3;}break;}
                        case ENTER:{if(s1.isDead){resetGame();break;}}
                    }

            }
        };
        scene.setOnKeyPressed(keyhandler);
        //---------------------------------------

        animation = new Timeline(new KeyFrame(Duration.millis(70), this::handle));//add in buttons for speed up slow down;
        animation.setCycleCount(Timeline.INDEFINITE);//look into lambda button expressions
        animation.play();




        primaryStage.setScene(scene);
        primaryStage.show();




    }

    @Override
    public void handle(ActionEvent event)
    {
        if(!s1.checkAte(f1))//only if didnt eat, then checks for self collision. Do this because if ate,, g
        {
            s1.snakeUpdate();
            s1.isDead = s1.isDead();
            endGame(s1.isDead);
        }
        else
        {
            root.getChildren().add(s1.parts.get(s1.length-1));
        }
    }

    public void endGame(boolean isdead)
    {
        if(isdead)
        {

            animation.stop();
            root.getChildren().add(text);
        }
    }

    public void resetGame()
    {
        for(int i = s1.length-1;i>0;i--)
        {
            root.getChildren().remove(s1.parts.get(i));
            s1.parts.remove(i);
        }
        root.getChildren().remove(text);
        s1.length = 1;
        s1.isDead = false;
        s1.parts.get(0).setX(6*b1.segmentSizeX);
        s1.parts.get(0).setY(8*b1.segmentSizeY);
        s1.heading=1;
        f1.createFood(s1);
        animation.play();
    }
}
