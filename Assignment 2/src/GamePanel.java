
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    // 2d arrays for storing the light states
    private boolean[][] aliens;
    private int gridSize;

    public GamePanel(int gridSize) {
        setPreferredSize(new Dimension(800, 800));
        this.gridSize = gridSize;
        aliens = new boolean[gridSize][gridSize];
        setBackground(Color.PINK);
        // initialize the two arrays
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2.setColor(Color.BLACK);

        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++){
                g2.fillRect(x, y, tileSize - LightsOut.tileMargin, tileSize - LightsOut.tileMargin);
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
