package app;

import java.awt.Desktop;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

  private Desktop desktop = Desktop.getDesktop();
  private FileHandler fileHandler = new FileHandler();

  @Override
  public void start(Stage stage) throws Exception {

    final FileChooser fileChooser = new FileChooser();

    final Label label = new Label("Assignment #1");
    label.setStyle("-fx-font-weight: bold; -fx-font-size: 3em; -fx-padding: 1em 1em 3em 1em");

    final Button openButton = new Button("Open a Grid...");
    openButton.setStyle("-fx-font-size: 2em;");
    openButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(final ActionEvent e) {
        FileHandler.configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          popUpResult(solveForResult(fileHandler.readFile(file)), stage);
        }
      }
    });

    final VBox body = new VBox(12);
    body.setAlignment(Pos.CENTER);

    body.getChildren().addAll(label, openButton);
    body.setPadding(new Insets(16, 16, 16, 16));
    stage.setScene(new Scene(body));
    stage.show();
  }

  public void popUpResult(boolean result, Stage primaryStage) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox popUpVBox = new VBox(20);
    popUpVBox.setAlignment(Pos.CENTER);
    Scene dialogScene = new Scene(popUpVBox, 300, 200);

    Label label = new Label(result?"A path exists!" : "No path exists!");
    label.setStyle("-fx-font-size: 3em; -fx-padding: 1em 1em 1em 1em; -fx-text-fill:" + (result?"darkgreen":"darkred"));

    popUpVBox.getChildren().add(label);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  private boolean solveForResult(String input){
    String[] stringArr = input.split("\n");

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