package app;

public class Grid {
  private int[][] innerGrid;

  public Grid(int ){
    innerGrid = new int[10][10];
  }

  public Grid(int[][] grid) {
    innerGrid = new int[10][10];
    for (int i = 0; i < matrix.length; i++)
      myInt[i] = matrix[i].clone();
  }

  public boolean write(Coordinate coord, int value) {
    if (coord.outBounds())
      return false;
    innerGrid[coord.getRow()][coord.getCol()] = value;
    return true;
  }

  public int read(Coordinate coord) {
    if (coord.outBounds())
      return -1;
    return innerGrid[coord.getRow()][coord.getCol()];
  }
}