import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
  private static List<Quadrilateral> database = new ArrayList<>(); // database
  private static final String SEPARATOR = "----------------------------------------------------------------\n";
  private static Scanner in;
  private static final int SHAPE_COUNT = 6;

  public static void main(String[] args) {
    in = new Scanner(System.in);
    while (mainMenu())
      ;
      separatorPrint("Thanks for using this program, enter any key to exit\n");
    in.next();
  }

  static boolean mainMenu() {
    separatorPrint(
        "Please choose an option:\n1. Create a new Shape\n2. List all current stored shapes\n3. Search for a specific shape, get its info, maybe change or delete it\n4. Exit the program\n");
    switch (in.nextInt()) {
      case 1:
        Quadrilateral temp = newShape();
        separatorPrint("You just created a " + temp.toString());
        database.add(temp);
        break;
      case 4:
        return false;
    }
    return true;
  }

  static Quadrilateral newShape() {
    separatorPrint(
        "Please choose a shape:\n1. Square\n2. Rectangle\n3. Rhombus\n4. Parallelogram\n5. Kite\n6. Trapezoid\n");
    int choice = in.nextInt();
    separatorPrint("Do you want to create a:\n1. Shape with default dimensions\nor\n2. Shape with custom \n");
    choice = choice + (in.nextInt() - 1) * SHAPE_COUNT;

    double side1 = 0, side2 = 0, height = 0, diag1 = 0, diag2 = 0, top = 0, bot = 0; // Shape characteristic

    switch (choice) {
      case 1:// default square
        return new Square();
      case 2:// default rectangle
        return new Rectangle();
      case 8:// custom rectangle
        System.out.println("Please enter the length of side 2");
        side2 = in.nextDouble();
      case 7:// custom square
        System.out.println("Please enter the length of side 1");
        side1 = in.nextDouble();
    }

    switch (choice) {
      case 7:
        return new Square(side1);
      case 8:
        return new Rectangle(side1, side2);
    }

    return new Square();// Won't get executed in normal use, just to please Java by making sure this
                        // method always returns a Quadrilateral
  }

  static void separatorPrint(String content) {
    System.out.print(SEPARATOR + content);
  }

  static void separatorPrintln(String content) {
    System.out.println(SEPARATOR + content);
  }
}