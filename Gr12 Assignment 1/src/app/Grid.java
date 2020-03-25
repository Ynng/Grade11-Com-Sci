package app;

//****************************************************************************************
//Grid Class
//Kevin Huang
//Date: March 14th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This class stores a square shaped (same number of rows as columns) 2d array of integer numbers and provides a few helper methods for working in this grid system. 
//It also provides a few overloaded constructors for easily creating grid object from integer 1d/2d array 
//<List Of Identifiers>
//let ERROR = the value some methods will return incase of an error <type int>
//let EMPTY = the value the grid initializes to <type int>
//let VISITED = the value that represent a "visited" state, useful for the "visited graph" in graph theory applications <type int>
//let innerGrid = the 2d array that contains the information of the grid <type int[][]>
//let size = the side length of the grid <type int>
//****************************************************************************************
public class Grid {
  public static final int ERROR = -1;
  public static final int EMPTY = 0;
  public static final int VISITED = -10;

  private int[][] innerGrid;
  private int size;

  /**
   * Default constructor. Creates an empty grid with a specific size
   * 
   * @param size the side length of the grid
   */
  public Grid(int size) {
    this.size = size;
    initInnerGrid(this.size);
  }// end of Grid (Default constructor)

  /**
   * Overloaded constructor. Creates a grid with the same and information as a
   * given 2d integer array.
   * <p>
   * The 2d integer array should also be square shaped (same number of rows as
   * columns), in the case that it is not, the larger of the side length will be
   * used as the size of the grid and empty coordinates will just be left empty
   * (same value as EMPTY)
   * 
   * @param grid the 2d integer array the new Grid object will be based on
   */
  public Grid(int[][] grid) {
    size = Math.max(grid.length, grid[0].length);
    initInnerGrid(size);
    for (int i = 1; i <= size; i++)
      innerGrid[i - 1] = grid[i - 1].clone();
  }// end of Grid (Overloaded constructor)

  /**
   * Overloaded constructor. Creates a grid from a 1d integer array.
   * <p>
   * The integer array should have a size of n (n = m^2, m∈ℕ) (a perfect square),
   * the size of the grid would be set to m. In the case that the size of the
   * integer array is not a perfect square, the array would be treated as if it
   * has a size of the largest perfect square number that's smaller than the size
   * of the array, the extra values would be ignored.
   * <p>
   * For example, if an array of size 112 is given, the grid would be set to size
   * 10 and the array would be treated as if it was 100 elements long.
   * 
   * @param array the 1d integer array the new Grid object will be based on
   */
  public Grid(int[] array) {
    size = (int) (Math.ceil(Math.sqrt(array.length)) + 0.5);
    initInnerGrid(size);
    for (int row = 1; row <= size; row++) {
      for (int col = 1; col <= size; col++) {
        innerGrid[row - 1][col - 1] = array[(row - 1) * size + (col - 1)];
      }
    }
  }// end of Grid (Overloaded constructor)

  /**
   * initializes the innerGrid 2d integer array with a new 2d integer array
   * 
   * @param size the side length of the 2d integer array
   */
  private void initInnerGrid(int size) {
    innerGrid = new int[size][size];
  }// end of initInnerGrid method

  /**
   * mutator method for the grid, writes a specific value to a specific coordinate
   * to the grid, if the coordinate is in bound
   * 
   * @param coord the coordinate to write to
   * @param value the value to write
   * @return a boolean of weather the write action was successful, false if the
   *         action was unsuccessful because the coordinate is out of bounds
   */
  public boolean write(Coordinate coord, int value) {
    if (!inBounds(coord))
      return false;
    innerGrid[coord.getRow() - 1][coord.getCol() - 1] = value;
    return true;
  }// end of write method

  /**
   * accessor method for the grid, returns the number stored at a specific
   * coordinate, or ERROR if the coordinate is out of bounds
   * 
   * @param coord the coordinate to read from
   * @return the number stored at the coordinate, or ERROR if the coordinate is
   *         out of bounds
   */
  public int read(Coordinate coord) {
    if (!inBounds(coord))
      return ERROR;
    return innerGrid[coord.getRow() - 1][coord.getCol() - 1];
  }// end of read method

  /**
   * Helper method to check if a given coordinate is at the target (right bottom
   * corner) of the grid
   * 
   * @param coord the coordinate to be checked
   * @return boolean of if the given coordinate is at the target
   */
  public boolean atTarget(Coordinate coord) {
    return coord.getRow() == size && coord.getCol() == size;
  }// end of atTarget method

  /**
   * Helper method to check if a given coordinate is inside the grid
   * 
   * @param coord the coordinate to be checked
   * @return boolean of if the coordinate is inside the grid
   */
  public boolean inBounds(Coordinate coord) {
    return withinRange(coord.getRow()) && withinRange(coord.getCol());
  }// end of inBounds

  /**
   * Helper method to check if a given integer number is between 1 and the size of
   * the grid (inclusive)
   * <p>
   * useful for checking if a given coordinate is inside the grid
   * 
   * @param i the number to be checked
   * @return boolean of if the given integer number is between 1 and the size of
   *         the grid (inclusive)
   */
  private boolean withinRange(int i) {
    return 1 <= i && i <= size;
  }// end of withinRange method

  /**
   * Assessor method for the side length of the grid
   * 
   * @return the side length of the grid
   */
  public int getSize() {
    return size;
  }// end of getSize method

}// end of Grid class