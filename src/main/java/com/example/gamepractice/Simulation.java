package com.example.gamepractice;

import javafx.scene.Group;

public class Simulation
{
    Snake[] snakes;
    Board board;
    Food[] foods;

    Snake bestOne;
    Snake bestTwo;

    public Simulation(int snakeCount, int foodCount, int boardWidth, int boardHeight, double sceneWidth, double sceneHeight, Group root)
    {
        board = new Board(boardWidth,boardHeight,sceneWidth,sceneHeight,root);

        snakes = new Snake[snakeCount];
        foods = new Food[foodCount];

        bestOne = new Snake(board,foods);
        bestTwo = new Snake(board,foods);

        for(int i = 0; i<snakeCount;i++)
        {
            snakes[i] = new Snake(board,foods);
        }
        for(int i = 0; i<foodCount;i++)
        {
            foods[i] = new Food(board);
            foods[i].createFood();
        }
    }

    /**
     * updates the game every frame
     */
    public void updateGame()//check order
    {
        int count = 0;
        for(int i = 0; i<snakes.length;i++)
        {
            snakes[i].isDead(snakes);

            if(!snakes[i].isDead)
            {
                boolean ate = snakes[i].checkAte();
                if(!ate)
                {
                    snakes[i].snakeUpdate();
                }
            }

        }

        board.arrSetter(snakes,foods);
        board.colorSet();

        for(int i=0; i<snakes.length;i++)
        {
            if(snakes[i].isDead)
                count++;
            if(snakes[i].timeAlive>7)
                resetSim();
        }
        if(count==snakes.length)
        {
            resetSim();
        }
    }

    /** sets or resets the game state
     *
     */
    private void resetSim()
    {
        findTwoBest();
        System.out.println(bestOne.length);
        repopulate();
        for(int i = 0;i< snakes.length;i++)
        {
            snakes[i].resetSnake();
        }
        for(int i = 0; i< foods.length;i++)
        {
            foods[i].createFood();
        }
    }

    /**
     * Finds the two snakes who have lived the longest
     */
    private void findTwoBest()
    {
        for(int i=0;i<snakes.length;i++)
        {
            if(snakes[i].timeAlive>bestTwo.timeAlive)
            {
                if(snakes[i].length>bestOne.length)//rating
                {
                    bestTwo.NN.layers = bestOne.NN.layers.clone();
                    bestTwo.length = bestOne.length;

                    bestOne.NN.layers = snakes[i].NN.layers.clone();
                    bestOne.length = snakes[i].length;
                }
                else
                {
                    bestTwo.NN.layers = snakes[i].NN.layers.clone();
                    bestTwo.length = snakes[i].length;
                }
            }
        }
    }


    /**
     * Using the best two snakes, randomly distrubetes, as well
     * as mutates the weights of the next gen
     */
    private void repopulate()
    {
        for(int i = 0; i<snakes.length;i++)
        {
            for(int j = 0; j<snakes[i].NN.layers.length;j++)
            {
                for(int y= 0; y<snakes[i].NN.layers[j].weightsArray.length;y++)
                {
                    for(int z = 0; z<snakes[i].NN.layers[j].weightsArray[y].length;z++)
                    {
                        if(Math.random()<.925)
                        {
                            if(Math.random()>.5)
                                snakes[i].NN.layers[j].weightsArray[y][z] = bestOne.NN.layers[j].weightsArray[y][z];
                            else
                                snakes[i].NN.layers[j].weightsArray[y][z] = bestTwo.NN.layers[j].weightsArray[y][z];
                        }
                        else
                        {
                            double ran = Math.random();
                            if(ran>.66)
                                snakes[i].NN.layers[j].weightsArray[y][z] = 1.25*bestOne.NN.layers[j].weightsArray[y][z];
                            else if(ran>.66)
                                snakes[i].NN.layers[j].weightsArray[y][z] = .75*bestTwo.NN.layers[j].weightsArray[y][z];
                            else
                                snakes[i].NN.layers[j].weightsArray[y][z] = -1*bestTwo.NN.layers[j].weightsArray[y][z];
                        }
                    }
                }
            }
        }
        bestTwo.length = 1;//subject to removal
        bestOne.length = 1;

    }

}
