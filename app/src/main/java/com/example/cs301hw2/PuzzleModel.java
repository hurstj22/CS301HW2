package com.example.cs301hw2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class stores all the information about the model of the puzzle
 * along with helper methods to initialize the puzzle as a 2D sudo random array
 */
public class PuzzleModel {

    public Tile theTiles[][];
    static public final Random RAND = new Random(); //create a random variable that can be used anywhere
    private int counter;
    private boolean hasWon;
    private int randArray[]; //array to store random 0 - 15

    public PuzzleModel(){
        theTiles = new Tile [4][4]; //initialize to 4x4 array
        counter = 0;
        hasWon = false;
        randArray = new int[16];
        genRandArray(); //populates the randArray with random nums 0 - 15, exactly once

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                if(randArray[counter] < 16) {
                    theTiles[i][j] = new Tile(i * 400, j * 400, randArray[counter]); //create a new tile and assign it to the array according to the counter
                }
                else{
                    theTiles[i][j] = new Tile(i * 400, j * 400, 0); //assigns whatever one in the randArray is 16 to actually be 0 in the Tiles array so it won't show up
                }
                counter++; //increment counter so it starts at 0 and ends at 15
            }
        }
    }


    /**
     * generates a random numbers from 1 - 16 to populate the random array for the puzzle
     */
    public void genRandArray(){
        int placeHolder;
        boolean badRand = false;
        for(int i = 0; i < randArray.length; i++){
            placeHolder = Math.max(1, RAND.nextInt(17)); //temporarily holds the random int, never will send a zero
            for(int j = 0; j <= i; j++){
                if(placeHolder == randArray[j]){ //if there is a duplicate already in the array
                    badRand = true; //found a duplicate
                    break;
                }
            }
            if(badRand && i >= 0){ //if we find a duplicate rand num then decrement so we stay in place, then reset the flag
                i--; //decrements so that the loop doesn't continue until we've found all unique random numbers
                badRand = false;
            }
            else{//got a good one, put it in the array!
                randArray[i] = placeHolder;
            }
        }
    }

}
