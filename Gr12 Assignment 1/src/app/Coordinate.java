package app;

//****************************************************************************************
//Coordinate Class
//Kevin Huang
//Date: March 14th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This class stores an integer 2d coordinate and contains a helper method for "moving" the coordinate in a certain direction for a certain distance.
//<List Of Identifiers>
//let row = the "row" value of the coordinate <type int>
//let col = the "column" value of the coordinate <type int>
//****************************************************************************************
public class Coordinate {
  private int row, col;

  /**
   * Default constructor method which sets the row and column values to specific values
   * 
   * @param row the row value
   * @param col the column value
   */
  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }// end of Coordinate (Default Constructor)

  /**
   * accessor method for the row value
   * 
   * @return the row value
   */
  public int getRow() {
    return row;
  }// end of getRow method

  /**
   * accessor method for the column value
   * 
   * @return the column value
   */
  public int getCol() {
    return col;
  }// end of getCol method

  /**
   * returns a nicely formatted string with the row and the column value of the
   * coordinate, useful for debugging purposes
   * 
   * @return a nicely formatted string with the row and the column value of the
   *         coordinate
   */
  public String toString() {
    return String.format("row:%d, col:%d", row, col);
  }// end of toString method

  /**
   * returns a new coordinate object, which is a given distance away from the
   * original coordinate in a given direction, as if you "moved" the given
   * distance in the given direction from the current coordinate.
   * <p>
   * useful because this sort of "moving" is in the center of the problem in the
   * assignment, and this action needs to be performed a lot.
   * 
   * @param dir  Direction object containing the direction to move in
   * @param dist the distance to move in the given direction
   * @return the new coordinate after moving in the given direction with the given
   *         distance from the current coordinate
   */
  public Coordinate move(Direction dir, int dist) {
    switch (dir) {
      case LEFT:
        return new Coordinate(row - dist, col);
      case RIGHT:
        return new Coordinate(row + dist, col);
      case UP:
        return new Coordinate(row, col + dist);
      default:
        return new Coordinate(row, col - dist);
    }
  }// end of move method
}// end of Coordinate class