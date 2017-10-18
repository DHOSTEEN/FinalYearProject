/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproject;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import static Board.BoardUtilities.printBoard;

/**
 *
 * @author Daniel
 */
public class AITestFight {
    public static void main(String args[]){
    
        //(AlphaBetaTree parent, boolean myType, int newDepthLevel,int passedAlpha, int passedBeta, int culmativeScore, char[][] passedBoard, int maxDepth)

        char[][] board = FinalYearProject.buildBoard();
        FinalYearProject.placePeices(board);
    int count = 0;    
    
    while(count<30){
    
        AlphaBetaTree isRedAI = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, board, 3);
        board = isRedAI.getBestMove();
        System.out.println("\nRed's turn: " + (count +1) );
        printBoard(board);
        AlphaBetaTree isBlackAI = new AlphaBetaTree(null, false, 0, -1000, 1000, 0, board, 5);
        board = isBlackAI.getBestMove();
        System.out.println("\nBlacks's turn: " + (count +1) );
        printBoard(board);
        
        count++;
    }
        System.out.println("Red pieces: " + BoardUtilities.countPieces('O', board));
        System.out.println("Red Kings: " + BoardUtilities.countPieces('E', board));
        
        System.out.println("Black pieces: " + BoardUtilities.countPieces('X', board));
        System.out.println("Black Kings: " + BoardUtilities.countPieces('K', board));
        
       
    }
    
}
