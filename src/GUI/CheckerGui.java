/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guichecker;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class CheckerGui extends javax.swing.JFrame{
    
    private JButton[][] buttons;
    private JPanel jPanel;
    private JFrame jFrame;
    
    public CheckerGui(){
    
        initComponents();
        setLocationRelativeTo(this);
        //setSize(1000, 1000);
    }
    private void initComponents()
    {
        jFrame = new JFrame();
        jFrame.setSize(1000, 1000);
        buttons = new JButton[8][8];
        jPanel = new javax.swing.JPanel();
        jPanel.setName("Checkers");
        jPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel.setMinimumSize(new java.awt.Dimension(600, 600));
        jPanel.setSize(600,600);
        jPanel.setLayout(new java.awt.GridLayout(8,8));
        

        for(int row = 0;row<buttons.length;row++)
        {
            for(int col = 0;col<buttons[row].length;col++)
            {
                buttons[row][col]=new javax.swing.JButton();
                buttons[row][col].setBorderPainted(false);
                buttons[row][col].setMaximumSize(new java.awt.Dimension(80, 80));
                buttons[row][col].setMinimumSize(new java.awt.Dimension(80, 80));
                buttons[row][col].setSize(60, 60);
                buttons[row][col].setRolloverEnabled(false);
                buttons[row][col].setName(row+", "+col);
               // buttons[row][col].addActionListener(new ButtonListener(new Location(row,col),myGrid,this));
               // try {buttons[row][col].setIcon(myGrid.pieceAt(new Location(row,col)).getImage()); } catch(Exception ex) {}

                if(row%2==col%2)
                {
                    buttons[row][col].setForeground(Color.white);
                    buttons[row][col].setBackground(Color.white);
                }
                else
                {
                    buttons[row][col].setForeground(Color.black);
                    buttons[row][col].setBackground(Color.black);
                }
                jPanel.add(buttons[row][col]);
            }
            jFrame.add(jPanel);
        }
        getContentPane().add(jPanel);
       // myGrid.update();
        super.repaint();
    }// </editor-fold>
}
