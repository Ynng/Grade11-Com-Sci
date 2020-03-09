package app;

import java.awt.Desktop;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

  private Desktop desktop = Desktop.getDesktop();
  private FileHandler fileHandler;
  @Override
  public void start(Stage stage) throws Exception {

    final FileChooser fileChooser = new FileChooser();

    final Button openButton = new Button("Open a Grid...");
    openButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(final ActionEvent e) {
        FileHandler.configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          fileHandler.openFile(file);
        }
      }
    });

    Label label = new Label("Assignment #1");

    final GridPane gridPane = new GridPane();
    GridPane.setConstraints(openButton, 0, 1);
    gridPane.setHgap(6);
    gridPane.setVgap(6);
    gridPane.getChildren().addAll(openButton,label);


    final Pane rootGroup = new VBox(12);
    rootGroup.getChildren().addAll(gridPane);
    rootGroup.setPadding(new Insets(12, 12, 12, 12));
    stage.setScene(new Scene(rootGroup, 400,400));
    stage.show();
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