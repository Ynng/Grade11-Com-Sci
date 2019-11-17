
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 2d arrays for storing the light states
    private boolean[][] aliens;
    private int graphSize;
    private Image alienImage;
    private int borderWidth = 20;// px
    private int x, y, iconSize;
    private int[] arrowX = new int[3], arrowY = new int[3];

    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public GamePanel(int graphLimit) {
        graphSize = graphLimit * 2 + 1;
        setPreferredSize(new Dimension(graphSize * 100, graphSize * 100));
        initialize();
    }

    private void initialize() {
        aliens = new boolean[graphSize][graphSize];
        setBackground(Color.PINK);
        // initialize the two arrays
        addMouseListener(this);
        try {
            alienImage = ImageIO.read(new File(".\\Assignment 2\\Yuki_Nagato.png"));
        } catch (IOException ex) {
            System.out.println("Warning! Where's the alien image??!?!");
        }
        for (int i = 0; i < ((graphSize * graphSize)); i++) {
            // while (true) {
            int row = randomRange(0, graphSize);
            int col = randomRange(0, graphSize);
            aliens[row][col] = !aliens[row][col];
            // System.out.println((row + 1) + " " + (col + 1) + "\n");
            // if (countBools(lights) == (LightsOut.graphSize * LightsOut.graphSize) / 2)
            // break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        iconSize = (int)((getWidth() > getHeight() ? getHeight() : getWidth() - borderWidth * 2) / graphSize / 2.5);

        // for drawing the axis
        g2.setColor(Color.GRAY);
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawLine(x, getHeight() - borderWidth, x, borderWidth);
        }
        for (int i = 0; i < graphSize; i++) {
            y = (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (i + 0.5));
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
        
        //drawing the arrows of the axis
        arrowX[0]=getWidth()/2-iconSize/2;
        arrowX[1]=getWidth()/2;
        arrowX[2]=getWidth()/2+iconSize/2;
        arrowY[0]=borderWidth+iconSize/2;
        arrowY[1]=borderWidth/2;
        arrowY[2]=borderWidth+iconSize/2;
        g2.fillPolygon(arrowX, arrowY, 3);
        arrowX[0]=getWidth()-borderWidth-iconSize/2;
        arrowX[1]=getWidth()-borderWidth/2;
        arrowX[2]=getWidth()-borderWidth-iconSize/2;
        arrowY[0]=getHeight()/2-iconSize/2;
        arrowY[1]=getHeight()/2;
        arrowY[2]=getHeight()/2+iconSize/2;
        g2.fillPolygon(arrowX, arrowY, 3);

        // drawing the alien images
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5) - iconSize / 2);
            for (int j = 0; j < graphSize; j++) {
                y = (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (j + 0.5) - iconSize / 2);
                if (aliens[i][j])
                    g2.drawImage(alienImage, x, y, iconSize, iconSize, this);
            }
        }

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

    // public int getStepCounter(){
    // return this.stepCounter;
    // }

    // public void setStepCounter(int stepCounter){
    // this.stepCounter = stepCounter;
    // }
}
