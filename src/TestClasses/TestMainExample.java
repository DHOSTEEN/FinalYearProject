/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClasses;

import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class TestMainExample {
    
    static int treePositionHolder =0;
    static int[] treeValues;
    private static boolean ownValues;
    public static void main(String[] args){
        
        System.out.println("This test will output the current level of the tree as it generates nodes where level 0 is the root, "
                + "whether it is a min or as max node (false and true respectivly),"
                + "the value a leaf node recieves and where alpha beta pruning takes place"
                + "It also will display when all child nodes are completely explored");
        System.out.println("Enter the depth of the tree, integers only:");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        int depth = Integer.parseInt(input);
        System.out.println("Enter the number of branches per level, integers only");
        input = scan.nextLine();
        int branches = Integer.parseInt(input);
        System.out.println("Using random values? y/n");
        input = scan.nextLine();
        if(input.equals("n")){
            ownValues = true;
            System.out.println("Enter values to be used in this format: \n"
                    + "1,2,3,4 for a for leaf tree /n"
                    + "PLEASE NOTE - THESE ARE THE VALUES FOR LEAVES THAT ARE GENERATED, NOT ALL POSSIBLE LEAVES \n"
                    + "eg: 2,7,1,8 would only use the first 3 values, in bigger trees this may cause unexpected results");
            input = scan.nextLine();
            String[] inputSplit = input.split(",");
            treeValues = new int[inputSplit.length];
            for(int i =0; i<treeValues.length; i++){

                treeValues[i] = Integer.parseInt(inputSplit[i]);
            }
        }
        else{
        
            ownValues = false;
        }
        System.out.println(ownValues);
        testTreeAlphaBeta tree = new testTreeAlphaBeta( null, true, 0, -1000, 1000, ownValues, depth, branches);
        int x = tree.bestMove();

        System.out.println("\n Final value is: " +x);
        
        
        
    
    }
    
}
