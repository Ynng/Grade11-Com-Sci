
import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 35);
    private int colorFlag = 0;
    
    public InfoPanel() {
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 50));
        
        timeLabel = new JLabel("Error: no countdown");
        timeLabel.setFont(mainFont);
        add(timeLabel);

        scoreLabel  = new JLabel("Error: no score");
        scoreLabel.setFont(mainFont);
        add(scoreLabel);
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
        scoreLabel.setText(String.format("%.2f", score));
    }
}