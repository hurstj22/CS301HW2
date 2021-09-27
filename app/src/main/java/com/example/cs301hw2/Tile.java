package com.example.cs301hw2;

import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;

public class Tile {
    private float x, y; //starting left and right coords for the tile
    private int num; //number assigned to that tile
    private Paint tileColor;
    private Paint textColor;

    public Tile(float startX, float startY, int num){
        x = startX;
        y = startY;
        this.num = num;

        tileColor.setARGB(255, 0, 0, 0);
        tileColor.setStyle(Paint.Style.STROKE);
        textColor.setARGB(255, 100, 0, 100);
    }

    //draws the tile on the canvas and textbox with number
    public void draw(Canvas canvas){
        canvas.drawRect(x,y,x+25, y+25, tileColor);
        canvas.drawText(String.valueOf(num), x+12, y+12, textColor); //draw textbox roughly in the middle
    }
}
