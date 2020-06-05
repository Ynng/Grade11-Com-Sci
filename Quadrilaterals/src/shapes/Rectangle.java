package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the rectangle class. It represents a rectangle and stores the needed information to calculate the rectangle's area and perimeter.
//<List Of Identifiers>
//let numRectangles = the number of rectangles that have been created <type int>
//****************************************************************************************
public class Rectangle extends Rhombus {
  private static int numRectangles = 0;// the number of rectangles that have been created

  /**
   * Default constructor method, sets up the rectangle with arbitrary dimensions,
   * increments numRectangles, and sets the key, only if the shape being created is
   * actually a rectangle, not a subclass of rectangle.
   */
  public Rectangle() {
    if (getClass() == Rectangle.class) {
      numRectangles++;
      key = getShapeName() + numRectangles;
    }
  }// end of default constructor

  /**
   * Overloaded constructor method, calls the default constructor first, and then
   * sets the dimensions according to arguments.
   * 
   * @param base   the length of the base of the rectangle
   * @param height the length of the height of the rectangle
   */
  public Rectangle(double base, double height) {
    this();
    this.base = base;
    this.height = height;
  }// end of an overloaded constructor

  @Override
  double findPerimeter() {
    return (base + height) * 2;
  }

  /**
   * When a rectangle is removed from the database, the Driver class should call this method to decrement the appropriate counters
   */
  public static void removeRectangle(){
    removeQuadrilateral();
    numRectangles--;
  }//end of removeRectangle method

  /**
   * assessor method of the number of rectangles that have been created
   * 
   * @return the number of rectangles that have been created
   */
  public static int getNumRectangles() {
    return numRectangles;
  }// end of getNumRectangles method

}// end of the Rectangle class