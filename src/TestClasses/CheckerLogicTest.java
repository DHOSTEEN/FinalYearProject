/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import Pieces.IllegalMoveException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class CheckerLogicTest {
    
   public  static int counter;
    static ArrayList<CheckerTEST> redCheckers = new ArrayList<>();
    static ArrayList<CheckerTEST> blackCheckers = new ArrayList<>();
    
    static char[][] TEST ={
         {'W','B','W','B','W','B','W','B'},
         {'B','W','B','W','B','W','B','W'},
         {'W','B','W','B','W','B','W','B'},
         {'W','B','W','O','W','B','W','B'},
         {'W','B','X','B','X','B','X','B'},
         {'B','W','B','W','B','W','B','X'},
         {'X','B','X','B','X','B','X','B'},
         {'B','X','B','X','B','X','B','W'}

 } ;
    static char[][] TEST2 ={
         {'W','B','W','B','W','B','W','B'},
         {'B','W','B','W','B','O','B','W'},
         {'W','B','W','B','W','B','W','B'},
         {'W','O','W','O','W','B','W','B'},
         {'W','B','X','B','X','B','X','B'},
         {'B','W','B','W','B','W','B','X'},
         {'X','B','X','B','X','B','X','B'},
         {'B','X','B','X','B','X','B','W'}

 } ;
    static char[][] testy = {
    
        {'O', 'B','O', 'B','O', 'B','O', 'B',},
        {'B', 'O','B', 'O','B', 'O','B', 'O',},        
        {'O', 'B','O', 'B','O', 'B','O', 'B',},
        {'W', 'B','W', 'B','W', 'B','W', 'B',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'B','X','B','X','B','X','B','X'},
        {'X','B','X','B','X','B','X','B'},
        {'B','X','B','X','B','X','B','X'}
    };
    static char[][] testy2 = {
    
       {'W', 'B','X', 'B','W', 'B','W', 'B',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'W', 'B','W', 'B','W', 'B','W', 'B',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'W', 'B','W', 'B','W', 'B','W', 'B',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'B', 'O','B', 'W','B', 'W','B', 'W',}
    };
    static char[][] testy3 = {
    
       {'W', 'B','W', 'B','W', 'B','W', 'B',},
        {'B', 'W','E', 'W','O', 'W','B', 'W',},
        {'B', 'W','B', 'K','B', 'W','B', 'W',},
        {'W', 'B','O', 'B','O', 'B','W', 'B',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'W', 'B','W', 'B','W', 'B','E', 'B',},
        {'B', 'W','B', 'W','B', 'W','B', 'W',},
        {'B', 'O','B', 'W','B', 'W','B', 'W',}
    };
     static char[][] testy4 = {
    
    {'W', 'B','K', 'B','W', 'B','W', 'B',},
    {'B', 'X','W', 'K','W', 'W','B', 'W',},
    {'B', 'W','B', 'W','B', 'W','B', 'W',},
    {'W', 'B','W', 'B','W', 'B','W', 'B',},
    {'B', 'W','B', 'W','B', 'W','B', 'W',},
    {'W', 'B','W', 'B','W', 'B','E', 'B',},
    {'B', 'W','B', 'W','B', 'W','B', 'W',},
    {'B', 'O','B', 'W','B', 'W','B', 'W',}
    };
     
     /*RED TESTING*/
     /*Test for 3 possible branches: take left, take right and stop, take right and take right*/
     static char[][] redTest = { 
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'W','B','W','O','W','B','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','W','B','X','B','X','B','W'}
             
        } ;
    /*Test for 4 possible branches: take left, take right and stop, take right and take left, take right and take right*/
      static char[][] redTest2 = {
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'W','B','W','O','W','B','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','X','B','W','B','W','B','W'}
             
        } ;
       static char[][] redTest3= {
               {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'W','B','W','W','W','O','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','W','B','X','B','W','B','W'}
             
        } ;
         static char[][] redTest4 = {
               {'W','B','W','B','W','B','W','B'},
                {'B','O','B','W','B','W','B','W'},
                {'W','B','X','B','W','B','W','B'},
                {'W','B','W','W','W','O','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','W','B','X','B','W','B','W'}
             
        } ;
         /*KING TESTING*/
      /*TEst for many poss*/
         static char[][] redKingTest = {
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','E','B','W','B','W'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','X','B','W','B','W','B','W'}
             
        } ;
          static char[][] redKingTest2 = {
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','X','B','W'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','E','B','W','B','W'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','X','B','W','B','W','B','W'}
             
        } ;
    public static void main (String args[]){
        
        //redTest();
       redKingTest();
        
        
    
    }
    
    public static void redTest(){
        
        char[][] currentBoard = redTest4;
        
        for(int i =0; i<currentBoard.length; i++){// makes a Checker as per board
            
                for(int j = 0; j<currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] ==  'O'){
                    
                        System.out.println("i = " + i + " j = " + j);
                        redCheckers.add(new CheckerTEST(i, j, true, new AlphaBetaTree (null, true, 0, -1000, 1000, 0, currentBoard)));
                       
                    
                    }
                    else if(currentBoard[i][j] == 'X'){
                    
                        //blackCheckers.add(new Checker(i, j, false, false, false));
                    
                    }                
                }            
        }
        
             try{
            redCheckers.get(0).moveLeft(currentBoard);
            //System.out.println(testPair.getScore());
           // BoardUtilities.printBoard(testPair.getBoard());
            System.out.println("\nA  MOVED LEFT \n");
            
        
            }catch(IllegalMoveException e){System.out.println(e.getMessage());}
        try{
            redCheckers.get(0).moveRight(currentBoard);
            // System.out.println(testPair.getScore());
           // BoardUtilities.printBoard(testPair.getBoard());
              System.out.println("\nA MOVED RIGHT \n");
        }catch(IllegalMoveException e){System.out.println(e.getMessage());}
        
      
       
        BoardUtilities.printBoard(currentBoard);
        int x = -1;
        int y = 2;
       
       // System.out.println("\n" + (y+(-x*2)));
        System.out.println("\n" +counter);
    
    
    }
    
    public static void redKingTest(){
    
        char[][] currentBoard = redKingTest;
        
        for(int i =0; i<currentBoard.length; i++){// makes a Checker as per board
            
                for(int j = 0; j<currentBoard[i].length; j++){
                
                    if(currentBoard[i][j] ==  'E'){
                    
                        System.out.println("i = " + i + " j = " + j);
                        redCheckers.add(new CheckerKingTEST(i, j, true, new AlphaBetaTree (null, true, 0, -1000, 1000, 0, currentBoard)));
                       
                    
                    }
                    else if(currentBoard[i][j] == 'X'){
                    
                        //blackCheckers.add(new Checker(i, j, false, false, false));
                    
                    }                
                } 
                
        }
    
             try{
            ((CheckerKingTEST)redCheckers.get(0)).moveLeft(currentBoard);
            //System.out.println(testPair.getScore());
           // BoardUtilities.printBoard(testPair.getBoard());
            System.out.println("\nA  MOVED LEFT \n");
            
        
            }catch(IllegalMoveException e){System.out.println(e.getMessage());}
        try{
            ((CheckerKingTEST)redCheckers.get(0)).moveRight(currentBoard);
            // System.out.println(testPair.getScore());
           // BoardUtilities.printBoard(testPair.getBoard());
              System.out.println("\nA MOVED RIGHT \n");
        }catch(IllegalMoveException e){System.out.println(e.getMessage());}
        
       try{
        ((CheckerKingTEST)redCheckers.get(0)).moveBackLeft(currentBoard);
             //System.out.println(testPair.getScore());
            //BoardUtilities.printBoard(testPair.getBoard());
            System.out.println("\nA NEW MOVE \n");
        }catch(IllegalMoveException e){System.out.println(e.getMessage());}
        try{
        ((CheckerKingTEST)redCheckers.get(0)).moveBackRight(currentBoard);
          //   System.out.println(testPair.getScore());
           // BoardUtilities.printBoard(testPair.getBoard());
            System.out.println("\nA NEW MOVE \n");
        }catch(IllegalMoveException e){System.out.println(e.getMessage());}
       
        BoardUtilities.printBoard(currentBoard);
        int x = -1;
        int y = 2;
       
        System.out.println("\n" + (y+(-x*2)));
        System.out.println(counter);
    }
    
}
