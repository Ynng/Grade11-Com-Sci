import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import shapes.*;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//OpenJDK 14.0.1, Visual Studio Code 1.43
//****************************************************************************************
//Problem Definition - Operate a user friendly interface to create, store, modify, delete and search for a variety of Quadrilateral shapes
//Input - User input through the console
//Output - Printing text to the user through the console
//Process - Lots and lots of conditional statements and loops. I use switch statements based on user input to determine which menu/action the user has chosen, I also use switch statements based on a shape instance to determine the correct menu to display.
// More detailed explanation can be found on the method comments.
//****************************************************************************************
//<Class>
//This is the driver/use class, it handles most of the user friendly prompts and user inputs.
//It allows shapes to be created, stored, modified, and contains the main function of the program.
//<List Of Identifiers>
//let database = the list in which the shapes are stored in. <type List<Quadrilateral>>
//let SEPARATOR = the string that looks like a horizontal straight line. Used for separating text visually when printing to the user. <type String>
// let in = the Scanner object used to take in user input. <type Scanner>
// let SHAPE_COUNT = the total amount of shapes this project contains. <type int>
//****************************************************************************************
public class Driver {
  private static List<Quadrilateral> database = new ArrayList<>(); // the list in which the shapes are stored in
  // the string that looks like a horizontal straight line. Used for separating
  // text visually when printing to the user.
  private static final String SEPARATOR = "----------------------------------------------------------------\n";
  private static Scanner in;// the Scanner object used to take in user input.
  private static final int SHAPE_COUNT = 6;// the total amount of shapes this project contains.

  /**
   * Main function of the program. Initializes the Scanner object in and calls
   * mainMenu() forever until mainMenu returns false, indicating that the user has
   * decided to quit. Than displays a thank you message before exiting the
   * program.
   * 
   * @param args - The command line arguments that would be passed to the java
   *             program.
   */
  public static void main(String[] args) {
    in = new Scanner(System.in);
    while (mainMenu())
      ;
    separatorPrint("Thanks for using this program, submit any key to exit\n");
    in.next();
  }// end of main method

  /**
   * Main menu of the program. Calls other sub-menu methods based on the user's
   * input.
   * 
   * @return wether the user wants to the program to continue.
   */
  static boolean mainMenu() {
    separatorPrint("Welcome to Object-Oriented Programming Quadrilateral Project by Kevin Huang\n");
    separatorPrint(
        "Please choose an option:\n1. Create a new Shape\n2. List all current stored shapes and get the numbers of total shapes\n3. Search for a specific shape, get its info and maybe modify it\n4. Exit the program\n",false);
    switch (in.nextInt()) {
      case 1:
        newShapeMenu();
        break;
      case 2:
        listShapeMenu();
        break;
      case 3:
        findShapeMenu();
        break;
      case 4:
        return false;
    }
    return true;
  }// end of mainMenu method

  /**
   * Handles prompting the user to enter new information about the shape
   * modification, and the functions calls to modify the shape object. This
   * function can handle all types of shapes, and will prompt differently
   * depending on the type of shape.
   * 
   * @param shape - the shape to be modified
   */
  static void modifyShape(Quadrilateral shape) {
    separatorPrintln("Please choose the dimension of the shape you wish to modify:", false);
    switch (shape.getShapeName()) {
      case "Kite":
        System.out.println("[D1]. diagonal");
        System.out.println("[D2]. the other diagonal");
        System.out.println("[S1]. side");
        System.out.println("[S2]. the other side");
        break;
      case "Trapezoid":
        System.out.println("[T]. top side");
        System.out.println("[R]. right side");
      case "Parallelogram":
        System.out.println("[L]. side (left side for trapezoid)");
      case "Rhombus":
      case "Rectangle":
        System.out.println("[H]. height");
      case "Square":
        System.out.println("[B]. base/width/bottom");
    }
    String side = in.next();
    separatorPrintln("Please enter the new value:", false);
    double input = in.nextDouble();

    switch (side) {
      case "D1":
        ((Kite) shape).setDiag1(input);
        break;
      case "D2":
        ((Kite) shape).setDiag2(input);
        break;
      case "S1":
        ((Kite) shape).setSide1(input);
        break;
      case "S2":
        ((Kite) shape).setSide2(input);
        break;
      case "T":
        ((Trapezoid) shape).setTop(input);
        break;
      case "R":
        ((Trapezoid) shape).setSide2(input);
        break;
      case "L":
        ((Parallelogram) shape).setSide(input);
        break;
      case "H":
        ((Rhombus) shape).setHeight(input);
        break;
      case "B":
        ((Square) shape).setBase(input);
        break;
    }
  }// end of modifyShape method

