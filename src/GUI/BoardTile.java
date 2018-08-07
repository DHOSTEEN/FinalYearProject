/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Daniel
 */
public class BoardTile {

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    private int row;
    private int col;
    
    public BoardTile(int row, int col){
        
        this.row = row;
        this.col = col;
    }
    
}
