package com.example.cs301hw2;

import android.graphics.Canvas;
import android.graphics.Color;
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
    private Paint tileColor;
    private Paint textColor;
    private Paint borderColor;

    /**
     * constructor for the Tile
     * takes a upper left starting position
     * and a number to assign to the tile
     * @param startX
     * @param startY
     * @param num
     */
    public Tile(float startX, float startY, int num){
        x = startX;
        y = startY;
        this.num = num;

        //colors galore!
        tileColor = new Paint();
        textColor = new Paint();
        borderColor = new Paint();
        borderColor.setColor(Color.BLUE);
        tileColor.setARGB(255, 255, 255, 255);
        borderColor.setStyle(Paint.Style.STROKE);
        tileColor.setStyle(Paint.Style.FILL);
        textColor.setARGB(255, 100, 0, 100);
        textColor.setTextSize(48);

    }

    /**
     * draws each tile as a filled in rectangle with a blue border color
     * then overlays a textbox with the number of the tile
     * @param canvas
     */
    public void draw(Canvas canvas){
        float xEnd = x+400;
        float yEnd = y+400;
        tileColor.setStyle(Paint.Style.FILL);
        canvas.drawRect(x,y,xEnd, yEnd, tileColor);
        canvas.drawRect(x,y,xEnd, yEnd, borderColor);

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
        this.tileColor = new Paint(other.tileColor);
        this.textColor = new Paint(other.textColor);
    }

    //a bunch of boring setter functions are below
    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public int getNum(){
        return this.num;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void setTileColor(int r, int g, int b){
        tileColor.setARGB(255, r, g, b);
    }
}
