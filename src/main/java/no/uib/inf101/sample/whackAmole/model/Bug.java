package no.uib.inf101.sample.whackAmole.model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


import javax.swing.Timer;
import javax.swing.*;

public class Bug implements IconsInterface{
    private ImageIcon bugIcon;
    private ImageIcon babyIcon;
    private ImageIcon holeIcon;
    private JButton[] gameButtons;
    public Timer bugTimer;
    private Difficulty difficulty;
    private int speed = 0;
    private Random random = new Random();
    private Set<Integer> occupiedIndexes;


    public Bug(ImageIcon bugIcon, ImageIcon babyIcon,  ImageIcon holeIcon, JButton[] gameButtons, Difficulty difficulty){
        this.bugIcon = bugIcon;
        this.holeIcon = holeIcon;
        this.gameButtons = gameButtons;
        this.babyIcon = babyIcon;
        this.difficulty = difficulty;
        this.occupiedIndexes = new HashSet<>();
    
       //chooses the spedd based on the difficulty
        if(difficulty == Difficulty.EASY){
            speed = 2000;
        }else if (difficulty== Difficulty.MEDIUM){
            speed = 1000;
        }else{
            speed = 750;
        }
      
        //a timer for the bug to move
        bugTimer = new Timer(speed, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               hideIcon();
               showIcon();
            }
        });
    }

    @Override
    public void startTimer(){
        bugTimer.start();
    }

    @Override
    public void stopTimer(){
        bugTimer.stop();
    }

    @Override
    public void showIcon(){
        int index  = randomIndex();
        while(occupiedIndexes.contains(index) || isOccupied(index)){
            index = randomIndex();
       }
       occupiedIndexes.add(index);
       gameButtons[index].setIcon(bugIcon);
    }

    @Override
    public void hideIcon(){
       for(Integer index: occupiedIndexes){
        gameButtons[index].setIcon(holeIcon);
       }
       occupiedIndexes.clear();
    }

    @Override
    public boolean isOccupied(int index){
        return gameButtons[index].getIcon().equals(babyIcon);
    }
    
    @Override
    public int randomIndex(){
        int num = random.nextInt(gameButtons.length);
        return num;
    }
    
}
