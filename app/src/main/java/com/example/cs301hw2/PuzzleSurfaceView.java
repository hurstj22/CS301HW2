package com.example.cs301hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PuzzleSurfaceView extends SurfaceView {

    PuzzleModel pModel;
    /*
        Default constructor, initialize all variables for the surface view
     */
    public PuzzleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        //create the puzzle model object from the basic constructor
        pModel = new PuzzleModel();
    }

    /**
     * Draws all square tiles on the surface view
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {

        //run through all values in the tile array and draw them on the surface view
        for (int row = 0; row < pModel.theTiles.length; row++) {
            for (int col = 0; col < pModel.theTiles[row].length; col++) {
                if(pModel.theTiles[row][col] != null) {
                    pModel.theTiles[row][col].draw(canvas);
                }
            }
        }
    }

    public PuzzleModel getPuzzleModel(){
        return this.pModel; //return this methods instance of the puzzle model held within
    }

    public void reset(){
        pModel = new PuzzleModel();
        Log.i("button", "Puzzle Reset!");
    }
}
