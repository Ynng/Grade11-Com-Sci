
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class StatsPanel extends JPanel {
    private SpringLayout layout;

    private JLabel timeTipLabel;
    private JLabel timeLabel;
    private JLabel scoreTipLabel;
    private JLabel scoreLabel;
    private JButton showAliensButton;


    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 25);
    private Font tipFont = new Font("Sans-serif", Font.PLAIN, 15);

    private int colorFlag = 0;
    
    public StatsPanel(double score, double avgTimeUsed) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 50));
        
        timeTipLabel = new JLabel("Average time used:");
        timeTipLabel.setFont(tipFont);
        add(timeTipLabel);

        timeLabel = new JLabel("Error: no time");
        timeLabel.setFont(mainFont);
        add(timeLabel);


        scoreTipLabel = new JLabel("   Final score:");
        scoreTipLabel.setFont(tipFont);
        add(scoreTipLabel);

        scoreLabel  = new JLabel("Error: no score");
        scoreLabel.setFont(mainFont);
        add(scoreLabel);

        showAliensButton = new JButton("toggle aliens");
        showAliensButton.setFont(mainFont);
        showAliensButton.setAction(new AbstractAction("Toggle Aliens"){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.gamePanel.toggleAliens();
                MainFrame.gamePanel.triggerMessage("Alien Toggled");
            }
        });
        showAliensButton.setBackground(Color.WHITE);
        showAliensButton.setMargin(new Insets(0,0,0,0));
        add(showAliensButton);
        updateStats(score,avgTimeUsed);
    }

    public void updateStats(double score, double avgTimeUsed){
        timeLabel.setText(String.format("%.0f:%02.0f:%01.0f",Math.floor(avgTimeUsed/60.0),Math.floor(avgTimeUsed%60),Math.floor(avgTimeUsed*10%10)));
        scoreLabel.setText(String.format("%.2f", score));
    }
}