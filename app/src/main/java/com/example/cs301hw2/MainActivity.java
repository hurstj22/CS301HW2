package com.example.cs301hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Author: James Hurst (hurstj22@up.edu)
 *
 * Version: 9/27/2021
 *
 * This is the main activity which declares all my layout assets,
 * Note: I took the approach of dragging the tiles rather than making buttons
 * This game is a fun puzzle game where you try to get all the tiles in
 * numerical order.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        //create the surface view to hold the board in
        PuzzleSurfaceView puzzleSurfaceView = (PuzzleSurfaceView) findViewById(R.id.puzzleSurfaceView);
        PuzzleController puzzleController = new PuzzleController(puzzleSurfaceView);

        //create the button object to interact with
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener((View.OnClickListener) puzzleController);

        //create the touch listener
        puzzleSurfaceView.setOnTouchListener(puzzleController);
    }
}