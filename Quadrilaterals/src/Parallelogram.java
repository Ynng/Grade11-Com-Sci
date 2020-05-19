public class Parallelogram extends Rectangle{
  protected double side1 = 1;
  private static int count = 0;
  private static int keyCount = 0;

  public Parallelogram() {
    if (getClass() == Parallelogram.class) {
      count++;
      keyCount++;
      key = getShapeName() + keyCount;
    }
  }

  public Parallelogram(double base, double height, double side1) {
    this();
    this.base = base;
    this.height = height;
    this.side1 = side1;
  }

  @Override
  double findArea() {
    return base * height;
  }

  @Override
  double findPerimeter () {
    return (base + side1) * 2;
  }

  public String toString() {
    return String.format("[%s] - Base: %f, Height: %f, Side: %f, Key: %s", getShapeName(), base, height, side1, key);
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