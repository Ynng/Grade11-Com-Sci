public abstract class Quadrilateral {
  private static int count;
  protected String key;

  public Quadrilateral() {
    count += 1;
  }

  public static int getQuadCount() {
    return count;
  }

  public static int quadCountDecrement(){
    count--;
    return count;
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

  abstract double findArea();

  abstract double findParameter();

  public String getKey(){
    return key;
  }
}