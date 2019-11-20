import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class IOPanel extends JPanel implements DocumentListener {

    private JTextField entry;
    final Color entryBg;
    final static String CANCEL_ACTION = "delete-text";
    final static String ENTER_ACTION = "shoot-aliens";
    final static Color CORRECT_COLOR = Color.GREEN;
    final static Color ERROR_COLOR = Color.RED;
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 25);

    public IOPanel() {
        // setPreferredSize(new Dimension(gameDimension, LightsOut.tileSize/2));
        setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 100));

        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        entry = new JTextField();
        entryBg = entry.getBackground();
        entry.getDocument().addDocumentListener(this);
        entry.setPreferredSize(new Dimension((MainFrame.gameSize * 2 + 1) * 100, 100));
        add(entry);
        entry.setFont(mainFont);
        InputMap im = entry.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = entry.getActionMap();
        im.put(KeyStroke.getKeyStroke("ESCAPE"), CANCEL_ACTION);
        im.put(KeyStroke.getKeyStroke("ENTER"), ENTER_ACTION);
        am.put(CANCEL_ACTION, new CancelAction());
        am.put(ENTER_ACTION, new EnterAction());
    }

    // DocumentListener methods

    public void insertUpdate(DocumentEvent ev) {
        entry.setBackground(entryBg);
    }

    public void removeUpdate(DocumentEvent ev) {
        entry.setBackground(entryBg);
    }

    public void changedUpdate(DocumentEvent ev) {
    }

    class CancelAction extends AbstractAction {
        public void actionPerformed(ActionEvent ev) {
            entry.setText("");
            entry.setBackground(entryBg);
        }
    }

    class EnterAction extends AbstractAction {
        String input;
        String[] inputArray;
        int x, y;

        public void actionPerformed(ActionEvent ev) {
            input = entry.getText();
            try {
                inputArray = input.trim().split(" ");
                x = Integer.parseInt(inputArray[0].trim()) + MainFrame.gameSize;
                y = Integer.parseInt(inputArray[1].trim()) + MainFrame.gameSize;
            } catch (NumberFormatException e) {
                entry.setBackground(ERROR_COLOR);
                System.out.println("Can't parse input");
            }
            if (MainFrame.gamePanel.hitAlien(x, y))
                entry.setBackground(CORRECT_COLOR);
            else
                entry.setBackground(ERROR_COLOR);
        }
    }
}