package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//OpenJDK 14.0.1, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the parallelogram class. It represents a parallelogram and stores the needed information to calculate the parallelogram's area and perimeter.
//<List Of Identifiers>
//let side = the length of the side of the parallelogram <type double>
// let numParallelograms = the number of parallelograms that currently exists (haven't got deleted yet) <type int>
//****************************************************************************************
public class Parallelogram extends Rectangle {
  protected double side = 1;// the length of the side of the parallelogram
  private static int numParallelograms = 0;// the number of parallelograms that currently exists (haven't got deleted yet)

  /**
   * Default constructor method, sets up the parallelogram with arbitrary 
   * dimensions, increments numParallelograms, and sets the key, only if the shape being created
   * is actually a parallelogram, not a subclass of parallelogram.
   */
  public Parallelogram() {
    if (getClass() == Parallelogram.class) {
      numParallelograms++;
      //The line below is not used. The generation of keys is now moved into the Quadrilateral class
      //key = getShapeName() + numParallelograms;
    }
  }// end of default constructor

  /**
   * Overloaded constructor method, calls the default constructor first, and then
   * sets the dimensions according to arguments.
   * 
   * @param base   the length of the base of the parallelogram
   * @param height the length of the height of the parallelogram
   * @param side   the length of the side of the parallelogram
   */
  public Parallelogram(double base, double height, double side) {
    this();
    this.base = base;
    this.height = height;
    this.side = side;
  }// end of an overloaded constructor

  @Override
  public double findArea() {
    return base * height;
  }

  @Override
  public double findPerimeter() {
    return (base + side) * 2;
  }

  /**
   * assessor method of the length of the side of the parallelogram
   * 
   * @return the length of the side of the parallelogram
   */
  public double getSide() {
    return side;
  }// end of getHeight method

  /**
   * mutator method of the length of the side of the parallelogram
   * 
   * @param side the new length of the side of the parallelogram
   */
  public void setSide(double side) {
    this.side = side;
  }// end of setHeight method

  /**
   * Returns a string representation of the object.
   * 
   * @return a string containing useful information about the shape
   */
  public String toString() {
    return String.format("[%s] - Base: %.2f | Height: %.2f | Side: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f",
        getShapeName(), base, height, side, key, findArea(), findPerimeter());
  }// end of toString method
  
  /**
   * When a parallelogram is removed from the database, the Driver class should call this method to decrement the appropriate counters
   */
  public static void removeParallelogram(){
    removeQuadrilateral();
    numParallelograms--;
  }//end of parallelogram method

  /**
   * assessor method of the number of parallelograms that currently exists (haven't got deleted yet)
   * 
   * @return the number of parallelograms that currently exists (haven't got deleted yet)
   */
  public static int getNumParallelograms() {
    return numParallelograms;
  }// end of getNumParallelograms method

}// end of the Rhombus class