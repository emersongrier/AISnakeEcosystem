package com.example.gamepractice;

public class SimpleRay
{
    Snake Snake;
    Board Board;

    int[] FrontRays;
    int[] BackRays;

    public SimpleRay(Snake s1,Board b1)//used
    {
        this.Snake = s1;
        this.Board = b1;
        FrontRays = new int[8];//change 4 to variable input 4 or 8
        BackRays = new int[8];
    }

    public void sendRays() {rayFinder(true);rayFinder(false);}//used

    /**
     * Finds the value of 8 rays cast from the simple Rays snake
     */
    //used
    private void rayFinder(Boolean marker)//true if front, false if tail
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
                {xSign = 1;ySign = -1;}
                else if(i==3)
                {xSign = 1;ySign =1;}
                else if(i==5)
                {xSign = -1;ySign = 1;}
                else if(i==7)
                {xSign = -1;ySign = -1;}
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

            int xVal;
            int yVal;

            if(marker==true)//is front
            {
                xVal = Snake.parts.get(0).getX();
                yVal = Snake.parts.get(0).getY();
            }
            else//is back
            {
                xVal = Snake.parts.get(Snake.parts.size()-1).getX();
                yVal = Snake.parts.get(Snake.parts.size()-1).getY();
            }

            do {
                xVal += xSign;
                yVal +=ySign;
                valArr = Board.arrBoard[yVal][xVal];
                flag++;

            }while(valArr==0);

            if(marker==true)
                FrontRays[i] = flag;
            else//is back
                BackRays[i] = flag;

        }
    }
}
