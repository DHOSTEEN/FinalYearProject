/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JButton;

/**
 *
 * @author Daniel
 */
public class Pieces {
    
    private ArrayList<PiecePos> blacks;
    private ArrayList<PiecePos> reds;
    
    private Hashtable<String, PiecePos> lookUp = new Hashtable<>();
    private Hashtable<JButton, PiecePos> boardLookUp = new Hashtable<>();
    
    public void add(PiecePos piece){
    
        System.out.println("ADDED");
        System.out.println("Added in row: " +piece.getRow());
         System.out.println("Added in col: " +piece.getCol());
         Integer wibble = piece.getRow() + piece.getCol();
         System.out.println("wible is " + piece.getRow() + "-" + piece.getCol());
        String key = piece.getRow() + "-" + piece.getCol();
        lookUp.put(key, piece);
        boardLookUp.put(piece.getButton(),piece);
    }
    
    public boolean contains(int row, int col){
        String key = row +"-"+ col;
        
        
       return lookUp.containsKey(key);
    
    }
    public boolean contains(JButton button){
    
        return boardLookUp.containsKey(button); 
    }
    public PiecePos getPiece(JButton button){
        
        return boardLookUp.get(button);
    
    
    }
    public PiecePos getPiece(int row, int col){
        String key = row+ "-"+ col;
        return lookUp.get(key);
    
    }
    public boolean isColour(boolean colour, int row, int col){
    
           String key = row+ "-"+ col;
        return lookUp.get(key).isColour() == colour;
    
    }
    public boolean getColour(int row, int col){
         String key = row+ "-"+ col;
        return lookUp.get(key).isColour();
    
    }
    public boolean type(int row, int col){
    
           String key = row+ "-"+ col;
           System.out.println(row+ "-"+ col);
        return (lookUp.get(key)).isKing();
    }
    public void remove(JButton button){
    
        String key = boardLookUp.get(button).getRow() +"-"+ boardLookUp.get(button).getCol();
    
        lookUp.remove(key);
        boardLookUp.remove(button);
    
    }
}
