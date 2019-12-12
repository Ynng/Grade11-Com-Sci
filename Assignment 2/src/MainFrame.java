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
    private int gameStatusFlag = 0;
    public static int gameSize = 4, standardWidth = 800, standardHeight = 1050;

    public MainFrame() {
        super("Alien");
        setLayout(new BorderLayout());
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 400));
        setPreferredSize(new Dimension(standardWidth,standardHeight));
        menuPanel = new MenuPanel();
        add(menuPanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }

    public void startNewGame(){
        if(gameStatusFlag==0){
            gameStatusFlag = 1;
            remove(menuPanel);
            if(gamePanel == null){
                gamePanel = new GamePanel(gameSize, 30000);
            }
            if(infoPanel==null){
                infoPanel = new InfoPanel();
            }
            if(ioPanel==null){
                ioPanel = new IOPanel();
            }
            add(gamePanel, BorderLayout.CENTER);
            add(infoPanel, BorderLayout.PAGE_START);
            add(ioPanel, BorderLayout.SOUTH);
        }else{
            gamePanel.startGame(gameSize, 30000);
        }
    }
}
