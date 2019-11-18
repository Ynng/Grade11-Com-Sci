import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static GamePanel gamePanel;
    // public static StatusPanel statusPanel;
    public static InputPanel inputPanel;

    public static int gameSize = 4;

    public MainFrame() {
        super("Alien");
        setLayout(new BorderLayout());
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));

        // toolbar = new ToolBar();
        // add(toolbar, BorderLayout.PAGE_START);
        gamePanel = new GamePanel(gameSize);
        add(gamePanel, BorderLayout.CENTER);
        inputPanel = new InputPanel();
        add(inputPanel, BorderLayout.PAGE_END);
        setVisible(true);
        pack();
    }


}
