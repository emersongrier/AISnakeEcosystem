package com.example.gamepractice;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board
{
    double segmentSizeX;
    double segmentSizeY;

    int boardWidth;
    int boardHeight;

    double sceneWidth;
    double sceneHeight;

    int[][] arrBoard;
    Rectangle[][] tileBoard;

    Group root;

    public Board(int boardWidth, int boardHeight, double sceneWidth, double sceneHeight,Group root)
    {
        this.root = root;

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        this.segmentSizeX = sceneWidth/boardWidth;
        this.segmentSizeY = sceneHeight/boardHeight;

        arrBoard = new int [this.boardHeight][this.boardWidth];
        tileBoard = new Rectangle[this.boardHeight][this.boardWidth];

        for(int i = 0; i< tileBoard.length;i++)//this creates all the tiles for use in graphics, as well as makes the border = to 3s
        {
            for(int j = 0; j<tileBoard[i].length;j++)
            {
                if(i==0||j==0||i==arrBoard.length-1||j==arrBoard[i].length-1)
                    arrBoard[i][j] = 3;
                tileBoard[i][j] = new Rectangle(j*segmentSizeX,i*segmentSizeY,segmentSizeX,segmentSizeY);//tiles
                tileBoard[i][j].setFill(Color.WHITE);
                root.getChildren().add(tileBoard[i][j]);
            }
        }
    }


    /**
     * Reads known entity data, and places it on board
     * should recieve a sim class that contains snakes, foods, and possibly rocks
     * WARNING - make sure to change this so it makes the rectangles more efficient
     */
    public void arrSetter(Snake[] snakes, Food[] foods)//used
    {
        for(int i = 0; i<arrBoard.length;i++)
        {
            for(int j = 0; j<arrBoard[i].length;j++)
            {
                if(i==0||j==0||i==arrBoard.length-1||j==arrBoard[i].length-1)
                    arrBoard[i][j] = 3;
                else
                    arrBoard[i][j] = 0;
            }
        }

        for(int i = 0; i<foods.length;i++)
        {
            arrBoard[foods[i].ypos][foods[i].xpos] = 2;
        }
        for(int j = 0; j<snakes.length;j++)
        {
            for (int i = 0; i < snakes[j].parts.size(); i++)
            {
                arrBoard[snakes[j].parts.get(i).getY()][snakes[j].parts.get(i).getX()] = 1;
            }
        }
    }


    /**
     *Scans array data for use in drawing
     * WARNING very slow- implement a way to change finite rec location, instead of CASCADING entire board...
     */
    public void colorSet()//used
    {
        for(int i = 0; i<boardHeight;i++)
        {
            for(int j = 0; j<boardWidth;j++)
            {
                if(arrBoard[i][j]==3)
                    tileBoard[i][j].setFill(Color.BURLYWOOD);
                else if(arrBoard[i][j]==2)
                    tileBoard[i][j].setFill(Color.RED);
                else if(arrBoard[i][j]==1)
                    tileBoard[i][j].setFill(Color.GREEN);
                else
                    tileBoard[i][j].setFill(Color.WHITE);
            }
        }
    }
}
