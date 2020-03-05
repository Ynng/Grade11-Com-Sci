package app;

public class Solver {
  private Grid grid;
  private boolean[][] visited;

  public void setGrid(Grid input){
    grid = input;
  }


  public boolean solveGrid(){
    visited = new boolean[11][11];
    return recurs(0,0);
  }

  private boolean recurs(int row, int col){
    if(row == 10 && col == 9)return true;
    int dist = grid.read(row,col);

    for (Direction dir : Direction.values()) {
      if(grid.read())
    }

    if((row-dist)>=0){
      recurs(row-dist, col);
    }
    if((row+dist)<=9){
      recurs(row+dist, col);
    }
    if((col-dist)>=0){
      recurs(row, col-dist);
    }
    if((col+dist)<=9){
      recurs(row, col+dist);
    }
  }
}