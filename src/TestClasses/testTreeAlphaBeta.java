/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Daniel
 * le sigh - min max but NOT alpha beta. whhhhhhhhhhhhy
 */
public class testTreeAlphaBeta {
    
    private boolean allChildrenExplored;
    private boolean isMax;
    private boolean isUserInput;
    private  int depth;
    private  int maxDepth = 2;
    private  int value = 616;
    private  int alpha;
    private  int beta;
    private int branchFactor;

    private  ArrayList<testTreeAlphaBeta> childNodes = new ArrayList<>();
    private testTreeAlphaBeta parent;
   
   public testTreeAlphaBeta(testTreeAlphaBeta parent, boolean isMax, int depth, int alpha, int beta, boolean isUserInput, int maxDepth, int branchFactor){
       
    allChildrenExplored = false;
    this.parent = parent;
    this.alpha = alpha;
    this.beta = beta;
    this.isMax = isMax;
    this.depth = depth;
    this.maxDepth = maxDepth;
    this.isUserInput = isUserInput;
    this.branchFactor = branchFactor;

   }
   public testTreeAlphaBeta(testTreeAlphaBeta parent, boolean isMax, int depth, int alpha, int beta, int branchFactor){
       
      allChildrenExplored = false;
      this.parent = parent;
      this.isMax = isMax;
      this.depth = depth;
      this.alpha = alpha;
      this.beta = beta;
      this.branchFactor = branchFactor;
     
 
   }
   
   public int bestMove(){
   
        doThings();
        return value;
   }
   public void doThings(){
       System.out.println("I am at level of: "+ depth +  " I am: " + this + "a  " + isMax + " node");
        if(!(depth == maxDepth)){// not a leaf

            int count = 0;
            while(count<branchFactor){

               
                makeChildren();
                count++;//ends loop
            }
            this.allChildrenExplored = true;
            propergate();
            
        }
        else{// is a leaf
                makeLeaf();
                assignValues();
                leafPropergate();
        }
         
   }
   public void assignValues(){
   

        if(!isUserInput){
            Random val = new Random();
            int x = val.nextInt(50)+1;;
            int aValue = x;
            System.out.println("Leaf has a value of: "+ aValue);
            value = aValue;
        }
        else if(isUserInput){
            
            System.out.println("is value of: "+ TestMainExample.treeValues[TestMainExample.treePositionHolder]);
            value = TestMainExample.treeValues[TestMainExample.treePositionHolder];
            TestMainExample.treePositionHolder++;
        
        }

   }
   
   public void leafPropergate(){
   
       if(isMax){//sending to a min node
           if(value<parent.value || parent.value == 616){
            parent.value = value;
           }
       }
       else{
            if(value>parent.value || parent.value == 616){
            parent.value = value;
           }
       }
   }

   public void propergate(){
   

        if(allChildrenExplored){
            System.out.println("I AM: " + this + "\n My child nodes have been explored");
        }
        if(!isMax){// i am a MIN

            if(value == 616){
                System.out.println("User has inputed zero branches, defult value will be returned");
            }
            if(parent!=null && value!= 616 && allChildrenExplored){
                if(value>parent.value || parent.value == 616){
                parent.alpha = value;
                parent.value = value;

                }
            }

        }
        else{// i am a MAX
            if(value == 616){
                 System.out.println("User has inputed zero branches, defult value will be returned");
            }
            if(parent!=null && value!= 616 && allChildrenExplored){
                if(value< parent.value || parent.value == 616){
   
                    parent.beta = value;
                    parent.value = value;

                }

            }

        }

        
     
       
      

   }
   public void makeChildren(){

         if(!isMax && value!=616){
            if(alpha!=-1000){
                if(alpha>=value){

                System.out.println("a min node has pruned: alpha is: " + alpha + " beta is: " + beta + " value is: " + value);
                       return;
                }
                if(beta!=1000){
                    if(alpha>=beta){
                    
                        System.out.println("a min node has pruned: alpha is: " + alpha + " beta is: " + beta + " value is: " + value);
                        return;
                    }
                
                }
            }
         }
         else if(isMax && value!=616){

           if(beta!=1000){
                if(beta<=value){

                System.out.println("a max node has pruned: alpha is: " + alpha + " beta is: " + beta);
                       return;
                }
               if(alpha!=-1000){
                    if(alpha>=beta){
                    
                        System.out.println("a max node has pruned: alpha is: " + alpha + " beta is: " + beta);
                        return;
                    }
                
                }
            }
        }
       
        if(!isUserInput){
            new testTreeAlphaBeta(this, !isMax, depth+1, alpha, beta, branchFactor).branch();
        }
        else{
             new testTreeAlphaBeta(this, !isMax, depth+1, alpha, beta, isUserInput, maxDepth, branchFactor).branch();
        }
     
   }
   public void makeLeaf(){
        childNodes.add(new testTreeAlphaBeta(this, !isMax, depth +1, alpha, beta, branchFactor));
   }
   public void branch(){
   
       bestMove();
   
   }
   
  
}

 