package com.company;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {

    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public Map(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 1;
            }
        }
        brickWidth = 540 / col;  //---
                                 //  |-->for setting dimension of bricks
        brickHeight = 150 / row; //---
    }

    public void draw(Graphics2D gr) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > 0) {
                    gr.setColor(Color.GREEN);   //bricks colour
                    gr.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    // to create margins between each brick for better visibility
                    gr.setStroke(new BasicStroke(3));
                    gr.setColor(Color.darkGray);
                    gr.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setbrickValue(int value, int row, int col) {
        map[row][col] = value;
    }  //for setting number of bricks
}