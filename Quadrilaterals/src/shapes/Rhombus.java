package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the rhombus class. It represents a rhombus and stores the needed information to calculate the rhombus's area and perimeter.
//<List Of Identifiers>
//let height = the length of the height of the rhombus <type double>
// let numRhombuses = the number of rhombuses that have been created <type int>
//****************************************************************************************
public class Rhombus extends Square {
  protected double height = 1;// the length of the height of the rhombus
  private static int numRhombuses = 0;// the number of rhombuses that have been created

  /**
   * Default constructor method, sets up the rhombus with arbitrary dimensions,
   * increments numRhombuses, and sets the key, only if the shape being created is
   * actually a rhombus, not a subclass of rhombus.
   */
  public Rhombus() {
    if (getClass() == Rhombus.class) {
      numRhombuses++;
      key = getShapeName() + numRhombuses;
    }
  }// end of default constructor

  /**
   * Overloaded constructor method, calls the default constructor first, and then
   * sets the dimensions according to arguments.
   * 
   * @param base   the length of the base of the rhombus
   * @param height the length of the height of the rhombus
   */
  public Rhombus(double base, double height) {
    this();
    this.base = base;
    this.height = height;
  }// end of an overloaded constructor

  @Override
  public double findArea() {
    return base * height;
  }

  /**
   * assessor method of the length of the height of the rhombus
   * 
   * @return the length of the height of the rhombus
   */
  public double getHeight() {
    return height;
  }// end of getHeight method

  /**
   * mutator method of the length of the height of the rhombus
   * 
   * @param base the new length of the height of the rhombus
   */
  public void setHeight(double height) {
    this.height = height;
  }// end of setHeight method

  /**
   * Returns a string representation of the object.
   * 
   * @return a string containing useful information about the shape
   */
  public String toString() {
    return String.format("[%s] - Base: %.2f | Height: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f",
        getShapeName(), base, height, key, findArea(), findPerimeter());
  }// end of toString method


  /**
   * When a rhombus is removed from the database, the Driver class should call this method to decrement the appropriate counters
   */
  public static void removeRhombus(){
    removeQuadrilateral();
    numRhombuses--;
  }//end of removeRhombus method

  /**
   * assessor method of the number of rhombuses that have been created
   * 
   * @return the number of rhombuses that have been created
   */
  public static int getNumRhombuses() {
    return numRhombuses;
  }//end of getNumRhombuses method

}//end of the Rhombus class