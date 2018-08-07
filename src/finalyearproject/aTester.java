/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproject;

import AlphaBetaTreeV2.AlphaBetaTree;
import static Board.BoardUtilities.printBoard;

/**
 *
 * @author Daniel
 */
public class aTester {
    
    public static void main(String args[]){
    
        //(AlphaBetaTree parent, boolean myType, int newDepthLevel,int passedAlpha, int passedBeta, int culmativeScore, char[][] passedBoard)
    
        
        char[][] atestyBoard = {
                {'O','B','O','B','O','B','O','B'},
                {'B','O','B','O','B','O','B','O'},
                {'O','B','W','B','O','B','O','B'},
                {'W','B','W','O','W','B','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','X','B','X','B','X','B','W'}
             
        } ;
        
         char[][] atestyBoard2 = {
                {'W','B','O','B','O','B','O','B'},
                {'B','O','B','O','B','O','B','O'},
                {'O','B','W','B','O','B','W','B'},
                {'W','B','W','O','W','O','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','X','B','X','B','X','B','W'}
             
        } ;
         char[][] redTest = { 
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'W','B','W','O','W','B','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','X','B','W','B','X','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','W','B','X','B','X','B','W'}
             
        } ;
        char[][] board = FinalYearProject.buildBoard();
        FinalYearProject.placePeices(board);
        
         System.out.println("RED PIECE BOARD");
        printBoard(atestyBoard);
      
        AlphaBetaTree isTest = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, redTest,3);
        System.out.println("\n RED BEST MOVE :");
        printBoard(isTest.getBestMove());
           System.out.println("\n IS VALUE OF: " +isTest.nodeValue);
           
        printBoard(atestyBoard2);
       
         System.out.println("\n BLACK PIECE BOARD");
        FinalYearProject.placePeices(board);
        AlphaBetaTree isTestR = new AlphaBetaTree(null, false, 0, -1000, 1000, 0, atestyBoard2,3);
       System.out.println("\n BLACK BEST MOVE :");
        printBoard(isTestR.getBestMove());
        System.out.println("\n Balck??? IS VALUE OF: " +isTestR.nodeValue);
    }
    
}
