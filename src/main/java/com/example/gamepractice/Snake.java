package com.example.gamepractice;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Snake
{
    int heading = 1;
    int length = 1;

    ArrayList<Rectangle> parts;

    double segmentSizeX;
    double segmentSizeY;

    int boardWidth;
    int boardHeight;

    double sceneWidth;
    double sceneHeight;

    Boolean isDead;

    public Snake(int boardWidth, int boardHeight, double sceneWidth, double sceneHeight)
    {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        this.segmentSizeX = sceneWidth/boardWidth;
        this.segmentSizeY = sceneHeight/boardHeight;

        parts = new ArrayList<Rectangle>();
        parts.add(new Rectangle(6*segmentSizeX,8*segmentSizeY,segmentSizeX,segmentSizeY));
        parts.get(0).setStroke(Color.WHITE);
    }

    public void snakeUpdate()//somewehre this is the issue, i cant figure out why.
    {
        double tempx = parts.get(0).getX();
        double tempy = parts.get(0).getY();

        switch (heading)
        {
            case 0://updates head block based on heading
            {parts.get(0).setY(parts.get(0).getY()-segmentSizeY);break;}
            case 1:
            {parts.get(0).setX(parts.get(0).getX()+segmentSizeX);break;}
            case 2:
            {parts.get(0).setY(parts.get(0).getY()+segmentSizeY);break;}
            case 3:
            {parts.get(0).setX(parts.get(0).getX()-segmentSizeX);break;}
        }
        for(int i = 1; i<length;i++)//updates subsequent segments to move forward
        {
            double tempx2 = parts.get(i).getX();
            double tempy2 = parts.get(i).getY();
            parts.get(i).setX(tempx);
            parts.get(i).setY(tempy);
            tempx = tempx2;
            tempy = tempy2;
        }
    }

    public boolean checkAte(Food f1)
    {
        if(f1.ypos==this.parts.get(0).getY()&&f1.xpos==this.parts.get(0).getX())
        {
            f1.createFood(this);
            this.parts.add(new Rectangle(parts.get(length-1).getX(),parts.get(length-1).getY(),this.segmentSizeX,this.segmentSizeY));//needs to update without doing the second part// split into two functions
            length++;
            this.parts.get(length-1).setStroke(Color.WHITE);
            return true;
        }
        else
            return false;
    }

    public Boolean isDead()
    {
        if(this.parts.get(0).getX()<0||this.parts.get(0).getX()>=sceneWidth||this.parts.get(0).getY()<0||this.parts.get(0).getY()>=sceneHeight)
            return true;//if head outside of map it dies
        else
        {
            for(int i = 1; i<this.length;i++)//checks head agaisnt each segment
            {
                if(parts.get(i).getX()==parts.get(0).getX()&&parts.get(i).getY()==parts.get(0).getY())
                return true;
            }
        }
        return false;
    }


}
