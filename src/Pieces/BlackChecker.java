/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import AlphaBetaTree.AlphaBetaNode;
import Board.BoardUtilities;
import finalyearproject.FinalYearProject;

/**
 *
 * @author Daniel
 */
public class BlackChecker {
    
    private final int startRow;
    private final int startColumn;
    private final char mySymbol;
    private final char opponentSymbol;
    private int row;
    private int column;
    private String message = "black cannot move";
    public int score = 0;
    
    public BlackChecker(int row, int column){
        
        startRow = row;
        startColumn = column;
        mySymbol = 'X';
        opponentSymbol = 'O';
    
    }
    public BlackChecker(int row, int column, boolean isRedKing){
    
    }
    
    public BoardScorePair moveLeft(char[][] board) throws IllegalMoveException{
        
        //test to see if move is applicable -- (redundant?)
        board = BoardUtilities.buildBoard(board);
       score = 0;
       row = startRow;
       column = startColumn;
        if(this.column-1<0){
        
            throw new IllegalMoveException("black left move  column out of bounds");
        }       
        if(this.row-1<0){

            throw new IllegalMoveException(" black left move  row out of bounds");

        }
        if(board[this.row -1][this.column -1] == 'X'){
        
            throw new IllegalMoveException("black left move will hit another black");
        
        }

            //this.row = this.row +1;// move UP the board
        if(checkTakeLeft(board)){//testing for take in one single turn

            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column); // boardSymbol(int i, int j)
            board[this.row-1][this.column-1] = FinalYearProject.boardSymbol(this.row -1, this.column -1);
            board[this.row-2][this.column-2] = 'X';
            this.row = this.row-2;
            this.column = this.column-2;

            score -= 2;
            board = doMoves(board);

        }
        else{
        
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            this.row = this.row -1;
            this.column = this.column -1;
            board[this.row][this.column] = 'X';
            
        }
        return new BoardScorePair(score, board);
       // AlphaBetaNode newNode = new AlphaBetaNode(myNode, board, score, myNode.depthLevel+1, !(myNode.isMaxNode));
         //return newNode.getBestMove(); // create a Node with a parent attached. will carry Culmative score until seesation point. then fire up the tree
                    //myNode.deepness <--- how far down the algorithm u are
        //this.column =this.column -1;
        //update Table info and/or GUI
    
    }
    
    public BoardScorePair moveRight(char[][] board) throws IllegalMoveException{
    
        board = BoardUtilities.buildBoard(board);
        score = 0;
        row = startRow;
        column = startColumn;
        
        if(this.column +1 >7){
        
            throw new IllegalMoveException("Black right move column out of bounds");
        }       
        if(this.row-1<0){

            throw new IllegalMoveException("Black right move row out of bounds");
            
        }
        if(board[this.row -1][this.column +1] == 'X'){
        
            throw new IllegalMoveException("black right move will hit another balck");
        
        }
        
        if(checkTakeRight(board)){//testing for take in one single turn

            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row-1][this.column +1] = FinalYearProject.boardSymbol(this.row -1, this.column+1);
            board[this.row-2][this.column+2] = 'X';
            this.row = this.row-2;
            this.column = this.column+2;

            score -= 2;      
           board = doMoves(board);// will be left hand favoring

        }
        else{
            
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);;
            this.row = this.row -1;
            this.column = this.column +1;
            board[this.row][this.column] = 'X';
        
        }

        // AlphaBetaNode newNode = new AlphaBetaNode(myNode, board, score, myNode.depthLevel+1, !(myNode.isMaxNode));
         
         return new BoardScorePair(score, board);
       
         //return newNode.getBestMove(); 
    }
    
    
    ///// helper methods ////
    
    
    public char[][] doMoves(char[][] board){

        while(checkTakeLeft(board)){

            //System.out.println("EXPLAIN is BLACK to me how the score of" + score + " is returned");
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column); // boardSymbol(int i, int j)
            board[this.row-1][this.column-1] = FinalYearProject.boardSymbol(this.row -1, this.column -1);
            board[this.row-2][this.column-2] = 'X';
            this.row = this.row-2;
            this.column = this.column-2;

            score -= 2;
           // System.out.println("EXPLAIN is BLACK to me how the score of " + score + " is returned");
        }
        while(checkTakeRight(board)){

            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row-1][this.column +1] = FinalYearProject.boardSymbol(this.row -1, this.column+1);
            board[this.row-2][this.column+2] = 'X';
            this.row = this.row-2;
            this.column = this.column+2;

            score -= 2;      
            //System.out.println("EXPLAIN is BLACK to me how the score of " + score + " is returned");

        }

        return board;

    }
    
    public boolean checkTakeLeft(char[][] board){
    
        
        return !(this.row-1<0) && !(this.column-1<0) && (board[this.row-1][this.column-1] == 'O' && canTakeLeft(board)); 
    
    }
    public boolean canTakeLeft(char[][] board){

        return (!(this.row-2<0) && !(this.column-2<0)) && (board[this.row-2][this.column-2] == 'B' || board[this.row-2][this.column-2] == 'W');
                   
    }
  
    
    public boolean checkTakeRight(char[][] board){
    
        return (!(this.row-1<0) && !(this.column+1>7)) && (board[this.row-1][this.column+1] == 'O' && canTakeRight(board));
    
    }
    
    public boolean canTakeRight(char[][] board){
        
        return (!(this.row-2<0) && !(this.column+2>7)) && (board[this.row-2][this.column+2] == 'B' || board[this.row-2][this.column+2] == 'W');
    
    }
    
}
