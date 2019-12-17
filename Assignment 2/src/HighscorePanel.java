
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        setupHighscoreFile();
        updateText();
    }

    /**
     * updates highscoreText to reflect the current highscoreList
     */
    private void updateText(){
        String textToBeDisplayed = "";
        try{
            highscoreText.setText(readHighscoreFile());
        }catch (Exception e) {
            System.out.println("Error with reading the highscore file");
        }
    }

    /**
     * Reads the content of a file into highscoreList
     * @throws FileNotFoundException if the input file doesn't exists
     * @throws IOException if interrupted
     */
    private void readHighscoreFile() throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(highscoreFile));
        String temp;
        highscoreList.clear();
        while ((temp = fileReader.readLine()) != null){
            try{
                highscoreList.add(Double.parseDouble(temp));
            }catch (Exception e) {
                System.out.println("Error parsing the highscore file, skipping a line");
            }
            System.out.println(temp);
        }
        fileReader.close();
    }


    private void writeHighscoreFile(){
        Collections.sort(highscoreList);
        FileWriter
    }



    /**
     * Create a highscore file at a default location in the working directory of the program if a highscore file does not exist in the default location, otherwise, reads the highscore file from the default location
     */
    private void setupHighscoreFile() {
        try {
            if (highscoreFile.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error with setting up highscore files");
        }
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
            updateText();
        }
    }
}