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
        printBoard(atestyBoard);
        char[][] board = FinalYearProject.buildBoard();
        FinalYearProject.placePeices(board);
        AlphaBetaTree isTest = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, atestyBoard);
        System.out.println("IS VALUE OF: " +isTest.nodeValue);
        printBoard(isTest.getBestMove());
           System.out.println("IS VALUE OF: " +isTest.nodeValue);
    }
    
}
