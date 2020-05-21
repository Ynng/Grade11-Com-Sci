public abstract class Quadrilateral {
  private static int count;
  protected String key;

  protected Quadrilateral() {
    count += 1;
  }

  public static int getCount(){
    return count;
  }

  public static int countDecrement(){
    count--;
    return count;
  }

  public String getShapeName(){
    return getClass().getSimpleName();
  }

  public String getKey(){
    return key;
  }

  abstract double findArea();

  abstract double findPerimeter ();
}