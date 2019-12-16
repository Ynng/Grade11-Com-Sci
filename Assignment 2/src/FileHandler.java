import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {

    public FileHandler() {

    }

    

    public static void readLeaderBoard() {
        File file = new File("/scores.txt");
        try {
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<String>();

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
                System.out.println(lines);
            }
            scanner.close();
            String[] arr = lines.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}