/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import Board.BoardUtilities;

/**
 *
 * @author Daniel
 */
public class CheckerMoveAlphaBetaTree {
    
    private Integer alpha;
    private Integer beta;
    private int currentScore;
    private Integer nodeValue;
    private CheckerMoveAlphaBetaTree parentNode;
    private boolean isMaxNode;
    private char[][] board;
    private RedChecker redChecker;

    public BoardScorePair getBestMove() {
        return bestMove;
    }
    private BoardScorePair bestMove;
    
    char[][] checkBoard;
    
    public CheckerMoveAlphaBetaTree(char[][] board, RedChecker checker){
        
        alpha = null;
        beta = null;
        currentScore = 0;
        isMaxNode = true;
        this.board = board;
        redChecker = checker;
    
    }
    public CheckerMoveAlphaBetaTree(CheckerMoveAlphaBetaTree parent, char[][] board, int score ,boolean isMaxNode, RedChecker checker){
    
        currentScore += score;
        parentNode = parent;
        this.board = board;
        this.isMaxNode = isMaxNode;
        redChecker = checker;
    }
    public BoardScorePair move(){
    
        bestCheckerMove();
        return bestMove;
    }
    public BoardScorePair bestCheckerMove(){
    
        boolean test = true;
        BoardScorePair leftMove = new BoardScorePair(0, board);
        BoardScorePair rightMove = new BoardScorePair(0, board);
        try{
            while(test || leftMove.isAbleToMove() || rightMove.isAbleToMove()){
                
                test = false;
                System.out.println("IS IT GOIN");
                checkBoard = BoardUtilities.buildBoard(this.board);
                leftMove = takeLeft(checkBoard);
                //currentScore += leftMove.getScore();
                //here goes alpha beta pruning//
               if(isMaxNode){
                    if(parentNode != null){


                        //System.out.println(parentNode.beta);
                        if(parentNode.beta != null){// can only work with actual values, not infinity
                            if(parentNode.beta>=nodeValue && nodeValue != null){//because the parent is a Min node

                                System.out.println("DOES IT BREAK");
                                break;//effectivly FK the rest of the branches
                            }

                         }
                    
                        if(!leftMove.isAbleToMove()){

                            if(parentNode.nodeValue == null || nodeValue < parentNode.nodeValue){

                                parentNode.nodeValue = nodeValue;
                                parentNode.bestMove = leftMove;
                            }

                        }
                    }
             
                    branch(leftMove.getBoard(),leftMove.getScore(), leftMove.getRow(), leftMove.getColumn());
                
                  /// HAVE A BRANCH HERE ||||
                  
                    checkBoard= BoardUtilities.buildBoard(this.board);
                    rightMove = takeRight(checkBoard);

                    if(parentNode != null){


                        //System.out.println(parentNode.beta);
                        if(parentNode.beta != null){// can only work with actual values, not infinity
                            if(parentNode.beta>=nodeValue && nodeValue != null){//because the parent is a Min node

                                System.out.println("DOES IT BREAK");
                                break;//effectivly FK the rest of the branches
                            }

                         }
                    
                        if(!leftMove.isAbleToMove()){

                            if(parentNode.nodeValue == null || nodeValue < parentNode.nodeValue){

                                parentNode.nodeValue = nodeValue;
                                parentNode.bestMove = leftMove;
                            }

                        }
                    }
             
                if(parentNode != null){
                //System.out.println("is not leaf but finished its children");
                    if((nodeValue < parentNode.nodeValue || parentNode.nodeValue == 616)){/// FUKKKKKK YOPUUUUUUIKSGDJGDSJIUG
                        System.out.println("whhhhhhhhhhhhhhy");
                        parentNode.nodeValue = nodeValue;
                        parentNode.bestMove = bestMove;
                    }
                }
                if(!rightMove.isAbleToMove()){//is a leaf

                    if(parentNode.nodeValue == null || nodeValue < parentNode.nodeValue){

                        parentNode.nodeValue = nodeValue;
                        parentNode.bestMove = rightMove;
                    }

                }
 
                if(parentNode != null){
                    if(isMaxNode){
                                //System.out.println(parentNode.beta);
                                if(parentNode.beta != null){// can only work with actual values, not infinity
                                    if(parentNode.beta>=nodeValue && nodeValue != null){//because the parent is a Min node

                                        System.out.println("DOES IT BREAK");
                                        break;//effectivly FK the rest of the branches
                                    }
                                   
                                }
                    }
                
                    
                
                }
                /////BRANCH/////
                branch(rightMove.getBoard(),rightMove.getScore(), rightMove.getRow(), rightMove.getColumn());
                
                
                    if(parentNode != null){
                    //System.out.println("is not leaf but finished its children");
                        if((nodeValue < parentNode.nodeValue || parentNode.nodeValue == null)){/// FUKKKKKK YOPUUUUUUIKSGDJGDSJIUG
                            System.out.println("whhhhhhhhhhhhhhy");
                            parentNode.nodeValue = nodeValue;
                            parentNode.bestMove = bestMove;
                        }
                    }
                    if(!rightMove.isAbleToMove()){//is a leaf
                    
                        if(parentNode.nodeValue == null || nodeValue < parentNode.nodeValue){
                        
                            parentNode.nodeValue = nodeValue;
                            parentNode.bestMove = rightMove;
                        }
                    
                    }
                
               }// end of MAX
               else if(!isMaxNode){
                   
                checkBoard = BoardUtilities.buildBoard(this.board);
                leftMove = takeLeft(checkBoard);
                            //decisionScore += // is saved
                            //System.out.println(decisionScore + "<--- the score for BLACK LEFT");
                            
                    if(parentNode != null){

                        if(parentNode.alpha != -1000){// can only work with actual values, not infinity
                            if(parentNode.alpha<=nodeValue && nodeValue != 616){//because the parent is a MAX node

                                System.out.println("DOES IT BREAK");
                                break;//effectivly FK the rest of the branches
                            }
                        }

                        if(!leftMove.isAbleToMove()){

                            if(parentNode.nodeValue == null|| currentScore>parentNode.nodeValue){

                                parentNode.nodeValue = currentScore;

                            }
                        }

                    }
                           // if it is NOT a leaf we can branch
                    branch(leftMove.getBoard(),leftMove.getScore(), leftMove.getRow(), leftMove.getColumn());// makes children
                            
                            if(parentNode != null){
                                if(nodeValue > parentNode.nodeValue || parentNode.nodeValue == 616){

                                    System.out.println("Black tthing changed");
                                    parentNode.nodeValue = nodeValue;
                                    parentNode.bestMove = bestMove;;
                                }
                            }
                              
                            //REPEAT HERE FOR move LEFT option - kings ignored for now
                            
                           checkBoard = BoardUtilities.buildBoard(this.board);
                            rightMove = takeRight(checkBoard);
                            //System.out.println(decisionScore + "<--- the score for BLACK RIGHT");

                            if(parentNode != null){
                
                                if(parentNode.alpha != null){// can only work with actual values, not infinity
                                    if(parentNode.alpha<=nodeValue && nodeValue != 616){//because the parent is a MAX node

                                       // System.out.println("DOES IT BREAK");
                                        break;//effectivly FK the rest of the branches
                                    }
                                }
                               
                                if(!rightMove.isAbleToMove()){
                                
                                    if(parentNode.nodeValue == null || currentScore>parentNode.nodeValue){
                                    
                                        System.out.println("GOT 2 HERE");
                                        parentNode.nodeValue = currentScore;
                                                
                                        
                                    }
                                }
                                
                            }
                           
                                branch(rightMove.getBoard(),rightMove.getScore(), rightMove.getRow(), rightMove.getColumn());
                            
                            if(parentNode != null){
                                if(nodeValue > parentNode.nodeValue || parentNode.nodeValue == null){

                                    System.out.println("Black tthing changed");
                                    parentNode.nodeValue = nodeValue;
                                    parentNode.bestMove = bestMove;
                                }
                            }
                        
                        
                    
               }
            }// end of while loop
            
            if(parentNode != null){
                propergateUp();
            }
  
        }catch(IllegalMoveException e){}
        
    return bestMove;
    }
    
