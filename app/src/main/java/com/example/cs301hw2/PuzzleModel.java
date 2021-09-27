package com.example.cs301hw2;

import java.util.ArrayList;
import java.util.Random;

public class PuzzleModel {

    public ArrayList<Tile> theTiles;
    static public final Random RAND = new Random(); //create a random variable that can be used anywhere
    static int counter = 0;

    public PuzzleModel(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                counter++; //increment counter so it starts at 1 and ends at 16
                Tile myTile = new Tile(i * 25, j * 25, counter); //make a new Tile
                theTiles.add(myTile); //put it into the ArrayList
            }
        }
    }
}
