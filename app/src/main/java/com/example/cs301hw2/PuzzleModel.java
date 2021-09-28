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
    private PuzzleController parentCon;

    public PuzzleModel(PuzzleController parentCon){
        this.parentCon = parentCon;
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
     * getter method to check on the hasWon variable for the win functionality
     * @return
     */
    public boolean getHasWon(){
        return hasWon;
    }

    /**
     * swaps the tiles at the corresponding indexes
     * went to office hours with Dr Tribelhorn to help with this swap function
     * @param startIndex
     * @param endIndex
     */
    public void swap(int[] startIndex, int[] endIndex){
        float oldX, oldY;

        oldX = theTiles[startIndex[0]][startIndex[1]].getX();
        theTiles[startIndex[0]][startIndex[1]].setX(theTiles[endIndex[0]][endIndex[1]].getX());
        theTiles[endIndex[0]][endIndex[1]].setX(oldX);

        oldY = theTiles[startIndex[0]][startIndex[1]].getY();
        theTiles[startIndex[0]][startIndex[1]].setY(theTiles[endIndex[0]][endIndex[1]].getY());
        theTiles[endIndex[0]][endIndex[1]].setY(oldY);

        Tile temp = theTiles[startIndex[0]][startIndex[1]];
        theTiles[startIndex[0]][startIndex[1]] = theTiles[endIndex[0]][endIndex[1]];
        theTiles[endIndex[0]][endIndex[1]] = temp;
    }

    /**
     * This function makes all the moves by performing
     * bounds checking as well as then swapping the tiles
     * once the bounds have been confirmed to be valid
     * @param startIndex
     * @param endIndex
     */
    public void makeMove(int[] startIndex, int[] endIndex) {
        int upBound = 4;
        int downBound = -1;

        //must first bound check the index before checking to see if the number is zero
        if (endIndex[0] > downBound && endIndex[0] < upBound && //is the tile row within the bounds
                endIndex[1] > downBound && endIndex[1] < upBound) { //is the tile col within the bounds)

            if (theTiles[endIndex[0]][endIndex[1]].getNum() == 0){ //is the tile the empty one

                //swap if on the same col and row is up or down from the start index
                if (startIndex[0] == endIndex[0] &&
                        ((startIndex[1] == endIndex[1] + 1 && endIndex[1] + 1 < upBound) ||
                                (startIndex[1] == endIndex[1] - 1 && endIndex[1] - 1 > downBound))) {
                    swap(startIndex, endIndex);
                }
                //swap if on the same row and col is left or right from the start index
                else if (startIndex[1] == endIndex[1] &&
                        ((startIndex[0] == endIndex[0] + 1 && endIndex[0] + 1 < upBound) ||
                                (startIndex[0] == endIndex[0] - 1 && endIndex[0] - 1 > downBound))) {
                    swap(startIndex, endIndex);
                }
        }
    }
        //tell control to check if game has been won
        hasWon = parentCon.checkWin();
        //update model on gameboard each time a move has been made
        parentCon.modelUpdate();
    }
} //end of model class