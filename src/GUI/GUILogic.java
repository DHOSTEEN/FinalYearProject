/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Board.BoardUtilities;
import GUI.Pieces;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Daniel
 */
public class GUILogic {
    
    
    private static final boolean redColour = true;
    private static final boolean blackColour = false;
    private static final char[][] board = new char[8][8];
    
    protected static  final char redChecker = 'O';
    protected static final char redKingChecker = 'E';
    protected static final char blackChecker = 'X';
    protected static final char blackKingChecker = 'K';
    
    private JButton[][] buttons;
    private Pieces allPieces;
    public GUILogic(JButton[][] buttons, Pieces allPieces){
    
        this.buttons = buttons;
        this.allPieces = allPieces;
    }
    
    public char[][] readGUIBoard(JButton[][] buttons, Pieces allPieces){
    
        for(int i =0; i< buttons.length; i++){
        
            for(int j = 0; j<buttons[i].length; j++){
        
                if(allPieces.contains(i, j)){
                
                    if(allPieces.isColour(redColour, i, j) && allPieces.type(i, j)){//is red king
                    
                        board [i][j] = redKingChecker;
                    }
                    else if(allPieces.isColour(redColour, i, j)){//is normal red
                    
                        board[i][j] = redChecker;
                        
                    }
                    else if(allPieces.isColour(blackColour, i, j) && allPieces.type(i, j)){
                        
                        board[i][j] = blackKingChecker;
                    
                    }
                    else if(allPieces.isColour(blackColour, i, j)){
                    
                        board[i][j] = blackChecker;
                    
                    }
                    
                }
                else{
                
                    board[i][j] = BoardUtilities.getBoardSymbol(i, j);
                }
        
            }
            
            
        
        }
        return BoardUtilities.buildBoard(board);
    }
    
    
    
    public void doMove(int fromRow, int fromCol, int takeRow, int takeCol, int toRow, int toCol, boolean isRed, String icon) throws IOException{
         
          if(allPieces.contains(takeRow, takeCol)){
            
                  if(allPieces.getPiece(takeRow, takeCol).isColour() != isRed){
                  
                       takePiece(fromRow, fromCol, takeRow, takeCol, toRow, toCol, icon, isRed);
            
                  }
                //can take a piece
                //takePiece(fromRow, fromCol, takeRow, takeCol, toRow, toCol, icon);
            
            }
        
            checkKing(toRow, isRed, buttons[toRow][toCol]);
     
     
     }
     public void takePiece(int fromRow, int fromCol, int takeRow, int takeCol, int toRow, int toCol, String icon, boolean isRed) throws IOException{
         
        
           buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(buttons[fromRow][fromCol]);
           buttons[takeRow][takeCol].setIcon(null);
           allPieces.remove(buttons[takeRow][takeCol]);
           buttons[toRow][toCol].setIcon(new ImageIcon(icon));
           System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


            Image redimg = ImageIO.read(getClass().getResource(icon));

            buttons[toRow][toCol].setIcon(new ImageIcon(redimg));
            allPieces.add(new PiecePos(toRow, toCol, isRed, false, buttons[toRow][toCol]));

     
     }
    public void checkKing(int row, boolean isRed, JButton aButton) throws IOException{
    
        if(row == 7 && isRed){
        Image redimg = ImageIO.read(getClass().getResource("redKing.jpg"));
            aButton.setIcon(new ImageIcon(redimg));
        
        }
        else if(row ==0 && !isRed){
            Image redimg = ImageIO.read(getClass().getResource("blackKing.jpg"));
            aButton.setIcon(new ImageIcon(redimg));
        
        }
    
    }
    
   
    
   
    
}
