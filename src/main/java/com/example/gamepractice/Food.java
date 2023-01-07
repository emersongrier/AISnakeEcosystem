package com.example.gamepractice;


public class Food
{
    int xpos;
    int ypos;

    Board b1;

    public Food(Board b1)
    {
        this.b1 = b1;
    }

    /**
     * Using the foods board param, it makes sure the food is placed in an empty slot
     */
    public void createFood()
    {

        while(true)
        {
            this.xpos = (int) Math.floor(Math.random()*b1.boardWidth);
            this.ypos = (int) Math.floor(Math.random()*b1.boardHeight);

            if(b1.arrBoard[ypos][xpos]==0)
            {
                break;
            }
        }
    }
}
