package shapes;

//****************************************************************************************
//Quadrilateral Project
//Kevin Huang
//Date: May 22th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This is the kite class. It represents a kite and stores the needed information to calculate the kite's area and perimeter.
//<List Of Identifiers>
//let diag1 = the length of one diagonal of the kite <type double>
//let diag2 = the length of the other diagonal of the kite <type double>
//let side1 = the length of one pair of sides in the kite <type double>
//let side2 = the length of the other pair of sides in the kite <type double>
// let numKites = the number of kites that currently exists (haven't got deleted yet) <type int>
//****************************************************************************************
public class Kite extends Quadrilateral {
  protected double diag1 = 1;// the length of one diagonal of the kite
  protected double diag2 = 1;// the length of the other diagonal of the kite
  protected double side1 = 1;// the length of one pair of sides in the kite
  protected double side2 = 1;// the length of the other pair of sides in the kite

  private static int numKites = 0;// the number of kites that currently exists (haven't got deleted yet)

  /**
   * Default constructor method, sets up the kite with arbitrary dimensions,
   * increments numKites, and sets the key, only if the shape being created is
   * actually a kite, not a subclass of kite.
   */
  public Kite() {
    if (getClass() == Kite.class) {
      diag1 = 8;
      diag2 = 10;
      side1 = 5;
      side2 = Math.sqrt(7 * 7 + 4 * 4);
      numKites++;
      //The line below is not used. The generation of keys is now moved into the Quadrilateral class
      //key = getShapeName() + numKites;
    }
  }// end of the default constructor

  /**
   * Overloaded constructor method, calls the default constructor first, and then
   * sets the dimensions according to arguments.
   * 
   * @param diag1 the length of one diagonal of the kite
   * @param diag2 the length of the other diagonal of the kite
   * @param side1 the length of one pair of sides in the kite
   * @param side2 the length of the other pair of sides in the kite
   */
  public Kite(double diag1, double diag2, double side1, double side2) {
    this();
    this.diag1 = diag1;
    this.diag2 = diag2;
    this.side1 = side1;
    this.side2 = side2;
  }// end of an overloaded constructor

  @Override
  public double findArea() {
    return (diag1 * diag2) / 2;
  }

  @Override
  public double findPerimeter() {
    return (side1 + side2) * 2;
  }

  /**
   * assessor method of the length of a diagonal of the kite
   * 
   * @return the length of a diagonal of the kite
   */
  public double getDiag1() {
    return diag1;
  }// end of getDiag1 method

  /**
   * mutator method of the length of a diagonal of the kite
   * 
   * @param diag1 the new a length of diagonal of the kite
   */
  public void setDiag1(double diag1) {
    this.diag1 = diag1;
  }

  /**
   * assessor method of the length of the other diagonal of the kite
   * 
   * @return the length of the other diagonal of the kite
   */
  public double getDiag2() {
    return diag2;
  }// end of getDiag2 method

  /**
   * mutator method of the length of the other diagonal of the kite
   * 
   * @param diag2 the new length of the other diagonal of the kite
   */
  public void setDiag2(double diag2) {
    this.diag2 = diag2;
  }// end of setDiag2 method

  /**
   * assessor method of the length of one pair of sides of the kite
   * 
   * @return the length of one pair of sides of the kite
   */
  public double getSide1() {
    return side1;
  }// end of getSide1 method

  /**
   * mutator method of the length of one pair of sides of the kite
   * 
   * @param side1 the new length of one pair of sides of the kite
   */
  public void setSide1(double side1) {
    this.side1 = side1;
  }

  /**
   * assessor method of the length of the other pair of sides of the kite
   * 
   * @return the length of the other pair of sides of the kite
   */
  public double getSide2() {
    return side2;
  }// end of getSide2 method

  /**
   * mutator method of the length of the other pair of sides of the kite
   * 
   * @param side2 the new length of the other pair of sides of the kite
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
        "[%s] - Diagonal 1: %.2f | Diagonal 2: %.2f | Side 1: %.2f | Side 2: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f",
        getShapeName(), diag1, diag2, side1, side2, key, findArea(), findPerimeter());
  }// end of toString method

  /**
   * When a kite is removed from the database, the Driver class should call this method to decrement the appropriate counters
   */
  public static void removeKite(){
    removeQuadrilateral();
    numKites--;
  }//end of removeKite method

  // @Override
  // public boolean equals(Object o){
  //   if(!(o instanceof Kite))return false;
  //   Kite quad = (Kite)o;
  //   if(diag1!=quad.getDiag1())return false;
  //   if(diag2!=quad.getDiag2())return false;
  //   if(side1!=quad.getSide1())return false;
  //   if(side2!=quad.getSide2())return false;
  // }

  /**
   * assessor method of the number of kites that currently exists (haven't got deleted yet)
   * 
   * @return the number of kites that currently exists (haven't got deleted yet)
   */
  public static int getNumKites() {
    return numKites;
  }//end of getNumKites method

}//end of the Kite class