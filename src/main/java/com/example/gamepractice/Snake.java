package com.example.gamepractice;

import java.util.ArrayList;

public class Snake
{
    //data
    int heading;//snake
    int length = 1;//1, snake
    boolean isDead = false;//snake specific
    double timeAlive = 0;

    //Objects
    ArrayList<Point> parts;//snake specific
    Board b1;
    NeuralNetwork NN;
    Food[] foods;

    SimpleRay Ray;

    /**
     * Snake constructor
     * @param b1
     */
    public Snake(Board b1,Food[] foods)
    {
        this.foods = foods;
        this.b1 = b1;
        parts = new ArrayList<Point>();

        parts.add(new Point());

        while(true)
        {
            parts.get(0).setX((int) Math.floor(Math.random()*b1.boardWidth));
            parts.get(0).setY((int) Math.floor(Math.random()*b1.boardWidth));

            if(b1.arrBoard[parts.get(0).getY()][parts.get(0).getX()]==0)
            {
                break;
            }
        }

        heading = (int) Math.floor(Math.random()*4);

        NN = new NeuralNetwork();
        Ray = new SimpleRay(this,b1);
    }//just updated all this


    /**
     * used on a snake, it updates the head base on its heading;
     * then, it updates each subsequent segment into the place held by its predesessor
     */
    public void snakeUpdate()
    {
        heading = snakeBrain();
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
        timeAlive +=(0.1/2.0);

    }



    /*

     */
    public boolean checkAte()//this can recieve a gam sim that scans through all food on that game sim;
    {
        for(int i=0;i<foods.length;i++)
        {
            if(foods[i].ypos==this.parts.get(0).getY()&&foods[i].xpos==this.parts.get(0).getX())
            {
                foods[i].createFood();
                this.parts.add(new Point(parts.get(length-1).getX(),parts.get(length-1).getY()));//needs to update without doing the second part// split into two functions
                length++;
                return true;
            }
        }
        return false;
    }


    /**
     * WARNING! for and if must be changed, so its checked agaisnt all, not just itself
     * @return true if the snake should die, false if otherwise
     */
    public void isDead(Snake[] snakes)
    {
        if(this.parts.get(0).getX()<0+1||this.parts.get(0).getX()>=b1.boardWidth-1||this.parts.get(0).getY()<0+1||this.parts.get(0).getY()>=b1.boardHeight-1)
            this.isDead = true;//if head outside of map it dies
        else
        {
            for(int i = 0; i<snakes.length;i++)
            {
                int j;
                if(snakes[i].equals(this))
                    j=1;
                else {
                    j = 0;
                    for (; j < snakes[i].parts.size(); j++) {
                        if (this.parts.get(0).getX() == snakes[i].parts.get(j).getX() && this.parts.get(0).getY() == snakes[i].parts.get(j).getY())
                            this.isDead = true;
                    }
                }
            }

        }
    }

    public void resetSnake()
    {
        for(int i = length-1;i>0;i--)
        {
            parts.remove(i);
        }

        length = 1;
        isDead = false;
        heading = (int)Math.floor(Math.random()*4);

        while(true)
        {
            parts.get(0).setX((int) Math.floor(Math.random()*b1.boardWidth));
            parts.get(0).setY((int)Math.floor(Math.random()*b1.boardWidth));

            if(b1.arrBoard[parts.get(0).getY()][parts.get(0).getX()]==0)
            {
                break;
            }
        }
        timeAlive = 0;
    }

    /**
     * This method runs the neural network, and returns the chosen heading the snake should use
     * @return
     */
    private int snakeBrain()
    {
        this.Ray.sendRays();
        double[] input = new double[19];
        double[] output;

        for(int i = 0; i<Ray.FrontRays.length;i++)
        {
            input[i] = Ray.FrontRays[i];
            input[i+8] = Ray.BackRays[i];
        }
        input[16] = heading+1;
        input[17] = this.parts.get(0).getX();
        input[18] = this.parts.get(0).getY();

        output = this.NN.Brain(input);

        double holder = -1;
        int flag = 0;

        for(int i =0; i<4;i++)
        {
            if(output[i]>holder)
            {
                holder = output[i];
                flag = i;
            }
        }

        return flag;

    }


}
