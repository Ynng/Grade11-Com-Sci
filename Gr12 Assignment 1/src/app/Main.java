package app;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application{

  @Override
  public void start(Stage stage) throws Exception {
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    Scene scene = new Scene(new StackPane(l), 640, 480);
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) {
    launch();
    // int[] array = {1,
    //   2,
    //   3,
    //   5,
    //   4,
    //   2,
    //   1,
    //   1,
    //   1
    //   };
    // Grid grid = new Grid(array);
    // Solver solver = new Solver();
    // solver.setGrid(grid);
    // System.out.printf("output: %b", solver.solveGrid());
  }
}