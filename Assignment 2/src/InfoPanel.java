
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class InfoPanel extends JPanel {
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JButton newGameButton;
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 35);
    private int colorFlag = 0;
    
    public InfoPanel() {
        setLayout(new FlowLayout(FlowLayout.LEADING, 25,0));
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 50));
        
        timeLabel = new JLabel("Error: no time");
        timeLabel.setFont(mainFont);
        add(timeLabel);

        scoreLabel  = new JLabel("Error: no score");
        scoreLabel.setFont(mainFont);
        add(scoreLabel);

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

    public void updateTimer(double timeLeft){
        timeLabel.setText(String.format("Time Taken: %.0f:%02.0f:%01.0f",Math.floor(timeLeft/60.0),Math.floor(timeLeft%60),Math.floor(timeLeft*10%10)));
        if(timeLeft>5){
            if(colorFlag==0){
                timeLabel.setForeground(Color.RED);
                colorFlag = 1;
            }
        }else{
            if(colorFlag==1){
                timeLabel.setForeground(Color.BLACK);
                colorFlag = 0;
            }
        }
    }

    public void updateScore(double score){
        scoreLabel.setText(String.format("Score: %.2f", score));
    }
}