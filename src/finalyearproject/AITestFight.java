/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproject;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import static Board.BoardUtilities.printBoard;
import Pieces.BoardMovesPair;
import Pieces.MoveCoordinates;
import java.util.ArrayList;
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
     long redSum= 0;
     long blackSum =0;
    /* char [][] board = { 
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'W','B','W','O','W','B','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','W','B','X','B','X','B','W'}
             
        } ;*/
     
     char[][] oldBoard = board;
        System.out.println("WIBB");
         printBoard(board);
    while(count<30){
        
        time1 = new Date().getTime();
        for(int i =0; i<6; i++){
        
           // AlphaBetaTree isRedAI = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, board, i);
        
             //isRedAI.getBestMove();
        
        }
        ArrayList<MoveCoordinates> aThing = new ArrayList<>();
       
        AlphaBetaTree isRedAI = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, board, 8, aThing);
       // time1 = new Date().getTime();
        BoardMovesPair best= isRedAI.getBestMove();
        board = best.getBoard();
        if(board.equals(oldBoard)){
        
            break;
        }
        oldBoard = board;
        time2 = new Date().getTime();
        System.out.println("\nRed's turn: " + (count +1) );
        redSum+= (time2-time1);
        System.out.println("It took " + (time2-time1 ) + "ms");
        //printBoard(board);
        ArrayList<MoveCoordinates> allMoves = best.getAllMoves();
        for(int i =0; i<allMoves.size(); i++){
        
            //System.out.println(allMoves.get(i).getRow() + " - " + allMoves.get(i).getCol());
        
        }
        
        
        AlphaBetaTree isBlackAI = new AlphaBetaTree(null, false, 0, -1000, 1000, 0, board, 3, aThing);
        
        
        time1 = new Date().getTime();
        best = isBlackAI.getBestMove();
        board = best.getBoard();
        if(board.equals(oldBoard)){
        
            break;
        }
          oldBoard = board;
          time2 = new Date().getTime();
        System.out.println("\nBlacks's turn: " + (count +1) );
         blackSum+= (time2-time1);
        System.out.println("It took " + (time2-time1 ) + "ms");
        //printBoard(board);
        
         allMoves = best.getAllMoves();
        for(int i =0; i<allMoves.size(); i++){
        
          // System.out.println("UM!? " + allMoves.get(i).getRow() + " - " + allMoves.get(i).getCol());
        
        }
        
        count++;
    }
        System.out.println("Red pieces: " + BoardUtilities.countPieces('O', board));
        System.out.println("Red Kings: " + BoardUtilities.countPieces('E', board));
        System.out.println("Average move: " + (redSum/(count+1)) + "ms" );
        
        System.out.println("Black pieces: " + BoardUtilities.countPieces('X', board));
        System.out.println("Black Kings: " + BoardUtilities.countPieces('K', board));
        System.out.println("Average move: " + (blackSum/(count+1)) + "ms" );
        printBoard(board);
       
    }
    
}
