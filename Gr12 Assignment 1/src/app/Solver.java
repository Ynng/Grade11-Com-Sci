package app;

public class Solver {
  private Grid grid, visited;

  public void setGrid(Grid input) {
    grid = input;
  }

  public boolean solveGrid() {
    visited = new Grid(grid.getSize());
    visited.write(new Coordinate(1, 1), Grid.VISITED);
    return recurs(new Coordinate(1, 1));
  }

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
  }
}