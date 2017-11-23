/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javax.swing.JButton;

/**
 *
 * @author Daniel
 */
public class PiecePos {

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }
    
    
    
private int row;
private int col;
private boolean colour;

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
private JButton button;

    public boolean isKing() {
        return isKing;
    }

    public void setIsKing(boolean isKing) {
        this.isKing = isKing;
    }
private boolean isKing;

    public PiecePos(int row, int col, boolean colour, boolean isKing, JButton button){
            this.row = row;
            this.col = col;
            this.colour = colour;
            this.isKing = isKing;
            this.button = button;
    }
    public PiecePos(int row, int col, JButton button){
        
            this.row = row;
            this.col = col;
            this.button = button;
    
    }
    
    
}

