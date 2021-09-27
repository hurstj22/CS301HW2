package com.example.cs301hw2;

import android.view.DragEvent;
import android.view.View;

/**
 * The PuzzleController class is what controls the functionality of all the
 * listeners in the game. This class controls how the game works when various
 * tiles are dragged or the reset button is pressed.
 */
public class PuzzleController implements View.OnClickListener, View.OnDragListener{

    private PuzzleModel puzzleModel;
    private PuzzleSurfaceView puzzleView;

    public PuzzleController(PuzzleSurfaceView puzzle) {
        puzzleView = puzzle;
        puzzleModel = puzzle.getPuzzleModel();
    }

    @Override
    public void onClick(View view) {
        puzzleView.reset(); //reset the view -> creates a new board/model
        puzzleModel = puzzleView.getPuzzleModel(); //thus must update the model stored here in controller with the new one
        puzzleView.invalidate();
    }


    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if(dragEvent.getAction() == DragEvent.ACTION_DRAG_STARTED ){ //started the drag so get coords
            puzzleModel.startIndex =  findTileIndex(dragEvent.getX(), dragEvent.getY());
            return true;
        }
        if(dragEvent.getAction() == DragEvent.ACTION_DROP){ //ending the drag so try to swap the tiles
            puzzleModel.endIndex = findTileIndex(dragEvent.getX(), dragEvent.getY());
            puzzleModel.swap(puzzleModel.startIndex, puzzleModel.endIndex); //swaps the two tiles at those indexes IF they are next to each other
            return true;
        }
        return false;
    }

    /**
     *
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
}
