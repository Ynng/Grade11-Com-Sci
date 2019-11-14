import javax.swing.*;

public class SpaceAlien {
    public static MainFrame frame = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new MainFrame();
            }
        });
    }
}