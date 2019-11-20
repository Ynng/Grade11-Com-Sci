
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
    public boolean[][] aliens;
    public int[][] animation;
    private int graphSize;
    private Image alienImage;
    private int x, y, iconSize, borderWidth, deltaT;
    private long lastT, curT;//in nanoseconds
    private int[] arrowX = new int[3], arrowY = new int[3];
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 20);
    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public GamePanel(int graphLimit) {
        graphSize = graphLimit * 2 + 1;
        setPreferredSize(new Dimension(graphSize * 100, graphSize * 100));
        borderWidth = 20;
        initialize();
    }

    private void initialize() {
        lastT = System.nanoTime();
        aliens = new boolean[graphSize][graphSize];
        animation = new int[graphSize][graphSize];
        setBackground(Color.PINK);
        // initialize the two arrays
        addMouseListener(this);
        try {
            alienImage = ImageIO.read(new File(".\\Assignment 2\\Yuki_Nagato.png"));
        } catch (IOException ex) {
            System.out.println("Warning! Where's the alien image??!?!");
        }
        for (int i = 0; i < 10; i++) {
            // while (true) {
            int row = randomRange(0, graphSize);
            int col = randomRange(0, graphSize);
            aliens[row][col] = !aliens[row][col];
            // System.out.println((row + 1) + " " + (col + 1) + "\n");
            // if (countBools(lights) == (LightsOut.graphSize * LightsOut.graphSize) / 2)
            // break;
        }
    }


    /**
     * 
     * @param x
     * @param y
      * @return true = hit alien, false = didn't hit anything/error 
     */
    public boolean hitAlien(int x, int y){
        try{
            if(aliens[x][y]){
                aliens[x][y] = false;
                animation[x][y] = 100;
                repaint();
                return true;
            }else{
                animation[x][y] = -100;
                repaint();
                return false;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            repaint();
            return false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateTime();
        Graphics2D g2 = (Graphics2D) g;

        iconSize = (int)((getWidth() > getHeight() ? getHeight() : getWidth() - borderWidth * 2) / graphSize / 2);

        // for drawing the axis
        g2.setColor(Color.GRAY);
        g2.setFont(mainFont);
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawLine(x, getHeight() - borderWidth, x, borderWidth);
        }
        for (int i = 0; i < graphSize; i++) {
            y = getHeight()-(int)(borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (i + 0.5));
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

        //for drawing the texts for the axis
        g2.setColor(Color.BLACK);
        g2.setFont(mainFont);
        for (int i = 0; i < graphSize; i++) {
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawString(Integer.toString(i-graphSize/2), x, getHeight()/2+20);
        }
        for (int i = 0; i < graphSize; i++) {
            y = getHeight()-(int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (i + 0.5));
            g2.drawString(Integer.toString(i-graphSize/2), getWidth()/2+8,y);
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
            System.out.println(deltaT);
            x = (int) (borderWidth + (getWidth() - 2 * borderWidth) / graphSize * (i + 0.5) - iconSize / 2);
            for (int j = 0; j < graphSize; j++) {
                y = getHeight() - (int) (borderWidth + (getHeight() - 2 * borderWidth) / graphSize * (j + 0.5) + iconSize / 2);
                if(animation[i][j]>0){
                    animation[i][j] = animation[i][j] - deltaT;
                    if(animation[i][j]<0)animation[i][j] = 0;
                    g2.setColor(new Color(0,2*animation[i][j],0));
                    g2.fillRect(x,y,iconSize,iconSize);
                }else if(animation[i][j]<0){
                    animation[i][j] = animation[i][j] + deltaT;
                    if(animation[i][j]>0)animation[i][j] = 0;
                    g2.setColor(new Color(2*animation[i][j],0,0));
                    g2.fillRect(x,y,iconSize,iconSize);
                }
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

    private void updateTime(){
        curT = System.nanoTime();
        deltaT = (int)((curT - lastT)/1000000);
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
