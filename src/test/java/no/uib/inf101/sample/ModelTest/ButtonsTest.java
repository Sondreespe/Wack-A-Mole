package no.uib.inf101.sample.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sample.whackAmole.model.Buttons;

public class ButtonsTest {

    //tests if the method creates 25 buttons
    @Test
    public void testCreateButtons5x5(){
        int gridSize = 5;
        JButton[] buttons = Buttons.createButtons(gridSize);

        assertNotNull(buttons);
        assertEquals(gridSize*gridSize, buttons.length);
    }

     //tests if the method creates 16 buttons
     @Test
     public void testCreateButtons4x4(){
         int gridSize = 4;
         JButton[] buttons = Buttons.createButtons(gridSize);
 
         assertNotNull(buttons);
         assertEquals(gridSize*gridSize, buttons.length);
     }
}
