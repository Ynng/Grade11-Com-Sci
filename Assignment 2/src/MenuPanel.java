
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private JLabel gameTitleLabel;

    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 35);
    private SpringLayout layout;


    
    public MenuPanel() {
        layout = new SpringLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(MainFrame.standardWidth, MainFrame.standardHeight));
        setBackground(Color.LIGHT_GRAY);
        newGameButton = new JButton("New Game");
        newGameButton.setFont(mainFont);
        newGameButton.setAction(new AbstractAction("New Game"){
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaceAlien.frame.startNewGame();
            }
        });
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setMargin(new Insets(0,0,0,0));
        add(newGameButton);
        gameTitleLabel = new JLabel("Assignment#2 by Kevin H");
        gameTitleLabel.setFont(mainFont);
        add(gameTitleLabel);


        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gameTitleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, gameTitleLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, newGameButton, 50, SpringLayout.SOUTH, gameTitleLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER, this);

    }
}