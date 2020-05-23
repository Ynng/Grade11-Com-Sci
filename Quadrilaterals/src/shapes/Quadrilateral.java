package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the Quadrilateral class. It contains common data fields and operations from all quadrilateral classes.
//<List Of Identifiers>
// let count = the total number of quadrilateral that have been created <type int>
// let key = the unique key of the quadrilateral <type String>
//****************************************************************************************
public abstract class Quadrilateral {
  private static int count;// the total number of quadrilateral that have been created
  protected String key;// the unique key of the quadrilateral

  /**
   * Default constructor method for the quadrilateral class. Will be called when
   * any subclass of Quadrilateral is created. Increments the counter every-time a
   * new subclass of quadrilateral is created.
   */
  protected Quadrilateral() {
    count += 1;
  }// end of the default constructor

  /**
   * assessor method of the total number of quadrilateral that have been created
   * 
   * @return the total number of quadrilateral that have been created
   */
  public static int getCount() {
    return count;
  }// end of getCount method

  /**
   * Gets the name of the shape an instance of Quadrilateral's subclass is
   * representing
   * 
   * @return the name of the shape an instance of Quadrilateral's subclass is
   *         representing
   */
  public String getShapeName() {
    return getClass().getSimpleName();
  }// end of getShapeName method

  /**
   * assessor method of the unique key of the quadrilateral
   * 
   * @return the unique key of the quadrilateral
   */
  public String getKey() {
    return key;
  }// end of getKey method

  /**
   * Finds the area of the shape and returns it
   * 
   * @return the area of the shape
   */
  abstract double findArea();

  /**
   * Finds the perimeter of the shape and returns it
   * 
   * @return the perimeter of the shape
   */
  abstract double findPerimeter();
}// end of Quadrilateral class