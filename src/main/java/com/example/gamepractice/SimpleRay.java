package com.example.gamepractice;

public class SimpleRay
{
    Snake Snake;
    Board Board;

    int[] Rays;

    public SimpleRay(Snake s1,Board b1)
    {
        this.Snake = s1;
        this.Board = b1;
        Rays = new int[8];//change 4 to variable input 4 or 8
    }


    /**
     * Finds the value of 8 rays cast from the simple Rays snake
     */
    public void rayFinder()//input 0 or 1 for front or back
    {
        for(int i = 0; i < 8 ; i++)
        {
            int valArr = 0;
            int xSign = 0;
            int ySign = 0;
            int flag = 0;

            if((i+1)%2==0)
            {
                if(i==1)
                {
                    xSign = 1;
                    ySign = -1;
                }
                else if(i==3)
                {
                    xSign = 1;
                    ySign =1;
                }
                else if(i==5)
                {
                    xSign = -1;
                    ySign = 1;
                }
                else if(i==7)
                {
                    xSign = -1;
                    ySign = -1;
                }
            }
            else
            {
                if(i==0)
                    ySign = -1;
                else if(i==2)
                    xSign = 1;
                else if(i==4)
                    ySign = 1;
                else
                    xSign = -1;
            }

            int xVal = Snake.parts.get(0).getX();
            int yVal = Snake.parts.get(0).getY();

            do {
                xVal += xSign;
                yVal +=ySign;
                valArr = Board.arrBoard[yVal][xVal];
                flag++;

            }while(valArr==0);

            Rays[i] = flag;


        }
    }
}
