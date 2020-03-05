package app;

public class Solver {
  private Grid grid;
  private boolean[][] visited;

  public void setGrid(Grid input) {
    grid = input;
  }

  public boolean solveGrid() {
    visited = new boolean[11][11];
    return recurs(new Coordinate(1, 1));
  }

  private boolean recurs(Coordinate coord) {
    if (coord.atTarget())
      return true;
    if (visited[coord.getRow()][coord.getCol()])
      return false;
    visited[coord.getRow()][coord.getCol()] = true;
    int dist = grid.read(coord);
    boolean output = false;

    for (Direction dir : Direction.values()) {
      coord.move(dir, dist);
      if (!coord.outBounds()) {
        output |= recurs(new Coordinate(coord));
      }
      coord.revert();
    }
    return output;
  }
}