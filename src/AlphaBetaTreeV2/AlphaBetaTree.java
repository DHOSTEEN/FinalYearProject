/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaBetaTreeV2;

import Pieces.Checker;
import Pieces.CheckerKing;
import Pieces.IllegalMoveException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class AlphaBetaTree {

    public AlphaBetaTree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCurrentScore() {
        return currentScore;
    }
    
    
    private int currentScore =616;
    private char[][] currentBoard;
    private char[][] bestBoard;
    private ArrayList<Checker> checkers = new ArrayList<>();
   // private ArrayList<Checker> blackCheckers = new ArrayList<>();
    
    /*Tree variables*/

    private AlphaBetaTree parentNode;
    public int nodeValue = 616;

    public boolean isIsMaxNode() {
        return isMaxNode;
    }

    public int getDepthLevel() {
        return depthLevel;
    }

    public int getAlpha() {
        return alpha;
    }

    public int getBeta() {
        return beta;
    }
    private boolean isMaxNode;
    private int depthLevel;
    private final int maxDepth;
    private int alpha;
    private int beta;
    private boolean allChildrenExplored;
    
    private boolean hasMoved;
    
    
    public AlphaBetaTree(AlphaBetaTree parent, boolean myType, int newDepthLevel,int passedAlpha, int passedBeta, int culmativeScore, char[][] passedBoard, int maxDepth){
    
        parentNode = parent;
        isMaxNode = myType;
        depthLevel = newDepthLevel;
        alpha = passedAlpha;
        beta = passedBeta;
        currentScore = culmativeScore;
        currentBoard = passedBoard;
        this.maxDepth = maxDepth;
    
    }
    
    public char[][] getBestMove(){
    
        findBestMove();
        return bestBoard;
    
    }
    
    private void findBestMove(){
        
        //System.out.println("My current Val is: " + currentScore);
        hasMoved = false;
        if(maxDepth!=depthLevel){
            
            readBoard();
            for(Checker checkers: checkers){
            
                branchAtMove(checkers);
            
            }
            if(hasMoved){
                allChildrenExplored = true;
                propergate();
            }
            else{
                leafPropergate();
            }
        
        }
        else{
            
            
            leafPropergate();
        
        }
    
    }
    private void branchAtMove(Checker checker){
        
        if(!isMaxNode && nodeValue!=616){
            if(alpha!=-1000){
                if(alpha>=nodeValue){

                //System.out.println("a min node has pruned: alpha is: " + alpha + " beta is: " + beta + " value is: " + nodeValue);
                       return;
                }
                if(beta!=1000){
                    if(alpha>=beta){
                    
                        //System.out.println("a min node has pruned: alpha is: " + alpha + " beta is: " + beta + " value is: " + nodeValue);
                        return;
                    }
                
                }
            }
         }
         else if(isMaxNode && nodeValue!=616){

           if(beta!=1000){
                if(beta<=nodeValue){

                //System.out.println("a max node has pruned: alpha is: " + alpha + " beta is: " + beta);
                       return;
                }
               if(alpha!=-1000){
                    if(alpha>=beta){
                    
                        //System.out.println("a max node has pruned: alpha is: " + alpha + " beta is: " + beta);
                        return;
                    }
                
                }
            }
        }
        
        /* Branch */
        
        testMoves(checker);
    
    
    }
    private void testMoves(Checker checker){
        try{
             checker.moveLeft(currentBoard);
              hasMoved = true;
        }catch(IllegalMoveException e){}
       
        try{
             checker.moveRight(currentBoard);
              hasMoved = true;
        }catch(IllegalMoveException e){}
       
        if(checker instanceof CheckerKing){
    
            try{
                    ((CheckerKing) checker).moveBackLeft(currentBoard);
                     hasMoved = true;
                }catch(IllegalMoveException e){}
           
            try{
                    ((CheckerKing) checker).moveBackRight(currentBoard);
                     hasMoved = true;
                }catch(IllegalMoveException e){}
            
        }
        
    
    }
    public void branch(int score, char[][] board){
        
        new AlphaBetaTree(this, !(isMaxNode), depthLevel + 1, alpha, beta, currentScore + score, board, maxDepth).getBestMove();
    }
    private void makeALeaf(){
    
        //undeeded?
    
    }
    private void leafPropergate(){
        //System.out.println("I am a Leaf at depth of " + depthLevel +" value is: " + currentScore + "parent value is: " + parentNode.nodeValue);
        if(parentNode!=null){
            if(isMaxNode){//sending to a min node
                if(currentScore!=616){
                    if(currentScore<parentNode.nodeValue || parentNode.nodeValue == 616){
                        parentNode.nodeValue = currentScore;
                        parentNode.bestBoard = currentBoard;
                           //System.out.println("parent(MIN) value is: " + nodeValue);
                    }
                }
           }
           else{
                if(currentScore!=616){
                    if(currentScore>parentNode.nodeValue || parentNode.nodeValue == 616){
                        parentNode.nodeValue = currentScore;
                        parentNode.bestBoard = currentBoard;
                    }
                //System.out.println("parent(MAX) value is: " + nodeValue);
               }
           }
        }
        else{
            bestBoard = currentBoard;
            nodeValue = currentScore;
        }
    
    }
    private void propergate(){
        
        //System.out.println("Propergating values at level: " + depthLevel);
         if(!isMaxNode){// i am a MIN

            if(nodeValue == 616){
                //System.out.println("WTF");
            }
            if(parentNode!=null && nodeValue!= 616 && allChildrenExplored){
                if(nodeValue>parentNode.nodeValue || parentNode.nodeValue == 616){
                parentNode.alpha = nodeValue;
                parentNode.nodeValue = nodeValue;
                parentNode.bestBoard = currentBoard;
                
                //System.out.println("parent(MAX) alpha is: " + nodeValue + " parent(MAX) is: " + parentNode);
                }
            }

        }
        else{// i am a MAX
            if(nodeValue == 616){
                //System.out.println("WTF");
            }
            if(parentNode!=null && nodeValue!= 616 && allChildrenExplored){
                if(nodeValue< parentNode.nodeValue || parentNode.nodeValue == 616){
                   // if(value>parent.alpha){
                        parentNode.beta = nodeValue;
                    //}
                    parentNode.nodeValue = nodeValue;
                    parentNode.bestBoard = currentBoard;
                    

                         //System.out.println("parent(MIN) beta is: " + nodeValue + " parent(MIN) is: " + parentNode);
                }
                  ////System.out.println(value + " max node updated ");
            }

        }
    
    }
    private void readBoard(){
    
        if(isMaxNode){
            
            for(int i = 0; i<currentBoard.length; i++){
            
                for(int j =0; j< currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] == 'O'){
                        checkers.add(new Checker(i, j, true, this));
                    }
                    else if(currentBoard[i][j] == 'E'){
                    
                        checkers.add(new CheckerKing(i, j, true, this));
                    }
                
                }
            
            }
        
        }
        else{
            
            for(int i = 0; i<currentBoard.length; i++){
            
                for(int j =0; j< currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] == 'X'){
                        checkers.add(new Checker(i, j, false, this));
                    }
                    else if(currentBoard[i][j] == 'K'){
                    
                        checkers.add(new CheckerKing(i, j, false, this));
                    }
                
                }
            
            }
        
        }
    
    }
}
