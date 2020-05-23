package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the square class. It represents a square and stores the needed information to calculate the square's area and perimeter.
//<List Of Identifiers>
//let base = the side length of the square <type double>
//let count = the number of squares that have been created <type int>
//****************************************************************************************
public class Square extends Quadrilateral {
  protected double base = 1;// the side length of the square
  private static int count = 0;// the number of squares that have been created

  /**
   * Default constructor method, sets up the square with arbitrary dimensions,
   * increments count, and sets the key, only if the shape being created is
   * actually a square, not a subclass of square.
   */
  public Square() {
    if (getClass() == Square.class) {
      count++;
      key = getShapeName() + count;
    }
  }// end of default constructor

  /**
   * Overloaded constructor method, calls the default constructor first, and then
   * sets the side length according to arguments.
   * 
   * @param base the side length of the square
   */
  public Square(double base) {
    this();
    this.base = base;
  }// end of an overloaded constructor

  @Override
  double findArea() {
    return base * base;
  }

  @Override
  double findPerimeter() {
    return base * 4;
  }

  /**
   * assessor method of the side length of the square
   * 
   * @return the side length of the square
   */
  public double getBase() {
    return base;
  }// end of getBase method

  /**
   * mutator method of the side length of the square
   * 
   * @param base the new side length of the square
   */
  public void setBase(double base) {
    this.base = base;
  }// end of setBase method

  /**
   * Returns a string representation of the object.
   * 
   * @return a string containing useful information about the shape
   */
  public String toString() {
    return String.format("[%s] - Width: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f", getShapeName(), base, key,
        findArea(), findPerimeter());
  }// end of toString method

  /**
   * assessor method of the number of squares that have been created
   * 
   * @return the number of squares that have been created
   */
  public static int getCount() {
    return count;
  }// end of getCount method

}// end of the Square class