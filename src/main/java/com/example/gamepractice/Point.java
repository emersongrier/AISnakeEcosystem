package com.example.gamepractice;

public class Point
{
    private int xpos;
    private int ypos;

    public Point(int x, int y)
    {
        this.xpos = x;
        this.ypos = y;
    }

    public void setX(int x)
    {
        this.xpos = x;
    }

    public void setY(int y)
    {
        this.ypos = y;
    }

    public int getX()
    {
        return this.xpos;
    }

    public int getY()
    {
        return this.ypos;
    }

}
