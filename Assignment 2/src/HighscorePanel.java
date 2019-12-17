
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HighscorePanel extends JPanel {
    private JButton backButton;
    private JButton choosePathButton;

    private JTextArea highscoreText;

    private Font buttonFont = new Font("Sans-serif", Font.PLAIN, 35);
    private Font helpFont = new Font("Sans-serif", Font.PLAIN, 20);
    private SpringLayout layout;

    private File highscoreFile;

    private String textDisplayed;
    private List<Double> highscoreList = new ArrayList<Double>();

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
                getHighscoreFilePath();
            }
        });
        choosePathButton.setBackground(Color.WHITE);
        choosePathButton.setMargin(new Insets(0, 0, 0, 0));
        add(choosePathButton);

        highscoreText = new JTextArea();
        highscoreText.setEditable(false);
        highscoreText.setFont(helpFont);
        add(highscoreText);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, highscoreText, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, highscoreText, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, choosePathButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, backButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, choosePathButton, 10, SpringLayout.SOUTH, highscoreText);
        layout.putConstraint(SpringLayout.NORTH, backButton, 10, SpringLayout.SOUTH, choosePathButton);
        // repaint();

        highscoreFile = new File(System.getProperty("user.dir") + "\\highscore.highscore");
    }

    /**
     * updates highscoreText to reflect the current highscoreList
     */
    private void updateText() {
        String stringToShow = "Highscores:\n";
        for (int i = 0; i < highscoreList.size(); i++) {
            stringToShow += String.format("%d:\t%.2f\n", i, highscoreList.get(i));
        }
        try {
            highscoreText.setText(stringToShow);
        } catch (Exception e) {
            System.out.println("Error with reading the highscore file");
        }
        repaint();
    }

    /**
     * Reads the content of a file into highscoreList
     */
    private void readHighscoreFile() {
        try {// I surounded all the code in a try catch block because all the Exceptions are
             // logically very rare to happen
            BufferedReader fileReader = new BufferedReader(new FileReader(highscoreFile));
            String temp;
            highscoreList.clear();
            while ((temp = fileReader.readLine()) != null) {
                try {
                    highscoreList.add(Double.parseDouble(temp));
                } catch (Exception e) {
                    System.out.println("Error parsing the highscore file, skipping a line");
                }
                System.out.println(temp);
            }
            fileReader.close();
            writeHighscoreFile();// sort the array and sync highscoreList with highscoreFile
        } catch (Exception e) {
            System.out.println("Error with reading highscore file?!??!?!");
        }
    }

    private void writeHighscoreFile() {
        String stringToWrite = "";
        Collections.sort(highscoreList);
        Collections.reverse(highscoreList);
        for (int i = 0; i < highscoreList.size(); i++) {
            stringToWrite += highscoreList.get(i) + "\n";
        }
        FileWriter writer;
        try {
            writer = new FileWriter(highscoreFile);
            writer.write(stringToWrite);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a highscore file at the default location if it doesn't exists yet.
     */
    public void setupHighscoreFile() {
        try {
            if (highscoreFile.exists()) {
                System.out.println("File already exists.");
            } else {
                Object[] options = { "Create new file", "Choose existing file" };
                if (JOptionPane.showOptionDialog(null, "Highscore File doesnt exist", "Alien Game File Handler",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Creating file highscore.highscore under " + System.getProperty("user.dir"));
                    highscoreFile.createNewFile();
                    System.out.println("File is created!");
                } else {
                    getHighscoreFilePath();
                }
            }
            readHighscoreFile();
            updateText();
        } catch (Exception e) {
            System.out.println("Error with setting up highscore files");
        }

    }

    public void addScore(double newScore){
        highscoreList.add(newScore);
        writeHighscoreFile();
        updateText();
    }

    private void getHighscoreFilePath() {
        // System.out.println(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Highscore Files", "highscore");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
            highscoreFile = chooser.getSelectedFile();
            readHighscoreFile();
            updateText();
        }
    }
}