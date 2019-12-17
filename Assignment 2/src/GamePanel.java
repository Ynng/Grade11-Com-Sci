import javax.swing.*;
import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GamePanel class extends JPanel, handles the "game" part of this game,
 * displays the game UI and handles most of the game logic.
 */
public class GamePanel extends JPanel {
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 20);
    private Font scoreFont = new Font("Sans-serif", Font.BOLD, 30);
    private Font messageFont = new Font("Sans-serif", Font.BOLD, 40);

    private Image alienImage, xImage;

    // 2d arrays for storing the light states
    private double[][] score;
    private int[][] aliens, aliens_A, score_A; // aliens_A for alpha animation
    private int[] arrowX = new int[3], arrowY = new int[3];
    private String message;
    private int graphSize, x, y, iconSize, borderWidth, alienCounter, message_A, gameEndingCount, curAlienX, curAlienY,
            successCounter, attempCounter;
    private long startTime, curT;// in nanoseconds
    private boolean animationFlag, animationFlagTemp, renderHit, gameRunning;
    private Timer timer;
    private double timeUsed, deltaT, lastT, totalScore, avgTimeUsed;// in millisecond
    private double errAT = 500, correctAT = 750, scoreAT = 750, messageAT = 1000;// in milliseconds

    /**
     * randomRange method Returns a random integer between min and max, include min,
     * exclude max Useful for randomly choosing an entry in an array
     * 
     * @param min the minimum value to be chosen, inclusive
     * @param max the maximum value to be chosen, exclusive
     * @return a random number between min and max
     */
    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    /**
     * GamePanel constructor method Creats a new GamePanel and starts a new game
     * 
     * @param i_graphSize determins the size of the graph to be played on, The game
     *                    will be played on a graph ranging from -i_graphSize to
     *                    +i_graphSize, making the final graphSize i_graphSize*2+1
     */
    public GamePanel(int i_graphSize) {
        setPreferredSize(new Dimension((i_graphSize * 2 + 1) * 50, (i_graphSize * 2 + 1) * 50));
        setBackground(Color.WHITE);
        borderWidth = 20;
        try {
            alienImage = ImageIO.read(new File(".\\Assignment 2\\Yuki_Nagato.png"));
        } catch (IOException ex) {
            System.out.println("Warning! Where's the alien image??!?!");
        }
        try {
            xImage = ImageIO.read(new File(".\\Assignment 2\\x.png"));
        } catch (IOException ex) {
            System.out.println("Warning! Where's the x image??!?!");
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (gameRunning) {
                    updateTime();
                    timeUsed = (curT - startTime) / 1000000.0;

                    MainFrame.infoPanel.updateTimer(timeUsed / 1000.0);
                    if (timeUsed / 1000.0 > 10)
                        alienEscape();
                    if (animationFlag) {
                        repaint();
                    }
                    System.out.println("Loop still running");
                }
            }
        }, 50, 50);// runs approximately every 50 millisecond, making the game 20 ticks per second
        startGame(i_graphSize);
    }

    /**
     * abandonGame method pauses the game logic loop to save resources
     * 
     * @param none
     * @return void
     */
    public void abandonGame() {
        gameRunning = false;
    }

    /**
     * endGame method ends the current game, submits information to MainFrame for
     * other classes to ues and pauses the main game logic loop
     * 
     * @param none
     * @return void
     */
    public void endGame() {
        gameRunning = false;
        renderHit = true;
        SpaceAlien.frame.showEndGame(totalScore, avgTimeUsed / 1000.0);
        repaint();
    }

    /**
     * startGame method Initializes the game, can be called after a game has already
     * started to start a new game
     * 
     * @param i_graphSize determins the size of the graph to be played on, The game
     *                    will be played on a graph ranging from -i_graphSize to
     *                    +i_graphSize, making the final graphSize i_graphSize*2+1
     * @return void
     */
    public void startGame(int i_graphSize) {
        gameRunning = true;
        graphSize = i_graphSize * 2 + 1;
        gameEndingCount = graphSize * graphSize / 16;
        avgTimeUsed = totalScore = alienCounter = successCounter = attempCounter = 0;
        renderHit = false;

        // initialize the arrays
        aliens = new int[graphSize][graphSize];
        aliens_A = new int[graphSize][graphSize];
        score_A = new int[graphSize][graphSize];
        score = new double[graphSize][graphSize];

        MainFrame.infoPanel.updateScore(totalScore);
        startTime = System.nanoTime();
        lastT = System.nanoTime();
        generateAlien();
    }

    /**
     * hitAlien method Attemps to hit an alien, should be called as a result of user
     * input, will result in animation, addition/subtraction of the user's score,
     * update of the different attemp counters and update to the average time to
     * respond
     * 
     * @param x the x coordinate of the alien attemped to hit, with the left top
     *          corner of the screen being 0, conversion between stored coordinates
     *          and coordinate presented to the player should be done by the caller
     * @param y the y coordinate of the alien attemped to hit, with the left top
     *          corner of the screen being 0, conversion between stored coordinates
     *          and coordinate presented to the player should be done by the caller
     * @return true = Aline is hit, false = didn't hit anything/error
     */
    public boolean hitAlien(int x, int y) {
        boolean output, invalid = false;
        try {
            if (aliens[x][y] == 1) {
                aliens[x][y] = 2;
                aliens_A[x][y] = score_A[x][y] = 255;
                animationFlag = output = true;
                timeUsed = (curT - startTime) / 1000000.0;
                score[x][y] = 1 + getTimeScore(timeUsed);
                successCounter += 1;
                totalScore += score[x][y];
                startTime = System.nanoTime();
            } else {
                aliens[curAlienX][curAlienY] = 3;
                aliens_A[x][y] = score_A[x][y] = -255;
                score[x][y] = -1;
                totalScore += score[x][y];
                timeUsed = (curT - startTime) / 1000000.0;
                animationFlag = true;
                output = false;
                startTime = System.nanoTime();
            }
            avgTimeUsed = (avgTimeUsed * (alienCounter - 1) + timeUsed) / alienCounter;
        } catch (ArrayIndexOutOfBoundsException e) {
            output = false;
            invalid = true;
        }
        MainFrame.infoPanel.updateScore(totalScore);
        if (!invalid) {
            attempCounter++;
            if (alienCounter < gameEndingCount)
                generateAlien();
            else
                endGame();
        }
        repaint();
        return output;
    }

    /**
     * alienEscape method, called to let the current focused alien "escape", similar
     * to if the hitAlien is called and returns with false, but instead of
     * triggering a small score animation, also triggers a large message animaton
     * 
     * @param none
     * @return void
     */
    private void alienEscape() {
        aliens[curAlienX][curAlienY] = 3;
        aliens_A[curAlienX][curAlienY] = score_A[curAlienX][curAlienY] = -255;
        score[curAlienX][curAlienY] = -1;
        totalScore += score[curAlienX][curAlienY];
        timeUsed = (curT - startTime) / 1000000.0;
        animationFlag = true;
        startTime = System.nanoTime();
        triggerMessage("Alien Escaped!");
        attempCounter++;
        if (alienCounter < gameEndingCount)
            generateAlien();
        else
            endGame();
    }

    /**
     * generateAlien method Generates a new alien at a new location, draws it to the
     * screen and sets its coordinate as the coordinate of the currently focused on
     * alien. Should not be called when there is already a focused unhit alien in
     * the game
     * 
     * @param none
     * @return void
     */
    private void generateAlien() {
        do {
            x = randomRange(0, graphSize);
            y = randomRange(0, graphSize);
        } while (aliens[x][y] != 0);
        alienCounter++;
        aliens[x][y] = 1;
        curAlienX = x;
        curAlienY = y;
        repaint();
    }

    /**
     * toggleAlien Toggles wether the user can see the aliens that they already hit,
     * act as a way for other classes to change the value of renderHit
     * 
     * @param none
     * @return void
     */
    public void toggleAliens() {
        renderHit = !renderHit;
        repaint();
    }

    /**
     * triggerMessage method Triggers the message animation, draws a slowly fading
     * and moving text to show certain messages
     * 
     * @param message the message being shown
     * @return void
     */
    public void triggerMessage(String message) {
        this.message = message;
        animationFlag = true;
        message_A = 255;
        repaint();
    }

    /**getTimeScore method
     * Gets the amount of additional time based score, the score follows this curve
     * here https://www.desmos.com/calculator/kkytvgap7t
     * 
     * @param time the time in milliseconds the user took to hit the alien
     * @return the amount of extra time based score that should be awarded to the
     *         user, between 1 and 0
     */
    private double getTimeScore(double time) {

        return time < 10000 ? Math.pow(time - 10000, 2) * Math.pow(0.0001, 2) : 0;
        // https://www.desmos.com/calculator/kkytvgap7t
    }

    /**paintComponent method
     * Calls the paint methods to draw the UI
     * 
     * @param g the graphics object to be drawn on
     * @return void
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // System.out.println(deltaT);
        Graphics2D g2 = (Graphics2D) g;
        iconSize = (int) ((getWidth() > getHeight() ? getHeight() : getWidth() - borderWidth * 2) / graphSize / 2);

        // for drawing the axis
        g2.setColor(Color.GRAY);
        g2.setFont(mainFont);
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawLine(x, getHeight() - borderWidth, x, borderWidth);
        }
        for (int i = 0; i < graphSize; i++) {
            y = getHeight() - (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawLine(getWidth() - borderWidth, y, borderWidth, y);
        }

        // for drawing the center axis
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i < graphSize; i++) {
            g2.drawLine(getWidth() / 2, getHeight() - borderWidth, getWidth() / 2, borderWidth);
        }
        for (int i = 0; i < graphSize; i++) {
            g2.drawLine(getWidth() - borderWidth, getHeight() / 2, borderWidth, getHeight() / 2);
        }

        // for drawing the texts for the axis
        g2.setColor(Color.BLACK);
        g2.setFont(mainFont);
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawString(Integer.toString(i - graphSize / 2), x, getHeight() / 2 + 20);
        }
        for (int i = 0; i < graphSize; i++) {
            y = getHeight() - (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawString(Integer.toString(i - graphSize / 2), getWidth() / 2 + 8, y);
        }

        // drawing the arrows of the axis
        arrowX[0] = getWidth() / 2 - iconSize / 2;
        arrowX[1] = getWidth() / 2;
        arrowX[2] = getWidth() / 2 + iconSize / 2;
        arrowY[0] = borderWidth + iconSize / 2;
        arrowY[1] = borderWidth / 2;
        arrowY[2] = borderWidth + iconSize / 2;
        g2.fillPolygon(arrowX, arrowY, 3);
        arrowX[0] = getWidth() - borderWidth - iconSize / 2;
        arrowX[1] = getWidth() - borderWidth / 2;
        arrowX[2] = getWidth() - borderWidth - iconSize / 2;
        arrowY[0] = getHeight() / 2 - iconSize / 2;
        arrowY[1] = getHeight() / 2;
        arrowY[2] = getHeight() / 2 + iconSize / 2;
        g2.fillPolygon(arrowX, arrowY, 3);

        // drawing the alien images and the animations
        animationFlagTemp = false;
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5) - iconSize / 2);
            for (int j = 0; j < graphSize; j++) {
                y = getHeight()
                        - (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (j + 0.5) + iconSize / 2);
                if (renderHit) {
                    // drawing the alien
                    if (aliens[i][j] != 0)
                        g2.drawImage(alienImage, x, y, iconSize, iconSize, this);
                    // cover the alines that are hit with an x mark and aliens_A white semi
                    // transparent thing to make it less distracting
                    if (aliens[i][j] == 2)
                        g2.drawImage(xImage, x, y, iconSize, iconSize, this);
                    if (aliens[i][j] >= 2) {
                        g2.setColor(new Color(255, 255, 255, 125));
                        g2.fillRect(x, y, iconSize, iconSize);
                    }
                } else {
                    if (aliens[i][j] == 1)
                        g2.drawImage(alienImage, x, y, iconSize, iconSize, this);
                }

                // draws the red/green animations only when the animatons are running to avoid
                // unnesacery checking
                if (animationFlag) {
                    if (aliens_A[i][j] > 0) {
                        aliens_A[i][j] = aliens_A[i][j] - (int) (255 / (correctAT / deltaT));
                        if (aliens_A[i][j] < 0)
                            aliens_A[i][j] = 0;
                        else
                            animationFlagTemp = true;
                        g2.setColor(new Color(0, 255, 0, aliens_A[i][j]));
                        g2.fillRect(x, y, iconSize, iconSize);
                    } else if (aliens_A[i][j] < 0) {
                        aliens_A[i][j] = aliens_A[i][j] + (int) (255 / (errAT / deltaT));
                        if (aliens_A[i][j] > 0)
                            aliens_A[i][j] = 0;
                        else
                            animationFlagTemp = true;
                        g2.setColor(new Color(255, 0, 0, -aliens_A[i][j]));
                        g2.fillRect(x, y, iconSize, iconSize);
                    }
                    // drawing the score
                    g2.setFont(scoreFont);
                    if (score_A[i][j] > 0) {
                        g2.setColor(Color.GREEN);
                        score_A[i][j] = score_A[i][j] - (int) (255 / (scoreAT / deltaT));
                        if (score_A[i][j] < 0)
                            score_A[i][j] = 0;
                        else {
                            animationFlagTemp = true;
                            g2.drawString(String.format("%+.2f", score[i][j]), x,
                                    y - (int) (Math.pow((255.0 - score_A[i][j]) / 255.0, 0.5) * 75));
                        }
                    } else if (score_A[i][j] < 0) {
                        g2.setColor(Color.RED);
                        score_A[i][j] = score_A[i][j] + (int) (255 / (scoreAT / deltaT));
                        if (score_A[i][j] > 0)
                            score_A[i][j] = 0;
                        else {
                            animationFlagTemp = true;
                            g2.drawString(String.format("%+.2f", score[i][j]), x,
                                    y - (int) (Math.pow((255.0 + score_A[i][j]) / 255.0, 0.5) * 75));
                        }
                    }
                }
            }
        }

        g2.setFont(messageFont);
        g2.setColor(Color.ORANGE);
        if (message_A > 0) {
            message_A = message_A - (int) (255 / (messageAT / deltaT));
            if (message_A < 0)
                message_A = 0;
            else {
                animationFlagTemp = true;
                g2.drawString(message, getWidth() / 2 - 50,
                        getHeight() / 2 - (int) (Math.pow((255.0 - message_A) / 255.0, 0.5) * 200.0));
            }
        }
        animationFlag = animationFlagTemp;

        g2.setFont(scoreFont);
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(String.format("%d/%d", alienCounter, gameEndingCount), 50, 50);
        g2.drawString(String.format("%d/%d = %.0f%%", successCounter, attempCounter,
                successCounter / (attempCounter - 0.0) * 100.0), 50, 100);
    }

    /**updateTime method
     * updates curT, deltaT and lastT, should be called in a loop to time animations
     * correctly
     * @param none
     * @return void
     */
    private void updateTime() {
        curT = System.nanoTime();
        deltaT = (int) ((curT - lastT) / 1000000.0);
        lastT = curT;
    }
}
