package com.example.cs301hw2;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;

/**
 * Tile class holds all the information for creating an individual Tile object
 * It draws a black outline of a rectangle with a textbox that contains the
 * sudo random numbers generated in the puzzle model class. Purposefully
 * omits the 0 as that is the number that is shown as blank.
 */
public class Tile {
    private float x, y; //starting left and right coords for the tile
    private int num; //number assigned to that tile
    private float xEnd, yEnd; //the end size for the squares
    private Paint tileColor;
    private Paint textColor;

    public Tile(float startX, float startY, int num){
        x = startX;
        y = startY;
        xEnd = startX+400;
        yEnd = startY+400;
        this.num = num;

        //colors galore!
        tileColor = new Paint();
        textColor = new Paint();
        tileColor.setARGB(255, 0, 0, 0);
        tileColor.setStyle(Paint.Style.STROKE);
        textColor.setARGB(255, 100, 0, 100);
        textColor.setTextSize(48);

    }

    //draws the tile on the canvas and textbox with number
    public void draw(Canvas canvas){
        canvas.drawRect(x,y,xEnd, yEnd, tileColor);
        if(num > 0) { //only draw textbox of the regular 1 - 15, leaves 0 out so there is an empty square
            canvas.drawText(String.valueOf(num), (x + xEnd) / 2, (y + yEnd) / 2, textColor); //draw textbox roughly in the middle
        }
    }

    /**
     * copy constructor for copying another tiles info
     * @param other the other Tile being copied from
     */
    public Tile(Tile other){
        this.x = other.x;
        this.y = other.y;
        this.num = other.num;
        this.xEnd = other.xEnd;
        this.yEnd = other.yEnd;
        this.tileColor = new Paint(other.tileColor);
        this.textColor = new Paint(other.textColor);
    }

    //a bunch of boring setter functions are below
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setNum(int num){
        this.num = num;
    }
}
