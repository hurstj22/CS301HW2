package com.example.cs301hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

/**
 * The PuzzleSurfaceView is a custom SurfaceView that
 * draws the board of Tiles on the screen in a 4X4 arrangement
 * The surface view instantiates the puzzle's model as found in the PuzzleModel class
 */
public class PuzzleSurfaceView extends SurfaceView {
    PuzzleModel pModel;

    /**
        Default constructor, initialize all variables for the surface view
     **/
    public PuzzleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
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

    /**
     * simple getter method to get the puzzleModel associated with this view
     * @return
     */
    public PuzzleModel getPuzzleModel(){
        return this.pModel; //return this methods instance of the puzzle model held within
    }

    /**
     * Allows for the puzzleModel in the view to be updated externally
     * @param puzzleModel
     */
    public void setModel(PuzzleModel puzzleModel){
        pModel = puzzleModel;
    }

    /**
     * Creates fun gradient of purple colors on the screen if the game has been won
     * @param hasWon
     */
    public void winScreen(boolean hasWon){
        if(hasWon){
            new CountDownTimer(16*100, 100) { //obtained this code from the Android API
                                                                        //https://developer.android.com/reference/android/os/CountDownTimer
                private int i = 0;
                private int j = 0;

                public void onTick(long millisUntilFinished) {
                    if (i > 3 || j > 3) {
                        return;
                    }
                    pModel.theTiles[i][j].setTileColor((i+j) * 255/12, (i+j)/2 * 255/12, (i+j) * 255/12); //runs through a gradient of purple, Oooooo
                    invalidate();
                    i++;
                    if (i == 4) {
                        i = 0;
                        j++;
                    }
                }

                public void onFinish() {
                    System.out.println("You win!");
                }
            }.start();
        }
    }
}
