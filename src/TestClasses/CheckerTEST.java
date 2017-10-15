/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;


import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import Pieces.IllegalMoveException;
import finalyearproject.FinalYearProject;
import finalyearproject.tester;

/**
 *
 * @author Daniel
 */
public class CheckerTEST {
    
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
    
   protected final char redChecker = 'O';
   protected final char redKingChecker = 'E';
   protected final char blackChecker = 'X';
   protected final char blackKingChecker = 'K';
   private CheckerTEST check;
   private int tempScore;
   private int tempRow;
   private int tempCol;
   
   protected boolean isFirstMove;
   
   protected AlphaBetaTree myNode;
   
    
    public CheckerTEST(int row, int column, boolean isRed, AlphaBetaTree myNode){
        
        startRow = row;
        startColumn = column;
        this.myNode = myNode;
        setVariables(isRed, false, false);
    
    }
    
    public CheckerTEST(int row, int column, boolean isRed, boolean isKing, boolean isBackwardsKing, AlphaBetaTree myNode){

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
            point = 2;
            myColour = "RED KING";
        
        }
        else if(!isRed && isKing){
            
            mySymbol = blackKingChecker;
            myOtherSymbol = blackChecker;
            opponentSymbol = redChecker;
            opponentKingSymbol = redKingChecker;
            upOrDown = 1;
            point = -2;
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
            point = 2;
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
            point = -2;
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

            System.out.println("I can take left");
            System.out.println("My row is: " + row + " my coloumn is: " + column);
            takeLeftLogic(board);
            System.out.println("My row is: " + row + " my coloumn is: " + column);
            System.out.println("Checker has branched, row: " + row + " column: " + column + " my score is: " + score);
            
           
            doMoves(board);
            

        }
        
        else{// can just move
        
            doMoveLeftLogic(board);
            kingMe(board);
            
        }
        //if(this.row == && this.column == 0){
        //}
        //kingMe(board);
        
        //return new BoardScorePair(score, board);
       
       
        ////BoardUtilities.printBoard(board);
       //myNode.branch(score, board);
       // new AlphaBetaTree(myNode, !(myNode.isIsMaxNode()), myNode.getDepthLevel() + 1, myNode.getAlpha(), myNode.getBeta(), myNode.getCurrentScore() + score, board).getBestMove();
        //(AlphaBetaTree parent, boolean myType, int newDepthLevel,int passedAlpha, int passedBeta, int culmativeScore, char[][] passedBoard
       // AlphaBetaNode newNode = new AlphaBetaNode(myNode, board, score, myNode.depthLevel+1, !(myNode.isMaxNode));
         //return newNode.getBestMove(); // create a Node with a parent attached. will carry Culmative score until seesation point. then fire up the tree
                    //myNode.deepness <--- how far down the algorithm u are
        //this.column =this.column -1;
        //update Table info and/or GUI
    
    }
    
    public void moveLeftTest(char[][] board) throws IllegalMoveException{
        
        //test to see if move is applicable -- (redundant?)
        
       
       // System.out.println(myOtherSymbol + "i r lefty");
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
        //System.out.println("I have branched, my score is: " + score);
        CheckerLogicTest.counter++;
    
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
       // System.out.println(blackChecker);
       
        if(checkTakeRight(board)){//testing for take in one single turn

          
           takeRightLogic(board);
           System.out.println("Checker has branched, row: " + row + " column: " + column + " my score is: " + score);
            
            doMoves(board);
           
        }
        else{
            
            doMoveRightLogic(board);
            kingMe(board);
           System.out.println("Checker has branched, row: " + row + " column: " + column + " my score is: " + score);
            
            
        
        }

        // AlphaBetaNode newNode = new AlphaBetaNode(myNode, board, score, myNode.depthLevel+1, !(myNode.isMaxNode));
         // makes a king if appropriate
         
         // probably just need to brach here. is awkward buttttttttttttttttttttt how else?
         
        
          //BoardUtilities.printBoard(board);
        // myNode.branch(score, board);
        //new AlphaBetaTree(myNode, !(myNode.isIsMaxNode()), myNode.getDepthLevel() + 1, myNode.getAlpha(), myNode.getBeta(), myNode.getCurrentScore() + score, board).getBestMove();

        // return new BoardScorePair(score, board);
       
         //return newNode.getBestMove(); 
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
        CheckerLogicTest.counter++;
    
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
        CheckerLogicTest.counter++;
        kingMe(board);
    
    }
    public void takeRightLogic(char[][] board){
        
        board[this.row][this.column] = FinalYearProject.boardSymbol(this.row, this.column);
        board[this.row+ upOrDown][this.column +1] = FinalYearProject.boardSymbol(this.row + upOrDown, this.column+1);
        board[this.row + upOrDown*2][this.column+2] = mySymbol;
        this.row = this.row + upOrDown*2;
        this.column = this.column+2;

        score += point; 
        CheckerLogicTest.counter++;
        kingMe(board);
    
    }
    public void doMoves(char[][] board){

        //NEED TO: check ifn we can move BOTH WAYS then BRANCH mahahahahhahahahah
       
        if(checkTakeLeft(board) && checkTakeRight(board)){

                 //System.out.println("I can take both - I has branched: " +score);
                 //need to branch here as well?
                 //System.out.println("I have branched,  my score is: " + score);
                  //BoardUtilities.printBoard(board);
                  int tempscore = score;
                  int tempRow = row;
                  int tempCol = column;
                  char tempSymbol = mySymbol;
                  char[][]tempboard = BoardUtilities.buildBoard(board);
                  
                  takeLeftLogic(board);
              System.out.println("Checker has at double choice branched, row: " + row + " column: " + column + " my score is: " + score);
            
                  score = tempscore;
                  row = tempRow;
                  column = tempCol;
                  mySymbol = tempSymbol;
                  board = tempboard;
                  
                  takeRightLogic(board);
                  System.out.println("Checker has at double choice branched, row: " + row + " column: " + column + " my score is: " + score);
            
                // System.out.println("I have branched, my score is: " + score);
     
        }
        else if(checkTakeLeft(board)){

           
            
            //myNode.branch(score, board);
             //BoardUtilities.printBoard(board);
             takeLeftLogic(board);
              System.out.println("Checker has branched, row: " + row + " column: " + column + " my score is: " + score);
            //System.out.println("EXPLAIN is BLACK to me how the score of" + score + " is returned");
     
             //BoardUtilities.printBoard(board);
           // System.out.println("EXPLAIN is BLACK to me how the score of " + score + " is returned");
        }
        else if(checkTakeRight(board)){

            
            
             //BoardUtilities.printBoard(board);
            //myNode.branch(score, board);
            
             takeRightLogic(board);
              System.out.println("Checker has branched, row: " + row + " column: " + column + " my score is: " + score);
            //BoardUtilities.printBoard(board);
            //System.out.println("EXPLAIN is BLACK to me how the score of " + score + " is returned");

        }
        
        System.out.println("right move is: "+ checkTakeLeft(board) + " right is " + checkTakeRight(board));
        if(checkTakeLeft(board) || checkTakeRight(board)){// if can keep going
        
            System.out.println("Can still move");
            doMoves(board);
        }


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
