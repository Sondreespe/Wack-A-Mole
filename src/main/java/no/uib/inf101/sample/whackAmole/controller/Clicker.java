package no.uib.inf101.sample.whackAmole.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import no.uib.inf101.sample.whackAmole.model.Difficulty;
import no.uib.inf101.sample.whackAmole.model.GameState;
import no.uib.inf101.sample.whackAmole.model.Score;
import no.uib.inf101.sample.whackAmole.view.GameView;

public class Clicker implements ActionListener{
    private Icon baby;
    private Icon bug;

    public Clicker(Icon bug, Icon baby){
        this.bug = bug;
        this.baby = baby;
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
       JButton clickedButton = (JButton) e.getSource();
       Icon icon = clickedButton.getIcon();

       if (clickedButton.getText().equals("Home")) {
           // Handle restart action
            GameView.setGameState(GameState.WELCOME_SCREEN);
            Score.resetScore();
            GameView.drawGame();
        } 
        //When pushed, the game starts with the corresponding difficulty. Easy, Medium, Hard
        else if(clickedButton.getText().equals("Easy")){
            GameView.setGameState(GameState.ACTIVE_GAME);
            GameView.setDifficulty(Difficulty.EASY);
            GameView.drawGame();
        } else if(clickedButton.getText().equals("Medium")){
            GameView.setGameState(GameState.ACTIVE_GAME);
            GameView.setDifficulty(Difficulty.MEDIUM);
            GameView.drawGame();
        } else if(clickedButton.getText().equals("Hard")){
            GameView.setGameState(GameState.ACTIVE_GAME);
            GameView.setDifficulty(Difficulty.HARD);
            GameView.drawGame();
            
        }
        else{
            //Handles what happens if the icons gets clicked
           if (icon.equals(bug)) {
               Score.increaseScore(20);
               GameView.updateScoreLabel(Score.getScore());
           }
           if (icon.equals(baby)) {
               Score.decreaseScore(10);
               GameView.updateScoreLabel(Score.getScore());
           }
       }
    } 
}
