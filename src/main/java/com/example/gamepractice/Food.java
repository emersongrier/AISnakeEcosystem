package com.example.gamepractice;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle
{
    double xpos;
    double ypos;

    public Food(Snake s1)
    {
        super(s1.segmentSizeX,s1.segmentSizeY);
        this.setFill(Color.RED);
        this.setStroke(Color.WHITE);
    }


    public void createFood(Snake s1)//may need to change what items are attributes of what.
    {
        this.xpos = (Math.floor(Math.random()*s1.boardWidth)*s1.segmentSizeX);
        this.ypos = (Math.floor(Math.random()*s1.boardHeight)*s1.segmentSizeY);

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
