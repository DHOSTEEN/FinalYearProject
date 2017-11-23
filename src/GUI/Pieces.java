/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guichecker;

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
    
    private Hashtable<Integer, PiecePos> lookUp = new Hashtable<>();
    private Hashtable<JButton, PiecePos> boardLookUp = new Hashtable<>();
    
    public void add(PiecePos piece){
    
        System.out.println("ADDED");
        Integer key = piece.getRow() + piece.getCol();
        lookUp.put(key, piece);
        boardLookUp.put(piece.getButton(),piece);
    }
    
    public boolean contains(int row, int col){
        Integer key = row + col;
        
        
       return lookUp.containsKey(key);
    
    }
    public boolean contains(JButton button){
    
        return boardLookUp.containsKey(button); 
    }
    public PiecePos getPiece(JButton button){
        
        return boardLookUp.get(button);
    
    
    }
    public boolean isColour(boolean colour, int row, int col){
    
        Integer key = row + col;
        return lookUp.get(key).isColour() == colour;
    
    }
    public boolean type(int row, int col){
    
        Integer key = row + col;
        return lookUp.get(key).isKing();
    }
    public void remove(int row, int col){
    
        Integer key = row + col;
        JButton x = lookUp.get(key).getButton();
        lookUp.remove(key);
        boardLookUp.remove(x);
    
    }
}
