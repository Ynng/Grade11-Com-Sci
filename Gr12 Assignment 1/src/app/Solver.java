package app;

public class Solver {
  private Grid grid, visited;

  public void setGrid(Grid input) {
    grid = input;
  }

  public boolean solveGrid() {
    visited = new Grid(grid.getSize());
    return recurs(new Coordinate(1, 1));
  }

  private boolean recurs(Coordinate coord) {
    if (grid.atTarget(coord))
      return true;
    if (visited.read(coord) == Grid.VISITED)
      return false;

    visited.write(coord,Grid.VISITED);
    
    int dist = grid.read(coord);
    boolean output = false;
    Coordinate nextCoord;
    
    System.out.println(coord.toString() + ", val:" + dist);
    
    for (Direction dir : Direction.values()) {
      nextCoord = coord.move(dir, dist);
      if (grid.inBounds(nextCoord)) {
        output |= recurs(nextCoord);
      }
      if (output)
        break;
    }
    return output;
  }
}