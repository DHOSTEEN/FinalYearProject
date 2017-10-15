/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import finalyearproject.FinalYearProject;
import finalyearproject.tester;

/**
 *
 * @author Daniel
 */
public class CheckerKing extends Checker{
    
    private Checker check;
    private int tempScore;
    private int tempRow;
    private int tempCol;

    
    public CheckerKing(int row, int column, boolean isRed, AlphaBetaTree myNode){
        
        /*EXPLAINATION
        
        first boolean value is wether it is a king moving normally - ie in the direction of normal checkers move. second is ONLY for
        */
        super(row, column, isRed, true, false, myNode);// to make normal move left/right
        
    }
    
    @Override
    public void moveLeft(char[][] board) throws IllegalMoveException{
        board = BoardUtilities.buildBoard(board);
        System.out.println("I R KING moving left");
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveLeft(board);
        
    
    }
    @Override
    public void doMoveLeft(char[][] board) throws IllegalMoveException{
        System.out.println("I R KING moving left");
        
        board = BoardUtilities.buildBoard(board);
        moveLeftTest(board);
       // System.out.println(blackChecker);
       
        if(checkTakeLeft(board)){//testing for take in one single turn

          
            takeLeftLogic(board);
            System.out.println("Is break");
            
            doKingMoves(board);
           
        }
        else{
            
            doMoveLeftLogic(board);
            System.out.println("I have branched, my score is: " + score);
            
            
            
        
        }
    
    
    }
    @Override
    public void moveRight(char[][] board) throws IllegalMoveException{
        
       
        board = BoardUtilities.buildBoard(board);
        System.out.println("I R KING moveing right");
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveRight(board);
    

    
    }
    @Override
    public void doMoveRight(char[][] board) throws IllegalMoveException{
        
        System.out.println("I R KING moving right");
        board = BoardUtilities.buildBoard(board);
        moveRightTest(board);
       // System.out.println(blackChecker);
       
        if(checkTakeRight(board)){//testing for take in one single turn

          
            takeRightLogic(board);
            System.out.println("Is break");
            
            doKingMoves(board);
           
        }
        else{
            
            doMoveRightLogic(board);
            System.out.println("I have branched, my score is: " + score);
            
        
        }
    
    }
    public void moveBackLeft(char[][] board) throws IllegalMoveException{
     
        board = BoardUtilities.buildBoard(board);
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveBackLeft(board);
    
        
    }
    private void doMoveBackLeft(char[][] board) throws IllegalMoveException{
        board = BoardUtilities.buildBoard(board);
        moveBackLeftTest(board);
       // System.out.println(blackChecker);
       
        if(checkTakeBackLeft(board)){//testing for take in one single turn

          
            takeBackLeftLogic(board);
            System.out.println("Is break");
            
            doKingMoves(board);
           
        }
        else{
            
            doMoveBackLeftLogic(board);
            System.out.println("I have branched, my score is: " + score);
            
        
        }
    }
    public void moveBackLeftTest(char[][] board) throws IllegalMoveException{
        
        // System.out.println(myOtherSymbol + "i r lefty");
        if(this.column-1<0){
        
            throw new IllegalMoveException(myColour + " left move  column out of bounds");
        }       
        if(this.row + (-upOrDown)<0 || this.row + (-upOrDown) > 7){

            throw new IllegalMoveException(myColour + " left move  row out of bounds");

        }
        if(board[this.row + (-upOrDown)][this.column -1] == mySymbol ){
        
            throw new IllegalMoveException(myColour + " left move will hit another " + myColour);
        
        }
        if(board[this.row + (-upOrDown)][this.column -1] == myOtherSymbol){
        
             throw new IllegalMoveException(myColour + " left move will hit another " + myColour);
        }
       
        if(!checkTakeBackLeft(board) && isBackBlockedByOtherLeft(board)){
             throw new IllegalMoveException(myColour + " cannot move left ");
        }
    
    }
     public void takeBackLeftLogic(char[][] board){// literally only thing that changes is the up or down.......
         
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column); // boardSymbol(int i, int j)
        board[this.row + (-upOrDown)][this.column-1] = FinalYearProject.boardSymbol(this.row + (-upOrDown), this.column -1);
        board[this.row + ((-upOrDown)*2)][this.column-2] = mySymbol;
        this.row = this.row + (-upOrDown)*2;
        this.column = this.column-2;

