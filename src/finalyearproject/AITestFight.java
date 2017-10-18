/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproject;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import static Board.BoardUtilities.printBoard;
import java.util.Date;

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
     long time1, time2;
     long sum= 0;
     char[][] oldBoard = board;
    
    while(count<30){
    
        AlphaBetaTree isRedAI = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, board, 5);
        time1 = new Date().getTime();
        board = isRedAI.getBestMove();
        if(board.equals(oldBoard)){
        
            break;
        }
        oldBoard = board;
        time2 = new Date().getTime();
        System.out.println("\nRed's turn: " + (count +1) );
        sum+= (time2-time1);
        System.out.println("It took " + (time2-time1 ) + "ms");
        printBoard(board);
        AlphaBetaTree isBlackAI = new AlphaBetaTree(null, false, 0, -1000, 1000, 0, board, 8);
        board = isBlackAI.getBestMove();
        if(board.equals(oldBoard)){
        
            break;
        }
          oldBoard = board;
        System.out.println("\nBlacks's turn: " + (count +1) );
        printBoard(board);
        
        count++;
    }
        System.out.println("Red pieces: " + BoardUtilities.countPieces('O', board));
        System.out.println("Red Kings: " + BoardUtilities.countPieces('E', board));
        System.out.println("Average move: " + (sum/30) + "ms" );
        
        System.out.println("Black pieces: " + BoardUtilities.countPieces('X', board));
        System.out.println("Black Kings: " + BoardUtilities.countPieces('K', board));
        
       
    }
    
}
