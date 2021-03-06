package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//OpenJDK 14.0.1, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the square class. It represents a square and stores the needed information to calculate the square's area and perimeter.
//<List Of Identifiers>
//let base = the side length of the square <type double>
//let numSquares = the number of squares that currently exists (haven't got deleted yet) <type int>
//****************************************************************************************
public class Square extends Quadrilateral {
  protected double base = 1;// the side length of the square
  private static int numSquares = 0;// the number of squares that currently exists (haven't got deleted yet)

  /**
   * Default constructor method, sets up the square with arbitrary dimensions,
   * increments numSquares, and sets the key, only if the shape being created is
   * actually a square, not a subclass of square.
   */
  public Square() {
    if (getClass() == Square.class) {
      numSquares++;
      //The line below is not used. The generation of keys is now moved into the Quadrilateral class
      //key = getShapeName() + numSquares;
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
  public double findArea() {
    return base * base;
  }

  @Override
  public double findPerimeter() {
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
    return String.format("[%s] - Width: [%.2f] | Key: %s\n\t\t| Area : [%.2f] | Perimeter: [%.2f]", getShapeName(), base, key,
        findArea(), findPerimeter());
  }// end of toString method

  /**
   * When a square is removed from the database, the Driver class should call this method to decrement the appropriate counters
   */
  public static void removeSquare(){
    removeQuadrilateral();
    numSquares--;
  }//end of removeSquare method

  /**
   * assessor method of the number of squares that currently exists (haven't got deleted yet)
   * 
   * @return the number of squares that currently exists (haven't got deleted yet)
   */
  public static int getNumSquares() {
    return numSquares;
  }// end of getNumSquares method

}// end of the Square class