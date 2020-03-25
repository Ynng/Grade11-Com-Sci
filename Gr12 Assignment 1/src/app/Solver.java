package app;

//****************************************************************************************
//Solver Class
//Kevin Huang
//Date: March 14th 2020
//Java 13.0.2, Visual Studio Code 1.43
//****************************************************************************************
//<Class>
//This class contains the logic for solving the problem described in the assignment rubric
//<List Of Identifiers>
//let grid = the grid to be traversed/tested <type Grid>
//let visited = the visited grid, used to temporarily store information to increase efficiency when searching for a path through the grid <type Grid>
//****************************************************************************************
public class Solver {
  private Grid grid, visited;

  /**
   * mutator method for the grid object, used to set a grid to be tested.
   * 
   * @param input the grid object to be tested
   */
  public void setGrid(Grid input) {
    grid = input;
  }// end of setGrid method

  /**
   * attempts to find a path from the left top corner of the grid to the right
   * bottom corner of the grid
   * 
   * @return if a path from the left top corner to the right bottom corner
   *         exists
   */
  public boolean solveGrid() {
    visited = new Grid(grid.getSize());
    visited.write(new Coordinate(1, 1), Grid.VISITED);
    return recurs(new Coordinate(1, 1));
  }// end of solveGrid method

  /**
   * the recursive function that calls itself recursively in an attempt to find a
   * path from the left top corner of the grid
   * <p>
   * the method checks all four possible movement directions to see if the
   * resulting coordinate is both inbound and not visited before, if so, set that
   * coordinate to visited so we know we dont have to check that coordinate again
   * if we ever see it again in the future,than call the recursive method on the
   * resulting coordinate.
   * <p>
   * The base case is if the current coordinate is at the target. Skip all logic
   * and return true if so. Otherwise, perform the recursive routine described above and if any of
   * the recursive calls returns true, skip all remaining logic and return true.
   * If all recursive calls return false, return false.
   * <p>
   * An out of bounds coordinate is considered false.
   * 
   * @param coord the coordinate to explore from
   * @return true if a path exists from the current coordinate to the target (right bottom corner)
   */
  private boolean recurs(Coordinate coord) {
    if (grid.atTarget(coord))
      return true;

    int dist = grid.read(coord);
    boolean output = false;
    Coordinate nextCoord;

    System.out.println(coord.toString() + ", val:" + dist);

    for (Direction dir : Direction.values()) {
      nextCoord = coord.move(dir, dist);
      if (grid.inBounds(nextCoord)) {
        if (visited.read(nextCoord) != Grid.VISITED) {
          visited.write(nextCoord, Grid.VISITED);
          output |= recurs(nextCoord);
        }
      }
      if (output)
        break;
    }
    return output;
  }//end of recurs method
}//end of Solver class