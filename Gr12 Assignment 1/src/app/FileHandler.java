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
    if(new File("T:\\").exists())
      fileChooser.setInitialDirectory(new File("T:\\"));
    else
      fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

    fileChooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter("Text Files", "*.txt"),
      new FileChooser.ExtensionFilter("All Files", "*.*")
    );
  }

  public void openFile(File file) {
    try {
      fileContent = new String(Files.readAllBytes(file.toPath()));
      System.out.print(fileContent);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}