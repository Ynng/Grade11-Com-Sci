
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;// so that vscode can stop screaming at me about the need for
                                                    // aliens_A
                                                    // "serialVersionUID"

    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 20);
    private Font scoreFont = new Font("Sans-serif", Font.BOLD, 30);
    private Font messageFont = new Font("Sans-serif", Font.BOLD, 40);

    private Image alienImage, xImage;

    // 2d arrays for storing the light states
    private double[][] score;
    private int[][] aliens, aliens_A, score_A; // aliens_A for alpha animation
    private int[] arrowX = new int[3], arrowY = new int[3];
    private String message;
    private int graphSize, x, y, iconSize, borderWidth, alienCounter, message_A;
    private long startTime, curT;// in nanoseconds
    private boolean animationFlag, animationFlagTemp, renderHit;
    private Timer timer;
    private double timeLimit, timeUsed, deltaT, lastT, totalScore;// in millisecond
    private double errAT = 500, correctAT = 750, scoreAT = 750, messageAT = 1000;// in milliseconds

    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public GamePanel(int i_graphSize, double i_timeLimit) {
        setPreferredSize(new Dimension((i_graphSize * 2 + 1) * 50, (i_graphSize * 2 + 1) * 50));
        setBackground(Color.WHITE);
        addMouseListener(this);
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
                updateTime();
                timeUsed = (curT - startTime) / 1000000.0;

                MainFrame.infoPanel.updateTimer(timeUsed / 1000.0);
                if (animationFlag) {
                    repaint();
                }

                MainFrame.infoPanel.updateScore(totalScore);
            }
        }, 50, 50);// runs approximately every 50 millisecond, making the game 20 ticks per second
        startGame(i_graphSize, i_timeLimit);
    }

    public void startGame(int i_graphSize, double i_timeLimit) {
        graphSize = i_graphSize * 2 + 1;
        timeLimit = i_timeLimit;

        // initialize the arrays
        aliens = new int[graphSize][graphSize];
        aliens_A = new int[graphSize][graphSize];
        score_A = new int[graphSize][graphSize];
        score = new double[graphSize][graphSize];

        totalScore = 0;
        startTime = System.nanoTime();
        lastT = System.nanoTime();
        generateAlien();
        timeUsed = timeLimit;// in millisecond
    }

    /**
     * 
     * @param x
     * @param y
     * @return true = hit alien, false = didn't hit anything/error
     * @throws InterruptedException
     */
    public boolean hitAlien(int x, int y) {
        boolean output;
        try {
            if (aliens[x][y] == 1) {
                aliens[x][y] = 2;
                aliens_A[x][y] = score_A[x][y] = 255;
                animationFlag = output = true;
                timeUsed = (curT - startTime) / 1000000.0;
                score[x][y] = 1 + getTimeScore(timeUsed);
                totalScore += score[x][y];
                if (alienCounter <= graphSize * graphSize / 2)
                    generateAlien();
                startTime = System.nanoTime();
            } else {
                aliens_A[x][y] = -255;
                animationFlag = true;
                output = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            output = false;
        }
        repaint();
        return output;
    }

    private void generateAlien() {
        do {
            x = randomRange(0, graphSize);
            y = randomRange(0, graphSize);
        } while (aliens[x][y] != 0);
        alienCounter++;
        aliens[x][y] = 1;
        repaint();
    }

    public void toggleAliens() {
        renderHit = !renderHit;
        repaint();
    }

    public void triggerMessage(String message) {
        this.message = message;
        animationFlag = true;
        message_A = 255;
        repaint();
    }

    private double getTimeScore(double time) {

        return time < 10000 ? Math.pow(time - 10000, 4) * Math.pow(0.0001, 4) : 0;
        // https://www.desmos.com/calculator/0ls2e5ylld
    }

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
                    if (aliens[i][j] == 1 || aliens[i][j] == 2)
                        g2.drawImage(alienImage, x, y, iconSize, iconSize, this);
                    // cover the alines that are hit with an x mark and aliens_A white semi
                    // transparent thing to make it less distracting
                    if (aliens[i][j] == 2) {
                        g2.drawImage(xImage, x, y, iconSize, iconSize, this);

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
                    g2.setColor(Color.MAGENTA);
                    if (score_A[i][j] > 0) {
                        score_A[i][j] = score_A[i][j] - (int) (255 / (scoreAT / deltaT));
                        if (aliens_A[i][j] < 0)
                            aliens_A[i][j] = 0;
                        else
                            animationFlagTemp = true;
                        g2.drawString(String.format("+ %.2f", score[i][j]), x,
                                y - (int) (Math.pow((255.0 - score_A[i][j]) / 255.0, 0.5) * 75));
                    }
                }
            }
        }

        g2.setFont(messageFont);
        g2.setColor(Color.ORANGE);
        if (message_A > 0) {
            message_A =  message_A - (int) (255 / (messageAT / deltaT));
            if (message_A < 0)
                message_A = 0;
            else {
                animationFlagTemp = true;
                g2.drawString(message, getWidth() / 2 - 50,
                        getHeight() / 2 - (int)(Math.pow((255.0 - message_A) / 255.0, 0.5) * 200.0));
            }
        }
        System.out.println(message_A+"");
        animationFlag = animationFlagTemp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*
         * if (solving) { JOptionPane.showMessageDialog(null,
         * "In the process of solving"); return; }
         */
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.printf("Clicked at x:%d, y:%d\n", mouseX, mouseY);
    }

    private void updateTime() {
        curT = System.nanoTime();
        deltaT = (int) ((curT - lastT) / 1000000.0);
        lastT = curT;
    }

    // Useless junk
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
