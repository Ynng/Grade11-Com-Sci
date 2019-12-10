import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static GamePanel gamePanel;
    public static InfoPanel infoPanel;
    public static IOPanel ioPanel;
    public static MenuPanel menuPanel;


    public static int gameSize = 4;

    public MainFrame() {
        super("Alien");
        setLayout(new BorderLayout());
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));

        menuPanel = new MenuPanel();
        add(menuPanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }

    public void startNewGame(){
        remove(menuPanel);
        if(gamePanel == null){
            gamePanel = new GamePanel(gameSize, 30000);
        }else{
            gamePanel.startGame(gameSize, 30000);
            add(gamePanel, BorderLayout.CENTER);
        }
        if(infoPanel==null){
            infoPanel = new InfoPanel();
        }
        add(infoPanel, BorderLayout.PAGE_START);
        if(ioPanel==null){
            ioPanel = new IOPanel();
        }
        add(ioPanel, BorderLayout.SOUTH);
    }
}
