
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;


public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;//so that vscode can stop screaming at me about the need for a "serialVersionUID"

    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 20);
    private Image alienImage;

    // 2d arrays for storing the light states
    public boolean[][] aliens;
    public int[][] a; // A for alpha animation
    private int[] arrowX = new int[3], arrowY = new int[3];
    private int graphSize, x, y, iconSize, borderWidth;
    private long lastT, curT;// in nanoseconds
    private boolean aFlag = false, aFlagTemp = false;
    private Timer timer;
    private double countDown, deltaT;//in millisecond
    private double errorAnimationTime = 500, correctAnimationTime = 750;//in milliseconds

    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public GamePanel(int graphLimit) {
        graphSize = graphLimit * 2 + 1;
        setPreferredSize(new Dimension(graphSize * 50, graphSize * 50));
        borderWidth = 20;
        initialize();
    }

    private void initialize() {
        // initialize the two arrays
        aliens = new boolean[graphSize][graphSize];
        a = new int[graphSize][graphSize];
        setBackground(Color.WHITE);
        addMouseListener(this);
        try {
            alienImage = ImageIO.read(new File(".\\Assignment 2\\Yuki_Nagato.png"));
        } catch (IOException ex) {
            System.out.println("Warning! Where's the alien image??!?!");
        }
        for (int i = 0; i < 10; i++) {
            // while (true) {
            x = randomRange(0, graphSize);
            y = randomRange(0, graphSize);
            aliens[x][y] = !aliens[x][y];
        }

        lastT = System.nanoTime();
        timer = new Timer();
        updateTime();
        countDown = 15000;//in seconds
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                updateTime();
                countDown -= deltaT;
                MainFrame.infoPanel.updateTimer(Double.toString(countDown/1000.0));
                if (aFlag){
                    repaint();
                }
            }
        }, 50, 50);//runs approximately every 50 millisecond, making the game 20 ticks per second
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
            if (aliens[x][y]) {
                aliens[x][y] = false;
                a[x][y] = 255;
                aFlag = true;
                output = true;
            } else {
                a[x][y] = -255;
                aFlag = true;
                output = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            output = false;
        }
        repaint();
        return output;
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
        aFlagTemp = false;
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5) - iconSize / 2);
            for (int j = 0; j < graphSize; j++) {
                y = getHeight()
                        - (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (j + 0.5) + iconSize / 2);
                if (aFlag) {
                    if (a[i][j] > 0) {
                        a[i][j] = a[i][j] - (int)(255/(correctAnimationTime/deltaT));
                        if (a[i][j] < 0) {
                            a[i][j] = 0;
                        } else {
                            aFlagTemp = true;
                        }
                        g2.setColor(new Color(0, 255, 0, a[i][j]));
                        g2.fillRect(x, y, iconSize, iconSize);
                    } else if (a[i][j] < 0) {
                        a[i][j] = a[i][j] + (int)(255/(errorAnimationTime/deltaT));
                        if (a[i][j] > 0) {
                            a[i][j] = 0;
                        } else {
                            aFlagTemp = true;
                        }
                        g2.setColor(new Color(255, 0, 0, -a[i][j]));
                        g2.fillRect(x, y, iconSize, iconSize);
                    }
                }
                if (aliens[i][j])
                    g2.drawImage(alienImage, x, y, iconSize, iconSize, this);
            }
        }
        aFlag = aFlagTemp;
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
