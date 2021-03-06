/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import AlphaBetaTreeV2.AlphaBetaTree;
import Board.BoardUtilities;
import Pieces.BoardMovesPair;
import Pieces.MoveCoordinates;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class AiVSAiGUI extends javax.swing.JFrame {

    /**
     * Creates new form Testy
     */
    private final int depthLevel = 7;
    
    private boolean player1 = true;
    private boolean moveFromP1 = false;
    private boolean moveFromP2 = false;
    private Pieces allPieces = new Pieces();
    private Hashtable<JButton, BoardTile> boardTiles = new Hashtable<>();
    
     private boolean multiMove = false;
     private boolean hasTakenMove= false;
    public AiVSAiGUI() {
        initComponents();
        initComponents2();
        
        //gameStart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        boardPane = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        moveDisplay = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Checker Board");

        javax.swing.GroupLayout boardPaneLayout = new javax.swing.GroupLayout(boardPane);
        boardPane.setLayout(boardPaneLayout);
        boardPaneLayout.setHorizontalGroup(
            boardPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        boardPaneLayout.setVerticalGroup(
            boardPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
        );

        jLabel2.setText("Game INFO");

        moveDisplay.setColumns(20);
        moveDisplay.setRows(5);
        jScrollPane1.setViewportView(moveDisplay);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(162, 162, 162))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(boardPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boardPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Testy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Testy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Testy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Testy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        AiVSAiGUI gui = new AiVSAiGUI();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });
        
        gui.gameStart();
    }
    
     private void initComponents2()
    {
        buttons = new JButton[8][8];
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setName("Checkers");
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 600));
        jPanel1.setSize(600,600);
        jPanel1.setLayout(new java.awt.GridLayout(8,8));
        

        for(int row = 0;row<buttons.length;row++)
        {
            //Image image = ;
            //Image img = new ImageIcon("/aplace");
            
       
    
            for(int col = 0;col<buttons[row].length;col++)
            {
                      Image redimg;
                      Image blackimg;
            try {
                redimg = ImageIO.read(getClass().getResource("redChecker.jpg"));
                blackimg = ImageIO.read(getClass().getResource("blackChecker.jpg"));
           
                buttons[row][col]=new javax.swing.JButton();
                buttons[row][col].setBorderPainted(false);
                buttons[row][col].setMaximumSize(new java.awt.Dimension(80, 80));
                buttons[row][col].setMinimumSize(new java.awt.Dimension(80, 80));
                buttons[row][col].setSize(60, 60);
                buttons[row][col].setRolloverEnabled(false);
                buttons[row][col].setName(row+", "+col);
               
               /*  MainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuButtonActionPerformed(evt);
            }
        });*/
               //new ButtonListener();
               // buttons[row][col].addActionListener(new ButtonListener(new Location(row,col),myGrid,this));
               // try {buttons[row][col].setIcon(myGrid.pieceAt(new Location(row,col)).getImage()); } catch(Exception ex) {}
               
                if(row%2==col%2)
                {
                    buttons[row][col].setForeground(Color.white);
                    buttons[row][col].setBackground(Color.white);
                    //blanck buttons here
                   
                   
                }
                else
                {
                    buttons[row][col].setForeground(Color.black);
                    buttons[row][col].setBackground(Color.black);
                     if(row<3){
                        JButton x = buttons[row][col];
                         x.setIcon(new ImageIcon(redimg));
                         allPieces.add(new PiecePos(row, col, true, false, x));//kay- search by object then
                    }
                    if(row>4 && row<8){
                         JButton x = buttons[row][col];
                         x.setIcon(new ImageIcon(blackimg));
                           allPieces.add(new PiecePos(row, col, false, false, x));
                         
                    }
                     boardTiles.put(buttons[row][col], new BoardTile(row, col));
               

                }
                jPanel1.add(buttons[row][col]);
                 } catch (IOException ex) {
                Logger.getLogger(Testy.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
        }
        boardPane.add(jPanel1);
       // myGrid.update();
        super.repaint();
    }// </editor-fold>
     
    

    private void checkKing(int row, boolean isRed, JButton aButton) throws IOException{
    
        if(row == 7 && isRed){
        Image redimg = ImageIO.read(getClass().getResource("redKing.jpg"));
            aButton.setIcon(new ImageIcon(redimg));
            int kingRow = allPieces.getPiece(aButton).getRow();
            int kingCol = allPieces.getPiece(aButton).getCol();
            allPieces.remove(aButton);
       
            allPieces.add(new PiecePos(kingRow, kingCol, isRed, true, aButton));
            
        
        }
        else if(row ==0 && !isRed){
            Image redimg = ImageIO.read(getClass().getResource("blackKing.jpg"));
            aButton.setIcon(new ImageIcon(redimg));
            
            int kingRow = allPieces.getPiece(aButton).getRow();
            int kingCol = allPieces.getPiece(aButton).getCol();
            allPieces.remove(aButton);
       
            allPieces.add(new PiecePos(kingRow, kingCol, isRed, true, aButton));
        
        }
    
    }
    
    public  void gameStart(){
    
        GUILogic logic = new GUILogic(buttons, allPieces);
        char[][] board = logic.readGUIBoard(buttons, allPieces);
        int control = 60;
        while(control > 1){
        AlphaBetaTree redAI = new AlphaBetaTree(null, true, 0, -1000, 1000, 0, board, depthLevel, null);
        
        BoardMovesPair moves =  redAI.getBestMove();
        
        board = moves.getBoard();
            BoardUtilities.printBoard(board);
        
            
        ArrayList<MoveCoordinates> allMoves = moves.getAllMoves();
       System.out.println("RED Move:");
        updateBoard(allMoves);
        
        
        AlphaBetaTree blackAI = new AlphaBetaTree(null, false, 0, -1000, 10000, 0, board, depthLevel, null);
        
        moves =  blackAI.getBestMove();
        
        board = moves.getBoard();
        allMoves = moves.getAllMoves();
        
            System.out.println("Black Move:");
             BoardUtilities.printBoard(board);
         updateBoard(allMoves);
        control--;
        System.out.println("Control is:" + control);
        
        }
        
    }
    
     public void addPiece(JButton button, boolean isRed, boolean isKing, int row, int col) throws IOException{
         
      GUILogic logic = new GUILogic();
         String icon = logic.getIcon(isRed, isKing);
         
            Image redimg = ImageIO.read(getClass().getResource(icon));

             button.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(row, col, isRed, isKing, button));
     
     }
     public void removePiece(JButton button, int row, int col){
     
         button.setIcon(null);
         allPieces.remove(button);
     }
    
   public void updateBoard(ArrayList<MoveCoordinates> allMoves){
       ///redo!
       //will get
   
       for(int i =0; i< allMoves.size(); i++){
           
              System.out.println(allMoves.get(i).getRow() + " - " + allMoves.get(i).getCol());
       
       }
       //multi takes being difficult
        int newMovePos = 0;
        String moveTally = "";
        
        boolean colour = true;
        boolean type = true;
        
        boolean firstMove = true;
        
        for(int i =0; i<allMoves.size(); i++){
            int counter = 0;
            int row = allMoves.get(i).getRow();
            int col = allMoves.get(i).getCol();
            
            if(firstMove){
            
                System.out.println("The row to save is: " + row);
                System.out.println("The col to save is: " + col);
                type = allPieces.type(row, col);
                colour =  allPieces.getColour(row, col);
                
                
                removePiece(buttons[row][col], row, col);
                System.out.println("Removed: " + row + "-" + col);
                firstMove = false;
            
            }
           
            else if(allMoves.get(i).isKill()){
                 System.out.println("Removed: " + row + "-" + col);
                removePiece(buttons[row][col], row, col);
            }
            else if(allMoves.get(i).getRow() == -1){
            
                 updateBoard( new ArrayList(allMoves.subList(i+1,allMoves.size())));
               break;
            }
           
            else if(!firstMove){
            
            
                try {
                    addPiece(buttons[row][col], colour, type, row, col);
                    checkKing(row, type, buttons[row][col]);
                } catch (IOException ex) {
                    Logger.getLogger(AiVSAiGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
         /* if(row == -1){// is end of move
            
                allMoves = new ArrayList(allMoves.subList(i+1,allMoves.size()));
                break;
            
            }
            if(allMoves.get(i).isKill()){
            
                moveTally += "Taken piece at: " +row + " - " + col+ "/n";
                removePiece(buttons[row][col], row, col);
                
            
            }
            else{
                 moveTally += row + " - " + col + "/n";
        
            }
            if(i == 0){
                System.out.println("row-col: " + row +"-"+col);
                //colour = allPieces.getPiece(row, col).isColour();
               // type = allPieces.getPiece(row, col).isKing();
                type = allPieces.getPiece(row, col).isKing();
                colour = allPieces.getPiece(row, col).isColour();
                removePiece(buttons[row][col], row, col);
            
            }
           else if(!allMoves.get(i).isKill()){
           
           
                try {
                    addPiece(buttons[row][col], colour, type, row, col);
                } catch (IOException ex) {
                    
                    System.out.println("is fali???");
                    Logger.getLogger(AiVSAiGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
           }*/
            
            
          repaint();
        
        }
       
   
   }
     
     
     
   
private JButton[][] buttons;
private JPanel jPanel1;
    // Variables declaration - do not modify                     
    private javax.swing.JPanel boardPane;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea moveDisplay;
    // End of variables declaration                   
}
