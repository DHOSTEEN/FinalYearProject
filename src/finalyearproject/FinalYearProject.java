    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalyearproject;

import AlphaBetaTree.AlphaBetaNode;
import AlphaBetaTree.AlphaBetaNodeTEST;
import AlphaBetaTree.AlphaBetaTreeNode;
import java.util.Scanner;

/**
 *
 * @author Daniel
 * 
 * 
 * IDEAS:
 * 
 * MUST(?) calculate dynamically - other oprion is to know all possible arrangements of a board, search this,
 * then use for MinMax calculations. possible - seems excessive
 * 
 * boolean array along side traditional array or arraylist for positions. will find out if board space is occupied (T)
 * or empty (F) to trigger capture logic
 * 
 * alpha-beta nodes are created with a temp(?) copy of the board to use for minmax
 * 
 * Predictions: first few moves will be long time to calculate as few chances of - or + moves
 * 
 * essential logic: root is the final move decision  - will have 4 child nodes, as will each in turn. (for a 
 * 8 sided board
 * 
 * attempt at algorithm: move a piece, assign score (1 for no take, +2 for capture, -2 for loss)
 * LOGIC:
 * MAX
 * MIN
 * MAX
 * MIN
 * ....
 * and so on
 * 
 */
public class FinalYearProject {

