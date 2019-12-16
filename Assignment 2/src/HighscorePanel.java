
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HighscorePanel extends JPanel {
    private JButton backButton;
    private JButton choosePathButton;

    private JTextArea highscoreText;

    private Font buttonFont = new Font("Sans-serif", Font.PLAIN, 35);
    private Font helpFont = new Font("Sans-serif", Font.PLAIN, 20);
    private SpringLayout layout;

    public HighscorePanel() {
        layout = new SpringLayout();
        setLayout(layout);
        setPreferredSize(new Dimension(MainFrame.standardWidth, MainFrame.standardHeight));
        setBackground(Color.LIGHT_GRAY);
        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setAction(new AbstractAction("Back") {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaceAlien.frame.openMainMenu();
            }
        });
        backButton.setBackground(Color.WHITE);
        backButton.setMargin(new Insets(0, 0, 0, 0));
        add(backButton);

        choosePathButton = new JButton("Choose a different file");
        choosePathButton.setFont(buttonFont);
        choosePathButton.setAction(new AbstractAction("Choose a different file") {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFilePath();
            }
        });
        choosePathButton.setBackground(Color.WHITE);
        choosePathButton.setMargin(new Insets(0, 0, 0, 0));
        add(choosePathButton);

        highscoreText = new JTextArea();
        highscoreText.setEditable(false);
        highscoreText.setFont(helpFont);
        highscoreText.setText("Test\ntest\ntest");
        add(highscoreText);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, highscoreText, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, highscoreText, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, choosePathButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, choosePathButton, 10, SpringLayout.SOUTH, highscoreText);
        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.SOUTH, choosePathButton);
        // repaint();
        try{
            setupFile();
        }catch(Exception e){
            System.out.println("Error with setting up highscore files");
        }
    }

    private void setupFile() throws Exception {
        File file = new File(System.getProperty("user.dir") + "\\highscore.txt");
        
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        FileWriter writer = new FileWriter(file);
        writer.write("Test data");
        writer.close();
    }

    private void getFilePath() {
        System.out.println(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Highscore Files", "hs");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
        }
    }
}