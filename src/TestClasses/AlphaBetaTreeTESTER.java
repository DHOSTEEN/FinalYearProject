/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import AlphaBetaTree.AlphaBetaPruningException;
import java.util.ArrayList;


/**
 *
 * @author Daniel
 */

    
   public class AlphaBetaTreeTESTER{
    
        // issue: got leaf logic down: loop means - 
    
       
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
        private int treeValuePointer;
        
        //the move - as in once AlphaBeta done, this is the move it should make.
       
       // private final int[] treeValues; 
    
       // public ArrayList<Node> childNodes;// need to be checkd. other option have max number of possible child nodes and only use required
        //need a board 
        //does it go back to the object AFTER the SECOND child has done!? :O
        
        public int checkerMovedCount = 0;
        //alpha beta set at creation: overrided as nesscercary
        public int alpha = -1000;
        public int beta = 1000;
        private int control = 0;
        private int childCounter;
   
        public ArrayList<AlphaBetaTreeTESTER> children = new ArrayList<>();
        public AlphaBetaTreeTESTER parentNode;
        
        
        public AlphaBetaTreeTESTER(char[][] masterBoard){
            
            currentBoard = masterBoard;
            //char[][] copyBoard = build(masterBoard);
            
        }
        /*Description of arguments: 
        board - where peices arem,
        score - culmative to be used for alpha beta pruning,
        depth - current depth of node in tree, 
        isMaxNode - maximizer or minimiser node*/
        public AlphaBetaTreeTESTER( int depth, boolean isMaxNode){
        
            //this.control = control;
            //this.treeValues = treeValues;
            parentNode = null;// is the root
            //decisionScore = score;
            depthLevel = depth;
            this.isMaxNode = isMaxNode;
            allChildrenExplored = false;
            treeValuePointer =0;
        
        }
        public AlphaBetaTreeTESTER( AlphaBetaTreeTESTER parent, int depth, boolean isMaxNode, int alpha, int beta){
            
            
            //BoardUtilities.printBoard(board);
            //this.control = control;
            parentNode = parent;
            //decisionScore = score += parentNode.decisionScore; //  maybe?
            depthLevel = depth;
            this.isMaxNode = isMaxNode;
            allChildrenExplored = false;
            this.alpha = alpha;
            this.beta = beta;
            //treeValuePointer = pointer;
        
        }
     
        
        public int realBestMove(){
        
            getBestMove();
            return nodeValue;
        
        }
        
        public void getBestMove(){
            
            /*System.out.println("The alpha value is" + alpha + " at depth of " + depthLevel);
            System.out.println("The beta value is" + beta + " at depth of " + depthLevel);
            System.out.println("The culmative Score is " + decisionScore + " at depth of " + depthLevel);
            System.out.println("The node Value is " + nodeValue);*/
          
           
            if(isMaxNode)//ie the AI's turn - wants TO MAX SCORE
            {
                try{
                    if(depthLevel!=lowestLevel){
                        while(control<2){// cycle through the "level" --- ahhhhhhhhhhhhhhh this is MINMAX

                                maxLogic();
                                control ++;




                        }// outside loop of LEVEL in tree
                        allChildrenExplored = true;
                    }
                    else{
                        
                            maxLeafLogic();// because leafs dont propergate and dont need to fire logic twice -- poss change to leaf logic?
                            
                        
                        //allChildrenExplored = true;
                    }
                   // for(AlphaBetaTreeTESTER child : children){
                    
                   //     child.getBestMove();
                    
                  //  }
                if(parentNode != null){
                  
                    //propergateUp();//hmmmmmmmmmmmmm
                    //System.out.println("the value above is: " + parentNode.nodeValue);
                    if(parentNode.parentNode != null){
                    System.out.println("AFTER completing one MIN NODE loop, the parent alpha is " + parentNode.alpha + " parent beta is " + parentNode.beta
                    + "and the value of the node is " + parentNode.nodeValue );
                    }
                   // parentNode.parentNode.beta = parentNode.nodeValue;// yes - set the parent of the parent to this beta value WHEN all children are explored
                }
                }catch(AlphaBetaPruningException e){
                    System.out.println(e.getMessage());
                }
               
            }
            else if(!isMaxNode){
               // System.out.println("UMMMMMMMM");
               try{
                   if(depthLevel!=lowestLevel){
                        while(control<2){


                                minLogic();
                                control++;


                        }// outside loop
                        allChildrenExplored = true;
                   }
                   else{
                      
                        minLeafLogic();
                      
                       
                      
                   
                   }
                   if(parentNode != null){
                       
                       
                       
                        if(parentNode.parentNode != null){
                        System.out.println("AFTER completing one MAX NODE loop: parent alpha is " + parentNode.alpha + "parent beta is " + parentNode.beta);
                        }
                       // parentNode.parentNode.beta = parentNode.nodeValue;// yes - set the parent of the parent to this beta value WHEN all children are explored

                    }
               }catch(AlphaBetaPruningException e){
                 System.out.println(e.getMessage());
               }
          
            
            }// end of else if
            //System.out.println("Node value is at AFTER CHILDREN: " + parentNode.nodeValue);
            //return currentBoard;// wrong -  for now
        
        }
        

        
        /// LOGIC
        
        public void maxLogic() throws AlphaBetaPruningException{ // max nodes GIVE THEIR VALUE AS A BETA TO THIER PARENTSAS
            
            ///currentBoard = pair.getBoard();
            //System.out.println("I am a MAX node :" + this + " my parent is");
            System.out.println("I AM max node: " + this);
             
            if(parentNode != null){
            
                System.out.println("My parents is: " + parentNode + "and its alpha is " +  parentNode.alpha + "and my parents beta is " + parentNode.beta);
            }
            else{
                System.out.println("I am the Root");
            }
    
            if(parentNode != null){
                    if(parentNode.alpha>=parentNode.beta){

                        throw new AlphaBetaPruningException("alpha value greater than beta - alpha value is: " + parentNode.alpha + " beta value is " + parentNode.beta);//effectivly FK the rest of the branches
                    }

                    //System.out.println(parentNode.beta);
                    if(parentNode.beta != 1000){// can only work with actual values, not infinity
                        if(parentNode.beta<=nodeValue && nodeValue != 616){//because the parent is a Min node

                            //System.out.println("DOES IT BREAK");
                            throw new AlphaBetaPruningException("beta value pruned tree, the node value is: " + nodeValue + " and the beta value is: " + parentNode.beta);//effectivly FK the rest of the branches
                        }

                    }

                }
            branch();// makes children
            
            
            if(parentNode != null && nodeValue != 616){
                    //System.out.println("is not leaf but finished its children");
                    if((nodeValue < parentNode.nodeValue || parentNode.nodeValue == 616)){/// FUKKKKKK YOPUUUUUUIKSGDJGDSJIUG
                        System.out.println("max node has sent a value up to its parent value is: " + nodeValue);
                        parentNode.nodeValue = nodeValue;
                       
                        if(parentNode.parentNode != null){
                            parentNode.parentNode.beta = nodeValue;
                        }
                        
                        //parentNode.bestBoard = currentBoard;
                    }
                }
            //System.out.println(decisionScore);
            //System.out.println(decisionScore + "<--- the score for RED LEFT");
            
            
        }
        public void maxLeafLogic(){
            
            System.out.println("I AM MAX node LEAF: " + this);
            if(parentNode != null){
            
                System.out.println("My parents alpha is " + parentNode.alpha + "and my parents beta is " + parentNode.beta);
                System.out.println("My parent is: " + parentNode);
            }
            else{
                System.out.println("I am the Root");
            }
            System.out.println("is leaf of a MIN node");
            int scoreHolder = TestMain.treeValues[TestMain.treeHolder];// is saved
            System.out.println("the scoreHodler value is: " +scoreHolder);
            TestMain.treeHolder++;
            System.out.println("The arrayPosition is at: " +TestMain.treeHolder + " for the next leaf");
            if(parentNode.nodeValue == 616 || scoreHolder < parentNode.nodeValue){
                    
                System.out.println("As a leaf - my Parent node value is now: " + scoreHolder + " and the depth of the parent node is: " + parentNode.depthLevel);
                parentNode.nodeValue = scoreHolder;
                if(parentNode.parentNode != null){
                
                    parentNode.parentNode.alpha = scoreHolder;
                
                }
                //parentNode.bestBoard = currentBoard;

            }
        
        
        }
        public void minLogic()throws AlphaBetaPruningException{// after each brach will return to where it left off 

            
        System.out.println("I AM min node: " + this);
            if(parentNode != null){
            
                System.out.println("My parents alpha is " + parentNode.alpha + "and my parents beta is " + parentNode.beta);
                System.out.println("My parent is: " + parentNode);
            }
            else{
                System.out.println("I am the Root");
            }
             // if it is NOT a leaf we can branch
             
             if(parentNode != null){

                if(parentNode.alpha>=parentNode.beta){

                 throw new AlphaBetaPruningException("alpha value greater than beta");//effectivly FK the rest of the branches
                }

                if(parentNode.alpha != -1000){// can only work with actual values, not infinity
                    if(parentNode.alpha>=nodeValue && nodeValue != 616){//because the parent is a MAX node

                        //System.out.println("DOES IT BREAK");
                       throw new AlphaBetaPruningException("alpha value pruned tree, node value is: " + nodeValue +" the parent alpha is: " + parentNode.alpha);
                    }
                }
            }
             
            branch();// makes children
            System.out.println("I R BACK TO PARENT I R: " + this);
            
            if(parentNode != null && nodeValue != 616){// parent Node is a maximizer
                if((nodeValue > parentNode.nodeValue || parentNode.nodeValue == 616)){

                    System.out.println("min node sent its value up value of: " + nodeValue);
                    parentNode.nodeValue = nodeValue;
                   if(parentNode.parentNode != null){
                       System.out.println("min node sent its value up ALPHA VALUE: " + nodeValue);
                        parentNode.parentNode.beta = nodeValue;
                   }
                    
                    //parentNode.bestBoard = currentBoard;
                }
            }
            if(nodeValue != 616 ){// ie has been updated from a leaf
                 System.out.println("I have updated my parents beta value to " + nodeValue  + "I am at depth " + depthLevel + " I am: " + this + " I am a " + isMaxNode);
                
            }
            
   
        }
        public void minLeafLogic(){
            
            System.out.println("I AM min node LEAF: " + this);
            if(parentNode != null){
            
                System.out.println("My parents alpha is " + parentNode.alpha + "and my parents beta is " + parentNode.beta);
                System.out.println("My parent is: " + parentNode);
            }
            else{
                System.out.println("I am the Root");
            }
            int scoreHolder = TestMain.treeValues[TestMain.treeHolder];// is saved
            System.out.println("the scoreHodler value is: " +scoreHolder);
            TestMain.treeHolder++;
            System.out.println("The arrayPosition is at: " +TestMain.treeHolder + "for the next leaf");

            if(parentNode.nodeValue == 616 || scoreHolder > parentNode.nodeValue){

                 System.out.println("Parent node value is now: " + scoreHolder + "and the depth of the parent node is: " + parentNode.depthLevel);
                parentNode.nodeValue = scoreHolder;
                if(parentNode.parentNode != null){
                
                    parentNode.parentNode.alpha = scoreHolder;
                }
                //parentNode.bestBoard = currentBoard;

            }
 
        }
        
        public void branch(){
            
            //System.out.println("a branch");
           // System.out.println("this node is " + isMaxNode);
           // System.out.println("its child is " +!isMaxNode);
           
           //AlphaBetaTreeTESTER parent,  int score, int depth, boolean isMaxNode, int alpha, int beta
            System.out.println("Have made a child Node at depthLevel:" + depthLevel);
            
           // children.add(new AlphaBetaTreeTESTER(this, depthLevel +1, !isMaxNode, alpha, beta));// it doesnt branch!?
           
            new AlphaBetaTreeTESTER(this, depthLevel +1, !isMaxNode, alpha, beta).getBestMove();
            
             

            //System.out.println("depthLevel is:" + depthLevel);
            //T.getBestMove();
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

                        System.out.println("Alpha changed to: " + parentNode.nodeValue);
                        parentNode.parentNode.alpha = parentNode.nodeValue; //give the MAX node the value as a alpha
                        //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                    }
                }
                else{
                
                     if(parentNode.nodeValue != 616){// if the value has been updated

                        System.out.println("Beta changed to: " + parentNode.nodeValue);
                        parentNode.parentNode.beta = parentNode.nodeValue; //give the MIN node the value as a beta
                        //parentNode.parentNode.nodeValue = parentNode.nodeValue;

                    }
                    
                
                }
            }
        }
        
        
        
    
    
    
}
