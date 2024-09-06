package no.uib.inf101.sample.ClickerTest;

import no.uib.inf101.sample.whackAmole.controller.Clicker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ClickerTest {

    private static Clicker clicker;
    private static int bugClickCount;
    private static int babyClickCount;

    @BeforeAll
    public static void setUp() {
        // Initialiser Clicker med en ActionListener
        clicker = new Clicker(new ImageIcon(), new ImageIcon()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Counts amount of clicks on baby and bug butto 
                if (e.getSource() instanceof JButton) {
                    JButton button = (JButton) e.getSource();
                    if (button.getName().equals("bug")) {
                        bugClickCount++;
                    } else if (button.getName().equals("baby")) {
                        babyClickCount++;
                    }
                }
            }
        };
    }

    @Test
    public void testActionPerformed() {
        // Create buttons for test
        JButton bugButton = new JButton();
        bugButton.setName("bug");
        JButton babyButton = new JButton();
        babyButton.setName("baby");

        // Simulates clicks on the buttons
        clicker.actionPerformed(new ActionEvent(bugButton, ActionEvent.ACTION_PERFORMED, null));
        clicker.actionPerformed(new ActionEvent(babyButton, ActionEvent.ACTION_PERFORMED, null));
        clicker.actionPerformed(new ActionEvent(bugButton, ActionEvent.ACTION_PERFORMED, null));

        // Check if the clicks count
        assertEquals(2, bugClickCount);
        assertEquals(1, babyClickCount);
    }
}