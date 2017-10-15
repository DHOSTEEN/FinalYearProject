/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaBetaTree;

import Pieces.BlackChecker;
import Pieces.BoardScorePair;
import Pieces.CheckerMoveAlphaBetaTree;
import Pieces.IllegalMoveException;
import Pieces.RedChecker;
import static finalyearproject.FinalYearProject.printBoard;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */

    
   public class AlphaBetaNode{
       // AHKJAGDSJHGDAJHDA
       //EACH MOVEEEEEEEEEEE MUST BE REPRESTENED BY A NODE --- IF ALL are CREATED BY ONE CLASS THEN ALL IN A LEVEL ARE THE SAAAAAAAAAAAME NODE
       //SO LEFT AND RIGHT MOVES HAVE UNIQUE CHILDRENNNN BUT ARE THE SAME AS IS ALL OTHER NODES IN A LINE FFS DANIEL THINK BEFORE YOU CODE
    
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
        public int lowestLevel =  4; //<-- this is for the depth of the alpha beta tree - no child nodes after this point
        public int depthLevel;
        public boolean allChildrenExplored;
        
        //the move - as in once AlphaBeta done, this is the move it should make.
        public int row;
        public int column;
        
        public ArrayList<BlackChecker> blackCheckers = new ArrayList<>();//since we have two move need to keep hold of the checker's.
        public ArrayList<RedChecker> redCheckers =  new ArrayList<>();
        public ArrayList<AlphaBetaNode> childNodes = new ArrayList<>();// need to be checkd. other option have max number of possible child nodes and only use required
        //need a board 
        
        
        public int checkerMovedCount = 0;
        //alpha beta set at creation: overrided as nesscercary
        public int alpha = -1000;
        public int beta = 1000;
        
        public int relatedChecker;
        
        public AlphaBetaNode parentNode;
        
        public AlphaBetaNode(char[][] masterBoard, boolean isMaxNode){
            
            this.isMaxNode = isMaxNode;
            currentBoard = masterBoard;
            
            //char[][] copyBoard = build(masterBoard);
            
        }
        /*Description of arguments: 
        board - where peices arem,
        score - culmative to be used for alpha beta pruning,
        depth - current depth of node in tree, 
        isMaxNode - maximizer or minimiser node*/
        public AlphaBetaNode(char[][] board, int score, int depth, boolean isMaxNode){
        
            currentBoard = board;
            parentNode = null;// is the root
            decisionScore = score;
            depthLevel = depth;
            this.isMaxNode = isMaxNode;
            allChildrenExplored = false;
        
        }
        public AlphaBetaNode( AlphaBetaNode parent, char[][] board, int score, int depth, boolean isMaxNode, int relatedChecker, int alpha, int beta){
            
            currentBoard = board;
            parentNode = parent;
            decisionScore = score += parentNode.decisionScore; //  maybe?
            depthLevel = depth;
            this.isMaxNode = isMaxNode;
            allChildrenExplored = false;
            this.relatedChecker = relatedChecker;
            this.alpha = alpha;
            this.beta = beta;
        
        }
     
        
        public char[][] firstMove(){
            
            System.out.println(currentBoard.length);
            for(int i =0; i<currentBoard.length; i++){// makes a Checker as per board
            
                for(int j = 0; j<currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] ==  'O'){
                    
                        redCheckers.add(new RedChecker(i, j));
                    
                    }
                    else if(currentBoard[i][j] == 'X'){
                    
                        blackCheckers.add(new BlackChecker(i, j));
                    
                    }                
                }            
            }
            if(isMaxNode){
                for(int i =0; i<redCheckers.size(); i++){

                    char[][] copyBoard = build(currentBoard);
                    childNodes.add(new AlphaBetaNode(this, copyBoard, 0 , 0, isMaxNode, i, alpha, beta));
                }
                getBestMoveMAX();
            }
            else{
                for(int i =0; i<blackCheckers.size(); i++){

                    char[][] copyBoard = build(currentBoard);
                    childNodes.add(new AlphaBetaNode(this, copyBoard, 0 ,0, isMaxNode, i, alpha, beta));
                }
                getBestMoveMIN();
            }
            
            return bestBoard;// SHOULD be updated to SOMTHING by the above
        
        }
        public char[][] realBestMove(AlphaBetaNode parent, char[][] board, int score, int depth, boolean isMax, int alpha, int beta){
        
            
            for(int i =0; i<currentBoard.length; i++){// makes a Checker as per board
            
                for(int j = 0; j<currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] ==  'O'){
                    
                        redCheckers.add(new RedChecker(i, j));
                    
                    }
                    else if(currentBoard[i][j] == 'X'){
                    
                        blackCheckers.add(new BlackChecker(i, j));
                    
                    }                
                }            
            }
            if(isMax){
                for(int i =0; i<redCheckers.size(); i++){

                    char[][] copyBoard = build(board);
                    childNodes.add(new AlphaBetaNode(parent, copyBoard, score , depth, isMax, i,  alpha, beta));
                }
                getBestMoveMAX();
            }
            else{
                for(int i =0; i<blackCheckers.size(); i++){

                    char[][] copyBoard = build(currentBoard);
                    childNodes.add(new AlphaBetaNode(parent, copyBoard, score ,depth, isMax, i,alpha, beta));
                }
                getBestMoveMIN();
            }
            
            return bestBoard;
        
        }
        private char[][] getBestMoveMAX(){
            
           
                          /*System.out.println("The alpha value is" + alpha + " at depth of " + depthLevel);
            System.out.println("The beta value is" + beta + " at depth of " + depthLevel);
            System.out.println("The culmative Score is " + decisionScore + " at depth of " + depthLevel);
            System.out.println("The node Value is " + nodeValue);*/
           
           
           // System.out.println(redCheckers.size() + "<---- red peices");

                for(AlphaBetaNode node : childNodes){// cycle through the "level" --- ahhhhhhhhhhhhhhh this is MINMAX
                    //flip a switch at the end to say: all NODES are explored, send currentScore UP???

                    /*logic for IF to create a new node:
                    if alpha(min) >= beta(max) DO NOT
                    if beta                 
                    
                    REMINDER MAX nodes SEND their value as BETA to all children Nodes.
                    MIN nodes SEND thier value as ALPHA
                    */
                    //create a NODE
                   
                    try{// try to move left
                        
                        //LOGIC: Find value, compare to alpha beta values of parent, decide weather to keep down the tree
           
                       // if(parentNode.nodeValue != 616){
                        
                       //     beta = parentNode.nodeValue;
                       // }
                             //bestBoard = O.moveLeft(currentBoard, this);
                             
                             
                            char[][] copyOfBoard = build(node.currentBoard);// avoidning aliasing
                            //innerTree = new CheckerMoveAlphaBetaTree(copyOfBoard, O);
                            //innerTree.move();
                            //BoardScorePair pair = innerTree.move();
                            BoardScorePair pair = redCheckers.get(node.relatedChecker).moveLeft(copyOfBoard);
                            node.currentBoard = pair.getBoard();
                            int scoreHolder = pair.getScore();// is saved
                            System.out.println("the DECSISION IS : " +node.decisionScore + " THE move score is " + scoreHolder);
                            //System.out.println(decisionScore + "<--- the score for RED LEFT");
             
                            
                
                            if(node.depthLevel == node.lowestLevel){
                                //System.out.println("is leaf");
                                if(node.parentNode.nodeValue == 616 || node.decisionScore < node.parentNode.nodeValue){
                                    System.out.println("A leaf has been reached with a score of: " + decisionScore);
                                    node.parentNode.nodeValue = node.decisionScore;

                                }
                            }
                                //System.out.println(parentNode.beta);
                            if(beta != 1000){// can only work with actual values, not infinity
                                if(beta>=node.nodeValue && node.nodeValue != 616){//because the parent is a Min node

                                    System.out.println("DOES IT BREAK");
                                    throw new IllegalMoveException();
                                    //break;//effectivly FK the rest of the branches
                            }

                            
                                
                            }
                            if(node.depthLevel != node.lowestLevel){ // if it is NOT a leaf we can branch
                                System.out.println("Aa RED child has been made the LEFT move");
                                branch(pair.getBoard(), scoreHolder, node);// makes children
                            }
                              /// HAVE A BRANCH HERE ||||
                            
                                //System.out.println("is not leaf but finished its children");
                            if((node.nodeValue < node.parentNode.nodeValue || node.parentNode.nodeValue == 616) && node.allChildrenExplored){/// FUKKKKKK YOPUUUUUUIKSGDJGDSJIUG
                                System.out.println("The best board has propergated to the parent node at level: " + node.parentNode.depthLevel + "with a value of " + node.nodeValue);
                                node.parentNode.nodeValue = node.nodeValue;
                                if(node.depthLevel == 0){
                                    node.parentNode.bestBoard = node.currentBoard;
                                }
                            }
                            
                    }catch(IllegalMoveException e){}
                    
                    /// Try to move right
                            //REPEAT HERE FOR move LEFT option - kings ignored for now
                    try{
                              char [][] copyOfBoard = build(node.currentBoard);
                              //printBoard(copyOfBoard);
                              // System.out.println("\n\n\n RED TURN");
                              BoardScorePair pair = redCheckers.get(node.relatedChecker).moveRight(copyOfBoard);
                              node.currentBoard = pair.getBoard();
                              int scoreHolder = pair.getScore();// is saved
                              //System.out.println(decisionScore);

                              
                              //System.out.println(decisionScore + "<--- the score for RED RIGHT");
                              
                            if(node.depthLevel == node.lowestLevel){
                                
                                if(node.parentNode.nodeValue == 616 || node.decisionScore<nodeValue){

                                    System.out.println("A leaf has been reached with a score of: " + node.decisionScore);///hmmmmmmmmmmmmmmmm
                                    nodeValue = node.decisionScore;

                                }
                            }
                
                            if(beta != 1000){// can only work with actual values, not infinity
                                if(beta>=node.nodeValue && node.nodeValue != 616){//because the parent is a Min node

                                    System.out.println("DOES IT BREAK");
                                    break;//effectivly FK the rest of the branches
                                }




                            }
                            if(node.depthLevel != node.lowestLevel){ // if it is NOT a leaf we can branch
                                System.out.println("RED has made a child to the RIGHT");
                                branch(pair.getBoard(), scoreHolder, node);// makes children
                            }
                            
                                if((node.nodeValue < nodeValue || nodeValue == 616) && node.allChildrenExplored){

                                   System.out.println("The best board has propergated to the parent node at level: " + node.parentNode.depthLevel + "with a value of " + node.nodeValue);
                                   

                                    nodeValue = node.nodeValue;
                                    if(node.depthLevel == 0){// only update board at penultimate level
                                        parentNode.bestBoard = node.currentBoard;
                                    }
                                }
                            
                              // end of right move
                              
                    }catch(IllegalMoveException e){                    
                        //System.out.println("CAUGHT ILLEGAL RED MOVE");
                    }//should skip any impossible moves
                 
                }// outside loop of LEVEL in tree
       
                //if(parentNode != null){ change in logic means the parent node IS THIS instance
                    allChildrenExplored = true; // 100% correct - all possible moves tried
                    propergateUp();//hmmmmmmmmmmmmm
                    //System.out.println("the value above is: " + parentNode.nodeValue);
                    //if(parentNode.parentNode != null){
                   // System.out.println("AFTER one level: parent alpha is " + parentNode.parentNode.alpha + "parent beta is " + parentNode.parentNode.beta);
                   // }
                   // parentNode.parentNode.beta = parentNode.nodeValue;// yes - set the parent of the parent to this beta value WHEN all children are explored
                //}                  
            
          
            // end of else if
            
            return currentBoard;// wrong -  for now
        
        }
        
    public char[][] getBestMoveMIN(){

// System.out.println("UMMMMMMMM");
               
        for(AlphaBetaNode node : childNodes){


            try{// to move left

                char[][] copyOfBoard = build(currentBoard);

                BoardScorePair pair = blackCheckers.get(node.relatedChecker).moveLeft(copyOfBoard);
                node.currentBoard = pair.getBoard();
                int scoreHolder = pair.getScore();

                if(node.depthLevel == node.lowestLevel){

                        if(nodeValue == 616 || node.decisionScore>nodeValue){

                            System.out.println("Leaf has been reached with a score of: " + node.decisionScore);///hmmmmmmmmmmmmmmmm
                            node.parentNode.nodeValue = node.decisionScore;

                        }
                }
                if(alpha != -1000){// can only work with actual values, not infinity
                    if(alpha<=node.nodeValue && node.nodeValue != 616){//because the parent is a Min node

                        System.out.println("DOES IT BREAK");
                        break;//effectivly FK the rest of the branches
                    }


                    

                }
                if(node.depthLevel != node.lowestLevel){ // if it is NOT a leaf we can branch
                    System.out.println("A BLACK child has been made to the LEFT");
                    branch(pair.getBoard(), scoreHolder, node);// makes children
                }
                 //after branch so therfore after root nodes explored
                if((node.nodeValue > nodeValue || nodeValue == 616) && node.allChildrenExplored){

                    System.out.println("The best board has propergated to the parent node at level: " + node.parentNode.depthLevel + "with a value of " + node.nodeValue);

                    node.parentNode.nodeValue = node.nodeValue;
                    if(node.depthLevel == 0){
                        node.parentNode.bestBoard = node.currentBoard;
                    }
                }

            }catch(IllegalMoveException e){                    
                //System.out.println("CAUGHT ILLEGAL BLACK MOVE");
            }
            //try to mmove right
            
            try{
            
                char[][] copyOfBoard = build(currentBoard);

                BoardScorePair pair = blackCheckers.get(node.relatedChecker).moveRight(copyOfBoard);
                node.currentBoard = pair.getBoard();
                int scoreHolder = pair.getScore();
                
                if(node.depthLevel == node.lowestLevel){

                    if(nodeValue == 616 || node.decisionScore>nodeValue){

                        System.out.println("A leaf has been reached with a score of: " + node.decisionScore);///hmmmmmmmmmmmmmmmm
                        nodeValue = node.decisionScore;

                    }
                }
                if(alpha != -1000){// can only work with actual values, not infinity
                    if(alpha<=node.nodeValue && node.nodeValue != 616){//because the parent is a Min node

                        System.out.println("DOES IT BREAK");
                        break;//effectivly FK the rest of the branches
                    }

                }
                if(node.depthLevel != node.lowestLevel){ // if it is NOT a leaf we can branch
                    System.out.println("A BLACK child is made form the RIGHT");
                    branch(pair.getBoard(), scoreHolder, node);// makes children
                }
                 //after branch so therfore after root nodes explored
                if((node.nodeValue > nodeValue || nodeValue == 616) && node.allChildrenExplored){

                  System.out.println("The best board has propergated to the parent node at level: " + node.parentNode.depthLevel + "with a value of " + node.nodeValue);

                    node.parentNode.nodeValue = node.nodeValue;
                    if(node.depthLevel ==0){
                        node.parentNode.bestBoard = node.currentBoard;
                    }
                }
                
            
            }catch(IllegalMoveException e){}

        }// outside loop
       
            allChildrenExplored = true; // 100% correct - all possible moves tried
            propergateUp();
            if(parentNode.parentNode != null){
            //System.out.println("AFTER one level: parent alpha is " + parentNode.parentNode.alpha + "parent beta is " + parentNode.parentNode.beta);
            }
           // parentNode.parentNode.beta = parentNode.nodeValue;// yes - set the parent of the parent to this beta value WHEN all children are explored
        return currentBoard;
    }


        public void maxNodeLogic(BoardScorePair pair, int scoreHolder){
            
        
        
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
        
        public void branch(char[][] board, int score, AlphaBetaNode parent){
            
            //System.out.println("a branch");
           // System.out.println("this node is " + isMaxNode);
           // System.out.println("its child is " +!isMaxNode);
            //childNodes.add(new AlphaBetaNode(this, board, score, depthLevel++, !isMaxNode));
            //AlphaBetaNode T = new AlphaBetaNode(this, board, score, depthLevel +1, !isMaxNode);
            ///.getBestMove();
            //AlphaBetaNode parent, char[][] board, int score, int depth, boolean isMaxNode
            
            System.out.println("The depth is at :" + (parent.depthLevel +1) + " The next node is " + !parent.isMaxNode);
            parent.realBestMove(parent, board, score, parent.depthLevel+1, !parent.isMaxNode, parent.alpha, parent.beta);

            
            
        }
        
        public void reachALeaf(){
            //System.out.println("IS IN A LEAF");
            
            if(parentNode.isMaxNode){// max Nodes UPDATED from children TO alpha value
                if( decisionScore > parentNode.nodeValue || parentNode.nodeValue == 616){
                    parentNode.nodeValue = decisionScore;
                   // parentNode.bestBoard = currentBoard;// for ultimate return
                }

            }
            else{ 

                if( decisionScore < parentNode.nodeValue || parentNode.nodeValue == 616){
                    parentNode.nodeValue = decisionScore;
                    //parentNode.bestBoard = currentBoard;
                }

            }
        
        
        }
        
        public void propergateUp(){
           
           // System.out.println(decisionScore);
           
            
            
            if(parentNode != null){
                //System.out.println("IS PROPERGATING, THE DEPTH IS " + depthLevel );
               // System.out.println(parentNode.parentNode.isMaxNode);
                //System.out.println(!parentNode.parentNode.isMaxNode);
               // System.out.println("is true");
                if(parentNode.isMaxNode){
                   //System.out.println("The parent is a Max Node");
                    //System.out.println(parentNode.nodeValue);
                    if(nodeValue != 616){// if the value has been updated

                        System.out.println("Alpha changed!");
                        
                        parentNode.alpha = nodeValue; //give the MAX node the value as a alpha
                        //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                    }
                }
                else{
                
                   // System.out.println("The parent is a Min Node");
                     if(nodeValue != 616){// if the value has been updated

                         System.out.println("Beta changed!");
                        parentNode.beta = nodeValue; //give the MIN node the value as a beta
                        //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                    }
                    
                
                }
            }
        }
        
        
        
    
    
    
}
