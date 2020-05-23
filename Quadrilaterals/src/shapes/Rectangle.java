package shapes;

public class Rectangle extends Rhombus {
  private static int count = 0;

  public Rectangle() {
    if (getClass() == Rectangle.class) {
      count++;
      key = getShapeName() + count;
    }
  }

  public Rectangle(double base, double height) {
    this();
    this.base = base;
    this.height = height;
  }

  @Override
  double findPerimeter () {
    return (base + height) * 2;
  }

  public static int getCount() {
    return count;
  }

}