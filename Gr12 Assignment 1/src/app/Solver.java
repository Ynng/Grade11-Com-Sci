package app;

public class Solver {
  private Grid grid, visited;

  public void setGrid(Grid input) {
    grid = input;
  }

  public boolean solveGrid() {
    visited = new Grid(grid.getSize());
    visited.write(new Coordinate(1, 1), Grid.VISITED);
    boolean output = recurs(new Coordinate(1, 1));
    printResult();
    return output;
  }

  private void printResult() {
    final String ANSI_NOT_REACHED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    Coordinate coord;
    for (int row = 1; row <= grid.getSize(); row++) {
      for (int col = 1; col <= grid.getSize(); col++) {
        coord = new Coordinate(row, col);
        if (visited.read(coord) != Grid.VISITED) {
          System.out.print(ANSI_NOT_REACHED);
        }
        System.out.printf("%-3s", grid.read(coord));
        if (visited.read(coord) != Grid.VISITED) {
          System.out.print(ANSI_RESET);
        }
      }
      System.out.println();
    }
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