/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

/**
 *
 * @author Daniel
 */
public class BoardScorePair {

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
    
    private int score;
    private char[][] board;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    
   
    private int row;
    private int column;

    public boolean isAbleToMove() {
        return isAbleToMove;
    }

    public void setIsAbleToMove(boolean isAbleToMove) {
        this.isAbleToMove = isAbleToMove;
    }
    private boolean isAbleToMove = false;
    public BoardScorePair(int s, char[][] b){
        
        score = s;
        board = b;
    
    }
    
}
