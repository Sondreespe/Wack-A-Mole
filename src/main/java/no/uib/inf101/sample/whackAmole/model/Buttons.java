package no.uib.inf101.sample.whackAmole.model;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;



public class Buttons {
    private static ImageIcon holeIcon;
    private static ActionListener listener;

    public Buttons(int GRID_SIZE, ImageIcon holeIcon){
        this.holeIcon = holeIcon;
    }

    /**
     * creates the buttons of the game
     * @param GRID_SIZE
     * @return a grid with buttons
     */
    public static JButton[] createButtons(int gridSize){
        JButton[] game =  new JButton[gridSize*gridSize];

        for(int i = 0;i < gridSize * gridSize; i++){
            JButton tile = new JButton(holeIcon);
            tile.setPreferredSize(new Dimension(30,30));
            tile.setFocusable(false);
            game[i] = tile;   
        }
        return game;
    }
}
