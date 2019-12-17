import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * IOPanel handles the coordinate entering part of the program
 */
public class IOPanel extends JPanel implements DocumentListener {

    private JTextField entry;
    private JLabel inputTipLabel;
    final Color entryBg;
    final static String CANCEL_ACTION = "delete-text";
    final static String ENTER_ACTION = "shoot-aliens";
    final static Color CORRECT_COLOR = Color.GREEN;
    final static Color ERROR_COLOR = Color.RED;
    private Font mainFont = new Font("Sans-serif", Font.PLAIN, 25);

    private boolean clearTextFlag = false;

    /**
     * IOPanel constructor method sets up IOPanel with the JLabel and JTextArea so
     * its useful
     * 
     * @param none
     */
    public IOPanel() {
        // setPreferredSize(new Dimension(gameDimension, LightsOut.tileSize/2));
        setPreferredSize(new Dimension(500, 50));

        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        entry = new JTextField();
        entryBg = entry.getBackground();
        entry.getDocument().addDocumentListener(this);
        entry.setPreferredSize(new Dimension(500, 50));
        entry.setFont(mainFont);

        inputTipLabel = new JLabel("input here:");
        inputTipLabel.setFont(mainFont);

        add(inputTipLabel);
        add(entry);
        InputMap im = entry.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = entry.getActionMap();
        im.put(KeyStroke.getKeyStroke("ESCAPE"), CANCEL_ACTION);
        im.put(KeyStroke.getKeyStroke("ENTER"), ENTER_ACTION);
        am.put(CANCEL_ACTION, new CancelAction());
        am.put(ENTER_ACTION, new EnterAction());
    }

    // DocumentListener methods

    /**
     * insertUpdate method called when there are inserts into the document,
     * indicates that the user has made more inputs to the input field, resets
     * clearTextFlag to false and the background color of the input field incase it
     * was a different color/value
     * 
     * @param ev DocumentEvent, not used
     * @return void
     */
    public void insertUpdate(DocumentEvent ev) {
        clearTextFlag = false;// don't clear the input if the user changed something
        entry.setBackground(entryBg);
    }

    /**
     * removeUpdate method called when there are removes from the document,
     * indicates that the user has made more inputs to the input field, resets
     * clearTextFlag to false and the background color of the input field incase it
     * was a different color/value
     * 
     * @param ev DocumentEvent, not used
     * @return void
     */
    public void removeUpdate(DocumentEvent ev) {
        clearTextFlag = false;
        entry.setBackground(entryBg);
    }
    
    /**changedUpdate method
     * useless, not used at all, 
     * 
     */
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
            if (clearTextFlag) {// so that the user can just hit enter to clear their input, but is still able
                                // to submit their input using
                clearTextFlag = false;
                entry.setText("");
                return;
            }
            try {
                if (input.contains(","))
                    inputArray = input.trim().replaceAll("\\s+", "").split(",");
                else
                    inputArray = input.trim().replaceAll("\\s+", " ").split(" ");
                x = Integer.parseInt(inputArray[0].trim()) + MainFrame.gameSize;
                y = Integer.parseInt(inputArray[1].trim()) + MainFrame.gameSize;
                if (MainFrame.gamePanel.hitAlien(x, y))
                    entry.setBackground(CORRECT_COLOR);
                else
                    entry.setBackground(ERROR_COLOR);
            } catch (Exception e) {
                entry.setBackground(ERROR_COLOR);
                System.out.println("Error parsing input");
            }
            clearTextFlag = true;
        }
    }

    public void wipeEntry() {
        entry.setText("");
    }
}