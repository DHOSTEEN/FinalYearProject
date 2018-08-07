/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

/**
 *
 * @author Daniel
 */
public class MoveCoordinates {

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isKill() {
        return kill;
    }
    
    
    private final int row;
    private final int col;
    private final boolean kill;
    public MoveCoordinates(int row, int col, boolean isKill){
    
        this.row = row;
        this.col = col;
        this.kill = isKill;
        
    
    }
    
}
