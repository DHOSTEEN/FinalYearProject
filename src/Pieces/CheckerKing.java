/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import finalyearproject.FinalYearProject;
import java.util.ArrayList;


/**
 *
 * @author Daniel
 */
public class CheckerKing extends Checker{
    


    
    public CheckerKing(int row, int column, boolean isRed, AlphaBetaTree myNode){
        
        /*EXPLAINATION
        
        first boolean value is wether it is a king moving normally - ie in the direction of normal checkers move. second is ONLY for
        */
        super(row, column, isRed, true, false, myNode);// to make normal move left/right
        
    }
    
    @Override
    public void moveLeft(char[][] board) throws IllegalMoveException{
        board = BoardUtilities.buildBoard(board);
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveLeft(board);
        
    
    }
    @Override
    public void doMoveLeft(char[][] board) throws IllegalMoveException{
        ////System.out.println("I R KING moving left");
        
        board = BoardUtilities.buildBoard(board);
        moveLeftTest(board);
       // //System.out.println(blackChecker);
       
        if(checkTakeLeft(board)){//testing for take in one single turn

          
            takeLeftLogic(board);
            //System.out.println("First Branch: row: " + row + " column: " + column + " score: " + score );
           
            
            doKingMoves(board);
           
        }
        else{
            
            doMoveLeftLogic(board);
            //System.out.println("I have branched, my score is: " + score);
           
            
            
            
        
        }
    
    
    }
    @Override
    public void moveRight(char[][] board) throws IllegalMoveException{
        
       
        board = BoardUtilities.buildBoard(board);
        //System.out.println("I R KING moveing right");
        score = 0;
        row = startRow;
        column = startColumn;
    
        doMoveRight(board);
    

    
    }
    @Override
    public void doMoveRight(char[][] board) throws IllegalMoveException{
        
        //System.out.println("I R KING moving right");
        board = BoardUtilities.buildBoard(board);
        moveRightTest(board);
       // //System.out.println(blackChecker);
       
        if(checkTakeRight(board)){//testing for take in one single turn

          
            takeRightLogic(board);
             //System.out.println("First Branch: row: " + row + " column: " + column + " score: " + score );
           
            doKingMoves(board);
           
        }
        else{
            
            doMoveRightLogic(board);
            //System.out.println("I have branched, my score is: " + score);
           
        
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
       // //System.out.println(blackChecker);
       
        if(checkTakeBackLeft(board)){//testing for take in one single turn

          
            takeBackLeftLogic(board);
            //System.out.println("First Branch: row: " + row + " column: " + column + " score: " + score );
           
            doKingMoves(board);
           
        }
        else{
            
            doMoveBackLeftLogic(board);
            //System.out.println("I have branched, my score is: " + score);
           
        
        }
    }
    public void moveBackLeftTest(char[][] board) throws IllegalMoveException{
        
        // //System.out.println(myOtherSymbol + "i r lefty");
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
         
         if(!isMultiMove){allMoves = new ArrayList<>();}
         fromMove = new MoveCoordinates(this.row, this.column, !isTaken);
         allMoves.add(fromMove);
         
         takenMove = new MoveCoordinates(this.row + (-upOrDown), this.column-1, isTaken);
         allMoves.add(takenMove);
         board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column); // boardSymbol(int i, int j)
        board[this.row + (-upOrDown)][this.column-1] = FinalYearProject.boardSymbol(this.row + (-upOrDown), this.column -1);
        board[this.row + ((-upOrDown)*2)][this.column-2] = mySymbol;
        this.row = this.row + (-upOrDown)*2;
        this.column = this.column-2;
        
        toMove = new MoveCoordinates(this.row, this.column, !isTaken);
        allMoves.add(toMove);

        score += point;
        branch(board);
       //CheckerLogicTest.counter++;
    
    }
     public void doMoveBackLeftLogic(char[][] board){
         
         if(!isMultiMove){allMoves = new ArrayList<>();}
         fromMove = new MoveCoordinates(this.row, this.column, !isTaken);
         allMoves.add(fromMove);
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        this.row = this.row + (-upOrDown);
        this.column = this.column -1;
        board[this.row][this.column] = mySymbol;
        
        toMove = new MoveCoordinates(this.row, this.column, !isTaken);
        allMoves.add(toMove);
        branch(board);
        ////System.out.println("I have branched, my score is: " + score);
       //CheckerLogicTest.counter++;
     
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
       // //System.out.println(blackChecker);
       
        if(checkTakeBackRight(board)){//testing for take in one single turn

          
            takeBackRightLogic(board);
            //System.out.println("First Branch: row: " + row + " column: " + column + " score: " + score );
           
            doKingMoves(board);
           
        }
        else{
            
            doMoveBackRightLogic(board);
            //System.out.println("I have branched, my score is: " + score);
           
            
        
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
         
         if(!isMultiMove){allMoves = new ArrayList<>();}
         fromMove = new MoveCoordinates(this.row, this.column, !isTaken);
         allMoves.add(fromMove);
         
         takenMove = new MoveCoordinates(this.row + (-upOrDown), this.column +1, isTaken);
         allMoves.add(takenMove);
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        board[this.row+ (-upOrDown)][this.column +1] = FinalYearProject.boardSymbol(this.row + (-upOrDown), this.column+1);
        board[this.row + (-upOrDown)*2][this.column+2] = mySymbol;
        this.row = this.row + (-upOrDown)*2;
        this.column = this.column+2;

        toMove = new MoveCoordinates(this.row,this.column, !isTaken);
        allMoves.add(toMove);
        score += point; 
        branch(board);
       //CheckerLogicTest.counter++;
    
    }
     private void doMoveBackRightLogic(char[][] board){
         
         if(!isMultiMove){allMoves = new ArrayList<>();}
         fromMove = new MoveCoordinates(this.row, this.column, !isTaken);
         allMoves.add(fromMove);
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);;
        this.row = this.row + (-upOrDown);
        this.column = this.column +1;
        board[this.row][this.column] = mySymbol;
        toMove = new MoveCoordinates(this.row, this.column, !isTaken);
        allMoves.add(toMove);
        branch(board);
       //CheckerLogicTest.counter++;
     
     }
  /* private void backUpValues(){
       
        this.tempScore = this.score;
        this.tempRow = this.row;
        this.tempCol = this.column;
   }
   private void resetValues(){
       
        this.score = this.tempScore;
        this.row = this.tempRow;
        this.column = this.tempCol;

   }*/
   
    
    public void doKingMoves(char[][] board){
        //checkleft == a
        //checkright == b
        //checkKingLeft == c
        //checkKingright == d
        if(checkTakeLeft(board) && checkTakeRight(board) && checkTakeBackLeft(board) && checkTakeBackRight(board)){//abcd - impossible
 
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
        
                 // char tempSymbol = mySymbol;
            //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
              //System.out.println("King has done the impossible!!!");
            doKingMoves(board);
          
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
                      //System.out.println("King has done the impossible!!!");
            doKingMoves(board);
           
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
                      //System.out.println("King has done the impossible!!!");
            doKingMoves(board);
        
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
                    //System.out.println("King has done the impossible!!!");
            //doKingMoves(board);
        }
          
        else if(checkTakeLeft(board) && checkTakeRight(board) && checkTakeBackLeft(board)){//abc
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
            //System.out.println("King has branched on 'abc");
            //board = BoardUtilities.buildBoard(board);
                 // char tempSymbol = mySymbol;
            takeLeftLogic(board);//do a
             //System.out.println("King has branched first time on 'abc', row: " + row + " column: " + column + " my score is: " + score );
             
            doKingMoves(board);
           
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
             //System.out.println("King has branched second time on 'abc', row: " + row + " column: " + column + " my score is: " + score );
            doKingMoves(board);
           
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
            //System.out.println("King has branched third time one 'abc', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
           
            
      
         
        
        }
        else if(checkTakeLeft(board) && checkTakeRight(board) && checkTakeBackRight(board)){//abd
            
          int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
             //System.out.println("King has branched on 'abd");
           //board = BoardUtilities.buildBoard(board);
                 // char tempSymbol = mySymbol;
            takeLeftLogic(board);//do a
       //System.out.println("King has branched first time on 'abd', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
       //System.out.println("King has branched second time on 'abd', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
          
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
       //System.out.println("King has branched third time on 'abd', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
            
        
        }
        else if(checkTakeRight(board) && checkTakeBackLeft(board) && checkTakeBackRight(board)){//bcd
           
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
             //System.out.println("King has branched on 'bcd");

            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
  //System.out.println("King has branched first time on 'bcd', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
           
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
        //System.out.println("King has branched second time on 'bcd, row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
             // <---- HERE IS ISSUE WHY!?
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
       //System.out.println("King has branched third time on 'bcd', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
           
        }
        else if(checkTakeLeft(board) && checkTakeBackLeft(board) && checkTakeBackRight(board)){//acd
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
            ////System.out.println("Values in 'acd' is: " + score + " row: " + row + " column: " + column);
            // //System.out.println("BackUp Values in 'acd' is: " + tempScore + " row: " + tempRow + " column: " + tempCol);

            //System.out.println("King has branched on 'acd'");
            //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
        //System.out.println("King has branched first time on 'acd', row: " + row + " column: " + column + " my score is: " + score);
             //BoardUtilities.printBoard(board);
            doKingMoves(board);
           
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
              ////System.out.println("King back in 'acd' - values are score: " + score + " row: " + row + " column: " + column);
             // //System.out.println("BackUp Values in 'acd' is: " + tempScore + " row: " + tempRow + " column: " + tempCol);
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
             //System.out.println("King has branched second time on 'acd', row: " + row + " column: " + column + " my score is: " + score);
             //BoardUtilities.printBoard(board);
            doKingMoves(board);
   
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
             ////System.out.println("King back in 'acd' - values are score: " + score + " row: " + row + " column: " + column);
            // //System.out.println("BackUp Values in 'acd' is: " + tempScore + " row: " + tempRow + " column: " + tempCol);
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
        //System.out.println("King has branched third time on 'acd', row: " + row + " column: " + column + " my score is: " + score);
             //BoardUtilities.printBoard(board);
            //doKingMoves(board);
            
        
        }
        else if(checkTakeLeft(board) && checkTakeRight(board)){//ab
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
           //System.out.println("King has branched on 'ab");
                 // char tempSymbol = mySymbol;
                 //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
        //System.out.println("King has branched first time on 'ab', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
          
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
        //System.out.println("King has branched second time on 'ab', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
          
            
           
        
        }
        else if(checkTakeLeft(board) && checkTakeBackLeft(board)){//ac
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
              //System.out.println("King has branched on 'ac");
            //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
        //System.out.println("King has branched first time on 'ac', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
            

           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
        //System.out.println("King has branched second time on 'ac', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
            
           
        }
        else if(checkTakeLeft(board) && checkTakeBackRight(board)){//ad
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
              //System.out.println("King has branched on 'ad");
            //board = BoardUtilities.buildBoard(board);
                 // char tempSymbol = mySymbol;
            takeLeftLogic(board);//do a
        //System.out.println("King has branched first time on 'ad', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);

           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
        //System.out.println("King has branched second time on 'ad', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
            
        }
        else if(checkTakeRight(board) && checkTakeBackLeft(board)){//bc
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
  //System.out.println("King has branched on 'bc'");
            
            //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
        //System.out.println("King has branched first time on 'bc', row: " + row + " column: " + column + " my score is: " + score);
            doKingMoves(board);
           
            
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
        //System.out.println("King has branched second time on 'bc', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
            
        
        }
        else if(checkTakeRight(board) && checkTakeBackRight(board)){//bd
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
            //board = BoardUtilities.buildBoard(board);
              //System.out.println("King has branched on 'bd'");

            takeRightLogic(board);//do b
       //System.out.println("King has branched first time on 'bd', row: " + row + " column: " + column + " my score is: " + score);
          
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
        //System.out.println("King has branched second time on 'bd', row: " + row + " column: " + column + " my score is: " + score);
            //doKingMoves(board);
           
        
        }
        else if(checkTakeBackLeft(board) && checkTakeBackRight(board)){//cd
            
           int tempScore = score;            int tempRow = row;            int tempCol = column;       char[][]tempBoard = BoardUtilities.buildBoard(board);
              //System.out.println("King has branched on 'cd'");

            //board = BoardUtilities.buildBoard(board);
              ////System.out.println("Before branch at cd: ");
            //BoardUtilities.printBoard(board);
            takeBackLeftLogic(board);//do c
        //System.out.println("King has branched first time on 'cd', row: " + row + " column: " + column + " my score is: " + score);
          
            doKingMoves(board);
           // //System.out.println("After branch at cd:");
            //BoardUtilities.printBoard(board);
           score = tempScore;           row = tempRow;           column = tempCol;     board = tempBoard;
            //board = BoardUtilities.buildBoard(board);
             ////System.out.println("Before branch at cd: ");
            //BoardUtilities.printBoard(board);
            takeBackRightLogic(board);// do d
        //System.out.println("King has branched second time on 'cd', row: " + row + " column: " + column + " my score is: " + score);
       
            //doKingMoves(board);
            ////System.out.println("After branch at cd: row: " + row + " column: " + column);
            //BoardUtilities.printBoard(board);
            
        
        }
        else if(checkTakeLeft(board)){//a
            
           // //board = BoardUtilities.buildBoard(board);
            takeLeftLogic(board);//do a
           // doKingMoves(board);
       //System.out.println("King has branched on 'a', row: " + row + " column: " + column + " my score is: " + score);
            
        }
        else if(checkTakeRight(board)){//b
           
           // //board = BoardUtilities.buildBoard(board);
            takeRightLogic(board);//do b
           // doKingMoves(board);
            //System.out.println("King has branched on 'b', row: " + row + " column: " + column + " my score is: " + score);
            
        }
        else if(checkTakeBackLeft(board)){//c
       
           // //board = BoardUtilities.buildBoard(board);
            takeBackLeftLogic(board);//do c
           // doKingMoves(board);
       //System.out.println("King has branched on 'c', row: " + row + " column: " + column + " my score is: " + score);
        
        }
        else if(checkTakeBackRight(board)){//d
            
           // //board = BoardUtilities.buildBoard(board);
            takeBackRightLogic(board);// do d
           // doKingMoves(board);
       //System.out.println("King has branched on 'd', row: " + row + " column: " + column + " my score is: " + score);
        }
        
        if(checkTakeLeft(board) || checkTakeRight(board) || checkTakeBackLeft(board) || checkTakeBackRight(board)){// if can keep going
        
             //board = BoardUtilities.buildBoard(board);
           
            //System.out.println("Can still move, row : " + row + " column: " + column);
            //BoardUtilities.printBoard(board);
            isMultiMove = true;
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