    /**
     * @param args the command line arguments
     */
    static char[][] board;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
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
        char[][] TEST ={
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'W','B','W','O','W','B','W','B'},
                {'W','B','X','B','X','B','X','B'},
                {'B','W','B','W','B','W','B','X'},
                {'X','B','X','B','X','B','X','B'},
                {'B','X','B','X','B','X','B','W'}
             
        } ;
        board = buildBoard();
        System.out.println("THIS IS BAORD:  ");
        printBoard(board);
       //printBoard(atestyBoard);
       placePeices(board);
       // System.out.println("\n");
      printBoard(board);
       
        System.out.println("BEFORE");
            //AlphaBetaNode test = new AlphaBetaNode(board, 0 , 0, true);
            //board = test.realBestMove();
            //if(board == null){
            
              //  System.out.println("WOT");
           // }
            //else{
              //  printBoard(board);
           // }
            //System.out.println("AFTER"); 
           // 
            int control = 14;
          while(control>0){
            
                AlphaBetaTreeNode test = new AlphaBetaTreeNode(board, 0, 0 ,true);
                board = test.realBestMove();
                    System.out.println("RED TURN \n");
                    if(board != null){
                         printBoard(board);
                    }
                 AlphaBetaNodeTEST test2 = new AlphaBetaNodeTEST(board, 0, 0, true);
                 board = test2.realBestMove();
                System.out.println("BLACK TURN \n");
                if(board != null){
                     printBoard(board);
                }
                  control --;
          }   
             System.out.println("\nblack = : " + count('X', board) + " red = : " + count('O', board));
                    
                
        
    
        /*while(!in.equals("S")){
            
            
         in = input.nextLine();
         in = in.toUpperCase();
         String[] movePut = in.split("-");
         char[] from = movePut[0].toCharArray();
         char[] goTo = movePut[1].toCharArray();
         
         int x = (int)from[0];
         int y = (int)from[1];
            System.out.println(x - 65);//handles uppercase string to number
            System.out.println("is num "+ (y - 48));
            
                      
         moveTEST(from, goTo);
           
           // System.out.println("NEW BOARD BELOW\n");
         printBoard(board);
         test = new AlphaBetaNode(board, 0 , 0, true);
         board = test.realBestMove();
            System.out.println("comouter turn");
            printBoard(board);
         
         //for(int i =0; i<from.length; i++){
         
             //System.out.println(from);
         
         //}
          //System.out.println(in);
        //}
            
        
    }*/
    }
    public static void moveTEST(char[] from, char[] to){
        
        int fromCol = (int)from[0] - 65;
        int fromRow = (int)from[1] - 48;
        
        int toCol = (int)to[0] - 65;
        int toRow = (int)to[1] - 48;
        
        System.out.println("on board is " +board[fromRow][fromCol]);
        System.out.println("the cordinates are: " + fromRow + " and " + fromCol );
        
        
        
        if(board[fromRow][fromCol] == 'X'){
            System.out.println("IN IF\n");
            if(board[toRow][toCol] == 'B' || board[toRow][toCol] == 'W'){
            
                if(board[fromRow][fromCol]%2==0){
                    board[fromRow][fromCol] = 'W';
                    board[toRow][toCol] = 'X';
                }
                else{
                    board[fromRow][fromCol] = 'B';
                    board[toRow][toCol] = 'X';
                }            
            }
            else if(board[toRow][toCol] == 'O'){
            
                if(toRow<fromRow){// left
                    if(board[toRow-1][toCol-1] == 'B' || board[toRow-1][toCol-1] == 'W'){
                    
                        board[toRow-1][toCol-1] = 'X';
                        
                        if(board[fromRow][fromCol]%2==0){
                            board[fromRow][fromCol] = 'W';
                            board[toRow][toCol] = 'X';
                        }
                        else{
                            board[fromRow][fromCol] = 'B';
                            board[toRow][toCol] = 'X';
                        }            
                    
                    }
                }
                else if(toRow>fromRow){//
                    
                    if(board[toRow-1][toCol+1] == 'B' || board[toRow+1][toCol-1] == 'W'){
                    
                        board[toRow-1][toCol+1] = 'X';
                         if(board[fromRow][fromCol]%2==0){
                            board[fromRow][fromCol] = 'W';
                            board[toRow][toCol] = 'X';
                        }
                        else{
                            board[fromRow][fromCol] = 'B';
                            board[toRow][toCol] = 'X';
                        }            
                        
                    }
                }
            
            }
        }
    
    
    }
    
    public static char[][] buildBoard(){
    
        char[][] ret = new char[8][8]; 
        for(int i=0; i<ret.length; i++){
        
            for(int j = 0; j< ret[i].length; j++){
            
                ret[i][j] = boardSymbol(i, j);
              /*  if(i%2==0){
                
                    if(j%2==0){
                    
                        ret[i][j] = 'W';                    }
                    else{
                    
                        ret[i][j] = 'B';                    
                    }
                
                }
                else{
                
                     if(!(j%2==0)){
                    
                        ret[i][j] = 'W';                    }
                    else{
                    
                        ret[i][j] = 'B';                    
                    }                
                }   */         
            }        
        }
        return ret;
    }
    
    public static char boardSymbol(int i, int j){
        
        if(i%2==0 && j%2==0){return 'W';}
        else if(i%2==0){return 'B';}
        else if(j%2==0){return 'B';}
        else{return 'W';}
    
    }
    
    public static void placePeices(char[][] board){
        
        for(int i =0; i<3; i++){
        
            for(int j =0; j<board[i].length; j++){
            
                 if(i%2==0){
                
                    if(j%2==0){
                    
                        board[i][j] = 'O';
                    }               
                }
                 else{
                     
                     if(!(j%2==0)){
                    
                        board[i][j] = 'O';
                    }               
                 
                 }
        
            }
        }
        
        
        for(int i =5; i<board.length; i++){
            
             for(int j =0; j<board[i].length; j++){
            
                 if(i%2==0){
                
                    if(j%2==0){
                    
                        board[i][j] = 'X';
                    }               
                }
                else{
                     
                     if(!(j%2==0)){
                    
                        board[i][j] = 'X';
                    }               
                 
                 }
        
            }
        
        }
    }
    
    
    public static void printBoard(char[][] board){
    
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
    public static int count(char x, char[][] board){
    
        int count =0;
        for(int i =0; i< board.length; i++){
        
            for(int j =0; j< board[i].length; j++){
            
                if(board[i][j] == x){
                    count ++;
                }
            
            }
        }
        
        return count;
    
    }
    
    
    
}

