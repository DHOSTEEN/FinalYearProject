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
public class RedChecker {
    
    private final int startRow;
    private final int startColumn;
    private int row;
    private int column;
   // private final boolean colour; // true is top, false is bottom
    private int score = 0;
    private int potentialScore =0;
   
    private String redMessage = "red Cannopt Move";
    
    public RedChecker(int initialRow, int initialColumn){
        
        startRow = initialRow;
        startColumn = initialColumn;
       //S this.colour = colour;
    
    }
    
    // getters and setters unsure how many, if any, will be used
    
    public int getRow() {
        return startRow;
    }

 
    public int getColumn() {
        return startColumn;
    }

  
  /*  public boolean getColour(){
    
        return colour;
        
    }*/
    
    public BoardScorePair moveLeft(char[][] board) throws IllegalMoveException{
        
        //test to see if move is applicable -- (redundant?)
       board = BoardUtilities.buildBoard(board);
       column = startColumn;
       row = startRow;
       score = 0;
       // System.out.println("the ROW of RED is " +row + "THE COL is " + column);
        
        if(this.column -1<0){
        
            throw new IllegalMoveException(redMessage);
        }       
        if(this.row+1>7){

            throw new IllegalMoveException(redMessage);

        }
        if(board[this.row +1][this.column -1] == 'O'){
        
        
            throw new IllegalMoveException(redMessage);
        }

            //this.row = this.row +1;// move UP the board
        if(checkTakeLeft(board)){//testing for take in one single turn

           // System.out.println("DO IT EVEN");
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row+1][this.column -1] = FinalYearProject.boardSymbol(this.row +1, this.column-1); // boardSymbol(int i, int j)
            board[this.row+2][this.column-2] = 'O';
            this.row = this.row +2;
            this.column = this.column-2;

            score += 2;
            //pairValue.setIsAbleToMove(checkTakeLeft(board));
            board = doMoves(board);

        }
        else{
        
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            
            board[this.row+1][this.column-1] = 'O';
          
            
        }
     
        return new BoardScorePair(score, board);
        //AlphaBetaNode newNode = new AlphaBetaNode(myNode, board, score, myNode.depthLevel+1, !(myNode.isMaxNode));
        // return newNode.getBestMove(); // create a Node with a parent attached. will carry Culmative score until seesation point. then fire up the tree
                    //myNode.deepness <--- how far down the algorithm u are
        //this.column =this.column -1;
        //update Table info and/or GUI
    
    }
    public BoardScorePair moveRight(char[][] board) throws IllegalMoveException{
    
    //same logic as above
    board = BoardUtilities.buildBoard(board);
    column = startColumn;
    row = startRow;
    score = 0;
       if(this.column +1>7){
        
            throw new IllegalMoveException("Red column is out of bounds");
        }       
        if(this.row+1>7){

            throw new IllegalMoveException("Red Row is out of bounds");

        }
        if(board[this.row +1][this.column +1] == 'O'){
        
        
            throw new IllegalMoveException("will hit another rred");
        }


            //this.row = this.row +1;// move UP the board
        if(checkTakeRight(board)){//testing for take in one single turn

            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row+1][this.column +1] = FinalYearProject.boardSymbol(this.row +1, this.column+1);
            board[this.row+2][this.column+2] = 'O';
            this.row = this.row+2;
            this.column = this.column+2;

            score += 2;         
        
            board = doMoves(board);

        }
        else {
        
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row +1][this.column +1] = 'O';
         
            
        }
        //AlphaBetaNode newNode = new AlphaBetaNode(myNode, board, score, myNode.depthLevel+1, !(myNode.isMaxNode));
        //return newNode.getBestMove(); 
   
        //return pairValue;
        return new BoardScorePair(score, board);
    
    }
    
   
    
    /// helper ///
    
    public char[][] doMoves(char[][] board){
    
        
        while(checkTakeLeft(board)){

          
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row+1][this.column -1] = FinalYearProject.boardSymbol(this.row +1, this.column-1); // boardSymbol(int i, int j)
            board[this.row+2][this.column-2] = 'O';
            this.row = this.row+2;
            this.column = this.column-2;

            score += 2;
         
            
             // System.out.println("EXPLAIN is RED to me how the score of " + score + " is returned");

        }
        while(checkTakeRight(board)){
            
           
            board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
            board[this.row+1][this.column +1] = FinalYearProject.boardSymbol(this.row +1, this.column+1);
            board[this.row+2][this.column+2] = 'O';
            this.row = this.row+2;
            this.column = this.column+2;

            score += 2;         
       
            // System.out.println("EXPLAIN is RED to me how the score of " + score + " is returned");


        }

        
        return board;
    
    }
    
    
    public boolean checkTakeLeft(char[][] board){
        
        
    
        return (!(this.row+1>7) && !(this.column-1<0)) && (board[this.row+1][this.column-1] == 'X' && canTakeLeft(board)); 
    
    }
    public boolean canTakeLeft(char[][] board){

        return (!(this.row+2>7) && !(this.column-2<0)) && (board[this.row+2][this.column-2] == 'B' || board[this.row+2][this.column-2] == 'W');
                   
    }
  
    
    public boolean checkTakeRight(char[][] board){
    
        return (!(this.row+1>7) && !(this.column+1>7)) && (board[this.row+1][this.column+1] == 'X' && canTakeRight(board));
    
    }
    
    public boolean canTakeRight(char[][] board){
        
        return (!(this.row+2>7) && !(this.column+2>7)) && (board[this.row+2][this.column+2] == 'B' || board[this.row+2][this.column+2] == 'W');
    
    }
    
}
