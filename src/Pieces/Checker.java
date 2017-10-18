/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;


import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import Pieces.IllegalMoveException;
import finalyearproject.FinalYearProject;
import finalyearproject.tester;

/**
 *
 * @author Daniel
 */
public class Checker {
    
    protected final int startRow;
    protected final int startColumn;
   
    protected int row;
    protected int column;
    protected String message = "black cannot move";
    public int score = 0;
    protected boolean isRed;
    protected boolean isKing;
    protected boolean  isBackwardsKing;
    
    protected char mySymbol;
    protected char myOtherSymbol;
    protected char myKingSymbol;
    protected String myColour;
    protected char opponentSymbol;
    protected char opponentKingSymbol;
    protected int upOrDown;
    protected int point;
    protected int extraPoint;
    
   protected final char redChecker = 'O';
   protected final char redKingChecker = 'E';
   protected final char blackChecker = 'X';
   protected final char blackKingChecker = 'K';
   
   private final int takeValue = 10;
   private final int extraValue = 1;
   
   private Checker check;
   private int tempScore;
   private int tempRow;
   private int tempCol;
   
   protected boolean isFirstMove;
   
   protected AlphaBetaTree myNode;
   
    
    public Checker(int row, int column, boolean isRed, AlphaBetaTree myNode){
        
        startRow = row;
        startColumn = column;
        this.myNode = myNode;
        setVariables(isRed, false, false);
    
    }
    
    public Checker(int row, int column, boolean isRed, boolean isKing, boolean isBackwardsKing, AlphaBetaTree myNode){

        startRow = row;
        startColumn = column;
        this.myNode = myNode;
        //check = new Checker(row, column, isRed, false, true, myNode);// to make BACKWARDS left/right
        setVariables(isRed, isKing, isBackwardsKing);
    
    }
    protected void setVariables(boolean isRed, boolean isKing, boolean isBackwardsKing){
        
        this.isBackwardsKing = isBackwardsKing;
        this.isKing = isKing;
        
        if(isRed && isKing){
            
            mySymbol = redKingChecker;
            myOtherSymbol = redChecker;
            opponentSymbol = blackChecker;
            opponentKingSymbol = blackKingChecker;
            upOrDown = -1;
            point = takeValue;
            extraPoint = extraValue;
            myColour = "RED KING";
        
        }
        else if(!isRed && isKing){
            
            mySymbol = blackKingChecker;
            myOtherSymbol = blackChecker;
            opponentSymbol = redChecker;
            opponentKingSymbol = redKingChecker;
            upOrDown = 1;
            point = -takeValue;
            extraPoint = -extraValue;
            myColour = "BLACK KING";
        
        }
        else if(isRed){
            if(isBackwardsKing){
            
                mySymbol = redKingChecker;
                myOtherSymbol = redChecker;
            }
            else{
                mySymbol = redChecker;
                myOtherSymbol = redKingChecker;
            }
            opponentSymbol = blackChecker;
            opponentKingSymbol = blackKingChecker;
            upOrDown = 1;
            point = takeValue;
            extraPoint = extraValue;
            myColour = "RED";
        }
        else{
            if(isBackwardsKing){
                mySymbol = blackKingChecker;
                myOtherSymbol = 'X';
                
            }
            else{
                mySymbol = blackChecker;
                myOtherSymbol = blackKingChecker;
            }
            opponentSymbol = redChecker;
            opponentKingSymbol = redKingChecker;
            upOrDown = -1;
            point = -takeValue;
            extraPoint = -extraValue;
            myColour = "BLACK";
        
        }
    
    }
    public void moveLeft(char[][] board)throws IllegalMoveException{
        
        board = BoardUtilities.buildBoard(board);
        score = 0;
        row = startRow;
        column = startColumn;

        doMoveLeft(board);
    
    
    }
    
    
    public void doMoveLeft(char[][] board) throws IllegalMoveException{
        
        board = BoardUtilities.buildBoard(board);
        moveLeftTest(board);
        
        if(checkTakeLeft(board)){//testing for take in one single turn
            
            doMoves(board);

        }
        
        else{// can just move
        
            doMoveLeftLogic(board);
            kingMe(board);
           
           
        }
       
    }
    
    public void moveLeftTest(char[][] board) throws IllegalMoveException{

        if(this.column-1<0){
        
            throw new IllegalMoveException(myColour + " left move  column out of bounds");
        }       
        if(this.row + upOrDown<0 || this.row + upOrDown > 7){

            throw new IllegalMoveException(myColour + " left move  row out of bounds");

        }
        if(board[this.row + upOrDown][this.column -1] == mySymbol ){
        
            throw new IllegalMoveException(myColour + " left move will hit another " + myColour);
        
        }
        if(board[this.row + upOrDown][this.column -1] == myOtherSymbol){
        
             throw new IllegalMoveException(myColour + " left move will hit another " + myColour);
        }
       
        if(!checkTakeLeft(board) && isBlockedByOtherLeft(board)){
             throw new IllegalMoveException(myColour + " cannot move left ");
        }
            //this.row = this.row +1;// move UP the board
    
    }
    public void doMoveLeftLogic(char[][] board){
        
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        this.row = this.row + upOrDown;
        this.column = this.column -1;
        board[this.row][this.column] = mySymbol;
       branch(board);

    }
    /*Resets Checker for next move on it*/
    public void moveRight(char[][] board) throws IllegalMoveException{
        
        board = BoardUtilities.buildBoard(board);
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveRight(board);
    
    }
    
