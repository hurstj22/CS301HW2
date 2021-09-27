package com.example.cs301hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PuzzleSurfaceView extends SurfaceView {

    ArrayList<Tile> theTiles;
    /*
        Default constructor, initialize all variables for the surface view
     */
    public PuzzleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    /**
     * Draws all square tiles on the surface view
     * @param canvas
     */
    protected void onDraw(Canvas canvas){

        for(Tile tile : theTiles){ //foreach list to run through and draw all tiles
            tile.draw(canvas);
        }
    }

}