  /**
   * Shape searching menu of the program. Does not handle the actual searching of
   * the shape, but handles the potential modification of the shape after a shape
   * is selected.
   */
  static void findShapeMenu() {
    Quadrilateral result = findShape();
    if(result == null)return;
    separatorPrintln(String.format("Shape found:\n%s", result.toString()));
    separatorPrintln("Enter:\n1. to modify the shape\nor\n2. to go back to the main menu", false);
    if (in.next().equals("1")) {
      separatorPrintln("Shape to modify: " + result.toString());
      modifyShape(result);
      separatorPrintln("Your new shape: " + result.toString());
      separatorPrintln("Submit any text to go back to main menu", false);
      in.next();
    } else {
      return;
    }
  }// end of findShape method

  /**
   * Prompts the user to enter the key of a shape, then tries to find it by looping
   * through the database.
   * 
   * @return the shape with the matching key to the user's input
   */
  static Quadrilateral findShape() {
    String key;
    Quadrilateral result = null;
    SearchTarget searchTarget;
    boolean firstTime = true;
    do {
      separatorPrintln("Please enter the key of the shape you want to find, or enter [Q] to go back to the main menu: ", firstTime);
      firstTime = false;
      key = in.next();
      searchTarget = new SearchTarget(key);
      if (key.equals("Q"))
        break;
      for (int i = 0; i < database.size(); i++) {
        if (database.get(i).equals(searchTarget)) {
          result = database.get(i);
          break;
        }
      }

      if (result == null) {
        separatorPrintln(String.format("Did not find any shape with the key \"%s\"", key));
      }
    } while (result == null);
    return result;
  }// end of findShape method


  // Simpler version of findShape(), does not use equals(). New version that does use equals() is above
  /**
   * Prompts the user to enter the key to a shape and tries to find it by looping
   * through the database. Probably not the most efficient way but good enough for
   * a max of 20 objects. Loops forever until a shape is found.
   * 
   * @return the shape with the matching key to the user's input
   */
  // static Quadrilateral findShape() {
  //   String key;
  //   Quadrilateral result = null;
  //   boolean firstTime = true;
  //   do {
  //     separatorPrintln("(Case Sensitive!)\nPlease enter the key of the shape you want to find, or enter [Q] to go back to the main menu: ",
  //         firstTime);
  //     firstTime = false;
  //     key = in.next();
  //     if (key.equals("Q"))
  //       break;
  //     for (int i = 0; i < database.size(); i++) {
  //       if (database.get(i).getKey().equals(key)) {
  //         result = database.get(i);
  //         break;
  //       }
  //     }

  //     if (result == null) {
  //       separatorPrintln(String.format("Did not find any shape with the key \"%s\"", key));
  //     }
  //   } while (result == null);
  //   return result;
  // }

