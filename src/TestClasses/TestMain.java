/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

/**
 *
 * @author Daniel
 */
public class TestMain {
    
  //static int[] treeValues = {2,7,1,8}; //wworks with depth value 2
   // static int[] treeValues ={8,7,3,9,8,1,8,9,9,9};//depth == 4
    // static int[] treeValues ={3,17,2,15,16,2,3,3};//depth == 4
   //static int[] treeValues ={10,17,4,8,6,9,11,12,1};
    //static int[] treeValues ={15,19,33,1,18,9,11,12};
    static int treeHolder = 0;
            
    public static void main(String args[]){
    
        //AlphaBetaTreeTESTER testLogic = new AlphaBetaTreeTESTER(0,true);
  
            //System.out.println("The returned value is: " + testLogic.realBestMove());
        int y = 0;
        String word = String.valueOf((char)(y + 65));
        System.out.println(word);


        testTreeAlphaBeta aTest = new testTreeAlphaBeta(null, true, 0, -1000, 1000);
        int x = aTest.bestMove();

        System.out.println("final value is: " +x);
        
    }
        
    
}
    

