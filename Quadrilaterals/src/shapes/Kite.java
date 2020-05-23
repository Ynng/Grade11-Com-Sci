package shapes;

public class Kite extends Quadrilateral {
  protected double diag1 = 1;
  protected double diag2 = 1;
  protected double side1 = 1;
  protected double side2 = 1;

  private static int count = 0;

  public Kite() {
    if (getClass() == Kite.class) {
      diag1 = 8;
      diag2 = 10;
      side1 = 5;
      side2 = Math.sqrt(7 * 7 + 4 * 4);
      count++;
      key = getShapeName() + count;
    }
  }

  public Kite(double diag1, double diag2, double side1, double side2) {
    this();
    this.diag1 = diag1;
    this.diag2 = diag2;
    this.side1 = side1;
    this.side2 = side2;
  }

  /**
   * @return the diag1
   */
  public double getDiag1() {
    return diag1;
  }

  /**
   * @param diag1 the diag1 to set
   */
  public void setDiag1(double diag1) {
    this.diag1 = diag1;
  }

  /**
   * @return the diag2
   */
  public double getDiag2() {
    return diag2;
  }

  /**
   * @param diag2 the diag2 to set
   */
  public void setDiag2(double diag2) {
    this.diag2 = diag2;
  }

  /**
   * @return the side1
   */
  public double getSide1() {
    return side1;
  }

  /**
   * @param side1 the side1 to set
   */
  public void setSide1(double side1) {
    this.side1 = side1;
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
  
  @Override
  double findArea() {
    return (diag1 * diag2) / 2;
  }

  @Override
  double findPerimeter() {
    return (side1 + side2) * 2;
  }

  public String toString() {
    return String.format("[%s] - Diagonal 1: %.2f | Diagonal 2: %.2f | Side 1: %.2f | Side 2: %.2f | Key: %s\n\t\t| Area : %.2f | Perimeter: %.2f", getShapeName(), diag1, diag2, side1, side2, key, findArea(), findPerimeter());
  }

  public static int getCount() {
    return count;
  }

}