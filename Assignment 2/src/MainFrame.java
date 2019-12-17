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
    public static HighscorePanel highscorePanel;
    private int gameStatusFlag = 0;// 0 = in menu, 1 = in game, 2 = in help, 3 = in highscore
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
        if(gameStatusFlag==3)remove(highscorePanel);
        if(gameStatusFlag!=1){
            if(gamePanel == null){
                gamePanel = new GamePanel(gameSize);
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
            gamePanel.startGame(gameSize);
        }
        gameStatusFlag = 1;
    }
    public void openHelpPage(){
        if(gameStatusFlag==0)remove(menuPanel);
        if(gameStatusFlag==3)remove(highscorePanel);
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
        if(gameStatusFlag==3)remove(highscorePanel);
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

    public void openHighscore(){//warning: also stops and destroys the current game
        if(gameStatusFlag==0)remove(menuPanel);
        if(gameStatusFlag==2)remove(helpPanel);
        if(gameStatusFlag==1){
            gamePanel.abandonGame();
            remove(gamePanel);
            remove(infoPanel);
            remove(ioPanel);
        }
        if(highscorePanel==null){
            highscorePanel = new HighscorePanel();
        }
        add(highscorePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        gameStatusFlag = 3;
    }
}
