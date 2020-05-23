package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the trapezoid class. It represents a trapezoid and stores the needed information to calculate the trapezoid's area and perimeter.
//<List Of Identifiers>
//let top = the length of the top side of the trapezoid <type double>
//let side2 = the length of the right side of the trapezoid <type double>
// let count = the number of trapezoids that have been created <type int>
//****************************************************************************************
public class Trapezoid extends Parallelogram {
  protected double top = 1;// the length of the top side of the trapezoid
  protected double side2 = 1;// the length of the right side of the trapezoid
  private static int count = 0;// the number of trapezoids that have been created

  /**
   * Default constructor method, sets up the trapezoid with arbitrary dimensions,
   * increments count, and sets the key, only if the shape being created is
   * actually a trapezoid, not a subclass of trapezoid.
   */
  public Trapezoid() {
    if (getClass() == Trapezoid.class) {
      base = 6;
      top = 2;
      side = 3;
      side2 = 5;
      height = 3;

      count++;
      key = getShapeName() + count;
    }
  }// end of the default constructor

  /**
   * Overloaded constructor method, calls the default constructor first, and then
   * sets the dimensions according to arguments.
   * 
   * @param base   the length of the base of the trapezoid
   * @param top    the length of the top of the trapezoid
   * @param height the height of the trapezoid
   * @param side   the length of the left side of the trapezoid
   * @param side2  the length of the right side of the trapezoid
   */
  public Trapezoid(double base, double top, double height, double side, double side2) {
    this();
    this.base = base;
    this.top = top;
    this.height = height;
    this.side = side;
    this.side2 = side2;
  }// end of an overloaded constructor

  @Override
  double findArea() {
    return ((base + top) / 2) * height;
  }

  @Override
  double findPerimeter() {
    return base + top + side + side2;
  }

  /**
   * assessor method of top side of the trapezoid
   * 
   * @return the length of the top side of the trapezoid
   */
  public double getTop() {
    return top;
  }// end of getTop method

  /**
   * mutator method of top side of the trapezoid
   * 
   * @param top the new length of the top side to set
   */
  public void setTop(double top) {
    this.top = top;
  }// end of setTop method

  /**
   * assessor method of the right side of the trapezoid
   * 
   * @return the length of the right side of the trapezoid
   */
  public double getSide2() {
    return side2;
  }// end of getSide2 method

  /**
   * mutator method of the right side of the trapezoid
   * 
   * @param side2 the new length of the right side to set
   */
  public void setSide2(double side2) {
    this.side2 = side2;
  }// end of setSide2 method

  /**
   * Returns a string representation of the object.
   * 
   * @return a string containing useful information about the shape
   */
  public String toString() {
    return String.format(
        "[%s] - Base: %.2f | Top: %.2f | Height: %.2f | Side1: %.2f | Side2: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f",
        getShapeName(), base, top, height, side, side2, key, findArea(), findPerimeter());
  }// end of toString method

  /**
   * assessor method of the number of trapezoids that have been created
   * 
   * @return the number of trapezoids that have been created
   */
  public static int getCount() {
    return count;
  }// end of getCount method

}// end of the Trapezoid class