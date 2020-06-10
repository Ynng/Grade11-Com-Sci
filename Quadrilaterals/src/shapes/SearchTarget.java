package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//OpenJDK 14.0.1, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the SearchTarget class.
//It does nto represent any shape in particular.
//This is used to when searching for a shape based on a key.
//This class allows for the creation of a Quadrilateral subclass with a custom key, without affecting the keyCounter and numQuadrilateral counters in Quadrilateral class.
//An instance of this class can be compared to a normal Quadrilateral shape using the Quadrilateral equals() method.
//If both objects have the same key, the equals() method will return true, allowing for the shape to be found.
//****************************************************************************************
public class SearchTarget extends Quadrilateral {

  /**
   * Custom constructor that allows for a custom key to be set. Calls an
   * overloaded constructor in the Quadrilateral class explicitly, specifically
   * made to handle a custom key and not increment counters.
   * 
   * Does not increment the numQuadrilaterals and keyCounter counters.
   * 
   * @param key: the key to be set for this instance
   */
  public SearchTarget(String key) {
    super(key);
  }// end of constructor

  /**
   * Empty findArea method, because SearchTarget must implement its inherited
   * abstract methods. That includes findArea() and findPerimeter(). The
   * SearchTarget class does not represent a shape, so this method should never be
   * called.
   * 
   * @return 0
   */
  @Override
  public double findArea() {
    return 0;
  }// end of the findArea method

  /**
   * Empty findPerimeter method, because SearchTarget must implement its inherited
   * abstract methods. That includes findArea() and findPerimeter(). The
   * SearchTarget class does not represent a shape, so this method should never be
   * called.
   * 
   * @return 0
   */
  @Override
  public double findPerimeter() {
    return 0;
  }
}// end of the SearchTarget class