package no.uib.inf101.sample.whackAmole.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import no.uib.inf101.sample.whackAmole.view.GameView;

public class GameTimer {
    private int timeLeft;
    private Timer timer;

    public GameTimer(Bug bug, Baby baby){
    
        timeLeft = 45;
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if(timeLeft > 0){
                    GameView.updateTimeLabel(timeLeft);//updates the timer
                    timeLeft--;//decrement the timer by 1;

                }else{
                    GameView.updateTimeLabel(timeLeft);// updates the timer
                    baby.stopTimer();// stops the babyTimer
                    bug.stopTimer();// stops the bugTimer
                    GameView.setGameState(GameState.GAME_OVER); // sets the gamestate  to GAME_OVER
                    GameView.drawGame();
                    timer.stop();// stops the timer
                }
            }
        });
    }
  
    /**
     * starts the timer
     */
    public void startTimer(){
        timer.start();
    }

    /**
     * stops the timer
     */
    public void stopTimer(){
        timer.stop();
    }

    /**
     * 
     * @return the remainding time
     */
    public int getTimeLeft(){
        return timeLeft;
    }

    /**
     * this method restart the timer
     */
    public void restartTimer(){
        timeLeft = 45;
    }
}
