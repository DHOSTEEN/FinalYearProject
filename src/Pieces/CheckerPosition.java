/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

/**
 *
 * @author Daniel
 * 
 * alt to storing integer position in checker class
 */
public class CheckerPosition {

    private int row;
    private int column;
    
    public CheckerPosition(int initalRow, int initialColumn){
        
        row = initalRow;
        column = initialColumn;
    
    }
    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
}
