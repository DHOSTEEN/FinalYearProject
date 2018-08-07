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
    
     boolean allChildrenExplored;
     boolean isMax;
     int depth;
     int maxDepth = 2;
     int value = 616;
     int alpha;
     int beta;
     String nodeName;
   

    ArrayList<testTreeAlphaBeta> childNodes = new ArrayList<>();
    testTreeAlphaBeta parent;
   
   public testTreeAlphaBeta(testTreeAlphaBeta parent, boolean isMax, int depth){
       
      allChildrenExplored = false;
     // leafExplored; = false;
      this.parent = parent;
      this.isMax = isMax;
      this.depth = depth;
 
   }
   public testTreeAlphaBeta(testTreeAlphaBeta parent, boolean isMax, int depth, int alpha, int beta){
       
      allChildrenExplored = false;
//      leafExplored = false;
      this.parent = parent;
      this.isMax = isMax;
      this.depth = depth;
      this.alpha = alpha;
      this.beta = beta;
     
 
   }
   
   public int bestMove(){
   
        doThings();
        return value;
   }
   public void doThings(){
       System.out.println("I am at level of: "+ depth +  " I am: " + this + "a  " + isMax + " node");
        if(!(depth == maxDepth)){// not a leaf

            int count = 0;
            while(count<2){

                //System.out.println("making children");
               
                makeChildren();
                //propergate();
                count++;//ends loop
            }
            this.allChildrenExplored = true;
            propergate();
            
        }
        else{// is a leaf
                //System.out.println("is assigning");
                makeLeaf();
                assignValues();
                leafPropergate();
        }
        //System.out.println("propergating");
        
   
   }
   public void assignValues(){
   
       
       Random val = new Random();
       int x = val.nextInt(50)+1;
       //System.out.println(x);
       int aValue = x;
      // System.out.println("is assigning: " +childNodes.size());
    
        System.out.println("is value of: "+ aValue);
        value = aValue;
        
       
       

         /* System.out.println("is value of: "+ TestMain.treeValues[TestMain.treeHolder]);
            value = TestMain.treeValues[TestMain.treeHolder];
            TestMain.treeHolder++;*/
       


       
       
   
   }
   
   public void leafPropergate(){
   
       if(isMax){//sending to a min node
           if(value<parent.value || parent.value == 616){
            parent.value = value;
            //   System.out.println("parent(MIN) value is: " + value);
           }
       }
       else{
       
            if(value>parent.value || parent.value == 616){
            parent.value = value;
          //  System.out.println("parent(MAX) value is: " + value);
           }
       }
   }

   public void propergate(){
   
       //System.out.println(childNodes.size());
            /* if I am a leaf, all i need to do is fire my value UP based on the parents conditionals - wont go to children
       if i am NOT i need to:
       get a value
       check if I have a parent
       give my value to parent based on conditionals + alpha beta values
       decide wether to spawn children based on MY value and alpha/beta values
       complish*/
       
    

            if(allChildrenExplored){System.out.println("I AM: " + this + " I are done with kids");}
        if(!isMax){// i am a MIN

            if(value == 616){
                System.out.println("WTF");
            }
            if(parent!=null && value!= 616 && allChildrenExplored){
                if(value>parent.value || parent.value == 616){
                parent.alpha = value;
                parent.value = value;
                
              //  System.out.println("parent alpha is: " + value + " parent is: " + parent);
                }
            }

        }
        else{// i am a MAX
            if(value == 616){
                System.out.println("WTF");
            }
            if(parent!=null && value!= 616 && allChildrenExplored){
                if(value< parent.value || parent.value == 616){
                   // if(value>parent.alpha){
                        parent.beta = value;
                    //}
                    parent.value = value;
                    

                       //  System.out.println("parent beta is: " + value + " parent is: " + parent);
                }
                  //System.out.println(value + " max node updated ");
            }

        }

        
     
       
      

   }
   public void makeChildren(){
       /*
       
       if I am a leaf, all i need to do is fire my value UP based on the parents conditionals - wont go to children
       if i am NOT i need to:
       get a value
       check if I have a parent
       give my value to parent based on conditionals + alpha beta values
       decide wether to spawn children based on MY value and alpha/beta values
       complish
       
       */
   
        // System.out.println("child is: " + (depth+1));
       //  System.out.println("BEFORE new NODE : alpha = " + alpha + " beta = " + beta);
      //  testTreeAlphaBeta child = new testTreeAlphaBeta(this, !isMax, depth +1, alpha, beta);
      
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
        /*if(isMax && beta<=value  || alpha>=beta){

            System.out.println("has been pruned: alpha is: " + alpha + " beta is: " + beta);
            return;
        }*/
       // System.out.println("BEFORE new BRANCH at level " + depth + ": alpha = " + alpha + " beta = " + beta);
        
        new testTreeAlphaBeta(this, !isMax, depth+1, alpha, beta).branch();
       // System.out.println("AFTER new BRANCH at level "  + depth + ": alpha = " + alpha + "beta = " + beta);
        //propergate();//send value up
       

                //System.out.println("is branching");
             

        
   }
   public void makeLeaf(){
        childNodes.add(new testTreeAlphaBeta(this, !isMax, depth +1, alpha, beta));
   }
   public void branch(){
   
       bestMove();
   
   }
   
    /*
    
    (* Initial call *)
        alphabeta(origin, depth, -∞, +∞, TRUE)
    
    function alphabeta(node, depth, α, β, maximizingPlayer)
02      if depth = 0 or node is a terminal node
03          return the heuristic value of node
04      if maximizingPlayer
05          v := -∞
06          for each child of node
07              v := max(v, alphabeta(child, depth – 1, α, β, FALSE))
08              α := max(α, v)
09              if β ≤ α
10                  break (* β cut-off *)
11          return v
12      else
13          v := +∞
14          for each child of node
15              v := min(v, alphabeta(child, depth – 1, α, β, TRUE))
16              β := min(β, v)
17              if β ≤ α
18                  break (* α cut-off *)
19          return v*/
    
}

 