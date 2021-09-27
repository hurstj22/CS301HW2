package com.example.cs301hw2;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;

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
}
