
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MenuPanel extends JPanel {
    private JButton newGameButton;
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 35);
    private int colorFlag = 0;
    
    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 50));
        
        newGameButton = new JButton("New Game");
        newGameButton.setFont(mainFont);
        newGameButton.setAction(new AbstractAction("New Game"){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.gamePanel.startGame(4, 30000);
            }
        });
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setMargin(new Insets(0,0,0,0));
        add(newGameButton);
    }
}