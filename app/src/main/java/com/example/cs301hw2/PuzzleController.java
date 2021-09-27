package com.example.cs301hw2;

import android.view.View;

public class PuzzleController implements View.OnClickListener{

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


}
