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
    public static HelpPanel helpPanel;
    private int gameStatusFlag = 0;// 0 = in menu, 1 = in game, 2 = in help
    public static int gameSize = 4, standardWidth = 800, standardHeight = 1000;

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
        if(gameStatusFlag==0)remove(menuPanel);
        if(gameStatusFlag==2)remove(helpPanel);
        if(gameStatusFlag!=1){
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
            revalidate();
            repaint();
        }else{
            gamePanel.startGame(gameSize, 30000);
        }
        gameStatusFlag = 1;
    }
    public void openHelpPage(){
        if(gameStatusFlag==0)remove(menuPanel);
        if(gameStatusFlag==1){
            gamePanel.abandonGame();
            remove(gamePanel);
            remove(infoPanel);
            remove(ioPanel);
        }
        if(helpPanel==null){
            helpPanel = new HelpPanel();
        }
        add(helpPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        gameStatusFlag = 2;
    }
    public void openMainMenu(){//warning: also stops and destroys the current game
        if(gameStatusFlag==2)remove(helpPanel);
        if(gameStatusFlag==1){
            gamePanel.abandonGame();
            remove(gamePanel);
            remove(infoPanel);
            remove(ioPanel);
        }
        add(menuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        gameStatusFlag = 0;
    }
}