        score += point;
        tester.counter++;
    
    }
     public void doMoveBackLeftLogic(char[][] board){
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        this.row = this.row + (-upOrDown);
        this.column = this.column -1;
        board[this.row][this.column] = mySymbol;
        System.out.println("I have branched, my score is: " + score);
        tester.counter++;
     
     }
     
     /*Back Right*/
    public void moveBackRight(char[][] board) throws IllegalMoveException{
        
        board = BoardUtilities.buildBoard(board);
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveBackRight(board);
    
       
    
    }
    
    public void doMoveBackRight(char[][] board) throws IllegalMoveException{
        
        board = BoardUtilities.buildBoard(board);
        moveBackRightTest(board);
       // System.out.println(blackChecker);
       
        if(checkTakeBackRight(board)){//testing for take in one single turn

          
            takeBackRightLogic(board);
            System.out.println("Is break");
            
            doKingMoves(board);
           
        }
        else{
            
            doMoveBackRightLogic(board);
            System.out.println("I have branched, my score is: " + score);
            
        
        }
        
    
    }
    public void moveBackRightTest(char[][] board) throws IllegalMoveException{
         if(this.column +1 >7 ){
        
            throw new IllegalMoveException(myColour + " right move column out of bounds");
        }       
        if(this.row + (-upOrDown)<0 || this.row + (-upOrDown) > 7){

            throw new IllegalMoveException(myColour + " right move row out of bounds");
            
        }
        if(board[this.row + (-upOrDown)][this.column +1] == mySymbol || board[this.row + (-upOrDown)][this.column +1] == myOtherSymbol){
        
            throw new IllegalMoveException(myColour + " right move will hit another " + myColour );
        
        }
        if(!checkTakeBackRight(board) && isBackBlockedByOtherRight(board)){
        
            throw new IllegalMoveException(myColour + " Right move is blocked" );
        }
    
    }
     public void takeBackRightLogic(char[][] board){
         
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        board[this.row+ (-upOrDown)][this.column +1] = FinalYearProject.boardSymbol(this.row + (-upOrDown), this.column+1);
        board[this.row + (-upOrDown)*2][this.column+2] = mySymbol;
        this.row = this.row + (-upOrDown)*2;
        this.column = this.column+2;

        score += point; 
        tester.counter++;
    
    }
     private void doMoveBackRightLogic(char[][] board){
         
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);;
        this.row = this.row + (-upOrDown);
        this.column = this.column +1;
        board[this.row][this.column] = mySymbol;
        tester.counter++;
     
     }
   private void backUpValues(){
       
        tempScore = score;
        tempRow = row;
        tempCol = column;
   }
   private void resetValues(){
       
        score = tempScore;
        row = tempRow;
        column = tempCol;

   }
   
    
    public void doKingMoves(char[][] board){
        //checkleft == a
        //checkright == b
        //checkKingLeft == c
        //checkKingright == d
        if(checkTakeLeft(board) && checkTakeRight(board) && checkTakeBackLeft(board) && checkTakeBackRight(board)){//abcd - impossible
 
            backUpValues();
                 // char tempSymbol = mySymbol;
            //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
              System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
          
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
              System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
              System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
        
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
              System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
        }
          
        else if(checkTakeLeft(board) && checkTakeRight(board) && checkTakeBackLeft(board)){//abc
            
            backUpValues();
            //board = BoardUtilities.buildBoard(board);
                 // char tempSymbol = mySymbol;
            takeLeftLogic(board);//do a
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
         
        
        }
        else if(checkTakeLeft(board) && checkTakeRight(board) && checkTakeBackRight(board)){//abd
            
           backUpValues();
           //board = BoardUtilities.buildBoard(board);
                 // char tempSymbol = mySymbol;
            takeLeftLogic(board);//do a
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            
            doKingMoves(board);
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
          
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
        
        }
        else if(checkTakeRight(board) && checkTakeBackLeft(board) && checkTakeBackRight(board)){//bcd
           
            backUpValues();

            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
       System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
             // <---- HERE IS ISSUE WHY!?
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
        }
        else if(checkTakeLeft(board) && checkTakeBackLeft(board) && checkTakeBackRight(board)){//acd
            
            backUpValues();

            //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
        
        }
        else if(checkTakeLeft(board) && checkTakeRight(board)){//ab -- handeled by checker?
            
            backUpValues();
                 // char tempSymbol = mySymbol;
                 //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
          
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
          
            
           
        
        }
        else if(checkTakeLeft(board) && checkTakeBackLeft(board)){//ac
            
            backUpValues();
            //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            

            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
           
        }
        else if(checkTakeLeft(board) && checkTakeBackRight(board)){//ad
            
            backUpValues();
            //board = BoardUtilities.buildBoard(board);
                 // char tempSymbol = mySymbol;
            takeLeftLogic(board);//do a
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
            
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
        }
        else if(checkTakeRight(board) && checkTakeBackLeft(board)){//bc
            
            backUpValues();

            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
        
        }
        else if(checkTakeRight(board) && checkTakeBackRight(board)){//bd
            
            backUpValues();
            //board = BoardUtilities.buildBoard(board);
            

            takeRightLogic(board);//do b
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
          
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
           
        
        }
        else if(checkTakeBackLeft(board) && checkTakeBackRight(board)){//cd
            
            backUpValues();

            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
            resetValues();
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
             System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            doKingMoves(board);
            
        
        }
        else if(checkTakeLeft(board)){//a
            
           // //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
           // doKingMoves(board);
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            
        }
        else if(checkTakeRight(board)){//b
           
           // //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
           // doKingMoves(board);
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
            
        }
        else if(checkTakeBackLeft(board)){//c
       
           // //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
           // doKingMoves(board);
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
        
        }
        else if(checkTakeBackRight(board)){//d
            
           // //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
           // doKingMoves(board);
            System.out.println("King has branched,  my score is: " + score + " row: " + row + " column: " + column);
        }
        
        if(checkTakeLeft(board) || checkTakeRight(board) || checkTakeBackLeft(board) || checkTakeBackRight(board)){// if can keep going
        
             //board = BoardUtilities.buildBoard(board);
           
            System.out.println("Can still move");
            doKingMoves(board);
        }
      
    }
    
   
    
   
    
    //booleans
    
    public boolean checkTakeBackLeft(char[][] board){
        
         return !(this.row+(-upOrDown)<0) && !(this.row+(-upOrDown)>7)&& !(this.column-1<0) && ((board[this.row+(-upOrDown)][this.column-1] == opponentSymbol  || board[this.row+(-upOrDown)][this.column-1] == opponentKingSymbol) && canTakeBackLeft(board)); 

    }
    public boolean canTakeBackLeft(char[][] board){
         return (!(this.row+(-upOrDown)*2<0) && !(this.row+(-upOrDown)*2>7) && !(this.column-2<0)) && (board[this.row+(-upOrDown)*2][this.column-2] == 'B' || board[this.row+(-upOrDown)*2][this.column-2] == 'W');

    }
    public boolean checkTakeBackRight(char[][] board){
          return (!(this.row+(-upOrDown)<0) && !(this.row+(-upOrDown)>7) && !(this.column+1>7)) && ((board[this.row+(-upOrDown)][this.column+1] == opponentSymbol || board[this.row+(-upOrDown)][this.column+1] == opponentKingSymbol) && canTakeBackRight(board));
    
    }
    public boolean canTakeBackRight(char[][] board){

        return (!(this.row+(-upOrDown)*2<0) && !(this.row+(-upOrDown)*2>7) && !(this.column+2>7)) && (board[this.row+(-upOrDown)*2][this.column+2] == 'B' || board[this.row+(-upOrDown)*2][this.column+2] == 'W');
    }
    public boolean isBackBlockedByOtherLeft(char[][] board){
    
        return board[this.row + (-upOrDown)][this.column -1] == opponentKingSymbol || board[this.row + (-upOrDown)][this.column -1] == opponentSymbol;
    }
    public boolean isBackBlockedByOtherRight(char[][] board){
    
        return board[this.row + (-upOrDown)][this.column +1] == opponentKingSymbol || board[this.row + (-upOrDown)][this.column +1] == opponentSymbol;
    }
   
    
}