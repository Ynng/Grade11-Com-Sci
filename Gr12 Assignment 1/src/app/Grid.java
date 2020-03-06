package app;

public class Grid {
  public static final int ERROR = -1;
  public static final int EMPTY = 0;
  public static final int VISITED = -10;



  private int[][] innerGrid;
  private int size;

  public Grid(int size){
    this.size = size;
    initInnerGrid(this.size);
  }

  public Grid(int[][] grid) {
    size = Math.max(grid.length, grid[0].length);
    initInnerGrid(size);
    for (int i = 1; i <= size; i++)
      innerGrid[i-1] = grid[i-1].clone();
  }

  public Grid(int[] array) {
    size = (int)(Math.ceil(Math.sqrt(array.length)) + 0.5);
    initInnerGrid(size);
    for(int row = 1; row <= size; row++) {
      for(int col = 1; col <= size; col++) {
        innerGrid[row-1][col-1] = array[(row-1) * size + (col-1)];
      }
    }
  }

  private void initInnerGrid(int size){
    innerGrid = new int[size][size];
  }

  public boolean write(Coordinate coord, int value) {
    if (inBounds(coord))
      return false;
    innerGrid[coord.getRow()-1][coord.getCol()-1] = value;
    return true;
  }

  public int read(Coordinate coord) {
    if (!inBounds(coord))
      return ERROR;
    return innerGrid[coord.getRow()-1][coord.getCol()-1];
  }

  public boolean atTarget(Coordinate coord) {
    return coord.getRow()==size && coord.getCol()==size;
  }

  public boolean inBounds(Coordinate coord){
    return withinRange(coord.getRow()) && withinRange(coord.getCol());
  }

  private boolean withinRange(int i){
    return 1<=i && i<=size;
  }

  public int getSize(){
    return size;
  }
  

}