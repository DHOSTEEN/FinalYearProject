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
public class IllegalMoveException extends Exception{
    
    public IllegalMoveException()
 {
  super();
 }

 public IllegalMoveException(String message)
 {
  super(message);
 }
    
}
