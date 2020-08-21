/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game.application;
import javax.swing.*;


public class Snake extends  JFrame{
    public Snake(){
    Board b;
        add(new Board());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
    }
    
    public static void main(String[] args) {
        new Snake().setVisible(true);
    }
}
