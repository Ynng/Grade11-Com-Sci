package app;

import java.io.File;
import java.io.IOException;

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

  private FileHandler fileHandler = new FileHandler();
  private Solver solver = new Solver();
  private final String DESC_TEXT = "This program will take a N × N grid (1<=N, N∈I)(unstable on N>25) in .txt format";

  @Override
  public void start(Stage stage) throws Exception {

    final FileChooser fileChooser = new FileChooser();
    FileHandler.configureFileChooser(fileChooser);

    final Label label = new Label("Assignment #1");
    label.setStyle("-fx-font-weight: bold; -fx-font-size: 3em; -fx-padding: 1em 1em 1em 1em");

    final Label description = new Label(DESC_TEXT);
    description.setStyle("-fx-font-size: 1.3em; -fx-padding: 1em 1em 3em 1em");

    final Button openButton = new Button("Open a Grid...");
    openButton.setStyle("-fx-font-size: 2em;");
    openButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(final ActionEvent e) {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          try {
            boolean result = solveForResult(fileHandler.readGrid(file));
            popUpResult(result ? "A path exists!" : "No path exist!", !result, stage);
          } catch (Exception ex) {
            popUpResult("Error parsing input", true, stage);
          }
        }
      }
    });

    final VBox body = new VBox(12);
    body.setAlignment(Pos.CENTER);

    body.getChildren().addAll(label, description, openButton);
    body.setPadding(new Insets(16, 16, 16, 16));
    stage.setScene(new Scene(body));
    stage.show();
  }

  public void popUpResult(String text, boolean error, Stage primaryStage) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox popUpVBox = new VBox(20);
    popUpVBox.setAlignment(Pos.CENTER);
    Scene dialogScene = new Scene(popUpVBox, 400, 200);

    Label label = new Label(text);
    label.setStyle(
        "-fx-font-size: 3em; -fx-padding: 1em 1em 1em 1em; -fx-text-fill:" + (error ? "darkred" : "darkgreen"));

    popUpVBox.getChildren().add(label);
    dialog.setScene(dialogScene);
    dialog.show();
  }

  private boolean solveForResult(Grid input) {
    solver.setGrid(input);
    return solver.solveGrid();
  }

  public static void main(String[] args) {
    launch();
  }
}