    public void maxMoveLeft(){
    
    
    }
    
    public void propergateUp(){
    

        if(parentNode.parentNode != null){
           // System.out.println(parentNode.parentNode.isMaxNode);
            //System.out.println(!parentNode.parentNode.isMaxNode);
           // System.out.println("is true");
            if(parentNode.parentNode.isMaxNode){
               // System.out.println("IS TRUE");
                //System.out.println(parentNode.nodeValue);
                if(parentNode.nodeValue != null){// if the value has been updated

                    System.out.println("Alpha changed!");
                    parentNode.parentNode.alpha = parentNode.nodeValue; //give the MAX node the value as a alpha
                    //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                }
            }
            else{

                 if(parentNode.nodeValue != null){// if the value has been updated

                     System.out.println("Beta changed!");
                    parentNode.parentNode.beta = parentNode.nodeValue; //give the MIN node the value as a beta
                    //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                }


            }
        }
    }
    
    public BoardScorePair takeLeft(char[][] board) throws IllegalMoveException{
        
        return redChecker.moveLeft(board);
    
    }
    public BoardScorePair takeRight(char[][]board) throws IllegalMoveException{
    
        return redChecker.moveRight(board);
    }
    
    public void branch(char[][] board,int score, int row, int column){
    
        CheckerMoveAlphaBetaTree temp = new CheckerMoveAlphaBetaTree(this, board, score, !isMaxNode, new RedChecker(row, column));
        temp.bestCheckerMove();
    }
    
}
