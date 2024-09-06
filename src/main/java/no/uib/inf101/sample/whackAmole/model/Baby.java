package no.uib.inf101.sample.whackAmole.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Baby implements IconsInterface {
    private ImageIcon babyIcon;
    private ImageIcon holeIcon;
    private ImageIcon bugIcon;
    private JButton[] gameButtons;
    public Timer babyTimer;
    private Difficulty difficulty;
    private Random random = new Random();
    private int speed = 0;
    private Set<Integer>  occupiedIndexes;

    public Baby(ImageIcon babyIcon, ImageIcon bugIcon, ImageIcon holeIcon,JButton[] gameButtons, Difficulty difficulty){
        this.babyIcon = babyIcon;
        this.holeIcon = holeIcon;
        this.bugIcon = bugIcon;
        this.gameButtons = gameButtons;
        this.difficulty = difficulty;
        this.occupiedIndexes = new HashSet<>();

        //chooses speed based on the difficulty
        if(difficulty == Difficulty.EASY){
            speed = 2000;
        }else if (difficulty== Difficulty.MEDIUM){
            speed = 1000;
        }else{
            speed = 750;
        }

        // a timer for the baby to move
        babyTimer = new Timer(speed,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideIcon();
                showIcon();
            }  
        });
    }

    @Override
    public void startTimer() {
        babyTimer.start();
    }

    @Override
    public void stopTimer() {
        babyTimer.stop();
    }

    @Override
    public void showIcon() {
        int index = randomIndex();
        while(occupiedIndexes.contains(index) || isOccupied(index)){
            index = randomIndex();
        }
        occupiedIndexes.add(index);
        gameButtons[index].setIcon(babyIcon);
    }

    @Override
    public void hideIcon() {
        for(Integer index : occupiedIndexes){
            gameButtons[index].setIcon(holeIcon);
        }
        occupiedIndexes.clear();
    }

    @Override
    public int randomIndex() {
        return random.nextInt(gameButtons.length);
    }

    @Override
    public boolean isOccupied(int index){
        return gameButtons[index].getIcon().equals(bugIcon);
    }
    
}

    

    
    

