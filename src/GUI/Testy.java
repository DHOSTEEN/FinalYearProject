/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
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
public class Testy extends javax.swing.JFrame {

    /**
     * Creates new form Testy
     */
    private boolean player1 = true;
    private boolean moveFromP1 = false;
    private boolean moveFromP2 = false;
    private Pieces allPieces = new Pieces();
    private Hashtable<JButton, BoardTile> boardTiles = new Hashtable<>();
    private PiecePos fromMove;
     private boolean multiMove = false;
     private boolean hasTakenMove= false;
    public Testy() {
        initComponents();
        initComponents2();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Testy().setVisible(true);
            }
        });
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
                     if(row<2){
                        JButton x = buttons[row][col];
                         x.setIcon(new ImageIcon(redimg));
                         allPieces.add(new PiecePos(row, col, true, false, x));//kay- search by object then
                    }
                    if(row>5 && row<8){
                         JButton x = buttons[row][col];
                         x.setIcon(new ImageIcon(blackimg));
                           allPieces.add(new PiecePos(row, col, false, false, x));
                         
                    }
                     boardTiles.put(buttons[row][col], new BoardTile(row, col));
                    buttons[row][col].addActionListener(new java.awt.event.ActionListener() {
                        
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            try {
                                buttonSelected(evt);
                            } catch (IOException ex) {
                                Logger.getLogger(Testy.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              }
                     });

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
     
     private void buttonSelected(java.awt.event.ActionEvent evt) throws IOException{
         
         System.out.println("IN button");
         System.out.println("");
       
         JButton aButton = (JButton)evt.getSource();
         int row = boardTiles.get(aButton).getRow();
         int col = boardTiles.get(aButton).getCol();
         
         ///
         if(allPieces.contains(aButton)){
             System.out.println("Row is: " + allPieces.getPiece(aButton).getRow());
             System.out.println("Col is: " + allPieces.getPiece(aButton).getCol());
             System.out.println("Row other is: " + allPieces.getPiece(row, col).getRow());
             System.out.println("Col other is " + allPieces.getPiece(row, col).getCol());
             
          //   System.out.println("My Neightbour is " + allPieces.contains(row-1, col-1));
           //  System.out.println("My Nieghbour is Row :" + allPieces.getPiece(row-1, col-1).getRow());
         }
         
         ///
       
            if(allPieces.contains(aButton)){

                System.out.println("Has piece");
                if(player1 && allPieces.getPiece(aButton).isColour()){
                    fromMove = allPieces.getPiece(aButton);
                    moveFromP1 = true;
                    moveDisplay.append("Move from " + fromMove.getRow() + " - " + fromMove.getCol());
                }
                else if(!player1 && !allPieces.getPiece(aButton).isColour()){
                
                    fromMove = allPieces.getPiece(aButton);
                    moveDisplay.append("Move from " + fromMove.getRow() + " - " + fromMove.getCol());
                    moveFromP2 = true;
                }
            }
            else{

                if(moveFromP1){


                    System.out.println("has old move");
                    if(fromMove.isColour()){

                        moveChecker(aButton, true);
                        

                    }
                }
                else if(moveFromP2){
                
                    moveChecker(aButton, false);
                    hasTakenMove = true;
                }
                else{
                
                    moveDisplay.setText("Not valid move - redo");
                
                }

            }
           
         
        
         
     }
     private void moveChecker(JButton aButton, boolean isRed) throws IOException{
         
         int upDown;
         String icon;
        
       
        
         
        int fromRow = fromMove.getRow();
        int fromCol = fromMove.getCol();
        int toRow = boardTiles.get(aButton).getRow();
        int toCol = boardTiles.get(aButton).getCol();
        boolean isKing = fromMove.isKing();
        
          if(isRed && isKing){
         
            upDown = 1;
            icon = "redKing.jpg";
         }
          else if(!isRed && isKing){
              
              upDown = -1;
             icon = "blackKing.jpg";
          
          }
          else if(isRed){
          
            upDown = 1;
            icon = "redChecker.jpg";
          }  
         else{
         
             upDown = -1;
             icon = "blackChecker.jpg";
         }
         
            System.out.println("Past colour check");
            System.out.println("from row: " + fromRow + " from col " + fromCol);
            System.out.println("to row = " + toRow + " TO CoL " + toCol);

            System.out.println("contaisn button?" + allPieces.contains(aButton));
                  //  if(canMoveTo()){
                    
                  //  }
        if((!allPieces.contains(aButton)) && toRow == (fromRow+upDown) && (toCol == (fromCol-1) || toCol ==(fromCol+1)) &&!multiMove){
            System.out.println("Is moving");
            String cols = "";
            if(fromCol-1>=0){

                cols+= (fromCol-1);
            }
            else if(fromCol+1<=7){

                cols+= (fromCol+1);

            }
            System.out.println("Conditonals: " + (fromRow+upDown) + " - " + cols);

           buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));

             if(isRed){
            moveFromP1 = false;
            player1 = false;
             }
             else{
             moveFromP2 = false;
             player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            checkKing(toRow, isRed, aButton);
        }
        else if(isKing && (!allPieces.contains(aButton)) && toRow == (fromRow+(-upDown)) && (toCol == (fromCol-1) || toCol ==(fromCol+1)) &&!multiMove){
            
            System.out.println("Is moving");
            String cols = "";
            if(fromCol-1>=0){

                cols+= (fromCol-1);
            }
            else if(fromCol+1<=7){

                cols+= (fromCol+1);

            }
            System.out.println("Conditonals: " + (fromRow+(-upDown)) + " - " + cols);

           buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));

             if(isRed){
            moveFromP1 = false;
            player1 = false;
             }
             else{
             moveFromP2 = false;
             player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            checkKing(toRow, isRed, aButton);
     
         }
        else if(canTakeLeft(aButton, toRow,  fromRow,  toCol,  fromCol,  upDown) && isEnemy(fromRow, fromCol, upDown, isRed)){
            System.out.println("weeeeeeeeeeeeeeeeeeeee");
            
              //System.out.println("Conditonals: " + (fromRow+upDown) + " - " + cols);

           buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));
             
             ///
            
             ///
            
            if(isRed){
            moveFromP1 = false;
            multiMove = true;
            //player1 = false;
             }
             else{
             moveFromP2 = false;
             multiMove = true;
            // player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            checkKing(toRow, isRed, aButton);
        }
        else if(canTakeRight(aButton, toRow,  fromRow,  toCol,  fromCol,  upDown) && isEnemy(fromRow, fromCol, upDown, isRed)){
            
            buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));
             
             ///
            
             ///
            
            if(isRed){
            moveFromP1 = false;
            multiMove = true;
            //player1 = false;
             }
             else{
             moveFromP2 = false;
             multiMove = true;
            // player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            checkKing(toRow, isRed, aButton);
        
        }
        
        else if(isKing && kingCanTakeRight(aButton, toRow,  fromRow,  toCol,  fromCol,  upDown) && isEnemyofKing(fromRow, fromCol, upDown, isRed)){
            
            buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));
             
             ///
            
             ///
            
            if(isRed){
            moveFromP1 = false;
            multiMove = true;
            //player1 = false;
             }
             else{
             moveFromP2 = false;
             multiMove = true;
            // player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            checkKing(toRow, isRed, aButton);
        
        }
         else if(isKing && kingCanTakeLeft(aButton, toRow,  fromRow,  toCol,  fromCol,  upDown) && isEnemyofKing(fromRow, fromCol, upDown, isRed)){
            
            buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));
             
             ///
            
             ///
            
            if(isRed){
            moveFromP1 = false;
            multiMove = true;
            //player1 = false;
             }
             else{
             moveFromP2 = false;
             multiMove = true;
            // player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            
            checkKing(toRow, isRed, aButton);
        
        }
         else if(hasTakenMove){

            moveFromP1 = false;
            moveFromP2 = false;
            multiMove = false;
            player1 = !player1;

        }
     
        
     
         
     
     }
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
     private boolean canTakeLeft(JButton aButton, int toRow, int fromRow, int toCol, int fromCol, int upDown){
     
         boolean test = false;
         System.out.println("In canTakeLeft");
        /* if(isKing){
        
             test = !allPieces.contains(aButton) && (normalTakeMove(toRow, fromRow, toCol, fromCol, upDown) || kingTakeMove(toRow, fromRow, toCol, fromCol, upDown));
         }
         else{*/
            test = !allPieces.contains(aButton) && toRow == (fromRow+upDown*2) && (toCol == (fromCol-2));
         System.out.println(test);
         
         //}
         return test;
     }
      private boolean canTakeRight(JButton aButton, int toRow, int fromRow, int toCol, int fromCol, int upDown){
     
         boolean test = false;
         System.out.println("In canTakeRight");
        /* if(isKing){
        
             test = !allPieces.contains(aButton) && (normalTakeMove(toRow, fromRow, toCol, fromCol, upDown) || kingTakeMove(toRow, fromRow, toCol, fromCol, upDown));
         }
         else{*/
            test = !allPieces.contains(aButton) && toRow == (fromRow+upDown*2) && (toCol == (fromCol+2));
         System.out.println(test);
         
         //}
         return test;
     }
      private boolean kingCanTakeRight(JButton aButton, int toRow, int fromRow, int toCol, int fromCol, int upDown){
          
           boolean test = false;
         System.out.println("In KingcanTakeRight");
        /* if(isKing){
        
             test = !allPieces.contains(aButton) && (normalTakeMove(toRow, fromRow, toCol, fromCol, upDown) || kingTakeMove(toRow, fromRow, toCol, fromCol, upDown));
         }
         else{*/
            test = !allPieces.contains(aButton) && toRow == (fromRow+(-upDown)*2) && (toCol == (fromCol+2));
         System.out.println(test);
         
         //}
         return test;
      
      }
      private boolean kingCanTakeLeft(JButton aButton, int toRow, int fromRow, int toCol, int fromCol, int upDown){
     
         boolean test = false;
         System.out.println("In KingcanTakeLeft");
        /* if(isKing){
        
             test = !allPieces.contains(aButton) && (normalTakeMove(toRow, fromRow, toCol, fromCol, upDown) || kingTakeMove(toRow, fromRow, toCol, fromCol, upDown));
         }
         else{*/
            test = !allPieces.contains(aButton) && toRow == (fromRow+(-upDown)*2) && (toCol == (fromCol-2));
         System.out.println(test);
         
         //}
         return test;
      
      }
     private boolean isEnemy(int row, int col, int upDown, boolean isRed){
     
         boolean test = false;
         System.out.println("in isEneny");
         if(allPieces.contains(row+upDown, (col-1))){
             System.out.println("enemy to bot left");
            test = isRed != allPieces.getPiece(row+upDown, (col-1)).isColour() ;
            if(test){
            
                JButton killedPiece = allPieces.getPiece(row+upDown, (col-1)).getButton();
                killedPiece.setIcon(null);
                allPieces.remove(killedPiece);
  
            }
         }
         else if(allPieces.contains(row+upDown, (col+1))){
             System.out.println("enemy to bot right");
            test =  isRed != allPieces.getPiece(row+upDown, (col+1)).isColour();
             if(test){
            
                JButton killedPiece = allPieces.getPiece(row+upDown, (col+1)).getButton();
                killedPiece.setIcon(null);
                allPieces.remove(killedPiece);

            }
         }
         System.out.println("isEnemy is: " + test);
         return test;
     }
     
      private boolean isEnemyofKing(int row, int col, int upDown, boolean isRed){
     
         boolean test = false;
         System.out.println("in isEnenyOfKing");
         if(allPieces.contains(row+(-upDown), (col-1))){
             System.out.println("enemy to bot left");
            test = isRed != allPieces.getPiece(row+(-upDown), (col-1)).isColour() ;
            if(test){
            
                JButton killedPiece = allPieces.getPiece(row+(-upDown), (col-1)).getButton();
                killedPiece.setIcon(null);
                allPieces.remove(killedPiece);
  
            }
         }
         else if(allPieces.contains(row+(-upDown), (col+1))){
             System.out.println("enemy to bot right");
            test =  isRed != allPieces.getPiece(row+(-upDown), (col+1)).isColour();
             if(test){
            
                JButton killedPiece = allPieces.getPiece(row+(-upDown), (col+1)).getButton();
                killedPiece.setIcon(null);
                allPieces.remove(killedPiece);

            }
         }
         System.out.println("isEnemyofKing is: " + test);
         return test;
     }
      
       private void moveLogic(int fromRow, int fromCol, int toRow, int toCol, String icon, boolean isKing, JButton aButton, boolean isRed) throws IOException{
    
         buttons[fromRow][fromCol].setIcon(null);
           allPieces.remove(fromMove.getButton());
            System.out.println("IS DONE? " + allPieces.contains(fromRow, fromCol));

    ///remove old symbol


               Image redimg = ImageIO.read(getClass().getResource(icon));

             aButton.setIcon(new ImageIcon(redimg));
             allPieces.add(new PiecePos(toRow, toCol, isRed, isKing, aButton));
             
             ///
            
             ///
            
            if(isRed){
            moveFromP1 = false;
            multiMove = true;
            //player1 = false;
             }
             else{
             moveFromP2 = false;
             multiMove = true;
            // player1 = true;
             }
            moveDisplay.append("\nMove to " + toRow + " - " + toCol);
            checkKing(toRow, isRed, aButton);
        
    
    }
     
     
   
private JButton[][] buttons;
private JPanel jPanel1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPane;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea moveDisplay;
    // End of variables declaration//GEN-END:variables
}