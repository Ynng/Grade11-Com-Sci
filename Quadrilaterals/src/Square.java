public class Square extends Quadrilateral {
  protected double base = 1;
  private static int count = 0;
  private static int keyCount = 0;

  public Square() {
    if (getClass() == Square.class) {
      count++;
      keyCount++;
      key = getShapeName() + keyCount;
    }
  }

  public Square(double base) {
    this();
    this.base = base;
  }

  @Override
  double findArea() {
    return base * base;
  }

  @Override
  double findPerimeter () {
    return base * 4;
  }

  public String toString() {
    return String.format("[%s] - Width: %.2f, Key: %s", getShapeName(), base, key);
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