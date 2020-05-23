package shapes;

public class Rhombus extends Square{
  protected double height = 1;
  private static int count = 0;

  public Rhombus() {
    if (getClass() == Rhombus.class) {
      count++;
      key = getShapeName() + count;
    }
  }

  public Rhombus(double base, double height) {
    this();
    this.base = base;
    this.height = height;
  }

  @Override
  double findArea() {
    return base * height;
  }

  /**
   * @return the height
   */
  public double getHeight() {
    return height;
  }

  /**
   * @param height the height to set
   */
  public void setHeight(double height) {
    this.height = height;
  }

  public String toString() {
    return String.format("[%s] - Base: %.2f | Height: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f", getShapeName(), base, height, key, findArea(), findPerimeter());
  }

  public static int getCount() {
    return count;
  }

}