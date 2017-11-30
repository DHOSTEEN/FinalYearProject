/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class BoardMovesPair {

    public ArrayList<MoveCoordinates> getAllMoves() {
        return allMoves;
    }

    public char[][] getBoard() {
        return board;
    }

    private final ArrayList<MoveCoordinates> allMoves;
    private final char[][] board;
 public BoardMovesPair(char[][] board, ArrayList<MoveCoordinates> allMoves){
 
     this.allMoves = allMoves;
     this.board = board;
     
 }
    
}
