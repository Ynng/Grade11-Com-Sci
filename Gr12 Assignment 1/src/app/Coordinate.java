package app;

public class Coordinate {
  private int row, col, lastRow, lastCol;

  public Coordinate(Coordinate original) {
    this.row = original.getRow();
    this.col = original.getCol();
    lastRow = row;
    lastCol = col;
  }

  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
    lastRow = row;
    lastCol = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public void move(Direction dir, int dist) {
    lastRow = row;
    lastCol = col;
    switch (dir) {
      case LEFT:
        row -= dist;
      case RIGHT:
        row += dist;
      case UP:
        col += dist;
      case DOWN:
        col -= dist;
    }
  }

  public void revert(){
    row = lastRow;
    col = lastCol;
  }

  public boolean outBounds() {
    if (row < 1 || row > 10 || col < 1 || col > 10)
      return true;
    return false;
  }

  public boolean atTarget(){
    return row==10&&col==10;
  }
}