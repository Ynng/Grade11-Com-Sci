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
    while (mainMenu());
    separatorPrint("Thanks for using this program, enter any key to exit\n");
    in.next();
  }

  static boolean mainMenu() {
    separatorPrint(
        "Please choose an option:\n1. Create a new Shape\n2. List all current stored shapes\n3. Search for a specific shape and get its info\n4. Exit the program\n");
    switch (in.nextInt()) {
      case 1:
        newShapeMenu();
        break;
      case 2:
        listShape();
        break;
      case 3:
        searchShape();
        break;
      case 4:
        return false;
    }
    return true;
  }

  static void searchShape(){
    separatorPrintln("Please enter the key of the shape you want to find:");
    String key = in.next();
    Quadrilateral result = null;
    for(int i = 0; i < database.size(); i++){
      if(database.get(i).getKey().equals(key)){
        result = database.get(i);
        break;
      }
    }
    if(result == null){
      separatorPrintln(String.format("Did not find any shape with the key %s", key));
    }else{
      separatorPrintln(String.format("Shape found:\n%s", result.toString()));
    }
    separatorPrintln("Enter any key to go back to main menu", false);
    in.next();
  }

  static void listShape(){
    separatorPrint("");
    for(int i = 0; i < database.size(); i++){
      System.out.println(database.get(i).toString());
    }
    separatorPrintln("Enter any key to go back to main menu", false);
    in.next();
  }

  static void newShapeMenu() {
    Quadrilateral temp;
    String input;
    do{
      temp = newShape();
      separatorPrintln("Are you satisfied with this shape? Enter:\nN for no and make another shape instead\n or \nAnything else to confirm this shape and add it to the database");
      separatorPrintln("Your Shape: " + temp.toString(), false);
      input = in.next();
    }while(input.equals("N"));
    database.add(temp);
    separatorPrintln("Shape added to database! Enter any key to go back to main menu");
    in.next();
  }

  static Quadrilateral newShape() {
    separatorPrint(
        "Please choose a shape:\n1. Square\n2. Rectangle\n3. Parallelogram\n4. Kite\n5. Trapezoid\n6. Rhombus\n");
    int choice = in.nextInt();
    separatorPrint("Do you want to create a:\n1. Shape with default dimensions\nor\n2. Shape with custom dimensions\n");
    choice = choice + (in.nextInt() - 1) * SHAPE_COUNT;

    double base = 0, height = 0, side = 0, side2 = 0, diag1 = 0, diag2 = 0, top = 0; // Shape characteristic

    switch (choice) {
      case 1:// default square
        return new Square();
      case 2:// default rectangle
        return new Rectangle();
      case 3:// default parallelogram
        return new Parallelogram();
      case 4:// default kite
        return new Kite();
      case 5:// default trapezoid
        return new Trapezoid();
      case 6:// default rhombus
        return new Rhombus();
      case 10:// custom kite
        System.out.println("Please enter the length of a diagonal");
        diag1 = in.nextDouble();
        System.out.println("Please enter the length of the other diagonal");
        diag2 = in.nextDouble();
        System.out.println("Please enter the length of a side");
        side = in.nextDouble();
        System.out.println("Please enter the length of the other side");
        side2 = in.nextDouble();
        break;
      case 11:// custom trapezoid
        System.out.println("Please enter the length of the top side");
        top = in.nextDouble();
        System.out.println("Please enter the length of the right side");
        side2 = in.nextDouble();
      case 9:// custom parallelogram
        System.out.println("Please enter the length of the side (length of left side for trapezoid)");
        side = in.nextDouble();
      case 12:// custom rhombus
      case 8:// custom rectangle
        System.out.println("Please enter the length of the height");
        height = in.nextDouble();
      case 7:// custom square
        System.out.println("Please enter the length of the base/width/bottom side");
        base = in.nextDouble();
    }

    switch (choice) {
      case 7:
        return new Square(base);
      case 8:
        return new Rectangle(base, height);
      case 9:
        return new Parallelogram(base, height, side);
      case 10:
        return new Kite(diag1, diag2, side, side2);
      case 11:
        return new Trapezoid(base, top, height, side, side2);
      case 12:
        return new Rhombus(base, height);
    }

    return new Square();// Won't get executed in normal use, just to please Java by making sure this
                        // method always returns a Quadrilateral
  }

  static void separatorPrint(String content) {
    separatorPrint(content, true);
  }

  static void separatorPrint(String content, boolean beginningNewLine) {
    if(beginningNewLine)System.out.print("\n\n");
    System.out.print(SEPARATOR + content);
  }

  static void separatorPrintln(String content) {
    separatorPrintln(content, true);
  }

  static void separatorPrintln(String content, boolean beginningNewLine) {
    if(beginningNewLine)System.out.print("\n\n");
    System.out.println(SEPARATOR + content);
  }
}