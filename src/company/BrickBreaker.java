package com.company;
import javax.swing.*;

public class BrickBreaker {
    public static void displayGame() {
        JFrame j = new JFrame();
        inGame starts = new inGame();
        j.setBounds(10, 10, 700, 600);
        j.setTitle("Brick Breaker");
        j.setResizable(false);
        j.setVisible(true);
        j.add(starts);
        j.setVisible(true);
    }
}