  /**
   * Shape listing menu. Lists the number of each shape independently, the number
   * of all quadrilaterals combined and than lists all the shapes currently
   * stored. Lists the shapes by simply looping through all shapes and printing it
   * out. The task is simple enough to not be split into more methods.
   */
  static void listShapeMenu() {
    separatorPrintln("List of all shapes:");
    for (int i = 0; i < database.size(); i++) {
      System.out.println(database.get(i).toString());
    }

    separatorPrintln("That's a total of :", false);
    System.out.print(Square.getNumSquares() + " Squares | ");
    System.out.print(Rectangle.getNumRectangles() + " Rectangles | ");
    System.out.print(Parallelogram.getNumParallelograms() + " Parallelograms | ");
    System.out.print(Kite.getNumKites() + " Kites | ");
    System.out.print(Trapezoid.getNumTrapezoids() + " Trapezoids | ");
    System.out.println(Rhombus.getNumRhombuses() + " Rhombuses");
    System.out.println("Which is " + Quadrilateral.getNumQuadrilaterals() + " Quadrilaterals in total");

    separatorPrintln("Submit any text to go back to main menu", false);
    in.next();
  }// end of listShapeMenu method

  /**
   * New Shapes creation menu. Does not handle the actual shape creations, but
   * handles the confirmation of the shape attributes, and offers choice of
   * creating a completely new shape or modifying the original shape.
   */
  static void newShapeMenu() {
    Quadrilateral newQuad = null;
    String input = "3";
    do {
      if(input.equals("3") && newQuad != null){
        switch (newQuad.getShapeName()) {
          case "Kite":
            Kite.removeKite();
            break;
          case "Trapezoid":
            Trapezoid.removeTrapezoid();
            break;
          case "Parallelogram":
            Parallelogram.removeParallelogram();
            break;
          case "Rhombus":
            Rhombus.removeRhombus();
            break;
          case "Rectangle":
            Rectangle.removeRectangle();
            break;
          case "Square":
            Square.removeSquare();
            break;
        }
        newQuad = null;
        //delete the previous shape
      }
      if(newQuad == null){
        newQuad = newShape();
      }
     if (input.equals("2")) {
        modifyShape(newQuad);
      }
      separatorPrintln("Your Shape: " + newQuad.toString());
      separatorPrintln(
          "Are you satisfied with this shape?\n1. Yes, bring me back to the main menu\n2. No, I want to edit this shape\n3. No, I want to make a new shape entirely",
          false);
      input = in.next();
    } while (!input.equals("1"));

    database.add(newQuad);
    separatorPrintln("Shape added to database! Submit any text to go back to main menu");
    in.next();
  }// end of newShapeMenu method

  /**
   * Creates a new shape from scratch by guiding the user through a shape creating
   * process. Asks for the type of shape to be created, offers a choice of a
   * default shape or custom shape, and than asks for length of different segments
   * of the shape accordingly.
   * 
   * @return the quadrilaterals shape that was created.
   */
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
  }// end of newShape method

  /**
   * Helper method for printing a piece of string with a separator in-front.
   * Overloaded method, allows for the default selection of 2 new lines before the
   * separator.
   * 
   * @param content the string to be printed
   */
  static void separatorPrint(String content) {
    separatorPrint(content, true);
  }// end of separatorPrint method

  /**
   * Helper method for printing a piece of string with a separator in-front.
   * 
   * @param content          the string to be printed
   * @param beginningNewLine wether or not to have 2 new lines before the
   *                         separator
   */
  static void separatorPrint(String content, boolean beginningNewLine) {
    if (beginningNewLine)
      System.out.print("\n\n\n\n\n");
    System.out.print(SEPARATOR + content);
  }// end of separatorPrint method

  /**
   * Helper method for printing a piece of string with a separator in-front and a
   * new line at the back. Overloaded method, allows for the default selection of
   * 2 new lines before the separator.
   * 
   * @param content the string to be printed
   */
  static void separatorPrintln(String content) {
    separatorPrintln(content, true);
  }// end of separatorPrintln method

  /**
   * Helper method for printing a piece of string with a separator in-front and a
   * new line at the back.
   * 
   * @param content          the string to be printed
   * @param beginningNewLine wether or not to have 2 new lines before the
   *                         separator
   */
  static void separatorPrintln(String content, boolean beginningNewLine) {
    if (beginningNewLine)
      System.out.print("\n\n\n\n\n");
    System.out.println(SEPARATOR + content);
  }// end of separatorPrintln method
}// end of Driver class