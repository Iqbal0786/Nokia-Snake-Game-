/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game.application;





import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;



public class Board extends JPanel implements ActionListener{
    private Image apple;
    private Image dot;
    private Image head;
    private  int dots;
    private final int DOT_SIZE=10;
    private final int  ALL_DOTS=900;
    private final int x[]= new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private int RANDOM_POSTION=29;
    private int apple_x;
    private int apple_y;
    private Timer timer;
    private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downDirection=false;
    private boolean inGame= true;
    public Board() {
        addKeyListener( new TAdapter());
        
        setBackground(Color.BLACK);
        setPreferredSize( new Dimension(300,300));
        setFocusable(true);
        initGame();
        loadImages();
        
    }
    public void loadImages(){
    ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("snake/game/application/icons/apple.png"));
    apple= i1.getImage();
    ImageIcon i2= new ImageIcon(ClassLoader.getSystemResource("snake/game/application/icons/dot.png"));
    dot =i2.getImage();
    ImageIcon i3= new ImageIcon(ClassLoader.getSystemResource("snake/game/application/icons/head.png"));
    head = i3.getImage();
    
    }
    public void  initGame(){
        dots=3;
        // code to set the postion of the all 3 dots in frame
        for(int z=0; z<dots; z++){
         x[z]=50-z*DOT_SIZE; //x axis localion of dots
         y[z]=50;               // y axis dots remain same at starting 
        }
      locateApple();
      timer= new Timer(140, this);
      timer.start();
    }
    public void locateApple(){              //this method defines the randome location of apple 
    
        //int r=(int) Math.random()*RANDOM_POSTION;
        //apple_x=(r*DOT_SIZE);
         // r=(int) Math.random()*RANDOM_POSTION;
       // apple_y=(r*DOT_SIZE);
       Random randome= new Random();
       apple_x= randome.nextInt(30) * DOT_SIZE;
       apple_y=randome.nextInt(30)*DOT_SIZE;

      
     
     System.out.print(apple_x );
    }
    public void checkApple(){             // this method discribes the eating action of snake
        
       if((x[0]==apple_x) && (y[0]==apple_y)){    //this condition defines that the head of snake and apple direction is in the same direction(means snake has eat the apple)
    
            dots++;                   //increments the size of snake 
            locateApple();            //reinitilize the position of the apple
    
             }
    }
    public void checkCollision(){
      for(int z= dots; z>0; z--){
         if( (z>4) && (x[0]==x[z]&&(y[0]==y[z])) ){
          inGame=false;
         }
      
        }
        if (x[0]>=300) {
            inGame=false;
        }
        if (y[0]>=300) {
            inGame=false;
        }
        if (x[0]<0) {
            inGame=false;
        }
        if (y[0] < 0) {
            inGame=false;
        }
        if (!inGame) {
            timer.stop();
        }
    
       }
    public void paintComponent(Graphics g){
     
        super.paintComponent(g);
        draw(g);
    
    }
    public void draw(Graphics g){
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for(int z=0; z<dots; z++){
                if (z==0) {
                    g.drawImage(head, x[z], y[z], this);
                }
                else{
                g.drawImage(dot, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else{
        gameOver(g);
        }
        
    }
    public void gameOver(Graphics g){
        String msg="Game Over !!";
        Font font= new Font("SEN_SEIF",Font.BOLD,14);
        FontMetrics metrics= getFontMetrics(font);
         g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg, 150-(metrics.stringWidth(msg)/2), 300/2);
        
        
       }
   
    public void move(){
       
        for(int z=dots; z>0;z--){
          x[z]=x[z-1];
          y[z]=y[z-1];
        }
        if (leftDirection) {
            x[0]-=DOT_SIZE;
        }
        if (rightDirection) {
            x[0]+=DOT_SIZE;
        }
         if (upDirection) {
            y[0]-=DOT_SIZE;
        }
        if (downDirection) {
            y[0]+=DOT_SIZE;
        }
     
       }
    public void  actionPerformed(ActionEvent ae){
        
        if(inGame){
        checkApple();
        checkCollision();
        move();
       
        }
        repaint();
    
    }
    private class TAdapter extends KeyAdapter {
    
        public void  keyPressed(KeyEvent e){
        
            int key=e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && (!rightDirection)){
                leftDirection=true;
                upDirection=false;
                downDirection=false;
                
            
            }
             if(key == KeyEvent.VK_RIGHT && (!leftDirection)){
                rightDirection=true;
                 upDirection=false;
                downDirection=false;

                           
            }
              if(key == KeyEvent.VK_UP && (!downDirection)){
                upDirection=true;
                rightDirection=false;
                leftDirection=false;
                
            
            }
               if(key == KeyEvent.VK_DOWN && (!upDirection)){
                leftDirection=false;
                rightDirection=false;
                 downDirection=true;
               
               
            
            }
        }
    
    } 
}
