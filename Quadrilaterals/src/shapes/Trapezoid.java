package shapes;

public class Trapezoid extends Parallelogram {
  protected double top = 1;
  protected double side2 = 1;
  private static int count = 0;
  private static int keyCount = 0;

  public Trapezoid() {
    if (getClass() == Trapezoid.class) {
      base = 6;
      top = 2;
      side = 3;
      side2 = 5;

      count++;
      keyCount++;
      key = getShapeName() + keyCount;
    }
  }

  public Trapezoid(double base, double top, double height, double side, double side2) {
    this();
    this.base = base;
    this.top = top;
    this.height = height;
    this.side = side;
    this.side2 = side2;
  }

  @Override
  double findArea() {
    return ((base + top) / 2) * height;
  }

  @Override
  double findPerimeter() {
    return base + top + side + side2;
  }

  /**
   * @return the top
   */
  public double getTop() {
    return top;
  }
  
  /**
   * @param top the top to set
   */
  public void setTop(double top) {
    this.top = top;
  }

  /**
   * @return the side2
   */
  public double getSide2() {
    return side2;
  }

  /**
   * @param side2 the side2 to set
   */
  public void setSide2(double side2) {
    this.side2 = side2;
  }

  public String toString() {
    return String.format("[%s] - Base: %.2f | Top: %.2f | Height: %.2f | Side1: %.2f | Side2: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f", getShapeName(), base, top, height, side, side2, key, findArea(), findPerimeter());
  }

  public static int getCount() {
    return count;
  }

  public static int countDecrement() {
    Quadrilateral.countDecrement();
    count--;
    return count;
  }
}