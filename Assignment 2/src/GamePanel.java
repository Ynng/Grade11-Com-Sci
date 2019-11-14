
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    // 2d arrays for storing the light states

    public GamePanel() {
        setPreferredSize(new Dimension(800, 800));

        setBackground(Color.PINK);
        // initialize the two arrays
        addMouseListener(this);
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int panelSize = smaller(getWidth(), getHeight());
        int tileSize = panelSize / LightsOut.gridSize;

        int y = (getHeight() - panelSize) / 2 + LightsOut.tileMargin / 2;
        for (int row = 0; row < LightsOut.gridSize; row++) {
            int x = (getWidth() - panelSize) / 2 + LightsOut.tileMargin / 2;
            for (int col = 0; col < LightsOut.gridSize; col++) {
                if (lightsClicked[row][col] && showSolution) {
                    // drawing the border
                    g2.setColor(Color.GREEN);
                    g2.fillRect(x, y, tileSize - LightsOut.tileMargin, tileSize - LightsOut.tileMargin);
                    // drawing the actual tile
                    if (lights[row][col])
                        g2.setColor(Color.WHITE);
                    else
                        g2.setColor(Color.BLACK);
                    g2.fillRect(x + LightsOut.tileBorder, y + LightsOut.tileBorder,
                            tileSize - LightsOut.tileMargin - LightsOut.tileBorder * 2,
                            tileSize - LightsOut.tileMargin - LightsOut.tileBorder * 2);

                } else {
                    // drawing the actual tiles
                    if (lights[row][col])
                        g2.setColor(Color.WHITE);
                    else
                        g2.setColor(Color.BLACK);
                    g2.fillRect(x, y, tileSize - LightsOut.tileMargin, tileSize - LightsOut.tileMargin);
                    // drawing the border of the tiles
                }
                x += tileSize;
            }
            y += tileSize;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (solving) {
            JOptionPane.showMessageDialog(null, "In the process of solving");
            return;
        }
        int mouseX = e.getX();
        int mouseY = e.getY();
        // System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
        int panelSize = smaller(getWidth(), getHeight());
        int tileSize = panelSize / LightsOut.gridSize;
        // filter out invalid clicks
        if (mouseX < ((getWidth() - panelSize) / 2) || mouseX > ((getWidth() - panelSize) / 2 + panelSize)
                || mouseY < ((getHeight() - panelSize) / 2) || mouseY > ((getHeight() - panelSize) / 2 + panelSize)) {
            return;
        }
        mouseX -= (getWidth() - panelSize) / 2;
        mouseY -= (getHeight() - panelSize) / 2;

        int col = mouseX / tileSize;
        int row = mouseY / tileSize;

        if (col > LightsOut.gridSize - 1) {
            col = LightsOut.gridSize - 1;
            System.out.println("col out of bound");
        }
        if (row > LightsOut.gridSize - 1) {
            row = LightsOut.gridSize - 1;
            System.out.println("row out of bound");
        }

        stepCounter++;
        toggle(lights, row, col);

        lightsClicked[row][col] = !lightsClicked[row][col];
        MainFrame.statusPanel.setStep(stepCounter);
        repaint();
        if (0 == countBools(lights)) {
            // gameOver();
            LightsOut.gameOver();
        }
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