    public void doMoveRight(char[][] board) throws IllegalMoveException{
    
        board = BoardUtilities.buildBoard(board);
        moveRightTest(board);

       
        if(checkTakeRight(board)){//testing for take in one single turn

          
           takeRightLogic(board);
 
           doMoves(board);
           
        }
        else{
            
            doMoveRightLogic(board);
            kingMe(board);

        }

    }
    public void moveRightTest(char[][] board) throws IllegalMoveException{
    
         if(this.column +1 >7 ){
        
            throw new IllegalMoveException(myColour + " right move column out of bounds");
        }       
        if(this.row + upOrDown<0 || this.row + upOrDown > 7){

            throw new IllegalMoveException(myColour + " right move row out of bounds");
            
        }
        if(board[this.row + upOrDown][this.column +1] == mySymbol || board[this.row + upOrDown][this.column +1] == myOtherSymbol){
        
            throw new IllegalMoveException(myColour + " right move will hit another " + myColour );
        
        }
        if(!checkTakeRight(board) && isBlockedByOtherRight(board)){
        
            throw new IllegalMoveException(myColour + " Right move is blocked" );
        }
    }
    public void doMoveRightLogic(char[][] board){
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);;
        this.row = this.row + upOrDown;
        this.column = this.column +1;
        board[this.row][this.column] = mySymbol;
       branch(board);

    
    }
    ///// helper methods ////
    
    public void kingMe(char[][] board){
    
        if(mySymbol != blackKingChecker || mySymbol != redKingChecker){
         
             if(mySymbol == redChecker){
             
                 if(row==7){
                 
                     board[this.row][this.column] = redKingChecker;
                 
                 }
             }
             else if(mySymbol == blackChecker){
             
                 if(row==0){
                 
                      board[this.row][this.column] = blackKingChecker;
                     
                 }
             }
         
         }
    }
    public void takeLeftLogic(char[][] board){
        
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column); // boardSymbol(int i, int j)
        board[this.row + upOrDown][this.column-1] = FinalYearProject.boardSymbol(this.row + upOrDown, this.column -1);
        board[this.row + (upOrDown*2)][this.column-2] = mySymbol;
        this.row = this.row + upOrDown*2;
        this.column = this.column-2;

        score += point;
        
        kingMe(board);
       branch(board);
    
    }
    public void takeRightLogic(char[][] board){
        
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        board[this.row+ upOrDown][this.column +1] = FinalYearProject.boardSymbol(this.row + upOrDown, this.column+1);
        board[this.row + upOrDown*2][this.column+2] = mySymbol;
        this.row = this.row + upOrDown*2;
        this.column = this.column+2;

        score += point; 

        kingMe(board);
       branch(board);
    
    }
    public void doMoves(char[][] board){

        //NEED TO: check ifn we can move BOTH WAYS then BRANCH mahahahahhahahahah
       
        if(checkTakeLeft(board) && checkTakeRight(board)){

                int tempscore = score;
                int tempRow = row;
                int tempCol = column;
                char tempSymbol = mySymbol;
                
                char[][]tempboard = BoardUtilities.buildBoard(board);
                  
                takeLeftLogic(board);

               
                doMoves(board);
                score = tempscore;
                row = tempRow;
                column = tempCol;
                mySymbol = tempSymbol;
                board = tempboard;
                  
                takeRightLogic(board);

     
        }
        else if(checkTakeLeft(board)){

             takeLeftLogic(board);
             
        }
        else if(checkTakeRight(board)){

             takeRightLogic(board);
 
        }

        if(checkTakeLeft(board) || checkTakeRight(board)){// if can keep going

            doMoves(board);
        }


    }
    
    public void branch(char[][] board){
        int extra =0;
         if(column == 0 || column == 7){
        
            extra = extraPoint*4;
        }
        else if(row == 0 || row == 7){
        
            extra = extraPoint*4;
        }
        
        myNode.branch((score + extra), board);
    
    }
    /*Booleans*/
    public boolean isBlockedByOtherLeft(char[][] board){
    
        return board[this.row + upOrDown][this.column -1] == opponentKingSymbol || board[this.row + upOrDown][this.column -1] == opponentSymbol;
    }
    public boolean isBlockedByOtherRight(char[][] board){
    
        return board[this.row + upOrDown][this.column +1] == opponentKingSymbol || board[this.row + upOrDown][this.column +1] == opponentSymbol;
    }
    
    public boolean checkTakeLeft(char[][] board){
    
        
        return !(this.row+upOrDown<0) && !(this.row+upOrDown>7)&& !(this.column-1<0) && ((board[this.row+upOrDown][this.column-1] == opponentSymbol  || board[this.row+upOrDown][this.column-1] == opponentKingSymbol) && canTakeLeft(board)); 
    
    }
    public boolean canTakeLeft(char[][] board){

        return (!(this.row+upOrDown*2<0) && !(this.row+upOrDown*2>7) && !(this.column-2<0)) && (board[this.row+upOrDown*2][this.column-2] == 'B' || board[this.row+upOrDown*2][this.column-2] == 'W');
                   
    }
  
    
    public boolean checkTakeRight(char[][] board){
    
        return (!(this.row+upOrDown<0) && !(this.row+upOrDown>7) && !(this.column+1>7)) && ((board[this.row+upOrDown][this.column+1] == opponentSymbol || board[this.row+upOrDown][this.column+1] == opponentKingSymbol) && canTakeRight(board));
    
    }
    
    public boolean canTakeRight(char[][] board){
        
        return (!(this.row+upOrDown*2<0) && !(this.row+upOrDown*2>7) && !(this.column+2>7)) && (board[this.row+upOrDown*2][this.column+2] == 'B' || board[this.row+upOrDown*2][this.column+2] == 'W');
    
    }
    
    //King logic
   
   
    
   
}
