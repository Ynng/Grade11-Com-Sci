public class Kite extends Quadrilateral {
  protected double diag1 = 1;
  protected double diag2 = 1;
  protected double side1 = 1;
  protected double side2 = 1;

  private static int count = 0;
  private static int keyCount = 0;

  public Kite() {
    if (getClass() == Kite.class) {
      diag1 = 8;
      diag2 = 10;
      side1 = 5;
      side2 = Math.sqrt(7 * 7 + 4 * 4);
      count++;
      keyCount++;
      key = getShapeName() + keyCount;
    }
  }

  public Kite(double diag1, double diag2, double side1, double side2) {
    this();
    this.diag1 = diag1;
    this.diag2 = diag2;
    this.side1 = side1;
    this.side2 = side2;
  }

  @Override
  double findArea() {
    return (diag1 * diag2) / 2;
  }

  @Override
  double findPerimeter() {
    return (side1 + side2) * 2;
  }

  public String toString() {
    return String.format("[%s] - Diagonal 1: %.2f | Diagonal 2: %.2f | Side 1: %.2f | Side 2: %.2f | Key: %s", getShapeName(), diag1, diag2, side1, side2, key);
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