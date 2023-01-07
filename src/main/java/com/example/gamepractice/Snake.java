package com.example.gamepractice;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Snake
{
    int heading = 1;//snake
    int length = 1;//snake

    ArrayList<Point> parts;//snake specific

    Boolean isDead;//snake specific

    Board b1;

    public Snake(Board b1)
    {
        this.b1 = b1;
        parts = new ArrayList<Point>();
        parts.add(new Point(6,8));
    }


    /**
     * used on a snake, it updates the head base on its heading;
     * then, it updates each subsequent segment into the place held by its predesessor
     */
    public void snakeUpdate()
    {
        int tempx = parts.get(0).getX();
        int tempy = parts.get(0).getY();

        switch (heading)
        {
            case 0://updates head block based on heading
            {parts.get(0).setY(parts.get(0).getY()-1);break;}
            case 1:
            {parts.get(0).setX(parts.get(0).getX()+1);break;}
            case 2:
            {parts.get(0).setY(parts.get(0).getY()+1);break;}
            case 3:
            {parts.get(0).setX(parts.get(0).getX()-1);break;}
        }
        for(int i = 1; i<length;i++)//updates subsequent segments to move forward
        {
            int tempx2 = parts.get(i).getX();
            int tempy2 = parts.get(i).getY();
            parts.get(i).setX(tempx);
            parts.get(i).setY(tempy);
            tempx = tempx2;
            tempy = tempy2;
        }
    }


    /**
     * WARNING should recieve sim object so it my scan through ALL food
     * @param f1
     * @return true if the snake ate, false if otherwise
     */
    public boolean checkAte(Food f1)//this can recieve a gam sim that scans through all food on that game sim;
    {
        if(f1.ypos==this.parts.get(0).getY()&&f1.xpos==this.parts.get(0).getX())
        {
            f1.createFood();
            this.parts.add(new Point(parts.get(length-1).getX(),parts.get(length-1).getY()));//needs to update without doing the second part// split into two functions
            length++;
            return true;
        }
        else
            return false;
    }


    /**
     * WARNING! for and if must be changed, so its checked agaisnt all, not just itself
     * @return true if the snake should die, false if otherwise
     */
    public Boolean isDead()
    {
        if(this.parts.get(0).getX()<0+1||this.parts.get(0).getX()>=b1.boardWidth-1||this.parts.get(0).getY()<0+1||this.parts.get(0).getY()>=b1.boardHeight-1)
            return true;//if head outside of map it dies
        else
        {
            for(int i = 1; i<this.length;i++)
            {
                if(parts.get(i).getX()==parts.get(0).getX()&&parts.get(i).getY()==parts.get(0).getY())
                return true;
            }
        }
        return false;
    }


}
