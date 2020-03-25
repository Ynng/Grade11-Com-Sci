package app;

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

//****************************************************************************************
//Main Class
//Kevin Huang
//Date: March 14th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the main class of application and extends the Application class from the javafx library. 
//This class houses the main function and handles most of the user interface.
//<List Of Identifiers>
//let solver = the solver object that we will be using to find a path through the grid <type Solver>
//let DESC_TEXT = the string that will be displayed in the GUI as description for the program <type String>
//let TITLE_STYLE = the styling for the title <type String>
//let DESC_STYLE = the styling for the description <type String>
//let BTN_STYLE = the styling for the button <type String>
//let POP_UP_STYLE = the styling for the text in the popup <type String>
//****************************************************************************************

public class Main extends Application {

  private Solver solver = new Solver();
  private final String DESC_TEXT = "This program will take a N × N grid (N∈ℕ)(unstable on N>25) in .txt format";// TODO
  private final String TITLE_STYLE = "-fx-font-weight: bold; -fx-font-size: 3em; -fx-padding: 1em 1em 1em 1em";
  private final String DESC_STYLE = "-fx-font-size: 1.3em; -fx-padding: 1em 1em 3em 1em";
  private final String BTN_STYLE = "-fx-font-size: 2em;";
  private final String POP_UP_STYLE = "-fx-font-size: 3em; -fx-padding: 1em 1em 1em 1em;";

  /**
   * The main entry point for all JavaFX applications. The start method is called
   * after the init method has returned, and after the system is ready for the
   * application to begin running.
   * <p>
   * This method is called on the JavaFX Application Thread.
   * <p>
   * Initializes and sets up the main window of the GUI, also sets up a
   * FileChooser using FileHandler.
   *
   * @param primaryStage the primary stage for this application, onto which the
   *                     application scene can be set. The primary stage will be
   *                     embedded in the browser if the application was launched
   *                     as an applet. Applications may create other stages, if
   *                     needed, but they will not be primary stages and will not
   *                     be embedded in the browser.
   * @throws Exception if an error occurs
   */
  @Override
  public void start(Stage stage) throws Exception {

    final FileChooser fileChooser = new FileChooser();
    FileHandler.configureFileChooser(fileChooser);

    final Label title = new Label("Assignment #1");
    title.setStyle(TITLE_STYLE);

    final Label description = new Label(DESC_TEXT);
    description.setStyle(DESC_STYLE);

    final Button openButton = new Button("Open a Grid...");
    openButton.setStyle(BTN_STYLE);
    openButton.setOnAction(new EventHandler<ActionEvent>() {
      /**
       * Invoked when a specific event of the type for which this handler is
       * registered happens.
       * <p>
       * Opens the file chooser dialog, parses the file, tries to find a path and than
       * displays the result in a popup window
       * <p>
       * Has basic error handling ability
       * 
       * @param e the event which occurred
       */
      @Override
      public void handle(final ActionEvent e) {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          try {
            solver.setGrid(FileHandler.readGrid(file));
            boolean result = solver.solveGrid();
            popUpResult(result ? "A path exists!" : "No path exist!", !result, stage);
          } catch (Exception ex) {
            popUpResult("Error parsing input", true, stage);
          }
        }
      }// end of handle method
    });

    final VBox body = new VBox(12);
    body.setAlignment(Pos.CENTER);

    body.getChildren().addAll(title, description, openButton);
    body.setPadding(new Insets(16, 16, 16, 16));
    stage.setScene(new Scene(body));
    stage.show();
  }// end of start method

  /**
   * Displays a popup window with the given text in either green or red depending
   * on the "error" value
   * 
   * @param text         - the text to be displayed in the popup window
   * @param error        - wether to use the color red for the text instead of
   *                     green to indicate an error
   * @param primaryStage - the parent stage of the popup
   */
  public void popUpResult(String text, boolean error, Stage primaryStage) {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(primaryStage);
    VBox popUpVBox = new VBox(20);
    popUpVBox.setAlignment(Pos.CENTER);
    Scene dialogScene = new Scene(popUpVBox, 400, 200);

    Label label = new Label(text);
    label.setStyle(POP_UP_STYLE + " -fx-text-fill:" + (error ? "darkred" : "darkgreen"));

    popUpVBox.getChildren().add(label);
    dialog.setScene(dialogScene);
    dialog.show();
  }// end of popUpResult method

  /**
   * main method of the application, launches the javafx application.
   * 
   * @param args - The command line arguments that would be passed to the java
   *             program.
   */
  public static void main(String[] args) {
    launch();
  }// end of main method
}//end of Main class