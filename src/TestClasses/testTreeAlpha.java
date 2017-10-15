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
public class testTreeAlpha {
    
     boolean allChildrenExplored;
     boolean isMax;
     int depth;
     int maxDepth = 2;
     int value = 616;
     int alpha;
     int beta;
     String nodeName;
   

     ArrayList<testTreeAlpha> childNodes = new ArrayList<>();
     testTreeAlpha parent;
   
   public testTreeAlpha(testTreeAlpha parent, boolean isMax, int depth){
       
      allChildrenExplored = false;
      this.parent = parent;
      this.isMax = isMax;
      this.depth = depth;
 
   }
   public testTreeAlpha(testTreeAlpha parent, boolean isMax, int depth, int alpha, int beta){
       
      allChildrenExplored = false;
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
                count++;
            }
            
        }
        else{// is a leaf
                //System.out.println("is assigning");
                makeLeaf();
                assignValues();
        }
        //System.out.println("propergating");
        propergate();
   
   }
   public void assignValues(){
   
       /*
       Random val = new Random();
       int x = val.nextInt(50)+1;
       //System.out.println(x);
       int aValue = x;
      // System.out.println("is assigning: " +childNodes.size());
       for(testTreeAlpha node : childNodes){
           System.out.println("is value of: "+ aValue);
            node.value = aValue;
            aValue++;

        }*/
       
       
      for(testTreeAlpha node : childNodes){
           System.out.println("is value of: "+ TestMain.treeValues[TestMain.treeHolder]);
            node.value = TestMain.treeValues[TestMain.treeHolder];
            TestMain.treeHolder++;

        }
       
       
   
   }
   public void propergate(){
   
       //System.out.println(childNodes.size());
        for(testTreeAlpha node : childNodes){

            if(!isMax){// i am a MIN
                if(value==616 || node.value<value){
                     value = node.value;
                     if(parent!=null){
                        parent.alpha = value;
                        parent.value = value;
                         System.out.println("parent alpha is: " + value + " parent is: " + parent);
                     }
                        //System.out.println(value + " min node value upddated ");
                }
                else{
                   // System.out.println("value skipped for min " + node.value);
                }
            }
            else{// i am a MAX
                if(value == 616 || node.value> value){
                    value = node.value;
                     if(parent!=null){
                        parent.beta = value;
                        parent.value = value;
                         System.out.println("parent beta is: " + value + " parent is: " + parent);
                        }
                      //System.out.println(value + " max node updated ");
                }
                else{
                   // System.out.println("value skipped for max " + node.value);
                }
            }
        
       


        }
     
       
      

   }
   public void makeChildren(){
   
        // System.out.println("child is: " + (depth+1));
         System.out.println("BEFORE new NODE : alpha = " + alpha + " beta = " + beta);
        testTreeAlpha child = new testTreeAlpha(this, !isMax, depth +1, alpha, beta);
         if(!isMax && value!=616){
            if(alpha!=-1000){
                if(alpha>=value){

                System.out.println("has been pruned: alpha is: " + alpha + " beta is: " + beta);
                       return;
                }
                if(beta!=1000){
                    if(alpha>=beta){
                    
                        System.out.println("has been pruned: alpha is: " + alpha + " beta is: " + beta);
                        return;
                    }
                
                }
            }
         }
        if(isMax && value!=616){

           if(beta!=1000){
                if(beta<=value){

                System.out.println("has been pruned: alpha is: " + alpha + " beta is: " + beta);
                       return;
                }
                if(alpha!=-11000){
                    if(alpha>=beta){
                    
                        System.out.println("has been pruned: alpha is: " + alpha + " beta is: " + beta);
                        return;
                    }
                
                }
            }
        }
        /*if(isMax && beta<=value  || alpha>=beta){

            System.out.println("has been pruned: alpha is: " + alpha + " beta is: " + beta);
            return;
        }*/
        System.out.println("BEFORE new BRANCH: alpha = " + alpha + " beta = " + beta);
        child.branch();
        
        childNodes.add(child);
       

                //System.out.println("is branching");
             

        
   }
   public void makeLeaf(){
        childNodes.add(new testTreeAlpha(this, !isMax, depth +1, alpha, beta));
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
