package com.example.gamepractice;

public class Board
{
    double segmentSizeX;
    double segmentSizeY;

    int boardWidth;
    int boardHeight;

    double sceneWidth;
    double sceneHeight;

    public Board(int boardWidth, int boardHeight, double sceneWidth, double sceneHeight)
    {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        this.segmentSizeX = sceneWidth/boardWidth;
        this.segmentSizeY = sceneHeight/boardHeight;
    }
}
