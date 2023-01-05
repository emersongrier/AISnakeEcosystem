package com.example.gamepractice;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle
{
    double xpos;
    double ypos;

    Board b1;

    public Food(Board b1,double segmentSizeX, double segmentSizeY)
    {
        super(segmentSizeX,segmentSizeY);
        this.b1 = b1;
        this.setFill(Color.RED);
        this.setStroke(Color.WHITE);
    }


    public void createFood(Snake s1)//maybe place in a Game class that contains 1 or more snakes, for use of 2p or ecosystem
    {
        this.xpos = (Math.floor(Math.random()*b1.boardWidth)*b1.segmentSizeX);//some board specific data
        this.ypos = (Math.floor(Math.random()*b1.boardHeight)*b1.segmentSizeY);

        for(int i = 0; i<s1.length;i++)
        {
            if(!(this.xpos==s1.parts.get(i).getX()&&this.ypos==s1.parts.get(i).getY()))
            {
                this.setX(xpos);
                this.setY(ypos);
                break;
            }//makes sure that the food isnt intersecting snake before it spawns
        }//else, it retries until it isnt intersecitng
    }
}
