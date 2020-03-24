package app;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;
import java.nio.file.Files;

/**
 * FileHandler
 */
public class FileHandler {
  String fileContent;

  public static void configureFileChooser(final FileChooser fileChooser) {
    fileChooser.setTitle("Select Grid");
    if (new File("H:\\").exists())
      fileChooser.setInitialDirectory(new File("H:\\"));
    else
      fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setInitialFileName("assign1.txt");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
        new FileChooser.ExtensionFilter("All Files", "*.*"));
  }

  public Grid readGrid(File file) throws IOException {
    String input = new String(Files.readAllBytes(file.toPath())).trim();
    String[] stringArr = input.split("\n");
    int[] intArr = new int[stringArr.length];
    for (int i = 0; i < stringArr.length; i++)
      intArr[i] = Integer.parseInt(stringArr[i].trim());
    return new Grid(intArr);
  }
}