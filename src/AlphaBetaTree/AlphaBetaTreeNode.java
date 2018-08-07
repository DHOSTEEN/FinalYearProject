/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaBetaTree;

import Board.BoardUtilities;
import Pieces.BlackChecker;
//import Pieces.BoardScorePair;
import Pieces.Checker;
import Pieces.CheckerMoveAlphaBetaTree;
import Pieces.IllegalMoveException;
import Pieces.RedChecker;
import static finalyearproject.FinalYearProject.printBoard;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */

    
   public class AlphaBetaTreeNode{
    
        //Logic is as follows: see if the current piece can move, if it can create a child for that move - repeat until all possible (2?) moves are done.
        // so....each move is 2(?) possible moves, from around 4 or so diff checkers.
        
        //attempt at algorithm: 
        //choose a checker - calc move
        //calc opponent move
        //sends score to parent
        //repeat until depth hit
        
        //a normal checker can have up to 2 possible moves (so 2,1,0)
        //tree needs to branch at - checker selection and left/right
        //so at the begining of the game, 8 children - 4 poss moves left/right
        //next stage has - 10...
        //board state needs to be sent via all nodes
       //MY value is sort of REDUNDANT
        
    
        public CheckerMoveAlphaBetaTree innerTree;
        public char[][] masterBoard;// is used at fist stage, cannot be changed

    public void setCurrentBoard(char[][] currentBoard) {
        this.currentBoard = currentBoard;
    }
        public char[][] currentBoard;
        public char[][] bestBoard;
        public boolean isMaxNode;
        
        public int decisionScore;// the score of all moves
        public int nodeValue = 616;
        public int lowestLevel =  2; //<-- this is for the depth of the alpha beta tree - no child nodes after this point
        public int depthLevel;
        public boolean allChildrenExplored;
        
        //the move - as in once AlphaBeta done, this is the move it should make.
        public int row;
        public int column;
        
        public ArrayList<Checker> blackCheckers = new ArrayList<>();//since we have two move need to keep hold of the checker's.
        public ArrayList<Checker> redCheckers =  new ArrayList<>();
       // public ArrayList<Node> childNodes;// need to be checkd. other option have max number of possible child nodes and only use required
        //need a board 
        
        
        public int checkerMovedCount = 0;
        //alpha beta set at creation: overrided as nesscercary
        public int alpha = -1000;
        public int beta = 1000;
        
        public AlphaBetaTreeNode parentNode;
        
        public AlphaBetaTreeNode(char[][] masterBoard){
            
            currentBoard = masterBoard;
            //char[][] copyBoard = build(masterBoard);
            
        }
        /*Description of arguments: 
        board - where peices arem,
        score - culmative to be used for alpha beta pruning,
        depth - current depth of node in tree, 
        isMaxNode - maximizer or minimiser node*/
        public AlphaBetaTreeNode(char[][] board, int score, int depth, boolean isMaxNode){
        
            currentBoard = board;
            parentNode = null;// is the root
            decisionScore = score;
            depthLevel = depth;
            this.isMaxNode = isMaxNode;
            allChildrenExplored = false;
        
        }
        public AlphaBetaTreeNode( AlphaBetaTreeNode parent, char[][] board, int score, int depth, boolean isMaxNode, int alpha, int beta){
            
            currentBoard = board;
            //BoardUtilities.printBoard(board);
            parentNode = parent;
            decisionScore = score += parentNode.decisionScore; //  maybe?
            depthLevel = depth;
            this.isMaxNode = isMaxNode;
            allChildrenExplored = false;
            this.alpha = alpha;
            this.beta = beta;
        
        }
     
        
        public char[][] realBestMove(){
        
            getBestMove();
            return bestBoard;
        
        }
        
        public char[][] getBestMove(){
            
            /*System.out.println("The alpha value is" + alpha + " at depth of " + depthLevel);
            System.out.println("The beta value is" + beta + " at depth of " + depthLevel);
            System.out.println("The culmative Score is " + decisionScore + " at depth of " + depthLevel);
            System.out.println("The node Value is " + nodeValue);*/
          
            for(int i =0; i<currentBoard.length; i++){// makes a Checker as per board
            
                for(int j = 0; j<currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] ==  'O'){
                    
                        redCheckers.add(new Checker(i, j, true, false));
                    
                    }
                    else if(currentBoard[i][j] == 'X'){
                    
                        blackCheckers.add(new Checker(i, j, false, false));
                    
                    }                
                }            
            }
           
            if(isMaxNode)//ie the AI's turn - wants TO MAX SCORE
            {
                for(Checker O : redCheckers){// cycle through the "level" --- ahhhhhhhhhhhhhhh this is MINMAX
                    //flip a switch at the end to say: all NODES are explored, send currentScore UP???

                    /*logic for IF to create a new node:
                    if alpha(min) >= beta(max) DO NOT
                    if beta                 
                    
                    REMINDER MAX nodes SEND their value as BETA to all children Nodes.
                    MIN nodes SEND thier value as ALPHA
                    */
                    
                    try{
                       // System.out.println(redCheckers.size() + "<---- red peices");
                       // System.out.println("Node value is at: BEFORE children: " + nodeValue);
                        
                        
                        //LOGIC: Find value, compare to alpha beta values of parent, decide weather to keep down the tree
           
                       // if(parentNode.nodeValue != 616){
                        
                       //     beta = parentNode.nodeValue;
                       // }
                             //bestBoard = O.moveLeft(currentBoard, this);
                        char[][] copyOfBoard = build(currentBoard);// avoidning aliasing
                        //innerTree = new CheckerMoveAlphaBetaTree(copyOfBoard, O);
                        //innerTree.move();
                        //BoardScorePair pair = innerTree.move();
                        BoardScorePair pair = O.moveLeft(copyOfBoard);
                        maxLogic(pair);
                    }catch(IllegalMoveException e){                    
                         System.out.println(e.getMessage());
                    }//should sk
                            
                    try{
                            //REPEAT HERE FOR move LEFT option - kings ignored for now

                        char[][] copyOfBoard = build(currentBoard);
                        //printBoard(copyOfBoard);
                        // System.out.println("\n\n\n RED TURN");
                        BoardScorePair pair = O.moveRight(copyOfBoard);
                        maxLogic(pair);
                    }catch(IllegalMoveException e){
                    
                         System.out.println(e.getMessage());
                    }
                            
        
                }// outside loop of LEVEL in tree
       
                if(parentNode != null){
                    parentNode.allChildrenExplored = true; // 100% correct - all possible moves tried
                    propergateUp();//hmmmmmmmmmmmmm
                    //System.out.println("the value above is: " + parentNode.nodeValue);
                    if(parentNode.parentNode != null){
                    System.out.println("AFTER completing one MIN NODE branch, the parent alpha is " + parentNode.parentNode.alpha + " parent beta is " + parentNode.parentNode.beta
                    + "and the value of the node is " + parentNode.nodeValue );
                    }
                   // parentNode.parentNode.beta = parentNode.nodeValue;// yes - set the parent of the parent to this beta value WHEN all children are explored
                }   
               
            }
            else if(!isMaxNode){
               // System.out.println("UMMMMMMMM");
                for(Checker X : blackCheckers){
                
                    try{
                        
                        char[][] copyOfBoard = build(currentBoard); 
                        BoardScorePair pair = X.moveLeft(copyOfBoard);
                        minLogic(pair);
                    }catch(IllegalMoveException e){ 
                        //System.out.println(e.getMessage());
                        //System.out.println("CAUGHT ILLEGAL BLACK MOVE");
                    }
                           
                        //REPEAT HERE FOR move LEFT option - kings ignored for now
                    try{   
                        char[][] copyOfBoard = build(currentBoard);
                        BoardScorePair pair = X.moveRight(copyOfBoard);
                        minLogic(pair);
                    }catch(IllegalMoveException e){
                         //System.out.println(e.getMessage());
                    }
                    
                   
                
                }// outside loop
                if(parentNode != null){
                    parentNode.allChildrenExplored = true; // 100% correct - all possible moves tried
                    propergateUp();
                    if(parentNode.parentNode != null){
                    System.out.println("AFTER completing one MAX NODE branch: parent alpha is " + parentNode.parentNode.alpha + "parent beta is " + parentNode.parentNode.beta);
                    }
                   // parentNode.parentNode.beta = parentNode.nodeValue;// yes - set the parent of the parent to this beta value WHEN all children are explored
                
                }
          
            
            }// end of else if
            //System.out.println("Node value is at AFTER CHILDREN: " + parentNode.nodeValue);
            return currentBoard;// wrong -  for now
        
        }
        

        
        public char[][] build(char[][] board){
        
        
            char[][] newBoard = new char[8][8];
            for(int i =0; i<board.length; i++){
                for(int j = 0; j<board[i].length; j++){
                    newBoard[i][j] = board[i][j];
                }
            
            }
            return newBoard;
        }
        
        /// LOGIC
        
        public void maxLogic(BoardScorePair pair) throws IllegalMoveException{
            
            ///currentBoard = pair.getBoard();
            int scoreHolder = pair.getScore();// is saved
            //System.out.println(decisionScore);
            //System.out.println(decisionScore + "<--- the score for RED LEFT");

            
            if(depthLevel == lowestLevel){
                System.out.println("is leaf of a MIN node");
                if(parentNode.nodeValue == 616 || decisionScore < parentNode.nodeValue){
                    //System.out.println("A THING" + decisionScore);
                    parentNode.nodeValue = decisionScore;
                    parentNode.bestBoard = currentBoard;

                }
            }
            if(parentNode != null){
                if(parentNode.alpha>=parentNode.beta){
            
                    throw new IllegalMoveException("alpha value greater than beta");//effectivly FK the rest of the branches
                }

                //System.out.println(parentNode.beta);
                if(parentNode.beta != 1000){// can only work with actual values, not infinity
                    if(parentNode.beta>=nodeValue && nodeValue != 616){//because the parent is a Min node

                        //System.out.println("DOES IT BREAK");
                        throw new IllegalMoveException("beta value pruned tree");//effectivly FK the rest of the branches
                    }

                }

            }
            if(depthLevel != lowestLevel){ // if it is NOT a leaf we can branch
                branch(pair.getBoard(), scoreHolder);// makes children
            }
              /// HAVE A BRANCH HERE ||||
            if(parentNode != null){
                //System.out.println("is not leaf but finished its children");
                if((nodeValue < parentNode.nodeValue || parentNode.nodeValue == 616) && allChildrenExplored){/// FUKKKKKK YOPUUUUUUIKSGDJGDSJIUG
                    //System.out.println("whhhhhhhhhhhhhhy");
                    parentNode.nodeValue = nodeValue;
                    parentNode.bestBoard = currentBoard;
                }
            }
        }
        public void minLogic(BoardScorePair pair)throws IllegalMoveException{
        
            int scoreHolder = pair.getScore();
                           //decisionScore += // is saved
                           //System.out.println(decisionScore + "<--- the score for BLACK LEFT");
               
           
            if(depthLevel == lowestLevel){

                if(parentNode.nodeValue == 616 || decisionScore>parentNode.nodeValue){

                    parentNode.nodeValue = decisionScore;
                    parentNode.bestBoard = currentBoard;

                }
            }
            if(parentNode != null){
                
                if(parentNode.alpha>=parentNode.beta){
            
                 throw new IllegalMoveException("alpha value greater than beta");//effectivly FK the rest of the branches
                }

                if(parentNode.alpha != -1000){// can only work with actual values, not infinity
                    if(parentNode.alpha<=nodeValue && nodeValue != 616){//because the parent is a MAX node

                        //System.out.println("DOES IT BREAK");
                       throw new IllegalMoveException("alpha value pruned tree");
                    }
                }
            }
            if(depthLevel != lowestLevel){ // if it is NOT a leaf we can branch
                branch(pair.getBoard(), scoreHolder);// makes children
            }
            if(parentNode != null){// parent Node is a maximizer
                if((nodeValue > parentNode.nodeValue || parentNode.nodeValue == 616) && depthLevel != lowestLevel){

                    //System.out.println("Black tthing changed");
                    parentNode.nodeValue = nodeValue;
                    parentNode.bestBoard = currentBoard;
                }
            }
        }
        
        public void branch(char[][] board, int score){
            
            //System.out.println("a branch");
           // System.out.println("this node is " + isMaxNode);
           // System.out.println("its child is " +!isMaxNode);
            
            AlphaBetaTreeNode T = new AlphaBetaTreeNode(this, board, score, depthLevel +1, !isMaxNode, alpha, beta);
            T.getBestMove();
            //AlphaBetaNode parent, char[][] board, int score, int depth, boolean isMaxNode
        
        }

        
        public void propergateUp(){
            System.out.println("IS PROPERGATING, THE DEPTH IS " + depthLevel );
            //System.out.println(decisionScore);
           
            if(parentNode.parentNode != null){
               // System.out.println(parentNode.parentNode.isMaxNode);
                //System.out.println(!parentNode.parentNode.isMaxNode);
               // System.out.println("is true");
                if(parentNode.parentNode.isMaxNode){
                   // System.out.println("IS TRUE");
                    //System.out.println(parentNode.nodeValue);
                    if(parentNode.nodeValue != 616){// if the value has been updated

                        System.out.println("Alpha changed!");
                        parentNode.parentNode.alpha = parentNode.nodeValue; //give the MAX node the value as a alpha
                        //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                    }
                }
                else{
                
                     if(parentNode.nodeValue != 616){// if the value has been updated

                        System.out.println("Beta changed!");
                        parentNode.parentNode.beta = parentNode.nodeValue; //give the MIN node the value as a beta
                        //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                    }
                    
                
                }
            }
        }
        
        
        
    
    
    
}
