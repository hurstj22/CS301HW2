package com.example.cs301hw2;

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
    private int mult; //multiplier to make the squares bigger/smaller
    public int[] startIndex, endIndex;

    public PuzzleModel(){
        theTiles = new Tile [4][4]; //initialize to 4x4 array
        counter = 0;
        mult = 400; //pretty good sized squares for the Pixel C emulator
        startIndex= new int[2];
        endIndex = new int[2];
        hasWon = false;
        randArray = new int[16];
        genRandArray(); //populates the randArray with random nums 1 - 16, exactly once

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                if(randArray[counter] < 16) {
                    theTiles[i][j] = new Tile(i * mult, j * mult, randArray[counter]); //create a new tile and assign it to the array according to the counter
                }
                else{
                    theTiles[i][j] = new Tile(i * mult, j * mult, 0); //assigns whatever one in the randArray is 16 to actually be 0 in the Tiles array so it won't show up
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

    /**
     *
     * @return the multiplier that was used to create the squares
     */
    public int getMult(){
        return mult;
    }

    /**
     * swaps the tiles at the corresponding indexes IF
     * they are next to one another
     * @param startIndex
     * @param endIndex
     */
    public void swap(int[] startIndex, int[] endIndex){
        Tile copiedTile = new Tile(theTiles[startIndex[0]][startIndex[1]]); //copy the first Tile to a new empty Tile
        theTiles[startIndex[0]][startIndex[1]] = new Tile(theTiles[endIndex[0]][endIndex[1]]); //turn the first Tile into the ending tile
        theTiles[endIndex[0]][endIndex[1]] = new Tile(copiedTile); //now set the ending tile to be the new first tile
        //the o'l 1 2 switcharoo
    }
}
