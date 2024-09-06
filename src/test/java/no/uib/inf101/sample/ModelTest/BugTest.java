package no.uib.inf101.sample.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.util.Timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import no.uib.inf101.sample.whackAmole.model.Bug;
import no.uib.inf101.sample.whackAmole.model.Difficulty;
import no.uib.inf101.sample.whackAmole.model.GameTimer;
import no.uib.inf101.sample.whackAmole.view.GameView;

public class BugTest {

    private Bug testBug;

    private ImageIcon bugIcon;
    private ImageIcon babyIcon;
    private ImageIcon holeIcon;
    private JButton[] gameButtons;
    private Difficulty difficulty;

    private Timer bugTimer;

    private GameView gameView;

   
    //the setup for the tests
    @BeforeEach
    public void setUp() {
        // Set up icons
        gameView = new GameView();
        

        // Set up game buttons
        gameButtons = new JButton[25]; // Assuming a 5x5 grid
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i] = new JButton();
        }

        // Create Bug object
        testBug = new Bug(bugIcon, babyIcon, holeIcon, gameButtons, difficulty);
    }

    //Test if the timer is running
    @Test
    public void testStartAndStopTimer() {
        
        // Initially, the timer should not be running
        assertFalse(testBug.bugTimer.isRunning());

        // Start the timer
        testBug.startTimer();

        // Check if the timer is running
        assertTrue(testBug.bugTimer.isRunning());

        // Stop the timer
        testBug.stopTimer();

        // Check if the timer is stopped
        assertFalse(testBug.bugTimer.isRunning());
    }

    //Tests if the icons shows and doeasnt show
    @Test
    public void testShowIcon() {
        // Ensure the icon is not shown initially
        for (JButton button : gameButtons) {
            assertNull(button.getIcon());
        }

        // Start the timer to show the bug icon
        testBug.startTimer();

        // Check if the bug icon is shown on one of the buttons
        boolean bugIconShown = false;
        for (JButton button : gameButtons) {
            if (button.getIcon() == bugIcon) {
                bugIconShown = true;
                break;
            }
        }
        assertTrue(bugIconShown);
        testBug.stopTimer();
    }
}
    
