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
public class testTree {
    
     boolean allChildrenExplored;
     boolean isMax;
     int depth;
     int maxDepth = 2;
     Integer value = 616;
     Integer alpha = -1000;
     Integer beta = 1000;
     ArrayList<testTree> childNodes = new ArrayList<>();
     testTree parent;
   
   public testTree(testTree parent, boolean isMax, int depth){
       
      allChildrenExplored = false;
      this.parent = parent;
      this.isMax = isMax;
      this.depth = depth;
   }
   
   public int bestMove(){
   
      // System.out.println("I AM: " + this + "alpha = " + alpha +" beta = " + beta);
       if(parent!=null){
          // System.out.println("my parent is " + parent + " alpha = " + parent.alpha + " beta = " +  beta);
       }
       if(isMax){
           if(parent!=null && beta!=1000){
            if((parent.beta<=value) ){
                //System.out.println("Doing things");
                doThings();

            }
           }
           else{
           doThings();
           }
       }
       else{
           if(parent!=null){
            if( (parent.alpha>=value) && alpha != -1000){
               // System.out.println("doing min things");
                doThings();
            }
            else{
            doThings();
            }
           }
       }
       if(beta!=1000){
        if(beta<=value){System.out.println("beta prunded");}
       }
       if(alpha!=-1000){
        if(alpha>=value){System.out.println("alpha prunded");}
       }
       
       
       return value;
   }
   public void doThings(){
        if(!(depth == maxDepth)){


            int count = 0;
            while(count<2){

                //System.out.println("making children");
                makeChildren();
                count++;
            }
            for(testTree node : childNodes){

                //System.out.println("is branching");
                node.branch();

            }
           }
           else{
                //System.out.println("is assigning");
                makeChildren();
                assignValues();
           }
        //System.out.println("propergating");
           propergate();
   
   }
   public void assignValues(){
   
       Random val = new Random();
       int x = val.nextInt(50)+1;
       //System.out.println(x);
       int aValue = x;
      // System.out.println("is assigning: " +childNodes.size());
       for(testTree node : childNodes){
           System.out.println("is value of: "+ aValue);
            node.value = aValue;
            aValue++;

        }
       
   
   }
   public void propergate(){
   
       //System.out.println(childNodes.size());
       for(testTree node : childNodes){

            if(!isMax){
                if(value==616 || node.value<value){
                     value = node.value;
                     System.out.println(value + " min node value upddated ");
                }
                else{System.out.println("value skipped for min " + node.value);}
            }
            else{
                if(value == 616 || node.value> value){
                    value = node.value;
                      System.out.println(value + " max node updated ");
                }
                else{System.out.println("value skipped for max " + node.value);}
            }
            

        }
      if(depth != maxDepth){
        if(!isMax){

            if(parent!=null){
                System.out.println("alpha updated");
             parent.alpha = value;
            }
        }
        else{
            if(parent!=null){
                 System.out.println("beta updated");
             parent.beta = value;
            }
        }
      }
       
   }
   public void makeChildren(){
   
      // System.out.println("child is: " + (depth+1));
       childNodes.add(new testTree(this, !isMax, depth +1));
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
