package app;

public class Grid {
  private int[][] innerGrid;

  public Grid() {
    innerGrid = new int[10][10];
  }

  public boolean write(int row, int col, int value) {
    if (row < 1 || row > 10 || col < 1 || col > 10)
      return false;
    innerGrid[row - 1][col - 1] = value;
    return true;
  }

  public int read(int row, int col, Direction dir, int dist) {
    
  }

  public int read(int row, int col) {
    if (row < 1 || row > 10 || col < 1 || col > 10)
      return -1;
    return innerGrid[row - 1][col - 1];
  }
}