public class Rectangle extends Square {
  protected double side2 = 1;
  private static int count = 0;
  private static int keyCount = 0;

  public Rectangle() {
    if (getClass() == Rectangle.class) {
      count++;
      keyCount++;
      key = getShapeName() + keyCount;
    }
  }

  public Rectangle(double side1, double side2) {
    this();
    this.side1 = side1;
    this.side2 = side2;
  }

  @Override
  double findArea() {
    return side1 * side2;
  }

  @Override
  double findParameter() {
    return (side1 + side2) * 2;
  }

  public static int getCount(){
    return count;
  }

  public static int countDecrement(){
    count--;
    return count;
  }

  public String toString() {
    return "[Rectangle] - Side1: " + side1 + ", Side2: " + side2 + ", Key: " + getKey();
  }
}