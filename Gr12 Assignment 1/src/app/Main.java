package app;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

  private Desktop desktop = Desktop.getDesktop();

  @Override
  public void start(Stage stage) throws Exception {

    final FileChooser fileChooser = new FileChooser();

    final Button openButton = new Button("Open a Grid...");
    openButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(final ActionEvent e) {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          openFile(file);
        }
      }
    });

    final GridPane inputGridPane = new GridPane();
    GridPane.setConstraints(openButton, 1, 0);
    inputGridPane.setHgap(6);
    inputGridPane.setVgap(6);
    inputGridPane.getChildren().add(openButton);

    final Pane rootGroup = new VBox(12);
    rootGroup.getChildren().addAll(inputGridPane);
    rootGroup.setPadding(new Insets(12, 12, 12, 12));
    stage.setScene(new Scene(rootGroup));
    stage.show();
  }

  private void openFile(File file) {
    try {
      desktop.open(file);
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void configureFileChooser(final FileChooser fileChooser) {
    fileChooser.setTitle("Select Grid");
    if(new File("T:\\").exists())
      fileChooser.setInitialDirectory(new File("T:\\"));
    else
      fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    fileChooser.getExtensionFilters().addAll(
      new FileChooser.ExtensionFilter("Text Files", "*.txt"),
      new FileChooser.ExtensionFilter("All Files", "*.*")
    );
  }

  public static void main(String[] args) {
    launch();
    // int[] array = {1,
    // 2,
    // 3,
    // 5,
    // 4,
    // 2,
    // 1,
    // 1,
    // 1
    // };
    // Grid grid = new Grid(array);
    // Solver solver = new Solver();
    // solver.setGrid(grid);
    // System.out.printf("output: %b", solver.solveGrid());
  }
}