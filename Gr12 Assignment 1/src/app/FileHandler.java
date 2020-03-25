package app;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;
import java.nio.file.Files;

//****************************************************************************************
//FileHandler Class
//Kevin Huang
//Date: March 14th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This class contains useful helper methods for handling files
//<List Of Identifiers>
//none
//****************************************************************************************
public class FileHandler {

  /**
   * sets the title, initial directory, initial file name and extension filter for
   * a given FileChooser object. The initial directory will be set to the H drive
   * if it exists, otherwise, it will be set to the working directory of the
   * project.
   * 
   * @param fileChooser FileChooser object to be set up.
   */
  public static void configureFileChooser(FileChooser fileChooser) {
    fileChooser.setTitle("Select Grid");
    if (new File("H:\\").exists())
      fileChooser.setInitialDirectory(new File("H:\\"));
    else
      fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setInitialFileName("assign1.txt");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
        new FileChooser.ExtensionFilter("All Files", "*.*"));
  }// end of configureFileChooser function

  /**
   * reads and generate a Grid object based on the information in a given file.
   * The file should contain a list of numbers, each in its own line. The method
   * is able to handle unexpected spaces, initial new lines and ending new lines.
   * 
   * @param file File object to read from
   * @return the grid object with the numbers from the file
   * @throws IOException if an I/O error occurs reading from the stream
   */
  public static Grid readGrid(File file) throws IOException {
    String input = new String(Files.readAllBytes(file.toPath())).trim();
    String[] stringArr = input.split("\n");
    int[] intArr = new int[stringArr.length];
    for (int i = 0; i < stringArr.length; i++)
      intArr[i] = Integer.parseInt(stringArr[i].trim());
    return new Grid(intArr);
  }// end of readGrid method
}// end of FileHandler class