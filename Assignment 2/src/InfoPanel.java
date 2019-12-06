
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
    }

    public void updateTimer(double timeLeft){
        timeLabel.setText(String.format("Time Left: %.0f:%02.0f:%01.0f",Math.floor(timeLeft/60.0),Math.floor(timeLeft%60),Math.floor(timeLeft*10%10)));
        if(timeLeft<5){
            if(colorFlag==0){
                timeLabel.setForeground(Color.RED);
                colorFlag = 1;
            }
        }
    }
}