package no.uib.inf101.sample.whackAmole.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import no.uib.inf101.sample.whackAmole.controller.Clicker;
import no.uib.inf101.sample.whackAmole.model.Baby;
import no.uib.inf101.sample.whackAmole.model.Bug;
import no.uib.inf101.sample.whackAmole.model.Buttons;
import no.uib.inf101.sample.whackAmole.model.Difficulty;
import no.uib.inf101.sample.whackAmole.model.GameState;
import no.uib.inf101.sample.whackAmole.model.GameTimer;
import no.uib.inf101.sample.whackAmole.model.Score;

import java.awt.*;




public class GameView extends JPanel{
    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 850;
    private static final int GRID_SIZE = 5;
    private static final int MARGIN_TOP = 50;

    private static GameState gameState;
    private static Difficulty difficulty;
    static JFrame frame = new JFrame("Whac-A-Bug");

   
    private static JPanel mainPanel = new JPanel(new BorderLayout());
    private static JPanel textPanel = new JPanel(new FlowLayout());

    
    private static JLabel timeLabel;
    private static JLabel scoreLabel;
    
    private static JPanel gameBoard = new JPanel();
    private static JPanel labelPanel = new JPanel(new GridLayout(1,2));
    private static JButton[] game = new JButton[GRID_SIZE*GRID_SIZE];

    private static ImageIcon bugIcon;
    private static ImageIcon holeIcon;
    private static ImageIcon babyIcon;
    private static GameTimer timer;
    private static Score score;

    public GameView(){
        this.gameState = GameState.WELCOME_SCREEN;
        
        //creates the frame
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadIcons();//loads the icons
        drawGame();//draws the game

        frame.setVisible(true);
    }

