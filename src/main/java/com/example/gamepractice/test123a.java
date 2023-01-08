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
import javafx.scene.shape.Line;
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
    SimpleRay rayMan;

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


    @Override
    public void start(Stage primaryStage)
    {

        root = new Group();
        Scene scene = new Scene(root, SceneWidth, SceneHeight);
        scene.setFill(Color.FORESTGREEN);//Color.FORESTGREEN

        text = new Text("Press Enter To replay");//slated to be removed
        text.setX(SceneWidth/2.0);//slated to be removed
        text.setY(SceneHeight/2.0);//slated to be removed

        /*
        Game setup- move to sim class or main class
         */
        b1 = new Board(BoardWidth,BoardHeight,SceneWidth,SceneHeight,root);
        s1 = new Snake(b1);
        f1 = new Food(b1);
        f1.createFood();

        rayMan = new SimpleRay(s1, b1);




        /*

         */

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

        animation = new Timeline(new KeyFrame(Duration.millis(100), this::handle));//
        animation.setCycleCount(Timeline.INDEFINITE);//look into lambda button expressions
        animation.play();


        primaryStage.setScene(scene);//setup
        primaryStage.show();//setup

    }

    @Override
    public void handle(ActionEvent event)
    {

        b1.arrSetter(s1,f1);
        b1.colorSet();

        rayMan.rayFinder(true);

        for(int i =0; i<8;i++)
            System.out.print(rayMan.Rays[i]+"  ");
        System.out.println();

        if(!s1.checkAte(f1))
        {
            s1.snakeUpdate();
            s1.isDead = s1.isDead();
            endGame(s1.isDead);
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

    /**
     * reset state of game
     * must be altered to allow for more snakes, more food
     */
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
        s1.parts.get(0).setX(6);
        s1.parts.get(0).setY(8);
        s1.heading=1;
        f1.createFood();
        animation.play();
        System.out.println();//remove
    }
}
