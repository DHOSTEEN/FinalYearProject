/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

/**
 *
 * @author Daniel
 */
public class BoardUtilities {
    
    public static char[][] buildBoard(char[][] board){
    
        
        char[][] newBoard = new char[8][8];
        for(int i =0; i<board.length; i++){
            for(int j = 0; j<board[i].length; j++){
                newBoard[i][j] = board[i][j];
            }

        }
        return newBoard;
    
    } 
    public static void printBoard(char[][] board){
        
        System.out.println("\n a board is printed \n");
         for (int i = 0; i< board.length; i++) {
            System.out.print(i);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.print("  A B C D E F G H");
    
    
    
    }
    
}