    /**
     * draw the screen based on wich gamestate
     */
    public static void drawGame(){
        switch (gameState) {
            case ACTIVE_GAME:
              
                //re Draws
                mainPanel.removeAll();
                mainPanel.repaint();

                gameBoard.removeAll();
                gameBoard.repaint();

                labelPanel.removeAll();
                labelPanel.repaint();

                //adds the gameboard and textboard to the frame
                mainPanel.add(textPanel,BorderLayout.NORTH);
                mainPanel.add(gameBoard, BorderLayout.CENTER);

                // creates the game board
                gameBoard.setLayout(new GridLayout(GRID_SIZE,GRID_SIZE));
                gameBoard.setBorder(new EmptyBorder(MARGIN_TOP,0, 0, 0));

                //creates buttons
                Buttons buttons = new Buttons(GRID_SIZE,holeIcon);
                game = Buttons.createButtons(GRID_SIZE);
                
                Clicker moleClicker = new Clicker(bugIcon, babyIcon);
                for(JButton button: game){
                    button.addActionListener(moleClicker);
                    gameBoard.add(button);
                }

                //bug
                Bug bug = new Bug(bugIcon, babyIcon, holeIcon, game, difficulty);
                bug.startTimer();

                //Baby
                Baby baby = new Baby(babyIcon, bugIcon ,holeIcon,game, difficulty);
                baby.startTimer();

                //timer
                timer = new GameTimer(bug, baby);
                timeLabel = new JLabel("Time left: " + timer.getTimeLeft(),SwingConstants.CENTER);
                timeLabel.setFont(new Font("Arial", Font.BOLD, 20 ));
                textPanel.add(GameView.timeLabel);
                timer.startTimer();

                //ScoreLabel
                
                scoreLabel = new JLabel("Score: " + Score.getScore(), SwingConstants.LEFT);
                scoreLabel.setFont(new Font("Arial",Font.BOLD, 20));

               
                labelPanel.add(scoreLabel);
                labelPanel.add(timeLabel);


                textPanel.add(labelPanel);

                // adds the mainpanel
                frame.add(mainPanel);

                break;
            case WELCOME_SCREEN:
                //clears the panel
                mainPanel.removeAll();
                mainPanel.repaint();
                
                //creates mainpanel with layouts
                mainPanel.setLayout(new BorderLayout());
                mainPanel.setBackground(Color.BLACK); 
            
                //Create panels for gametext
                JPanel welcomePanel = new JPanel();
                welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
                welcomePanel.setBackground(Color.BLACK); // Set black background
                welcomePanel.setBorder(new EmptyBorder(20, 20, 20, 20));//borders
            
                // Titel
                JLabel titleLabel = new JLabel("Welcome to Whac-A-Bug!", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
                titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                titleLabel.setForeground(Color.WHITE); 
                welcomePanel.add(titleLabel);
            
                //Game instructions
                JTextArea instructionsTextArea = new JTextArea();
                instructionsTextArea.setEditable(false);
                instructionsTextArea.setLineWrap(true);
                instructionsTextArea.setWrapStyleWord(true);
                instructionsTextArea.setText("Instructions:\n"
                        + "1. Click on bugs to score points.\n"
                        + "2. Avoid clicking on babies.\n"
                        + "3. The game lasts for 45 seconds.\n"
                        + "4. Have fun!");
                instructionsTextArea.setAlignmentX(Component.CENTER_ALIGNMENT); 
                instructionsTextArea.setFont(new Font("Arial", Font.PLAIN, 20)); 
                instructionsTextArea.setForeground(Color.BLACK); 
                welcomePanel.add(instructionsTextArea);
            
                // Create a panel for buttons
                JPanel levelButtonPanel = new JPanel();
                levelButtonPanel.setLayout(new GridLayout(1, 3, 10, 0)); // 3 buttons in a row with some spacing
                levelButtonPanel.setBackground(Color.BLACK); // Set black background
            
                // Create buttons for game modes
                JButton easyButton = new JButton("Easy");
                easyButton.setFont(new Font("Arial", Font.BOLD, 20));
                easyButton.addActionListener(new Clicker(null, null)); // listener
                levelButtonPanel.add(easyButton);
            
                JButton mediumButton = new JButton("Medium");
                mediumButton.setFont(new Font("Arial", Font.BOLD, 20));
                mediumButton.addActionListener(new Clicker(null, null)); // listener
                levelButtonPanel.add(mediumButton);
            
                JButton hardButton = new JButton("Hard");
                hardButton.setFont(new Font("Arial", Font.BOLD, 20));
                hardButton.addActionListener(new Clicker(null, null)); // listener
                levelButtonPanel.add(hardButton);
            
                // Add the button panel to the welcome panel
                welcomePanel.add(Box.createVerticalStrut(20)); // Add some vertical space
                welcomePanel.add(levelButtonPanel);
            
                // adds the welcomePanel to mainpanel
                mainPanel.add(welcomePanel, BorderLayout.CENTER);
            
                // Legg til hovedpanelet i rammen og oppdater visningen
                frame.add(mainPanel);
                frame.revalidate();
                break;
            



            
            
            
            case GAME_OVER: 
                // Clear existing components
                mainPanel.removeAll();
                mainPanel.repaint();
        
                // Create a panel for game over screen
                JPanel gameOverPanel = new JPanel();
                gameOverPanel.setLayout(new BorderLayout());
                gameOverPanel.setBackground(Color.WHITE); // Set background color
                
                // Add "Game Over" text
                JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
                gameOverLabel.setFont(new Font("Arial", Font.BOLD, 48));
                gameOverLabel.setPreferredSize(new Dimension(BOARD_WIDTH, 100)); // Set preferred size
                gameOverPanel.add(gameOverLabel, BorderLayout.NORTH);
        
                // Add score label
                int score = Score.getScore();
                JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
                scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
                gameOverPanel.add(scoreLabel, BorderLayout.CENTER);
        
                // Add restart button
                JButton restartButton = new JButton("Home");
                restartButton.setFont(new Font("Arial", Font.BOLD, 20));

                //What happens when click the button
                Clicker restartClicker = new Clicker(null, null);
                restartButton.addActionListener(restartClicker);
                    
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(restartButton);
                gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);
        
                // Add game over panel to main panel
                mainPanel.add(gameOverPanel, BorderLayout.CENTER);
                frame.revalidate(); // Refresh the frame
                break;
            default:
                break;
        }
    }
    /**
     * updates the timeLabel everytime its called upon
     * @param timeLeft
     */
    public static void updateTimeLabel(int timeLeft){
        timeLabel.setText("Time left: " + timer.getTimeLeft());
    }

    /**
     * updates the scoreLabel everytime its called upon
     * @param score
     */
    public static void updateScoreLabel(int score){
        scoreLabel.setText("Score: " + Score.getScore());
    }

    /**
     * sets the gameState of the game
     * @param state
     */
    public static void setGameState(GameState state){
        gameState = state;
    }
    /**
     * sets the difficulty of the game
     * @param diff
     */
    public static void setDifficulty(Difficulty diff){
        difficulty = diff;
    }

    /**
     * Loades the images into icons
     */
    public  void loadIcons(){
        Image holeImg = new ImageIcon(getClass().getResource("../Images/hole.png")).getImage();
        holeIcon = new ImageIcon(holeImg.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));
            
        Image bugImg = new ImageIcon(getClass().getResource("../Images/bug.png")).getImage();
        bugIcon = new ImageIcon(bugImg.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH));

        Image babyImg = new ImageIcon(getClass().getResource("../Images/baby.png")).getImage();
        babyIcon = new ImageIcon(babyImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
    }
}

