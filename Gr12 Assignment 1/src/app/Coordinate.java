package app;

public class Coordinate {
  private int row, col;

  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public String toString(){
    return String.format("row:%d, col:%d", row, col);
  }

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
  }
}