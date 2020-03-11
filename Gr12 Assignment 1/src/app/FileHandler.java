package app;

import java.io.File;
import java.io.IOException;

import javafx.stage.FileChooser;
import java.nio.file.Files;

/**
 * FileHandler
 */
public class FileHandler{
  String fileContent;

  public static void configureFileChooser(final FileChooser fileChooser) {
    fileChooser.setTitle("Select Grid");
    if(new File("H:\\").exists())
      fileChooser.setInitialDirectory(new File("H:\\"));
    else
      fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setInitialFileName("assign1.txt");
    fileChooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter("Text Files", "*.txt"),
      new FileChooser.ExtensionFilter("All Files", "*.*")
    );
  }

  public String readFile(File file) {
    try {
      return new String(Files.readAllBytes(file.toPath()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}