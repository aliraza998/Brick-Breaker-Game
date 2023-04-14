package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//interface
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; // interface
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class inGame extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private static int score = 0;
    private int totalbricks = 48;
    private final Timer timer;
    private int Player001 = 310;
    private int BallXPosition = 120;
    private int BallYPosition = 350;
    private int BallXDirection = -3;
    private int BallYDirection = -2;
    private Map map;
    private int round=0;
    private static int max;
    ArrayList<Integer> a =new ArrayList<>();


    public inGame() {
        map = new Map(6, 12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        timer = new Timer(delay, this);
        timer.start();
    }

    public static int getMax() {
        return max;
    }

    public void paint(Graphics graphic) {
        // background
        graphic.setColor(Color.darkGray);
        graphic.fillRect(1, 1, 692, 592);

        // drawing map
        map.draw((Graphics2D) graphic);

        // the scores during game
        graphic.setColor(Color.WHITE);
        graphic.setFont(new Font("serif", Font.BOLD, 25));
        graphic.drawString("" + score, 350, 30);

        // the paddle
        graphic.setColor(Color.yellow);
        graphic.fillRect(Player001, 550, 100, 8);

        // the ball
        graphic.setColor(Color.red);
        graphic.fillOval(BallXPosition, BallYPosition, 20, 20);

        // after player wins the game
        if (totalbricks <= 0) {
            play = false;
            BallXDirection = 0;
            BallYDirection = 0;
            graphic.setColor(Color.RED);
            graphic.setFont(new Font("arial", Font.BOLD, 30));
            graphic.drawString("CONGRATS!!! You Won", 260, 300);
            graphic.setColor(Color.RED);
            graphic.setFont(new Font("arial", Font.BOLD, 20));
            graphic.drawString("Press (Enter) to Restart", 230, 350);
        }

        // when you lose the game
        if (BallYPosition > 570) {
            play = false;
            round++;//for checking the highest score
            BallXDirection = 0;
            BallYDirection = 0;
            for(int i=0;i<round;i++) {
                a.add(i,score);
            }

            max=Collections.max(a);
            graphic.setColor(Color.yellow);
            graphic.setFont(new Font("serif", Font.BOLD, 40));//for setting fonts
            graphic.drawString(" Game Over", 250, 290);
            graphic.setFont(new Font("serif", Font.BOLD, 20));
            graphic.drawString("             Score: " + score, 250, 320);
            graphic.drawString(" Press (Enter) to Restart", 250, 380);
            try {
                graphic.drawString("      Highest score: " +max , 250, 350);
            }
            catch (ClassCastException | NoSuchElementException e) {
                System.out.println("Exception caught : " + e);
            }
            graphic.setColor(Color.green);
            graphic.setFont(new Font("arial",Font.TRUETYPE_FONT,20));
            ((Graphics2D) graphic).drawString("       Made By",440,480);
            ((Graphics2D) graphic).drawString(" Ali Raza (21K-4703)",440,510);
            ((Graphics2D) graphic).drawString("Haris Jamal (21K-3061)",440,530);
        }
        graphic.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(BallXPosition, BallYPosition, 20, 20).intersects(new Rectangle(Player001, 550, 30, 8))) {
                BallYDirection = -BallYDirection;               //for changing direction of ball after hitting paddle
                BallXDirection = -2;
            } else if (new Rectangle(BallXPosition, BallYPosition, 20, 20).intersects(new Rectangle(Player001 + 70, 550, 30, 8))) {
                BallYDirection = -BallYDirection;               //for changing direction of ball after hitting paddle
                BallXDirection = BallXDirection + 1;
            } else if (new Rectangle(BallXPosition, BallYPosition, 20, 20).intersects(new Rectangle(Player001 + 30, 550, 40, 8))) {
                BallYDirection = -BallYDirection;               //for changing direction of ball after hitting paddle
            }
            // check map collision with the ball
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(BallXPosition, BallYPosition, 20, 20);
                        Rectangle brickRect = rect;
                        if (ballRect.intersects(brickRect)) {
                            map.setbrickValue(0, i, j);       //for diminishing bricks
                            score += 5;
                            totalbricks--;
                            // when ball hit right or left of brick
                            if (BallXPosition + 19 <= brickRect.x || BallXPosition + 1 >= brickRect.x + brickRect.width) {
                                BallXDirection = -BallXDirection;
                            }
                            // when ball hits top or bottom of brick
                            else {
                                BallYDirection = -BallYDirection;
                            }
                            break A;
                        }
                    }
                }
            }
            BallXPosition += BallXDirection;     //------
            BallYPosition += BallYDirection;     //     |
            if (BallXPosition < 0) {             //     |
                BallXDirection = -BallXDirection;//     |
            }                                    //     |-----> for changing direction of ball
            if (BallYPosition < 0) {             //     |
                BallYDirection = -BallYDirection;//     |
            }                                    //     |
            if (BallXPosition > 670) {           //     |
                BallXDirection = -BallXDirection;//------
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {              //for moving paddle right
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (Player001 >= 600) {
                Player001 = 600;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {      //for moving paddle right
            if (Player001 < 10) {
                Player001 = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {      //for rendering again in the game
            if (!play) {
                round++;
                for(int i=0;i<round;i++) {
                    a.add(i,score);
                }
                int max=Collections.max(a);
                play = true;
                BallXPosition = 120;
                BallYPosition = 350;
                BallXDirection = -3;
                BallYDirection = -2;
                Player001 = 310;
                score = 0;
                totalbricks = 72;
                map = new Map(6, 12);
                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        Player001 += 20;
    }

    public void moveLeft() {
        play = true;
        Player001 -= 20;
    }

    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
}