
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**HelpPanel class 
 * Shows some instructions and explanations for the game
*/
public class HelpPanel extends JPanel {
    private JButton backButton;

    private JTextArea helpText;

    private Font buttonFont = new Font("Sans-serif", Font.PLAIN, 35);
    private Font helpFont = new Font("Sans-serif", Font.PLAIN, 20);
    private SpringLayout layout;

    /**HelpPanel constructor method
     * Creates and setup HelpPanel with the variety of JComponents
     */
    public HelpPanel() {
        layout = new SpringLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(MainFrame.standardWidth, MainFrame.standardHeight));
        setBackground(Color.LIGHT_GRAY);
        backButton = new JButton("New Game");
        backButton.setFont(buttonFont);
        backButton.setAction(new AbstractAction("Back") {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaceAlien.frame.openMainMenu();
            }
        });
        backButton.setBackground(Color.WHITE);
        backButton.setMargin(new Insets(0, 0, 0, 0));
        add(backButton);

        helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setFont(helpFont);
        helpText.setText(
                "Space Alien Grid Game: By Kevin H\nEnter coordinates as a pair of numbers seperated by \",\" or \" \"\nfor example: \"2 3\"\nHit enter again after inputting a coordinate to clear the input\n\nAliens caught gives you 1 point + a time bonus between 0 and 1\nAliens missed will deduct 1 point\nAliens escape after 10 seconds, also deducting 1 point");
        helpText.setLineWrap(true);
        helpText.setSize(700, 400);
        add(helpText);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, helpText, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, helpText, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.SOUTH, helpText);
    }
}