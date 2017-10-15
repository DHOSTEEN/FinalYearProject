/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaBetaTree;

/**
 *
 * @author Daniel
 */
public class AlphaBetaPruningException extends Exception{
    
    public AlphaBetaPruningException(){
        super();
    }

    public AlphaBetaPruningException(String message){
        super(message);
    }
    
}
