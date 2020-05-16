/**
 * Square
 */
public class Square extends Quadrilateral {
  protected double side1 = 1;
  private static int count = 0;
  private static int keyCount = 0;

  public Square() {
    if (getClass() == Square.class) {
      count++;
      keyCount++;
      key = getShapeName() + keyCount;
    }
  }

  public Square(double side1) {
    this();
    this.side1 = side1;
  }

  @Override
  double findArea() {
    return side1 * side1;
  }

  @Override
  double findParameter() {
    return side1 * 4;
  }

  public static int getCount(){
    return count;
  }

  public static int countDecrement(){
    count--;
    return count;
  }

  public String toString() {
    return "[Square] - Side1: " + side1 + ", Key: " + getKey();
  }

}