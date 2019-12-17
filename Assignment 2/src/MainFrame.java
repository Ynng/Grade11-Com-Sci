import javax.swing.*;
import java.awt.*;

/**MainFrame Class
 * the main JFrame that manages and contains all the other parts of the program, in the form of JPanels
 */
public class MainFrame extends JFrame {
    public static GamePanel gamePanel;
    public static InfoPanel infoPanel;
    public static IOPanel ioPanel;
    public static MenuPanel menuPanel;
    public static HelpPanel helpPanel;
    public static HighscorePanel highscorePanel;
    public static StatsPanel statsPanel;

    private int gameStatusFlag = 0;// 0 = in menu, 1 = in game, 2 = in help, 3 = in highscore
    public static int gameSize = 4, standardWidth = 800, standardHeight = 1000;

    /**MainFrame Constructor
     * constructs a new MainFrame object, initializing it to be have the main menu open
     * @param none
     */
    public MainFrame() {
        super("Alien");
        setLayout(new BorderLayout());
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        setPreferredSize(new Dimension(standardWidth, standardHeight));
        menuPanel = new MenuPanel();
        add(menuPanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }

    /**startNewGame Method
     * reconfigures the JFrame to show the layout used for the main gameplay, also triggers gamePanel to start the game logic.
     * @param none
     * @return void
     */
    public void startNewGame() {
        if (gameStatusFlag == 0)
            remove(menuPanel);
        if (gameStatusFlag == 2)
            remove(helpPanel);
        if (gameStatusFlag == 3)
            remove(highscorePanel);
        if (gameStatusFlag == 4) {
            remove(statsPanel);
            remove(gamePanel);
            remove(highscorePanel);
        }
        if (infoPanel == null) {
            infoPanel = new InfoPanel();
        }
        if (ioPanel == null) {
            ioPanel = new IOPanel();
        }
        ioPanel.wipeEntry();
        if (gamePanel == null) {
            gamePanel = new GamePanel(gameSize);
        } else {
            gamePanel.startGame(gameSize);
        }
        add(gamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.PAGE_START);
        add(ioPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
        gameStatusFlag = 1;
    }
    
    /**openHelpPage method
     * reconfigues the JFrame to show only the helpPanel, initializing it if it's not already initialized
     * @param none
     * @return void
     */
    public void openHelpPage() {
        if (gameStatusFlag == 0)
            remove(menuPanel);
        if (gameStatusFlag == 3)
            remove(highscorePanel);
        if (gameStatusFlag == 4) {
            remove(statsPanel);
            remove(gamePanel);
            remove(highscorePanel);
        }
        if (gameStatusFlag == 1) {
            gamePanel.abandonGame();
            remove(gamePanel);
            remove(infoPanel);
            remove(ioPanel);
        }
        if (helpPanel == null) {
            helpPanel = new HelpPanel();
        }
        add(helpPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        gameStatusFlag = 2;
    }

    /**openMainMenu method
     * reconfigures the JFrame to show only menuPanel, which is initialized in the constructor, so it's guranteed to be already initialized
     * @param none
     * @return void
     */
    public void openMainMenu() {
        if (gameStatusFlag == 2)
            remove(helpPanel);
        if (gameStatusFlag == 3)
            remove(highscorePanel);
        if (gameStatusFlag == 4) {
            remove(statsPanel);
            remove(gamePanel);
            remove(highscorePanel);
        }
        if (gameStatusFlag == 1) {
            gamePanel.abandonGame();
            remove(gamePanel);
            remove(infoPanel);
            remove(ioPanel);
        }
        add(menuPanel, BorderLayout.CENTER);
        setSize(standardWidth, standardHeight);
        revalidate();
        repaint();
        gameStatusFlag = 0;
    }

    /**openHighscore method
     * should only b called if a game is not running, reconfigures the JFrame to display only highscorePanel
     * @param none
     * @return void
     */
    public void openHighscore() {
        if (gameStatusFlag == 0)
            remove(menuPanel);
        if (gameStatusFlag == 2)
            remove(helpPanel);
        if (gameStatusFlag == 4) {
            remove(statsPanel);
            remove(gamePanel);
            remove(highscorePanel);
        }
        if (gameStatusFlag == 1) {
            gamePanel.abandonGame();
            remove(gamePanel);
            remove(infoPanel);
            remove(ioPanel);
        }
        if (highscorePanel == null) {
            highscorePanel = new HighscorePanel();
        }
        add(highscorePanel, BorderLayout.CENTER);
        highscorePanel.setupHighscoreFile();
        revalidate();
        repaint();
        gameStatusFlag = 3;
    }

    /**showEndGame method
     * should only be called when a game is running and gameStatusFlag == 1, reconfigures the JFrame to show gamePanel, highScorePanel and statsPanel.
     * Also updates statsPanel with correct stats and submits the current score to highScorePanel for saving and displaying
     * @param finalScore the score the user got in the game
     * @param avgTimeUsed the average time the user used to enter a valid response to each alien
     */
    public void showEndGame(double finalScore, double avgTimeUsed) {
        if (gameStatusFlag != 1)
            return;
        remove(infoPanel);
        remove(ioPanel);
        if (highscorePanel == null) {
            highscorePanel = new HighscorePanel();
        }
        if(statsPanel == null){
            statsPanel = new StatsPanel(finalScore,avgTimeUsed);
        }else{
            statsPanel.updateStats(finalScore, avgTimeUsed);
        }
        add(statsPanel, BorderLayout.NORTH);
        add(highscorePanel, BorderLayout.EAST);
        setSize(gamePanel.getWidth() * 2, gamePanel.getHeight());
        highscorePanel.setupHighscoreFile();
        highscorePanel.addScore(finalScore);
        revalidate();
        repaint();
        gameStatusFlag = 4;
    }
}
