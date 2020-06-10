package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//OpenJDK 14.0.1, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the Quadrilateral class. It contains common data fields and operations from all quadrilateral classes.
//<List Of Identifiers>
// let numQuadrilaterals = the total number of quadrilateral that currently exists (haven't got deleted yet) <type int>
// let keyCounter = the total number of quadrilateral that got created <type int>
// let key = the unique key of the quadrilateral <type String>
//****************************************************************************************
public abstract class Quadrilateral {
  private static int numQuadrilaterals;// the total number of quadrilateral that currently exists (haven't got deleted yet)
  private static int keyCounter;// similar to numQuadrilaterals, except it never counts down, this way, even if
                                // removeQuadrilateral() is called mistakenly, and the shape is not removed, the
                                // keys would still be unique.
  protected String key;// the unique key of the quadrilateral

  /**
   * Default constructor method for the quadrilateral class. Will be called when
   * any subclass of Quadrilateral is created. Increments the counter every-time a
   * new subclass of quadrilateral is created.
   */
  protected Quadrilateral() {
    numQuadrilaterals++;
    keyCounter++;
    key = Integer.toString(keyCounter);
  }// end of the default constructor

  /**
   * Overloaded constructor that allows for a custom key to be set.
   * Should only be used by the SearchTarget constructor.
   * When a search target is created, you wouldn't want it to increment the numQuadrilaterals and keyCounter counters.
   * @param key: the key to be set for this instance
   */
  protected Quadrilateral(String key){
    this.key = key;
  }// end of an overloaded constructor

  /**
   * assessor method of the total number of quadrilateral that currently exists (haven't got deleted yet)
   * 
   * @return the total number of quadrilateral that currently exists (haven't got deleted yet)
   */
  public static int getNumQuadrilaterals() {
    return numQuadrilaterals;
  }// end of getNumQuadrilaterals method

  /**
   * decreases the total number of quadrilateral that currently exists (haven't got deleted yet).
   * 
   * should be called when a subclass of Quadrilateral is removed from the
   * program.
   */
  protected static void removeQuadrilateral() {
    numQuadrilaterals--;
  }// end of removeQuadrilateral method

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
   * Indicates wether some other objects is equal to this one.
   * 
   * @param obj The object to be compared to with this one.
   * @return true if the object is the same as this one, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    // since keys are guaranteed to be unique, we only need to check for the key and
    // nothing else.

    // Checks to see if the objects is a subclass of Quadrilateral, this way we can make sure a key exist.
    if (!Quadrilateral.class.isAssignableFrom(obj.getClass()))
      return false;

    Quadrilateral quadrilateral = (Quadrilateral) obj;

    // If the keys match, everything else should also match.
    if (key.equals(quadrilateral.getKey()))
      return true;
    else
      return false;
  }// end of equals method

  /**
   * Finds the area of the shape and returns it
   * 
   * @return the area of the shape
   */
  abstract public double findArea();

  /**
   * Finds the perimeter of the shape and returns it
   * 
   * @return the perimeter of the shape
   */
  abstract public double findPerimeter();
}// end of Quadrilateral class