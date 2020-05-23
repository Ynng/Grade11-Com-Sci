package shapes;

public class Parallelogram extends Rectangle{
  protected double side = 1;
  private static int count = 0;

  public Parallelogram() {
    if (getClass() == Parallelogram.class) {
      count++;
      key = getShapeName() + count;
    }
  }

  public Parallelogram(double base, double height, double side) {
    this();
    this.base = base;
    this.height = height;
    this.side = side;
  }

  @Override
  double findArea() {
    return base * height;
  }

  @Override
  double findPerimeter () {
    return (base + side) * 2;
  }

  /**
   * @return the side
   */
  public double getSide() {
    return side;
  }

  /**
   * @param side the side to set
   */
  public void setSide(double side) {
    this.side = side;
  }

  /**
   * Returns a string representation of the object.
   * 
   * @return a string containing useful information about the shape
   */
  public String toString() {
    return String.format("[%s] - Base: %.2f | Height: %.2f | Side: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f", getShapeName(), base, height, side, key, findArea(), findPerimeter());
  }

  public static int getCount() {
    return count;
  }

}