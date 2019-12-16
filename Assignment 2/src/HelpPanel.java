
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class HelpPanel extends JPanel {
    private JButton backButton;

    private JTextArea helpText;

    private Font buttonFont = new Font("Sans-serif", Font.PLAIN, 35);
    private Font helpFont = new Font("Sans-serif", Font.PLAIN, 20);
    private SpringLayout layout;

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
        helpText.setText("Test\ntest\ntest");
        add(helpText);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, helpText, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, helpText, 0, SpringLayout.VERTICAL_CENTER, this);
        // repaint();
    }

    public void repaintLater() {
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 50);
    }
}