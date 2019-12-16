
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private JButton helpButton;
    private JButton exitButton;

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

        exitButton = new JButton("Exit Game");
        exitButton.setBackground(Color.WHITE);
        exitButton.setFont(mainFont);
        exitButton.setAction(new AbstractAction("Exit Game"){
            @Override
            public void actionPerformed(ActionEvent a) {
                try{
                    System.exit(0);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
        exitButton.setMargin(new Insets(0,0,0,0));
        add(exitButton);

        helpButton = new JButton("Help?");
        helpButton.setBackground(Color.WHITE);
        helpButton.setFont(mainFont);
        helpButton.setAction(new AbstractAction("Help?"){
            @Override
            public void actionPerformed(ActionEvent a) {
                SpaceAlien.frame.openHelpPage();
            }
        });
        helpButton.setMargin(new Insets(0,0,0,0));
        add(helpButton);

        gameTitleLabel = new JLabel("Assignment#2 by Kevin H");
        gameTitleLabel.setFont(mainFont);
        add(gameTitleLabel);
        

        layout.putConstraint(SpringLayout.VERTICAL_CENTER, gameTitleLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gameTitleLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exitButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, helpButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, newGameButton, 50, SpringLayout.SOUTH, gameTitleLabel);
        layout.putConstraint(SpringLayout.NORTH, exitButton, 50, SpringLayout.SOUTH, newGameButton);
        layout.putConstraint(SpringLayout.NORTH, helpButton, 50, SpringLayout.SOUTH, exitButton);


    }
}