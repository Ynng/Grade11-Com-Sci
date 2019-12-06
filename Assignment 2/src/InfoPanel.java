
import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {
    private JLabel timeLabel;
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 35);

    public InfoPanel() {
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 50));
        timeLabel = new JLabel("Test");
        timeLabel.setFont(mainFont);
        add(timeLabel);
    }

    public void updateTimer(double timeLeft){
        timeLabel.setText(Double.toString(timeLeft));
        if(timeLeft<5){
            timeLabel.setForeground(Color.RED);
        }
    }
}