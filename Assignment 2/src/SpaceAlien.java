import javax.swing.*;
/*=================================================================
Space Alien game - Assignment #2
Kevin Huang
December 19th
Java, 11
=================================================================
Problem Definition 	– to help young math students learn about the Cartesian plane and locate items on a grid
Input – coordinate pairs and mouse clicks
Output – highscore file and UI
=================================================================
*/ 

/**SpaceAlien Class
 * The main class that contains the main Jframe, MainFrame and the main method
 */
public class SpaceAlien {
    public static MainFrame frame = null;

    /**main method
     * Creates a new instance of MainFrame in a new thread.
     * @param args Not used
     * @return void
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new MainFrame();
            }
        });
    }
}