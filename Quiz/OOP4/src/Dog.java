public abstract class Dog {
  private static int dogCount = 0;
  protected Dog(){
    dogCount++;
  }
  public static int getDogCount(){
    return dogCount;
  }

  public abstract double intakeFood();
  public abstract double outputWaste();
}