package no.uib.inf101.sample.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.uib.inf101.sample.whackAmole.model.Score;

public class ScoreTest {
    private Score score;

    //sets the score = 0 before each test
    @BeforeEach
    public void setUp(){
        score = new Score();
        score.resetScore();
    }

    //The initial score should be 0
    @Test
    public void initialTest(){
        assertEquals(0, score.getScore());
    }

    // The initial score + the increment should be equal the score after calling score.increaseScore()
    @Test
    public void increaseScoreTest(){
        int initialScore = score.getScore();
        int increment = 10;

        score.increaseScore(increment);

        assertEquals(initialScore + increment, score.getScore());
    }

    //tests the resetScore method
    @Test
    public void resetScoreTest(){
        score.increaseScore(20);
        score.resetScore();
        assertEquals(0, score.getScore());
    }

    //tests the decreaseScore method
    @Test
    public void decreaseScoreTest(){
        int initialScore = score.getScore();
        int decrement = 50;

        score.decreaseScore(decrement);
        
        assertEquals(initialScore-decrement, score.getScore());
    }
}
