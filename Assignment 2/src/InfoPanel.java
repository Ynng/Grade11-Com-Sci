
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class InfoPanel extends JPanel {
    private SpringLayout layout;

    private JLabel timeTipLabel;
    private JLabel timeLabel;
    private JLabel scoreTipLabel;
    private JLabel scoreLabel;
    private JButton newGameButton;
    private JButton showAliensButton;

    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 25);
    private Font tipFont = new Font("Sans-serif", Font.PLAIN, 15);

    private int colorFlag = 0;
    
    public InfoPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 50));
        
        timeTipLabel = new JLabel("Time Used:");
        timeTipLabel.setFont(tipFont);
        add(timeTipLabel);

        timeLabel = new JLabel("Error: no time");
        timeLabel.setFont(mainFont);
        add(timeLabel);


        scoreTipLabel = new JLabel("   Score:");
        scoreTipLabel.setFont(tipFont);
        add(scoreTipLabel);

        scoreLabel  = new JLabel("Error: no score");
        scoreLabel.setFont(mainFont);
        add(scoreLabel);


        newGameButton = new JButton("New Game");
        newGameButton.setFont(mainFont);
        newGameButton.setAction(new AbstractAction("New Game"){
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaceAlien.frame.startNewGame();
                MainFrame.gamePanel.triggerMessage("New Game!");
            }
        });
        newGameButton.setBackground(Color.WHITE);
        newGameButton.setMargin(new Insets(0,0,0,0));
        add(newGameButton);

        showAliensButton = new JButton("toggle aliens");
        showAliensButton.setFont(mainFont);
        showAliensButton.setAction(new AbstractAction("toggle aliens"){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.gamePanel.toggleAliens();
                MainFrame.gamePanel.triggerMessage("Alien Toggled");
            }
        });
        showAliensButton.setBackground(Color.WHITE);
        showAliensButton.setMargin(new Insets(0,0,0,0));
        add(showAliensButton);
    }

    public void updateTimer(double timeLeft){
        timeLabel.setText(String.format("%.0f:%02.0f:%01.0f",Math.floor(timeLeft/60.0),Math.floor(timeLeft%60),Math.floor(timeLeft*10%10)));
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
        scoreLabel.setText(String.format("%.2f", score));
    }
}