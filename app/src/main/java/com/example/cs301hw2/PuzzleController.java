package com.example.cs301hw2;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * The PuzzleController class is what controls the functionality of all the
 * listeners in the game. This class controls how the game works when various
 * tiles are dragged or the reset button is pressed.
 */
public class PuzzleController implements View.OnClickListener, View.OnTouchListener{

    private PuzzleModel puzzleModel;
    private PuzzleSurfaceView puzzleView;

    public PuzzleController(PuzzleSurfaceView puzzle) {

        puzzleView = puzzle;
        puzzleModel = new PuzzleModel(this);
        puzzleView.setModel(puzzleModel);
    }

    /**
     * updates the model after each move
     * by first invalidating the view then calling the
     * winScreen to display a fun winning screen if
     * you have won the game
     */
    public void modelUpdate(){
        puzzleView.invalidate();
        puzzleView.winScreen(puzzleModel.getHasWon());
    }

    /**
     * The click controller that controls the functionality
     * of the reset button
     * @param view
     */
    @Override
    public void onClick(View view) {
        //reset the view -> creates a new board/model
        puzzleModel = new PuzzleModel(this);
        puzzleView.setModel(puzzleModel);
        puzzleView.invalidate();
        Log.e("button", "Puzzle Reset!");
    }

    /**
     * takes in the results from dragging a tile on the screen to a location
     * and returns the index in the array in which that tile is stored
     * @param dragX
     * @param dragY
     * @return the index for the drag event by reversing the math done to create the square
     */
    public int[] findTileIndex(float dragX, float dragY){
        int index [] = new int[2];
        index[0] = (int) dragX/puzzleModel.getMult();
        index[1] = (int) dragY/puzzleModel.getMult();
        return index;
    }

    /**
     * Found this code on StackOverflow to manipulate the touch event to simulate a swipe
     * https://stackoverflow.com/questions/65636782/android-studio-how-to-use-implement-swipe-gesture-in-android-game
     * Looks for a press down and records that as the starting position
     * then when the finger is lifted the ending position is passed into makeMove to swap the tiles
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                puzzleModel.startIndex = findTileIndex(motionEvent.getX(), motionEvent.getY());
                Log.e("ACTION_DOWN", "" + puzzleModel.startIndex[0] + " " + puzzleModel.startIndex[1]);
                break;
            case MotionEvent.ACTION_UP:
                puzzleModel.endIndex = findTileIndex(motionEvent.getX(), motionEvent.getY());
                Log.e("ACTION_UP", ""+puzzleModel.endIndex[0]+" "+puzzleModel.endIndex[1]);
                puzzleModel.makeMove(puzzleModel.startIndex, puzzleModel.endIndex); //swaps the two tiles at those indexes IF they are next to each other
                puzzleView.invalidate();
                break;
        }
        return true;
    }

    /**
     * checks for a win on the board
     * by iterating through each position of the tiles
     * and checking it's assigned number for if it
     * is in order
     * @return a boolean, true if all nums are in order else false
     */
    public boolean checkWin(){
        int counter = 1;
        for(int i = 0; i < puzzleModel.theTiles.length; i++){
            for(int j = 0; j < puzzleModel.theTiles[i].length; j++){
                if(counter != puzzleModel.theTiles[j][i].getNum() && counter < 16){
                    Log.e("TileWinNums", "" + counter + ", " + puzzleModel.theTiles[j][i].getNum());
                    return false; //stop as soon as finds incorrect num
                }
                counter++;
            }
        }
        return true; //made it all the way through and didn't find a wrong answer
    }
} //end of controller class
